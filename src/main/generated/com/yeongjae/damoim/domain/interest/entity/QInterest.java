package com.yeongjae.damoim.domain.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterest is a Querydsl query type for Interest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInterest extends EntityPathBase<Interest> {

    private static final long serialVersionUID = -23764801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterest interest = new QInterest("interest");

    public final com.yeongjae.damoim.domain.deal.entity.QDeal deal;

    public final NumberPath<Long> interest_id = createNumber("interest_id", Long.class);

    public final com.yeongjae.damoim.domain.member.entity.QMember member;

    public QInterest(String variable) {
        this(Interest.class, forVariable(variable), INITS);
    }

    public QInterest(Path<? extends Interest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterest(PathMetadata metadata, PathInits inits) {
        this(Interest.class, metadata, inits);
    }

    public QInterest(Class<? extends Interest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.deal = inits.isInitialized("deal") ? new com.yeongjae.damoim.domain.deal.entity.QDeal(forProperty("deal"), inits.get("deal")) : null;
        this.member = inits.isInitialized("member") ? new com.yeongjae.damoim.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

