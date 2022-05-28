package hello.core.singleton;

public class SingletonService {

    // static 영역에 인스턴스를 생성하고
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자로 외부에서 객체를 생성하지 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
