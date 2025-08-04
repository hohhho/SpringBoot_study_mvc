package project.server.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.server.board.dto.BoardPatchDto;
import project.server.board.dto.BoardPostDto;
import project.server.board.service.BoardService;

@Getter @Setter
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity postBoard(@RequestBody @Validated BoardPostDto boardPostDto){
        Long boardId = boardService.createBoard(boardPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
    }

    /*
     * ResponseEntity: Spring Framework 에서 HTTP 응답을 나타내는 클래스. 상태 코드, 헤더, 바디 등을 포함해
     *                  응답 전체를 세밀하게 제어할 수 있음.
     * ResponseEntity.status(HttpStatus.CREATED).body(boardId) : HTTP 상태 코드 201(CREATED)을 반환하고,
     *                                                           응답 본문(body)에 생성된 게시판의 ID를 담아 클라이언트에 전달.
     * @RequestBody : 클라이언트가 HTTP 요청의 본문에 담아 보낸 JSON, XML등의 데이터를 Spring이 자동으로 Java 객체(BoardPostDto)로 변환해주는 역할.
     * @Validated : Spring의 검증을 활성화하는 어노테이션. 이게 붙은 객체는 해당 클래스 필드에 선언된 @NotNull, @NotEmpty 등의
     *              Bean Validation 어노테이션 기반의 유효성 검사가 실행됨. 검증 실패 시 스프링은 자동으로 적절한 예외를 발생시키고
     *              400 Bad Request 등의 HTTP 응답을 클라이언트에 보냄.
     */

    @PatchMapping("/{boardId}")
    public ResponseEntity patchBoard(@PathVariable("boardId") Long boardId,
                                     @RequestBody @Validated BoardPatchDto boardPatchDto){
        boardService.updateBoard(boardPatchDto, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardId);
    }

    /*
     * @PathVariable: Spring Framework 에서 사용되는 어노테이션으로, HTTP 요청 URL 경로에 포함된 값을 컨트롤러 메서드의 파라미터로 쉽게 매핑해 주는 역할.
     *                URL 패턴에서 { }로 감싼 부분과 메서드 파라미터를 연결해 준다.
     */

}
