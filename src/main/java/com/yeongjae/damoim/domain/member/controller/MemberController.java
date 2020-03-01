package com.yeongjae.damoim.domain.member.controller;

import com.yeongjae.damoim.domain.member.dto.*;
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
    private final MemberCommonService memberCommonService;

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

    @PostMapping("/email")
    public ResponseEntity findEmail(@RequestBody EmailFoundDto emailFoundDto){
        return ResponseEntity.ok().body(memberCommonService.findEmail(emailFoundDto));
    }

    @GetMapping("/check/{email}")
    public boolean checkEmail(@PathVariable String email){
        return memberCommonService.checkEmail(email);
    }

    @PostMapping("/pw")
    public ResponseEntity findPassword(@RequestBody PasswordFoundDto passwordFoundDto){
        return ResponseEntity.ok().body(memberCommonService.findPassword(passwordFoundDto));
    }
}
