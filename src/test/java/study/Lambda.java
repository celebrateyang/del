package study;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.google.common.annotations.VisibleForTesting;

import java.time.*;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * @ClassName Lambda
 * @Description Lambda
 * @Author zhuqyang
 * @Date 2020/10/5 9:48
 * @Version 1.0
 */
public class Lambda {
    List<Employee> emps = Arrays.asList(
        new Employee(101,"zhang",12,2345.23),
        new Employee(102,"xie",59,456.32),
        new Employee(105,"aie",59,534.23),
        new Employee(103,"ma",32,87989.11),
        new Employee(104,"niao",23,4567.67)
    );

    //按年龄降序,如果年龄相同,按姓名首字母升序
    @Test
    public void test01(){
        Collections.sort(emps,(e1,e2)->{
            if(e1.getAge()== e2.getAge()){
               return e1.getName().compareTo(e2.getName());
            }else {
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for(Employee emp:emps){
            System.out.println(emp);
        }
    }

    //方法引用
    //Lambda体中调用方法的参数列表和返回值类型,要与函数式接口中抽象方法的函数列表和返回值类型一致
    @Test
    public void testMeRe(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;
        int compare = com1.compare(4, 3);
        System.out.println("compare=====>"+compare);
    }

    @Test
    public void testClassInstancMethod(){
        BiPredicate<String, String> bp = String::equals;
        boolean test = bp.test("ss", "ss1");
        System.out.println(test);
    }

    @Test
    public void testCreateStream(){
        Stream<Employee> stream = emps.stream();

        Employee[] empss = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(empss);

        Stream<String> aa = Stream.of("aa", "bb", "cc");

        //无限流
        //迭代
        Stream<Integer> integerStream = Stream.iterate(0, (x) -> x + 2);
        integerStream.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> generate = Stream.generate(() -> Math.random());//中间操作,不会执行任何操作
        generate.limit(8).forEach(System.out::println);//终止操作,一次性求职,惰性求值
    }

    @Test
    public void testAddAddAll(){
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        List list2 = new ArrayList();
        list2.add(11);
        list2.add(22);
        list2.add(list1);
        System.out.println(list2);
        List list3 = new ArrayList();
        list3.add(44);
        list3.add(55);
        list3.addAll(list1);
        System.out.println(list3);
    }

    @Test
    //自然排序(Comparable)
    //定制排序(Comparator)
    public void testSort(){

    }

    @Test
    public void testJava8time(){
        Instant in1 = Instant.now();
        System.out.println(in1);
        System.out.println(in1.toEpochMilli());//时间戳

        OffsetDateTime odt = in1.atOffset(ZoneOffset.ofHours(7));
        System.out.println(odt);
        System.out.println(odt.toEpochSecond());

        long seconds = Duration.between(in1, odt).getSeconds();
        System.out.println(seconds);
        //生成带时区的时间
        LocalDateTime ldt =  LocalDateTime.now(ZoneId.of("America/Chicago"));
        System.out.println("ldt=====>"+ldt);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Chicago"));
        System.out.println("zdt=====>"+zdt);
    }

    @Test
    public void testAllZone(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

}
