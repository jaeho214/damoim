package com.yeongjae.damoim.domain.deal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDealImage is a Querydsl query type for DealImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDealImage extends EntityPathBase<DealImage> {

    private static final long serialVersionUID = -637658792L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDealImage dealImage = new QDealImage("dealImage");

    public final com.yeongjae.damoim.global.jpa.QJpaBasePersistable _super = new com.yeongjae.damoim.global.jpa.QJpaBasePersistable(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final QDeal deal;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imagePath = createString("imagePath");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public QDealImage(String variable) {
        this(DealImage.class, forVariable(variable), INITS);
    }

    public QDealImage(Path<? extends DealImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDealImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDealImage(PathMetadata metadata, PathInits inits) {
        this(DealImage.class, metadata, inits);
    }

    public QDealImage(Class<? extends DealImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.deal = inits.isInitialized("deal") ? new QDeal(forProperty("deal"), inits.get("deal")) : null;
    }

}

