package kr.co.m2m.instagram.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.instagram.comment.mapper.CommentMapper;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper mCommentMapper;
	@Override
	public List<CommentVO> commentList(CommentVO cv){
        return mCommentMapper.commentList(cv);
    }
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
//	public ResultModel<String> commentInsert(List<CommentPO> po) {
//		ResultModel<String> rv = new ResultModel<>();
//		try {
//			for (int i = 0; i < po.size(); i++) {
//				this.mCommentMapper.commentInsert(po.get(i));
//				rv.setMessage(MessageUtils.getMessage("server.common.message.insert.success"));
//			}
//		} catch (Exception e) {
//			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
//		}
//		return rv;
//	}
//	@Override
//	public ResultListModel<CommentVO> commentUpdate(List<CommentVO> vo) {
//		ResultListModel<CommentVO> rv = new ResultListModel<>();
//		CommentVO cVO = new CommentVO();
//		try {
//			for (int i = 0; i < vo.size(); i++) {
//				cVO.setCommentId(vo.get(i).getCommentId());
//				cVO.setMemberId(vo.get(i).getMemberId());
//				cVO.setPostId(vo.get(i).getPostId());
//				cVO.setText(vo.get(i).getText());
//				cVO.setDeleteYN(vo.get(i).getDeleteYN());
//				cVO.setCreatedt(vo.get(i).getCreatedt());
//				this.mCommentMapper.commentUpdate(cVO);
//			}
//			rv.setMessage(MessageUtils.getMessage("server.common.message.update.success"));
//		} catch (Exception e) {
//			throw new BEMessageException("server.common.exmaple.error-during-save-update", e);
//		}
//
//		return rv;
//	}
	
	public int commentDelete(CommentVO cv){
        return mCommentMapper.commentDelete(cv);
    }

}

