package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",    // 탐색할 패키지의 시작위치 지정
        basePackageClasses = AutoAppConfig.class,    // 지정한 클래스의 패키지를 탐색 위치로 지정
        /**
         * default : @ComponentScan이 붙은 설정정보 클래스의 패키지
         * 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것을 권장
         * CoreApplication 클래스의 @SpringBootApplication에 @ComponentScan 포함
        */
//        @Configuration에도 @Component가 포함되어 있기 때문에 AppConfig, TestConfig 등 앞서 만들었던 설정정보 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    /**
     * 컴포넌트 스캔 기본 대상 (어노테이션으로 @Component 포함)
     * @Compoenent : 컴포넌트 스캔
     * @Controller : 스프링 MVC 컨트롤러
     * @Service : 스프링 비즈니스 로직
     * @Repository : 스프링 데이터 접근 계층, 데이터 게층 예외를 스프링 예외로 변환
     * @Configuration : 스프링 설정 정보, 스프링 빈 싱글톤 유지하도록 처리
     */
}




