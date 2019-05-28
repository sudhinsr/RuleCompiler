
import java.util.Map;
import java.util.Stack;

public class RuleCompiler {

    public static boolean IsAllowed(String conditionalExpression,
            String featureName, Map<String, Object> user) {

        Stack<String> operators = new Stack<>();
        Stack<String> values = new Stack<>();
        Stack<String> joiners = new Stack<>();
        Stack<Boolean> responses = new Stack<>();

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
                        Boolean current_response = operation.process(values, user);
                        if (responses.isEmpty()) {
                            responses.push(current_response);
                        } else {
                            Boolean prev_response = responses.pop();
                            String joiner = joiners.pop();
                            if (joiner.equals(Constants.AND)) {
                                responses.push(prev_response && current_response);
                            } else if (joiner.equals(Constants.OR)) {
                                responses.push(prev_response || current_response);
                            }
                        }

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

        while (!joiners.isEmpty()) {
            String joiner = joiners.pop();
            String operator = operators.pop();
            IJoiner joinerOperation = Helper.GetJoiner(joiner);
            joinerOperation.Join(operator, responses, values, user);
        }

        if (!operators.isEmpty()) {
            String operator = operators.pop();
            IOperation operation = Helper.GetOperator(operator);
            responses.push(operation.process(values, user));
        }
        Boolean response = responses.pop();
        return response;
    }
}
