package Zim.linstener;

import Zim.common.SysConfigUtil;
import Zim.task.MatchRunnable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */
public class MatchService {
    static final ExecutorService matchThreadPool = Executors.newFixedThreadPool(Integer.parseInt(SysConfigUtil.getSetting("import-thread-num")));
    @PostConstruct
    public void  init(){
//        MatchRunnable matchRunnable=new MatchRunnable();
//         Thread t1=new Thread(matchRunnable);
//         Thread t2=new Thread(matchRunnable);
//         Thread t3=new Thread(matchRunnable);
////         MyThread1 mt2 = new MyThread1();  
////         MyThread1 mt3 = new MyThread1();  
//         t1.start();
//         t2.start();
//         t3.start();



       matchThreadPool.execute(new MatchRunnable());
//        Thread t = new Thread(new MatchRunnable());
//        t.setDaemon(true);  //后台线程
//        t.start();
//        MatchThread matchThread=new MatchThread();
//        matchThread.setDaemon(true);
//        matchThread.start();
    }

    @PreDestroy
    public void  dostory(){
    }
}
