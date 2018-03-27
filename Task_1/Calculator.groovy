class Calc {
    public String string = System.console().readLine('Enter you expression: \n').replaceAll("\\s","")
    public void start() {
      Calculate(this.resolve(this.string))
    }
    private operations = ["^": 3,"*": 2,"/": 2, "+": 1,"-": 1] // operation: rang
    private out = [] as Queue //Output list
    private oper = [] as Queue //Operations list

    private resolve(str) {
      '''RESOLVES string and returns polish notation'''
      def flag = true //Used for combining numbers
      def previous //Stores previous number
      str.each{
        if (it.isNumber() || it == ".") {
          if (flag) {
            out.offerLast(it)
            flag = false
            previous = it
          } else if (!flag) {
            previous += it
            out.pop()
            out.offerLast(previous)
          }
        } else if (operations.containsKey(it))
        {
          if (oper?.empty) {
            oper.offerLast(it)
            flag = true
          } else{
            if (operations[oper.last()] > operations[it]) {
              out.offerLast(oper.first())
              oper.pop()
              oper.offerLast(it)
              flag = true
            } else if (operations[oper.last()] < operations[it]) {
              oper.offerLast(it)
              flag = true
            } else if (operations[oper.last()] == operations[it]) {
              out.offerLast(oper.last())
              oper.pop()
              oper.offerLast(it)
              flag = true
            }
          }
        } else if (it == ")") {
          oper.remove(oper.lastIndexOf("("))
          while(oper.last() != "(") {
            out.add(oper.pop())
            if(oper?.empty) {
              break
            }
          }
        }else oper.offerLast(it)
      }
    out.addAll(oper.reverse())
    return out //Returns Polish Notation
  }

  private Operation(op, num2, num1){ //num1 and num2 changed because of reversed list
    '''Calculates elements'''
    num1 = num1.toDouble()
    num2 = num2.toDouble()
    def sum = { a, b -> return a+b }
    def minus = { a, b -> return a-b }
    def multiple = { a, b -> return a*b }
    def div = { a, b -> return a/b }
    def pows = { a, b -> return a**b }

    switch(op) {
      case "+":
      return sum(num1,num2)
      case "-":
      return minus(num1,num2)
      case "*":
      return multiple(num1,num2)
      case "/":
      return div(num1,num2)
      case "^":
      return pows(num1,num2)
    }
 }

    private Calculate(polish){
      def left = polish.reverse()
      def right = []
      while(!left?.empty) {
        if (left.last().isNumber()) {
          right << left.pop()
        }else{
          right << this.Operation(left.last(), right.pop(), right.pop())
          left.pop()
        }
      }
      println "Result ${this.string}=${right.last().round(3)}"
    }
}

def Calculator = new Calc()

try {
  Calculator.start()
} catch (IndexOutOfBoundsException ex) {
  println "Wrong number of brackets!"
} catch (NoSuchElementException ex) {
  println "Expression is not valid. Enter only numbers, delimiter is '.'"
}
