package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.server.board.dto.BoardPatchDto;
import project.server.board.dto.BoardPostDto;
import project.server.board.entity.Board;
import project.server.board.repository.BoardRepository;
import project.server.exception.BusinessLogicException;
import project.server.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
public class BoardService {

    /*
     * @RequiredArgsConstructor: final로 선언된 필드 OR @NotNull 어노테이션이 붙은 필드들을 매개변수로 받는 생성자 생성
     */

    private final BoardRepository boardRepository;

    // board 생성
    public Long createBoard(BoardPostDto boardPostDto){
        Board board = new Board();
        board.setTitle(boardPostDto.getTitle());
        board.setContent(boardPostDto.getContent());

        return boardRepository.save(board).getBoardId();
    }

    // board 수정
    public Long updateBoard(BoardPatchDto boardPatchDto, Long boardId){
        Board board = findBoardId(boardId);
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());

        return boardRepository.save(board).getBoardId();
    }

    // board 수정 - findBoardId
    public Board findBoardId(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
