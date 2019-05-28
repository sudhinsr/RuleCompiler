
import java.util.Map;
import java.util.Stack;

public class EqualToOperation implements IOperation {

    @Override
    public Boolean process(Stack<String> values, Map<String, Object> user) {
        String right = values.pop();
        Object keyValue = user.get(values.pop());
        if (keyValue instanceof String) {
            right = right.substring(1, right.length() - 1);
        }
        return right.equals(keyValue);
    }

    @Override
    public void Skip(Stack<String> values) {
        values.pop();
        values.pop();
    }

}
