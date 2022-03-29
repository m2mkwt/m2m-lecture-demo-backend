package kr.co.m2m.instagram.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;

@Mapper
public interface PostMapper {

	public int insertPost(PostPO po); 
	
	public int updatePost(PostPO po);
	
	public int deletePost(PostPO po);
	
	public List<PostVO> selectPost(PostVO vo); 
	
	public List<PostVO> selectPostAll(PostVO vo);
	
	public List<PostVO> selectPostDetail(PostVO vo);
	
	
}
