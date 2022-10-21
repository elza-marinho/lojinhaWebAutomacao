package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoProdutoPage {

    private WebDriver navegador;

    public FormularioAdicaoProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }


    public FormularioAdicaoProdutoPage informarNomeProduto(String produtoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }

    public FormularioAdicaoProdutoPage informarValorProduto(String produtoValor) {
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);
        return this;
    }

    public FormularioAdicaoProdutoPage informarCoresProduto(String produtoCores) {
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }

    public ListaProdutosPage submeterFormularioDeAdicaoComErro() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaProdutosPage(navegador);
    }

    public FormularioEdicaoProdutoPage submeterFormularioDeAdicaoComSucesso() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioEdicaoProdutoPage(navegador);
    }


}
