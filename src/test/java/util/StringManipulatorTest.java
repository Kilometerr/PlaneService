package util;

import org.junit.jupiter.api.Test;
import planeServiceTraining.domain.exceptions.CantCreateInvoiceNumber;
import planeServiceTraining.util.StringManipulator;


import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringManipulatorTest {
    @Test
    void shouldConcatenate() throws CantCreateInvoiceNumber {
        //given
        char delimiter = '#';
        String first = "ala";
        String second = "kota";

        //when
        String result = StringManipulator.createInvoiceNumber(delimiter, first, second);

        //then
        assertThat(result).isNotBlank();
        assertEquals("ala#kota", result);
    }

    @Test
    void shouldConcatenateSingleString() throws CantCreateInvoiceNumber {
        //given
        char delimiter = '?';
        String first = "ala";

        //when
        String result = StringManipulator.createInvoiceNumber(delimiter, first);

        //then
        assertEquals("ala", result);
    }

    @Test
    void shouldThrowCantCreateInvoiceNumber() {
        //given
        char delimiter = '*';

        //when
        Throwable result = catchThrowable(()-> StringManipulator.createInvoiceNumber(delimiter,null));

        //then
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(CantCreateInvoiceNumber.class);
        assertThat(result.getMessage()).isNotEmpty();
    }

    @Test
    void shouldThrowCantCreateInvoiceNumberEmptyStringInArray() {
        //given
        char delimiter = '&';
        String first = "ala";
        String second = null;

        //when


        //then
        assertThrows(CantCreateInvoiceNumber.class, () -> StringManipulator.createInvoiceNumber(delimiter, first, null));
    }

    @Test
    void shouldThrowCantCreateInvoiceNumberBlankString() {
        //given
        char delimiter = '=';
        String first = "";
        //when
        //then
        assertThrows(CantCreateInvoiceNumber.class, () -> StringManipulator.createInvoiceNumber(delimiter, first));
    }

    @Test
    void shouldTrimWhiteSpaces() throws CantCreateInvoiceNumber {
        //given
        char delimiter = '5';
        String first = "   ala   ";
        String second = "kot";
        String third = "    i pies   ";
        String expected ="ala5kot5i pies";

        //when
        String result = StringManipulator.createInvoiceNumber(delimiter,first,second,third);

        //then
        assertEquals(expected,result);
    }

}