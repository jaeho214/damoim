package com.yeongjae.damoim.domain.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardLike is a Querydsl query type for BoardLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardLike extends EntityPathBase<BoardLike> {

    private static final long serialVersionUID = 2011517147L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardLike boardLike = new QBoardLike("boardLike");

    public final com.yeongjae.damoim.domain.board.entity.QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.yeongjae.damoim.domain.member.entity.QMember member;

    public QBoardLike(String variable) {
        this(BoardLike.class, forVariable(variable), INITS);
    }

    public QBoardLike(Path<? extends BoardLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardLike(PathMetadata metadata, PathInits inits) {
        this(BoardLike.class, metadata, inits);
    }

    public QBoardLike(Class<? extends BoardLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.yeongjae.damoim.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.member = inits.isInitialized("member") ? new com.yeongjae.damoim.domain.member.entity.QMember(forProperty("member")) : null;
    }

}
