
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String conditionalExpression = "( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000 )";

        //AND age > 26 AND gender == \"Male\" 
        //"age BETWEEN [ 24 , 28 ]";
        String featureName = "Exclusive access to certain set of categories";
        Map<String, Object> userAttributes = new HashMap<>();
        userAttributes.put("age", 26);
        userAttributes.put("gender", "Male");
        userAttributes.put("past_order_amount", 20000);

        Boolean out = RuleCompiler.IsAllowed(conditionalExpression, featureName, userAttributes);

        if (out) {
            System.out.println("Access");
        } else {
            System.out.println("Dont have access");
        }
    }

}
