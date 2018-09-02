package com.computools;

import com.computools.annotation.DenisAutowire;
import com.computools.annotation.DenisBean;
import com.computools.annotation.DenisConfigAnnotation;
import com.computools.annotation.Service;
import com.computools.model.User;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationProcessor {

    public static  Map<String, Object> serviceMap = new HashMap<>();
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections("com.computools");

        Set<Class<? extends Object>> allClasses =
                reflections.getTypesAnnotatedWith(Service.class);

        for (Class clazz : allClasses) {
            System.out.println(clazz.getName());
            inspectService(clazz);
        }

    }

    static void inspectService(Class<?> service) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Object cc = service.newInstance();
        Field[] fields = cc.getClass().getDeclaredFields();


        for (Field field : fields){
            if (field.isAnnotationPresent(DenisAutowire.class)){
                field.setAccessible(true);
                field.set(cc, AnnotationProcessor.getClassByName(field.getGenericType()));
                System.out.println("field " + field.getName() + " annotated with DenisAutowired");

            }else{
                System.out.println("field " + field.getName() + " not annotated with DenisAutowired");
            }
        }
    }

    static Object getClassByName(Type type) throws IllegalAccessException, InstantiationException {

        Reflections reflections = new Reflections("com.computools");

        Set<Class<? extends Object>> allClasses =
                reflections.getTypesAnnotatedWith(DenisConfigAnnotation.class);

        for (Class clazz : allClasses) {

                Object obj = clazz.newInstance();

            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods ){
                if (method.isAnnotationPresent(DenisBean.class) && method.getGenericReturnType().equals(type)){

//                    User user = null;
                    try {
                        return method.invoke(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(";;;;;;;;;;;;;;" + user.getName() + ";;;;;;;;;;;;;;");

                }
            }
            System.out.println(clazz.getName());
        }

        return null;
    }
}

