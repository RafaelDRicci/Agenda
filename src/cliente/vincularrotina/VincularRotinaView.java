/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaView extends javax.swing.JFrame {

    private VincularRotinaController controller;
    
    public VincularRotinaView() {
        controller = new VincularRotinaController(this);
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

        buttonGroupReagendavel = new javax.swing.ButtonGroup();
        buttonGroupPrioritario = new javax.swing.ButtonGroup();
        buttonGrouHoraFixa = new javax.swing.ButtonGroup();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelFundo = new javax.swing.JPanel();
        jLabelRotina = new javax.swing.JLabel();
        jLabelUnidade = new javax.swing.JLabel();
        jComboBoxPeriodo = new javax.swing.JComboBox<>();
        jComboBoxRotina = new javax.swing.JComboBox<>();
        jLabelFuncionario = new javax.swing.JLabel();
        jComboBoxFuncionario = new javax.swing.JComboBox<>();
        jComboBoxUnidade = new javax.swing.JComboBox<>();
        jLabelHora = new javax.swing.JLabel();
        jLabelObservacao = new javax.swing.JLabel();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelPeriodo = new javax.swing.JLabel();
        jButtonApagaHora = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPaneObeservacao = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabelDias = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jButtonApagaMes = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDia = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxDiaSemana = new javax.swing.JComboBox<>();
        jButtonApagaDiaSemana = new javax.swing.JButton();
        jLabelDiasUtil = new javax.swing.JLabel();
        jComboBoxDiaUtil = new javax.swing.JComboBox<>();
        jButtonApagaDiaUtil = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListDiaSemana = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListMes = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListHorario = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListDiaUtil = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListDia = new javax.swing.JList<>();
        jButtonApagaDia = new javax.swing.JButton();
        jComboBoxHora = new javax.swing.JComboBox<>();
        jLabelAno = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListAno = new javax.swing.JList<>();
        jComboBoxAno = new javax.swing.JComboBox<>();
        jButtonApagaAno = new javax.swing.JButton();
        jPanelTopo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(0, 51, 65));
        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(151, 158, 0)));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelFundo.setBackground(new java.awt.Color(0, 51, 65));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(1000, 550));
        jPanelFundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRotina.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRotina.setForeground(new java.awt.Color(240, 240, 240));
        jLabelRotina.setText("Rotina:");
        jPanelFundo.add(jLabelRotina, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabelUnidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelUnidade.setForeground(new java.awt.Color(240, 240, 240));
        jLabelUnidade.setText("Unidade:");
        jPanelFundo.add(jLabelUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jComboBoxPeriodo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Única", "Diário", "Semanal", "Dia Útil/Mês", "Dia/Mês", "Anual" }));
        jComboBoxPeriodo.setToolTipText("");
        jComboBoxPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxPeriodoItemStateChanged(evt);
            }
        });
        jComboBoxPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPeriodoActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 270, -1));

        jComboBoxRotina.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanelFundo.add(jComboBoxRotina, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 270, -1));

        jLabelFuncionario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFuncionario.setForeground(new java.awt.Color(240, 240, 240));
        jLabelFuncionario.setText("Funcionário:");
        jPanelFundo.add(jLabelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jComboBoxFuncionario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanelFundo.add(jComboBoxFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 270, -1));

        jComboBoxUnidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanelFundo.add(jComboBoxUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 270, -1));

        jLabelHora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelHora.setForeground(new java.awt.Color(240, 240, 240));
        jLabelHora.setText("Horarios:");
        jPanelFundo.add(jLabelHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 270, -1, -1));

        jLabelObservacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelObservacao.setForeground(new java.awt.Color(240, 240, 240));
        jLabelObservacao.setText("Observação:");
        jPanelFundo.add(jLabelObservacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jButtonRegistrar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, -1, -1));

        jButtonCancelar.setBackground(new java.awt.Color(151, 158, 0));
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, -1, -1));

        jLabelPeriodo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPeriodo.setForeground(new java.awt.Color(240, 240, 240));
        jLabelPeriodo.setText("Período:");
        jPanelFundo.add(jLabelPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jButtonApagaHora.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonApagaHora.setText("<--");
        jButtonApagaHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaHoraActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 320, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(151, 158, 0));
        jSeparator1.setForeground(new java.awt.Color(240, 240, 240));
        jPanelFundo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 1120, 10));

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaObservacao.setRows(5);
        jScrollPaneObeservacao.setViewportView(jTextAreaObservacao);

        jPanelFundo.add(jScrollPaneObeservacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 610, 180));

        jLabelDias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDias.setForeground(new java.awt.Color(240, 240, 240));
        jLabelDias.setText("Dia(s):");
        jPanelFundo.add(jLabelDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jComboBoxMes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 80, -1));

        jButtonApagaMes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonApagaMes.setText("<--");
        jButtonApagaMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaMesActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 50, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Mês(es):");
        jPanelFundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, 20));

        jComboBoxDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 60, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Dia(s) da Semana:");
        jPanelFundo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, 20));

        jComboBoxDiaSemana.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBoxDiaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" }));
        jComboBoxDiaSemana.setEnabled(false);
        jComboBoxDiaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaSemanaActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxDiaSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 80, 20));

        jButtonApagaDiaSemana.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonApagaDiaSemana.setText("<--");
        jButtonApagaDiaSemana.setEnabled(false);
        jButtonApagaDiaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaDiaSemanaActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaDiaSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, -1, -1));

        jLabelDiasUtil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDiasUtil.setForeground(new java.awt.Color(240, 240, 240));
        jLabelDiasUtil.setText("Dia(s) Útil(eis):");
        jPanelFundo.add(jLabelDiasUtil, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 270, -1, -1));

        jComboBoxDiaUtil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxDiaUtil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1°", "2°", "3°", "4°", "5°", "6°", "7°", "8°", "9°", "10°", "11°", "12°", "13°", "14°", "15°", "16°", "17°", "18°", "19°", "20°", "21°", "22°", "23°" }));
        jComboBoxDiaUtil.setEnabled(false);
        jComboBoxDiaUtil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaUtilActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxDiaUtil, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 290, 60, -1));

        jButtonApagaDiaUtil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonApagaDiaUtil.setText("<--");
        jButtonApagaDiaUtil.setEnabled(false);
        jButtonApagaDiaUtil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaDiaUtilActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaDiaUtil, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(240, 240, 240));
        jCheckBox1.setText("Reagendável");
        jPanelFundo.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 130, -1));

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(240, 240, 240));
        jCheckBox2.setText("Prioritário");
        jPanelFundo.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 130, -1));

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(240, 240, 240));
        jCheckBox3.setText("Horário Fixo");
        jPanelFundo.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 130, -1));

        jListDiaSemana.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListDiaSemana.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(jListDiaSemana);

        jPanelFundo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 100, 190));

        jListMes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListMes.setModel(new DefaultListModel());
        jScrollPane2.setViewportView(jListMes);

        jPanelFundo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 100, 190));

        jListHorario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListHorario.setModel(new DefaultListModel());
        jScrollPane3.setViewportView(jListHorario);

        jPanelFundo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 290, 100, 190));

        jListDiaUtil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListDiaUtil.setModel(new DefaultListModel());
        jScrollPane4.setViewportView(jListDiaUtil);

        jPanelFundo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, 100, 190));

        jListDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListDia.setModel(new DefaultListModel());
        jScrollPane5.setViewportView(jListDia);

        jPanelFundo.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, 190));

        jButtonApagaDia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonApagaDia.setText("<--");
        jButtonApagaDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaDiaActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jComboBoxHora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7h", "8h", "9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h" }));
        jComboBoxHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHoraActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 290, -1, -1));

        jLabelAno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelAno.setForeground(new java.awt.Color(240, 240, 240));
        jLabelAno.setText("Ano(s)");
        jPanelFundo.add(jLabelAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jListAno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jListAno.setModel(new DefaultListModel());
        jScrollPane6.setViewportView(jListAno);

        jPanelFundo.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 100, 190));

        jComboBoxAno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBoxAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnoActionPerformed(evt);
            }
        });
        jPanelFundo.add(jComboBoxAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 80, -1));

        jButtonApagaAno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonApagaAno.setText("<--");
        jButtonApagaAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagaAnoActionPerformed(evt);
            }
        });
        jPanelFundo.add(jButtonApagaAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 50, -1));

        jPanelPrincipal.add(jPanelFundo, java.awt.BorderLayout.CENTER);

        jPanelTopo.setBackground(new java.awt.Color(0, 51, 65));
        jPanelTopo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(151, 154, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vincular Rotina");
        jPanelTopo.add(jLabel1);

        jPanelPrincipal.add(jPanelTopo, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        controller.cancelar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPeriodoActionPerformed
         
        switch (jComboBoxPeriodo.getSelectedIndex()){
            case 0:
                jComboBoxMes.setEnabled(true);
                jButtonApagaMes.setEnabled(true);
                ((DefaultListModel)jListMes.getModel()).removeAllElements();
                
                jComboBoxDiaSemana.setEnabled(false);
                jButtonApagaDiaSemana.setEnabled(false);
                ((DefaultListModel)jListDiaSemana.getModel()).removeAllElements();
                
                jComboBoxDiaUtil.setEnabled(false);
                jButtonApagaDiaUtil.setEnabled(false);
                ((DefaultListModel)jListDiaUtil.getModel()).removeAllElements();
               
                jComboBoxDia.setEnabled(true);
                jButtonApagaDia.setEnabled(true);
                ((DefaultListModel)jListDia.getModel()).removeAllElements();
                
                break;
            case 1:
                jComboBoxMes.setEnabled(false);
                jButtonApagaMes.setEnabled(false);
                ((DefaultListModel)jListMes.getModel()).removeAllElements();
                
                jComboBoxDiaSemana.setEnabled(false);
                jButtonApagaDiaSemana.setEnabled(false);
                ((DefaultListModel)jListDiaSemana.getModel()).removeAllElements();
                
                jComboBoxDiaUtil.setEnabled(false);
                jButtonApagaDiaUtil.setEnabled(false);
                ((DefaultListModel)jListDiaUtil.getModel()).removeAllElements();
               
                jComboBoxDia.setEnabled(false);
                jButtonApagaDia.setEnabled(false);
                ((DefaultListModel)jListDia.getModel()).removeAllElements();
                
                break;
                
            case 2:
                
                jComboBoxMes.setEnabled(false);
                jButtonApagaMes.setEnabled(false);
                ((DefaultListModel)jListMes.getModel()).removeAllElements();
                
                jComboBoxDiaSemana.setEnabled(true);
                jButtonApagaDiaSemana.setEnabled(true);
                
                jComboBoxDiaUtil.setEnabled(false);
                jButtonApagaDiaUtil.setEnabled(false);
                ((DefaultListModel)jListDiaUtil.getModel()).removeAllElements();
               
                jComboBoxDia.setEnabled(false);
                jButtonApagaDia.setEnabled(false);
                ((DefaultListModel)jListDia.getModel()).removeAllElements();
                
                break;
                
             case 3:
                
                jComboBoxMes.setEnabled(false);
                jButtonApagaMes.setEnabled(false);
                ((DefaultListModel)jListMes.getModel()).removeAllElements();
                
                jComboBoxDiaSemana.setEnabled(false);
                jButtonApagaDiaSemana.setEnabled(false);
                ((DefaultListModel)jListDiaSemana.getModel()).removeAllElements();
                
                jComboBoxDiaUtil.setEnabled(true);
                jButtonApagaDiaUtil.setEnabled(true);
               
                jComboBoxDia.setEnabled(false);
                jButtonApagaDia.setEnabled(false);
                ((DefaultListModel)jListDia.getModel()).removeAllElements();
                
                break;
                
            case 4:
                
                jComboBoxMes.setEnabled(false);
                jButtonApagaMes.setEnabled(false);
                ((DefaultListModel)jListMes.getModel()).removeAllElements();
                controller.preencherDias();
                
                jComboBoxDiaSemana.setEnabled(false);
                jButtonApagaDiaSemana.setEnabled(false);
                ((DefaultListModel)jListDiaSemana.getModel()).removeAllElements();
                
                jComboBoxDiaUtil.setEnabled(false);
                jButtonApagaDiaUtil.setEnabled(false);
                ((DefaultListModel)jListDiaUtil.getModel()).removeAllElements();
               
                jComboBoxDia.setEnabled(true);
                jButtonApagaDia.setEnabled(true);
                
                break;
                
             case 5:
                
                jComboBoxMes.setEnabled(true);
                jButtonApagaMes.setEnabled(true);
                
                jComboBoxDiaSemana.setEnabled(false);
                jButtonApagaDiaSemana.setEnabled(false);
                ((DefaultListModel)jListDiaSemana.getModel()).removeAllElements();
                
                jComboBoxDiaUtil.setEnabled(false);
                jButtonApagaDiaUtil.setEnabled(false);
                ((DefaultListModel)jListDiaUtil.getModel()).removeAllElements();
               
                jComboBoxDia.setEnabled(true);
                jButtonApagaDia.setEnabled(true);
                ((DefaultListModel)jListDia.getModel()).removeAllElements();
                
                break;
        }
    }//GEN-LAST:event_jComboBoxPeriodoActionPerformed

    private void jComboBoxPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPeriodoItemStateChanged

    }//GEN-LAST:event_jComboBoxPeriodoItemStateChanged

    private void jComboBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesActionPerformed
        
        controller.preencherDias();
        
        DefaultListModel listModel = (DefaultListModel)jListMes.getModel();
        Object selectedItem = jComboBoxMes.getSelectedItem();
        
        if(jComboBoxPeriodo.getSelectedIndex() == 0){
            listModel.clear();
            listModel.addElement(selectedItem);
        } else 
        if(!listModel.contains(selectedItem)){
            listModel.addElement(selectedItem);
        } else JOptionPane.showMessageDialog(this,(String)selectedItem+" já esta na ista", "Lista de Meses", 1);
        
    }//GEN-LAST:event_jComboBoxMesActionPerformed

    private void jButtonApagaMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaMesActionPerformed
        ((DefaultListModel)jListMes.getModel()).removeElementAt(jListMes.getSelectedIndex());
    }//GEN-LAST:event_jButtonApagaMesActionPerformed

    private void jComboBoxDiaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaSemanaActionPerformed
        DefaultListModel listModel = (DefaultListModel)jListDiaSemana.getModel();
        Object selectedItem = jComboBoxDiaSemana.getSelectedItem();
        if(!listModel.contains(selectedItem)){
            listModel.addElement(selectedItem);
        } else JOptionPane.showMessageDialog(this,(String)selectedItem+" já está na lista", "Lista de Semanas", 1);
    }//GEN-LAST:event_jComboBoxDiaSemanaActionPerformed

    private void jButtonApagaDiaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaDiaSemanaActionPerformed
        ((DefaultListModel)jListDiaSemana.getModel()).remove(jListDiaSemana.getSelectedIndex());
    }//GEN-LAST:event_jButtonApagaDiaSemanaActionPerformed

    private void jComboBoxDiaUtilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaUtilActionPerformed
        DefaultListModel listModel = (DefaultListModel)jListDiaUtil.getModel();
        Object selectedItem = jComboBoxDiaUtil.getSelectedItem();
        if(!listModel.contains(selectedItem)){
            listModel.addElement(selectedItem);
        } else JOptionPane.showMessageDialog(this, (String)selectedItem+" dia útil já está na lista", "Dias úteis", 1);
        String opcao = "";
        
        if(selectedItem.equals("23°") || selectedItem.equals("22°") || selectedItem.equals("21°")){
            
            if(selectedItem.equals("23°")){
                opcao = "último";
            } else if (selectedItem.equals("22°")) {
                opcao = "penúltimo";
            } else if (selectedItem.equals("21°")) {
                opcao = "antepenúltimo";
            }
            JOptionPane.showMessageDialog(this, "Alguns meses não possuem o "+selectedItem+" dia útil, deseja marcar como "+opcao+" dia útil do mês?", "Dias úteis",1);        
        }
    }//GEN-LAST:event_jComboBoxDiaUtilActionPerformed

    private void jButtonApagaDiaUtilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaDiaUtilActionPerformed
        int index = jListDiaUtil.getSelectedIndex();
        if(!(index < 0)){
          ((DefaultListModel)jListDiaUtil.getModel()).removeElementAt(index);  
        }
    }//GEN-LAST:event_jButtonApagaDiaUtilActionPerformed

    private void jComboBoxDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaActionPerformed
        DefaultListModel listModel = (DefaultListModel)jListDia.getModel();
        Object selectedItem = jComboBoxDia.getSelectedItem();
        
        if(jComboBoxPeriodo.getSelectedIndex() == 0){
            listModel.clear();
            listModel.addElement(selectedItem);
        } else
        
        if(!listModel.contains(selectedItem)){
            listModel.addElement(selectedItem);
        } else JOptionPane.showMessageDialog(this,(Integer)selectedItem+" dia já está na lista", "Dias do mês", 1);
    }//GEN-LAST:event_jComboBoxDiaActionPerformed

    private void jButtonApagaDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaDiaActionPerformed
        int index = jListDia.getSelectedIndex();
        if(!(index < 0)){
            ((DefaultListModel)jListDia.getModel()).removeElementAt(index);
        }
    }//GEN-LAST:event_jButtonApagaDiaActionPerformed

    private void jComboBoxHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHoraActionPerformed
        DefaultListModel listModel = (DefaultListModel) jListHorario.getModel();
        Object selectedItem = jComboBoxHora.getSelectedItem();
        if(!listModel.contains(selectedItem)){
            listModel.addElement(selectedItem);
        } else JOptionPane.showMessageDialog(this, (String)selectedItem+" já está na lista", "Horário", 1);
    }//GEN-LAST:event_jComboBoxHoraActionPerformed

    private void jButtonApagaHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaHoraActionPerformed
        int index = jListHorario.getSelectedIndex();
        if(!(index < 0)){
            ((DefaultListModel)jListHorario.getModel()).removeElementAt(index);
        }
    }//GEN-LAST:event_jButtonApagaHoraActionPerformed

    private void jComboBoxAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAnoActionPerformed

    private void jButtonApagaAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagaAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonApagaAnoActionPerformed

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
            java.util.logging.Logger.getLogger(VincularRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VincularRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VincularRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VincularRotinaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VincularRotinaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGrouHoraFixa;
    private javax.swing.ButtonGroup buttonGroupPrioritario;
    private javax.swing.ButtonGroup buttonGroupReagendavel;
    private javax.swing.JButton jButtonApagaAno;
    private javax.swing.JButton jButtonApagaDia;
    private javax.swing.JButton jButtonApagaDiaSemana;
    private javax.swing.JButton jButtonApagaDiaUtil;
    private javax.swing.JButton jButtonApagaHora;
    private javax.swing.JButton jButtonApagaMes;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBoxAno;
    private javax.swing.JComboBox<Integer> jComboBoxDia;
    private javax.swing.JComboBox<String> jComboBoxDiaSemana;
    private javax.swing.JComboBox<String> jComboBoxDiaUtil;
    private javax.swing.JComboBox<String> jComboBoxFuncionario;
    private javax.swing.JComboBox<String> jComboBoxHora;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JComboBox<String> jComboBoxPeriodo;
    private javax.swing.JComboBox<String> jComboBoxRotina;
    private javax.swing.JComboBox<String> jComboBoxUnidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelDias;
    private javax.swing.JLabel jLabelDiasUtil;
    private javax.swing.JLabel jLabelFuncionario;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelObservacao;
    private javax.swing.JLabel jLabelPeriodo;
    private javax.swing.JLabel jLabelRotina;
    private javax.swing.JLabel jLabelUnidade;
    private javax.swing.JList<String> jListAno;
    private javax.swing.JList<String> jListDia;
    private javax.swing.JList<String> jListDiaSemana;
    private javax.swing.JList<String> jListDiaUtil;
    private javax.swing.JList<String> jListHorario;
    private javax.swing.JList<String> jListMes;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTopo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPaneObeservacao;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaObservacao;
    // End of variables declaration//GEN-END:variables

    public JComboBox<Integer> getjComboBoxDia() {
        return jComboBoxDia;
    }

    public JComboBox<String> getjComboBoxDiaSemana() {
        return jComboBoxDiaSemana;
    }

    public JComboBox<String> getjComboBoxDiaUtil() {
        return jComboBoxDiaUtil;
    }

    public JComboBox<String> getjComboBoxFuncionario() {
        return jComboBoxFuncionario;
    }

    public JComboBox<String> getjComboBoxHora() {
        return jComboBoxHora;
    }

    public JComboBox<String> getjComboBoxMes() {
        return jComboBoxMes;
    }

    public JComboBox<String> getjComboBoxPeriodo() {
        return jComboBoxPeriodo;
    }

    public JComboBox<String> getjComboBoxRotina() {
        return jComboBoxRotina;
    }

    public JComboBox<String> getjComboBoxUnidade() {
        return jComboBoxUnidade;
    }

    
    
    
    
}
