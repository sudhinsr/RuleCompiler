
import java.util.Map;
import java.util.Stack;

public class OrJoiner implements IJoiner {

    @Override
    public void Join(String operator, Stack<Boolean> responses, Stack<String> values, Map<String, Object> user) {
        Boolean operationResponse = responses.pop();
        IOperation operation = Helper.GetOperator(operator);
        if (operationResponse) {
            operation.Skip(values);
            responses.push(Boolean.TRUE);
        } else {
            responses.push(operationResponse || operation.process(values, user));
        }
    }

}
