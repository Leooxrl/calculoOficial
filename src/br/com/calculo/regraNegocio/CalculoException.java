package br.com.calculo.regraNegocio;

public class CalculoException extends Exception {

    private static final long serialVersionUID = 1L;

    public CalculoException() {}

    public CalculoException(String mensagem) {
        super(mensagem);
    }

}
