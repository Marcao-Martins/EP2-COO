package Criterio_ordenacao;
import Product.Produto;

public interface CriterioOrdenacao {

    static CriterioOrdenacao fromString(String criterio) {
        switch (criterio.toLowerCase()) {
            case "descricao_c":
                return new Descricao();
            case "preco_c":
                return new Preco();
            case "estoque_c":
                return new Estoque();
            case "descricao_d": // Novo critério para ordenação decrescente por descrição
                return new DescricaoDecrescente();
            case "preco_d": // Novo critério para ordenação decrescente por preço
                return new PrecoDecrescente();
            case "estoque_d": // Novo critério para ordenação decrescente por estoque
                return new EstoqueDecrescente();
            default:
                throw new IllegalArgumentException("Criterio de ordenacao invalido!");
        }
    }

    int comparar(Produto p1, Produto p2);
}