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

        //depth 100 expressions

        assertEquals( -295.06663166246904, interpreter.interpret("( + -39 ( * 167 ( / 161 ( + -105 ( / 61 ( * -92 ( + 66 ( * -124 ( - -27 ( - 199 ( - -146 ( / -171 ( + -144 ( + 37 ( * 94 ( + 36 ( - 37 ( + 144 ( * -14 ( * -92 ( + 68 ( + -24 ( - -80 ( + -120 ( - -90 ( - 173 ( - 91 ( - 140 ( * 144 ( - 86 ( * -97 ( * 179 ( * -197 ( * -56 ( + -29 ( - 2 ( * -122 ( * -163 ( * 184 ( + -161 ( + -75 ( * 192 ( - 40 ( + 195 ( - 152 ( - -150 ( + -164 ( + -22 ( * -58 ( * -89 ( * -74 ( + 167 ( + 132 ( - -88 ( - -120 ( * 118 ( * 21 ( * 123 ( + -183 ( * 86 ( - 39 ( * 197 ( - -86 ( + 1 ( * -126 ( * 146 ( * 177 ( * -53 ( - -105 ( + 125 ( - 7 ( + -44 ( - -29 ( + 101 ( * -189 ( * 186 ( + 176 ( + 131 ( * -77 ( - -171 ( + -175 ( + -20 ( * -48 ( + 47 ( - 66 ( - 26 ( - -157 ( * 12 ( * -106 ( + -77 ( + 66 ( + 60 ( + 177 ( - 45 ( - 16 ( * -86 ( + 182 ( + 163 ( - 15 ( + 157 105 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -2.7040990975128834E-53, interpreter.interpret("( * -57 ( / 32 ( + -23 ( + 34 ( - 166 ( / 100 ( / -142 ( - -19 ( * 28 ( * 118 ( - 184 ( + -116 ( + -15 ( + 183 ( + 10 ( + -122 ( + 191 ( * 32 ( * 46 ( - 105 ( + -128 ( * 134 ( - -104 ( + -79 ( - -101 ( + 92 ( * 1 ( * -119 ( * 11 ( + -177 ( * 167 ( + -132 ( + -37 ( - 167 ( - 38 ( + 195 ( * -10 ( * -133 ( * 41 ( + -128 ( + 46 ( * 199 ( + 174 ( - -187 ( + 100 ( - -28 ( + -84 ( - -42 ( - 187 ( + 15 ( * 34 ( + 16 ( * 48 ( * -103 ( + -76 ( - 178 ( * 48 ( - -90 ( + -98 ( + -133 ( * 38 ( * 100 ( * -5 ( - -185 ( + -162 ( + 58 ( + 141 ( + -111 ( - 57 ( - -2 ( * -147 ( - 200 ( * -143 ( + -197 ( * 13 ( * -119 ( + 104 ( - 130 ( * 184 ( * -59 ( + 104 ( - -81 ( - 50 ( * -195 ( - 156 ( + -4 ( - 22 ( - 159 ( + -141 ( - 164 ( - -124 ( * -41 ( * 94 ( + -158 ( - -55 ( * 118 ( - 109 ( + -169 ( * 121 ( - 133 24 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( 92457.99999997631, interpreter.interpret("( + -84 ( + 5 ( + 185 ( * 104 ( * -8 ( - 27 ( + -159 ( + 178 ( + 119 ( / 2 ( - 120 ( + 112 ( - 19 ( / 20 ( * 126 ( / -43 ( - 81 ( - -121 ( * -34 ( + 27 ( + -157 ( * 33 ( - -193 ( * 118 ( * 42 ( - 33 ( * 136 ( - 52 ( * 197 ( - 128 ( * 0 ( - -33 ( * -58 ( - 2 ( + -25 ( - -83 ( + -72 ( + -58 ( - 46 ( + 55 ( * 64 ( + 5 ( + 82 ( + 115 ( * -180 ( - -95 ( + -166 ( - 142 ( * 108 ( - -43 ( - 27 ( * 112 ( * 15 ( * -161 ( - -28 ( * -62 ( + 110 ( + 57 ( * 176 ( * 168 ( - -122 ( + 112 ( * -48 ( + 191 ( - -161 ( - -101 ( - -160 ( + -131 ( - 166 ( - 154 ( - -37 ( + -21 ( * 55 ( * -133 ( * -45 ( * -1 ( + -173 ( + 11 ( - 77 ( * -178 ( - -4 ( - 188 ( - 43 ( - 32 ( + 171 ( - -11 ( + -69 ( + -17 ( - 167 ( + -129 ( + 107 ( * -11 ( + -59 ( + -171 ( * -89 ( * 168 ( - -170 ( + -39 ( + -14 ( * -159 149 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -118296.0, interpreter.interpret("( * -106 ( * 12 ( - 14 ( + -79 ( / 61 ( / -89 ( / 180 ( + -65 ( - -58 ( * 34 ( * -1 ( * 100 ( - 47 ( * -157 ( * -2 ( + 132 ( * 45 ( - -32 ( + -160 ( + -12 ( - -140 ( - 100 ( + 118 ( + -164 ( - -194 ( * -69 ( * -85 ( - -55 ( - -116 ( + 64 ( + 154 ( - -99 ( * -189 ( - 116 ( + -54 ( - -100 ( * -39 ( + 186 ( - 3 ( + -128 ( - 47 ( - 171 ( * 180 ( - -140 ( + 50 ( - -74 ( + 128 ( * 45 ( - 80 ( - 40 ( - -29 ( - 69 ( * -48 ( - 39 ( + 25 ( - -189 ( + -4 ( - 144 ( - 148 ( - 166 ( * 28 ( * 34 ( * 108 ( - -72 ( * 179 ( + -165 ( * -111 ( + 170 ( + 14 ( * 54 ( * 179 ( + -158 ( * 13 ( * 89 ( + 20 ( * -167 ( * -102 ( + -26 ( - 74 ( - -183 ( - 154 ( + -135 ( - -127 ( * -113 ( * -193 ( - -89 ( + 188 ( - -10 ( + -50 ( * -117 ( + 141 ( * 188 ( * 134 ( + 120 ( - -64 ( + -15 ( - 114 ( * -13 ( + 1 ( - -78 18 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );
        assertEquals( -0.35769230769206073, interpreter.interpret("( / -186 ( + 33 ( - 58 ( + -170 ( - -60 ( - 199 ( * 1 ( * -122 ( / -189 ( * -61 ( - 22 ( - 83 ( * 133 ( * -70 ( + 26 ( * 142 ( - -61 ( * 36 ( + 188 ( + 55 ( * -143 ( + -153 ( * -102 ( * -25 ( / -115 ( * -78 ( - 14 ( * -137 ( + -133 ( - -61 ( * 55 ( * 31 ( * -156 ( * -108 ( * -24 ( * -119 ( + -28 ( + 101 ( - 177 ( + 142 ( + -50 ( - 54 ( + -80 ( + -43 ( + -39 ( + 70 ( * -50 ( + 155 ( * 188 ( + 62 ( + 38 ( * -175 ( * 135 ( + -157 ( - 93 ( - 129 ( - -114 ( + -135 ( - 83 ( - -70 ( - 92 ( + 183 ( - 168 ( * -96 ( + -26 ( - -192 ( - -167 ( - -106 ( - -172 ( * -193 ( * 103 ( - 66 ( + 110 ( + 110 ( * 116 ( * 191 ( * 35 ( * 184 ( + 133 ( - 54 ( + 59 ( - -100 ( + -185 ( - -179 ( + -68 ( - 13 ( * 73 ( - -157 ( + 153 ( * -112 ( - -118 ( - -120 ( - -41 ( - 181 ( + -79 ( + -35 ( - -11 ( + 16 ( - 124 ( - 60 187 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )"), .01 );






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

    private TestCase generateMathTest(int depth, int remainingDivisions){


        int rand = Math.abs(random.nextInt())% 4;
        int number = (random.nextInt() % 200) +1; //don't allow this to create divide by zero
        char[] operations = {'+','-','/','*'};


        if(depth == 0){
            return new TestCase(number,Integer.toString(number));
        }


        char operation = operations[rand];

        if(remainingDivisions == 0){
            while(operation == '/'){
                operation = operations[Math.abs(random.nextInt())% 4];
            }
        }
        if(operation == '/'){
            remainingDivisions--;
        }
        TestCase deeper = generateMathTest(depth-1,remainingDivisions);
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
        assertEquals(12, interpreter.interpret("(with ([x 6])(with ([x (+ x 6)]) x ))")); // ------------ x defined in terms of itself

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