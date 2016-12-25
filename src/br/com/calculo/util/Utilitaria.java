package br.com.calculo.util;

import java.math.BigDecimal;
import java.util.Set;

public class Utilitaria {

    public static String formataDouble(double valor) {
        if ((int) valor == valor) {
            return String.valueOf((int) valor);
        }

        return String.valueOf(valor);
    }

    public static BigDecimal formataBigDecimal(BigDecimal valor) {

        if (valor.compareTo(new BigDecimal(valor.intValue())) == 0) {
            return new BigDecimal(valor.intValue());
        }

        return valor;
    }


    public static boolean setNullOrEmpty(Set<?> lista) {
        return lista == null || lista.isEmpty();
    }

    private static int roundMode = BigDecimal.ROUND_HALF_EVEN;

    private static int scale = 4;

    public static int getScale() {
        return scale;
    }

    public static int getRoundMode() {
        return roundMode;
    }

}
