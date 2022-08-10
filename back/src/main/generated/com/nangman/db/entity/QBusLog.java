package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusLog is a Querydsl query type for BusLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusLog extends EntityPathBase<BusLog> {

    private static final long serialVersionUID = -1207571892L;

    public static final QBusLog busLog = new QBusLog("busLog");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final StringPath license = createString("license");

    public final NumberPath<Double> lng = createNumber("lng", Double.class);

    public final StringPath nid = createString("nid");

    public final StringPath no = createString("no");

    public final StringPath ord = createString("ord");

    public QBusLog(String variable) {
        super(BusLog.class, forVariable(variable));
    }

    public QBusLog(Path<? extends BusLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusLog(PathMetadata metadata) {
        super(BusLog.class, metadata);
    }

}

