package com.knuk.algo_memo.domain.question.model;

import com.knuk.algo_memo.domain.workbook.model.Question;
import com.knuk.algo_memo.domain.workbook.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuestionTest {

    @Autowired
    QuestionRepository questionRepository;
    @Test
    @DisplayName("Question 생성 테스트")
    void tryToCreateNewQuestion(){
        Question question = new Question();
        question.createQuestion("title","content");
        Question newQuestion = questionRepository.saveAndFlush(question);

        Assertions.assertEquals(
                questionRepository.findById(newQuestion.getId()).orElseThrow(),
                question);
    }

}
