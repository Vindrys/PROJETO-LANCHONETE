package control;
import model.Funcionario;

import java.time.LocalDate;
import java.util.*;

/**
 * A classe <b>CadastroUsuário</b> contém os métodos da classe para serem usados no domínio da aplicação
 * Usada para cadastrar usuarios do domínio
 * @author RaulRomulo
 * @since 1.0
 * @version 1.0
 */

public class CadastroUsuario {
    /**
     * A classe inicializa uma estrutura HashMap de funcionario com vários usuários
     */
    private Map<String, Funcionario> usuarios;
    private Funcionario loginMaster;

    /**
     * Metodo usario para salvar e inicializar o usuário na estrutura
     */
    public CadastroUsuario(){
        usuarios = new HashMap<>();
    }

    /**
     *
     * @param usuario indica o usuário
     * Metodo usado para cadastrar um novo usuário
     * @return se tem ou não um funcionário salvo com o mesmo email
     */
    public boolean isSalvar(Funcionario usuario) {
        if(isBuscar(usuario.getEmail())== null) {
            usuarios.put(usuario.getEmail(), usuario);
            return true;
        }
        return false;
    }

    /**
     *
     * @param email indica o email do usuário
     * @param senha indica a senha do usuário
     * Metodo para autenticar o usuário para acessar o sistema
     * @return se tem ou não o usuário cadastrado
     */
    public boolean isAutenticar(String email, String senha){
        Funcionario func = isBuscar(email);
        if (func != null){
            if (func.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param email indica o email do usuário
     * Metodo busca e remove um funcionario cadastrado
     * @return remove o usuário se estiver cadastrado no sistema
     */
    public boolean isRemove(String email){
        Funcionario func = isBuscar(email);
        if (func!=null){
                return usuarios.remove(email,func);
        }
        return false;
    }

    /**
     *
     * @param email indica o email do usuário
     * @param usuario indica o usuário
     * Metodo edita dados do usuário
     * @return o usuário salvo se ele estiver cadastrado
     */
    public boolean isEditar(String email, Funcionario usuario){
        if (isBuscar(email)!= null){
            isRemove(email);
            return isSalvar(usuario);
        }
        return false;
    }

    /**
     * Metodo lista os usuários cadastrados
     * @return usuários cadastrados
     */
    public Map<String,Funcionario> isListar(){
        return usuarios;
    }

    /**
     *
     * @param email indica o email do usuário
     * Método busca usuários cadastrados
     * @return usuários cadastrados
     */
    public Funcionario isBuscar(String email) {
        if(usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(email);
    }

}
