package br.udesc.dsd.rmts.view;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class FileChooser extends JFileChooser {

    private IMeshController meshController;

    public FileChooser() throws FileNotFoundException {
        super.getFileSystemView().getHomeDirectory();

        meshController = MeshController.getInstance();

        int returnValue = super.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = super.getSelectedFile();
            meshController.setPathName(selectedFile);
        }
    }

}
