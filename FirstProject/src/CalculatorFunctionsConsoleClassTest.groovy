class CalculatorFunctionsConsoleClassTest extends GroovyTestCase {
    def ccl = new CalculatorFunctionsEquilConsoleInputClass();

    void testFirstConsole() {
        assertEquals(16, ccl.runEqualConsole(8+8))
    }

    void testSecondConsole() {
        assertEquals(128, ccl.runEqualConsole("64+64"))
    }

    void testLastConsole() {
        assertEquals(387420489, ccl.runEqualConsole(3**3**2**3))
    }

    void testMinusConsole() {
        assertEquals(-9, ccl.runEqualConsole(3-3-3-3-3))
    }

    void testXORConsole() {
        assertEquals(1, ccl.runEqualConsole(1|0))
    }

    void testAmpersandConsole() {
        assertEquals(0, ccl.runEqualConsole(1&0))
    }

    void testFloarConsole() {
        assertEquals(46947046.59769235, ccl.runEqualConsole(23423523.25235235+23523523.34534))
    }

    void testBracketsConsole() {
        assertEquals(136, ccl.runEqualConsole((2-4)**2+2*(34+32)))
    }

    void testProcentConsole() {
        assertEquals(3.0303030303, ccl.runEqualConsole(100/33))
    }

    void testSqrtConsole() {
        assertEquals(3.1622776601683795, ccl.runEqualConsole(Math.sqrt(10)))
    }

    void testAbsConsole() {
        assertEquals(10, ccl.runEqualConsole(Math.abs(-10)))
    }

    void testCbrtConsole() {
        assertEquals(4.932424148660941, ccl.runEqualConsole(Math.cbrt(120)))
    }

    void testExponentConsole() {
        assertEquals(6, ccl.runEqualConsole(Math.getExponent(120)))
    }

    void testLog10tConsole() {
        assertEquals(2.0791812460476247, ccl.runEqualConsole(Math.log10(120)))
    }

    void testUlpConsole() {
        assertEquals(0.0000076293945, ccl.runEqualConsole(Math.ulp(120)))
    }

}
