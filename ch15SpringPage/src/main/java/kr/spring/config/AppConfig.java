package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.spring.interceptor.AutoLoginCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;
import kr.spring.websocket.SocketHandler;


//자바코드기반설정 클래스
@Configuration
@EnableWebSocket
public class AppConfig implements WebMvcConfigurer,WebSocketConfigurer{//어노테이션,implements를 추가하면 설정파일 역할을 함
	
	private AutoLoginCheckInterceptor autoLoginCheck;
	private LoginCheckInterceptor loginCheck;
	private WriterCheckInterceptor writerChek;
	
	@Bean
	public AutoLoginCheckInterceptor interceptor() {
		autoLoginCheck = new AutoLoginCheckInterceptor();
		return autoLoginCheck;
	}
	
	@Bean
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor();
		return loginCheck;
	}
	
	@Bean
	public WriterCheckInterceptor interceptor4() {
		writerChek = new WriterCheckInterceptor();
		return writerChek;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//AutoLoginCheckInterceptor 설정
		registry.addInterceptor(autoLoginCheck)
				.addPathPatterns("/**")
				.excludePathPatterns("/images/**")
				.excludePathPatterns("/image_upload/**")
				.excludePathPatterns("/upload/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("js/**")
				.excludePathPatterns("/member/login")
				.excludePathPatterns("/member/logout");
		// LoginCheckInterceptor 설정
		registry.addInterceptor(loginCheck)
					   .addPathPatterns("/member/myPage")
					   .addPathPatterns("/member/update")
					  .addPathPatterns("/member/changePassword")
					  .addPathPatterns("/member/delete")
					  .addPathPatterns("/board/write")
					  .addPathPatterns("/board/update")
					  .addPathPatterns("/board/delete")
					  .addPathPatterns("/talk/talkRoomWrite")
					  .addPathPatterns("/talk/talkList")
					  .addPathPatterns("/talk/talkDetail");
		//WriterCheckInterceptor 설정
		registry.addInterceptor(writerChek)
						.addPathPatterns("/board/update")
						.addPathPatterns("/board/delete");
	}
	
	
	@Bean//빈객체 생성해서 컨테이너에 넣어주는 역할
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		//XML 설정 파일 경로 지정
		configurer.setDefinitions(new String[] {//배열이니까 여러 개 셋팅 가능
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/board.xml" ,
				"/WEB-INF/tiles-def/talk.xml" 
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	//tilesViewResolver 호출
	@Bean //TilesViewResolver는 뷰의 경로,확장자 경로인데 뷰를 호출해주는 역할을 함
	public TilesViewResolver tilesViesResolver() {//여기서의 TilesViewResolver는 ->29이동
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		//뷰 지정							//여러개를 하나로 조합하는건 TilesView가 담당
		tilesViewResolver.setViewClass(TilesView.class);//TilesView가 경로의 정보를 가지고 있는데 이 경로를 호출해주는 역할을 함
		return tilesViewResolver;
	}
	
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	Properties prop = new Properties();
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.transport.protocol", "smtp");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.debug", "true");
    	
    	JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
    	javaMail.setHost("smtp.gmail.com");
    	javaMail.setPort(587);
    	javaMail.setDefaultEncoding("utf-8");
    	javaMail.setUsername("pminny0818@gmail.com");
    	javaMail.setPassword("nbnmwrycwhsxfjpm");
    	javaMail.setJavaMailProperties(prop);
    	return javaMail;
    }
	
	//웹소켓 셋팅
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "message-ws").setAllowedOrigins("*");
	}
	
	
}
