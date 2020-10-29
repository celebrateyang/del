package study;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName StreamSutdy
 * @Description StreamSutdy
 * @Author zhuqyang
 * @Date 2020/10/14 21:21
 * @Version 1.0
 */
public class StreamSutdy {
    List<Employee> emps = Arrays.asList(
        new Employee(101,"zhang",12,2345.23),
        new Employee(103,"ma",32,87989.11),
        new Employee(102,"xie",59,456.32),
        new Employee(104,"niao",23,4567.67),
        new Employee(105,"xie",45,4444.45)
    );

    @Test
    public void testFilter(){
        //中间操作,不会执行任何处理
        Stream<Employee> employeeStream = emps.stream().filter((employee -> employee.getAge() > 32));
        //终止操作,一次行执行全部内容.惰性求值
        employeeStream.forEach(System.out::println);
    }

    //一旦找到2个符合条件的,就返回了,不需要全部迭代完,提高了效率
    @Test
    public void testduanlu(){
        emps.stream().filter((employee) -> {
            System.out.println("执行了迭代");
            return employee.getAge()<=32;
        })
        .limit(2)
        .forEach(System.out::println);
    }

    //别忘记重写equals和hashCode方法
    @Test
    public void testDistinct(){
       emps.stream().distinct().forEach(System.out::println);
        System.out.println("-------------------");
       //跳过前2个
       emps.stream().filter(employee -> employee.getAge()<=32).skip(2)
           .forEach(System.out::println);
    }
}
