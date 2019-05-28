
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Helper {

    private static final String[] JOINERS = {Constants.AND, Constants.OR};
    private static final String[] OPERATORS = {Constants.ALLOF, Constants.BETWEEN, Constants.GREATER_THAN, Constants.GREATER_THAN_OR_EQUAL_TO,
        Constants.LESS_THAN, Constants.LESS_THAN_OR_EQUAL_TO, Constants.NONEOF, Constants.EQUAL_TO};

    public final static Set<String> JOINERS_SET = new HashSet<>(Arrays.asList(JOINERS));    
    public final static Set<String> OPERATORS_SET = new HashSet<>(Arrays.asList(OPERATORS));


    public static IOperation GetOperator(String operator) {

        IOperation operation;
        switch (operator) {
            case "<=":
                operation = new LessThanOrEqualToOperation();
                break;
            case ">=":
                operation = new GreaterThanOrEqualToOperation();
                break;
            case ">":
                operation = new GreaterThanOperation();
                break;
            case "<":
                operation = new LessThanOperation();
                break;
            case "==":
                operation = new EqualToOperation();
                break;
            case "BETWEEN":
                operation = new BetweenOperation();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operation");
        }
        return operation;
    }

    public static IJoiner GetJoiner(String joiner) {

        IJoiner joinerOperation;

        switch (joiner) {
            case "AND":
                joinerOperation = new AndJoiner();
                break;
            case "OR":
                joinerOperation = new OrJoiner();
                break;

            default:
                throw new UnsupportedOperationException("Invalid joiner " + joiner);
        }
        return joinerOperation;
    }

}
