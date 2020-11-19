package com.lv.study.hanmeimei.interceptor;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

public class RequestContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    /**
     * 返回一个装饰类
     *
     * @param callable
     * @param <T>
     * @return Callable
     */
    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new RequestAttributeAwareCallable<>(callable, RequestContextHolder.getRequestAttributes()); // 获取本线程的requestAttributes用来传递给hystrix线程
    }

    /**
     * 装类包含设置request的方法
     *
     * @param <T>
     */
    static class RequestAttributeAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;
        private final RequestAttributes requestAttributes;

        /**
         * 创建时传入request
         *
         * @param callable
         * @param requestAttributes
         */
        public RequestAttributeAwareCallable(Callable<T> callable, RequestAttributes requestAttributes) {
            this.delegate = callable;
            this.requestAttributes = requestAttributes;
        }

        /**
         * 执行该方法将其他线程传入的request注入到本线程中，也可以用来注入其他想要传递的参数
         *
         * @return
         * @throws Exception
         */
        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes); // 这里给spring的RequestContextHolder注入了requestAttributes也可以设在其他的值用来共享
                return delegate.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }


}
