package spring.woseok.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price;  // 여기가 문제 !
//    }
//
//    public int getPrice() {
//        return price;
//    }
    
    //애초에 공유될만한 필드를 두지 않고 값을 클라이언트마다 부여 받을수 있게 메소드를 변경함
    public int order(String name , int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
