package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 218208387L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoom room = new QRoom("room");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBus bus;

    public final ListPath<ChatInOutRecord, QChatInOutRecord> chatInOutRecords = this.<ChatInOutRecord, QChatInOutRecord>createList("chatInOutRecords", ChatInOutRecord.class, QChatInOutRecord.class, PathInits.DIRECT2);

    public final ListPath<Chat, QChat> chats = this.<Chat, QChat>createList("chats", Chat.class, QChat.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QReport report;

    public final StringPath sessionId = createString("sessionId");

    public final DateTimePath<java.time.LocalDateTime> terminatedDate = createDateTime("terminatedDate", java.time.LocalDateTime.class);

    public QRoom(String variable) {
        this(Room.class, forVariable(variable), INITS);
    }

    public QRoom(Path<? extends Room> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoom(PathMetadata metadata, PathInits inits) {
        this(Room.class, metadata, inits);
    }

    public QRoom(Class<? extends Room> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bus = inits.isInitialized("bus") ? new QBus(forProperty("bus"), inits.get("bus")) : null;
        this.report = inits.isInitialized("report") ? new QReport(forProperty("report"), inits.get("report")) : null;
    }

}

