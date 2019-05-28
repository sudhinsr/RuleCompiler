
import java.util.Map;
import java.util.Stack;

public interface IOperation {
    public Boolean process(Stack<String> values, Map<String, Object> user);
    public void Skip(Stack<String> values);
}
