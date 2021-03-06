package kr.co.m2m.instagram.post.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.instagram.post.mapper.PostMapper;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper; 
	


	@Override
	public String insertPost(PostPO po) {  //글 입력
		int result = postMapper.insertPost(po);
		if(result==1) {
			return "insert Success";
		}else {
			return "insert Fail";
		}
	}
	
	@Override
	public String deletePost(PostPO po) {  
		int result = postMapper.deletePost(po);
		if(result==1) {
			return "delete Success";
		}else {
			return "delete Fail";
		}
	}
	@Override
	public String updatePost(PostPO po) {  
		int result = postMapper.updatePost(po);
		if(result==1) {
			return "update Success";
		}else {
			return "update Fail";
		}
	}

	
	@Override
	public List<PostVO> selectList(PostVO vo){
		List<PostVO> resultList = postMapper.selectPost(vo);
		return resultList;
	}
	

	@Override
	public PostVO selectDetail(PostVO vo){
		PostVO resultList = postMapper.selectPostDetail(vo);
		return resultList;
	}

	@Override
	public int countPost(int memberNo) {
		return postMapper.countPost(memberNo);
	}

	@Override
	public List<Map<String, String>> selectMyPost(int memberNo) {
		return postMapper.selectMyPost(memberNo);
	}
	
	@Override
	public int getCntCmt(PostVO vo) {
		return postMapper.getCntCmt(vo);
	}
	
//	@Override
//	public ResultListModel<PostVO> selectAll(PostVO vo){
//		ResultListModel<PostVO> rv = new ResultListModel<>();
//		rv.setResultList(postMapper.selectPostAll(vo));
//		rv.setMessage("SUCCESS");
//		return rv;
//	}

}
