package Filtro;
import Product.Produto;

public class FiltroCategoriaIgual implements CriterioFiltro {

    private String categoria;

    public FiltroCategoriaIgual(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean filtrar(Produto p, String argFiltro) {
        return p.getCategoria().equalsIgnoreCase(categoria);
    }
}
