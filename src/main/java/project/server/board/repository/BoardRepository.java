package project.server.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.server.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    /*
     * JpaRepository 를 상속받아서 아무것도 없는 BoardRepository 기능을 확장. -> 기본 curd는 알아서 다 해줌
     * <Board, Long> : Repository 사용될 엔티티와 타입을 지정
     */
}
