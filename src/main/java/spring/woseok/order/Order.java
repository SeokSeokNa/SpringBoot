package spring.woseok.order;

public class Order {
    
    private Long memberId;
    private String itemname;
    private int itemPrice;
    private int discountPrice;

    //주문->할인적용 까지 다되서 만들어지는 객체라고 보자
    public Order(Long memberId, String itemname, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemname = itemname;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

   // sysout 으로 Order객체를 찍으면 이 toString 메소드가 호출됨으로써 보기편함
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemname='" + itemname + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
