package test;

import app.AnnotationClassPathXmlApplicationContext;
import pojo.StuMessage;

/**
 * @Date : 2019:09:06
 * @Author : Lilanzhou
 * 说明 :
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, AnnotationClassPathXmlApplicationContext.DocumentException, IllegalAccessException {
        AnnotationClassPathXmlApplicationContext applicationContext=new AnnotationClassPathXmlApplicationContext("pojo");
        StuMessage stuMessage=(StuMessage) applicationContext.getBean("sm");

        System.out.println(stuMessage.print());
    }
}
