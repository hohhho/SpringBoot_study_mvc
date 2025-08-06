package project.server.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.server.board.entity.Board;
import project.server.board.entity.Reply;

@Getter @Setter
@AllArgsConstructor
public class ReplyResponseDto {

    private Long replyId;
    private String reContent;
    private Long boardId;

    // 정적 템플릿 메서드
    public static ReplyResponseDto FindFromReply(Reply reply){
        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getReContent(),
                reply.getBoard().getBoardId()
        );
    }
}
