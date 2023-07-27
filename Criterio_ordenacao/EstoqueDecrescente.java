package Criterio_ordenacao;

import Product.Produto;

public class EstoqueDecrescente implements CriterioOrdenacao {
    @Override
    public int comparar(Produto p1, Produto p2) {
        return Integer.compare(p2.getQtdEstoque(), p1.getQtdEstoque());
    }
}
