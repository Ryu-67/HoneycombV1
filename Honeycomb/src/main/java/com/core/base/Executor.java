package com.core.base;

import java.util.ArrayList;
import java.util.Collections;

public class Executor {

    private ArrayList<Cell> commands = new ArrayList<>();

    public Executor(Cell... commands) {
        Collections.addAll(this.commands, commands);
    }

    public void start() {
        i = commands.get(0);
        i.init();
        flag = 1;
    }

    private Cell i;
    private int flag = 0;
    private boolean isFinished = false;

    public void update() {
        if (flag ==0) {
            if(commands.size() != 0) {
                i = commands.get(0);
                i.init();
                flag = 1;
                isFinished = false;
            } else {
                isFinished = true;
                return;
            }
        }

        if (i.isFinished()) {
            commands.remove(0);
            flag = 0;
        } else {
            i.update();
        }
    }

    public boolean complete() {
        return isFinished;
    }

}