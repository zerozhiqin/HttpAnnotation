package dev.misono.httpannotation.http;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解将URI中使用{}包裹的参数替为此值.</br>
 * 如果将调用注解参数的{@link #toString()}方法进行填充</br>
 * 例如
 *  @URI("/user/{uid}")
 *  void foo(@Path("uid") long userid);
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Path {
    String value();
}
