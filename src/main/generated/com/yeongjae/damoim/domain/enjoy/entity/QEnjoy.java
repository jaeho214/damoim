package com.yeongjae.damoim.domain.enjoy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnjoy is a Querydsl query type for Enjoy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEnjoy extends EntityPathBase<Enjoy> {

    private static final long serialVersionUID = -612026319L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnjoy enjoy = new QEnjoy("enjoy");

    public final com.yeongjae.damoim.global.jpa.QJpaBasePersistable _super = new com.yeongjae.damoim.global.jpa.QJpaBasePersistable(this);

    public final EnumPath<EnjoyCategory> category = createEnum("category", EnjoyCategory.class);

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

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final StringPath latitude = createString("latitude");

    public final StringPath location = createString("location");

    public final StringPath longitude = createString("longitude");

    public final com.yeongjae.damoim.domain.member.entity.QMember member;

    public final NumberPath<Integer> recruit = createNumber("recruit", Integer.class);

    public final StringPath title = createString("title");

    public QEnjoy(String variable) {
        this(Enjoy.class, forVariable(variable), INITS);
    }

    public QEnjoy(Path<? extends Enjoy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnjoy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnjoy(PathMetadata metadata, PathInits inits) {
        this(Enjoy.class, metadata, inits);
    }

    public QEnjoy(Class<? extends Enjoy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.yeongjae.damoim.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

