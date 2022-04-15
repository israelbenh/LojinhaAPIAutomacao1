package dateFactory;

import pojo.ComponentePojo;
import pojo.Produtopojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDateFactory {
    public static Produtopojo criarProdutoComOValorIgualA(double valor){

        Produtopojo produto = new Produtopojo();
        produto.setProdutoNome("Play Station");
        produto.setProdutoValor(valor);

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

        return produto;

    }
}
