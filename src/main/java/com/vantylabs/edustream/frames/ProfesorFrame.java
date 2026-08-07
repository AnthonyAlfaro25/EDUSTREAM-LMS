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

        jLabel1 = new javax.swing.JLabel();
        cmbCursos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        btnNuevoCurso = new javax.swing.JToggleButton();
        btnVolver = new javax.swing.JButton();
        btnAbrirCurso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Seleccionar Curso:");

        cmbCursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        jScrollPane1.setViewportView(tblEstudiantes);

        btnNuevoCurso.setText("Crear Curso");
        btnNuevoCurso.addActionListener(this::btnNuevoCursoActionPerformed);

        btnVolver.setText("<");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        btnAbrirCurso.setText("Abrir Curso ");
        btnAbrirCurso.addActionListener(this::btnAbrirCursoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnAbrirCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevoCurso)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnVolver)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnVolver)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAbrirCurso))
                .addGap(18, 18, 18))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstudiantes;
    // End of variables declaration//GEN-END:variables
}

