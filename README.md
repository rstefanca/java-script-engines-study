# java-script-engines-study

This project show cases using different scripting possibilities in Java. It also creates Java Samplers for performace testing using JMeter.

Scripts call native java method Fibonacci.fib... 

JavaScript snippet

<code>
 public static final String FIB_JS =
            "var FibonacciClass = Java.type('cz.codingmonkey.tests.Fibonacci');\n" +
            "function fib(n) {\n" +
            "  return FibonacciClass.fib(n);\n" +
            "}";
</code>

Groovy snippet

<code>
public static final String FIB_GROOVY =
    "def fib(n) {" +
    "\tcz.codingmonkey.tests.Fibonacci.fib(n)" +
"}";
</code>

<p>

To run console application with Maven and default params use

<code>
mvn exec:java
</code>

<p>

See also <https://blazemeter.com/blog/beanshell-vs-jsr223-vs-java-jmeter-scripting-its-performance>