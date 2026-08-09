package com.vantylabs.edustream.frames;
import com.vantylabs.edustream.frames.AbrirCurso;
import com.vantylabs.edustream.Curso;
import com.vantylabs.edustream.Estudiante;
import com.vantylabs.edustream.Profesor;
import com.vantylabs.edustream.dao.CursoDAO;
import com.vantylabs.edustream.dao.InscripcionDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anthony
 */

public class ProfesorFrame extends javax.swing.JFrame {
    
    private final Profesor profesorActual;
    private final CursoDAO cursoDAO = new CursoDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();

    private DefaultTableModel modeloEstudiantes;
    private List<Curso> listaCursos; // Para guardar la lista de cursos descargados

    public ProfesorFrame(Profesor profesor) {
        this.profesorActual = profesor;

        // Inicializar componentes gráficos de NetBeans
        initComponents();

        // Configuraciones de ventana
        setLocationRelativeTo(null);
        setTitle("EduStream - Panel Docente: " + profesor.getNombre());

        // Preparar la tabla de estudiantes
        modeloEstudiantes = new DefaultTableModel(new String[]{"ID", "Nombre", "Email"}, 0);
        tblEstudiantes.setModel(modeloEstudiantes);

        // Cargar los cursos en el ComboBox
        cargarCursosEnCombo();
    }

    private void cargarCursosEnCombo() {
        try {

            listaCursos = cursoDAO.obtenerPorProfesor(profesorActual.getId());

            cmbCursos.removeAllItems();
            listaCursos = cursoDAO.obtenerPorProfesor(profesorActual.getId());
            
            if (listaCursos.isEmpty()) {
                cmbCursos.addItem("Sin cursos asignados");
                return;
            }

            // Agregar el nombre de cada curso al ComboBox
            for (Curso c : listaCursos) {
                cmbCursos.addItem(c.getNombre());
            }
            
            // Volver a escuchar el ComboBox
            cmbCursos.addActionListener(this::cmbCursosActionPerformed);
            
            // Cargar estudiantes del primer curso por defecto
            cargarEstudiantes();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar mis cursos: " + ex.getMessage(), 
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEstudiantes() {
        int indiceSeleccionado = cmbCursos.getSelectedIndex();

        // Si no hay cursos o el índice es inválido, vaciar tabla y salir
        if (indiceSeleccionado == -1 || listaCursos == null || listaCursos.isEmpty()) {
            modeloEstudiantes.setRowCount(0);
            return;
        }

        // Obtener el curso correspondiente según el índice seleccionado en el ComboBox
        Curso cursoSeleccionado = listaCursos.get(indiceSeleccionado);

        try {
            modeloEstudiantes.setRowCount(0); // Vaciar la tabla
            List<Estudiante> alumnos = inscripcionDAO.obtenerEstudiantesPorCurso(cursoSeleccionado.getId());

            for (Estudiante est : alumnos) {
                modeloEstudiantes.addRow(new Object[]{
                    est.getId(), 
                    est.getNombre(), 
                    est.getEmail()
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar estudiantes: " + ex.getMessage(), 
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbCursos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        btnAbrirCurso = new javax.swing.JButton();
        btnNuevoCurso = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnVolver.setText("← Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Seleccionar Curso:");

        cmbCursos.setEditable(true);
        cmbCursos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbCursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCursos.setToolTipText("");
        cmbCursos.addActionListener(this::cmbCursosActionPerformed);

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEstudiantes.setPreferredSize(new java.awt.Dimension(594, 203));
        jScrollPane1.setViewportView(tblEstudiantes);

        btnAbrirCurso.setBackground(new java.awt.Color(45, 144, 237));
        btnAbrirCurso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAbrirCurso.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirCurso.setText("Abrir Curso ");
        btnAbrirCurso.addActionListener(this::btnAbrirCursoActionPerformed);

        btnNuevoCurso.setBackground(new java.awt.Color(45, 144, 237));
        btnNuevoCurso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevoCurso.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoCurso.setText("Crear Curso");
        btnNuevoCurso.setBorderPainted(false);
        btnNuevoCurso.addActionListener(this::btnNuevoCursoActionPerformed);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo 1.png"))); // NOI18N
        jLabel2.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Elige un curso para abrirlo o crea uno nuevo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(btnAbrirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cmbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCursosActionPerformed
        cargarEstudiantes();
    }//GEN-LAST:event_cmbCursosActionPerformed

    private void btnNuevoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCursoActionPerformed
     String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del curso:");

if (nombre == null || nombre.trim().isEmpty()) {
    return;
}

String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción del curso:");

if (descripcion == null || descripcion.trim().isEmpty()) {
    return;
}

Curso curso = new Curso();
curso.setNombre(nombre);
curso.setDescripcion(descripcion);
curso.setProfesor(profesorActual);

try {

    if (cursoDAO.insertar(curso)) {

        JOptionPane.showMessageDialog(this, "Curso creado correctamente.");

        cargarCursosEnCombo();

    } else {

        JOptionPane.showMessageDialog(this, "No fue posible crear el curso.");

    }

} catch (SQLException ex) {

    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());

}  
    }//GEN-LAST:event_btnNuevoCursoActionPerformed

    private void btnAbrirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCursoActionPerformed
    try {

        int indice = cmbCursos.getSelectedIndex();

        if (indice == -1 || listaCursos == null || listaCursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso.");
            return;
        }

        Curso cursoSeleccionado = listaCursos.get(indice);

        AbrirCurso abrir = new AbrirCurso(cursoSeleccionado);
        abrir.setLocationRelativeTo(this);
        abrir.setVisible(true);

        this.dispose();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.toString());
    }
    }//GEN-LAST:event_btnAbrirCursoActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCurso;
    private javax.swing.JToggleButton btnNuevoCurso;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbCursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstudiantes;
    // End of variables declaration//GEN-END:variables
}

