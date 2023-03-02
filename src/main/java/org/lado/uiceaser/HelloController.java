package org.lado.uiceaser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import org.lado.uiceaser.shifrovshik.Shipher;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField;
    @FXML
    private SplitPane resultPane;
    @FXML
    private TextField keyInput;
    @FXML
    private TextField Keyblyad;
    @FXML
    private Label resultLabel;
    private String text;
    private String cipherText;
    File file;

    @FXML
    protected void onHelloButtonClick() {
        text = textField.getText();

        cipherText = Shipher.chipher(
                text,
                Integer.parseInt(keyInput.getText()));
        welcomeText.setText(cipherText);
        resultPane.setVisible(true);
    }

    public void decipherByFreeqan() throws IOException {
        String out = Shipher.deChipher(cipherText, text);
        resultLabel.setText(out);

        writeToFile(out);
    }

    public void deChipherToKey() throws IOException {
        String out = Shipher.deChipher(cipherText, Integer.parseInt(Keyblyad.getText()));
        resultLabel.setText(out);
        writeToFile(out);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                onHelloButtonClick();
            }
        });
        Keyblyad.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                try {
                    deChipherToKey();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void chooseFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileReader reader = new FileReader(file);

        int c;
        List<Integer> data = new ArrayList<>();
        while ((c = reader.read()) != -1) {
            if (c != -1) {
                data.add(c);
            }
        }
        char[] word = new char[data.size()];
        for (Integer i = 0; i < word.length; i++) {
            word[i] = (char) ((int) data.get(i));
        }

        text = valueOf(word);
        cipherText = Shipher.chipher(valueOf(word), Integer.parseInt(keyInput.getText()));
        welcomeText.setText(cipherText);
        resultPane.setVisible(true);
    }

    private static void writeToFile(String out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        writer.write(out);
        writer.close();
    }
}
