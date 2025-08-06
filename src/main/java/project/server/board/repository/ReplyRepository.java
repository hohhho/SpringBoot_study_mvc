package project.server.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.server.board.entity.Board;
import project.server.board.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> findByBoard(Board board, Pageable pageable);
}
