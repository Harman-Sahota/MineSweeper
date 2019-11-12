//worked with shaya selincourt , jivraj grewal , jade molen ,
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.text.DecimalFormat;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MineSweeper extends Application {
	
   public DigitalClock clock = new DigitalClock();
	public Button[][] buttons = new Button[8][8];
	static int tileidx = 0;
	static int i=0;
	static int k=0;
	public HBox hbox = new HBox();
	public VBox vbox=new VBox();
	public GridPane pane = new GridPane();
	public Button button = new Button();
	gButton g = new gButton();
	public Scene scene;
	public boolean running = false;
	public int state=0;
	public DecimalFormat df = new DecimalFormat("00");
	public boolean press=false;
	public long last = System.nanoTime();
	public double delta =0;
	public double ns = 1000000000.0/2;
	public int count =0;public int mines = 10;
	public long now = System.nanoTime();
	public int[][] tiles = new int[10][3];
	public Button n = new Button();
	public static void main(String[] args) {
		launch(args);
		}
	public void start(Stage theStage) throws Exception {
		
		Scanner d = new Scanner(System.in);
	    vbox.setPrefWidth(40);
		vbox.setPrefHeight(40);
		n.setScaleY(1.5);
		n.setScaleX(2);
runClock();
		button.setMinWidth(vbox.getPrefWidth());
		button.setMinHeight(vbox.getPrefHeight());
		
		pane.setPadding(new Insets(12,12,12,12));
		hbox.setPadding(new Insets(12,12,12,12));
		pane.setAlignment(Pos.CENTER);
		

		n.setStyle("-fx-background-color:Black");
		hbox.getChildren().addAll(clock);
		
	    hbox.setAlignment(Pos.TOP_RIGHT);
	    hbox.setSpacing(55);
	    hbox.getChildren().add(button);
	   
		hbox.setAlignment(Pos.TOP_CENTER);
	 game();
		n.setText(""+mines);
		n.setTextFill(Color.RED);
		 hbox.setSpacing(55);
		   hbox.getChildren().add(n);
		    hbox.setAlignment(Pos.TOP_LEFT);
		 theStage.setScene(scene);
		theStage.show(); 
		
		
}  
	
	// counts mines around the input tile
   
    
    // adds distance numbers to the board
   
  
	private void runClock() {
		running=true;
		new Thread() {
			public void run() {
			
			while(running) {
				
				count =(count +1)%60;
				
				delta += (now+last)/ns;
				last=now;
				
				while(delta>=1) {
					System.out.println(count);
					
					clock.refreshDigits(df.format(count));
					delta--;
				
				}}}
			}.start();
		
	}
	public void game()
	{
		
         Image image = new Image(getClass().getResourceAsStream("cover2.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image flag = new Image(getClass().getResourceAsStream("flag.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image image3 = new Image(getClass().getResourceAsStream("mine-red.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image image4 = new Image(getClass().getResourceAsStream("face-dead.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image one= new Image(getClass().getResourceAsStream("1.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image zero= new Image(getClass().getResourceAsStream("0.png"),vbox.getPrefWidth(),vbox.getPrefHeight(),false,false);
		 Image image2 = new Image(getClass().getResourceAsStream("face-smile.png"),(vbox.getPrefWidth()+20),(vbox.getPrefHeight()+20),false,false);
		 Image image5 = new Image(getClass().getResourceAsStream("face-O.png"),(vbox.getPrefWidth()+20),(vbox.getPrefHeight()+20),false,false);
		int bombs=1;
		
         int[] mineWidth= new int[16];
		int[] mineHeight= new int[16];
    
	
		
		 for( i=0;i<8;i++) {
			 
				for(k=0;k<8;k++){
					
					int mine_width = (int) (0+Math.random()*8);
					int mine_height = (int) (0+Math.random()*8);
					
				    mineWidth[i]= mine_width;
					mineHeight[i]= mine_height;
					
					Button  b= new Button();
			
					buttons[i][k] = b;
					
					button.setGraphic(new ImageView(image2));
				   b.setMinWidth(40);
					b.setMinHeight(40);
					b.setGraphic(new ImageView(image));
					System.out.println(mine_width+" , "+mine_height);
					
					if(bombs<=10) {
						
				if(bombs<=mineWidth[i]&&bombs<=mineHeight[i]) {
					tiles[tileidx++] = new int[]{i,k};
				
			     	b.setOnAction(new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e){
							 state=-1;
				         b.setGraphic(new ImageView(image3));
				      
				        	button.setGraphic(new ImageView(image4));
				        	
							System.out.println(state);
							
							
							
							if(state==-1) {
							--mines;
							n.setText(""+mines);
							b.setDisable(true);
							
							  if(b.isDisable()==true) {
						     b.setMouseTransparent(true);
						    
						     
						     running=false;
						    }
							  
						    }
							}});
			     	
                     bombs++;
					}}
					
				b.setOnMouseClicked(new EventHandler<MouseEvent>(){
						
						public void handle(MouseEvent e){
							
							if(e.getButton() == MouseButton.SECONDARY) { 
								
							b.setGraphic(new ImageView(flag));
							--mines;
							n.setText(""+mines);
							}
					}});
				
          	pane.add(b,i,k);
					 
					}} 
		 
	         	button.setOnMouseClicked(e ->{
				button.setGraphic(new ImageView(image2)); // resetting the graphic for the face to default
				for(int i = 0; i <8; i++) {
					for(int j = 0; j < 8; j++) {
						button.setMinWidth(40);
						button.setMinHeight(40);
						buttons[i][j].setDisable(false);
						buttons[i][j].setGraphic(new ImageView(image)); // resetting the graphics for all buttons to default
						 last = System.nanoTime();
						 delta =0;
						 ns = 1000000000.0/2;
						 count =0;
						 now = System.nanoTime();
						 mines=10;
						 n.setText(""+mines);
						
				
				}}
			});

		vbox.getChildren().addAll(hbox,pane);
		vbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color:Skyblue");
		vbox.setStyle( "-fx-padding: 22;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 15;" +
                "-fx-border-insets: 20;" + 
                "-fx-border-radius: 20;" + 
                "-fx-border-color: LightGrey;");
	
     scene = new Scene(vbox);
	}}
	
	class gButton extends Button implements EventHandler<MouseEvent>{
		
		 boolean state = false; 
		 boolean isUncovered = false; 
		 Button buttons[][];
       public void handle(MouseEvent e) { 
    	 
		if(e.getButton() == MouseButton.SECONDARY) { 
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
			buttons[i][j].setGraphic(new ImageView("file:src/flag.png"));
			state=true;
		
		}
		// creating a void handle just to satisfy the EventHandler MouseEvent
			}}}
}
	
	