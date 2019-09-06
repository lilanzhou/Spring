package pojo;

import annotation.Component;
import annotation.SetValue;

/**
 * @Date : 2019:09:06
 * @Author : Lilanzhou
 * 说明 :
 */
@Component(name = "sm")
public class StuMessage {
    @SetValue(isPOJO = "yes",value = "student")
    private Student student;
    public String print(){
        return student.toString();
    }
}
