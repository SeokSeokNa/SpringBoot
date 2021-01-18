package spring.woseok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter  //롬복을 이용한 세터 자동생성 어노테이션
@Getter  //롬복을 이용한 게터 자동생성 어노테이션
@ToString // 롬복이 객체의 tostring 메소드를 자동으로 만들어줌
public class HelloLombok {

    private String name;
    private String age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdg");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("helloLombok= " + helloLombok);
    }
}
