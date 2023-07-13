package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{ // 대상 엔티티와 그것의 primary key타입

}
