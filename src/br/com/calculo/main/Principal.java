package br.com.calculo.main;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;
import br.com.calculo.bean.Variavel;
import br.com.calculo.regraNegocio.BhaskaraRN;
import br.com.calculo.regraNegocio.CalculoException;
import br.com.calculo.regraNegocio.DerivadaRN;
import br.com.calculo.regraNegocio.FuncaoRN;
import br.com.calculo.regraNegocio.OperacaoRN;
import br.com.calculo.regraNegocio.PolinomioRN;



public class Principal {

    public static Map<String, BigDecimal> calcular(BigDecimal larguraFolha,
                    BigDecimal comprimentoFolha) throws CalculoException {
        Map<String, BigDecimal> solucao = new HashMap<>();

        // equação do volume da caixa V(x) = 4x³ - 60x² + 200x
        Polinomio funcaoVolume = montarEquacaoVolume(larguraFolha, comprimentoFolha);

        // é necessário derivar a função de volume para encontrar os máximos e mínimos
        Polinomio derivadaVolume = DerivadaRN.derivar(funcaoVolume);

        BigDecimal[] solucoes = BhaskaraRN.resolver(derivadaVolume);

        BigDecimal[] volumes = new BigDecimal[2];
        for (int i = 0; i < solucoes.length; i++) {
            Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
            mapVarValor.put(new Variavel(), solucoes[i]);
            volumes[i] = FuncaoRN.resolver(funcaoVolume, mapVarValor);
        }

        int index = 0;
        // basta saber qual dos volumes é maior!
        if (volumes[0].compareTo(volumes[1]) == -1) {
            index = 1;
        }
        solucao.put("volume", volumes[index]);
        solucao.put("quadrado", solucoes[index]);
        solucao.put("altura", solucoes[index]);

        BigDecimal larguraCaixa =
                        larguraFolha.subtract(new BigDecimal("2").multiply(solucoes[index]));

        solucao.put("largura", larguraCaixa);

        BigDecimal comprimentoCaixa =
                        comprimentoFolha.subtract(new BigDecimal("2").multiply(solucoes[index]));

        solucao.put("comprimento", comprimentoCaixa);

        return solucao;
    }

    public static void main(String[] args) throws CalculoException {

        BigDecimal dimLarguraFolha = new BigDecimal("16");
        BigDecimal dimComprimentoFolha = new BigDecimal("10");

        // equação do volume da caixa V(x) = 4x³ - 60x² + 200x
        Polinomio funcaoVolume = montarEquacaoVolume(dimLarguraFolha, dimComprimentoFolha);

        // é necessário derivar a função de volume para encontrar os máximos e mínimos
        Polinomio derivadaVolume = DerivadaRN.derivar(funcaoVolume);

        BigDecimal[] solucoes = BhaskaraRN.resolver(derivadaVolume);


        BigDecimal[] volumes = new BigDecimal[2];
        for (int i = 0; i < solucoes.length; i++) {
            Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
            mapVarValor.put(new Variavel(), solucoes[i]);
            volumes[i] = FuncaoRN.resolver(funcaoVolume, mapVarValor);
        }

        // basta saber qual dos volumes é maior!
        if (volumes[0].compareTo(volumes[1]) == 0 || volumes[0].compareTo(volumes[1]) == 1) {
            System.out.println("O maior volume possível da caixa é: " + volumes[0] + " u.v");
            System.out.println("O quadrado deve ser cortado com medida = " + solucoes[0] + " u.d");
            System.out.println(
                            "As dimensões da caixa serão: Altura = " + solucoes[0] + ", Largura = "
                                            + dimLarguraFolha.subtract(new BigDecimal("2")
                                                            .multiply(solucoes[0]))
                            + ", Comprimento = " + dimComprimentoFolha
                                            .subtract(new BigDecimal("2").multiply(solucoes[0])));
        } else {
            System.out.println("O maior volume possível da caixa é: " + volumes[1] + " u.v");
            System.out.println("O quadrado deve ser cortado com medida = " + solucoes[1] + " u.d");
            System.out.println(
                            "As dimensões da caixa serão: Altura = " + solucoes[1] + ", Largura = "
                                            + dimLarguraFolha.subtract(new BigDecimal("2")
                                                            .multiply(solucoes[1]))
                            + ", Comprimento = " + dimComprimentoFolha
                                            .subtract(new BigDecimal("2").multiply(solucoes[1])));
        }
    }

    /**
     * Monta a equação de volume da caixa.
     * 
     * @param dimensaoLarguraFolha
     * @param dimensaoComprimentoFolha
     * @return
     */
    private static Polinomio montarEquacaoVolume(BigDecimal dimensaoLarguraFolha,
                    BigDecimal dimensaoComprimentoFolha) {
        Variavel x = new Variavel();
        Monomio altura = new Monomio(x);

        Monomio larguraFolha = new Monomio(dimensaoLarguraFolha);
        Monomio corte = new Monomio(new BigDecimal("2").negate(), x);
        Monomio comprimentoFolha = new Monomio(dimensaoComprimentoFolha);

        // 20 -2X
        Polinomio comprimentoCaixa = new Polinomio(comprimentoFolha, corte);
        // 10 -2X
        Polinomio larguraCaixa = new Polinomio(larguraFolha, corte);
        Polinomio alturaCaixa = new Polinomio(altura);


        // (10 -2X) * (20 -2X)
        Polinomio produtoLarguraComprimento =
                        OperacaoRN.multiplicarPolinomios(larguraCaixa, comprimentoCaixa);

        // 200 -20X -40X + 4X²
        // 4X² -60X + 200
        // System.out.println(produtoLarguraComprimento);

        // x(4x² -60x +200)
        // 4x³ - 60x² + 200x
        Polinomio produtoLarguraComprimentoAltura =
                        OperacaoRN.multiplicarPolinomios(alturaCaixa, produtoLarguraComprimento);
        System.out.println(produtoLarguraComprimentoAltura);

        PolinomioRN.ordenarTermos(produtoLarguraComprimentoAltura);

        return produtoLarguraComprimentoAltura;
    }
}
