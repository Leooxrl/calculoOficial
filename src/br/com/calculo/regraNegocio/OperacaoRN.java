package br.com.calculo.regraNegocio;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;
import br.com.calculo.bean.Variavel;

public class OperacaoRN {

    /**
     * Se os termos entre os monomios forem iguais somam-se os coeficientes
     * 
     * @param termo1
     * @param termo2
     * @return
     */
    public static Polinomio somarMonomios(Monomio termo1, Monomio termo2) {
        Polinomio soma = new Polinomio();
        Monomio monomioSoma = new Monomio();

        if (MonomioRN.ehSemelhante(termo1, termo2)) {
            BigDecimal coeficiente = termo1.getCoeficiente().add(termo2.getCoeficiente());
            monomioSoma = new Monomio(termo1);
            monomioSoma.setCoeficiente(coeficiente);

            PolinomioRN.addTermo(soma, monomioSoma);
        } else {
            PolinomioRN.addTermo(soma, termo1);
            PolinomioRN.addTermo(soma, termo2);
        }

        return soma;
    }

    public static Monomio somarMonomiosSemelhantes(Monomio termo1, Monomio termo2) {
        Monomio soma = new Monomio();

        BigDecimal coeficiente = termo1.getCoeficiente().add(termo2.getCoeficiente());
        soma = new Monomio(termo1);
        soma.setCoeficiente(coeficiente);

        return soma;
    }

    public static Monomio multiplicarMonomios(Monomio multiplicador, Monomio multiplicando) {
        BigDecimal coeficienteResultante =
                        multiplicador.getCoeficiente().multiply(multiplicando.getCoeficiente());

        Set<Variavel> variaveisResultantes = new HashSet<>();
        variaveisResultantes =
                        multiplicarVariaveis(multiplicador.getVariaveis(),
                                        multiplicando.getVariaveis());

        Monomio produto = new Monomio(coeficienteResultante, variaveisResultantes);

        // System.out.println("produto multiplicação monomio: " + produto.toString());

        return produto;
    }

    private static Set<Variavel> multiplicarVariaveis(Set<Variavel> variaveisM1,
                    Set<Variavel> variaveisM2) {
        Set<Variavel> produto = new HashSet<>();
        boolean isEncontrado = false;

        for (Variavel varM1 : variaveisM1) {

            for (Variavel varM2 : variaveisM2) {
                Variavel var;

                if (VariavelRN.ehMesmaVariavel(varM1, varM2)) {
                    var = new Variavel(varM1.getNome(), varM1.getPotencia() + varM2.getPotencia());
                    produto.add(var);
                    isEncontrado = true;
                    break;
                }
            }
            if (!isEncontrado) {
                produto.add(varM1);
            }
            isEncontrado = false;

        }

        if (produto.isEmpty()) {
            for (Variavel varM2 : variaveisM2) {
                produto.add(varM2);
            }
        }

        return produto;
    }

    public static Polinomio multiplicarPolinomios(Polinomio multiplicador, Polinomio multiplicando) {
        Polinomio produto = new Polinomio();

        // multiplica cada termo do primeiro por cada termo do segundo
        for (Monomio termoMultiplicador : multiplicador.getTermos()) {

            for (Monomio termoMultiplicando : multiplicando.getTermos()) {

                PolinomioRN.addTermo(produto, OperacaoRN.multiplicarMonomios(termoMultiplicador,
                                termoMultiplicando));
            }
        }

        // System.out.println("produto multiplicação polinômio " + produto.toString());
        return produto;
    }

}
