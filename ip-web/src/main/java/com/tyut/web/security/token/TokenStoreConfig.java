package com.tyut.web.security.token;

import com.tyut.core.constants.ConsParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 把token存到redis
 * Created by Fant.J.
 * 2018/5/3 12:11
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "ip.security.oauth2",name = "storeType",havingValue = "redis")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * JWT 配置
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ip.security.oauth2",name = "storeType",havingValue = "jwt",matchIfMissing = true)
    public static class JwtTokenConfig{

        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * token生成处理：指定签名(签名一定保密)
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(ConsParams.Auth.GET_SIGNING_KEY);
            return accessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
                return new JwtTokenEnhancer();
        }

    }
}
