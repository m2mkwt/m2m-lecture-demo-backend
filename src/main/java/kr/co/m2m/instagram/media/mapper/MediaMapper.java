package kr.co.m2m.instagram.media.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.media.model.MediaVO;

@Mapper
public interface MediaMapper {
	
	public int insertMedia(MediaVO vo);
		
	public void updateMedia(MediaVO vo);
	
	public void deleteMedia(int mediaNo);
	
	public MediaVO selectMedia(int mediaNo);
	
}
