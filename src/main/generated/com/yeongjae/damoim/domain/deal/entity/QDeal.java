package com.yeongjae.damoim.domain.deal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeal is a Querydsl query type for Deal
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeal extends EntityPathBase<Deal> {

    private static final long serialVersionUID = -220511805L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeal deal = new QDeal("deal");

    public final com.yeongjae.damoim.global.jpa.QJpaBasePersistable _super = new com.yeongjae.damoim.global.jpa.QJpaBasePersistable(this);

    public final EnumPath<DealCategory> category = createEnum("category", DealCategory.class);

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

    public final ListPath<DealImage, QDealImage> imagePaths = this.<DealImage, QDealImage>createList("imagePaths", DealImage.class, QDealImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final StringPath location = createString("location");

    public final com.yeongjae.damoim.domain.member.entity.QMember member;

    public final StringPath price = createString("price");

    public final EnumPath<DealStatus> status = createEnum("status", DealStatus.class);

    public final StringPath title = createString("title");

    public QDeal(String variable) {
        this(Deal.class, forVariable(variable), INITS);
    }

    public QDeal(Path<? extends Deal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeal(PathMetadata metadata, PathInits inits) {
        this(Deal.class, metadata, inits);
    }

    public QDeal(Class<? extends Deal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.yeongjae.damoim.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

