package kr.co.m2m.instagram.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.model.PostVO;

@Mapper
public interface PostMapper {

	public int insertPost(PostVO vo); 
	
	public int updatePost(PostVO vo);
	
	public int deletePost(PostVO vo);
	
	public List<PostVO> selectPost(PostVO vo);
	
	public List<PostVO> selectPostDetail(PostVO vo);
	
	
}
