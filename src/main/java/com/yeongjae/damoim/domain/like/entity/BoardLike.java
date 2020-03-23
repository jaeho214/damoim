package com.yeongjae.damoim.domain.like.entity;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "boardLike",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"member_id", "board_id"}
                )
        }
)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public BoardLike(Board board, Member member){
        this.board = board;
        this.member = member;
    }
}
