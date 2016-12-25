package br.com.calculo.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.calculo.regraNegocio.MonomioRN;

public class Monomio {

    private BigDecimal coeficiente;
    private Set<Variavel> variaveis = new LinkedHashSet<>();

    public Monomio() {}

    public Monomio(Monomio monomio) {
        this.coeficiente = monomio.getCoeficiente();
        this.variaveis = monomio.getVariaveis();
    }

    public Monomio(BigDecimal coeficiente, Variavel... variaveis) {
        this.coeficiente = coeficiente;
        this.variaveis = new LinkedHashSet<>(Arrays.asList(variaveis));
    }

    /**
     * coeficiente 1
     * 
     * @param variaveis
     */
    public Monomio(Variavel... variaveis) {
        this.coeficiente = BigDecimal.ONE;
        this.variaveis = new LinkedHashSet<>(Arrays.asList(variaveis));
    }

    public Monomio(BigDecimal coeficiente) {
        this.coeficiente = coeficiente;
    }

    public Monomio(BigDecimal coeficiente, Set<Variavel> variaveis) {
        this.coeficiente = coeficiente;
        this.variaveis = variaveis;
    }

    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(BigDecimal coeficiente) {
        this.coeficiente = coeficiente;
    }

    public Set<Variavel> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(Set<Variavel> variaveis) {
        this.variaveis = variaveis;
    }

    @Override
    public String toString() {
        // todo n�mero multiplicado por zero � zero!
        if (coeficiente.equals(BigDecimal.ZERO)) {
            return "0";
        }

        String var = "";
        if (this.variaveis != null) {
            var = this.variaveis.toString().replace("[", "").replace("]", "");

            if (this.variaveis.size() > 1) {
                var = var.replace(", ", " * ");
            }
        }

        // coeficiente um mostra apenas o nome da vari�vel
        if (this.coeficiente.equals(BigDecimal.ONE) && !var.isEmpty()) {
            return var;
        }

        if (this.coeficiente.equals(BigDecimal.ONE.negate()) && !var.isEmpty()) {
            return "-" + var;
        }

        return this.coeficiente.toString() + var;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coeficiente == null) ? 0 : coeficiente.hashCode());
        result = prime * result + ((variaveis == null) ? 0 : variaveis.hashCode());
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
        if (!(obj instanceof Monomio)) {
            return false;
        }
        Monomio other = (Monomio) obj;
        if (coeficiente == null) {
            if (other.coeficiente != null) {
                return false;
            }
        } else if (!coeficiente.equals(other.coeficiente)) {
            return false;
        }

        return MonomioRN.ehVariaveisIguais(this, (Monomio) obj);
    }

}
