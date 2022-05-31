package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;  // Java에서 공식적으로 지원하는 어노테이션
import javax.annotation.PreDestroy; // Java에서 공식적으로 지원하는 어노테이션

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String msg) {
        System.out.println("call: " + url + " message = " + msg);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() { // 초기화 콜백, 의존관계 주입이 끝나면 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {    // 소멸전 콜백, 소멸 전 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
