package kr.co.m2m.instagram.post.service;

import java.util.List;

import kr.co.m2m.framework.web.model.ResultListModel;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;

public interface PostService {

	public String insertPost(PostPO po); 
		
	public String updatePost(PostPO po);
		
	public String deletePost(PostPO po);
	
	public List<PostVO> selectList(PostVO vo); 
	
	public PostVO selectDetail(PostVO vo); 
	
	public ResultListModel<PostVO> selectAll(PostVO vo);
}
