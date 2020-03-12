package com.yeongjae.damoim.global.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaBasePersistable is a Querydsl query type for JpaBasePersistable
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QJpaBasePersistable extends EntityPathBase<JpaBasePersistable> {

    private static final long serialVersionUID = 1842766244L;

    public static final QJpaBasePersistable jpaBasePersistable = new QJpaBasePersistable("jpaBasePersistable");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public QJpaBasePersistable(String variable) {
        super(JpaBasePersistable.class, forVariable(variable));
    }

    public QJpaBasePersistable(Path<? extends JpaBasePersistable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaBasePersistable(PathMetadata metadata) {
        super(JpaBasePersistable.class, metadata);
    }

}

