package Criterio_ordenacao;
import Product.Produto;

public class Preco implements CriterioOrdenacao{
    @Override
    public int comparar(Produto p1, Produto p2) {
        return Double.compare(p1.getPreco(), p2.getPreco());
    }
}
