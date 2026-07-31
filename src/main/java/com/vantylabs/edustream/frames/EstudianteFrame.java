package com.vantylabs.edustream.frames;

import com.vantylabs.edustream.Curso;
import com.vantylabs.edustream.Estudiante;
import com.vantylabs.edustream.Inscripcion;
import com.vantylabs.edustream.dao.CursoDAO;
import com.vantylabs.edustream.dao.InscripcionDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Anthony
 */

public class EstudianteFrame extends javax.swing.JFrame {

    // Variables globales
    private final Estudiante estudianteActual;
    private final CursoDAO cursoDAO = new CursoDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    private final DefaultTableModel modeloTabla;

    // Constructor
    public EstudianteFrame(Estudiante estudiante) {
        this.estudianteActual = estudiante;

        // Inicializando ventana visual
        initComponents(); 

        // Centralizando la ventana en la pantalla
        setLocationRelativeTo(null);
        setTitle("Edustream - Bienvenido " + estudiante.getNombre());

        // Configurando el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre del curso");
        modeloTabla.addColumn("ID Profesor");

        // Asignando el modelo a la tabla tablaCursos
        tblCursos.setModel(modeloTabla);

        // Cargando los datos de la base de datos
        cargarCursos();
    }

    private void cargarCursos() {
        try {
            modeloTabla.setRowCount(0); // vaciar tabla antes de recargar

            List<Curso> cursos = cursoDAO.obtenerTodos();

            for (Curso curso : cursos) {
                modeloTabla.addRow(new Object[]{
                        curso.getId(),
                        curso.getNombre(),
                        curso.getProfesor() != null ? curso.getProfesor().getId() : "N/A"
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los cursos: " + ex.getMessage());
        }
    }

    private void inscribirse() {
        int filaSeleccionada = tblCursos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso primero.");
            return;
        }

        int idCurso = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        // Armamos el objeto Curso solo con el id (es lo único que
        // InscripcionDAO.insertar necesita para guardar la relación)
        Curso curso = new Curso();
        curso.setId(idCurso);

        // Armamos el objeto Inscripcion completo
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudianteActual);
        inscripcion.setCurso(curso);
        inscripcion.setFecha(LocalDate.now()); // fecha de hoy

        try {
            boolean exito = inscripcionDAO.insertar(inscripcion);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Inscripción exitosa!");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo completar la inscripción.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al inscribirse: " + ex.getMessage());
        }
    }
    
    private void cerrarSesion() {
    // 1. Abre de nuevo la ventana de Login
    LoginFrame login = new LoginFrame();
    login.setVisible(true);

    // 2. Cierra la ventana actual del estudiante
    this.dispose();
    }
    
    // Variables de Design
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        ttl = new javax.swing.JLabel();
        pnlCursos = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        btnIncribirse = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField2.setText("jTextField2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ttl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ttl.setText("EDUSTREAM");

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCursos.setToolTipText("tablaCursos");
        pnlCursos.setViewportView(tblCursos);

        btnIncribirse.setText("Inscribirse");
        btnIncribirse.addActionListener(this::btnIncribirseActionPerformed);

        btnVolver.setText("<");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnIncribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnVolver)
                        .addGap(79, 79, 79)
                        .addComponent(ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver))
                .addGap(30, 30, 30)
                .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIncribirse)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnIncribirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncribirseActionPerformed
        inscribirse();
    }//GEN-LAST:event_btnIncribirseActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIncribirse;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JScrollPane pnlCursos;
    private javax.swing.JTable tblCursos;
    private javax.swing.JLabel ttl;
    // End of variables declaration//GEN-END:variables

}