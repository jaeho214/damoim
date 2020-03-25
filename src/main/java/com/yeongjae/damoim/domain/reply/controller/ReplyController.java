package com.yeongjae.damoim.domain.reply.controller;

import com.yeongjae.damoim.domain.reply.dto.ReplyCreateDto;
import com.yeongjae.damoim.domain.reply.dto.ReplyGetDto;
import com.yeongjae.damoim.domain.reply.dto.ReplyUpdateDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.domain.reply.service.ReplyCreateService;
import com.yeongjae.damoim.domain.reply.service.ReplyDeleteService;
import com.yeongjae.damoim.domain.reply.service.ReplyUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/damoim/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCreateService replyCreateService;
    private final ReplyUpdateService replyUpdateService;
    private final ReplyDeleteService replyDeleteService;

    @PostMapping
    public ResponseEntity createReply(@RequestHeader("token") String token,
                                      @RequestBody ReplyCreateDto replyCreateDto){
        ReplyGetDto savedReply = replyCreateService.createReply(token, replyCreateDto);
        return ResponseEntity.created(URI.create("/damoim/reply/"+savedReply.getId())).body(savedReply);
    }

    @PutMapping
    public ResponseEntity updateReply(@RequestHeader("token") String token,
                                      @RequestBody ReplyUpdateDto replyUpdateDto){
        return ResponseEntity.ok(replyUpdateService.updateReply(token, replyUpdateDto));
    }

    @DeleteMapping("/{reply_id}")
    public ResponseEntity deleteReply(@RequestHeader("token") String token,
                                      @PathVariable Long reply_id){
        return replyDeleteService.deleteReply(token, reply_id);
    }
}
