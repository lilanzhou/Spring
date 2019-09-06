package app;

import annotation.Component;
import annotation.SetValue;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date : 2019:09:06
 * @Author : Lilanzhou
 * 说明 :
 */
public class AnnotationClassPathXmlApplicationContext implements ApplicationContext{
    private Map map = new HashMap<>();

    public AnnotationClassPathXmlApplicationContext(String packageName) throws DocumentException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        //扫描包下面的类是否有注解
        String path = packageName.replace(".","/");
        //项目路径
        URL url = this.getClass().getClassLoader().getResource(path);
        //判断路径是否存在
        if(url != null) {
            File file = new File(url.getPath());
            //判断是否是目录
            if(file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File file2 : listFiles) {
                    String fileName = file2.getName();
                    String className =packageName+"."+fileName.substring(0,fileName.indexOf("."));
                    //System.out.println(className);
                    Class cls = Class.forName(className);
                    Object obj = cls.newInstance();
                    //判断是否有Component注解
                    if(cls.isAnnotationPresent(Component.class)) {
                        //获取实例名
                        String id = ((Component)cls.getAnnotation(Component.class)).name();
                        if("".equals(id)) {
                            id = cls.getSimpleName().toLowerCase();
                        }
                        //注入属性值
                        Field[] fields = cls.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            SetValue setValue = field.getAnnotation(SetValue.class);
                            if(setValue != null) {
                                if("no".equals(setValue.isPOJO())) {
                                    field.set(obj,setValue.value());
                                }else {
                                    field.set(obj,map.get(setValue.value()));
                                }
                            }
                        }
                        map.put(id,obj);
                    }
                }
            }
        }
    }
    @Override
    public Object getBean(String beanName) {
        return map.get(beanName);
    }


    public class DocumentException extends Exception {
    }
}
