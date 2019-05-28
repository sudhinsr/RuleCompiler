
import java.util.Map;
import java.util.Stack;


public interface IJoiner {

    public void Join(String operator, Stack<Boolean> responses, Stack<String> values, Map<String, Object> user);
}
