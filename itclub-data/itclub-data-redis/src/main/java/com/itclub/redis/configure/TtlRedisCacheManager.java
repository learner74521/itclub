package com.itclub.redis.configure;

import cn.hutool.core.util.StrUtil;
import com.itclub.common.core.text.Convert;
import com.itclub.redis.configure.constants.TimeTypeConstant;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * 设置失效时间
 *
 * @author onePiece
 */
public class TtlRedisCacheManager extends RedisCacheManager {

    public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] cells = StringUtils.delimitedListToStringArray(name, "=");
        name = cells[0];
        if (cells.length > 1) {
            String timeType = StrUtil.subSufByLength(cells[1], 1);
            String ttlStr = StrUtil.sub(cells[1], 0, cells[1].length() - 1);
            long ttl = Convert.toLong(ttlStr);
            if (TimeTypeConstant.SECONDS.equalsIgnoreCase(timeType)){
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
            } else if (TimeTypeConstant.MINUTES.equalsIgnoreCase(timeType)) {
                cacheConfig = cacheConfig.entryTtl(Duration.ofMinutes(ttl));
            }else if ((TimeTypeConstant.HOURS.equalsIgnoreCase(timeType))){
                cacheConfig = cacheConfig.entryTtl(Duration.ofHours(ttl));
            }else {
                cacheConfig = cacheConfig.entryTtl(Duration.ofDays(ttl));
            }

        }
        return super.createRedisCache(name, cacheConfig);
    }

}
