/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.Controllers;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import prjtcc.PrjTCC;
import prjtcc.model.Funcionario;
import prjtcc.model.LoginComands;

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

    @FXML
    private AnchorPane anchPane;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private StackPane stack;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(anchPane).play();
        stack.toBack();
        btnVoltar.setOnMousePressed((event) -> {
            
            PrjTCC.changeScreen("login");
        }
        );
        PrjTCC.addOnChangeListeners((newScreen, userData) -> {
            if (newScreen.equals("cadastro")) {
                new ZoomIn(anchPane).play();
                stack.toBack();
            }
        });
}
    
    
    private void showDialog(String title, String body, String contentButton) {
        JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text(title));
                content.setBody(new Text(body));
                JFXDialog dialog = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
                JFXButton button = new JFXButton(contentButton);
                button.setMaxHeight(70);
                button.setMaxWidth(70);
                button.setFont(Font.font(14));
                button.setMinWidth(40);
                button.setMinHeight(40);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stack.toBack();
                        dialog.close();
                    }
                });
                content.setActions(button);
                dialog.show();
    }
    
    @FXML
    public void cadastrarUsuario(ActionEvent event) {
        LoginComands login = new LoginComands();
        if (txtUsuario.getText().equals("") || txtSenha.getText().equals("") || txtSenhaConf.getText().equals("")) {
                return;
            }
            if (txtSenha.getText().equals(txtSenhaConf.getText())) {
               boolean res= login.criarConta(new Funcionario(txtUsuario.getText(), txtSenha.getText()));
               stack.toFront();
               txtUsuario.setText("");
               txtSenha.setText("");
               txtSenhaConf.setText("");
               if (res) { 
                showDialog("Cadastro", "Usuário Cadastrado com sucesso!", "Ok");
               } else if (login.Erro == true) {
                   showDialog("Erro", "Ocorreu um Erro, tente novamente!", "Ok");
               } else {
                   showDialog("Cadastro", "Usuário já existe!", "Ok");
               }
            }
    }
    
    @FXML
    public void btnClose(ActionEvent event) {
        PrjTCC.getStageThis().close();
    }

}
