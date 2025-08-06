package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import project.server.board.entity.Board;

@Getter @Setter
public class ReplyPostDto {

    @NotEmpty
    private String reContent;

    @NotNull
    private Long boardId;
}
