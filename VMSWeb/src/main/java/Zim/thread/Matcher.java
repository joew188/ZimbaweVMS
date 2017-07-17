package Zim.thread;

import Zim.task.MatchApplicant;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Laxton-Joe on 2017/6/23.
 */
public class Matcher implements Runnable {
    private ConcurrentLinkedQueue<String> queue;

    public Matcher(ConcurrentLinkedQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (!queue.isEmpty()) {

                    String applicantId = queue.poll();
                    //                        synchronized (_buffer) {
                    new MatchApplicant(applicantId).match();
//                        }
                }

            }
        }
    }
}
