package com.nangman.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.nangman.db.entity.QUser;
import com.nangman.db.entity.User;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl{
    private final JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(long userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.id.eq(userId)).where(qUser.isDeleted.eq(false)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    public Optional<User> findUserByUseremail(String useremail) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.useremail.eq(useremail)).where(qUser.isDeleted.eq(false)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

}
