/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alumno
 */
public class Panel extends javax.swing.JFrame {

    DefaultTableModel modelo1;
    DefaultTableModel modelo2;
    DefaultTableModel modelo3;
    DefaultTableModel modelo4;

    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
        //setSize(863, 295);
        setLocationRelativeTo(null);
        setTitle("Estadisticas");

        //String[] Datos = new String[10];
        String[] Datos = {"Ejec 1", "Ejec 2", "Ejec 3", "Ejec 4", "Ejec 5", "Mejor", "Peor", "Media", "Desv."};
        String[] d = new String[3];
        d[1] = d[2] = "";
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("");
        modelo1.addColumn("Z");
        modelo1.addColumn("Tiempo");
        modelo1.addColumn("Z");
        modelo1.addColumn("Tiempo");
        modelo1.addColumn("Z");
        modelo1.addColumn("Tiempo");
        modelo1.addColumn("Z");
        modelo1.addColumn("Tiempo");
        modelo1.addColumn("Z");
        modelo1.addColumn("Tiempo");
        this.tabla1.setModel(modelo1);

        modelo2 = new DefaultTableModel();
        modelo2.addColumn("");
        modelo2.addColumn("Z");
        modelo2.addColumn("Tiempo");
        modelo2.addColumn("Z");
        modelo2.addColumn("Tiempo");
        modelo2.addColumn("Z");
        modelo2.addColumn("Tiempo");
        modelo2.addColumn("Z");
        modelo2.addColumn("Tiempo");
        modelo2.addColumn("Z");
        modelo2.addColumn("Tiempo");
        this.tabla2.setModel(modelo2);

        modelo3 = new DefaultTableModel();
        modelo3.addColumn("");
        modelo3.addColumn("Z");
        modelo3.addColumn("Tiempo");
        modelo3.addColumn("Z");
        modelo3.addColumn("Tiempo");
        modelo3.addColumn("Z");
        modelo3.addColumn("Tiempo");
        modelo3.addColumn("Z");
        modelo3.addColumn("Tiempo");
        modelo3.addColumn("Z");
        modelo3.addColumn("Tiempo");
        this.tabla3.setModel(modelo3);

        modelo4 = new DefaultTableModel();
        modelo4.addColumn("");
        modelo4.addColumn("Z");
        modelo4.addColumn("Tiempo");
        modelo4.addColumn("Z");
        modelo4.addColumn("Tiempo");
        modelo4.addColumn("Z");
        modelo4.addColumn("Tiempo");
        modelo4.addColumn("Z");
        modelo4.addColumn("Tiempo");
        modelo4.addColumn("Z");
        modelo4.addColumn("Tiempo");
        this.tabla4.setModel(modelo4);

        for (int i = 0; i < 9; i++) {
            d[0] = Datos[i];
            modelo1.addRow(d);
            modelo2.addRow(d);
            modelo3.addRow(d);
            modelo4.addRow(d);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabla4 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 214748));

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        PanelPrincipal.addTab("Greedy", jPanel1);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tabla2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        PanelPrincipal.addTab("Busqueda L.", jPanel2);

        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tabla3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        PanelPrincipal.addTab("Busqueda T.", jPanel3);

        tabla4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tabla4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        PanelPrincipal.addTab("Grasp", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelPrincipal))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }

    //String ficheros[] = {"scpe1.txt", "scp41.txt", "scpd1.txt", "scpnrf1.txt", "scpnrh4.txt"}; //El ultimo fichero esta dañado
    public void insertaDatos(String fich, int coste, long tiempo, int i, int alg) {
        if (null != fich) switch (fich) {
            case "scpe1.txt":
                switch (alg) {
                    case 1:
                        modelo1.setValueAt(coste, i, 1);
                        modelo1.setValueAt(tiempo, i, 2);
                        break;
                    case 2:
                        modelo2.setValueAt(coste, i, 1);
                        modelo2.setValueAt(tiempo, i, 2);
                        break;
                    case 3:
                        modelo3.setValueAt(coste, i, 1);
                        modelo3.setValueAt(tiempo, i, 2);
                        break;
                    case 4:
                        modelo4.setValueAt(coste, i, 1);
                        modelo4.setValueAt(tiempo, i, 2);
                        break;
                }   break;
            case "scp41.txt":
                switch (alg) {
                    case 1:
                        modelo1.setValueAt(coste, i, 3);
                        modelo1.setValueAt(tiempo, i, 4);
                        break;
                    case 2:
                        modelo2.setValueAt(coste, i, 3);
                        modelo2.setValueAt(tiempo, i, 4);
                        break;
                    case 3:
                        modelo3.setValueAt(coste, i, 3);
                        modelo3.setValueAt(tiempo, i, 4);
                        break;
                    case 4:
                        modelo4.setValueAt(coste, i, 3);
                        modelo4.setValueAt(tiempo, i, 4);
                        break;
                }   break;
            case "scpd1.txt":
                switch (alg) {
                    case 1:
                        modelo1.setValueAt(coste, i, 5);
                        modelo1.setValueAt(tiempo, i, 6);
                        break;
                    case 2:
                        modelo2.setValueAt(coste, i, 5);
                        modelo2.setValueAt(tiempo, i, 6);
                        break;
                    case 3:
                        modelo3.setValueAt(coste, i, 5);
                        modelo3.setValueAt(tiempo, i, 6);
                        break;
                    case 4:
                        modelo4.setValueAt(coste, i, 5);
                        modelo4.setValueAt(tiempo, i, 6);
                        break;
                }   break;
            case "scpa1.txt":
                switch (alg) {
                    case 1:
                        modelo1.setValueAt(coste, i, 7);
                        modelo1.setValueAt(tiempo, i, 8);
                        break;
                    case 2:
                        modelo2.setValueAt(coste, i, 7);
                        modelo2.setValueAt(tiempo, i, 8);
                        break;
                    case 3:
                        modelo3.setValueAt(coste, i, 7);
                        modelo3.setValueAt(tiempo, i, 8);
                        break;
                    case 4:
                        modelo4.setValueAt(coste, i, 7);
                        modelo4.setValueAt(tiempo, i, 8);
                        break;
                }   break;
            default:
                break;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane PanelPrincipal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tabla3;
    private javax.swing.JTable tabla4;
    // End of variables declaration//GEN-END:variables
}
