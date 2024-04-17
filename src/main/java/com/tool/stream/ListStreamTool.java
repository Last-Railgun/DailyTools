package com.tool.stream;

import java.lang.reflect.Field;
import java.util.List;

public class ListStreamTool {
    /**
     * 按list中的实体类属性值过滤
     *
     * @param list      需要过滤的list
     * @param fieldName 属性名
     * @param value     属性值
     * @param <T>       list中的对象类型
     * @return 过滤完毕的list
     */
    public static <T> List<T> filterByPropertie(List<T> list, String fieldName, String value) {
        return list.stream().filter(item -> {
            try {
                Field f = item.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                return f.get(item).equals(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    /**
     * 指定list中的实体类中的属性值,
     * 此修改方法没有进行深拷贝,
     * 即原list中的实体类无法保持原有属性值
     *
     * @param list      需要修改的list
     * @param fieldName 属性名
     * @param value     属性值
     * @param <T>       list中的对象类型
     * @return 修改完毕的list
     */
    public static <T> List<T> setByPropertie(List<T> list, String fieldName, Object value) {
        list.forEach(item -> {
            try {
                Field f = item.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                f.set(item, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return list;
    }
}
