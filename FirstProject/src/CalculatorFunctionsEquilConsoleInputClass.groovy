import org.codehaus.groovy.control.CompilerConfiguration

class CalculatorFunctionsEquilConsoleInputClass{
    private EqualConsole(input){
        try {
            def compilerConfiguration = new CompilerConfiguration()
            def binding = new Binding()
            def shell = new GroovyShell(this.class.classLoader, binding, compilerConfiguration)
            def result = shell.evaluate input
            println result
            return result
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    public runEqualConsole(input){
        EqualConsole(input)
    }
}
