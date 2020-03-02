package com.yeongjae.damoim.domain.board.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.QBoard;
import com.yeongjae.damoim.domain.board.entity.QBoardImage;
import com.yeongjae.damoim.domain.member.entity.QMember;
import com.yeongjae.damoim.domain.reply.entity.QReply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private QBoard board = QBoard.board;
    private QBoardImage boardImage = QBoardImage.boardImage;
    private QReply reply = QReply.reply;
    private QMember member = QMember.member;

    public BoardRepositoryImpl(){
        super(Board.class);
    }


    @Override
    public Optional<Board> fetchBoardById(Long board_id) {
        JPAQuery<Board> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = jpaQuery.select(board)
                    .leftJoin(board.replyList, reply)
                    .leftJoin(board.imagePaths, boardImage)
                    .leftJoin(board.member, member)
                    .fetchJoin()
                    .where(board.id.eq(board_id));

        Optional<Board> board = Optional.ofNullable((Board) jpaQuery.fetch());
        return board;
    }
}