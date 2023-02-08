import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalanceTheBrackets {
    public static void main(String[] args) {
        String input = "(3*(3+1)-3*2+1=19";
        //String input = "(8+2)*(2-1=10";
        //(3 * (3 + 1) - 3  * 2 + 1 = 19
        //sym (*(+
        //cal 3,3,1
        input = balanceTheEq(input);
        System.out.println(input);
    }
    public  static String  balanceTheEq(String input) {
        //TODO Add your implementation here
        int t = 1;
        int count = 0;
        int p_count = 0;
        int flag = 1;
        List<Integer> poss = new ArrayList<Integer>();
        int result = 0;
        for(int i = input.length()-1; input.charAt(i) != '='; i--){
            int temp = Character.getNumericValue(input.charAt(i));
            temp = temp*t;
            result = temp + result;
            t = t*10;
        }
        int i = 0;
        do {
            if(count >= 2  && p_count==1 && flag == 1 && input.charAt(i) != ')' && input.charAt(i) != '-'
                    && input.charAt(i) != '-'){
                poss.add(i);

            }
            if(input.charAt(i) == '('){
                p_count = p_count + 1;
            }
            if(input.charAt(i) == ')') {
                p_count = p_count - 1;
                if(p_count == 0){
                    count = 0;
                }
            }
            if(Character.isDigit(input.charAt(i)) && p_count > 0){
                count = count + 1;
                flag = 1;
            }
            if(IsOperator(input.charAt(i))){
                flag = 0;
            }
            i++;
        }while(input.charAt(i-1) != '=');
        if (poss.size() == 1) {
            int temp = poss.get(0);
            //input.charAt(temp) = ')';
            input = input.substring(0, temp) + ')' + input.substring(temp);
            return input;
        }
        else{
            for(Integer k:poss){
                Stack<Character> stck = new Stack<Character>();
                String input1 = input.substring(0, k) + ')' + input.substring(k);
                input = input1;
                return input;
            }
        }
        return input;
    }

    static boolean IsOperator(char ch){
        if(ch == '*' ||ch == '/' ||ch == '-' || ch == '+' ){
            return true;
        }
        return false;
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
