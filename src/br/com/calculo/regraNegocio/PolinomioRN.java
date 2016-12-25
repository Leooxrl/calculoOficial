package br.com.calculo.regraNegocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;



public class PolinomioRN {

    /**
     * <p>
     * Se já existir um termo semelhante eles são somados.
     * </p>
     * <p>
     * Caso contrário o termo é adicionado ao polinômio.
     * </p>
     * 
     * @param termo
     */
    public static void addTermo(Polinomio pol, Monomio mon) {
        Iterator<Monomio> iterator = pol.getTermos().iterator();

        while (iterator.hasNext()) {
            Monomio m = iterator.next();

            if (MonomioRN.ehSemelhante(m, mon)) {
                Monomio soma = OperacaoRN.somarMonomiosSemelhantes(m, mon);
                mon = soma;
                pol.getTermos().remove(m);
                break;
            }
        }

        pol.getTermos().add(mon);
    }

    /**
     * Ordenar no polinômio os monômios de variável de maior grau
     * 
     * @param polinomio
     */
    public static void ordenarTermos(Polinomio polinomio) {

        Comparator<Monomio> comparador = (m1, m2) -> {
            return Double.compare(MonomioRN.getMaiorGrau(m1), MonomioRN.getMaiorGrau(m2)) * (-1);
        };

        Collections.sort(polinomio.getTermos(), comparador);
    }
}
