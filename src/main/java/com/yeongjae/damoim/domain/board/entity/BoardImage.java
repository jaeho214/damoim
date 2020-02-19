package com.yeongjae.damoim.domain.board.entity;

import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "boardImage")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "boardImage_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class BoardImage extends JpaBasePersistable {

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public BoardImage(String imagePath, Board board) {
        this.imagePath = imagePath;
        this.board = board;
    }
}
