import CalculatorFunctionsClass
import CalculatorFunctionsConsoleClass

public class CalculatorAppStartClass{
    public static void main(String [ ] args) {
        try {
            def fc = new CalculatorFunctionsClass();
            def fcc = new CalculatorFunctionsConsoleClass();
            //
            // THIS GUI VERSION:
            //
            //fc.runGUI();
            //
            // ;) Have a nice d-day
            //
            // THIS CONSOLE VERSION:
            //
            fcc.runInputConsole();
            //
        } catch(Exception ex) {
            println("Exception:" + ex);
        }
    }
}