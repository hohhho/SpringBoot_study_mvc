package project.server.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.server.board.dto.ReplyPostDto;
import project.server.board.dto.ReplyResponseDto;
import project.server.board.service.ReplyService;

@Getter @Setter
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // reply 생성
    @PostMapping
    public ResponseEntity postReply(@RequestBody @Validated ReplyPostDto replyPostDto){
        Long replyId = replyService.createReply(replyPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(replyId);
    }


    // reply 전체 조회
    @GetMapping("{boardId}")
    public ResponseEntity<Page<ReplyResponseDto>> getAllReply(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ReplyResponseDto> replies = replyService.findAllReply(pageable, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }
}
