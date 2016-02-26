package cz.codingmonkey.tests;

import javax.script.Invocable;
import javax.script.ScriptEngine;

/**
 * @author Richard Stefanca
 */
public class InvocableScriptEngine {

    private final Invocable scriptEngine;

    private final String function;

    public static ScriptEngine getInvocableEngine(String engineName, String script) {
        try {
            return JSR223Utils.getInvocableEngine(engineName, script);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private InvocableScriptEngine(String scriptEngine, String script, String function) {
        //todo args check
        this.scriptEngine = (Invocable) getInvocableEngine(scriptEngine, script);
        this.function = function;
    }

    public Object invoke(Object... args) {
        try {
            return scriptEngine.invokeFunction(function, args);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static class InvocableScriptEngineBuilder {
        private String scriptEngine;
        private String script;
        private String function;

        private InvocableScriptEngineBuilder() {
        }

        public static InvocableScriptEngineBuilder builder() {
            return new InvocableScriptEngineBuilder();
        }

        public InvocableScriptEngineBuilder withScriptEngine(String scriptEngine) {
            this.scriptEngine = scriptEngine;
            return this;
        }

        public InvocableScriptEngineBuilder withScript(String script) {
            this.script = script;
            return this;
        }

        public InvocableScriptEngineBuilder withFunction(String function) {
            this.function = function;
            return this;
        }

        public InvocableScriptEngine build() {
            return new InvocableScriptEngine(scriptEngine, script, function);
        }

    }
}
