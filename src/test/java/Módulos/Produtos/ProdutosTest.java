package Módulos.Produtos;


import Páginas.LoginPage;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        //abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chromedriver96\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //maximizar o browser
        this.navegador.manage().window().maximize();

        //definir tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //navegar para a página Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        //fazer login

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdiçãoNovoProduto()
                .informarNomeDoProduto("Mack book Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterformulárioDeAdiçãoComErro()
                .capturarMensagemApresentada();


              Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Não é permitido registrar um valor acima de 7000")
    public void testNaoEPermitidoRegistrarUmValorAcimaDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdiçãoNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, azul")
                .submeterformulárioDeAdiçãoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7000 reais")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdiçãoNovoProduto()
                .informarNomeDoProduto("Bolsa Prada")
                .informarValorDoProduto("300000")
                .informarCoresDoProduto("prata")
                .submeterformulárioDeAdiçãoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7000.00 reais")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdiçãoNovoProduto()
                .informarNomeDoProduto("Bolsa Gucci")
                .informarValorDoProduto("280000")
                .informarCoresDoProduto("dourada")
                .submeterformulárioDeAdiçãoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }


    @AfterEach
                public void afterEach(){
        //fechar navegador
        navegador.quit();



    }
}
