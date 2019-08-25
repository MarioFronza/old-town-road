package br.udesc.dsd.rmts;

import javax.swing.SwingUtilities;

import br.udesc.dsd.rmts.view.ChoiceFrame;

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
