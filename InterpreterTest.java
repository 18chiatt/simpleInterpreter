import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    private Interpreter interpreter;
    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
    }

    @Test
    void math() {
        // simple arithmetic
        assertEquals(8.0, interpreter.interpret("(+ 3 5)"), .01);
        assertEquals(12.0, interpreter.interpret("(* 6 2)"), .01);
        assertEquals(70.0, interpreter.interpret("(/ 140 2)"), .01);
        assertEquals(0, interpreter.interpret("(- 7 7)"), .01);

        assertEquals( 10.0, interpreter.interpret("(+ (+ 3 5) 2)"),.01);
        assertEquals( 20.0, interpreter.interpret("(* (+ 0 1) 20)"),.01);
        assertEquals( 30.0, interpreter.interpret("(/ (- 6 3) (/ 1 10))"),.01);
        assertEquals( 1, interpreter.interpret("(- (/ (* -2 -2) (2)) (+ 1 1))"),.01);
        assertEquals(0 , interpreter.interpret("(with ([x (+ 4 9)])(+ x -13))"),.01);
        //assertEquals( , interpreter.interpret("()"),.01);
        //assertEquals( , interpreter.interpret(),.01);
        //assertEquals( , interpreter.interpret(),.01);




    }
    @Test
    public void with(){
        assertEquals(10.0, interpreter.interpret("(with ([x 10]) x )"), .01);
        assertEquals(20.0, interpreter.interpret("(with ([x 10]) (+ x x) )"), .01);
        assertEquals(30.0, interpreter.interpret("(with ([x 10]) (with ([y 20]) (+ x y) ) )"), .01);
        assertEquals(30.0, interpreter.interpret("(with ([x 10]) (with ([y (+ x x)]) (+ x y) ) )"), .01);
        assertEquals(60.0, interpreter.interpret("(with ([x 10]) (with ([y (+ x x)]) (with ([z (+ x y)]) (+ z (+ x y)) ) ) )"), .01);
    }

    @Test
    public void illegalExpressions(){

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret(""); // x is initially out of scope
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(* 1 2 3)"); //--- basic arithmetic fail
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(+ 1 2 3)");
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ 1 2 3)");
        });
        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ 1 2 3)");
        });


        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(+ (+ 1 2) (+ 3 4) 1)"); //extra nonsense
        });

        assertThrows(ParseException.class ,() -> {  //cyclic dependency
            interpreter.interpret("(with ([x x]) x)");
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(with ([x (with ([x x])(+ x x))]) x)");
        });



        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(* D D)");
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ / 3 3 3)");
        });




        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(with (x 10) x)"); // x is initially out of scope
        });


        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            interpreter.interpret("(/ (- 6 3) (/ 1 , 10))"); // random extra ','
        });



    }
}