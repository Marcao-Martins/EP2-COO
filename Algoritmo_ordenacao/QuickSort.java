package Algoritmo_ordenacao;

import java.util.List;
import Criterio_ordenacao.CriterioOrdenacao;
import Product.Produto;

public class QuickSort implements AlgoritmoOrdenacao {
    @Override
    public void ordenar(List<Produto> produtos, CriterioOrdenacao criterioOrdenacao) {
        ordena(0, produtos.size() - 1, produtos, criterioOrdenacao);
    }

    private int particiona(int ini, int fim, List<Produto> produtos, CriterioOrdenacao criterioOrdenacao) {
    
        Produto x = produtos.get(ini);
		int i = (ini - 1);
		int j = (fim + 1);

		while(true){

			do {
				j--;
			} while (criterioOrdenacao.comparar(produtos.get(j), x) > 0);
	
			do {
				i++;
			} while (criterioOrdenacao.comparar(produtos.get(i), x) < 0);

			/*if(!(criterio.equals(CRIT_DESC_CRESC) || criterio.equals(CRIT_ESTOQUE_CRESC) || criterio.equals(CRIT_PRECO_CRESC))){
				throw new RuntimeException("Criterio invalido!");
			}*/

			if(i < j){
				Produto temp = produtos.get(i);
				produtos.set(i, produtos.get(j));				
				produtos.set(j, temp);
			}
			else return j;
		}
    }

    private void ordena(int ini, int fim, List<Produto> produtos, CriterioOrdenacao criterioOrdenacao) {
        if (ini < fim) {
            int q = particiona(ini, fim, produtos, criterioOrdenacao);

            ordena(ini, q, produtos, criterioOrdenacao);
            ordena(q + 1, fim, produtos, criterioOrdenacao);
        }
    }
}
