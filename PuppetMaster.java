import ecs100.*;
/**
 * User Interface to command the cartoon characters
 *
 * @author S Mundra
 * @version 4/05/21
 */
public class PuppetMaster
{
    //fields to store values between events/method calls
    private static final int CANVASWIDTH = 1000; 
    private static final int CANVASHEIGHT = 500; 
    
    //create cartoon characters
    private CartoonCharacter cc1 = new CartoonCharacter(200, 100, "casey");
    private CartoonCharacter cc2 = new CartoonCharacter(400, 100, "alice");
    private CartoonCharacter cc3 = new CartoonCharacter(600, 100, "bob");
    
    
    private CartoonCharacter selectedCC = cc1;      // the selected one 
    
    private double walkDist = 20; 
    
    //methods to respond to the buttons, slider, textField'
    
    /**
     *  Check which object has been clicked on and selected
     */
    public void doMouse(String action, double x, double y){
        if (action.equals("clicked")){
            //check the location of x & y against the location of the character
            if ((x >= cc1.getX()) &&
                (x <= cc1.getX() + cc1.getWidth()) &&
                (y >= cc1.getY()) &&
                (y <= cc1.getY() + cc1.getHeight()))
                    selectedCC = cc1;
            else if ((x >= cc2.getX()) &&
                (x <= cc2.getX() + cc2.getWidth()) &&
                (y >= cc2.getY()) &&
                (y <= cc2.getY() + cc2.getHeight()))
                    selectedCC = cc2;
            else if ((x >= cc3.getX()) &&
                (x <= cc3.getX() + cc3.getWidth()) &&
                (y >= cc3.getY()) &&
                (y <= cc3.getY() + cc3.getHeight()))
                    selectedCC = cc3;
        }
    }

    /**
     * Switch Characters
     */
    public void doSwitch(){
        if (this.selectedCC == cc1){
            this.selectedCC = cc2;
        } else if (this.selectedCC == cc2) {
            this.selectedCC = cc3; 
        } else {
            this.selectedCC = cc1; 
        }
    }

    /**
     * Switch to Casey
     */
    //public void doCasey(){
      //  this.selectedCC = cc1;
    //}
    

    
    /**
     * Tell the cartoon character to smile
     */
    public void doSmile(){
        this.selectedCC.smile(); // find "cc" stored in 'this' class and make it smile
    }
    
    /**
     * Tell the cartoon character to frown
     */
    public void doFrown(){
        this.selectedCC.frown();
    }
    
    /**
     * Tell the cartoon character to look left
     */
    public void doLeft(){
        this.selectedCC.lookLeft();
    }
    
    /**
     * Tell the cartoon character to look right
     */
    public void doRight(){
        this.selectedCC.lookRight();
    }
    
    /**
     * Tell the cartoon character to say something
     */
    public void doSpeak(String words){
        this.selectedCC.speak(words);
    }
   
    /**
     * Tell the cartoon character to think something
     */
    public void doThink(String thoughts){
        this.selectedCC.speak(thoughts);
    }
    
    /**
     * Set distance to walk
     */
    public void setDist(double dist){
        this.walkDist = dist; 
    }
    
    /**
     * Tell the cartoon character to walk
     */
    public void doWalk(){
        this.selectedCC.walk(this.walkDist);
    }

        /**
     * Clear Graphics Pane
     */
    private void clear(){
        UI.clearPanes(); //Clear the graphics pane
        UI.setDivider(0.0); //Hide the text pane
    }
    
    
    //main setup GUI
    public static void main(String[] args){
    // make a PuppetMaster object
    PuppetMaster pm = new PuppetMaster(); 
    
    final int DISTMIN = 1; 
    final int DISMAX = 100; 
    final int DISTINIT = 5; 
    
    //setup the buttons, slider, textField, to call methods on the object
    UI.initialise();
    UI.setWindowSize(CANVASWIDTH, CANVASHEIGHT);
    
    // Buttons to switch selected character
    UI.addButton("Switch Character", pm::doSwitch);
    //UI.addButton("Casey", pm::doCasey);
    
    UI.addButton("Smile", pm::doSmile);
    UI.addButton("Frown", pm::doFrown);
    UI.addButton("Left", pm::doLeft);
    UI.addButton("Right", pm::doRight);
    UI.addButton("Walk", pm::doWalk);
    UI.addTextField("Say", pm::doSpeak);
    UI.addTextField("Think", pm::doThink);
    UI.addSlider("Distance", DISTMIN, DISMAX, DISTINIT, pm::setDist); //Make numbers constants 
    
    //Add standard button
    UI.addButton("Clear", pm::clear);
    UI.addButton("Quit", UI::quit);
    UI.setDivider(0.0);  //must come after(last)
   }
}
