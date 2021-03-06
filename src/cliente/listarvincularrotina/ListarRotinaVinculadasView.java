/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.listarvincularrotina;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class ListarRotinaVinculadasView extends javax.swing.JFrame {

    private ListarRotinaController controller;
    
    public ListarRotinaVinculadasView() {
        controller = new ListarRotinaController(this);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFundo = new javax.swing.JPanel();
        jPanelTopo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelBaixo = new javax.swing.JPanel();
        jButtonDesvincular = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jButtonExibir = new javax.swing.JButton();
        jPanelCentro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRotinas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelFundo.setBackground(new java.awt.Color(0, 51, 65));
        jPanelFundo.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(151, 158, 0)));
        jPanelFundo.setLayout(new java.awt.BorderLayout());

        jPanelTopo.setBackground(new java.awt.Color(0, 51, 64));
        jPanelTopo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(151, 158, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Rotinas Vinculadas");
        jLabel1.setToolTipText("");
        jPanelTopo.add(jLabel1);

        jPanelFundo.add(jPanelTopo, java.awt.BorderLayout.PAGE_START);

        jPanelBaixo.setBackground(new java.awt.Color(0, 51, 65));
        jPanelBaixo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(151, 158, 0)));

        jButtonDesvincular.setBackground(new java.awt.Color(151, 158, 0));
        jButtonDesvincular.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonDesvincular.setText("Desvincular");
        jPanelBaixo.add(jButtonDesvincular);

        jButtonEditar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonEditar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        jPanelBaixo.add(jButtonEditar);

        jButtonVoltar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonVoltar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        jPanelBaixo.add(jButtonVoltar);

        jButtonExibir.setBackground(new java.awt.Color(151, 158, 0));
        jButtonExibir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonExibir.setText("Exibir");
        jButtonExibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExibirActionPerformed(evt);
            }
        });
        jPanelBaixo.add(jButtonExibir);

        jPanelFundo.add(jPanelBaixo, java.awt.BorderLayout.PAGE_END);

        jPanelCentro.setBackground(new java.awt.Color(0, 51, 65));

        jTableRotinas.setBackground(new java.awt.Color(240, 240, 240));
        jTableRotinas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableRotinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Rotina", "Per??odo", "Hor??rio"
            }
        ));
        jScrollPane1.setViewportView(jTableRotinas);

        jPanelCentro.add(jScrollPane1);

        jPanelFundo.add(jPanelCentro, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelFundo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        controller.voltar();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        controller.editarRotina();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonExibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExibirActionPerformed
        controller.exibeItemSelecionado();
    }//GEN-LAST:event_jButtonExibirActionPerformed

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
            java.util.logging.Logger.getLogger(ListarRotinaVinculadasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarRotinaVinculadasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarRotinaVinculadasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarRotinaVinculadasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarRotinaVinculadasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDesvincular;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExibir;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelBaixo;
    private javax.swing.JPanel jPanelCentro;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelTopo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRotinas;
    // End of variables declaration//GEN-END:variables

    public void preencherVincularRotinas(List<VincularRotina> rotinasVinculadas) {       
        controller.preencheRotinasVinculadas(rotinasVinculadas);
    }
    
    public void setTableModel(TableModel tm){
        jTableRotinas.setModel(tm);
    }

    public JTable getjTableRotinas() {
        return jTableRotinas;
    }
    
    

}
