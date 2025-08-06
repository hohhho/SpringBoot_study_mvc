package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.server.board.dto.ReplyResponseDto;
import project.server.board.entity.Board;
import project.server.board.entity.Reply;
import project.server.board.repository.BoardRepository;
import project.server.board.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardService boardService;

    // reply 생성
//    public

    // reply 수정

    // reply 삭제

    // reply 모두 조회
    public Page<ReplyResponseDto> findAllReply(Pageable pageable, Long boardId){
        Board board = boardService.findBoardId(boardId);
        Page<Reply> replies = replyRepository.findByBoard(board, pageable);
        return replies.map(ReplyResponseDto::FindFromReply);
    }
}
