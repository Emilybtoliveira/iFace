package br.iface.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {

    public void hasANumber(String string) throws InvalidInputException{
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                throw new InvalidInputException("Esse campo não pode conter um numero.\n");

            }
        }
    }

    public void hasTheMinimumSize(String string, int minSize) throws InvalidInputException{
        if(string.length() < minSize){
            throw new InvalidInputException("Esse campo precisa ter, pelo menos, "+minSize+" caracteres!\n");
        }
    }

    public void hasSpecialChar(String string) throws InvalidInputException {
        Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
        Matcher m = p.matcher(string);

        if(m.find()) {
            throw new InvalidInputException("Esse campo não pode ter caracteres especiais, além de '_'.\n");
        }
    }

    public void hasSomeSpecialChar(String string) throws InvalidInputException {
        Pattern p = Pattern.compile("[^a-zA-Z0-9\\$\\@\\&\\!\\*\\%\\#]");
        Matcher m = p.matcher(string);

        if(m.find()) {
            throw new InvalidInputException("Os únicos caracteres especiais aceitáveis para esse campo " +
                    "são '$', '@', '#', '!', '*', '%', '&'.\n");
        }
    }

}
