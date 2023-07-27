package Algoritmo_ordenacao;

import java.util.List;
import Criterio_ordenacao.CriterioOrdenacao;
import Product.Produto;

public class InsertionSort implements AlgoritmoOrdenacao {
    @Override
    public void ordenar(List<Produto> produtos, CriterioOrdenacao criterioOrdenacao) {
        for (int i = 1; i < produtos.size(); i++) {
            Produto x = produtos.get(i);
            int j = i - 1;

            while (j >= 0 && criterioOrdenacao.comparar(produtos.get(j), x) > 0) {
                produtos.set(j + 1, produtos.get(j));
                j--;
            }

            produtos.set(j + 1, x);
        }
    }
}
