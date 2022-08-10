package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserReport is a Querydsl query type for UserReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserReport extends EntityPathBase<UserReport> {

    private static final long serialVersionUID = 110273159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserReport userReport = new QUserReport("userReport");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QReport report;

    public final QUser user;

    public QUserReport(String variable) {
        this(UserReport.class, forVariable(variable), INITS);
    }

    public QUserReport(Path<? extends UserReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserReport(PathMetadata metadata, PathInits inits) {
        this(UserReport.class, metadata, inits);
    }

    public QUserReport(Class<? extends UserReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.report = inits.isInitialized("report") ? new QReport(forProperty("report"), inits.get("report")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

