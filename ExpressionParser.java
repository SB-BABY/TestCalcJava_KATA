import java.util.Arrays;
import java.util.List;
public class ExpressionParser {
    private static final List<String> operators = Arrays.asList("+", "-", "*", "/");
    private static final List<String> allowedArabic = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    private static final List<String> allowedRoman = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    public static ExpressionDTO parse(String input) throws CustomException {
        String[] splicedInput = input.split(" ");
        ExpressionDTO expression;

        if (splicedInput.length != 3){
            throw new CustomException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (!operators.contains(splicedInput[1])) {
            throw new CustomException("т.к. неверная операция");
        } else if ((!allowedArabic.contains(splicedInput[0]) && !allowedRoman.contains(splicedInput[0])) ||
                (!allowedArabic.contains(splicedInput[2]) && !allowedRoman.contains(splicedInput[2]))){
            throw new CustomException("т.к. первый или второй операнд не соответствуют диапазону допустимых значений");
        } else if ((allowedArabic.contains(splicedInput[0]) && allowedRoman.contains(splicedInput[2])) ||
                (allowedArabic.contains(splicedInput[2]) && allowedRoman.contains(splicedInput[0]))){
            throw new CustomException("т.к. используются одновременно разные системы счисления");
        } else if (allowedArabic.contains(splicedInput[0])){
            expression = new ExpressionDTO(allowedArabic.indexOf(splicedInput[0]) + 1,
                    allowedArabic.indexOf(splicedInput[2]) + 1, splicedInput[1], TypeOfExpression.ARABIC);
        } else {
            expression = new ExpressionDTO(allowedRoman.indexOf(splicedInput[0]) + 1,
                    allowedRoman.indexOf(splicedInput[2]) + 1, splicedInput[1], TypeOfExpression.ROMAN);
        }
        return expression;
    }
}