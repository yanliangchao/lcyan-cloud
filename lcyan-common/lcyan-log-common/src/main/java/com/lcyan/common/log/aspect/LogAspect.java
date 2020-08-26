package com.lcyan.common.log.aspect;


/**
 * 日志切面，异步记录日志
 * @author ayan2070
 *
 */
//@Aspect
public class LogAspect {
/*
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint point, Log log) throws Throwable {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        logger.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
        com.github.tangyi.common.basic.model.Log logVo = LogUtil.getLog();
        logVo.setTitle(log.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(String.valueOf(endTime - startTime));
        logVo.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        SpringContextHolder.publishEvent(new LogEvent(logVo));
        return obj;
    }*/
}
