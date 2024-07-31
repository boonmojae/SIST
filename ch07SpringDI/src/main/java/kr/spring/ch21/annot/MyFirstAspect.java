package kr.spring.ch21.annot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect//이게 없으면 공통기능으로 인식 못함
public class MyFirstAspect {
	/*
	 * 구현 가능한 Advice(언제 공통 기능을 핵심 기능에 적용할 지를 정의) 종류
	 * 종류						설명
	 * Before Advice			대상 객체의 메서드 호출 전에 공통 기능을 실행
	 * @Before
	 * 
	 * After Returning Advice 	대상 객체의 메서드가 예외 없이 실행한 이후에 공통 기능을 실행
	 * @AfterReturning
	 * 
	 * After Throwing Advice	대상 객체의 메서드를 실행하는 도중 예외가 발생한 경우에 공통 기능을 실행
	 * @AfterThrowing
	 * 
	 * After Advice				대상 객체의 메서드를 실행하는 도중 예외가 발생했는지의 여부와 상관없이 메서드 실행 후 
	 * @After							공통 기능을 실행(try~catch~finally의 finally블럭과 비슷)
	 * 
	 * Around Advice			대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
	 * @Around
	 */
	
	//annotation 동작시키기 위해 형식적으로 만듦
	@Pointcut("execution(public String launch())")
	//(주의)메서드명이 getPointcut으로 정해져 있음
	public void getPointcut() {}
	
	/* @Before("getPointcut()") *///메서드명을 적어서 핵심기능이 동작될때 여기도 동작
	public void before() {
		//메서드 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! **메서드가 호출되기 전에 나온다");
	}
	
	/* @AfterReturning(value="getPointcut()",returning="msg") *///핵심기능을 수행하는 메서드 넣음
	public void afterReturning(String msg) {
		//메서드 호출이 예외를 내보내지 않고 종료했을 때 동작하는 어드바이스
		System.out.println("Hello AfterReturning! **메서드가 호출한 후에 나온다! 전달된 객체 : " + msg);
	}
	
	/* @AfterThrowing(value="getPointcut()",throwing="ex") */
	public void afterThrowing(Exception ex) {
		//메서드 호출이 예외를 던졌을 때 동작하는 어드바이스
		System.out.println("Hello AfterThrowing! **예외가 생기면 나온다! 예외 : " + ex);
	}
	
	/* @After("getPointcut()") */
	public void after() {
		//메서드 종료 후에 동작하는 어드바이스(예외가 발생해도 실행됨)
		System.out.println("Hello After! **메서드가 호출된 후에 나온다");
	}
	
	@Around("getPointcut()")
			//String을 넣어 핵심기능이 반환하는걸 여기서도 가능하다
	public String around(ProceedingJoinPoint joinpoint)throws Throwable{//ProceedingJoinPoint 핵심기능에 대한 정보를 가지고 있는 객체->이거로 핵심기능을 가지고 있는 메서드 호출
		//메서드 호출 전후에 동작하는 어드바이스
		System.out.println("Hello Around before! **메서드가 호출되기 전에 나온다");
		
		String s = null;
		//try~catch~finally 구조로 명시해야 예외가 발생해도 메서드 실행 후 공통 기능을 수행
		try {
			//핵심기능 동작하게 해야됨
			//핵심 기능이 수행된 후 데이터 반환
			s = (String)joinpoint.proceed();//핵심 기능을 수행하는 메서드 실행
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//핵심기능 동작한 이후에 공통기능 동작되게
			System.out.println("Hello Around after **메서드가 호출된 후에 나온다! 반환된 객체 : " + s);
		}
		return s;
	}
	
}
