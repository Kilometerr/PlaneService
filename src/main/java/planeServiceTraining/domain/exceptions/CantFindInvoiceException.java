package planeServiceTraining.domain.exceptions;

public class CantFindInvoiceException extends RuntimeException {
    public CantFindInvoiceException(String message) {
        super(message);
    }
}
