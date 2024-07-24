import java.lang.*;

public class scoreFormula {
    //constant(function)scalar.
    Double constant, scalar;
    String function;

    public scoreFormula(String s){
        String[] formula = s.split("|");
        scalar = Double.parseDouble(formula[1]);
        function = formula[1];
        constant = Double.parseDouble(formula[2]);
    }

    public Double evaluate(){
        if (function.equals("x")){ //1 x 2
            return scalar * constant;
        }
        else if (function.equals("+")){ //1 + 2
            return scalar + constant;
        }
        else if (function.equals("^")){
            return Math.pow(scalar, constant);
        }
        else return scalar/constant;
    }
}
