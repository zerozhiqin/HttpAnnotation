package dev.misono.httpannotation.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field代表的是http连接中key-value传入的内容（BodyParameter）
 * 例如 @Field("req") String name 会与URL组成
 * 127.0.0.1/1.0.1/res?req=name
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Field {
    String value();
}
