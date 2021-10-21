/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.salvarrotina;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class SalvarRotinaView extends javax.swing.JFrame {

    private SalvarRotinaController controller;
    
    public SalvarRotinaView() {
        controller = new SalvarRotinaController(this);
        initComponents();    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelFundo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jTextFieldNomeRotina = new javax.swing.JTextField();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelNomeRotina = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelDataLimite = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldDataLimite = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonDescricaoDescricao = new javax.swing.JButton();
        jButtonDescricaoNome = new javax.swing.JButton();
        jButtonDescricaoDataLimite = new javax.swing.JButton();
        jPanelTopo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(0, 54, 65));
        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(151, 158, 0)));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelFundo.setBackground(new java.awt.Color(0, 54, 65));
        jPanelFundo.setMinimumSize(new java.awt.Dimension(760, 340));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(455, 485));
        jPanelFundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextAreaDescricao.setLineWrap(true);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        jPanelFundo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 410, 240));

        jTextFieldNomeRotina.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldNomeRotina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeRotinaActionPerformed(evt);
            }
        });
        jPanelFundo.add(jTextFieldNomeRotina, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 410, -1));

        jLabelDescricao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDescricao.setForeground(new java.awt.Color(240, 240, 240));
        jLabelDescricao.setText("Descrição:");
        jLabelDescricao.setToolTipText("Opcional");
        jPanelFundo.add(jLabelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        jLabelNomeRotina.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNomeRotina.setForeground(new java.awt.Color(240, 240, 240));
        jLabelNomeRotina.setText("Nome:");
        jLabelNomeRotina.setToolTipText("Obrigatório");
        jPanelFundo.add(jLabelNomeRotina, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jButtonCadastrar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonCadastrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonCadastrar.setText("Salvar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        jButtonCancelar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, -1, -1));

        jLabelDataLimite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDataLimite.setForeground(new java.awt.Color(240, 240, 240));
        jLabelDataLimite.setText("Data Limite:");
        jLabelDataLimite.setToolTipText("Opcional");
        jPanelFundo.add(jLabelDataLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanelFundo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 430, 10));

        jTextFieldDataLimite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanelFundo.add(jTextFieldDataLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 410, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("*");
        jPanelFundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButtonDescricaoDescricao.setText("?");
        jButtonDescricaoDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescricaoDescricaoActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonDescricaoDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 40, 20));

        jButtonDescricaoNome.setText("?");
        jButtonDescricaoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescricaoNomeActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonDescricaoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 40, 20));

        jButtonDescricaoDataLimite.setText("?");
        jButtonDescricaoDataLimite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescricaoDataLimiteActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonDescricaoDataLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 40, 20));

        jPanelPrincipal.add(jPanelFundo, java.awt.BorderLayout.CENTER);

        jPanelTopo.setBackground(new java.awt.Color(0, 54, 65));
        jPanelTopo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(151, 158, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Salvar Rotina");
        jPanelTopo.add(jLabel1);

        jPanelPrincipal.add(jPanelTopo, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeRotinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeRotinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeRotinaActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        controller.cancelar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        controller.novaRotina();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonDescricaoDataLimiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescricaoDataLimiteActionPerformed
        JOptionPane.showMessageDialog(this, 
                  "Campo Opcional.\nDefine até quando uma rotina pode ser agendada, por exemplo; Caso um rotina seja, resgistrada com uma data\n"
                + "limite em 11/03/2025, ela só pode ser agendada antes de 11/03/2025. Também define o limite de agendamentos\n"
                + "automáticos, tomando a mesma data limite como exemplo, em um agendamento diário a rotina vai ser agendada\n"
                + "até 11/03/2025.\n"
                + "Caso não escolha nenhuma data, por padrão sera adicionada uma data limite 10 anos após o registro da rotina.", 
                "Descrição Data Limite", 1);
    }//GEN-LAST:event_jButtonDescricaoDataLimiteActionPerformed

    private void jButtonDescricaoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescricaoNomeActionPerformed
        JOptionPane.showMessageDialog(this, "Campo Obrigatório", "Descrição Nome Rotina", 1);
    }//GEN-LAST:event_jButtonDescricaoNomeActionPerformed

    private void jButtonDescricaoDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescricaoDescricaoActionPerformed
        JOptionPane.showMessageDialog(this, "Campo Opcional.\nCampo reservado para descrição da rotina\n"
                + "Caso não preencha campo, ficará vazio", "Descrição Descrição Rotina", 1);
    }//GEN-LAST:event_jButtonDescricaoDescricaoActionPerformed

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
            java.util.logging.Logger.getLogger(SalvarRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalvarRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalvarRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalvarRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalvarRotinaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDescricaoDataLimite;
    private javax.swing.JButton jButtonDescricaoDescricao;
    private javax.swing.JButton jButtonDescricaoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDataLimite;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelNomeRotina;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTopo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldDataLimite;
    private javax.swing.JTextField jTextFieldNomeRotina;
    // End of variables declaration//GEN-END:variables

    public JTextArea getjTextAreaDescricao() {
        return jTextAreaDescricao;
    }

    public JTextField getjTextFieldDataLimite() {
        return jTextFieldDataLimite;
    }

    public JTextField getjTextFieldNomeRotina() {
        return jTextFieldNomeRotina;
    }

    
    public void setUsuario(Usuario usuario){
        controller.setUsuario(usuario);
    }
    
    public Usuario getUsuario(){
        return controller.getUsuario();
    }
}
