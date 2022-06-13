package planeServiceTraining.domain.exceptions;

public class NoPrinterFound extends RuntimeException {
    public NoPrinterFound(String message) {
        super(message);
    }
}
