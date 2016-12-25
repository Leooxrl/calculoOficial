package br.com.calculo.bean;

import br.com.calculo.util.Utilitaria;

public class Variavel {

    private String nome; // ex: x, y, area
    private double potencia; // n�mero pelo qual a vari�vel ser� elevada.

    /**
     * vari�vel x
     */
    public Variavel() {
        this.nome = "x";
        this.potencia = 1;
    }

    public Variavel(String nome, double potencia) {
        this.nome = nome;
        this.potencia = potencia;
    }

    /**
     * vari�vel x na pot�ncia passada por par�metro
     * 
     * @param potencia
     */
    public Variavel(double potencia) {
        this.nome = "x";
        this.potencia = potencia;
    }

    /**
     * vari�vel com o nome do par�metro e pot�ncia 1.
     * 
     * @param potencia
     */
    public Variavel(String nome) {
        this.nome = nome;
        this.potencia = 1d;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        // qualquer n�mero na pot�ncia 0 � 1
        if (this.potencia == 0d) {
            return "";
        }
        // pot�ncia 1 mostra somente o nome.
        if (this.potencia == 1) {
            return this.nome;
        }

        return nome + "^" + Utilitaria.formataDouble(potencia);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        long temp;
        temp = Double.doubleToLongBits(potencia);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Para ser igual deve ter o mesmo nome e potência
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Variavel)) {
            return false;
        }
        Variavel other = (Variavel) obj;
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (Double.doubleToLongBits(potencia) != Double.doubleToLongBits(other.potencia)) {
            return false;
        }
        return true;
    }

}
