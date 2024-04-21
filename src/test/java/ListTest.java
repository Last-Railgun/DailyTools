import com.tool.entities.Fruit;
import com.tool.stream.ListStreamTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ListTest {
    @Test
    public void test01() {
        //中文测试
        List<Fruit> list = Stream.of(
                        new Fruit("苹果", "浆果", 5),
                        new Fruit("草莓", "浆果", 5),
                        new Fruit("橘子", "浆果", 6),
                        new Fruit("核桃", "坚果", 10))
                .filter(f -> f.getCategory().equals("浆果") && f.getPrice() == 5)
//                .limit(1)
                .toList();
        log.info(list.toString());
    }

    @Test
    public void test02() {
        List<Fruit> fruits = Stream.of(
                new Fruit("苹果", "浆果", 5),
                new Fruit("橘子", "浆果", 6),
                new Fruit("核桃", "坚果", 10)).collect(Collectors.toList());
        List<Fruit> newFruits = ListStreamTool.filterByPropertie(fruits, "category", "浆果");
        log.info("newFruits: {}", newFruits);
    }

    @Test
    public void test03() {
        List<Fruit> fruits = Stream.of(
                new Fruit("苹果", "浆果", 5),
                new Fruit("橘子", "浆果", 6),
                new Fruit("核桃", "坚果", 10)).toList();
        log.info("fruits: {}", fruits);
        ListStreamTool.setByPropertie(fruits, "category", "水果");
        log.info("newFruits: {}", fruits);
    }

    @Test
    public void test04() {
        log.info("{}", HttpStatus.OK.value());
    }
}
