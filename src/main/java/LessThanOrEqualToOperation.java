
import java.util.Map;
import java.util.Stack;

public class LessThanOrEqualToOperation implements IOperation {

    @Override
    public Boolean process(Stack<String> values, Map<String, Object> user) {
        Integer right = Integer.parseInt(values.pop());
        Integer left = (Integer) user.get(values.pop());
        return left <= right;
    }

    @Override
    public void Skip(Stack<String> values) {
        values.pop();
        values.pop();
    }
}
