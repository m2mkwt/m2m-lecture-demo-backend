package kr.co.m2m.instagram.media.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.media.model.MediaVO;

@Mapper
public interface MediaMapper {
	void insertFile();
	
	MediaVO selectPostFile(int post_id);
	
	MediaVO selectMemberFile(int media_id);
	
}
