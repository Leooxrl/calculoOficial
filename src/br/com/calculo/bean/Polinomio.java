package br.com.calculo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Polinomio {

    private List<Monomio> termos = new ArrayList<>();

    public Polinomio() {}

    public Polinomio(List<Monomio> termos) {
        this.termos = termos;
    }

    public Polinomio(Monomio... termos) {
        this.termos = new ArrayList<>(Arrays.asList(termos));
    }


    public List<Monomio> getTermos() {
        return termos;
    }

    public void setTermos(List<Monomio> termos) {
        this.termos = termos;
    }

    @Override
    public String toString() {
        String polinomio = "";
        boolean isPrimeiro = true;

        for (Monomio termo : this.termos) {

            if (isPrimeiro) {
                polinomio = termo.toString();
            } else {
                // todo coeficiente positivo adiciona-se o + entre monomios
                if (BigDecimal.ZERO.compareTo(termo.getCoeficiente()) < 0) {
                    polinomio += " +";
                } else {
                    polinomio += " ";
                }

                polinomio += termo.toString();
            }

            isPrimeiro = false;
        }

        return polinomio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((termos == null) ? 0 : termos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Polinomio)) {
            return false;
        }
        Polinomio other = (Polinomio) obj;
        if (termos == null) {
            if (other.termos != null) {
                return false;
            }
        } else if (!termos.equals(other.termos)) {
            return false;
        }
        return true;
    }

}
