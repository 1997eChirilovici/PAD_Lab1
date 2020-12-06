package main;

import lombok.extern.log4j.Log4j;
import main.service.GenericThread;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static main.consts.SimpleConst.partOfThreadName;

@Log4j
public class MainThread {

    public static void main(String[] args) {

        Map<String, Thread> mapOfMainThread = new HashMap<>();
        Map<String, GenericThread> mapOfGenericThread = new HashMap<>();

        for(int i = 1; i <= 6; i++) {
            String threadName = partOfThreadName + i;

            GenericThread myGenericThread = new GenericThread("Eugeniu");

            mapOfGenericThread.put(threadName, myGenericThread);
            mapOfMainThread.put(threadName, new Thread(myGenericThread, threadName));
        }

        mapOfGenericThread.get(partOfThreadName + 5)
                          .setThreads(Stream.of(
                                  mapOfMainThread.get(partOfThreadName + 1),
                                  mapOfMainThread.get(partOfThreadName + 2),
                                  mapOfMainThread.get(partOfThreadName + 3)
                          ));

        mapOfGenericThread.get(partOfThreadName + 6)
                          .setThreads(Stream.of(
                                  mapOfMainThread.get(partOfThreadName + 2),
                                  mapOfMainThread.get(partOfThreadName + 3),
                                  mapOfMainThread.get(partOfThreadName + 4)
                          ));

        log.info("Start threads");
        mapOfMainThread.values().forEach(Thread::start);

        log.info("Finished MainThread");
    }

}
