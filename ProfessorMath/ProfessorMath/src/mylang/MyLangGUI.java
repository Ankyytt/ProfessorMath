package mylang;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MyLangGUI extends Application {

    private final Evaluator evaluator = new Evaluator();
    private boolean isDarkMode = false;

    // Fixed directory for storing files
    private final File fixedDirectory = new File(System.getProperty("user.home"), "MyLangFiles");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ProfessorMath Interpreter");
        primaryStage.getIcons().add(
                new javafx.scene.image.Image(getClass().getResourceAsStream("catIcon.jpg"))
        );

        // Create directory if it doesn't exist
        if (!fixedDirectory.exists()) {
            fixedDirectory.mkdirs();
        }

        // Input and Output areas
        TextArea inputArea = new TextArea();
        inputArea.setPromptText("Enter your MyLang code here...");
        inputArea.setWrapText(true);

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        // Buttons
        Button runButton = new Button("Run");
        Button clearButton = new Button("Clear");
        Button guideButton = new Button("Guide");
        Button themeToggle = new Button("Dark Mode");
        Button openFileButton = new Button("Open File");
        Button saveFileButton = new Button("Save File");

        HBox buttonBox = new HBox(10, runButton, clearButton, guideButton, themeToggle, openFileButton, saveFileButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-alignment: center;");

        VBox inputBox = new VBox(10, new Label("Input"), inputArea, buttonBox);
        inputBox.setPadding(new Insets(10));
        inputBox.setVgrow(inputArea, Priority.ALWAYS);

        VBox outputBox = new VBox(10, new Label("Output"), outputArea);
        outputBox.setPadding(new Insets(10));
        outputBox.setVgrow(outputArea, Priority.ALWAYS);

        HBox root = new HBox(10, inputBox, outputBox);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #f4f4f4;");
        HBox.setHgrow(inputBox, Priority.ALWAYS);
        HBox.setHgrow(outputBox, Priority.ALWAYS);

        // Run Button
        runButton.setOnAction(event -> {
            String input = inputArea.getText();
            StringBuilder output = new StringBuilder();

            try {
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();
                Parser parser = new Parser(tokens);

                while (parser.peek().getType() != TokenType.EOF) {
                    Node ast = parser.parse();
                    output.append("AST: ").append(ast).append("\n");
                    Object result = evaluator.evaluate(ast);
                    if (result != null) {
                        output.append("Output: ").append(result).append("\n");
                    }
                }
            } catch (Exception ex) {
                output.append("Error: ").append(ex.getMessage()).append("\n");
            }

            outputArea.setText(output.toString());
        });

        // Clear Button
        clearButton.setOnAction(event -> {
            inputArea.clear();
            outputArea.clear();
        });

        // Guide Button
        guideButton.setOnAction(event -> {
            Stage guideStage = new Stage();
            guideStage.setTitle("ProfessorMath Guide");
            guideStage.initModality(Modality.APPLICATION_MODAL);

            TextArea guideText = new TextArea();
            guideText.setEditable(false);
            guideText.setWrapText(true);
            guideText.setText("Welcome to ProfessorMath!\n\n" +
                    "Supported Commands:\n" +
                    "- let x = 10           (variable declaration)\n" +
                    "- show x + 5           (output expressions)\n" +
                    "- Matrix: let m = [[1, 2], [3, 4]]\n" +
                    "- Functions: sin(0.5), cos(1.2), log(5), sqrt(9)\n\n" +
                    "Notes:\n" +
                    "- Input multiple lines before pressing 'Run'.\n" +
                    "- Use variables and expressions freely in matrices.\n\n" +
                    "Enjoy exploring your own language!");

            VBox guideLayout = new VBox(10, guideText);
            guideLayout.setPadding(new Insets(10));
            Scene guideScene = new Scene(guideLayout, 500, 300);
            guideStage.setScene(guideScene);
            guideStage.showAndWait();
        });

        // Theme Toggle
        themeToggle.setOnAction(event -> {
            isDarkMode = !isDarkMode;
            if (isDarkMode) {
                root.setStyle("-fx-background-color: #2b2b2b;");
                inputArea.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white;");
                outputArea.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white;");
                themeToggle.setText("Light Mode");
            } else {
                root.setStyle("-fx-background-color: #f4f4f4;");
                inputArea.setStyle("");
                outputArea.setStyle("");
                themeToggle.setText("Dark Mode");
            }
        });

        // Warn about semicolon
        inputArea.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.trim().endsWith(";")) {
                    outputArea.appendText("\n⚠️ You typed a semicolon. MyLang doesn't require it.\n");
                }
            }
        });

        // Open File Button
        openFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.setInitialDirectory(fixedDirectory);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null && file.exists()) {
                try {
                    String content = Files.readString(file.toPath());
                    inputArea.setText(content);
                } catch (IOException e) {
                    showAlert("Error", "Could not read file: " + e.getMessage());
                }
            }
        });

        // Save File Button
        saveFileButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("mycode.txt");
            dialog.setTitle("Save File");
            dialog.setHeaderText("Enter filename to save:");
            dialog.setContentText("Filename:");

            dialog.showAndWait().ifPresent(filename -> {
                File file = new File(fixedDirectory, filename);
                try {
                    Files.writeString(file.toPath(), inputArea.getText());
                    showAlert("Success", "File saved successfully to: " + file.getAbsolutePath());
                } catch (IOException e) {
                    showAlert("Error", "Could not save file: " + e.getMessage());
                }
            });
        });

        Scene scene = new Scene(root, 950, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
