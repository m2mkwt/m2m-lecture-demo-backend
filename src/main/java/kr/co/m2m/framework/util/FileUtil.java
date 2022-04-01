package kr.co.m2m.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.co.m2m.framework.exception.CustomException;
import kr.co.m2m.framework.web.model.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("fileUtil")
public class FileUtil {

  private static String[] FILEHANDLEALLOW;

  private static String pathShare;
  private static String pathTmp;
  private static String urlShare;
  private static String urlTmp;
  private static String pathTempShare;

  @Value("${spring.file.upload.path}")
  private String _pathShare;
  // private String _pathShare = "C:\\00_WORK\\workspace\\frontend\\m2m-lecture-demo-frontend\\public\\upload";
  
  @Value("${spring.file.upload.url}")
  private String _urlShare;
  // private String _urlShare = "/upload";

  @PostConstruct
  public void init() {
    pathShare = _pathShare;
    pathTmp = _pathShare;
    pathTempShare = _pathShare;
    urlShare = _urlShare;
    urlTmp = _urlShare;
  }

  public static void move(File orgFile, File targetFile) throws Exception {
    if (targetFile.isFile()) {
      targetFile.delete();
    }
    copy(orgFile, targetFile);
    orgFile.delete();
  }

  /**
   * 설명 : 파일 복사
   */
  public static void copy(File orgFile, File targetFile) throws Exception {

    if (!targetFile.getParentFile().exists()) {
      targetFile.getParentFile().mkdirs();
    }

    try (FileInputStream inputStream = new FileInputStream(orgFile);
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        FileChannel fcin = inputStream.getChannel();
        FileChannel fcout = outputStream.getChannel()) {

      long size = fcin.size();
      fcin.transferTo(0, size, fcout);
    } catch (Exception e) {
      log.error("파일 복사 오류", e);
      throw e;
    }
  }

  /**
   * 파일 삭제
   *
   * @param targetFile : 대상 파일
   * @throws Exception
   */
  public static void delete(File targetFile) throws Exception {
    targetFile.delete();
  }

  /**
   * 설명 : 파일다운로드를 위해 파일을 읽어 HttpServletResponse 에 쓴다
   */
  public static void writeFileToResponse(HttpServletResponse response, File file) {

    if (!file.exists()) {
      log.warn("파일 {} 이 존재하지 않음", file);
      return;
    }

    try (OutputStream out = response.getOutputStream()) {
      Path path = file.toPath();
      Files.copy(path, out);
      out.flush();
    } catch (IOException e) {
      log.error("writeFileToResponse 에러", e);
    }
  }

  /**
   * 설명 : URL공유가 필요한 파일의 임시폴더 파일을 삭제한다.
   *
   * @param fileName 파일명(확장자포함)
   * @param midPath 중간경로(기본절대경로(C:/amarket/upload/image/tmpshare)와 파일명(20170707_39949776507520.png)을
   *        제외한 경로명(SM/SAMP) ex>
   *        C:/amarket/upload/image/tmpshare/SM/SAMP/20170707_39949776507520.png)
   * 
   * @throws Exception
   */
  public static void deleteTempFile(String fileName) throws Exception {
    FileUtil.deleteTempFile(fileName, "");
  }

  public static void deleteTempFile(String fileName, String midPath) throws Exception {
    String path = pathTempShare;
    log.debug("[ deleteTempFile ] path : {}, midPath : {}, fileName : {}", path, midPath, fileName);

    File file = new File(path + FileUtil.getCombinedPath(midPath, fileName));
    log.debug("임시경로의 파일을 삭제: {}", file);
    delete(file);
  }

  /**
   * <pre>
   * 작성일 : 2017. 5. 25.
   * 작성자 : wtKim
   * 설명   : MultipartHttpServletRequest 에서 첨부파일 정보를 추출하여 targetPath 에 파일을 생성하고
   *          파일 정보를 목록으로 반환한다.
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2017. 5. 25. wtKim - 최초생성
   * </pre>
   *
   * @param request HttpServletRequest
   * @param targetPath 파일이 복사될 경로
   * @return
   */
  public static List<FileVO> getFileListFromRequest(HttpServletRequest request, String targetPath) {
    MultipartHttpServletRequest mRequest;
    if (request instanceof MultipartHttpServletRequest) {
      mRequest = (MultipartHttpServletRequest) request;
    } else {
      return null;
    }

    Iterator<String> fileIter = mRequest.getFileNames();
    List<FileVO> fileVOList = new ArrayList<>();
    String fileOrgName;
    String extension;
    String fileName;
    File file;
    String path;
    List<MultipartFile> files;
    FileVO fileVO;

    try {

      while (fileIter.hasNext()) {
        files = mRequest.getMultiFileMap().get(fileIter.next());
        for (MultipartFile mFile : files) {

          fileOrgName = mFile.getOriginalFilename();
          extension = FilenameUtils.getExtension(fileOrgName);

          fileName = System.currentTimeMillis() + "." + extension;
          path = File.separator + getNowdatePath();
          file = new File(targetPath + path + File.separator + fileName);

          if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
          }

          log.debug("원본파일 : {}", mFile);
          log.debug("대상파일 : {}", file);
          mFile.transferTo(file);
          fileVO = new FileVO();
          fileVO.setFileExtension(extension);
          fileVO.setFileOrgName(fileOrgName);
          fileVO.setFileSize(mFile.getSize());
          fileVO.setFileType(mFile.getContentType());
          fileVO.setFilePath(path);
          fileVO.setFileName(fileName);
          fileVOList.add(fileVO);
        }
      }

    } catch (IllegalStateException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    } catch (IOException e) {
      throw new CustomException("FILE_ERROR_CODE_DEFAULT", e);
    }
    return fileVOList;
  }

  /**
   * 설명 : URL공유가 필요한 파일의 임시저장 경로를 반환한다.
   */
  public static String getTempPath(String... directories) {
    StringBuilder path = new StringBuilder(pathTempShare);
    path.append(getCombinedPath(directories));

    return path.toString();
  }

  public static String getRealPath(String... directories) {
    StringBuilder path = new StringBuilder(pathShare);
    path.append(getCombinedPath(directories));

    return path.toString();
  }

  /**
   * 설명 : 경로 정보를 받아 디렉토리 구분자를 더한 경로 문자열을 반환한다. gtCombinedPath("temp", "img", "common") ->
   * "/temp/img/common"
   */
  public static String getCombinedPath(String... str) {
    StringBuilder path = new StringBuilder();
    for (String d : str) {
      if (!StringUtil.isEmpty(d) && !"null".equalsIgnoreCase(d)) {
        path.append(File.separator).append(FileUtil.getAllowedFilePath(d));
      }
    }
    return path.toString();
  }

  /**
   * 일자 계산
   */
  public static String calDate(String date_format) {
    DateFormat df = new SimpleDateFormat(date_format);
    Calendar calendar = Calendar.getInstance();
    return df.format(calendar.getTime());
  }

  /**
   * 설명 : 현재 날짜로 년월 경로 문자열을 반환한다. 2017년 07월 07일 -> "201707"
   */
  public static String getNowYearMonPath() {
    return FileUtil.getNowYearMonPath("");
  }

  public static String getNowYearMonPath(String split) {
    String yyyymmdd = calDate("yyyyMMdd");
    return yyyymmdd.substring(0, 4) + split + yyyymmdd.substring(4, 6);
  }

  /**
   * 설명 : 현재 날짜로 년/월/일 경로 문자열을 반환한다. 2017년 07월 07일 -> "2017/07/07"
   */
  public static String getNowdatePath() {
    String yyyymmdd = calDate("yyyyMMdd");
    return yyyymmdd.substring(0, 4) + File.separator + yyyymmdd.substring(4, 6) + File.separator
        + yyyymmdd.substring(6);
  }

  /**
   * 설명 : "YYYYMMDD_ds8sdfj20j..." 형식의 문자열을 YYYY/MM/DD/ds8sdfj20j... 형식의 경로 문자열로 반환한다. 에디터 업로드 이미지
   * 등에서 사용
   */
  public static String getDatePath(String fileName) {
    String[] temp = fileName.split("_");
    String result = fileName;

    if (temp.length > 1) { // 섬네일은 _가 뒤에 더 들어가서 1보다 크면으로 처리
      result = temp[0].substring(0, 4) + File.separator;
      result += temp[0].substring(4, 6) + File.separator;
      result += temp[0].substring(6) + File.separator;
      // if (temp.length == 2) {
      // result += temp[1];
      // } else {
      // result += fileName.substring(fileName.indexOf("_") + 1);
      // }
      result += fileName;
    }

    return result;
  }

  /**
   * 설명 : 폴더 내역 삭제 안의 파일 정보까지 모든걸 삭제하는 로직 처리
   */
  public static void deleteFolder(String parentPath) {
    // 보안 지적사항 대응 (HttpResponseSpilitting)
    File file = new File(getAllowedFilePath(parentPath));
    String childPath;
    File f;
    if (file != null && file.exists()) {
      for (String fileName : file.list()) {
        childPath = parentPath + File.separator + fileName;
        // 보안 지적사항 대응 (HttpResponseSpilitting)
        f = new File(getAllowedFilePath(childPath));
        if (!f.isDirectory()) {
          f.delete();
        } else {
          deleteFolder(childPath);
        }
      }
      // 보안 지적사항 대응 (HttpResponseSpilitting)
      file = new File(getAllowedFilePath(parentPath));
      file.delete();
    }
  }

  /**
   * 설명 : 파일이나 폴더 저장시 특수문자 경로 문자열 제거
   */
  public static String replaceFileNm(String str) {
    String reStr = str;
    if (StringUtil.isEmpty(reStr)) {
      return reStr;
    }
    reStr = reStr.replaceAll("&", "");
    reStr = reStr.replaceAll("!", "");
    reStr = reStr.replaceAll("#", "");
    reStr = reStr.replaceAll("$", "");
    reStr = reStr.replaceAll("%", "");
    reStr = reStr.replaceAll("^", "");
    reStr = reStr.replaceAll("~", "");
    reStr = reStr.replaceAll("`", "");
    reStr = reStr.replaceAll("'", "");
    reStr = reStr.replaceAll(";", "");
    return reStr;
  }

  /**
   * 설명 : 파일 결로명에서 허용하지 않은 문자열을 제거 하여 반환
   */
  public static String getAllowedFilePath(String filePath) {
    return getAllowedFilePath(replaceFileNm(filePath), false);
  }

  public static String getAllowedFilePath(String filePath, boolean pathCheck) {

    filePath = StringUtil.replaceAll(filePath, "../", "");
    filePath = StringUtil.replaceAll(filePath, "..", "");
    filePath = StringUtil.replaceAll(filePath, "\\uc0", "");

    if (pathCheck) {
      if (FILEHANDLEALLOW.length > 0) {

        boolean isAllowed = false;

        for (String allowPath : FILEHANDLEALLOW) {
          if (filePath.startsWith(allowPath)) {
            isAllowed = true;
            break;
          }
        }

        if (!isAllowed) {
          throw new CustomException("FILE_ERROR_CODE_DEFAULT");
        }
      }
    }
    log.debug("#### return filePath : {} ", filePath);

    return filePath;
  }

  /**
   * 설명 : 임시폴더의 이미지를 운영폴더로 복사한다.
   */
  public static void copyImageUrl(String fileName, String fileTmpPath, String bsnDsc, String flDsc,
      String filePath) throws Exception {
    log.debug("[ copyImageUrl ] fileName : {}, filePath : {}", fileName, filePath);

    String targetPath = FileUtil.getRealAtchFilePath(filePath);
    File orgFile = new File(FileUtil.getCombinedPath(FileUtil.getRealAtchFilePath(fileTmpPath),
        bsnDsc, flDsc, fileName));
    File targetFile = new File(targetPath + FileUtil.getCombinedPath(bsnDsc, flDsc,
        FileUtil.getNowdatePath(), fileName.substring(fileName.lastIndexOf("_") + 1)));

    log.debug("[ copyImageUrl ] targetPath : {}", targetPath);
    log.debug("이미지 파일을 임시경로에서 실제 서비스 경로로 복사 : {} -> {}", orgFile, targetFile);
    copy(orgFile, targetFile);
    delete(orgFile);
  }

  /**
   * 설명 : 임시폴더의 이미지를 운영폴더로 복사한다.
   */
  public static void copyImageUrl(String fileTmpName, String fileTmpPath, String fileRealName,
      String fileRealPath) throws Exception {
    log.debug(
        "[ copyImageUrl ] fileTmpName : {}, fileTmpPath : {} -> fileRealName : {}, fileRealPath : {}",
        fileTmpName, fileTmpPath, fileRealName, fileRealPath);

    File orgFile =
        new File(FileUtil.getCombinedPath(FileUtil.getRealAtchFilePath(fileTmpPath), fileTmpName));
    File targetFile = new File(
        FileUtil.getCombinedPath(FileUtil.getRealAtchFilePath(fileRealPath), fileRealName));

    log.debug("이미지 파일을 임시경로에서 실제 서비스 경로로 복사 orgFile: {} -> targetFile: {}", orgFile, targetFile);
    copy(orgFile, targetFile);
    delete(orgFile);
  }

  /**
   * 설명 : 운영폴더의 이미지 파일을 삭제한다.
   */
  public static void deleteImageUrl(String svrFileName, String svrPath) throws Exception {
    FileUtil.deleteImageUrl(svrFileName, null, svrPath);
  }

  public static void deleteImageUrl(String svrFileName, String fileUrlShare, String svrPath)
      throws Exception {
    StringBuilder fullPathFile = new StringBuilder();
    fullPathFile.append(FileUtil.getRealAtchFilePath(svrPath)).append(File.separator)
        .append(svrFileName);
    File file = new File(getAllowedFilePath(fullPathFile.toString()));
    log.debug("운영폴더의 이미지 파일을 삭제: {}", file);
    delete(file);
  }

  public static String getUploadFilePath(String flDsc, boolean isTemp) {
    StringBuilder path = new StringBuilder();
    String flDscPath = StringUtil.replaceAll(flDsc, "_", File.separator);
    String datePath = FileUtil.getNowdatePath();
    if (isTemp) {
      path.append(FileUtil.getTempPath(flDscPath));
    } else {
      if (StringUtil.isEmpty(flDscPath)) {
        path.append(FileUtil.getRealPath(flDscPath, datePath));
      } else {
        path.append(FileUtil.getRealPath(flDscPath));
      }
    }
    return path.toString();
  }

  public static String getUploadFileUrl(String flDsc, boolean isTemp) {
    StringBuilder url = new StringBuilder();
    String flDscPath = StringUtil.replaceAll(flDsc, "_", File.separator);
    String datePath = FileUtil.getNowdatePath();
    if (isTemp) {
      url.append(urlTmp).append(FileUtil.getCombinedPath(flDscPath));

    } else {
      if (StringUtil.isEmpty(flDscPath)) {
        url.append(urlShare).append(FileUtil.getCombinedPath(flDscPath, datePath));
      } else {
        url.append(urlShare).append(FileUtil.getCombinedPath(flDscPath));
      }
    }
    log.debug("### flDsc : {}, isTemp : {}, => URL : {} ###", flDsc, isTemp, url.toString());
    return url.toString();
  }

  public static String getRealSvrPath(String path) {
    log.debug("input path : {}", path);
    String realPath = path;
    if (StringUtil.isEmpty(realPath)) {
      return realPath;
    } else {
      if (realPath.startsWith(urlShare)) {
        realPath = StringUtil.replace(realPath, urlShare, pathShare, false, true);
      } else if (realPath.startsWith(urlTmp)) {
        realPath = StringUtil.replace(realPath, urlTmp, pathTmp, false, true);
      }
    }
    log.debug("return path : {}", realPath);
    return realPath;
  }

  /**
   * 설명 : 치환처리된 가상경로를 원래의 실제 서버경로로 치환하여 반환한다. /us/bc/evl/img/2017/10/30 =>
   * /nas_data/urlshare/bc/evl/img/2017/10/30
   */
  public static String getRealAtchFilePath(String path) {
    // log.debug("input path : {}", path);
    String atchFilePath = path;
    if (StringUtil.isEmpty(atchFilePath)) {
      return atchFilePath;
    } else {
      atchFilePath =
          StringUtil.replace(atchFilePath, "", pathShare, false, true);
    }
    // log.debug("return path : {}", atchFilePath);
    return atchFilePath;
  }
}
