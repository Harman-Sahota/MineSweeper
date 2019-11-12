import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Assignment4Harman extends Application {
	public static void main (String [] args) {
		launch(args);
	}
	public void start(Stage theStage) {
		// creating new panes
		BorderPane userInterface = new BorderPane(); // borderpane for the user interface: top = header, bottom = grid
		BorderPane header = new BorderPane(); // borderpane for header: left = mines left, center = face button, right = score
		GridPane grid = new GridPane(); // gridpane for 5 x 5 tiles

		// creating face button
		Button face = new Button(); // creating a new button for the face
		face.setMinWidth(52); // setting the size of the button to be the same size as the icon for the face
		face.setMaxWidth(52); //
		face.setMinHeight(52); //
		face.setMaxHeight(52); //
		face.setGraphic(new ImageView(new Image("file:face-smile.png"))); // setting the graphic for the face

		//	designing the header
		header.setLeft(new Label("\n   010")); // mines left on the left side of the header
		header.setCenter(face); // face in center of the header
		header.setRight(new Label("\n000   ")); // score on right of the header

		// creating grid buttons under the class gridButton
		gridButton buttonArray[][] = new gridButton[5][5]; // initializing an array of 5 x 5 gridButtons known as buttonArray
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				buttonArray[i][j] = new gridButton(); // adding a new gridButton to each element of the array
				grid.add(buttonArray[i][j], i, j); // adding the buttons to the grid
			}
		}

		// initializing the state of all your buttons in the 5 x 5 grid according to the layout
		buttonArray[0][0].state = 1;
		buttonArray[1][0].state = 1;
		buttonArray[2][0].state = 1;
		buttonArray[3][0].state = 0;
		buttonArray[4][0].state = 0;
		buttonArray[5][0].state = 0;
		buttonArray[6][0].state = 0;
		buttonArray[7][0].state = 0;
		buttonArray[8][0].state = 0;
		buttonArray[0][1].state = 1;
		buttonArray[1][1].state = -1;
		buttonArray[2][1].state = 2;
		buttonArray[3][1].state = 1;
		buttonArray[4][1].state = 0;
		buttonArray[5][1].state = 0;
		buttonArray[6][1].state = 0;
		buttonArray[7][1].state = 1;
		buttonArray[8][1].state = -1;
		buttonArray[0][2].state = 2;
		buttonArray[1][2].state = -1;
		buttonArray[2][2].state = 3;
		buttonArray[3][2].state = -1;
		buttonArray[4][2].state = -1;
		buttonArray[5][2].state = 1;
		buttonArray[6][2].state = 0;
		buttonArray[7][2].state = 0;
		buttonArray[8][2].state = 0;
		buttonArray[0][3].state = 1;
		buttonArray[1][3].state = -1;
		buttonArray[2][3].state = 2;
		buttonArray[3][3].state = -1;
		buttonArray[4][3].state = 1;
		buttonArray[5][3].state = 0;
		buttonArray[6][3].state = 0;
		buttonArray[7][3].state = 1;
		buttonArray[8][3].state = -1;
		buttonArray[0][4].state = 2;
		buttonArray[1][4].state = -1;
		buttonArray[2][4].state = 1;
		buttonArray[3][4].state = 0;
		buttonArray[4][4].state = 0;
		buttonArray[5][4].state = 0;
		buttonArray[6][5].state = 1;
		buttonArray[7][4].state = -1;
		buttonArray[8][4].state = 2;
		buttonArray[0][5].state = -1;
		buttonArray[1][5].state = 2;
		buttonArray[2][5].state = 1;
		buttonArray[3][5].state = -1;
		buttonArray[4][5].state = 3;
		buttonArray[5][5].state = -1;
		buttonArray[6][5].state = 1;
		buttonArray[7][5].state = 0;
		buttonArray[8][5].state = 0;
		buttonArray[0][6].state = 0;
		buttonArray[1][6].state = 0;
		buttonArray[2][6].state = 1;
		buttonArray[3][6].state = 2;
		buttonArray[4][6].state = -1;
		buttonArray[5][6].state = 0;
		buttonArray[6][6].state = 1;
		buttonArray[7][6].state = -1;
		buttonArray[8][6].state = 2;
		buttonArray[0][7].state = -1;
		buttonArray[1][7].state = 1;
		buttonArray[2][7].state = 0;
		buttonArray[3][7].state = 0;
		buttonArray[4][7].state = 0;
		buttonArray[5][7].state = 1;
		buttonArray[6][7].state = -1;
		buttonArray[7][7].state = 2;
		buttonArray[8][7].state = -1;
		

		// setting actions for the face button (reset the game when clicked)
		face.setOnMouseClicked(e ->{
			face.setGraphic(new ImageView(new Image("file:face-smile.png"))); // resetting the graphic for the face to default
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					buttonArray[i][j].setGraphic(new ImageView(new Image("file:cover.png"))); // resetting the graphics for all buttons to default
					buttonArray[i][j].isUncovered = false; // resetting the parameter to make all buttons covered again
				}
			}
		});

		// programming grid button actions
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				int x = i; // x-coordinate of the gridButton which the for loop is currently at
				int y = j; // y-coordinate of the gridButton which the for loop is currently at
				buttonArray[x][y].setOnMouseClicked(e -> {
					buttonArray[x][y].isUncovered = true; // set boolean isUncovered to be true when it is clicked i.e. it is uncovered
					if (buttonArray[x][y].state == 0) { // if the button is supposed to show a blank
						buttonArray[x][y].setGraphic(new ImageView(new Image("file:0.png"))); // set graphic to 0 when clicked
					}else if (buttonArray[x][y].state == 1) { // if the button is supposed to show 1
						buttonArray[x][y].setGraphic(new ImageView(new Image("file:1.png"))); // set graphic to 1 when clicked
					}else if (buttonArray[x][y].state == 2) { // if the button is supposed to show 2
						buttonArray[x][y].setGraphic(new ImageView(new Image("file:2.png"))); // set graphic to 2 when clicked
					}else if (buttonArray[x][y].state == 3) { // if the button is supposed to show 3
						buttonArray[x][y].setGraphic(new ImageView(new Image("file:2.png"))); // set graphic to 3 when clicked
					}else if (buttonArray[x][y].state == -1) { // if the button is a mine
						buttonArray[x][y].setGraphic(new ImageView(new Image("file:mine-grey.png"))); // set graphic to mine when clicked
						face.setGraphic(new ImageView(new Image("file:face-dead.png"))); // set face graphic to dead when mine is clicked
					}

					// checking win conditions - setting default boolean win to be true and using the for loop to test for whether it should remain true
					boolean win = true; // creating a new boolean "win", win = true will change the face to sunglasses, win = false does nothing
					for(int a = 0; a < 5; a++) {
						for(int b = 0; b < 5; b++) {
							if(!buttonArray[a][b].isUncovered && buttonArray[a][b].state >= 0) { // if any non-mine buttons are still covered
								win = false; // setting boolean to false if any of the non-mine buttons are still covered
							}
						}
					}
					if(win) { // if no false booleans are discovered, you win the game
						face.setGraphic(new ImageView(new Image("file:face-win.png"))); // change graphic for face to win
					}
				});
			}
		}

		userInterface.setTop(header); // header on top of user interface
		userInterface.setBottom(grid); // grid below of user interface
		theStage.setScene(new Scene(userInterface)); // setting the scene to be the user interface
		theStage.show(); // displaying the stage
	}
}

class gridButton extends Button implements EventHandler<MouseEvent>{
	int state = 0; // if state = 0, button should show a blank; if state = 1, button should show a 1; if state = -1, button should show a mine
	boolean isUncovered = false; // if button has not been clicked yet, isUncovered = false; if button has been clicked, isUncovered = true

	public gridButton() { // constructor
		setMinWidth(32); // setting the default size of the buttons
		setMaxWidth(32); //
		setMinHeight(32); //
		setMaxHeight(32); //
		setGraphic(new ImageView(new Image("file:cover.png"))); // setting the default graphic for the grid buttons
	}

	public void handle(MouseEvent e) { // creating a void handle just to satisfy the EventHandler MouseEvent
	}

}
