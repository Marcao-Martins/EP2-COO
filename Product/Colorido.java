package Product;
public class Colorido  extends ProductDecorator {
    private String color;

    public Colorido(Produto product, String color) {
        super(product);
        this.color = color.toLowerCase();
    }

    @Override
    public String formataParaImpressao() {
        return "<span style=\"color: " + color + "\">\n" + super.formataParaImpressao() + "</span>";
    }
    
}
