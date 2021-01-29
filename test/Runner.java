package test;
import Expression.*;
import Interpretation.*;
import test.*;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args){
        System.out.println("Running Tests --  an exception is thrown on fail, which will terminate the program");
        System.out.println("and print out a stack trace.  If execution reaches the end, the tests pass");
        try {



            System.out.println("\n\n -- TESTING PARSER -- \n\n");
            ParserTest pTest = new ParserTest();
            pTest.setup();
            System.out.println("Testing Helper function find end of parantheses ...");
            pTest._findEndOfParantheses();
            System.out.println("PASS");
            System.out.println("Testing helper function getItems ...");
            pTest._getItems();
            System.out.println("PASS");
            System.out.println("testing parse 1...");
            pTest.testParse();
            System.out.println("PASS");
            System.out.println("testing parse 2 ...");

            pTest.parse();
            System.out.println("PASS");
            System.out.println("Testing invalid to parse Expressions...");

            pTest.illegalToParse();
            System.out.println("PASS");
            System.out.println("Testing legal to parse, but illegal to interpret ...");

            pTest.legalToParse();
            System.out.println("PASS");
            System.out.println("Testing helper function findEndOfItem");

            pTest.test_findEndOfItem();
            System.out.println("PASS");
            System.out.println("Finished testing Parser --");

            System.out.println("\n\n -- TESTING INTERPRETER -- \n\n");
            InterpreterTest interTest = new InterpreterTest();
            interTest.setUp();
            System.out.println("Testing illegal to calculate expressions...");
            interTest.IllegalToCalculate();
            System.out.println("PASS");
            System.out.println("Testing deeply nested withs and multiply defined characters ...");
            interTest.with();
            System.out.println("PASS");
            System.out.println("Testing simple and deeply nested math");
            interTest.math();
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("ABORTED, A TEST FAILED");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;
        }

        System.out.println("ALL TESTS PASS");


    }
}
