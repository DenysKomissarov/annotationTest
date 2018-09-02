package com.computools.annotation;

import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DenisAutowire {
}
