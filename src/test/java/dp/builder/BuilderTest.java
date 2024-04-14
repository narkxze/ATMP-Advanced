package dp.builder;

public class BuilderTest {
    public static void main(String[] args) {
        Phone p = new PhoneBuilder().setOs("Android").setRam(2).setBattery(3000).getPhone();
        System.out.println(p);
    }
}
