package life.jugnu.learnkafka.ch03;

public class UserGenerator {
    public static void main(String[] args) {

    }

    public static User getNext(){
        User u = new User();
        u.setName("hello");
        u.setFavoriteColor("green");
        u.setFavoriteNumber(1);
        return u;
    }
}
