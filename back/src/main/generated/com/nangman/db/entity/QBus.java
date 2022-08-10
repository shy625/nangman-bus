package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBus is a Querydsl query type for Bus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBus extends EntityPathBase<Bus> {

    private static final long serialVersionUID = 1392497112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBus bus = new QBus("bus");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath license = createString("license");

    public final StringPath no = createString("no");

    public final ListPath<Room, QRoom> rooms = this.<Room, QRoom>createList("rooms", Room.class, QRoom.class, PathInits.DIRECT2);

    public final QRoute route;

    public QBus(String variable) {
        this(Bus.class, forVariable(variable), INITS);
    }

    public QBus(Path<? extends Bus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBus(PathMetadata metadata, PathInits inits) {
        this(Bus.class, metadata, inits);
    }

    public QBus(Class<? extends Bus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route")) : null;
    }

}

