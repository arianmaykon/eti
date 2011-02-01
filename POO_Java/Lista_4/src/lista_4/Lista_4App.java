/*
 * Lista_4App.java
 */

package lista_4;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Lista_4App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new Lista_4View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Lista_4App
     */
    public static Lista_4App getApplication() {
        return Application.getInstance(Lista_4App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Lista_4App.class, args);
    }
}
