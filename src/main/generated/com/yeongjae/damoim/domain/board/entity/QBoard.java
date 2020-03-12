package com.yeongjae.damoim.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1989108753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.yeongjae.damoim.global.jpa.QJpaBasePersistable _super = new com.yeongjae.damoim.global.jpa.QJpaBasePersistable(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final NumberPath<Long> hits = createNumber("hits", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<BoardImage, QBoardImage> imagePaths = this.<BoardImage, QBoardImage>createList("imagePaths", BoardImage.class, QBoardImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final StringPath location = createString("location");

    public final com.yeongjae.damoim.domain.member.entity.QMember member;

    public final ListPath<com.yeongjae.damoim.domain.reply.entity.Reply, com.yeongjae.damoim.domain.reply.entity.QReply> replyList = this.<com.yeongjae.damoim.domain.reply.entity.Reply, com.yeongjae.damoim.domain.reply.entity.QReply>createList("replyList", com.yeongjae.damoim.domain.reply.entity.Reply.class, com.yeongjae.damoim.domain.reply.entity.QReply.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.yeongjae.damoim.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

