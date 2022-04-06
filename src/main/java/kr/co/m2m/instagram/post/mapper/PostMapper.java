package kr.co.m2m.instagram.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;

@Mapper
public interface PostMapper {

	public int insertPost(PostPO po); 
	
	public int updatePost(PostPO po);
	
	public int deletePost(PostPO po);
	
	public List<PostVO> selectPost(PostVO vo); 
	
	public PostVO selectPostDetail(PostVO vo);
	
	public List<PostVO> selectPostAll(PostVO vo);
	
	public int countPost(int memberNo);
	
	public int getCntCmt(PostVO vo);
	
	public List<Map<String, String>> selectMyPost(int memberNo);
	
}
