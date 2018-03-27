import java.util.regex.Pattern
import java.util.regex.Matcher

    //make * / + - and round brackets only first level!!
     //for example statement:
    //String math_statement = 3.3*34/26.23-5.32-5.544/65+56/5*320.3*6.34/5+32.43-(2*78.5/45*43/13-4+34+54.1332-3.76)/45+45/2*(20.32/34/54.3434*34-23+43-233*32+4.222*5.5432*6.10+32)*6.30*34.12+10.43/23-(23+45-43*4)-43-5+4-(34+100)+(54+54-300)-(15*3000-34+90*4*32)*3*26-5-5*(320*6+32-2*78)+54-3*20+4*5*6*6*(34+103*20)+4*5*6*6+(5*3+4)-91*43-(23+54-23+8743+54*4)-3*26-5-325*(320*6+32+2*378)+(54-3-2*0-43*5*-236*6)*(34+10-3*20)+4*5+36*6+(5*5/3+4)-91-43-(23+98/354-34+23+87*43+54*4)+(5+6)-(3/4-5+4)-(34+100)+(54+54-300)-(15*3000-34+90*4*32)*3*26-5-5*(320*6+32-2*78)+54-(3*20+4*5)*6*6*(34+103*20)+4*5/4*6*6+(5*3+4)-91*43-(23+54-23+87/43+54*4)-3*2/6-5-325*(320*6+32+2*37/8)+54-3-20-43*5*-2/36*6*(34+103*20)+4*5+36*6+(5*3+4)-91-43-(23+5*43/34-4000*4)
    String math_statement = System.console().readLine('Enter you expression: \n').replaceAll("\\s","")
    //initialize result var
    def final_result = 0;
    Pattern p_alg_sum = Pattern.compile(/(\d*[\+-\\*]\d*)+/);
    Pattern scobenchik = Pattern.compile(/\([\d+\.\+\-\*\/]+\)/)   //to identity parentheses
    Matcher m = p_alg_sum.matcher(math_statement)
    ////println "Start parsing!"
    
    //detect how much blocks of level 1 in input string
    def int count_blocks_parent = 0;
    def String without_parent = math_statement
    Matcher scobki = scobenchik.matcher(math_statement);
    ////println "Start parsing!"
     while(scobki.find())
     {
	def String tempString = scobki.group(0)
	String truncated_string = ""
	//println tempString;
        //remember position in main string
	tempString1 = tempString.replaceAll("\\(","\\\\\\(")
	tempString1 = tempString1.replaceAll("\\)","\\\\\\)")
	tempString1 = tempString1.replaceAll("\\+","\\\\\\+")
	tempString1 = tempString1.replaceAll("\\*","\\\\\\*")
	//println "tempString1 = " + tempString1
	//tempString1 = "\\(2\\*78\\+54-3\\)"
	//println "tempString1 = $tempString1"
	tempString = tempString.replaceAll("\\(","")
	tempString = tempString.replaceAll("\\)","")
	//println "tempString = " + tempString
	//unnessesary treatment	
	//this block, is like below!!!!!!!!! make like functions
	//println "TEMP RESULT COMP " + (count_blocks_parent + 1) + " = " + Comp(tempString);
	without_parent = without_parent.replaceAll(tempString1, Double.toString(Comp(tempString)))
		//println "tempString2 = " + tempString1
	//println "result string = " + without_parent
	//end of block!!!!!!!
	count_blocks_parent++;
     }
    without_parent = without_parent.replace("--","+")
    //println "without_parent = " + without_parent
    final_result = Comp(without_parent)
    println "FINAL RESULT = " + final_result
    //detect how much blocks of level 2 in input string    
    //println "FINAL RESULT = " + Double.toString(Comp(math_statement));

def Comp(String math_statement)
{
    double result_composition = 1;
    int count_blocks_operators_1 = 0;
    def String znaki1 = "[\\+-]?(\\d+(\\.\\d+)?[\\*\\/]-?\\d+(\\.\\d+)?+(([\\*\\/]-?\\d+(\\.\\d+)?){1,})?){1,}"
    Pattern blocks1 = Pattern.compile(znaki1);
    Matcher m_blocks1 = blocks1.matcher(math_statement);
    List blocks_composition = []
    List tempResult = []
    
    while(m_blocks1.find())
     {	
 	tempString = m_blocks1.group(0)
	double intermediate_comp_result
	math_statement = math_statement.replace(tempString, "");
	//println "(After transformation) math_statement = " + math_statement
        blocks_composition.push(tempString);
        //work with first statement of level 1
		//it's need to learn how much blocks in subString
	Pattern p_comp = Pattern.compile("[\\*\\/]");
	Matcher m_comp = p_comp.matcher(tempString)
	List list_operators = []
	int count_in_sub_string = 0;
	while(m_comp.find())
    	 {
	       list_operators[count_in_sub_string] = m_comp.group(0)
	       //println "list_operators[" + count_in_sub_string + "] = " + list_operators[count_in_sub_string]
	       count_in_sub_string++;
	 }
	int cnumber_in_sub_string = count_in_sub_string + 1;
	List tempNumbers = []
	//cut numbers in sub_string
	Pattern numbers_in_sub = Pattern.compile("([\\+-])?\\d{1,}(\\.\\d+)?");
	Matcher m_numbers_in_sub = numbers_in_sub.matcher(tempString)
	m_numbers_in_sub.find();
	tempNumbers.push(Double.parseDouble(m_numbers_in_sub.group(0)));
	//println "tempNumbers[0] = " + tempNumbers[0]

	tempResult.push(tempNumbers[0])
	for(int i=1; i<cnumber_in_sub_string; i++)
          {
		//m_numbers_in_sub.find();
		m_numbers_in_sub.find();
		tempNumbers.push(Double.parseDouble(m_numbers_in_sub.group(0)))
		if(list_operators[i - 1] == '*')
			tempResult[count_blocks_operators_1] *= tempNumbers[i]
		else
			tempResult[count_blocks_operators_1] /= tempNumbers[i]
		//println Double.toString(i + 1) + " temp_number[i] = " + Double.toString(tempNumbers[i]) 
	  }
	//on that moment have numbers in substrings	
//	//println "Number of blocks in " + tempString + " is " + Double.toString(count_in_sub_string)
	count_blocks_operators_1++;
     }
    //println "count blocks of level 1 = " + Double.toString(count_blocks_operators_1);
    //println blocks_composition
	if(count_blocks_operators_1 != 0)
	  {
	    //println "result of level 1 = " + Double.toString(result_composition);
	    //println "tempResult = " + tempResult
	    for(int i=1; i<count_blocks_operators_1; i++)
		{
			tempResult[0] += tempResult[i]
		}
	    //println "Result of composition = " + tempResult[0]
	    //make in sequence of priority (* or /)
	    //println "(After transformation) math_statement = " + math_statement
	    double intermediate_res = Double.parseDouble(AlgSubMin(math_statement))
	    intermediate_comp_result = intermediate_res + tempResult[0]
	    //println "result comp = " + Double.toString(intermediate_comp_result)
	   }
	else{
		//println "No '*' blocks!"
		intermediate_comp_result = Double.parseDouble(AlgSubMin(math_statement))
	}
    return intermediate_comp_result
}

def AlgSubMin(String math_statement)
{
    Pattern plus = Pattern.compile(/\+/)
    Pattern minus = Pattern.compile(/-/)
    Pattern number1 = Pattern.compile(/([\+-])?\d+(\.\d+)?/);
    int count_blocks_operators = 0;
    double result = 0;
	    def String znaki2 = "([\\+-]|[^\\+\\*-])(\\d*(\\.\\d*)?[\\+-]){1}"
		    Pattern blocks = Pattern.compile(znaki2);
		    Matcher m_blocks = blocks.matcher(math_statement);
			    while(m_blocks.find())
			     {	
				count_blocks_operators++;
			     }
			    //println "count blocks of level 2 = " + Double.toString(count_blocks_operators);	
			    if(count_blocks_operators != 0)
			       {
				    int count_numbers = count_blocks_operators + 1;
				    Matcher m2 = number1.matcher(math_statement)
				    def testArray = new double[count_numbers]

				    String operators = new String[count_blocks_operators]
				    m2.find()
				    testArray[0] = Double.parseDouble(m2.group(0))
				    //println Double.toString(1) + " number = " + Double.toString(testArray[0])
				    for(int i = 1; i < count_numbers; i++)
					{
					    m2.find()
					    testArray[i] = Double.parseDouble(m2.group(0))
					    //println Double.toString(i + 1) + " number = " + Double.toString(testArray[i])    
					}
				    Matcher m_operator = plus.matcher(math_statement);
				    List tempList = []
				    String statements = new String[count_blocks_operators]

				    result = testArray[0]
				    for(int i = 1; i <= count_blocks_operators; i++)
				    {
					    //detect what operators(+-)
					    //form reg expressions
					    String str1 = Double.toString(testArray[i-1]) + "\\+" + Double.toString(testArray[i])	    
					    tempList.push(str1)
					    //println tempList[i-1]
					    
					    //do alg_sum!
					    result += testArray[i]
				    }
			      }
			   else if(math_statement != "")
				{
					result = Double.parseDouble(math_statement)
				}
	//println "intermediate result = " + Double.toString(result);
	return Double.toString(result)
}

