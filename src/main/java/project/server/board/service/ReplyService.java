package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.server.board.dto.ReplyPatchDto;
import project.server.board.dto.ReplyPostDto;
import project.server.board.dto.ReplyResponseDto;
import project.server.board.entity.Board;
import project.server.board.entity.Reply;
import project.server.board.repository.ReplyRepository;
import project.server.exception.BusinessLogicException;
import project.server.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardService boardService;

    // reply 생성
    public Long createReply(ReplyPostDto replyPostDto){
        Reply reply = new Reply();
        reply.setReContent(replyPostDto.getReContent());
        reply.setBoard(boardService.findBoardId(replyPostDto.getBoardId()));

        return replyRepository.save(reply).getReplyId();
    }

    // reply 수정
    public Long updateReply(ReplyPatchDto replyPatchDto, Long replyId){
        Reply reply = findReplyId(replyId);
        reply.setReContent(replyPatchDto.getReContent());
        reply.setBoard(boardService.findBoardId(replyPatchDto.getBoardId()));

        return replyRepository.save(reply).getReplyId();
    }

    // findReplyId
    public Reply findReplyId(Long replyId){
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }


    // reply 삭제

    // reply 모두 조회
    public Page<ReplyResponseDto> findAllReply(Pageable pageable, Long boardId){
        Board board = boardService.findBoardId(boardId);
        Page<Reply> replies = replyRepository.findByBoard(board, pageable);
        return replies.map(ReplyResponseDto::FindFromReply);
    }
}
