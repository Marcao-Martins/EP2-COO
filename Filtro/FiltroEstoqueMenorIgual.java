package Filtro;
import Product.Produto;

public class FiltroEstoqueMenorIgual implements CriterioFiltro {

    private int estoqueMaximo;

    public FiltroEstoqueMenorIgual(int estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    @Override
    public boolean filtrar(Produto p, String argFiltro) {
        return p.getQtdEstoque() <= estoqueMaximo;
    }
}
