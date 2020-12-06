package main.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.util.stream.Stream;

import static main.consts.SimpleConst.partOfThreadName;

@Setter
@Getter
@Log4j
@RequiredArgsConstructor
public class GenericThread implements Runnable {

    private final String message;

    private Stream<Thread> threads;

    public void run() {
        if(Thread.currentThread().getName().equals(partOfThreadName + 4)) {
            try {
                Thread.sleep(200);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitThreads(threads);
        log.info("Hi " + message);
    }

    private void waitThreads(Stream<Thread> threads) {
        if(threads == null) {
            return;
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });

        log.info(Thread.currentThread().getName() + " awaited to continue");
    }

}
