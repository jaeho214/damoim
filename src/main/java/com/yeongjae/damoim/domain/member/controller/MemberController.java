package com.yeongjae.damoim.domain.member.controller;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/damoim/sign")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCreateService memberCreateService;
    private final MemberSignInService memberSignInService;
    private final MemberGetService memberGetService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberCreateDto memberCreateDto){
        Member savedMember = memberCreateService.createMember(memberCreateDto);
        return ResponseEntity.created(URI.create("/damoim/sign/" + savedMember.getId())).body(savedMember);
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody MemberSignInDto memberSignInDto){
        return memberSignInService.signIn(memberSignInDto);
    }

    @GetMapping
    public ResponseEntity getMember(@RequestHeader("token") String token){
        return ResponseEntity.ok(memberGetService.getMember(token));
    }

    @PutMapping
    public ResponseEntity updateMember(@RequestHeader("token") String token,
                                       @RequestBody @Valid MemberUpdateDto memberUpdateDto){
        return ResponseEntity.ok(memberUpdateService.updateMember(token, memberUpdateDto));
    }

    @DeleteMapping
    public ResponseEntity deleteMember(@RequestHeader("token") String token){
        return memberDeleteService.deleteMember(token);
    }

    //TODO: 아이디찾기 필요(어떤 거로 아이디 찾을지 고민해보기)

    @GetMapping("/{email}")
    public boolean checkEmail(@PathVariable String email){
        return memberGetService.checkEmail(email);
    }

    @PostMapping("/pw")
    public ResponseEntity findPassword(@RequestBody String email){
        return ResponseEntity.ok().body(memberUpdateService.findPassword(email));
    }
}
