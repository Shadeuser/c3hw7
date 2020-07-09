package homework;

import jdk.nashorn.internal.ir.LiteralNode;
import lesson7.MyTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class MakeTests {
    private static ArrayList<Method> arr = new ArrayList<>();
    private static ArrayList<Method> testsArr = new ArrayList<>();

    public static void start(Class inClass) throws Exception {

        Method[] methods = inClass.getDeclaredMethods();


        int bs = 0;
        int as = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuit.class)) {
                bs++;
            }
            if (m.isAnnotationPresent(AfterSuit.class)) {
                as++;
            }
        }
        if (as > 1 | bs > 1) {
            throw new Exception("Методы с аннотацией @BeforeSuit и " +
                    "@AfterSuit должны присутствовать не более, чем  в одном экземпляре");
        }


        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuit.class)) {
                arr.add(m);
                break;
            }
        }
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                testsArr.add(m);
            }
        }

        for (int i = testsArr.size() - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                if (testsArr.get(j).getAnnotation(Test.class).priority() >
                        testsArr.get(j + 1).getAnnotation(Test.class).priority()) {

                    Method tmp = testsArr.get(j);
                    testsArr.set(j, testsArr.get(j + 1));
                    testsArr.set(j + 1, tmp);
                }
            }
        }

        for (int i = 0; i < testsArr.size(); i++) {
            arr.add(testsArr.get(i));
        }


        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuit.class)) {
                arr.add(m);
                break;
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).invoke(null);
        }
    }


}
