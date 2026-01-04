package frameworkException;

public class CRMException extends RuntimeException{

    public CRMException(String errorMessage){
        super(errorMessage);
    }
}
