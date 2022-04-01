package kr.co.m2m.instagram.common.service;

import java.util.List;
import org.springframework.context.annotation.Description;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.co.m2m.framework.web.model.FileVO;

public interface CommonService {

  @Description("업로드 된 파일을 지정된 폴더에 저장")
  public List<FileVO> fileUploadResult(MultipartHttpServletRequest mRequest);

  @Description("업로드 된 파일 1개를 지정된 폴더에 저장")
  public FileVO fileUploadResultOne(MultipartFile mFile, String flDsc, String pathDivCd,
      String sysDsc, String tempYn);
  
  public FileVO saveSvrFileData(MultipartFile mFile, String flDsc);
  
  public List<FileVO> saveSvrFileDataAll(List<MultipartFile> mFiles, String flDsc);
}
