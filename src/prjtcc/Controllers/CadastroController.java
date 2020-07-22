/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prjtcc.Cadastro;
import prjtcc.PrjTCC;
import prjtcc.model.LoginComands;

/**
 * FXML Controller class
 *
 * @author Jadir
 */
public class CadastroController implements Initializable {
    
    
    @FXML
    private Button btnClose;
    
     @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink btnVoltar;

    @FXML
    private PasswordField txtSenhaConf;
    
     @FXML
    private Button btnCadastrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVoltar.setOnMousePressed((event) -> {
            PrjTCC prj = new PrjTCC();
            Cadastro.getStageThis().close();
            try {
                prj.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    
}
    
    @FXML
    public void cadastrarUsuario(ActionEvent event) {
        LoginComands login = new LoginComands();
        if (txtUsuario.getText().equals("") || txtSenha.getText().equals("") || txtSenhaConf.getText().equals("")) {
                return;
            }
            if (txtSenha.getText().equals(txtSenhaConf.getText())) {
               boolean res= login.criarConta(txtUsuario.getText(), txtSenha.getText());
               if (res) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bem vindo...");
                alert.setHeaderText("Usuário cadastrado com sucesso!");
                alert.setContentText("Usuário cadastrado com sucesso!");
                alert.show();
               } else if (login.Erro == true) {
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Ocorreu algum erro, tente novamente");
                    alert.setContentText("Ocorreu algum erro, tente novamente");
                    alert.show();
               } else {
                   Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensagem");
                    alert.setHeaderText("Esse usuário já existe!");
                    alert.setContentText("Esse usuário já existe");
                    alert.show();
               }
            }
    }
    
    @FXML
    public void btnClose(ActionEvent event) {
        Cadastro.getStageThis().close();
    }

}
