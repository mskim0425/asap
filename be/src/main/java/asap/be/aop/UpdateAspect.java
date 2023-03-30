package asap.be.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class UpdateAspect {

    @Around("@annotation(asap.be.aop.ForUpdate)")
    public Object getProduct(ProceedingJoinPoint joinPoint) throws Throwable {
        // 애플리케이션에서 Request 객체를 읽어옴
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] parmeterlist = joinPoint.getArgs(); //요청 파라미터값

        if(request != null){
            String update = request.getHeader("update");//DTO작업
        }

        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
