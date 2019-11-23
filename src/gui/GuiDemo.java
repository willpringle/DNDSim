package gui;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GuiDemo<toReturn> extends Application {
    private Controller theController;
    private BorderPane root;
    private DescriptionPopup descriptionPane;
    private Stage primaryStage;
	private String selectedString;

    /*a call to start replaces a call to the constructor for a JavaFX GUI*/
    @Override
    public void start(Stage assignedStage) {
        /* Initializing instance variables */
        theController = new Controller(this);
        primaryStage = assignedStage;
		
		//
		selectedString = "";
		
        /* Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = new DescriptionPopup(200, 300, "please click on a space");
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Dungeon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane setUpRoot() {
        BorderPane temp = new BorderPane();
        temp.setTop(new Label("The name or identifier of the thing below"));
        Node buttons = setButtonPanel();  //separate method for the left section
        temp.setRight(buttons);
        ObservableList<String> hydraList = FXCollections.observableArrayList(theController.getNameList());
        temp.setLeft(createListView(hydraList));
        GridPane room = new ChamberView(4,4);
        temp.setCenter(room);
        return temp;
    }

    private Node createListView(ObservableList<String> spaces){
        ListView temp = new ListView<String>(spaces);
        temp.setPrefWidth(150);
        temp.setPrefHeight(150);
		
		// what happens when the thing is selected
        temp.setOnMouseClicked((MouseEvent event)->{
            System.out.println("clicked on " + temp.getSelectionModel().getSelectedItem());
			selectedString = (String) temp.getSelectionModel().getSelectedItem();
			
			// call the controller to ask for what chamber that is and its description
			theController.setCurrentSpace(selectedString);
			descriptionPane.setText(theController.getNewDescription());
        });

        return temp;
    }

    private Node setButtonPanel() {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        VBox temp = new VBox();
        /*This button listener is an example of a button changing something
        in the controller but nothing happening in the view */

        Button firstButton = createButton("Hello world", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");
        firstButton.setOnAction((ActionEvent event) -> {
            theController.reactToButton(selectedString);
        });
        temp.getChildren().add(firstButton);

        
        Button showButton = createButton("Show Description", "-fx-background-color: #FFFFFF; ");
        showButton.setOnAction((ActionEvent event) -> {
            descriptionPane.getPopup().show(primaryStage);
        });
        temp.getChildren().add(showButton);
		
        /*this button listener is an example of getting data from the controller */
        Button hideButton = createButton("Hide Description", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            descriptionPane.getPopup().hide();
//            changeDescriptionText(theController.getNewDescription());
        });
        temp.getChildren().add(hideButton);
        return temp;

    }


    /*generic button creation method ensure that all buttons will have a
    similar style and means that the style only need to be in one place
     */
    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        btn.setStyle("");
        return btn;
    }

	// ? what this do?
    private void changeDescriptionText(String text) {
        ObservableList<Node> list = descriptionPane.getPopup().getContent();
        for (Node t : list) {
            if (t instanceof TextArea) {
                TextArea temp = (TextArea) t;
                temp.setText(text);
            }

        }

    }

	/**
	 * Weird second main method that I dont fully understand how it works.
	 * @param agrs, the arguments for the program.
	 */
    public static void main(String[] args) {
        launch(args);
    }

}
