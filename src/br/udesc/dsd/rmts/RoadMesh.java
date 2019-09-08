package br.udesc.dsd.rmts;

import javax.swing.SwingUtilities;

import br.udesc.dsd.rmts.view.ChoiceFrame;

/**
 * Main class, the applications starts here
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadMesh {
    public static void main(String[] args) {
    	 SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 new ChoiceFrame();
             }
         });
    }
}
