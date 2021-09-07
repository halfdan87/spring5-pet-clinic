package guru.springframework.sfgpetclinic.services.map;

public class MapServiceException extends RuntimeException {

    public MapServiceException() {
        super();
    }

    public MapServiceException(String message) {
        super(message);
    }

    public MapServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
