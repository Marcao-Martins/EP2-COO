public class Estoque implements CriterioOrdenacao {
    @Override
    public int comparar(Produto p1, Produto p2) {
        return Integer.compare(p1.getQtdEstoque(), p2.getQtdEstoque());
    }
}
