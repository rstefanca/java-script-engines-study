package cz.codingmonkey.scripting;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * @author Richard Stefanca
 */
public class BeanShellUtils {

    public static String SCRIPT =
            "public static long fib(int n) {\n" +
            "        return cz.codingmonkey.scripting.Fibonacci.fib(n);\n" +
            "    }\n" +
            " fib(n);\n";

    public static void runInterpreter(int n) {
        try {
            Interpreter interpreter = new Interpreter();
            eval(interpreter, n);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void eval(Interpreter interpreter, int n) throws EvalError {
        interpreter.set("n", n);
        interpreter.eval(SCRIPT);
        interpreter.unset("n");
    }
}
