import CalculatorFunctionsEquilConsoleInputClass

class CalculatorFunctionsConsoleClass{
    private InputConsole(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
            print "Input:"
            def userInput = br.readLine()
            def cn = new CalculatorFunctionsEquilConsoleInputClass();
            cn.runEqualConsole(userInput)
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    public runInputConsole(){
        InputConsole()
    }
}