package com.lcyan.basic.core.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 692301005L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final StringPath applicationCode = createString("applicationCode");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath creator = createString("creator");

    public final NumberPath<Integer> delFlag = createNumber("delFlag", Integer.class);

    public final StringPath ext = createString("ext");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isNewRecord = createBoolean("isNewRecord");

    public final StringPath modifier = createString("modifier");

    public final DateTimePath<java.util.Date> modifyDate = createDateTime("modifyDate", java.util.Date.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

