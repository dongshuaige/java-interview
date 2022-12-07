package org.hgd.interview.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @description:
 * @author：hgd
 * @date: 2022/12/6
 */
public class App64_2 {
    public static void main(String[] args) {
        // aspect = 通知(advice) + 切点(pointcut),一个切面类中可能有多个通知方法
        // advisor = 更细粒度的切面, 包含一个通知和切点
        // ProxyFactory 通过代理对象进行功能增强
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置目标对象
        proxyFactory.setTarget(new Target());
        //添加通知, MethodInterceptor 是环绕通知
//        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
//            try {
//                System.out.println("before...");
//                // 调用目标
//                return invocation.proceed();
//            } finally {
//                System.out.println("after...");
//            }
//        });
        //定义切点 只增强foo 方法
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        // 切面
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, (MethodInterceptor) invocation -> {
            try {
                System.out.println("before...");
                // 调用目标
                return invocation.proceed();
            } finally {
                System.out.println("after...");
            }
        }));

        // 使用JDK代理的方式 接口进行代理 T1 proxy = (T1) proxyFactory.getProxy();
//        proxyFactory.addInterface(T1.class);
        // 强制使用Cglib 作为代理
        proxyFactory.setProxyTargetClass(true);
        // 创建代理对象
        Target proxy = (Target) proxyFactory.getProxy();
        proxy.foo();
        proxy.bar();
    }

    interface T1 {
        void foo();

        void bar();
    }

    static class Target implements T1 {

        @Override
        public void foo() {
            System.out.println("target foo");
        }

        @Override
        public void bar() {
            System.out.println("target bar");
        }
    }
}
