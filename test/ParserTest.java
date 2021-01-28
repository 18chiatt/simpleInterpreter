package test;
import Interpretation.Parser;

import Expression.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Expression.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Parser parser;
    @BeforeEach
    public void setup(){
        this.parser = new Parser();
    }

    @org.junit.jupiter.api.Test
    void parse() {
        String expression = "( with ([x 10])(+ x x))";
        Expression expected = new ReplacementE(new IdE("x"),new NumberE(10),new PlusE(new IdE("x"),new IdE("x")));
        Assertions.assertEquals(expected,parser.parse(expression));

        expression = "(with ([x (+ 4 9)])(+ x -13))";
        expected = new ReplacementE(new IdE("x"), new PlusE(new NumberE(4), new NumberE(9)),new PlusE( new IdE("x"),new NumberE(-13)));
        Assertions.assertEquals(expected, parser.parse(expression));

        expression = "(- (/ (* -2 -2) 2) (+ 1 1))";
        expected = new MinusE(new DivisE(new MultE(new NumberE(-2),new NumberE(-2)),new NumberE(2)) , new PlusE(new NumberE(1), new NumberE(1)));
        Assertions.assertEquals(expected,parser.parse(expression));

        expression = "(with ([x (with ([x x])(+ x x))]) x)";
        expected = new ReplacementE(new IdE("x"),new ReplacementE(new IdE("x"),new IdE("x"),new PlusE(new IdE("x"),new IdE("x"))), new IdE("x"));
        Assertions.assertEquals(expected,parser.parse(expression));

        expression = "(with ([iden 10])iden)";
        expected = new ReplacementE(new IdE("iden"),new NumberE(10),new IdE("iden"));
        assertEquals(expected,parser.parse(expression));


    }

    @Test
    public void legalToParse(){ //tests things which are valid to parse, but invalid to calcualte
        String expression = "(+ x x)";
        Expression expected = new PlusE(new IdE("x"),new IdE("x"));
        Assertions.assertEquals(expected,parser.parse(expression));

        expression = "(+ 1 (* x (+ y z)))";
        expected = new PlusE( new NumberE(1), new MultE(new IdE("x"),new PlusE(new IdE("y"), new IdE("z"))));
        Assertions.assertEquals(expected, parser.parse(expression));

        expression = "(/ 0 0)";
        expected = new DivisE(new NumberE(0), new NumberE(0));
        assertEquals(expected,parser.parse(expression));




    }

    @Test
    public void illegalToParse(){

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 , 10))"); // random extra ','
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("( x x)"); // no function
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(^ x x)"); //no valid function
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("((+ x x))"); //unnessecary nest
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(+ x )"); //only one operator
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(( + x x)"); //extra opening paranthese
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(+ x x"); //unterminated expression
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(+ x (+ y z)"); //unterminated nested parantheses
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("+ 3 3");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(with ([iden5 10])iden5)");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });



        assertThrows(ParseException.class ,() -> {
            parser.parse("(with ([with 10])(with))"); //using 'with' as ID
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(with ([* 10])(* * *))");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(* 1 2 3)"); //--- basic arithmetic fail
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(+ 1 2 3)");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ 1 2 3)");
        });
        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ 1 2 3)");
        });


        assertThrows(ParseException.class ,() -> {
            parser.parse("(+ (+ 1 2) (+ 3 4) 1)"); //extra nonsense
        });









        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ / 3 3 3)");
        });




        assertThrows(ParseException.class ,() -> {
            parser.parse("(with (x 10) x)"); // x is initially out of scope
        });


        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 10)/)"); // random extra '/'
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(/ (- 6 3) (/ 1 , 10))"); // random extra ','
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("(- (/ (* -2 -2) (2)) (+ 1 1))"); // number (2) is surrounded by extra parantheses
        });



        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });

        assertThrows(ParseException.class ,() -> {
            parser.parse("");
        });

    }

    @org.junit.jupiter.api.Test
    void _findEndOfParantheses() {

        String testString = "(abcd)";
        Assertions.assertEquals(parser._findEndOfParantheses(testString,0),5);


        String invalidString = "(()";
        assertThrows(ParseException.class, ()-> {
            parser._findEndOfParantheses(invalidString,0);
        });
        Assertions.assertEquals(parser._findEndOfParantheses(invalidString,1),2);
    }

    @Test
    void testParse() {
        String parse = "(+ 3 5)";
        Expression returned = parser.parse(parse);
        System.out.println(returned.toString());
        Expression expected = new PlusE(new NumberE(3),new NumberE(5));
        assertEquals(expected,returned);


        parse = "( + ( / 4 6 ) 5 )";
        returned = parser.parse(parse);
        expected = new PlusE(new DivisE(new NumberE(4), new NumberE(6)), new NumberE(5) );
        assertEquals(expected,returned);

        parse = "(with ([x  (+ 3 5 )]) x)";
        expected = new ReplacementE(new IdE("x"),new PlusE(new NumberE(3), new NumberE(5)) ,new IdE("x"));
        returned = parser.parse(parse);
        assertEquals(expected,returned);
    }

    @Test
    void _getItems() {
        String exampleExpression = "(a b c)";
        List<String> expected = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Assertions.assertEquals(parser._getItems(exampleExpression),expected);
        System.out.println(parser._getItems(exampleExpression).toString());

        expected = new ArrayList<>(Arrays.asList("a", "(a b c)", "c"));
        String nestedExpression = "(a   (a b c)    c)";
        Assertions.assertEquals(expected,parser._getItems(nestedExpression));

        System.out.println(parser._getItems(nestedExpression).toString());
    }

    // (+ (/ 3 5) (* 49 34))
    // (with ([x (+ (/ 3 5) (* 49 34))]) (+ x x))


    @Test
    void test_findEndOfItem() {
        String bunchOfItems = "abc deee 3.141592 (+ (3 5) (/ 3 2))";
                             //012 4457 9  16    18 - 34
        Assertions.assertEquals(parser._findEndOfItem(bunchOfItems,0),2);
        Assertions.assertEquals(7,parser._findEndOfItem(bunchOfItems,4));
        Assertions.assertEquals(parser._findEndOfItem(bunchOfItems,9),16);
        Assertions.assertEquals(parser._findEndOfItem(bunchOfItems,18),34);
    }


}