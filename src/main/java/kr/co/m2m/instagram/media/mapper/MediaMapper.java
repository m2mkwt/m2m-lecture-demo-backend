package kr.co.m2m.instagram.media.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.model.MediaVO;

@Mapper
public interface MediaMapper {
	
	public int insertMedia(MediaVO vo);
	
	public void updateProfileMedia(FileVO vo);
		
	public void updatePostMedia(FileVO vo);
	
	public void deleteMedia(FileVO vo);
	
	public MediaVO selectMedia(int mediaNo);
	
}
