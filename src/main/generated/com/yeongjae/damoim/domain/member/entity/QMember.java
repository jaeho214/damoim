package com.yeongjae.damoim.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -457379745L;

    public static final QMember member = new QMember("member1");

    public final com.yeongjae.damoim.global.jpa.QJpaBasePersistable _super = new com.yeongjae.damoim.global.jpa.QJpaBasePersistable(this);

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath email = createString("email");

    public final StringPath fcmToken = createString("fcmToken");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imagePath = createString("imagePath");

    public final BooleanPath isVerified = createBoolean("isVerified");

    public final ListPath<com.yeongjae.damoim.domain.keyword.entity.Keyword, com.yeongjae.damoim.domain.keyword.entity.QKeyword> keywords = this.<com.yeongjae.damoim.domain.keyword.entity.Keyword, com.yeongjae.damoim.domain.keyword.entity.QKeyword>createList("keywords", com.yeongjae.damoim.domain.keyword.entity.Keyword.class, com.yeongjae.damoim.domain.keyword.entity.QKeyword.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final StringPath location = createString("location");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath provider = createString("provider");

    public final StringPath role = createString("role");

    public final StringPath sex = createString("sex");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

