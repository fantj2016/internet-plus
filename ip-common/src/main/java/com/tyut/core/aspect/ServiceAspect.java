package com.tyut.core.aspect;

import com.tyut.core.utils.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by Fant.J.
 * 2018/5/20 16:19
 */
@Aspect
@Slf4j
public class ServiceAspect {


    @Around("@annotation(com.tyut.core.aspect.ServiceLog)")
    public Object logServiceInvoke(ProceedingJoinPoint pjp) throws Throwable{
        return doLog(pjp);
    }


    protected Object doLog(ProceedingJoinPoint pjp) throws Throwable{
        if (log.isInfoEnabled()){
            log.info("*****调用服务:"+pjp.getSignature().toLongString()+"*****");
            for (Object arg:pjp.getArgs()){
                PrintUtil.printObj(arg,"服务参数");
            }
            try{
                Object proceed = pjp.proceed();
                PrintUtil.printObj(proceed,"返回结果:");
                return proceed;
            }catch (Throwable e){
                log.info("抛出异常",e);
                throw e;
            }finally {
                log.info("*****调用服务结束******");
            }
        }

        return null;
    }
}
