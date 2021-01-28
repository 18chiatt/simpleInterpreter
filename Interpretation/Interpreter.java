package Interpretation;

import Expression.Expression;

public class Interpreter {
    private Parser parser;
    public Interpreter(){
        this.parser = new Parser();
    }

    public double interpret(String expression){
        Expression theExpression = parser.parse(expression);
        theExpression.doReplacements();

        return theExpression.getVal();

    }

}
