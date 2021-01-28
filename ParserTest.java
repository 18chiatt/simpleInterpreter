import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Parser parser;
    @BeforeEach
    public void setup(){
        this.parser = new Parser();
    }

    @org.junit.jupiter.api.Test
    void parse() {
    }

    @org.junit.jupiter.api.Test
    void _findEndOfParantheses() {

        String testString = "(abcd)";
        assertEquals(parser._findEndOfParantheses(testString,0),5);


        String invalidString = "(()";
        assertThrows(ParseException.class, ()-> {
            parser._findEndOfParantheses(invalidString,0);
        });
        assertEquals(parser._findEndOfParantheses(invalidString,1),2);
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
        assertEquals(parser._getItems(exampleExpression),expected);
        System.out.println(parser._getItems(exampleExpression).toString());

        expected = new ArrayList<>(Arrays.asList("a", "(a b c)", "c"));
        String nestedExpression = "(a   (a b c)    c)";
        assertEquals(expected,parser._getItems(nestedExpression));

        System.out.println(parser._getItems(nestedExpression).toString());
    }

    // (+ (/ 3 5) (* 49 34))
    // (with ([x (+ (/ 3 5) (* 49 34))]) (+ x x))


    @Test
    void test_findEndOfItem() {
        String bunchOfItems = "abc deee 3.141592 (+ (3 5) (/ 3 2))";
                             //012 4457 9  16    18 - 34
        assertEquals(parser._findEndOfItem(bunchOfItems,0),2);
        assertEquals(7,parser._findEndOfItem(bunchOfItems,4));
        assertEquals(parser._findEndOfItem(bunchOfItems,9),16);
        assertEquals(parser._findEndOfItem(bunchOfItems,18),34);
    }


}