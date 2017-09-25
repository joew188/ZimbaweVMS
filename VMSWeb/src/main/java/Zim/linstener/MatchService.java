package Zim.linstener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Laxton-Joe on 2017/9/18.
 */
public class MatchService {
    @PostConstruct
    public void  init(){
        Thread t = new Thread(new MatchRunnable());
        t.setPriority(Thread.MIN_PRIORITY);
        t.setDaemon(true);  //后台线程
        t.start();

    }

    @PreDestroy
    public void  dostory(){
    }
}
