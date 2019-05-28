
import java.util.Map;
import java.util.Stack;

public class BetweenOperation implements IOperation {

    @Override
    public Boolean process(Stack<String> values, Map<String, Object> user) {
        values.pop();
        Integer between_right = Integer.parseInt(values.pop());
        Integer between_left = Integer.parseInt(values.pop());
        values.pop();
        Integer left = (Integer) user.get(values.pop());

        return between_left <= left && left <= between_right;
    }

    @Override
    public void Skip(Stack<String> values) {
        values.pop();
        values.pop();
        values.pop();
        values.pop();
        values.pop();
    }

}
