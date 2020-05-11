package com.aop.demo.aspects;

import com.aop.demo.annotations.AccessIdentifier;
import com.aop.demo.services.CacheStore;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class AccessValidator {

    @Autowired
    private CacheStore cacheStore;

    @Before("@annotation(com.aop.demo.annotations.AccessIdentifier)")
    public void handle(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        int cacheDuration = extractTimeFromAnnotation(method.getDeclaredAnnotations());
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Object[] arguments = joinPoint.getArgs();
        for (int argIndex = 0; argIndex < arguments.length; argIndex++) {
            for (Annotation paramAnnotation : parameterAnnotations[argIndex]) {
                if (paramAnnotation instanceof RequestHeader) {
                    String accessKey = (String) arguments[argIndex];
                    if(cacheStore.isCached(method.getName(), accessKey)) {
                        throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Early Request");
                    }
                    cacheStore.putIn(method.getName(), accessKey, cacheDuration);
                    break;
                }
            }
        }
    }

    public int extractTimeFromAnnotation(Annotation[] annotations) {
        for (Annotation declaredAnnotation : annotations) {
            if (declaredAnnotation instanceof AccessIdentifier) {
                AccessIdentifier identifier = (AccessIdentifier) declaredAnnotation;
                switch (identifier.durationFormat()) {
                    case SECONDS:
                        return identifier.duration();
                    case MINS:
                        return identifier.duration() * 60;
                    case HOURS:
                        return identifier.duration() * 3600;
                }
            }
        }
        return 0;
    }
}
