package main;

import GUI.StartView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main_view {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        StartViewRunnable startViewRunnable = new StartViewRunnable();

        executorService.submit(startViewRunnable);
        executorService.submit(startViewRunnable);
        executorService.submit(startViewRunnable);

        executorService.shutdown();
    }
}
