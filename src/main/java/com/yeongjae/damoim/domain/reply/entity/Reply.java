package com.yeongjae.damoim.domain.reply.entity;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "reply")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "reply_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Reply extends JpaBasePersistable {

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Reply(final String title,
                 final Member member,
                 final Board board) {
        this.title = title;
        this.member = member;
        this.board = board;
    }
}
