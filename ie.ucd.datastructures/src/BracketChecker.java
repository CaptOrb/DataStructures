public class BracketChecker<E>  {

    static String reason;

    public boolean checkParentheses(String in) {
        final String opening = "({[";
        final String closing = ")}]";

        reason = null;

        LinkedStack<Character> s = new LinkedStack<Character>();

        for (char c : in.toCharArray()) {

            // iterate through every character in the array
            // if an opening character is detected, push it to stack
            if (opening.indexOf(c) != -1) {
                s.push(c);
            } else if (closing.indexOf(c) != -1) {

                if (s.isEmpty()) {
                    reason = "Missing left delimiter";
                    return false;
                }
                if (closing.indexOf(c) != opening.indexOf(s.pop())) {

                    reason = "Matching Error";
                    return false;
                }
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {

        BracketChecker<String> bracketChecker = new BracketChecker<String>();

        String[] inputs = {
                "{[()]}",
                "{[(])}",
                "[]]()()",
                "{{[[(())]]}}",
                "c[d]",
                "a{b[c]d}e",
                "a{b(c]d}e",
                "a{b)c_",
                "][]][][[]][]][][[[",
                "(((abc))((d)))))",
        };

        for (String input : inputs) {
            boolean isBalanced = bracketChecker.checkParentheses(input);

            System.out.print(" isBalanced " + (isBalanced ? " yes !" : "no !") + input);

            if(reason != null){
                System.out.print("\tReason: " + reason);
            }
            System.out.println();
        }

    }

}
