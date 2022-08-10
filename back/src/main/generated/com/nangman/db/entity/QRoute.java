package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoute is a Querydsl query type for Route
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoute extends EntityPathBase<Route> {

    private static final long serialVersionUID = -1825468511L;

    public static final QRoute route = new QRoute("route");

    public final ListPath<Bus, QBus> buses = this.<Bus, QBus>createList("buses", Bus.class, QBus.class, PathInits.DIRECT2);

    public final ListPath<Busstop, QBusstop> busstops = this.<Busstop, QBusstop>createList("busstops", Busstop.class, QBusstop.class, PathInits.DIRECT2);

    public final NumberPath<Integer> citycode = createNumber("citycode", Integer.class);

    public final StringPath code = createString("code");

    public final StringPath endBusstop = createString("endBusstop");

    public final StringPath endTime = createString("endTime");

    public final StringPath no = createString("no");

    public final StringPath startBusstop = createString("startBusstop");

    public final StringPath startTime = createString("startTime");

    public final StringPath type = createString("type");

    public QRoute(String variable) {
        super(Route.class, forVariable(variable));
    }

    public QRoute(Path<? extends Route> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoute(PathMetadata metadata) {
        super(Route.class, metadata);
    }

}

