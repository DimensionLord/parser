package calculator;

import exceptions.BadNumberException;
import exceptions.BadOperatorException;


public class Parser {
    private boolean isNumeric(char c, boolean isFirst) {
        return (c == '-' && isFirst) || (c == '.' || (c >= '0' && c <= '9'));
    }

    private String read(char[] symbols, int start, boolean shallBeNumeric) {
        String result = "";
        boolean isFirst = shallBeNumeric;
        for (int i = start; i < symbols.length; i++) {
            char sym = symbols[i];
            if (isNumeric(sym, isFirst) == shallBeNumeric) {
                result += sym;
            } else {
                break;
            }
            isFirst = false;
        }
        return result;
    }

    public double calculateLine(String line) {
        if (line.isBlank()) {
            throw new IllegalArgumentException("Вы не ввели арифметическую операцию");
        }
        char[] chars = line.toCharArray();

        String a = read(chars, 0, true);
        if (a.isBlank()) {
            throw new BadNumberException("Первое число не было передано");
        }
        double argumentA = parseDouble(a);
        String operator = read(chars, a.length(), false);
        if (operator.isBlank()){
            throw new BadOperatorException("Оператор не может быть пустым");
        }
        String b = read(chars, a.length() + operator.length(), true);
        operator = operator.trim();
        if (b.isBlank()) {
            throw new BadNumberException("Второе число не было передано");
        }

        double argumentB = parseDouble(b);
        if (argumentB > 0 && operator.length() > 1 && operator.endsWith("-")) {
            operator = operator.substring(0, operator.length() - 1);
            argumentB = -argumentB;
        }


        double result = calculate(argumentA, operator.trim(), argumentB);
        if (result < -Double.MAX_VALUE || result > Double.MAX_VALUE) {
            throw new BadNumberException("Результат за пределами возможностей калькулятора");
        }
        return result;
    }

    private double calculate(double argumentA, String operator, double argumentB) {
        switch (operator) {
            case "+", "плюс", "сложить", "прибавить", "сложи", "прибавь" -> {
                return argumentA + argumentB;
            }
            case "-", "минус", "вычесть", "уменьшить на", "вычти", "уменьшь на" -> {
                return argumentA - argumentB;
            }
            case "*", "умножить на", "перемножить с", "умножь на", "перемножь с" -> {
                return argumentA * argumentB;
            }
            case "/", ":", "поделить на", "разделить на", "подели на", "раздели на" -> {
                if (argumentB == 0) {
                    throw new ArithmeticException("Деление на 0 не поддерживается");
                }
                return argumentA / argumentB;
            }
            default -> {
                throw new BadOperatorException("Неизвестный оператор '" + operator + "'");
            }
        }
    }

    private double parseDouble(String value) {
        double argument;
        try {
            argument = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new BadNumberException("Не удалось распознать число из " + value, e);
        }
        if (argument < -Double.MAX_VALUE || argument > Double.MAX_VALUE) {
            throw new BadNumberException("Число '%s' за пределами возможностей калькулятора".formatted(value));
        }
        return argument;
    }
}
