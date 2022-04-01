package kr.co.m2m.instagram.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.co.m2m.framework.exception.CustomException;
import kr.co.m2m.framework.util.FileUtil;
import kr.co.m2m.framework.util.GenericUtil;
import kr.co.m2m.framework.util.StringUtil;
import kr.co.m2m.framework.web.common.CommonConstants;
import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("commonService")
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {

  @Description("업로드 된 파일을 지정된 폴더에 저장")
  @Override
  public List<FileVO> fileUploadResult(MultipartHttpServletRequest mRequest) {
    String tmpPathDivCd = mRequest.getParameter("tmpPathDivCd"); // 임시경로구분코드
    String bsnDsc = mRequest.getParameter("bsnDsc"); // 업무코드
    String flDsc = mRequest.getParameter("flDsc"); // 하위디렉토리
    FileVO result = null;
    List<FileVO> resultList = new ArrayList<>();
    Iterator<String> fileIter = mRequest.getFileNames();

    try {
      List<MultipartFile> files = null;

      while (fileIter.hasNext()) {
        files = mRequest.getMultiFileMap().get(fileIter.next());
        for (MultipartFile mFile : files) {
          // 업로드 된 파일 1개를 지정된 폴더에 저장
          result = this.fileUploadResultOne(mFile, flDsc, tmpPathDivCd, null, null);
          resultList.add(result);
        }
      }

      log.debug("### CommonServiceImpl fileUploadResult resultList : {}", resultList);
      return resultList;
    } catch (IllegalStateException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    }
  }

  @Description("업로드 된 파일 1개를 지정된 폴더에 저장")
  @Override
  public FileVO fileUploadResultOne(MultipartFile mFile, String flDsc, String pathDivCd,
      String sysDsc, String tempYn) {
    FileVO result = null;
    String[] fileFilter = new String[] {"pptx", "ppt", "xls", "xlsx", "doc", "docx", "hwp", "pdf",
        "gif", "png", "jpg", "jpeg"};

    try {
      String fileOrgName = "";
      String extension = "";
      StringBuilder fileName = null;
      File file = null;
      boolean isTemp = CommonConstants.YN_Y.equals(tempYn) ? true : false;

      if (StringUtil.isEmpty(sysDsc)) {
        fileOrgName = mFile.getOriginalFilename();
        extension = FilenameUtils.getExtension(fileOrgName);

        boolean checkExe = true;

        for (String ex : fileFilter) {
          if (ex.equalsIgnoreCase(extension)) {
            checkExe = false;
          }
        }

        if (checkExe) {
          throw new CustomException("FILE_ERROR_CODE_DEFAULT");
        }
      } else {
        fileOrgName = String.valueOf(System.currentTimeMillis());
        extension = "jpg";
      }

//      if (mFile.getSize() > fileSize) {
//        throw new CustomException("FILE_ERROR_CODE_DEFAULT");
//      }

      /**********************************************************************
       * tempYn에 따른 파일정보 설정 Start
       **********************************************************************/
      fileName = new StringBuilder();
      if (isTemp) {
        // 임시파일명 예시 : 20170623_b2c_12345678901234.jpg
        fileName.append(GenericUtil.genericDateId("yyyyMMdd", "b2c_", 14)).append(".")
            .append(extension);

      } else {
        // 서버파일명 예시 : 12345678901234.jpg
        fileName.append(GenericUtil.generateNum(14)).append(".").append(extension);
      }
      // 저장할 전체폴더정보 설정
      String saveAbsPath = FileUtil.getUploadFilePath(flDsc, isTemp);
      file = new File(new StringBuilder().append(saveAbsPath).append(File.separator)
          .append(fileName.toString()).toString());

      // URL접근 정보 설정
      String saveUrl = FileUtil.getUploadFileUrl(flDsc, isTemp);

      // 디렉토리 없으면 생성
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }

      log.debug("원본파일 : {}", mFile);
      log.debug("대상파일 : {}", file);
      mFile.transferTo(file);
      result = new FileVO();
      result.setFileOrgName(fileOrgName);
      result.setFileName(fileName.toString());
      result.setFilePath(saveAbsPath);
      result.setFileUrl(saveUrl);
      result.setFileSize(mFile.getSize());
      result.setFileType(mFile.getContentType());
      result.setFileExtension(extension);
      result.setTempYn(tempYn);

      log.debug("### CommonServiceImpl fileUploadResultOne result : {}", result);
      return result;
    } catch (IllegalStateException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    } catch (IOException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    }
  }

  @Override
  public FileVO saveSvrFileData(MultipartFile mFile, String flDsc) {
    FileVO result = null;
    String[] fileFilter = new String[] {"gif", "png", "jpg", "jpeg"};
    try {
      String fileOrgName = mFile.getOriginalFilename();
      String extension = FilenameUtils.getExtension(fileOrgName);
      StringBuilder fileName = new StringBuilder();
      fileName.append(GenericUtil.genericDateId("yyyyMMdd", "_", 14)).append(".")
      .append(extension);

      boolean checkExe = true;
      for (String ex : fileFilter) {
        if (ex.equalsIgnoreCase(extension)) {
          checkExe = false;
        }
      }
      if (checkExe) {
        throw new CustomException("FILE_ERROR_CODE_DEFAULT");
      }
      String saveAbsPath = FileUtil.getUploadFilePath(flDsc, false);
      File file = new File(new StringBuilder().append(saveAbsPath).append(File.separator)
          .append(fileName.toString()).toString());

      // URL접근 정보 설정
      String saveUrl = FileUtil.getUploadFileUrl(flDsc, false);
      if (saveUrl != null) {
        saveUrl = saveUrl.replaceAll(Matcher.quoteReplacement(File.separator), "/");
      }
      
      // 디렉토리 없으면 생성
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      log.debug("원본파일 : {}", mFile);
      log.debug("대상파일 : {}", file);

      mFile.transferTo(file);
      result = new FileVO();
      result.setFileOrgName(fileOrgName);
      result.setFileName(fileName.toString());
      result.setFilePath(saveAbsPath);
      result.setFileUrl(saveUrl);
      result.setFileSize(mFile.getSize());
      result.setFileType(mFile.getContentType());
      result.setFileExtension(extension);

      log.debug("### CommonServiceImpl fileUploadResultOne result : {}", result);
      return result;
    } catch (IllegalStateException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    } catch (IOException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    }
  }

  @Override
  public List<FileVO> saveSvrFileDataAll(List<MultipartFile> fileList, String flDsc) {
    String[] fileFilter = new String[] {"gif", "png", "jpg", "jpeg"};
    List<FileVO> resultFileList = new ArrayList<FileVO>();
    try {
      FileVO result = null;
      StringBuilder fileName = null;
      for (MultipartFile mFile : fileList) {
        String fileOrgName = mFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileOrgName);

        boolean checkExe = true;
        for (String ex : fileFilter) {
          if (ex.equalsIgnoreCase(extension)) {
            checkExe = false;
          }
        }
        if (checkExe) {
          throw new CustomException("FILE_ERROR_CODE_DEFAULT");
        }
        String saveAbsPath = FileUtil.getUploadFilePath(flDsc, false);
        File file = new File(new StringBuilder().append(saveAbsPath).append(File.separator)
            .append(fileName.toString()).toString());

        // URL접근 정보 설정
        String saveUrl = FileUtil.getUploadFileUrl(flDsc, false);
        // 디렉토리 없으면 생성
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        log.debug("원본파일 : {}", mFile);
        log.debug("대상파일 : {}", file);

        mFile.transferTo(file);
        result = new FileVO();
        result.setFileOrgName(fileOrgName);
        result.setFileName(fileName.toString());
        result.setFilePath(saveAbsPath);
        result.setFileUrl(saveUrl);
        result.setFileSize(mFile.getSize());
        result.setFileType(mFile.getContentType());
        result.setFileExtension(extension);
        log.debug("### CommonServiceImpl fileUploadResultOne result : {}", result);

        resultFileList.add(result);
      }
    } catch (IllegalStateException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    } catch (IOException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    }
    return resultFileList;
  }

}
