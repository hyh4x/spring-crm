package no.group.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* no.group.springdemo.controller.*.*(..))")
	private void controllerDeclaration() {}
	
	@Pointcut("execution(* no.group.springdemo.service.*.*(..))")
	private void serviceDeclaration() {}
	
	@Pointcut("execution(* no.group.springdemo.dao.*.*(..))")
	private void daoDeclaration() {}
	
	@Before("controllerDeclaration() || serviceDeclaration() || daoDeclaration()")
	public void logBefore(JoinPoint joinPoint) {
		myLogger.info("@Before Advice: You're calling "+joinPoint.getSignature().toShortString());
		Object[] args = joinPoint.getArgs();
		
			for(Object tmp: args) {
				if(tmp!=null) {
					myLogger.info("Argument: "+tmp.toString());
				}
			}
		
	}
	
	@AfterReturning(pointcut="controllerDeclaration() || serviceDeclaration() || daoDeclaration()",
			returning="result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		myLogger.info("@AfterReturning Advice: Finished "+joinPoint.getSignature().toShortString());
		
			if(result!=null) {
				myLogger.info("Returning: "+result.toString());
			}
		
	
	}
}
