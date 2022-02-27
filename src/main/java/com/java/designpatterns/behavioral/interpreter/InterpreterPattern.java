package com.java.designpatterns.behavioral.interpreter;

//  Interpreter pattern is used to defines a grammatical representation for a language and provides an interpreter to deal with this grammar.
class InterpreterContext{
    public String getBinaryFormat(int i){
        return Integer.toBinaryString(i);
    }

    public String getHexadecimalFormat(int i){
        return Integer.toHexString(i);
    }
}

interface Expression{
    String interpret(InterpreterContext interpreterContext);
}

class IntToBinaryExpression implements Expression{

    private int i;

    public IntToBinaryExpression(int i) {
        this.i = i;
    }

    @Override
    public String interpret(InterpreterContext interpreterContext) {
        return interpreterContext.getBinaryFormat(this.i);
    }
}

class IntToHexaExpression implements Expression{

    private int i;

    public IntToHexaExpression(int i){
        this.i = i;
    }
    @Override
    public String interpret(InterpreterContext interpreterContext) {
        return interpreterContext.getHexadecimalFormat(this.i);
    }
}

public class InterpreterPattern {
    public InterpreterContext ic;

    public InterpreterPattern(InterpreterContext ic) {
        this.ic = ic;
    }

    public String interpret(String str){
        Expression exp = null;
        if (str.contains("Hexadecimal")){
            exp=new IntToHexaExpression(Integer.parseInt(str.substring(0,str.indexOf(" "))));
        }
        else if(str.contains("Binary")){
            exp=new IntToBinaryExpression(Integer.parseInt(str.substring(0,str.indexOf(" "))));
        }
        else
            return str;

        return exp.interpret(ic);
    }

    public static void main(String[] args){
        String str1 = "28 in Binary";
        String str2 = "28 in Hexadecimal";

        InterpreterPattern ec = new InterpreterPattern(new InterpreterContext());
        System.out.println(str1+" = "+ec.interpret(str1));
        System.out.println(str2+" = "+ec.interpret(str2));
    }
}
