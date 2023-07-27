package Product;
public class Negrito extends ProductDecorator {

    public Negrito(Produto product) {
        super(product);
    }
    
    @Override
    public String formataParaImpressao() {
        return "<span style=\"font-weight:bold\">\n" + super.formataParaImpressao() + "</span>";
    }

}
