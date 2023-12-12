import java.math.BigInteger;
import java.text.NumberFormat;

public class calculatorOperation {
    double NumA,numB;
    public static Double BiOperator(String sign,double numA,double numB) {
        if (sign == "+") {
            System.out.println("\ncalculatorOperation class BiOperator method activated");
            return numA + numB;
        } else if (sign == "-") {
            System.out.println("\ncalculatorOperation class BiOperator method activate");
            return numA - numB;//becuase numB will get as negative if sign is "-"
        } else if (sign == "*" || sign == "×") {
            System.out.println("\ncalculatorOperation class BiOperator method activate");
            return numA * numB;
        } else if (sign == "/" || sign == "÷") {
            System.out.println("\ncalculatorOperation class BiOperator method activate");
            return numA / numB;
        }





        return 0000.0;
    }

    public static Double monoOperator(String sign,double numA,double numB) {
        if (sign == "√") {

            System.out.println("\ncalculatorOperation class BiOperator method activated");
            return Math.sqrt(numA);

        } else if (sign == "p") {

            System.out.println("\ncalculatorOperation class BiOperator method activate");
            return Math.pow(numA,numB);

        }else if (sign == "%") {

            System.out.println("\ncalculatorOperation class BiOperator method activate");
            return Math.abs((numA / 100) * numB);

        }

        return 0000.0;
    }


//it usually shows at console because it oversize to fit in console
    public static BigInteger factorialBigInteger(double number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }


//it usually shows at monitor because it is  not oversize to fit in console
    public static double factorialDouble(double number) {
        double result = 1;

        for (long factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public static double triangleOperation(String sign,double numA){//all mathematical sin cos ... operation will be done here
        //all mathematical sin cos ... operation will be done here
        if (sign == "tan") {
            System.out.println("\ncalculatorOperation class BiOperator method activate" +
                    "\ntan: ");
            return Math.tan(Math.toRadians(numA));

        }else if (sign == "cot") {
            System.out.println("\ncalculatorOperation class BiOperator method activate"+
                    "\ncot: ");
            return 1 / Math.tan(Math.toRadians(numA));

        }else if (sign == "sin") {
            System.out.println("\ncalculatorOperation class BiOperator method activate"+
                    "\nsin: ");
            return Math.sin(Math.toRadians(numA));

        }else if (sign == "cos") {
            System.out.println("\ncalculatorOperation class BiOperator method activate"+
                    "\ncos: ");
            return Math.cos(Math.toRadians(numA));

        }


        return 0000.0;
    }


//it uses for changing appearance of extra digit
    //and deleting extra digit buy not roun it
    public static double round(double source, int positions) {
        long multiplier = (long) Math.pow(10, positions);
        return ((double) ((int) (source * multiplier)) / multiplier);
    }

    public static float decimalDigitsToNumber(double x, int decimaldigits){
        final NumberFormat numFormat = NumberFormat.getNumberInstance();
        numFormat.setMaximumFractionDigits(decimaldigits);
        final String resultS = numFormat.format((float) x);
        String parsable=resultS.replace(".", "");
        parsable=resultS.replace(",", ".");
        float ris=Float.parseFloat(parsable);
        return ris;
    }

    public static String decimalDigitToString(double x, int decimaldigits ){
        final NumberFormat numFormat = NumberFormat.getNumberInstance();
        numFormat.setMaximumFractionDigits(decimaldigits);
        final String resultS = numFormat.format(x);
        return resultS;
    }
    static double decimalDigits(double value, int n)
    {
        double decimal = value - ((int) value);
        System.out.println(decimal);

        double short_decimal = 0;
        for(int i = 0; i < n; i++)
        {
            /* current digit on decimal */
            decimal = decimal * 10;
            System.out.println(decimal);
            short_decimal += (Math.pow(10, n - i - 1) * (int)decimal);

            /* find further */
            decimal = decimal - (int)decimal;
        }

        return (int)value + (double)(short_decimal / Math.pow(10, n));
    }
}


