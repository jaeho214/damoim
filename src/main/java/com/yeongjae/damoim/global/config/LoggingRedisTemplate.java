package com.yeongjae.damoim.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/*
 * 예외를 기록하는 확장 클래스
 * Redis 에 없으면 캐시 miss, -> db 로부터 가져옴
 */
@Slf4j
public class LoggingRedisTemplate<K,V> extends RedisTemplate<K,V> {

    @Override
    public <T> T execute(final RedisCallback<T> action, final boolean exposeConnection,
                         final boolean pipeline) {
        try {
            return super.execute(action, exposeConnection, pipeline);
        } catch (final Throwable t) {
            log.warn("Error executing cache operation1: {}", t.getMessage());
            t.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T execute(final RedisScript<T> script, final List<K> keys, final Object... args) {
        try {
            return super.execute(script, keys, args);
        } catch (final Throwable t) {
            log.warn("Error executing cache operation2: {}", t.getMessage());
            return null;
        }
    }

    @Override
    public <T> T execute(final RedisScript<T> script, final RedisSerializer<?> argsSerializer,
                         final RedisSerializer<T> resultSerializer, final List<K> keys, final Object... args) {
        try {
            return super.execute(script, argsSerializer, resultSerializer, keys, args);
        } catch (final Throwable t) {
            log.warn("Error executing cache operation3: {}", t.getMessage());
            return null;
        }
    }

    @Override
    public <T> T execute(final SessionCallback<T> session) {
        try {
            return super.execute(session);
        } catch (final Throwable t) {
            log.warn("Error executing cache operation4: {}", t.getMessage());
            return null;
        }
    }
}
