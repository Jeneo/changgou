package com.changgou.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }


    /**
     * 配合redis 令牌桶限流算法
     * 在GatewayApplicatioin引导类中添加如下代码，
     * KeyResolver用于计算某一个类型的限流的KEY也就是说，
     * 可以通过KeyResolver来指定限流的Key。
     * 就是使用IP作为用户唯一标识,根据IP进行限流
     * @return
     */
    @Bean(name="ipKeyResolver")
    public KeyResolver ipKeyResolver(){
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                //用户唯一ip
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }
}
