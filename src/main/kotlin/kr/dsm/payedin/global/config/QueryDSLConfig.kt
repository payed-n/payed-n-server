package kr.dsm.payedin.global.config

import com.querydsl.jpa.JPQLTemplates
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDSLConfig(
    private val entityManager: EntityManager
) {

    @Bean
    protected fun queryFactory() = JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
}