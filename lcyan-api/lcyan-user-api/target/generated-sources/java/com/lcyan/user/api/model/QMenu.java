package com.lcyan.user.api.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1993460975L;

    public static final QMenu menu = new QMenu("menu");

    public final com.lcyan.basic.core.persistence.entity.QBaseEntity _super = new com.lcyan.basic.core.persistence.entity.QBaseEntity(this);

    //inherited
    public final StringPath applicationCode = _super.applicationCode;

    public final StringPath component = createString("component");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    //inherited
    public final StringPath ext = _super.ext;

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isNewRecord = _super.isNewRecord;

    //inherited
    public final StringPath modifier = _super.modifier;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath path = createString("path");

    public final StringPath permission = createString("permission");

    public final StringPath redirect = createString("redirect");

    public final StringPath remark = createString("remark");

    public final StringPath sort = createString("sort");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath url = createString("url");

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

