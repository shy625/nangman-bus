package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatInOutRecord is a Querydsl query type for ChatInOutRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatInOutRecord extends EntityPathBase<ChatInOutRecord> {

    private static final long serialVersionUID = -838336902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatInOutRecord chatInOutRecord = new QChatInOutRecord("chatInOutRecord");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> inTime = createDateTime("inTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final DateTimePath<java.time.LocalDateTime> outTime = createDateTime("outTime", java.time.LocalDateTime.class);

    public final QRoom room;

    public final QUser user;

    public QChatInOutRecord(String variable) {
        this(ChatInOutRecord.class, forVariable(variable), INITS);
    }

    public QChatInOutRecord(Path<? extends ChatInOutRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatInOutRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatInOutRecord(PathMetadata metadata, PathInits inits) {
        this(ChatInOutRecord.class, metadata, inits);
    }

    public QChatInOutRecord(Class<? extends ChatInOutRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

