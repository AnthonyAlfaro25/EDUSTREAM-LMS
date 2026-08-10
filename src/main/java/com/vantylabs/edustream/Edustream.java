package com.vantylabs.edustream;

import com.vantylabs.edustream.frames.LoginFrame;

public class Edustream {
    
    //metodo main que inicio el programa cargando el LoginFrame
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));   
    }
}