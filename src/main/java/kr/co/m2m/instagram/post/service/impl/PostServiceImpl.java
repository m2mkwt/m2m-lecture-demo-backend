package kr.co.m2m.instagram.post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.instagram.post.mapper.PostMapper;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper; 
	
	@Override
	public int insert(PostVO vo) {
		return postMapper.insertPost(vo);
	}
	
	@Override
	public int delete(PostVO vo) {
		return postMapper.deletePost(vo);
	}
	
	@Override
	public int update(PostVO vo) {
		return postMapper.updatePost(vo);
	}
	
	@Override
	public List<PostVO> select(PostVO vo){
		return postMapper.selectPost(vo);
	}
}
