/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Jadir
 */
public class Conexao {
    private String user = "sa";
    private String senha = "junior2003";
    private Connection conexao;
    
    String url = "jdbc:sqlserver://localhost\\\\MSSQLSERVER:1433;databaseName=PrjTCCteste;user="+user+";password="+senha+"";
    
    public Conexao() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            setConexao(DriverManager.getConnection(url));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText(ex.toString());
            alert.show();
        }
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public Connection getConexao() {
        return conexao;
    }
    
    public void desconectar() {
        try {
            this.getConexao().close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
