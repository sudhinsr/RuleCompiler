
import java.util.Map;
import java.util.Stack;

public class RuleCompiler {

    private final Stack<String> operators = new Stack<>();
    private final Stack<String> values = new Stack<>();
    private final Stack<String> joiners = new Stack<>();
    private final Stack<Boolean> responses = new Stack<>();

    public boolean IsAllowed(String conditionalExpression,
            String featureName, Map<String, Object> user) {

        char[] tokens = conditionalExpression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ' || tokens[i] == ',') {
                continue;
            }

            switch (tokens[i]) {
                case '(':
                    operators.push("(");
                    break;
                case ')':
                    while (!operators.peek().equals("(")) {
                        String operator = operators.pop();
                        
                        IOperation operation = Helper.GetOperator(operator);
                        if (responses.isEmpty()) {
                            responses.push(operation.process(values, user));
                        } else {
                            String joiner = joiners.pop();
                            IJoiner joinerOperation = Helper.GetJoiner(joiner);
                            joinerOperation.Join(operator, responses, values, user);
                        }

                        //if next operator is close bracket backtrack and evauvate all the operation until opning bracket
                        if (!operators.peek().equals("(")) {
                            String joiner = joiners.pop();
                            operator = operators.pop();
                            IJoiner joinOperation = Helper.GetJoiner(joiner);
                            joinOperation.Join(operator, responses, values, user);
                        }
                    }
                    operators.pop();
                    break;
                default:
                    //building string with input token here sspace is the delimitte
                    StringBuilder sbuf = new StringBuilder();
                    while (i < tokens.length && tokens[i] != ' ') {
                        sbuf.append(tokens[i++]);
                    }
                    String s = sbuf.toString();
                    if (Helper.JOINERS_SET.contains(s)) {
                        joiners.push(s);
                    } else if (Helper.OPERATORS_SET.contains(s)) {
                        operators.push(s);
                    } else {
                        values.push(s);
                    }
                    break;
            }
        }

        //for rules without brackets
        while (!joiners.isEmpty()) {
            String joiner = joiners.pop();
            String operator = operators.pop();
            IJoiner joinerOperation = Helper.GetJoiner(joiner);
            joinerOperation.Join(operator, responses, values, user);
        }

        //for the single operator rules
        if (!operators.isEmpty()) {
            String operator = operators.pop();
            IOperation operation = Helper.GetOperator(operator);
            responses.push(operation.process(values, user));
        }
        Boolean response = responses.pop();
        return response;
    }
}
