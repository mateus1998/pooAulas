
package aula23.negocio;

public class NegocioException extends Exception{

    public NegocioException(String string) {
        super(string);
    }

    public NegocioException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
