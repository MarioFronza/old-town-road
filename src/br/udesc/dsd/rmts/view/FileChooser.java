package br.udesc.dsd.rmts.view;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;

import javax.swing.*;
import java.io.File;

public class FileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;
	private IMeshController meshController;

    public FileChooser() {
        try {
        	File workingDirectory = new File(System.getProperty("user.dir"));
        	super.setCurrentDirectory(workingDirectory);

            this.meshController = MeshController.getInstance();
            this.meshController.setPathName(null);

            int returnValue = super.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = super.getSelectedFile();
                meshController.setPathName(selectedFile);
            }
        } catch(Error err) {
        	System.out.println("Hey, something went wrong when trying to get the file:" + err);
        }
    }
    
    public String getChoosedFileName() {
    	if (this.meshController.getFile() == null) {
			return "Please, select a file";
		} else {
			return super.getSelectedFile().getName();
		}
    }

}
