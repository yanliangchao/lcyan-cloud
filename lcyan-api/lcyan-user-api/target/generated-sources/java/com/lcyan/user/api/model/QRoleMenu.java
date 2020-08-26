package com.lcyan.user.api.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleMenu is a Querydsl query type for RoleMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleMenu extends EntityPathBase<RoleMenu> {

    private static final long serialVersionUID = 40980101L;

    public static final QRoleMenu roleMenu = new QRoleMenu("roleMenu");

    public final NumberPath<Integer> menuId = createNumber("menuId", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public QRoleMenu(String variable) {
        super(RoleMenu.class, forVariable(variable));
    }

    public QRoleMenu(Path<? extends RoleMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleMenu(PathMetadata metadata) {
        super(RoleMenu.class, metadata);
    }

}

