package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration//자바기반설정파일이 되려면 implements하고 annotation사용해야됨
public class AppConfig implements WebMvcConfigurer{//설정파일의 역할

	@Bean//동작되면서 메서드 실행하고 컨테이너에 등록됨
	public TilesConfigurer tilesConfigurer() {
		//생성하면 변수에 객체 담아서 생성/객체 주소를 가지고 안바꿀거여서final붙일수 있음
		final TilesConfigurer configurer = new TilesConfigurer();
		
		//해당 경로에 tiles.xml 파일을 넣음
		configurer.setDefinitions(new String[] {"/WEB-INF/tiles-def/tilesdef.xml"});//""을 지정하기 위해 설정
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);//여러개를 하나로 조합하는건 TilesView가 담당
		return tilesViewResolver;
	}
}
