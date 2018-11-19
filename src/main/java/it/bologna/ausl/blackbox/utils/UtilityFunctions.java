package it.bologna.ausl.blackbox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author gdm
 */
public class UtilityFunctions {
         /**
     *
     * @param <T>
     * @param objectClass
     * @param annotationClass
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public static <T extends Annotation> T getFirstAnnotationOverEntity(Class objectClass, Class<T> annotationClass) throws ClassNotFoundException {
        do {
            Annotation annotation = objectClass.getAnnotation(annotationClass);
            if (annotation != null) {
                return (T) annotation;
            } else {
                objectClass = objectClass.getSuperclass();
            }
        } while (!objectClass.isAssignableFrom(Object.class));
        
        return null;
    }
    
    public static String getArrayString(ObjectMapper objectMapper, List list) throws JsonProcessingException {
        if (list == null)
            return null;
//        return String.format("string_to_array('%s', ',')", String.join(",", list));
        return objectMapper.writeValueAsString(list).replaceAll("^\\[", "{").replaceAll("\\]$", "}");
    }
    
    public static Object getPkValue(Object entity) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method primaryKeyGetMethod = getPrimaryKeyGetMethod(entity.getClass());
        return primaryKeyGetMethod.invoke(entity);
    }
    
    public static Method getPrimaryKeyGetMethod(Class entityClass) throws NoSuchMethodException {
        Field primaryKeyField = getPrimaryKeyField(entityClass);
        return getGetMethod(entityClass,primaryKeyField.getName());
    }
    
    public static Field getPrimaryKeyField(Class entityClass) {
        List<Field> declaredFields = new ArrayList(Arrays.asList(entityClass.getDeclaredFields()));
        Class superclass = entityClass.getSuperclass();
        while (superclass != null) {
            declaredFields.addAll(new ArrayList(Arrays.asList(superclass.getDeclaredFields())));
            superclass = superclass.getSuperclass();
        }
        Field res = null;
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Id.class) != null) {
                res = declaredField;
                break;
            }
        }
        return res;
    }
    
    public static Method getGetMethod(Class entityClass, String fieldName)  {

        Method result = ReflectionUtils.findMethod(entityClass, "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fieldName));
        if (result == null){
            result = ReflectionUtils.findMethod(entityClass, "is" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fieldName));
            if (result!=null)
                return result;
            else
                throw new RuntimeException(String.format("metodo get per il campo %s non trovato", fieldName));
        } else
            return result;
    }
}
