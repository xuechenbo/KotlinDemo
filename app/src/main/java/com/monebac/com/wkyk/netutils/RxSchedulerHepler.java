package com.monebac.com.wkyk.netutils;

/**
 * 封装 切换线程操作
 * 制定调度器  被观察者线程    Schedulers.io()
 * 线程调度   观察者线程      AndroidSchedulers.mainThread()
 */
public class RxSchedulerHepler {
//    public static <T> ObservableTransformer<T,T> io_main(){
//        return upstream -> upstream.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
