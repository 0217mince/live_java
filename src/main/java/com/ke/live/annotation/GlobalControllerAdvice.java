package com.ke.live.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;


/**
 * @author 小凡
 * @date 2021/11/26
 */
@RestControllerAdvice
public class GlobalControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getMethod().isAnnotationPresent(RestWrapper.class)){
            bodyContainer.setValue(getWrapperResponse(returnType,bodyContainer.getValue()));
        }
    }

    private Object getWrapperResponse(MethodParameter returnType, Object data) {
        RestWrapper wrapper = returnType.getMethod().getAnnotation(RestWrapper.class);
        return new BaseHelper(wrapper.code(), wrapper.msg(), data);
    }
}
