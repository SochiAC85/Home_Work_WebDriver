package exceptions;

public class BrowserNotFoundExceptions extends RuntimeException{

    public BrowserNotFoundExceptions (String browser){
        super(String.format("Browser %s not supported", browser));
    }
}
