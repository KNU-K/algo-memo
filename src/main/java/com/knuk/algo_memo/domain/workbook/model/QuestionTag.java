package com.knuk.algo_memo.domain.workbook.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    Question question;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    Tag tag;
}
