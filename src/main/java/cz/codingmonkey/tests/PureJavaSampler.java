package cz.codingmonkey.tests;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

import java.io.Serializable;

import static cz.codingmonkey.tests.Fibonacci.fib;

/**
 * @author Richard Stefanca
 */
public class PureJavaSampler extends AbstractFibonacciSampler implements Serializable {

    @Override
    protected void runTestInternal() {
        fib(n);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        this.getLogger().debug("teardown");
    }

    public static void run(int loops, int n) throws Exception{
        long start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            fib(n);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }
}
