package entity;

import java.security.PrivateKey;

public class Book {
    private String name;  //书名
    private String id;  //书号
    private String press;  //出版社
    private float price;  //价格
    private int isBorrowed;  //是否借阅,0表示未借阅，1表示借阅
    private String type;  //书的类型，图书，杂志，论文
    private String order_cardno;  //是否预约

    public Book() {
    }

    public Book(String name, String id, String press, float price, int isBorrowed, String type, String order_cardno) {
        this.name = name;
        this.id = id;
        this.press = press;
        this.price = price;
        this.isBorrowed = isBorrowed;
        this.type = type;
        this.order_cardno = order_cardno;
    }

    public Book(String name, String id, String press, float price, String type) {
        this.name = name;
        this.id = id;
        this.press = press;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(int isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrder_cardno() {
        return order_cardno;
    }

    public void setOrder_cardno(String order_cardno) {
        this.order_cardno = order_cardno;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", press='" + press + '\'' +
                ", price=" + price +
                ", isBorrowed=" + isBorrowed +
                ", type='" + type + '\'' +
                ", order_cardno='" + order_cardno + '\'' +
                '}';
    }
}
