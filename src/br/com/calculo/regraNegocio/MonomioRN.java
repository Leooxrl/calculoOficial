package br.com.calculo.regraNegocio;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Variavel;
import br.com.calculo.util.Utilitaria;

public class MonomioRN {

    /**
     * 
     * @param mon1
     * @param mon2
     * @return true se as variáveis forem iguais dos dois monômios
     */
    public static boolean ehVariaveisIguais(Monomio mon1, Monomio mon2) {

        if (mon1.getVariaveis().isEmpty() && mon2.getVariaveis().isEmpty()) {
            return true;
        }

        return mon1.getVariaveis().containsAll(mon2.getVariaveis())
                        && mon2.getVariaveis().containsAll(mon1.getVariaveis());
    }

    public static boolean ehSemelhante(Monomio mon1, Monomio mon2) {

        int qtdVariaveis = mon1.getVariaveis().size();
        if (mon2.getVariaveis().size() != qtdVariaveis) {
            return false;
        }

        int countSemelhantes = 0;
        for (Variavel var : mon1.getVariaveis()) {

            // o número de variáveis semelhantes deve ser igual ao número de variaveis totais
            for (Variavel varM : mon2.getVariaveis()) {

                if (var.equals(varM)) {
                    countSemelhantes++;
                }
            }

        }

        if (qtdVariaveis == countSemelhantes) {
            return true;
        }

        return false;
    }

    public static double getMaiorGrau(Monomio monomio) {

        if (Utilitaria.setNullOrEmpty(monomio.getVariaveis())) {
            return 1d;
        }

        Double maiorGrau = null;
        for (Variavel variavel : monomio.getVariaveis()) {

            if (maiorGrau == null) {
                maiorGrau = variavel.getPotencia();
            }

            if (variavel.getPotencia() > maiorGrau) {
                maiorGrau = variavel.getPotencia();
            }

        }

        return maiorGrau == null ? 1d : maiorGrau;

    }
}
