package cz.codingmonkey.scripting;

/**
 * @author Richard Stefanca
 */
public class TestAll {

    public static void main(String[] args) throws Exception {
        int loops = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.println("Loops: " + loops);
        System.out.println("n: " + n);
        System.out.println("----------------------");
        System.out.println("Pure Java: ");
        PureJavaSampler.run(loops, n);
        System.out.println("Nashorn (JS) with new engine for each iteration:");
        JSR223Sampler.run(loops, n, JSR223Utils.ENGINE_NASHORN, false);
        System.out.println("Nashorn (JS) with reused:");
        JSR223Sampler.run(loops, n, JSR223Utils.ENGINE_NASHORN, true);
        System.out.println("Groovy with new engine for each iteration:");
        JSR223Sampler.run(loops, n, JSR223Utils.ENGINE_GROOVY, false);
        System.out.println("Groovy with reused engine:");
        JSR223Sampler.run(loops, n, JSR223Utils.ENGINE_GROOVY, true);
        System.out.println("Beanshell with new interpreter");
        BeanShellSampler.run(loops, n);
    }
}
