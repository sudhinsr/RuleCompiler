
import java.util.Map;
import java.util.Stack;

public class AndJoiner implements IJoiner {

    @Override
    public void Join(String operator, Stack<Boolean> responses, Stack<String> values, Map<String, Object> user) {
        IOperation operation = Helper.GetOperator(operator);
        if (!responses.isEmpty()) {
            Boolean operationResponse = responses.pop();

            if (operationResponse) {

                responses.push(operationResponse && operation.process(values, user));
            } else {
                operation.Skip(values);
                responses.push(Boolean.FALSE);
            }
        } else {
            responses.push(operation.process(values, user));
        }
    }

}
