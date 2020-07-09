package homework;

public class TestClassFirst {


    @BeforeSuit
    public static void init() {
        System.out.println("START");
    }

//    @BeforeSuit
//    public static void init2() {
//        System.out.println("START");
//    }

    @AfterSuit
    public static void close() {
        System.out.println("CLOSE");
    }

//    @AfterSuit
//    public static void close2() {
//        System.out.println("CLOSE2");
//    }

    @Test(priority = 10)
    public static boolean tryToGoodbye() {
        int a= 15;
        int b =25;
        System.out.println("Всем досвидания!!!");
        return ((a*b) == (a+b));

    }

    @Test(priority = 2)
    public static void doSomeThing() {

        System.out.println("Давайте что-нибудь потестируем!!!");
    }

    @Test(priority = 3)
    public static void doSomeThingElse() {
        System.out.println("Чтобы ещё потестировать?!!!");
    }



    @Test(priority = 1)
    public static void tryToHello() {
        System.out.println("Всем привет");
    }



}
