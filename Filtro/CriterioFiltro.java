package Filtro;
import Product.Produto;

public interface CriterioFiltro {
    boolean filtrar(Produto p, String argFiltro);

    static CriterioFiltro fromString(String criterioFiltro, String argFiltro) {
        switch (criterioFiltro.toLowerCase()) {
            case "todos":
                return new FiltroTodos();
            case "estoque_menor_igual":
                return new FiltroEstoqueMenorIgual(Integer.parseInt(argFiltro));
            case "categoria_igual":
                return new FiltroCategoriaIgual(argFiltro);
            default:
                throw new IllegalArgumentException("Criterio de filtro invalido!");
        }
    }
}
