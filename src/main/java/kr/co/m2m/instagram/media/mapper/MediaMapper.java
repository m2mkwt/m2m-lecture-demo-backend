package kr.co.m2m.instagram.media.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.media.model.MediaVO;

@Mapper
public interface MediaMapper {
	
	public int insertPostMedia(MediaVO vo);
	
	public int insertProfileMedia(MediaVO vo);
	
	public int updateMedia(MediaVO vo);
	
	public int deleteMedia(int media_id);
	
	public MediaVO selectPostMedia(int post_id);
	
	public MediaVO selectProfileMedia(int media_id);
	
}
