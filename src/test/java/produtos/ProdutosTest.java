package produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web modulo produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");

        this.navegador = new ChromeDriver();

        //Acessar a pagina da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @Test
    @DisplayName("Nao e permitido registrar produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeProduto("Batom maybelline")
                .informarValorProduto("000")
                .informarCoresProduto("rosa, matte, vermelho")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);


    }

    @Test
    @DisplayName("Nao e permitido registrar produto com valor maior que 7000")

    public void testNaoEPermitidoProdutoComValorMaiorQueSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeProduto("TV 75 polegadas")
                .informarValorProduto("1000000")
                .informarCoresProduto("preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Adicionar produtos que estão dentro da faixa de preço")

    public void testAdicionarProdutosDentroDaFaixaDePreco() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeProduto("Batom Maybelline ")
                .informarValorProduto("12000")
                .informarCoresProduto("rosa")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);


    }

    @Test
    @DisplayName("Adicionar produto com valor maximo")

    public void testAdicionarProdutoComValorMaximo() {



    String mensagemApresentada = new LoginPage(navegador)
            .informarUsuario("admin")
            .informarSenha("admin")
            .submeterFormularioDeLogin()
            .acessarFormularioAdicaoNovoProduto()
            .informarNomeProduto("Relógio Rolex ")
            .informarValorProduto("700000")
            .informarCoresProduto("prata")
            .submeterFormularioDeAdicaoComSucesso()
            .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",mensagemApresentada);
}

    @AfterEach
    public void afterEach() {


        navegador.quit();
    }
}
