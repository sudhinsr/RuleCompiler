
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class AllOfOperation implements IOperation {

    @Override
    public Boolean process(Stack<String> values, Map<String, Object> user) {
        ArrayList<Object> arrayList = new ArrayList<>();
        values.pop();
        while (!values.peek().equals("[")) {
            arrayList.add(values.pop());
        }
        values.pop();

        Object userValue = user.get(values.pop());

        if (userValue instanceof String[]) {
            String[] userStrings = (String[]) userValue;
            for (Object element : arrayList) {
                String elementString = (String) element;
                elementString = elementString.substring(1, elementString.length() - 1);
                if (!Arrays.asList(userStrings).contains(elementString)) {
                    return Boolean.FALSE;
                }
            }
        } else if (userValue instanceof Integer[]) {
            Integer[] userIntegers = (Integer[]) userValue;
            for (Object element : arrayList) {
                Integer number = Integer.parseInt((String)element);
                if (!Arrays.asList(userIntegers).contains(number)) {
                    return Boolean.FALSE;
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return Boolean.TRUE;
    }

    @Override
    public void Skip(Stack<String> values) {
        values.pop();
        while (!values.peek().equals("[")) {
            values.pop();
        }
        values.pop();
        values.pop();
    }

}
