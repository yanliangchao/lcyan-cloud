package com.lcyan.user.api.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1993619462L;

    public static final QRole role = new QRole("role");

    public final com.lcyan.basic.core.persistence.entity.QBaseEntity _super = new com.lcyan.basic.core.persistence.entity.QBaseEntity(this);

    //inherited
    public final StringPath applicationCode = _super.applicationCode;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    public final StringPath deptName = createString("deptName");

    //inherited
    public final StringPath ext = _super.ext;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isDefault = createNumber("isDefault", Integer.class);

    //inherited
    public final BooleanPath isNewRecord = _super.isNewRecord;

    //inherited
    public final StringPath modifier = _super.modifier;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath roleCode = createString("roleCode");

    public final StringPath roleDesc = createString("roleDesc");

    public final StringPath roleName = createString("roleName");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

