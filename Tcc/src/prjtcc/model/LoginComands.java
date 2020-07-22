/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.imageio.ImageIO;
import prjtcc.classes.Converter;
import prjtcc.classes.Funcionario;

/**
 *
 * @author Jadir
 */
public class LoginComands {
    
    Conexao con;
    Statement statement;
    public boolean Erro = false;
    
    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ocorreu algum erro, tente novamente!");
            alert.show();
    }
    
    public boolean session(String user, String pass) throws IOException {
        ResultSet resultset = null;
        con = new Conexao();
        try {
            String query = "select * from funcionarios where nome_func = ? and senha_func = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);
            resultset = pst.executeQuery();
            if (resultset.next()) {
                    Funcionario.setNome(resultset.getString("nome_func"));
                    Funcionario.setSenha(resultset.getString("senha_func"));
                    Funcionario.setId(resultset.getInt("id_func"));
                    byte[] imagem_byte = null;
                    imagem_byte = resultset.getBytes("foto_func");
                    if (imagem_byte != null) { 
                        Converter.ByteToImage(imagem_byte);
                    }
                    Erro = false;
                    con.desconectar();
                    return true;
            }    else {
                    Funcionario.setNome("");
                    Funcionario.setSenha("");
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
        ResultSet res = null;
        Connection state = con.getConexao();
        try {
            statement = state.createStatement();
            String query = "select * from funcionarios where nome_func = '"+user+"'";
            res = statement.executeQuery(query);
            
            if (res.next()) {
                return res.getString("nome_func");
            } else {
                return "";
            }
        } catch (SQLException ex) {
            Erro = true;
            return "";
        }
    }
    
    public boolean criarConta(String user, String pass) {
        String usuario = validaConta(user);
        if (Erro == true) {
            alert();
            return false;
        }
        if (usuario.equals("")) { 
        ResultSet res = null;
        try {
            String query = "insert into funcionarios (nome_func, senha_func) values (? , ?)";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, user); 
            pst.setString(2, pass);
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
    
    public boolean updateUser(String user, String pass, byte[] imagem_byte) {
        String usuario = validaConta(user); //Ainda fazer verifiação 
        if (Erro == true) {
            return false;
        }
        try {
           String query = "update funcionarios set nome_func = ?, senha_func = ?, foto_func = ? where id_func = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.setBytes(3, imagem_byte);
            pst.setInt(4, Funcionario.getId());
            pst.executeUpdate();
            Funcionario.setNome(user);
            Funcionario.setSenha(pass);
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
    
    public boolean updateUser(String user, String pass) {
        String usuario = validaConta(user);//Ainda fazer verifiação 
        if (Erro == true) {
            return false;
        }
        
        try {
           String query = "update funcionarios set nome_func = ?, senha_func = ? where id_func = ?";
           PreparedStatement pst = con.getConexao().prepareStatement(query);
           pst.setString(1, user);
           pst.setString(2, pass);
           pst.setInt(3, Funcionario.getId());
           pst.executeUpdate();
           Funcionario.setNome(user);
            Funcionario.setSenha(pass);
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
