package br.com.calculo.regraNegocio;

import java.math.BigDecimal;

import br.com.calculo.bean.Polinomio;
import br.com.calculo.util.Utilitaria;

/**
 * Soluciona equações de sugundo grau.
 * 
 * Fórmula: Δ = b²-4ac x = (-b+-(Δ^(1/2)))/2a
 * 
 * @author leonardo.leandro
 *
 */
public class BhaskaraRN {


    /**
     * <p>
     * A função terá três termos. O termo A, B e C.
     * </p>
     * <ul>
     * <li>O primeiro termo será quadrático</li>
     * <li>O segundo termo é te primeiro grau</li>
     * <li>O terceiro termo é apenas numérico, sem x</li>
     * 
     * @param funcao
     * @return
     * @throws CalculoException
     */
    public static BigDecimal[] resolver(Polinomio funcao) throws CalculoException {

        BigDecimal a = funcao.getTermos().get(0).getCoeficiente();
        BigDecimal b = funcao.getTermos().get(1).getCoeficiente();
        BigDecimal c = funcao.getTermos().get(2).getCoeficiente();

        BigDecimal delta = b.multiply(b).subtract(new BigDecimal("4").multiply(a).multiply(c));

        if (delta.compareTo(BigDecimal.ZERO) == -1) {

            throw new CalculoException(
                            "Não existe solução no conjunto Real, pois não existe raiz quadrada real de número negativo.");
        }

        BigDecimal raizDeDelta = new BigDecimal(Math.sqrt(delta.doubleValue()));
        BigDecimal doisA = a.multiply(new BigDecimal("2"));

        BigDecimal xLinha = (b.negate().add(raizDeDelta)).divide(doisA, Utilitaria.getRoundMode());
        BigDecimal x2Linha = (b.negate().subtract(raizDeDelta)).divide(doisA, Utilitaria.getRoundMode());

        xLinha = xLinha.setScale(Utilitaria.getScale(), Utilitaria.getRoundMode());
        x2Linha = x2Linha.setScale(Utilitaria.getScale(), Utilitaria.getRoundMode());

        if (xLinha.equals(x2Linha)) {
            return new BigDecimal[] {xLinha};
        }

        return new BigDecimal[] {xLinha, x2Linha};
    }
}
