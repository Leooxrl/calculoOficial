package br.com.calculo.regraNegocio;

import java.math.BigDecimal;
import java.util.Map;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;
import br.com.calculo.bean.Variavel;

public class FuncaoRN {

    /**
     * Substitui o valor das variáveis pelo valor recebido na variável independente e retorna a
     * imagem na função
     * 
     * @param funcao
     * @return
     */
    public static BigDecimal resolver(Polinomio funcao, Map<Variavel, BigDecimal> mapaVariavelValor) {
        BigDecimal imagem = BigDecimal.ZERO;

        for (Monomio m : funcao.getTermos()) {

            BigDecimal valorMonomio = null;
            for (Variavel var : m.getVariaveis()) {

                for (Variavel varMap : mapaVariavelValor.keySet()) {
                    if (VariavelRN.ehMesmaVariavel(var, varMap)) {

                        if (valorMonomio == null) {
                            double v =
                                            Math.pow(mapaVariavelValor.get(varMap).doubleValue(),
                                                            var.getPotencia());

                            valorMonomio = new BigDecimal(v);

                        } else {

                            double v =
                                            Math.pow(mapaVariavelValor.get(varMap).doubleValue(),
                                                            var.getPotencia());

                            valorMonomio = valorMonomio.multiply(new BigDecimal(v));
                        }
                    }
                }


            }

            if (valorMonomio == null) {
                valorMonomio = m.getCoeficiente();
            } else {
                valorMonomio = valorMonomio.multiply(m.getCoeficiente());
            }

            imagem = imagem.add(valorMonomio);
        }


        return imagem;
    }
}
