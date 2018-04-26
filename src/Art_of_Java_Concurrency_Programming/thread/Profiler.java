package Art_of_Java_Concurrency_Programming.thread;

import java.util.concurrent.TimeUnit;

/**
 * 构建一个常用profiler类，具有begin()和end()两个方法
 * end()方法返回从begin()方法调用开始到end()方法被调用的时间差
 */
public class Profiler {

    //第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
    private  static  final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public  static  final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end()+" mils");
    }

}
