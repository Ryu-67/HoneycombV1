package com.core.base;

import java.util.ArrayList;
import java.util.Collections;

public class CellGroup implements Cell {

    private ArrayList<Cell> commands = new ArrayList<>();

    public CellGroup(Cell... commands) {
        Collections.addAll(this.commands, commands);
    }

    @Override
    public void init() {
        for(Cell c : commands) {
            c.init();
        }
    }

    @Override
    public void update() {
        for(Cell c : commands) {
            c.update();
        }
    }

    @Override
    public boolean isFinished() {
        for(Cell c : commands) {
            if (!c.isFinished()) {
                return false;
            }
        }
        return true;
    }
}
