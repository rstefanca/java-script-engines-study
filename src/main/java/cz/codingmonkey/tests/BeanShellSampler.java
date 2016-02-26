package cz.codingmonkey.tests;

import java.io.Serializable;

import static cz.codingmonkey.tests.BeanShellUtils.runInterpreter;

/**
 * @author Richard Stefanca
 */
public class BeanShellSampler extends AbstractFibonacciSampler implements Serializable {

    @Override
    protected void runTestInternal() {
        runInterpreter(n);
    }

    public static void run(int loops, int n) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            runInterpreter(n);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }
}
