package Páginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdiçãoDeProdutosPage {
    private WebDriver navegador;

    public FormularioDeAdiçãoDeProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }
    public FormularioDeAdiçãoDeProdutosPage informarNomeDoProduto(String produtoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }
    public FormularioDeAdiçãoDeProdutosPage informarValorDoProduto(String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }
    public FormularioDeAdiçãoDeProdutosPage informarCoresDoProduto(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);


        return this;
    }

    //submeter formulário
    public ListaDeProdutosPage submeterformulárioDeAdiçãoComErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }
    //submeter
    public FormulárioDeEdiçãoDeProdutoPage submeterformulárioDeAdiçãoComSucesso() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormulárioDeEdiçãoDeProdutoPage(navegador);

    }
}
