package cz.codingmonkey.tests;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author Richard Stefanca
 */
public abstract class AbstractFibonacciSampler extends AbstractJavaSamplerClient {

    private final int DEFAULT_N = 20;
    protected int n;

    @Override
    public void setupTest(JavaSamplerContext context) {
        this.n = context.getIntParameter("N", DEFAULT_N);
        this.getLogger().info("Fibonacci: " + n);
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument("N", String.valueOf(DEFAULT_N));
        return args;
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.setResponseMessageOK();
        try {
            sampleResult.sampleStart();
            runTestInternal();
            sampleResult.setSuccessful(true);
        } catch (Exception ex) {
            getLogger().error(ex.toString());
            sampleResult.setSuccessful(false);
        }

        sampleResult.sampleEnd();
        return sampleResult;
    }

    protected abstract void runTestInternal();

    @Override
    public void teardownTest(JavaSamplerContext context) {
        this.getLogger().debug("teardown");
    }
}
