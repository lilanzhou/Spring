package pojo;

import annotation.Component;
import annotation.SetValue;

/**
 * @Date : 2019:09:06
 * @Author : Lilanzhou
 * 说明 :
 */
@Component
public class Student {
    @SetValue(value = "1001")
    private String id;
    @SetValue(value = "张三")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[Student id"+id+"name"+name+"]";
    }
}
