package Criterio_ordenacao;

import Product.Produto;

public class DescricaoDecrescente implements CriterioOrdenacao {
    @Override
    public int comparar(Produto p1, Produto p2) {
        return p2.getDescricao().compareToIgnoreCase(p1.getDescricao());
    }
}
