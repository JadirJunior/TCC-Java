/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import prjtcc.classes.Converter;
import prjtcc.classes.StaticKeys;

public class LoginComands {
    
    Conexao con;
    Statement statement;
    public boolean Erro = false;
    
    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ocorreu algum erro, tente novamente!");
            alert.show();
    }
    
    public boolean session(Funcionario func) throws IOException {
        ResultSet resultset = null;
        con = new Conexao();
        try {
            String query = "select * from funcionarios where nome_func = ? and senha_func = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, func.getNome());
            pst.setString(2, func.getSenha());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                    StaticKeys.setNome(resultset.getString("nome_func"));
                    StaticKeys.setSenha(resultset.getString("senha_func"));
                    StaticKeys.setId(resultset.getInt("id_func")+"");
                    byte[] imagem_byte = null;
                    imagem_byte = resultset.getBytes("foto_func");
                    if (imagem_byte != null) { 
                        Converter.ByteToImage(imagem_byte);
                    }
                    Erro = false;
                    con.desconectar();
                    return true;
            }    else {
                    StaticKeys.setNome("");
                    StaticKeys.setSenha("");
                    Erro = false;
                    con.desconectar();
                    return false;
            }
        } catch (SQLException ex) {
            Erro = true;
            con.desconectar();
            alert();
            return false;
        }
    }
    
    private String validaConta(String user) {
        con = new Conexao();
        ResultSet response = null;
        try {
            String query = "select * from funcionarios where nome_func = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, user);
            response = pst.executeQuery();
            
            if (response.next()) {
                return response.getString("nome_func");
            } else {
                return "";
            }
        } catch (SQLException ex) {
            Erro = true;
            return "";
        }
    }
    
    public boolean criarConta(Funcionario func) {
        String usuario = validaConta(func.getNome());
        if (Erro == true) {
            alert();
            return false;
        }
        if (usuario.equals("")) { 
        ResultSet res = null;
        try {
            String query = "insert into funcionarios (nome_func, senha_func) values (? , ?)";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, func.getNome()); 
            pst.setString(2, func.getSenha());
            pst.executeUpdate();
            con.desconectar();
            Erro = false;
            return true;
        } catch (SQLException ex) {
            Erro = true;
             con.desconectar();
             alert();
            return false;
        }
        } else {
            Erro = false;
            return false;
        }
    }
    
    public boolean updateUser(Funcionario func, byte[] imagem_byte) {
        String usuario = validaConta(func.getNome()); //Ainda fazer verifiação 
        if (usuario.equalsIgnoreCase(func.getNome()) && (!usuario.equals(StaticKeys.getNome()))) {
            return false;
        }
        if (Erro == true) {
            return false;
        }
        try {
           String query = "update funcionarios set nome_func = ?, senha_func = ?, foto_func = ? where id_func = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, func.getNome());
            pst.setString(2, func.getSenha());
            pst.setBytes(3, imagem_byte);
            pst.setInt(4, Integer.parseInt(StaticKeys.getId()));
            pst.executeUpdate();
            StaticKeys.setNome(func.getNome());
            StaticKeys.setSenha(func.getSenha());
            con.desconectar();
            Erro = false;
            return true;
        } catch (SQLException ex) {
            Erro = true;
             con.desconectar();
            alert();
            return false;
        }
        } 
    
    public boolean updateUser(Funcionario func) {
        String usuario = validaConta(func.getNome()); //Ainda fazer verifiação 
        if (usuario.equalsIgnoreCase(func.getNome()) && (!usuario.equals(StaticKeys.getNome()))) {
            return false;
        }
        if (Erro == true) {
            return false;
        }
        
        try {
           String query = "update funcionarios set nome_func = ?, senha_func = ? where id_func = ?";
           PreparedStatement pst = con.getConexao().prepareStatement(query);
           pst.setString(1, func.getNome());
           pst.setString(2, func.getSenha());
           pst.setInt(3, Integer.parseInt(StaticKeys.getId()));
           pst.executeUpdate();
           StaticKeys.setNome(func.getNome());
           StaticKeys.setSenha(func.getSenha());
            con.desconectar();
            Erro = false;
            return true;
        } catch (SQLException ex) {
            Erro = true;
             con.desconectar();
           alert();
            return false;
        }
        
        
        }
    
}
