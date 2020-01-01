package com.changgou.system.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.changgou.system.util.JwtUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //2.获取响应对象
        ServerHttpResponse response = exchange.getResponse();

//        request.mutate().header("inner-token", token2).build();

        //判断令牌是否在http信息头,如果为false 重新设置token 到信息头,传递到微下级微服务AUTH2.0做权限验证
        boolean hasTokenHead =false;
        //3.判断当前的请求是否为登录请求,如果是,则直接放行
        if (request.getURI().getPath().contains("/user/login")){
            //放行
            return chain.filter(exchange);
        }
        //4.获取当前的所有请求头信息
        HttpHeaders headers = request.getHeaders();

        //5.获取jwt令牌信息
        String jwtToken = headers.getFirst("token");
        // 5.1 如果没有尝试从参数里获取
        if (StringUtils.isEmpty(jwtToken)){
            jwtToken=request.getQueryParams().getFirst("token");
            hasTokenHead = true;
        }
        //5.2 还是没有尝试从cookes获取
        if (StringUtils.isEmpty(jwtToken)){
            HttpCookie httpCookie = request.getCookies().getFirst("token");
            if (httpCookie!=null){
                jwtToken = httpCookie.getValue();
            }
        }
        //6.判断当前令牌是否存在,
        if (StringUtils.isEmpty(jwtToken)){
            //如果不存在,则向客户端返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        }

        //从新将令牌设置到头文件中
        if(!hasTokenHead){
            request.mutate().header("token",jwtToken);
        }

        //6.1 如果令牌存在,解析jwt令牌,判断该令牌是否合法,如果令牌不合法,则向客户端返回错误提示信息
        try {
            //解析令牌
            Claims claims = JwtUtil.parseJWT(jwtToken);
            JSONObject jsonObject = JSON.parseObject(claims.getSubject());
            String ip = (String) jsonObject.get("ip");
            String curIp=request.getRemoteAddress().getHostName();
//            if (!ip.equals(curIp)){
//                throw new Exception("请重新登陆..");
//            }
        }catch (Exception e){
            e.printStackTrace();
            //令牌解析失败
            //向客户端返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //6.2 如果令牌合法,则放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
