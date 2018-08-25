package model;
import java.time.LocalDate;
import java.util.*;

public class CadastroUsuario {

    private Map<String,Funcionario> usuarios;
    private Funcionario loginMaster;

    public CadastroUsuario(){
        usuarios = new HashMap<>();
        loginMaster = new Funcionario("Master", "111.111.111-01",LocalDate.of(2018,1,1), "master@gmail.com","master123","9999999",Funcionario.Setor.ATENDIMENTO);
    }

    public boolean isSalvar(Funcionario usuario) {
        if(isBuscar(usuario.getEmail())== null) {
            usuarios.put(usuario.getEmail(), usuario);
            return true;
        }
        return false;
    }

    public boolean isAutenticar(String email, String senha){
        Funcionario func = isBuscar(email);
        if (func != null){
            if (func.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }

    public boolean isRemove(String email){
        Funcionario func = isBuscar(email);
        if (func!=null){
                return usuarios.remove(email,func);
        }
        return false;
    }

    public boolean isEditar(String email, Funcionario usuario){
        if (isBuscar(email)!= null){
            isRemove(email);
            return isSalvar(usuario);
        }
        return false;
    }

    public Map<String,Funcionario> isListar(){
        return usuarios;
    }

    public Funcionario isBuscar(String email) {
        if(usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(email);
    }

}
