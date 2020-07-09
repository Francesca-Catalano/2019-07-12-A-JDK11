/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.db.Vicino;
import it.polito.tdp.food.model.Food;

import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    String s =	this.txtPorzioni.getText();
    if(s==null)
    {
    this.txtResult.appendText("Inserire un valore!\n");	
    return;
    }
    int n;
    try {
    	n=Integer.parseInt(s);
    }
    catch(NumberFormatException e)
    {
    	 this.txtResult.appendText("Formato non valio!\n");	
    	    return;
    }
    this.model.creaGrafo(n);
    this.boxFood.getItems().addAll( this.model.setV());
    
    this.txtResult.appendText("VERTEX : "+ this.model.setV().size()+"\n");	
    this.txtResult.appendText("EDGE : "+ this.model.setE().size()+"\n");
    this.txtResult.appendText("\n");

		  
		 
    
    	
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	
    	
        
    	   if( this.boxFood.getValue() == null)

    	   {
    		   this.txtResult.appendText("Scegliere un elemento!\n");	
    		    return;
    	   }
    	   
    	   
    			
    			  Food food = this.boxFood.getValue();
    			  
    			  this.txtResult.appendText("VICINI : \n");
    			  if(this.model.listVicini(food) == null)
    				  if(this.model.listVicini(food) == null)
    				  {
 						 this.txtResult.appendText("Errore lettura !\n"); 
 						 return;
 					  }
    					  
    					  if(this.model.listVicini(food).size() == 0)
    					  {
    						 this.txtResult.appendText("Nessun arco adiacente!\n"); 
    						 return;
    					  }
    					  
    			// System.out.print(this.model.listVicini(food));
    			  
    			  
    			  for(int i =0; i<5 && i<this.model.listVicini(food).size();i++) {
    			  this.txtResult.appendText(this.model.listVicini(food).get(i).toString()+"\n")
    			  ; 
    			  }
  
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    
    }
}
