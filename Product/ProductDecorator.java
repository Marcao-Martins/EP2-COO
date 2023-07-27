package Product;
public abstract class ProductDecorator implements Produto{
    protected Produto product;

    public ProductDecorator(Produto product) {
        this.product = product;
    }

    @Override
    public void setQtdEstoque(int qtdEstoque) {
        this.product.setQtdEstoque(qtdEstoque);;
    }

    @Override
    public void setPreco(double preco) {
        this.product.setPreco(preco); }

    @Override
    public int getId() {
        return this.product.getId();
    }
    @Override
    public String getDescricao() {
        return this.product.getDescricao();
    }

    @Override
    public String getCategoria() {
        return this.product.getCategoria();
    }

    @Override
    public int getQtdEstoque() {
        return this.product.getQtdEstoque();
    }

    @Override
    public double getPreco() {
        return this.product.getPreco();
    }

    @Override
    public String formataParaImpressao() {
        return this.product.formataParaImpressao();
    }
}
