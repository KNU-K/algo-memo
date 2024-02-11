package com.example.backend.domain;

import com.example.backend.domain.constant.Tier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "incorrect_algo")
@NoArgsConstructor
public class IncorrectAlgorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 필드명은 소문자로 시작하는 것이 관례에 맞습니다.
    private String title;

    private String contents;

//    @Enumerated(EnumType.STRING)
//    private Tier tier;
//
    @ManyToOne
    @JoinColumn(name = "u_id", referencedColumnName = "userId")
    private User author;

    public IncorrectAlgorithm(String title, String contents, User author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        author.getIncorrectAlgorithm().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncorrectAlgorithm that = (IncorrectAlgorithm) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //    @ManyToMany()
//    private List<Tag> tags;
}
