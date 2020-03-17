package com.yeongjae.damoim.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.board.dto.BoardGetByLocationDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetByMemberDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.QBoard;
import com.yeongjae.damoim.domain.board.entity.QBoardImage;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.entity.QMember;
import com.yeongjae.damoim.domain.reply.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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

        Board board = jpaQuery
                .select(this.board)
                .from(this.board)
                .innerJoin(this.board.member, member).fetchJoin()
                .leftJoin(this.board.imagePaths, boardImage).fetchJoin()
                .leftJoin(this.board.replyList, reply).fetchJoin()
                .where(this.board.id.eq(board_id))
                .fetchOne();

        return Optional.ofNullable(board);
    }

    @Override
    public Page<BoardGetByLocationDto> findByLocation(String location, Pageable pageable) {
        JPAQuery<Board> jpaQuery = new JPAQuery<>(entityManager);

        jpaQuery = jpaQuery.select(board)
                .from(board)
                .innerJoin(board.member, member).fetchJoin()
                .where(board.location.eq(location));

        List<Board> boardList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<BoardGetByLocationDto> boardGetByLocationDtoList = new ArrayList<>();
        boardList.forEach(board ->
                boardGetByLocationDtoList.add(
                    BoardGetByLocationDto.builder()
                        .board_id(board.getId())
                        .createdAt(board.getCreatedAt())
                        .hits(board.getHits())
                        .title(board.getTitle())
                        .writer(board.getMember().getNickName())
                        .replyCount(board.getReplyList().size())
                        .build()
                ));

        return new PageImpl<>(boardGetByLocationDtoList, pageable, jpaQuery.fetchCount());
    }

    @Override
    public Page<BoardGetByMemberDto> findByMember(Member member, Pageable pageable) {
        JPAQuery<Board> jpaQuery = new JPAQuery<>(entityManager);

        jpaQuery = jpaQuery.select(board)
                .from(board)
                .innerJoin(board.member, this.member)
                .where(board.member.eq(member));

        List<Board> boardList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<BoardGetByMemberDto> boardGetByMemberDtoList = new ArrayList<>();
        boardList.forEach(board ->
                boardGetByMemberDtoList.add(
                        BoardGetByMemberDto.builder()
                                .board_id(board.getId())
                                .hits(board.getHits())
                                .title(board.getTitle())
                                .createdAt(board.getCreatedAt())
                                .location(board.getLocation())
                                .replyCount(board.getReplyList().size())
                                .build()
                ));

        return new PageImpl<>(boardGetByMemberDtoList, pageable, jpaQuery.fetchCount());
    }
}
