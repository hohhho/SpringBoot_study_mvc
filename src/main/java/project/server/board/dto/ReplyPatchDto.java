package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyPatchDto {

    @NotEmpty
    private String reContent;

    @NotNull
    private Long boardId;
}
