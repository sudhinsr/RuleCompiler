
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String exampleFeatureName = "Exclusive access to certain set of categories";
        String exampleExpression = "( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000 )";

        String betweenFeatureName = "Age limit";
        String betweenExpression = "age BETWEEN [ 24 , 28 ]";

        String prevoiusOrderFeatureName = "prevoiusly orderd";
        String prevoiusOrderExpressson = "previous_product_ids ALLOF [ 5 , 1 , 6 ]";

        Map<String, Object> userAttributes = new HashMap<>();
        userAttributes.put("age", 26);
        userAttributes.put("gender", "Male");
        userAttributes.put("past_order_amount", 20000);
        Integer[] productIds = {5, 6, 1};
        userAttributes.put("previous_product_ids", productIds);

        Boolean exampleOut = new RuleCompiler().IsAllowed(exampleExpression, exampleFeatureName, userAttributes);

        Boolean betweenOut = new RuleCompiler().IsAllowed(betweenExpression, betweenFeatureName, userAttributes);    
        
        Boolean prevoiusOrderOut = new RuleCompiler().IsAllowed(prevoiusOrderExpressson, prevoiusOrderFeatureName, userAttributes);


        if (exampleOut) {
            System.out.println(exampleFeatureName);
        }

        if (betweenOut) {
            System.out.println(betweenFeatureName);
        }
        
         if (prevoiusOrderOut) {
            System.out.println(prevoiusOrderFeatureName);
        }
    }

}
