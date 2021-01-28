package test;

import Expression.CalculationException;
import Expression.ParseException;
import Interpretation.Interpreter;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    private Interpreter interpreter;
    private Random random = new Random();

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
    }



    @Test
    void math() {
        // simple arithmetic
        Assertions.assertEquals(8.0, interpreter.interpret("(+ 3 5)"), .01);
        Assertions.assertEquals(12.0, interpreter.interpret("(* 6 2)"), .01);
        Assertions.assertEquals(70.0, interpreter.interpret("(/ 140 2)"), .01);
        Assertions.assertEquals(0, interpreter.interpret("(- 7 7)"), .01);

        Assertions.assertEquals(10.0, interpreter.interpret("(+ (+ 3 5) 2)"), .01);
        Assertions.assertEquals(20.0, interpreter.interpret("(* (+ 0 1) 20)"), .01);
        Assertions.assertEquals(30.0, interpreter.interpret("(/ (- 6 3) (/ 1 10))"), .01);
        Assertions.assertEquals(0, interpreter.interpret("(- (/ (* -2 -2) 2) (+ 1 1))"), .01);
        Assertions.assertEquals(0, interpreter.interpret("(with ([x (+ 4 9)])(+ x -13))"), .01);


        // these were generated using "generate Math Test"
        assertEquals(65536, interpreter.interpret("( * ( * ( * ( * ( * ( * ( * ( * ( * ( * ( * ( * ( * ( * ( * 2 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 ) 2 )"), .01);
        assertEquals(16, interpreter.interpret("( + ( + ( + ( + ( + ( + ( + ( + ( + ( + ( + ( + ( + ( + ( + 1 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 )"), .01);
        assertEquals(-16, interpreter.interpret("( - ( - ( - ( - ( - ( - ( - ( - ( - ( - ( - ( - ( - ( - ( - -1 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 ) 1 )"), .01);
        assertEquals( 35716.0, interpreter.interpret("( - -31 ( - -38 ( + 153 ( - 74 ( * -157 ( + 156 ( - 196 ( - -89 ( + -150 ( + -29 -36 ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( 7.20347289719049E-8, interpreter.interpret("( / -3 ( + 138 ( + -136 ( + -68 ( * -76 ( + 129 ( * -95 ( * -32 ( - 187 ( / -95 -14 ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( 367931.1439602868, interpreter.interpret("( * -34 ( + -59 ( - -33 ( * -124 ( / 190 ( * -148 ( / 49 ( * 24 ( / -95 ( / 136 -197 ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( 0.9673796938708135, interpreter.interpret("( / -119 ( + -67 ( + 86 ( + -142 ( / -199 ( + -98 ( + 113 ( * 99 ( + 158 ( / -5 170 ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( -305789.63333333336, interpreter.interpret("( - -179 ( - -73 ( - 167 ( * -106 ( - -166 ( + 0 ( * -33 ( + 68 ( + -172 ( / -13 ( * 120 ( / 174 ( + 176 ( + -84 ( * -184 189 ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( 8629.20924369748, interpreter.interpret("( / -190 ( / 140 ( - -161 ( / 141 ( / -85 ( + 112 ( - 76 ( - -58 ( / -44 ( / -16 ( + -47 ( - -66 ( - -33 ( * -12 ( + -85 199 ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"),.01);
        assertEquals( -122.49187840132583, interpreter.interpret("( - -103 ( - -20 ( / 170 ( / 155 ( - -109 ( + -73 ( / 84 ( * 144 ( - 79 ( / -80 ( + 53 ( + -40 ( + -99 ( + 197 ( - 69 91 ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"),.01);

        assertEquals( -89.0, interpreter.interpret("-89"), .01 );
        assertEquals( 4.173913043478261, interpreter.interpret("( * -192 ( / 3 -138 ) )"), .01 );
        assertEquals( -324117.07317073166, interpreter.interpret("( * -113 ( / -168 ( / -164 ( * 140 20 ) ) ) )"), .01 );
        assertEquals( -578.0, interpreter.interpret("( - -176 ( + 37 ( + 170 ( - 21 ( + -47 ( + -179 52 ) ) ) ) ) )"), .01 );
        assertEquals( -38685.37634408602, interpreter.interpret("( * -95 ( / 58 ( * -57 ( / 155 ( - -90 ( * -163 ( - -151 ( - 66 -163 ) ) ) ) ) ) ) )"), .01 );
        assertEquals( 176.6531956828035, interpreter.interpret("( / 73 ( / 121 ( - 138 ( - -156 ( / 158 ( - -133 ( * 22 ( / 91 ( + -140 ( * 44 -120 ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -3.0689704559503062E13, interpreter.interpret("( * 144 ( * 188 ( * -157 ( + -198 ( + -109 ( * -171 ( * 186 ( * -186 ( / -166 ( * 17 ( - 121 ( - 151 22 ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( 0.30966866710165397, interpreter.interpret("( / 61 ( + 100 ( + 165 ( + -68 ( / -99 ( * -40 ( - -163 ( / -146 ( + 189 ( + -22 ( / 23 ( - -89 ( / 33 ( + 112 166 ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -4255.009363410212, interpreter.interpret("( * -37 ( + 134 ( + 37 ( + 44 ( - -100 ( * 46 ( * -15 ( / 83 ( + 118 ( * 113 ( * 87 ( * -188 ( * 4 ( - 78 ( + 110 ( / 100 -72 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -244855.08133425543, interpreter.interpret("( - -114 ( * 105 ( - -6 ( / -89 ( / -118 ( + 31 ( - -197 ( * -181 ( - -16 ( / -37 ( - -105 ( - 79 ( / 74 ( - 180 ( + -23 ( - 119 ( - -106 ( - 87 -78 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );


        for(int i=0; i< 10; i++){
            int difficulty = i * 2;
            TestCase testCase = generateMathTest(difficulty);
            System.out.println(testCaseToAssert(testCase));
        }



    }

    private class TestCase{
        public TestCase(double expected, String expression) {
            this.expected = expected;
            this.expression = expression;
        }

        public double getExpected() {
            return expected;
        }

        private double expected;

        public String getExpression() {
            return expression;
        }

        private String expression;
    }

    private TestCase generateMathTest(int depth){


        int rand = Math.abs(random.nextInt())% 4;
        int number = random.nextInt() % 200;
        char[] operations = {'+','-','/','*'};

        if(depth == 0){
            return new TestCase(number,Integer.toString(number));
        }
        char operation = operations[rand];
        TestCase deeper = generateMathTest(depth-1);
        String testCase = "( "+operation+" "+Integer.toString(number)+" "+ deeper.getExpression()+" )";
        double expected = -99;

        if(operation == '*'){
            expected = deeper.expected * number;
        }
        if(operation == '+'){
            expected = deeper.expected + number;
        }
        if(operation == '/'){
            expected =  number /deeper.expected;
        }
        if(operation == '-'){
            expected = number -deeper.expected ;
        }

        return new TestCase(expected,testCase);




    }

    private String testCaseToAssert(TestCase testCase){
        return "assertEquals( "+testCase.getExpected() +", interpreter.interpret(\""+ testCase.getExpression()+"\"), .01 );";
    }



    @Test
    public void with() {
        Assertions.assertEquals(10.0, interpreter.interpret("(with ([x 10]) x )"), .01);
        Assertions.assertEquals(20.0, interpreter.interpret("(with ([x 10]) (+ x x) )"), .01);
        Assertions.assertEquals(30.0, interpreter.interpret("(with ([x 10]) (with ([y 20]) (+ x y) ) )"), .01);
        Assertions.assertEquals(30.0, interpreter.interpret("(with ([x 10]) (with ([y (+ x x)]) (+ x y) ) )"), .01);
        Assertions.assertEquals(60.0, interpreter.interpret("(with ([x 10]) (with ([y (+ x x)]) (with ([z (+ x y)]) (+ z (+ x y)) ) ) )"), .01);

        Assertions.assertEquals(20.0, interpreter.interpret("(with ([x 10])(with ([x x])(+ x x)))"),.01);
        Assertions.assertEquals(24.0, interpreter.interpret("(with ([x 10])(with ([x 12])(+ x x)))"),.01);
        Assertions.assertEquals(34.0, interpreter.interpret("(with ([x 10]) (+  (with ([x 12])(+ x x)) x ) )"),.01); //repeat of x being used in two different contexts
        Assertions.assertEquals(75.0, interpreter.interpret("(with ([x 15]) (+  (with ([x (+ x x)])(+ x x)) x ) )"),.01);
        Assertions.assertEquals(34.0, interpreter.interpret("(with ([x 10]) (+  (with ([x 12])(+ x x)) x ) )"),.01);
        Assertions.assertEquals(34.0, interpreter.interpret("(with ([x 10]) (+  (with ([x 12])(+ x x)) x ) )"),.01);
        assertEquals(12, interpreter.interpret("(with ([x 6])(with ([x (+ x 6)]) x ))"));

        //these were generated using get deep with
        assertEquals(1.0, interpreter.interpret("(with ([ A 1 ]) A )"), .01); // Using With to sum number from 1 to 1
        assertEquals(10.0, interpreter.interpret("(with ([ A 1 ]) (with ([ B 2 ]) (with ([ C 3 ]) (with ([ D 4 ]) ( + A ( + B ( + C D ) ) ) ) ) ) )"), .01); // Using With to sum number from 1 to 4
        assertEquals(36.0, interpreter.interpret("(with ([ A 1 ]) (with ([ B 2 ]) (with ([ C 3 ]) (with ([ D 4 ]) (with ([ E 5 ]) (with ([ F 6 ]) (with ([ G 7 ]) (with ([ H 8 ]) ( + A ( + B ( + C ( + D ( + E ( + F ( + G H ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01); // Using With to sum number from 1 to 8
        assertEquals(105.0, interpreter.interpret("(with ([ A 1 ]) (with ([ B 2 ]) (with ([ C 3 ]) (with ([ D 4 ]) (with ([ E 5 ]) (with ([ F 6 ]) (with ([ G 7 ]) (with ([ H 8 ]) (with ([ I 9 ]) (with ([ J 10 ]) (with ([ K 11 ]) (with ([ L 12 ]) (with ([ M 13 ]) (with ([ N 14 ]) ( + A ( + B ( + C ( + D ( + E ( + F ( + G ( + H ( + I ( + J ( + K ( + L ( + M N ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01); // Using With to sum number from 1 to 14
        assertEquals(253.0, interpreter.interpret("(with ([ A 1 ]) (with ([ B 2 ]) (with ([ C 3 ]) (with ([ D 4 ]) (with ([ E 5 ]) (with ([ F 6 ]) (with ([ G 7 ]) (with ([ H 8 ]) (with ([ I 9 ]) (with ([ J 10 ]) (with ([ K 11 ]) (with ([ L 12 ]) (with ([ M 13 ]) (with ([ N 14 ]) (with ([ O 15 ]) (with ([ P 16 ]) (with ([ Q 17 ]) (with ([ R 18 ]) (with ([ S 19 ]) (with ([ T 20 ]) (with ([ U 21 ]) (with ([ V 22 ]) ( + A ( + B ( + C ( + D ( + E ( + F ( + G ( + H ( + I ( + J ( + K ( + L ( + M ( + N ( + O ( + P ( + Q ( + R ( + S ( + T ( + U V ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01); // Using With to sum number from 1 to 22
        assertEquals(300.0, interpreter.interpret("(with ([ A 1 ]) (with ([ B 2 ]) (with ([ C 3 ]) (with ([ D 4 ]) (with ([ E 5 ]) (with ([ F 6 ]) (with ([ G 7 ]) (with ([ H 8 ]) (with ([ I 9 ]) (with ([ J 10 ]) (with ([ K 11 ]) (with ([ L 12 ]) (with ([ M 13 ]) (with ([ N 14 ]) (with ([ O 15 ]) (with ([ P 16 ]) (with ([ Q 17 ]) (with ([ R 18 ]) (with ([ S 19 ]) (with ([ T 20 ]) (with ([ U 21 ]) (with ([ V 22 ]) (with ([ W 23 ]) (with ([ X 24 ]) ( + A ( + B ( + C ( + D ( + E ( + F ( + G ( + H ( + I ( + J ( + K ( + L ( + M ( + N ( + O ( + P ( + Q ( + R ( + S ( + T ( + U ( + V ( + W X ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01); // Using With to sum number from 1 to 24


    }

    @Test
    public void IllegalToCalculate() {
        assertThrows(CalculationException.class, () -> {
            interpreter.interpret("(+ x x)"); //unbound identifier
        });

        assertThrows(CalculationException.class, () -> {
            interpreter.interpret("(* Dad mom)");
        });

        assertThrows(ParseException.class, () -> {  //cyclic dependency
            interpreter.interpret("(with ([x x]) x)");
        });

        assertThrows(ParseException.class, () -> {
            interpreter.interpret("(with ([x (with ([x x])(+ x x))]) x)");
        });

    }

    private String getDeepWith(char identifier, int numToDo, int value, String fullSequence) {
        if (numToDo == 0) {
            return sumOfChars(fullSequence);
        }
        return "(with ([ " + identifier + " " + Integer.toString(value) + " ]) " + getDeepWith((char) (identifier + 1), numToDo - 1, value + 1, fullSequence + identifier) + " )";
    }



    private String sumOfChars(String vars) {
        if (vars.length() == 1) {
            return String.valueOf(vars.charAt(0));
        }

        if (vars.length() == 0) {
            return "";
        }
        return "( + " + vars.charAt(0) + " " + sumOfChars(vars.substring(1)) + " )";
    }


    private String getNested(int depth, String toSum, String operation) {

        if (depth == 0) {
            return toSum;
        }
        return "( " + operation + " " + getNested(depth - 1, toSum, operation) + " " + toSum + " )";
    }


}