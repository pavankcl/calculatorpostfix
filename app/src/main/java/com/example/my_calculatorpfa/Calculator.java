package com.example.my_calculatorpfa;

import android.util.Log;

import java.util.*;
public class Calculator {
    private String expression;
    //constructor
    public Calculator(String expression){
        this.expression = expression;
        regular_expression(expression);
    }
    public  int regular_expression(String expression) {
        char[]  token = expression.toCharArray();
        Stack<Integer> st1 = new Stack<>(); //operands(numbers) stack
        Stack<Character> st2 = new Stack<>(); //operators stack

        for(int i=0;i<token.length; i++){
            if(token[i]== ' '|| token[i]==')' || token[i]==')' )
                continue;

            //pushing values into the operands stack(st1)
            if(token[i]>='0' && token[i]<='9'){
                StringBuffer s = new StringBuffer();
                while(i<token.length && token[i]>='0' && token[i]<='9')
                    s.append(token[i++]);
                st1.push(Integer.parseInt(s.toString()));
                i--;
            }
            //checking the precedence level
            //calculating the value and push into the value(operand stack)
            else if (token[i] == '+' || token[i] == '-' || token[i] == '*' ||  token[i] == '/') {

                while (!st2.empty() && precedenceLevel(token[i], st2.peek()))
                    st1.push(operator(st2.pop(), st1.pop(), st1.pop()));


                st2.push(token[i]);
            }
        }
        while (!st2.empty())
            st1.push(operator(st2.pop(), st1.pop(), st1.pop()));


        return st1.pop();

    }
    //to check the precedence level of expression
    public static boolean precedenceLevel(char oper1, char oper2)
    {

        if ((oper1 == '*' || oper1 == '/') && (oper2 == '+' || oper2 == '-'))
            return false;
        else
            return true;
    }
    //apply operator to the operands
    public static int operator(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }


}
