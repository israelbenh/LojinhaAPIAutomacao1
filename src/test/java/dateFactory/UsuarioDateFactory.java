package dateFactory;

import pojo.UsuarioPojo;

public class UsuarioDateFactory {
    public static UsuarioPojo criarUsuarioComLoginESenha(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioSenha("admin");
        usuario.setUsuarioLogin("admin");

        return usuario;
    }
}
