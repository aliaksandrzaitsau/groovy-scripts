import groovy.swing.SwingBuilder
import org.codehaus.groovy.control.CompilerConfiguration

import javax.swing.JTextField
import java.awt.BorderLayout

class CalculatorFunctionsClass{
    def sb = new SwingBuilder();

    private GUI(){
        sb.frame(id:"form", title:"MNT-Lab Calc 18K", visible:true, resizable:false){
            borderLayout()
            textField(id:"out", editable:false, horizontalAlignment:JTextField.RIGHT, constraints:BorderLayout.NORTH);
            //
            // http://groovy-lang.org/swing.html
            //
            panel(constraints:BorderLayout.CENTER){
                gridLayout(cols:6, rows:7, hgap:4, vgap:4)

                label("")
                label("")

                panel(constraints: BorderLayout.SOUTH) {
                    button text: 'XC', actionPerformed: {
                        removeOneSymbol()
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: 'C', actionPerformed: {
                        clearOut()
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '7', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '8', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '9', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '/', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '4', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '5', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '6', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '*', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '1', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '2', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '3', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '-', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '0', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '.', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '=', actionPerformed: {
                        Equal()
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '(', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: ')', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '**', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '|', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '%', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '&', actionPerformed: {
                        insertString(it.source.text)
                    }
                }

                panel(constraints: BorderLayout.SOUTH) {
                    button text: '+', actionPerformed: {
                        insertString(it.source.text)
                    }
                }
            }
        }
        sb.form.pack();
    }

    private insertString = { text ->
        try {
            return sb.out.text += text
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    private removeOneSymbol = { ->
        try {
            return sb.out.text = sb.out.text[0..sb.out.text.length() - 2]
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    private clearOut = { ->
        try {
            return sb.out.text = "";
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    private Equal() {
        try {
            def compilerConfiguration = new CompilerConfiguration()
            def binding = new Binding()
            def shell = new GroovyShell(this.class.classLoader, binding, compilerConfiguration)
            String expression = sb.out.text
            def result = shell.evaluate expression
            return sb.out.text = result
        } catch (Exception ex) {
            //println("Exception:" + ex);
        }
    }

    public runEqual(){
        Equal()
    }

    public runGUI(){
        GUI()
    }

}