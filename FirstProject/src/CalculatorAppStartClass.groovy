import CalculatorFunctionsClass
import CalculatorFunctionsConsoleClass

public class CalculatorAppStartClass{
    public static void main(String [ ] args) {
        try {
            def fc = new CalculatorFunctionsClass();
            def fcc = new CalculatorFunctionsConsoleClass();
            //fc.runGUI();
            fcc.runInputConsole();
        } catch(Exception ex) {
            println("Exception:" + ex);
        }
    }
}
