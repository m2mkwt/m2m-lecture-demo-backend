package kr.co.m2m.instagram.post.service;

import java.util.List;

import kr.co.m2m.framework.web.model.ResultListModel;
import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;

public interface PostService {

	public ResultModel<String> insertPost(PostPO po); 
		
	public ResultModel<PostVO> updatePost(PostPO po);
		
	public ResultModel<String> deletePost(PostPO po);
	
	public List<PostVO> selectPost(PostVO vo);
	
	public ResultListModel<PostVO> selectAll(PostVO vo);
}
