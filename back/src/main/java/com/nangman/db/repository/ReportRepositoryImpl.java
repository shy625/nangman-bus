package com.nangman.db.repository;

import com.nangman.db.entity.QUser;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;

public class ReportRepositoryImpl implements ReportRepository{
    private final JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public List<Report> findReportsByUserId(Long userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.useremail.eq(useremail)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
}
