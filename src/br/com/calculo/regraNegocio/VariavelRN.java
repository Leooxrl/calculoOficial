package br.com.calculo.regraNegocio;

import br.com.calculo.bean.Variavel;

public class VariavelRN {

    /**
     * Se as vari�veis possuirem nomes iguais, s�o a mesma vari�vel.
     * 
     * @param var1
     * @param var2
     * @return
     */
    public static boolean ehMesmaVariavel(Variavel var1, Variavel var2) {
        return var1.getNome().equals(var2.getNome());
    }

}
