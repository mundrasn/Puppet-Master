import ecs100.*;
/**
 * User Interface to command the cartoon characters
 *
 * @author S Mundra
 * @version 4/05/21
 */
public class PuppetMaster2
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
                (x <= cc1.getX () + cc1.getWidth()) &&
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
     * Set distance to walk
     */
    public void setDist(double dist){
        this.walkDist = dist; 
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
    // make a PuppetMaster2 object
    PuppetMaster2 pm = new PuppetMaster2(); 
    
    final int DISTMIN = 1; 
    final int DISMAX = 100; 
    final int DISTINIT = 5; 
    
    //setup the buttons, slider, textField, to call methods on the object
    UI.initialise();
    UI.setWindowSize(CANVASWIDTH, CANVASHEIGHT);
    
    // Buttons to switch selected character
    UI.addButton("Switch Character", pm::doSwitch);
    //UI.addButton("Casey", pm::doCasey);
    
    UI.addButton("Smile", ()->{pm.selectedCC.smile();});
    UI.addButton("Frown", ()->{pm.selectedCC.frown();});
    UI.addButton("Left", ()->{pm.selectedCC.lookLeft();});
    UI.addButton("Right", ()->{pm.selectedCC.lookRight();});
    UI.addTextField("Say", (String words)->{pm.selectedCC.speak(words);});
    UI.addTextField("Think", (String thoughts)->{pm.selectedCC.think(thoughts);});
    UI.addButton("Walk", ()->{pm.selectedCC.walk(pm.walkDist);});
    UI.addSlider("Distance", DISTMIN, DISMAX, DISTINIT, pm::setDist); //Make numbers constants 
    
    //Add standard button
    UI.addButton("Clear", pm::clear);
    UI.addButton("Quit", UI::quit);
    UI.setDivider(0.0);  //must come after(last)
   }
}
