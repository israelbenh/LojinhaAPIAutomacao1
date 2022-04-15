package modulos.produto;

import dateFactory.ProdutoDateFactory;
import dateFactory.UsuarioDateFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.Produtopojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        //port = 8080
        basePath = "/lojinha-bugada";



        // Obter o token do usuario admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDateFactory.criarUsuarioComLoginESenha())
        .when()
                .post("/v2/login")
            .then()
                    .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 nao e permitido")
    public void testValidarLimitesZeradoProibidoValorProduto() {
        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        // status code retornando foi 422

        Produtopojo produto = new Produtopojo();
        produto.setProdutoNome("Play Station");
        produto.setProdutoValor(0.00);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("controle");
        componente.setComponenteQuantidade(1);

        componentes.add(componente);

        produto.setComponentes(componentes);



        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDateFactory.criarProdutoComOValorIgualA(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 nao e permitido")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {
        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        // status code retornando foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDateFactory.criarProdutoComOValorIgualA(7000.01))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);





    }

}