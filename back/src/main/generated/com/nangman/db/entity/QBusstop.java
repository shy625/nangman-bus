package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusstop is a Querydsl query type for Busstop
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusstop extends EntityPathBase<Busstop> {

    private static final long serialVersionUID = 1221144026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusstop busstop = new QBusstop("busstop");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lng = createNumber("lng", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath nodeid = createString("nodeid");

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public final QRoute route;

    public final NumberPath<Integer> updown = createNumber("updown", Integer.class);

    public QBusstop(String variable) {
        this(Busstop.class, forVariable(variable), INITS);
    }

    public QBusstop(Path<? extends Busstop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusstop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusstop(PathMetadata metadata, PathInits inits) {
        this(Busstop.class, metadata, inits);
    }

    public QBusstop(Class<? extends Busstop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route")) : null;
    }

}

