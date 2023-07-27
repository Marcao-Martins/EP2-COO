package Algoritmo_ordenacao;

import java.util.List;
import Criterio_ordenacao.CriterioOrdenacao;
import Product.Produto;

public interface AlgoritmoOrdenacao {

    static AlgoritmoOrdenacao fromString(String algoritmo) {
        switch (algoritmo.toLowerCase()) {
            case "insertion":
                return new InsertionSort();
            case "quick":
                return new QuickSort();
            default:
                throw new IllegalArgumentException("Algoritmo de ordenacao invalido!");
        }
    }

    void ordenar(List<Produto> produtos, CriterioOrdenacao criterioOrdenacao);
}
