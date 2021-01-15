package spring.woseok.singleton;

public class SingletonService {

    //static 영역에 자바 jvm이 시작되자마자 올려두게 된다
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }
    
    // private 생성자를 통해 외부에서 객체를 무분별하게 만들수 없게 막음
    // 여기까지는 싱글톤 처럼 움직이는거 같다
    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
