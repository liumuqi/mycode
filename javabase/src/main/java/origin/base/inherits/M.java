package origin.base.inherits;


import com.google.common.collect.ImmutableList;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import lombok.SneakyThrows;
import org.joda.time.format.DateTimeFormat;

import java.util.*;

/**
 * @Author:lmq
 * @Date: 2020/4/10
 * @Desc:
 **/
public class M {
    @SneakyThrows
    public static void main(String[] args) {
        TestA testA = new TestA();
        TestB testB = new TestB();
//        testA.p();
//        testB.p();
//        System.out.println("-----------------------");
//        TestA testA1 = new TestA();
//        TestB testB2 = new TestB();
//        testA1.p();
//        testB2.p();
//        System.out.println("===============");
//        testA.p();
//        testB.p();
//        testA1.p();
//        testB2.p();
//        System.out.println("=============");
//        FastThreadLocal<Object> threadLocal1 = new FastThreadLocal<>();
//        FastThreadLocal<Object> threadLocal2 = new FastThreadLocal<>();
//        FastThreadLocal<Object> t = null;
//        for (int i = 0; i < 1000000; i++) {
//            t = new FastThreadLocal<>();
//            t.set("ii:" + i);
//        }
//        System.out.println("........." + t.get());
//
//        FastThreadLocal<Object> threadLocal3 = new FastThreadLocal<>();
//        threadLocal3.set("vvvvvvvvvvv");
//
//
//        threadLocal1.set(1L);
//        threadLocal1.set("x");
//        threadLocal2.set(2L);
////        System.out.println(threadLocal1.get());
////        ThreadLocal<Object> tll = new ThreadLocal<>();
////        tll.set("AA");
////        tll.set("bbbbbbbbbbb");
////        System.out.println(tll.get());
////
////        ThreadLocal<Object> tll2 = new ThreadLocal<>();
////        tll2.set("AAA");
////        tll2.set("cccccccccc");
////        System.out.println("()()()");
////        System.out.println(tll.get());
////        System.out.println(tll2.get());
//
//
//        FastThreadLocalThread ftt = new FastThreadLocalThread(() -> {
//            FastThreadLocal<Object> tt = new FastThreadLocal<>();
//            tt.set(111L);
//            System.out.println(tt.get());
//        });
//        ftt.start();
//        ftt.join(100000);

        System.out.println("10 testA:" + testA.getPushDataCount(testA).toString());
        System.out.println("10 testB:" + testB.getPushDataCount(testB).toString());
        Test testA1 = testB;
        System.out.println("10 testA1:" + testA1.getPushDataCount(testA1).toString());

        testA.setPd();

        testB.setPd();

        System.out.println("333 testA:" + testA.getPushDataCount(testA).toString());
        System.out.println("111 testB:" + testB.getPushDataCount(testB).toString());

        System.out.println("111 testA1 ref testB:" + testA1.getPushDataCount(testA1));
        Test tt = new Test(new Object[10]);
        TestA testAa = new TestA();
        System.out.println("10 testAa:" + testAa.getPushDataCount(testAa));
        System.out.println(tt.getPushDataCount(tt));
        testAa.setPd();
        System.out.println("setpd testAa:" + testAa.getPushDataCount(testAa));
        System.out.println(tt.getPushDataCount(tt));



//        Date yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("19831201").toDate();
//        System.out.println(yyyyMMdd);
//
//        System.out.println("====================");
//        T t = new T();
//        ((Test) t).p();
//
//        System.out.println("#################invoke test===================");
//        Test.class.cast(t).getPushDataCount(new Object());
//        System.out.println("##############invoke test====done===============");
//
//        System.out.println("=======================");
//        test_generics();
//        System.out.println("=======================");
//        testf();
//        printInstance(testA1, t);
    }

    private static void printInstance(Test testA1, T t) {
        System.out.println("instacne check--------------------");
        //??????  ???=???  x = y (x.getClass.isAssignablefrom y.getclass)  x??????????????? ?????????....   ??????  x?????????
        boolean assignableFrom = testA1.getClass().isAssignableFrom(TestB.class);
        //?????? ????????? ?????? X x = .....   X.class.isInstance(x);  ??????:  ?????? ?????????....
        boolean instance = T.class.isInstance(t);
        boolean instance2 = TestB.class.isInstance(t);
        System.out.println(assignableFrom);
        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(t instanceof T);
    }

    public static void testf() throws Exception {

        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);  //???????????? add ????????????????????????????????????????????????????????? Integer

        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //collections.copy
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        if (srcSize < 15 || (src instanceof RandomAccess && dest instanceof RandomAccess)) {
            for (int i = 0; i < srcSize; i++)
                dest.set(i, src.get(i));
        } else {
            ListIterator<? super T> di = dest.listIterator();
            ListIterator<? extends T> si = src.listIterator();
            for (int i = 0; i < srcSize; i++) {
                di.next();
                di.set(si.next());
            }
        }
    }


    //??????? extends T????????????????????????????????????????????????????????????T???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    //??????? super T ??????????????????????????????????????????????????????????????????T???????????????????????????T??????T????????????????????????????????????????????????????????????
    //List, List ??????List<? super Apple>???????????????
    //List ???List<? extends Apple>???????????????
//    public static void test_generics() {
//        Test t = new Test();
//        TestA ta = new TestA();
//        TestB tb = new TestB();
//        List<? super Test> test = new ArrayList<>();
//        test.add(t);
//        test.add(ta);
//        test.add(tb);
//        List<TestA> testAS = Arrays.asList(ta);
//        List<? extends Test> tt = testAS;//????????????, ???????????????tt.add,?????????
//        System.out.println(">>>>>>" + tt.size());
//    }
}
