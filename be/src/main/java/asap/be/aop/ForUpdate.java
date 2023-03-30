package asap.be.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//메서드 호출시 사용하겠다
@Retention(RetentionPolicy.RUNTIME) //런타임시 유지시키겠다.
public @interface ForUpdate {
}
