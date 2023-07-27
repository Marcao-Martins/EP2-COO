package main;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

import Algoritmo_ordenacao.AlgoritmoOrdenacao;
import Criterio_ordenacao.CriterioOrdenacao;
import Filtro.CriterioFiltro;
import Product.*;

public class GeradorDeRelatorios {

	public static final String ALG_INSERTIONSORT = "quick";
	public static final String ALG_QUICKSORT = "insertion";

	public static final String CRIT_DESC_CRESC = "descricao_c";
	public static final String CRIT_PRECO_CRESC = "preco_c";
	public static final String CRIT_ESTOQUE_CRESC = "estoque_c";

	// Constantes para critérios de ordenação decrescente
	public static final String CRIT_DESC_DECR = "descricao_d";
	public static final String CRIT_PRECO_DECR = "preco_d";
	public static final String CRIT_ESTOQUE_DECR = "estoque_d";
	
	public static final String FILTRO_TODOS = "todos";
	public static final String FILTRO_ESTOQUE_MENOR_OU_IQUAL_A = "estoque_menor_igual";
	public static final String FILTRO_CATEGORIA_IGUAL_A = "categoria_igual";

	// operador bit a bit "ou" pode ser usado para combinar mais de  
	// um estilo de formatacao simultaneamente (veja como no main)
	public static final int FORMATO_PADRAO  = 0b0000;
	public static final int FORMATO_NEGRITO = 0b0001;
	public static final int FORMATO_ITALICO = 0b0010;

	//substituição de um array tradicional por um que utiliza a coleção List
	private List<Produto> produtos;
	private String algoritmo;
	private String criterio;
	private CriterioOrdenacao criterioOrdenacao;
	private AlgoritmoOrdenacao algoritmoOrdenacao;
	private String filtro;
	private CriterioFiltro criterioFiltro;
	private String argFiltro;
	private int format_flags;	

	public GeradorDeRelatorios(List<Produto> produtos, String algoritmo, String criterio, CriterioOrdenacao criterioOrdenacao, AlgoritmoOrdenacao algoritmoOrdenacao,String filtro, CriterioFiltro criterioFiltro, String argFiltro, int format_flags){
		//adicionando os elementos diretamente na lista
		this.produtos = new ArrayList<>(produtos);
		
		this.algoritmo = algoritmo;
		this.criterio = criterio;
		this.criterioOrdenacao = criterioOrdenacao;
		this.algoritmoOrdenacao = algoritmoOrdenacao;
		this.format_flags = format_flags;
		this.filtro = filtro;
		this.argFiltro = argFiltro;
		this.criterioFiltro = criterioFiltro;
	}
	
	public void debug(){

		System.out.println("Gerando relatório para array contendo " + produtos.size() + " produto(s)");
		System.out.println("parametro filtro = '" + argFiltro + "'"); 
	}


	public void geraRelatorio(String arquivoSaida) throws IOException {

		debug();

		//ordena(0, produtos.size() - 1);
		algoritmoOrdenacao.ordenar(produtos, criterioOrdenacao);

		PrintWriter out = new PrintWriter(arquivoSaida);

		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");

		int count = 0;

		for (int i = 0; i < produtos.size(); i++) {
	        Produto p = produtos.get(i);
	        boolean selecionado = criterioFiltro.filtrar(p, argFiltro);

	        if (selecionado) {
	            out.print("<li>");

	            String cor = null; // definir a cor do produto

	            Produto.formataProdutoParaImpressao(produtos, format_flags, cor);
	            out.print(p);

	            out.println("</li>");
	            count++;

	    }
	        else {
	        	
	        }
	}
	}

	public static void main(String [] args) {

		if(args.length < 4){

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <opções de formatação>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println("\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
			System.out.println("\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual'"); 
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem"); 
			System.out.println("\topções de formatação: 'negrito' e/ou 'italico, ou colorido'");
			System.out.println();
			System.exit(1);
		}
		String opcao_algoritmo = args[0];
		String opcao_criterio_ord = args[1];
		String opcao_criterio_filtro = args[2];
		String opcao_parametro_filtro = args[3];

		// Verificar se o critério de ordenação é crescente ou decrescente
		boolean ordenacaoDecrescente = opcao_criterio_ord.endsWith("_d");

		// Remover o sufixo "_d" se for decrescente
		if (ordenacaoDecrescente) {
			opcao_criterio_ord = opcao_criterio_ord.substring(0, opcao_criterio_ord.length() - 2);
		}

		// Obtendo os critérios de ordenação e filtragem e o algoritmo com base no argumento passado via linha de comando.
		CriterioOrdenacao criterioOrdenacao = ordenacaoDecrescente
				? CriterioOrdenacao.fromString(opcao_criterio_ord + "_d") // Critério decrescente
				: CriterioOrdenacao.fromString(opcao_criterio_ord);      // Critério crescente

		AlgoritmoOrdenacao algoritmoOrdenacao = AlgoritmoOrdenacao.fromString(opcao_algoritmo);
		CriterioFiltro criterioFiltro = CriterioFiltro.fromString(opcao_criterio_filtro, opcao_parametro_filtro);

		String[] opcoes_formatacao = new String[2];
		opcoes_formatacao[0] = args.length > 4 ? args[4] : null;
		opcoes_formatacao[1] = args.length > 5 ? args[5] : null;
		int formato = FORMATO_PADRAO;

		for (int i = 0; i < opcoes_formatacao.length; i++) {
			String op = opcoes_formatacao[i];
			formato |= (op != null ? op.equals("negrito") ? FORMATO_NEGRITO : (op.equals("italico") ? FORMATO_ITALICO : 0) : 0);
		}

		GeradorDeRelatorios gdr = new GeradorDeRelatorios(Produto.carregaProdutos(),
				opcao_algoritmo,
				opcao_criterio_ord,
				criterioOrdenacao,
				algoritmoOrdenacao,
				opcao_criterio_filtro,
				criterioFiltro,
				opcao_parametro_filtro,
				formato
		);

		try {
			gdr.geraRelatorio("saida.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}