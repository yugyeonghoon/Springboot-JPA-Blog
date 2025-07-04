package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private UserRepository userRepository;

	// 위처럼 @Autowired를 하지 않으려면 생성자를 호출할떄
	// @RequiredArgsConstructor를 @Service 밑 클래스 위에 붙이고 (RequiredArgsConstructor =
	// 생성자 호출할 때 초기화되지 않은 생성자들을 초기화 해줌)
	// private final BoardRepository boardRepository;
	// private final ReplyRepository replyRepository; 로 호출 하면 된다.

	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0); // 조회수 강제로 0
		board.setUser(user);
		boardRepository.save(board);
	}

	// 글 목록 호출 페이징 처리를 하게 되면 타입이 List 가 아니라 Page 타입
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."); // 영속화 완료
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
	    User user = userRepository.findById(replySaveRequestDto.getUserId())
	        .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 유저 id를 찾을 수 없습니다."));
	    Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
	        .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다."));

	    Reply reply = Reply.builder()
	        .content(replySaveRequestDto.getContent())
	        .board(board)
	        .user(user)
	        .build();

	    replyRepository.save(reply);
	    System.out.println("댓글 저장 완료");
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
