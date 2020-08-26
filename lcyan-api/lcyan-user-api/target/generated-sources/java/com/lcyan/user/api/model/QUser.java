package com.lcyan.user.api.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1993712475L;

    public static final QUser user = new QUser("user");

    public final com.lcyan.basic.core.persistence.entity.QBaseEntity _super = new com.lcyan.basic.core.persistence.entity.QBaseEntity(this);

    //inherited
    public final StringPath applicationCode = _super.applicationCode;

    public final NumberPath<Long> avatarId = createNumber("avatarId", Long.class);

    public final DateTimePath<java.util.Date> born = createDateTime("born", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    public final StringPath email = createString("email");

    //inherited
    public final StringPath ext = _super.ext;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isNewRecord = _super.isNewRecord;

    public final DateTimePath<java.util.Date> lockTime = createDateTime("lockTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> loginTime = createDateTime("loginTime", java.util.Date.class);

    //inherited
    public final StringPath modifier = _super.modifier;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final StringPath signature = createString("signature");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath userDesc = createString("userDesc");

    public final StringPath wechat = createString("wechat");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

