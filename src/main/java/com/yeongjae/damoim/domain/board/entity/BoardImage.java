package com.yeongjae.damoim.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "boardImage")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "boardImage_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class BoardImage extends JpaBasePersistable implements Serializable {

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @JsonIgnore
    private Board board;

    @Builder
    public BoardImage(String imagePath, Board board) {
        this.imagePath = imagePath;
        this.board = board;
    }
}
