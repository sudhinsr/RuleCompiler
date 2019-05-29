
import java.util.Map;
import java.util.Stack;

public class OrJoiner implements IJoiner {
    
    @Override
    public void Join(String operator, Stack<Boolean> responses, Stack<String> values, Map<String, Object> user) {
        IOperation operation = Helper.GetOperator(operator);
        
        if (!responses.isEmpty()) {
            Boolean previousResponse = responses.pop();
            if (previousResponse) {
                operation.Skip(values);
                responses.push(Boolean.TRUE);
            } else {
                responses.push(previousResponse || operation.process(values, user));
            }
        } else {
            responses.push(operation.process(values, user));
        }
        
    }
    
}
