package main;

import GUI.StartView;

public class StartViewRunnable implements Runnable{
    @Override
    public void run() {
        new StartView();
    }
}
