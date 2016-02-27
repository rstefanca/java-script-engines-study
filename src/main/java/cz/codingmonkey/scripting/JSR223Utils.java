package cz.codingmonkey.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Richard Stefanca
 */
public final class JSR223Utils {

    public static final String FIB_JS =
            "var FibonacciClass = Java.type('cz.codingmonkey.tests.Fibonacci');\n" +
            "function fib(n) {\n" +
            "  return FibonacciClass.fib(n);\n" +
            "}";

    public static final String FIB_GROOVY =
            "def fib(n) {" +
            "\tcz.codingmonkey.tests.Fibonacci.fib(n)" +
            "}";

    public static final String ENGINE_NASHORN = "nashorn";
    public static final String ENGINE_GROOVY = "groovy";

    private static Map<String, String> SCRIPTS_BY_ENGINE = new HashMap<>(2);
    static {
        SCRIPTS_BY_ENGINE.put(ENGINE_NASHORN, FIB_JS);
        SCRIPTS_BY_ENGINE.put(ENGINE_GROOVY, FIB_GROOVY);
    }

    public static String getScriptByEngineName(String engineName) {
        if (!SCRIPTS_BY_ENGINE.containsKey(engineName)) {
            throw new RuntimeException("Unknown engine");
        }

        return SCRIPTS_BY_ENGINE.get(engineName);
    }

    public static ScriptEngine getInvocableEngine(String engineName) throws ScriptException {
        if (engineName == null) {
            throw new IllegalArgumentException("engineName");
        }

        ScriptEngine engine = new ScriptEngineManager().getEngineByName(engineName);
        if (engine == null) {
            if (engineName.equals(ENGINE_GROOVY)) {
                throw new RuntimeException("Groovy engine cannot be initialized. Add groovy-all dependency.");
            }
            throw new RuntimeException("Engine not found");
        }
        return engine;
    }

    public static ScriptEngine getInvocableEngine(String engineName, String script) throws ScriptException {
        if (script == null) {
            throw new IllegalArgumentException("script");
        }

        ScriptEngine engine = getInvocableEngine(engineName);
        engine.eval(script);
        return engine;
    }

}
