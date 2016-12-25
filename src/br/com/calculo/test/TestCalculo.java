package br.com.calculo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.calculo.bean.Monomio;
import br.com.calculo.bean.Polinomio;
import br.com.calculo.bean.Variavel;
import br.com.calculo.regraNegocio.BhaskaraRN;
import br.com.calculo.regraNegocio.CalculoException;
import br.com.calculo.regraNegocio.DerivadaRN;
import br.com.calculo.regraNegocio.FuncaoRN;
import br.com.calculo.regraNegocio.OperacaoRN;
import br.com.calculo.regraNegocio.PolinomioRN;
import br.com.calculo.util.Utilitaria;

public class TestCalculo {

    /*
     * soma e multiplicação de números simples ex: 5 + 3 = 8 ex: 2 x 2 = 4
     */

    @Test
    public void testSomaAlgarismos() {

        Monomio cinco = new Monomio(new BigDecimal("5"));
        Monomio tres = new Monomio(new BigDecimal("3"));

        Polinomio soma = OperacaoRN.somarMonomios(cinco, tres);

        Polinomio somaEsperada = new Polinomio(new Monomio(new BigDecimal("8")));

        Monomio cincoEsperado = new Monomio(new BigDecimal("5"));

        assertEquals(cincoEsperado, cinco);
        assertEquals(somaEsperada, soma);

        Polinomio poliCincoEsperado = new Polinomio(cincoEsperado);
        Polinomio poliCinco = new Polinomio(cinco);

        System.out.println(poliCincoEsperado.getTermos().containsAll(poliCinco.getTermos()));

        assertEquals(poliCincoEsperado.getTermos(), poliCinco.getTermos());
    }

    @Test
    public void testSomaMonomio() {

        Monomio oito = new Monomio(new BigDecimal("8"));
        Monomio oito1 = new Monomio(new BigDecimal("8"));

        Polinomio poliEsperado = new Polinomio(oito);
        Polinomio poliAtual = new Polinomio(oito1);

        assertEquals(poliEsperado, poliAtual);
    }

    @Test
    public void testMultiplicacaoPolinomios() {

        Monomio mp1 = new Monomio(new Variavel());
        Polinomio p1 = new Polinomio(mp1);

        Monomio m1p2 = new Monomio(new BigDecimal("16"));
        Monomio m2p2 = new Monomio(new BigDecimal("-2"), new Variavel());
        Polinomio p2 = new Polinomio(m1p2, m2p2);

        Monomio m1p3 = new Monomio(new BigDecimal("30"));
        Polinomio p3 = new Polinomio(m1p3, m2p2);

        Polinomio resultado = OperacaoRN.multiplicarPolinomios(p2, p3);
        Polinomio resultadoFinal = OperacaoRN.multiplicarPolinomios(resultado, p1);

        PolinomioRN.ordenarTermos(resultadoFinal);

        Monomio mResultado1 = new Monomio(new BigDecimal("4"), new Variavel(3));
        Monomio mResultado2 = new Monomio(new BigDecimal("-92"), new Variavel(2));
        Monomio mResultado3 = new Monomio(new BigDecimal("480"), new Variavel());
        Polinomio resultadoEsperado = new Polinomio(mResultado1, mResultado2, mResultado3);

        assertEquals(resultadoEsperado, resultadoFinal);

        System.out.println(resultadoFinal);
    }

    @Test
    public void testDerivada() {

        Monomio m1 = new Monomio(new BigDecimal("3"), new Variavel(2));
        Monomio m2 = new Monomio(BigDecimal.ONE);
        Polinomio funcao = new Polinomio(m1, m2);

        System.out.println("f(x) = " + funcao);

        Polinomio derivada = DerivadaRN.derivar(funcao);

        Polinomio derivadaEsperada =
                        new Polinomio(new Monomio(new BigDecimal("6"), new Variavel()));

        assertEquals(derivadaEsperada, derivada);

        System.out.println("f'(x) = " + derivada);
    }

    @Test
    public void testDerivadaFuncaoTerceiroGrau() {

        // 4x� - 60x� + 200x
        Monomio termo1 = new Monomio(new BigDecimal("4"), new Variavel(3));
        Monomio termo2 = new Monomio(new BigDecimal("-60"), new Variavel(2));
        Monomio termo3 = new Monomio(new BigDecimal("200"), new Variavel());

        System.out.println(termo2);

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        System.out.println("f(x) = " + funcao);

        Polinomio derivada = DerivadaRN.derivar(funcao);

        Monomio dTermo1 = new Monomio(new BigDecimal("12"), new Variavel(2));
        Monomio dTermo2 = new Monomio(new BigDecimal("-120"), new Variavel());
        Monomio dTermo3 = new Monomio(new BigDecimal("200"));

        // 12x� -12x +200
        Polinomio derivadaEsperada = new Polinomio(dTermo1, dTermo2, dTermo3);

        System.out.println("f'(x) =" + derivada);
        assertEquals(derivadaEsperada, derivada);
    }

    @Test
    public void test200x() {

        Monomio termo = new Monomio(new BigDecimal("200"), new Variavel());
        Polinomio funcao = new Polinomio(termo);

        System.out.println(funcao);

        Polinomio derivada = DerivadaRN.derivar(funcao);

        Polinomio derivadaEsperada = new Polinomio(new Monomio(new BigDecimal("200")));

        assertEquals(derivadaEsperada, derivada);

        System.out.println("f'(x)=" + derivada);

    }

    @Test
    public void testBhaskara() throws CalculoException {

        Monomio termo1 = new Monomio(new BigDecimal("3"), new Variavel(2));
        Monomio termo2 = new Monomio(new BigDecimal("-7"), new Variavel());
        Monomio termo3 = new Monomio(new BigDecimal("4"));

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        BigDecimal[] solucoes = BhaskaraRN.resolver(funcao);

        System.out.println(solucoes[0]);

        BigDecimal[] solucoesEsperadas = {new BigDecimal("1.0000")};
        assertTrue(Arrays.equals(solucoesEsperadas, solucoes));

    }

    @Test
    public void testBhaskara2() throws CalculoException {

        Monomio termo1 = new Monomio(new Variavel(2));
        Monomio termo2 = new Monomio(new BigDecimal("-49"), new Variavel());
        Monomio termo3 = new Monomio(new BigDecimal("3"));

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        BigDecimal[] solucoes = BhaskaraRN.resolver(funcao);

        BigDecimal[] solucoesEsperadas = {new BigDecimal("48.9387"), new BigDecimal("0.0613")};

        assertTrue(Arrays.equals(solucoesEsperadas, solucoes));
    }

    @Test
    public void testBhaskara3() {

        Monomio termo1 = new Monomio(new BigDecimal("7"), new Variavel(2));
        Monomio termo2 = new Monomio(new BigDecimal("3"), new Variavel());
        Monomio termo3 = new Monomio(new BigDecimal("4"));

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        String mensagemExcecao = "";
        try {
            BhaskaraRN.resolver(funcao);
        } catch (CalculoException e) {
            mensagemExcecao = e.getMessage();
        }

        assertEquals("Não existe solução no conjunto Real, pois não existe raiz quadrada real de número negativo.",
                        mensagemExcecao);
    }

    @Test
    public void testFuncao() {
        Monomio termo = new Monomio(new BigDecimal("4"));
        Polinomio funcao = new Polinomio(termo);

        Map<Variavel, BigDecimal> mapaVarValor = new HashMap<>();

        BigDecimal valor = FuncaoRN.resolver(funcao, mapaVarValor);

        assertEquals(valor, new BigDecimal("4"));
    }

    @Test
    public void testFormataBigDecimal() {

        BigDecimal valor = new BigDecimal("1.0000");

        System.out.println(Utilitaria.formataBigDecimal(valor));

        assertEquals(new BigDecimal("1"), Utilitaria.formataBigDecimal(valor));
    }

    @Test
    public void testFuncao2() {
        Monomio termo1 = new Monomio(new BigDecimal("12"), new Variavel(2));
        Monomio termo2 = new Monomio(new BigDecimal("-104"), new Variavel());
        Monomio termo3 = new Monomio(new BigDecimal("160"));

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
        mapVarValor.put(new Variavel(), new BigDecimal("2"));

        BigDecimal y = FuncaoRN.resolver(funcao, mapVarValor);

        System.out.println(y);
        assertEquals(BigDecimal.ZERO, y);
    }

    @Test
    public void testFuncao3() {
        Monomio termo1 = new Monomio(new BigDecimal("4"), new Variavel(3));
        Monomio termo2 = new Monomio(new BigDecimal("-52"), new Variavel(2));
        Monomio termo3 = new Monomio(new BigDecimal("160"), new Variavel());

        Polinomio funcao = new Polinomio(termo1, termo2, termo3);

        Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
        mapVarValor.put(new Variavel(), new BigDecimal("2"));

        BigDecimal y = FuncaoRN.resolver(funcao, mapVarValor);

        System.out.println(y);
        assertEquals(new BigDecimal("144"), y);
    }

    @Test
    public void testFuncao4() {
        Monomio m = new Monomio(new BigDecimal("2"), new Variavel(), new Variavel("y"));
        Polinomio funcao = new Polinomio(m);

        Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
        mapVarValor.put(new Variavel(), new BigDecimal("1"));
        mapVarValor.put(new Variavel("y"), new BigDecimal("2"));

        BigDecimal z = FuncaoRN.resolver(funcao, mapVarValor);

        assertEquals(new BigDecimal("4"), z);
        System.out.println(z);
    }

    @Test
    public void testFuncao5() {
        Monomio m = new Monomio(new BigDecimal("2"), new Variavel(3), new Variavel("y", 2));
        Polinomio funcao = new Polinomio(m);

        Map<Variavel, BigDecimal> mapVarValor = new HashMap<>();
        mapVarValor.put(new Variavel(), new BigDecimal("1"));
        mapVarValor.put(new Variavel("y"), new BigDecimal("2"));

        BigDecimal z = FuncaoRN.resolver(funcao, mapVarValor);

        assertEquals(new BigDecimal("8"), z);
        System.out.println(z);
    }

}
