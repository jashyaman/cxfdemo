package com.keblal.cxfdemo.annotations;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// special annotation created as a group of annotations, used to annotate Rest services, and also to identify these rest
// web services ; refer: com.keblal.cxfdemo.scanners.RestServiceBeanScanner

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestService {
}