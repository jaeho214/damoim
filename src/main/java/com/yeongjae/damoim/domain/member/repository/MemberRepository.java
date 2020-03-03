package com.yeongjae.damoim.domain.member.repository;

import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhone(String phone);
    boolean existsByEmail(String email);
    Optional<Member> findByEmailAndProvider(String email, String provider);
}
