package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNickname is a Querydsl query type for Nickname
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNickname extends EntityPathBase<Nickname> {

    private static final long serialVersionUID = 408857622L;

    public static final QNickname nickname1 = new QNickname("nickname1");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath nickname = createString("nickname");

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QNickname(String variable) {
        super(Nickname.class, forVariable(variable));
    }

    public QNickname(Path<? extends Nickname> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNickname(PathMetadata metadata) {
        super(Nickname.class, metadata);
    }

}

