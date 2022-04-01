package kr.co.m2m.instagram.common.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@RestController
@RequestMapping("/api/v1/common")
public class CommonController {

  @Autowired
  private CommonService commonService;
  
  @CrossOrigin("*")
  @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
  public ResponseEntity<? extends BasicResponse> requestUploadImage(@RequestParam("fileList") MultipartFile mFile) {
    FileVO file = null;
    log.info("[uploadImg] mFile : {}", mFile);
    try {
      file = commonService.saveSvrFileData(mFile, "");
      log.info("[uploadImg] file : {}", file);
    } catch (Exception e) {
        log.info("[uploadImg] e : {}", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse("이미지 업로드가 실패 하였습니다."));
    }
    return ResponseEntity.ok().body(new CommonResponse<FileVO>(file));
  }
  
  @CrossOrigin("*")
  @RequestMapping(value = "uploadImgs", method = RequestMethod.POST)
  public ResponseEntity<? extends BasicResponse> requestUploadImageList(@RequestParam("fileList") List<MultipartFile> fileList) {
    List<FileVO> resultFileList = null;
    try {
      resultFileList = commonService.saveSvrFileDataAll(fileList, "");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("이미지 업로드가 실패 하였습니다."));
    }
    return ResponseEntity.ok().body(new CommonResponse<List<FileVO>>(resultFileList));
  }
}
