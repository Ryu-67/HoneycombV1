package com.core.tests;

import com.core.base.CellGroup;
import com.core.base.Executor;


public class UnitTest {

    public static Executor e = new Executor
            (
                    new AddCell(1,2),
                    new AddCell(1,3),
                    new CellGroup(
                            new AddCell(5,11),
                            new AddCell(12,12)
                    )
            );

    public static int i = 0;

    public static void main(String[] args) {
        e.start();
        while(i < 25) {
            e.update();
            System.out.println(e.complete());
            i++;
        }
    }

}
