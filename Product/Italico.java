package Product;
public class Italico extends ProductDecorator {

    public Italico(Produto product) {
        super(product);
    }

    @Override
    public String formataParaImpressao() {
        return "<span style=\"font-style:italic\">\n" + super.formataParaImpressao() + "</span>";
    }

}
