import java.lang.*;

public class scoreFormula {
    //constant(function)scalar.
    Double constant, scalar;
    String function;
    //format constant(function)scalar. 
    //- e.g. 1x1 (means 1 point * (1 x the scalar))
    //- e.g. 1^1 (means 1 point to the power of (1 x the scalar))
    //- e.g. 2+3 (means 2 points plus 3 x the scalar)
    public scoreFormula(String s){
        String[] formula = s.split("|");
        scalar = Double.parseDouble(formula[1].trim());
        function = formula[1].trim();
        constant = Double.parseDouble(formula[2].trim());
    }

    public String toString(){
        return Double.toString(constant) + "|" + function + "|" + Double.toString(scalar);
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
