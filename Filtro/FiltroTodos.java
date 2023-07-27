package Filtro;
import Product.Produto;

public class FiltroTodos implements CriterioFiltro {
    @Override
    public boolean filtrar(Produto p, String argFiltro) {
        return true; // Retorna true para todos os produtos, ou seja, não filtra nada.
    }
}
