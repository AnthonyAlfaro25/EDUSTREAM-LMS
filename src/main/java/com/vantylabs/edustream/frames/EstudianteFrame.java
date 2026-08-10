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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlCursos = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnIncribirse = new javax.swing.JButton();

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
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo 1.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("Mis cursos");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 30, 30));
        jLabel3.setText("Estos son los cursos en los que estás inscrito.");

        pnlCursos.setBackground(new java.awt.Color(232, 244, 252));
        pnlCursos.setForeground(new java.awt.Color(91, 181, 241));
        pnlCursos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        btnVolver.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnVolver.setText("← Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        btnIncribirse.setBackground(new java.awt.Color(45, 144, 237));
        btnIncribirse.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnIncribirse.setForeground(new java.awt.Color(255, 255, 255));
        btnIncribirse.setText("Inscribirse a un curso");
        btnIncribirse.addActionListener(this::btnIncribirseActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btnIncribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver))
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(62, 62, 62)
                .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnIncribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 154, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JScrollPane pnlCursos;
    private javax.swing.JTable tblCursos;
    // End of variables declaration//GEN-END:variables

}