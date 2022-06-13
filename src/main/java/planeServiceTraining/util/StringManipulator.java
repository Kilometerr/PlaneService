package planeServiceTraining.util;

import org.apache.commons.lang3.StringUtils;
import planeServiceTraining.domain.exceptions.CantCreateInvoiceNumber;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class StringManipulator {

    public static String createInvoiceNumber(char delimiter, String... strings) throws CantCreateInvoiceNumber {
        if (validateArray(strings)) {
            return Arrays.asList(strings).stream()
                    .map(string->string.trim())
                    .collect(Collectors.joining(String.valueOf(delimiter)));
        }
        throw new CantCreateInvoiceNumber("Can't create invoice number");

    }

    private static boolean validateArray(String[] strings) {
        if (isNull(strings))  return false;
            for (String s : strings) {
                if (StringUtils.isBlank(s)) return false;

        }
        return true;
    }
}
