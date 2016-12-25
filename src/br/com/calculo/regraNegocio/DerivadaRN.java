package br.com.calculo.regraNegocio;

import java.math.BigDecimal;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;
import br.com.calculo.bean.Variavel;

public class DerivadaRN {


    /**
     * Multiplica a pot�ncia pelo coeficienteo reduz um da pot�ncia.
     * 
     * @param polinomio
     * @return
     */
    public static Polinomio derivar(Polinomio funcao) {
        // para derivar preciso garantir que o polinomio tenha apenas uma única variável.
        Polinomio derivada = new Polinomio();

        // para cada monômio subtrair 1 da potência e multiplicar no coeficiente.
        // ex: f(x) = 3x² + 1
        // f'(x) = 6x
        for (Monomio m : funcao.getTermos()) {

            BigDecimal coeficiente = null;
            Variavel variavel;
            Monomio monomio;
            for (Variavel var : m.getVariaveis()) {
                coeficiente = m.getCoeficiente().multiply(new BigDecimal(var.getPotencia()));
                double potencia = var.getPotencia() - 1;
                // adiciona a variável somente se a potência for diferente de 0.
                if (potencia == 0) {
                    monomio = new Monomio(coeficiente);
                } else {
                    variavel = new Variavel(var.getNome(), potencia);
                    monomio = new Monomio(coeficiente, variavel);
                }

                PolinomioRN.addTermo(derivada, monomio);
                break;
            }

        }

        return derivada;

    }
}
