package prjtcc.classes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import prjtcc.PrjTCC;

public class Dialog {
    
    public static void showDialog(String title, String body, String contentButton, boolean principal, StackPane stack) {
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
                button.setStyle("-fx-background-color: #C86EE7");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stack.toBack();
                        if (principal) {
                            PrjTCC.changeScreen("principal", StaticKeys.getNome());
                        }
                        dialog.close();
                    }
                });
                content.setActions(button);
                stack.toFront();
                dialog.show();
    }
    
}
