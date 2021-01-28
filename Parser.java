import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class Parser {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public Expression parse(String toParse){
        if(toParse == null ||  toParse.length() == 0){
            throw new ParseException("Null or zero length input");
        }

        List<String> strings = _getItems(toParse);
        String firstItem = strings.get(0);

        if(firstItem.equals("+")){
            return _makePlus(strings);
        }

        if(firstItem.equals("-")){
            return _makeMinus(strings);
        }
        if(firstItem.equals("*")){
            return _makeMultiply(strings);
        }
        if(firstItem.equals("/")){
            return _makeDivision(strings);
        }
        if(firstItem.equals("with")){
            return _makeWith(strings);
        }
        if(pattern.matcher(firstItem).matches()){
            return _makeNum(strings);
        }
        System.out.println("MAKING ID" + strings.get(0));
        return _makeID(strings);


    }


    private Expression _makeID(List<String> strings) {
        if(strings.size() != 1){
            throw new ParseException("incorrect arguments for ID creation operation, expected 1, got " + Integer.toString(strings.size()));
        }
        return new IdE(strings.get(0));
    }

    private Expression _makeNum(List<String> strings) {
        if(strings.size() != 1){
            throw new ParseException("incorrect arguments for number creation operation, expected 1, got " + Integer.toString(strings.size()));
        }
        return new NumberE(Integer.parseInt(strings.get(0)));
    }

    private Expression _makeWith(List<String> strings) {
        if(strings.size() != 3){
            throw new ParseException("incorrect arguments for with operation, expected 3, got " + Integer.toString(strings.size()));
        }



        String middleBit = strings.get(1);
        validateForm(middleBit, "([", "])");

        List<String> idAndReplacement = getWithElements(middleBit);
        IdE idToReplace = new IdE(idAndReplacement.get(0));
        System.out.println(idAndReplacement.get(1));
        Expression toReplaceWith = parse(idAndReplacement.get(1));
        Expression scope = parse(strings.get(2));

        return new ReplacementE(idToReplace,toReplaceWith,scope);
    }

    private void validateForm(String input, String firstFew, String lastFew) {

        int indexInString = 0;
        for(int i =0; i< firstFew.length(); i++){
            while(input.charAt(indexInString)!= firstFew.charAt(i)){
                if(input.charAt(indexInString) != ' '){
                    throw new ParseException("Incorrect ordering");
                }
                indexInString ++;
                if(indexInString == firstFew.length()){
                    throw new ParseException("Incorrect ordering");
                }
            }
        }
        indexInString = input.length() -1;
        for(int i=0; i< lastFew.length(); i++){
            while(input.charAt(indexInString)!= lastFew.charAt(i)){
                if(input.charAt(indexInString) != ' '){
                    throw new ParseException("Incorrect ordering");
                }
                if(indexInString == 0){
                    throw new ParseException("Incorrect ordering");
                }
                indexInString--;
            }
        }
    }

    private List<String> getWithElements(String middleBit) {
        int curr = 0;

        while(middleBit.charAt(curr) == '(' ||middleBit.charAt(curr) == '[' || middleBit.charAt(curr) == ' '  ){
            curr +=1;
        }
        int startOfID = curr;
        curr +=1;

        while(middleBit.charAt(curr)!= ' '){
            curr +=1;
        }
        String ID = middleBit.substring(startOfID,curr);
        while(middleBit.charAt(curr)== ' '){
            curr ++;
        }
        String replacementExpression = middleBit.substring(curr, _findEndOfItem(middleBit,curr) +1 );
        return Arrays.asList(ID, replacementExpression);

    }



    private Expression _makeMultiply(List<String> strings) {
        if(strings.size() != 3){
            throw new ParseException("incorrect arguments for multiplication operation, expected 3, got " + Integer.toString(strings.size()));
        }
        Expression lhs = parse(strings.get(1));
        Expression rhs = parse(strings.get(2));
        return new MultE(lhs, rhs);
    }
    private Expression _makeDivision(List<String> strings) {
        if(strings.size() != 3){
            throw new ParseException("incorrect arguments for multiplication operation, expected 3, got " + Integer.toString(strings.size()));
        }
        Expression lhs = parse(strings.get(1));
        Expression rhs = parse(strings.get(2));
        return new DivisE(lhs, rhs);
    }


    private Expression _makeMinus(List<String> strings) {
        if(strings.size() != 3){
            throw new ParseException("incorrect arguments for subtraction operation, expected 3, got " + Integer.toString(strings.size()));
        }
        Expression lhs = parse(strings.get(1));
        Expression rhs = parse(strings.get(2));
        return new MinusE(lhs, rhs);

    }

    private Expression _makePlus(List<String> strings) {
        if(strings.size() != 3){
            throw new ParseException("incorrect arguments for addition operation, expected 3, got " + Integer.toString(strings.size()));
        }
        Expression lhs = parse(strings.get(1));
        Expression rhs = parse(strings.get(2));
        return new PlusE(lhs, rhs);
    }


    public List<String> _getItems(String fullExpression){
        List<String> strings = new ArrayList<>();

        if(fullExpression.charAt(0)=='('){

            int startIndex = _findStartOfItem(fullExpression,1);
            while(startIndex != -1){
                int endIndex = _findEndOfItem( fullExpression, startIndex);
                String currItem = fullExpression.substring(startIndex,endIndex+1);
                strings.add(currItem);
                startIndex = _findStartOfItem(fullExpression,endIndex+1);
            }



        } else {
            int endOfThing = _findEndOfItem(fullExpression,0);
            strings.add(fullExpression.substring(0,endOfThing+1));

        }

        return strings;
    }

    public int _findEndOfParantheses(String fullExpression, int start){
        int depth =1;
        int currIndex = start;

        while(depth!= 0){
            currIndex +=1;
            if(currIndex == fullExpression.length()){
                throw new ParseException("Unable to find matching parantheses");
            }
            char currChar = fullExpression.charAt(currIndex);
            if(currChar == '('){
                depth +=1;
            }
            if(currChar == ')'){
                depth -=1;
            }
        }
        return currIndex;
    }

    public int _findEndOfItem(String fullExpression, int start){
        if(fullExpression.charAt(start)=='('){
            return _findEndOfParantheses(fullExpression,start);
        }
        return _findEndOfLiteral( fullExpression, start);
    }

    public int _findEndOfLiteral(String fullExpression, int start){



        for(int i=start; i< fullExpression.length(); i++){
            char currChar = fullExpression.charAt(i);
            if(currChar == ' ' || currChar == ')' || currChar == '(' || currChar == ']'){
                return i-1;
            }
        }
        return fullExpression.length()-1;
    }

    public int _findStartOfItem(String fullExpression, int start){
        while(fullExpression.charAt(start)== ' ' || fullExpression.charAt(start) == ')' || fullExpression.charAt(start) == ']'){
            start++;
            if(start == fullExpression.length()){
                return -1;
            }
        }
        return start;
    }
}
