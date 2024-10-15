package com.core.tests;

import com.core.base.Cell;

public class AddCell implements Cell {

    int n1, n2, n3 = 0;

    public AddCell(int n1, int n2) {

        this.n1 = n1;
        this.n2 = n2;

    }

    @Override
    public void init() {
        System.out.println("Initializing");
    }

    @Override
    public void update() {
        System.out.println("Updating");
        n3 = n1+n2;
        System.out.println(n3);
    }

    @Override
    public boolean isFinished() {
        System.out.println("Checking end condition");
        return n3 != 0;
    }

}
