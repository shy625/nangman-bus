package com.nangman.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 218301299L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    public final ListPath<ChatInOutRecord, QChatInOutRecord> chatInOutRecords = this.<ChatInOutRecord, QChatInOutRecord>createList("chatInOutRecords", ChatInOutRecord.class, QChatInOutRecord.class, PathInits.DIRECT2);

    public final ListPath<Chat, QChat> chats = this.<Chat, QChat>createList("chats", Chat.class, QChat.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> deletedDate = createDateTime("deletedDate", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isRouletted = createBoolean("isRouletted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QNickname nickname;

    public final StringPath password = createString("password");

    public final QSetting setting;

    public final StringPath socialToken = createString("socialToken");

    public final StringPath userBirthday = createString("userBirthday");

    public final StringPath useremail = createString("useremail");

    public final ListPath<UserReport, QUserReport> userReports = this.<UserReport, QUserReport>createList("userReports", UserReport.class, QUserReport.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nickname = inits.isInitialized("nickname") ? new QNickname(forProperty("nickname")) : null;
        this.setting = inits.isInitialized("setting") ? new QSetting(forProperty("setting"), inits.get("setting")) : null;
    }

}

