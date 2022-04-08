package kr.co.m2m.instagram.post.service;

import java.util.List;
import java.util.Map;
// import kr.co.m2m.framework.web.model.ResultListModel;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;

public interface PostService {


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 게시글 입력
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param po
   * @return
   */
  public String insertPost(PostPO po);

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 게시글 수정
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param po
   * @return
   */
  public String updatePost(PostPO po);

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 게시글 삭제
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param po
   * @return
   */
  public String deletePost(PostPO po);


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 전체 게시글 조회
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param vo
   * @return
   */
  public List<PostVO> selectList(PostVO vo);

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 상세 게시글 조회
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param vo
   * @return
   */
  public PostVO selectDetail(PostVO vo);

  // public ResultListModel<PostVO> selectAll(PostVO vo);

  public int countPost(int memberNo);

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "ktim"
   * 설명   : 바뀐 댓글 수 조회
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "ktim" - 최초생성
   * </pre>
   *
   * @param vo
   * @return
   */
  public int getCntCmt(PostVO vo);

  public List<Map<String, String>> selectMyPost(int memberNo);
}
