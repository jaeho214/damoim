package com.yeongjae.damoim.domain.deal.entity;


import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "dealImage")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "dealImage_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class DealImage extends JpaBasePersistable {

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id", nullable = false)
    private Deal deal;

    @Builder
    public DealImage(final String imagePath,
                     final Deal deal) {
        this.imagePath = imagePath;
        this.deal = deal;
    }
}
