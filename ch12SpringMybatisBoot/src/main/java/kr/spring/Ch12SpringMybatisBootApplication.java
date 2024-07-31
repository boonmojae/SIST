package kr.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//부트를 실행시키면 가장 먼저 동작 -> 동작되면서 컨테이너 만들고 내장되어있는 톰캣 구동
@SpringBootApplication
public class Ch12SpringMybatisBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch12SpringMybatisBootApplication.class, args);
	}

}
