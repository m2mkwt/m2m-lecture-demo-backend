package kr.co.m2m.instagram.post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.instagram.post.mapper.PostMapper;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper; 
	

//	@Override
//	public ResultModel<String> insertPost(PostPO po) {  //글 입력
//		ResultModel<String> rv = new ResultModel<>();
//		postMapper.insertPost(po);
//		rv.setMessage("insert Success");
//		return rv;
//	}
//	
//	@Override
//	public ResultModel<String> deletePost(PostPO po) {  
//		ResultModel<String> rv = new ResultModel<>();
//		postMapper.deletePost(po);
//		rv.setMessage("delete Success");
//		return rv;
//	}
//	@Override
//	public ResultModel<PostVO> updatePost(PostPO po) {  
//		ResultModel<PostVO> rv = new ResultModel<>();
//		postMapper.updatePost(po);
//		rv.setMessage("update Success");
//		return rv;
//	}
	
	@Override
	public List<PostVO> selectPost(PostVO vo){
		List<PostVO> resultList = postMapper.selectPost(vo);
		return resultList;
	}
	
//	@Override
//	public ResultListModel<PostVO> selectAll(PostVO vo){
//		ResultListModel<PostVO> rv = new ResultListModel<>();
//		rv.setResultList(postMapper.selectPostAll(vo));
//		rv.setMessage("SUCCESS");
//		return rv;
//	}
}
