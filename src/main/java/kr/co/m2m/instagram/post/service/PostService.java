package kr.co.m2m.instagram.post.service;

import java.util.List;

import kr.co.m2m.instagram.post.model.PostVO;

public interface PostService {

	public int insert(PostVO vo);
	
	public int delete(PostVO vo);
	
	public int update(PostVO vo);
	
	public List<PostVO> select(PostVO vo);
	
}
