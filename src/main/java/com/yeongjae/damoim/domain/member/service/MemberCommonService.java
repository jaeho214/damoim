package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.EmailFoundDto;
import com.yeongjae.damoim.domain.member.dto.PasswordFoundDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommonService {

    private final MemberRepository memberRepository;

    private final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private final String NUMBER = "0123456789";
    private final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
    private final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;


    public String findPassword(PasswordFoundDto passwordFoundDto) {
        Member member = memberRepository.findByEmail(passwordFoundDto.getEmail()).orElseThrow(MemberNotFoundException::new);

        checkPhone(member, passwordFoundDto);
        member.setPassword(generateRandomPassword(15));

        return member.getPassword();
    }

    public String findEmail(EmailFoundDto emailFoundDto) {
        Member member = memberRepository.findByPhone(emailFoundDto.getPhone()).orElseThrow(MemberNotFoundException::new);
        return member.getEmail();
    }

    public boolean checkEmail(String email) {
        if(!memberRepository.existsByEmail(email))
            return true;
        return false;
    }

    private String generateRandomPassword(int length) {
        if (length < 1)
            throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
            char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);
            sb.append(rndChar);
        }

        return sb.toString();
    }

    private static String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        return letters.stream().collect(Collectors.joining());
    }


    private void checkPhone(Member member, PasswordFoundDto passwordFoundDto) {
        if(member.getPhone().equals(passwordFoundDto.getPhone()))
            return;
        throw new MemberNotFoundException();
    }

}
