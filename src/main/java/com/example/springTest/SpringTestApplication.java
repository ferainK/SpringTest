package com.example.springTest;

import com.example.springTest.ioc.ApplicationContextProvider;
import com.example.springTest.ioc.Base64Encoder;
import com.example.springTest.ioc.Encoder;
import com.example.springTest.ioc.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);

		// API는 controller + dto 패키지 참고

		// ioc : new 생성자를 Spring에 이관하는 방법
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		Encoder encoder = context.getBean("base64Encode", Encoder.class);
		String url = "www.naver.com/books/it?page=10&size=20";
		String result = encoder.encode(url);
		System.out.println(result);

		// aop : 서비스 로직에 불필요한 로직들을 저장할 때 사용할 수 있다. (build.gradle에 aop를 추가해주어야한다.)

	}

}

@Configuration	//하나의 Component에 여러 Bean을 입력하는 경우!
class AppConfig {
	@Bean("base64Encode")
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
		//만약 Encoder에 Component가 지정되어 있다면 new 생성자 때문에 충돌이 발생한다!
		//만약 Base64Encoder에 Component를 지정하지 않았다면 따로 코드에서 new 생성자로 만들어주어야한다.
	}
	@Bean("urlEncode")
	public Encoder encoder(UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
}