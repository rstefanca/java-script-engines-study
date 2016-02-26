package cz.codingmonkey.scripting;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

import java.io.Serializable;
import java.util.function.Supplier;

import static cz.codingmonkey.scripting.InvocableScriptEngine.InvocableScriptEngineBuilder;

/**
 * @author Richard Stefanca
 */
public class JSR223Sampler extends AbstractFibonacciSampler implements Serializable {

    private static final String ENGINE_NAME_PARAM = "EngineName";
    private static final String INIT_ONCE_PARAM = "InitOnce";

    private Supplier<InvocableScriptEngine> engineSupplier;

    private static Supplier<InvocableScriptEngine> getEngineSupplier(String engineName, boolean initOnce) {
        if (initOnce) {
            final InvocableScriptEngine engine = getFibonacci(engineName);
            return () -> engine; // returns initialized engine
        } else {
            return () -> getFibonacci(engineName); // returns new engine for each call
        }
    }

    private static InvocableScriptEngine getFibonacci(String engineName) {
        return InvocableScriptEngineBuilder.builder()
                .withScriptEngine(engineName)
                .withScript(JSR223Utils.getScriptByEngineName(engineName))
                .withFunction("fib")
                .build();
    }

    private static Object invoke(Supplier<InvocableScriptEngine> engineSupplier, int n) {
        return engineSupplier.get().invoke(n);
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        String engineName = context.getParameter(ENGINE_NAME_PARAM);
        getLogger().info("Engine: " + engineName);

        boolean initOnce = Boolean.parseBoolean(context.getParameter(INIT_ONCE_PARAM));
        getLogger().info("Init engine every test: " + !initOnce);

        engineSupplier = getEngineSupplier(engineName, initOnce);
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = super.getDefaultParameters();
        args.addArgument(ENGINE_NAME_PARAM, "nashorn");
        args.addArgument(INIT_ONCE_PARAM, "true");
        return args;
    }

    @Override
    protected void runTestInternal() {
        invoke(engineSupplier, n);
    }

    public static void run(int loops, int n, String engineName, boolean initOnce) throws Exception {
        long start = System.currentTimeMillis();
        Supplier<InvocableScriptEngine> engineSupplier = getEngineSupplier(engineName, initOnce);
        for (int i = 0; i < loops; i++) {
            invoke(engineSupplier, n);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }
}
