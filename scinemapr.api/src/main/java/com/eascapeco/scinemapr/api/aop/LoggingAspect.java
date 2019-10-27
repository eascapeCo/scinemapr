package com.eascapeco.scinemapr.api.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
	
	private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.eascapeco.scinemapr..*Service.*(..))")
	public void servicePointcut() {}
	
	@Around("servicePointcut()")
	public Object serviceChecker(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getName();
		String serviceName = className.substring(className.indexOf(".") +  1, className.length());
		String methodName = joinPoint.getSignature().getName();
		
		String serviceMethodName = serviceName + "." + methodName + "()";
		
		log.debug("### 메소드 시작 {}", serviceMethodName);
		
		StopWatch stopWatch = new StopWatch();
		
		try {
			stopWatch.start();
			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			 throw e;
		} finally {
			stopWatch.stop();
			log.debug("### {} : 메소드 종료 (runtime: {})", serviceMethodName, stopWatch.getTotalTimeMillis());
		}
	}
}
