package FunctionalJava;

public class LambdaExpression {

    public interface MathLambda{
        int operation(int a,int b);
    }

    public interface StringLambda{
        void getMessage(String message);
    }

    public interface StringCompare{
        void compareString(String s1, String s2);
    }

    public static void main(String[] args) {

        MathLambda addition = (int a, int b) -> a + b; // Integer::sum
        MathLambda subtraction = (int a, int b) -> a - b;

        System.out.println(addition.operation(3,5));
        System.out.println(subtraction.operation(5,3));

        StringLambda hello = message -> System.out.println("Hello, "+message);
        StringLambda goodbye = msg -> System.out.println("Bye, "+ msg);

        hello.getMessage("Accuser.");
        goodbye.getMessage("Chris.");

        StringCompare compare = (String first, String second)->
        {
            if(first.length()>second.length()) System.out.println(first+" is longer.");
            else if (first.length()<second.length()) System.out.println(second+" is longer.");
            else System.out.println("Same length.");
        };

        compare.compareString("Chris","Github");
        compare.compareString("McDonald","KFC");
    }
}
