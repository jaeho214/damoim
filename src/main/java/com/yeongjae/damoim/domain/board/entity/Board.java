package com.yeongjae.damoim.domain.board.entity;

import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "board")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Board extends JpaBasePersistable {
    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "hits", nullable = false)
    private Long hits = 0L;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Column(name = "image_paths")
    private List<BoardImage> imagePaths = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replyList = new ArrayList<>();

    @Builder
    public Board(final String title,
                 final String content,
                 final Long hits,
                 final String location,
                 final Member member,
                 final List<BoardImage> imagePaths,
                 final List<Reply> replyList) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.location = location;
        this.member = member;
        this.imagePaths = imagePaths;
        this.replyList = replyList;
    }

    public void addImage(BoardImage imagePath){
        this.imagePaths.add(imagePath);
    }

    public void addReply(Reply reply){
        this.replyList.add(reply);
        reply.setBoard(this);
    }

    public void updateBoard(BoardUpdateDto boardUpdateDto){
        this.title = boardUpdateDto.getTitle();
        this.content = boardUpdateDto.getContent();
    }

    public void updateHits(){
        this.hits++;
    }

}
