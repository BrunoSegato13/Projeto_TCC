/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telas;

import java.sql.*;
import br.com.connection.ModuloConexao;
import java.text.DecimalFormat;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Home
 */
public class TelaDepositos extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaDepositos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    // variaveis
    String ocupacao, divisao;
    double cargadeIncendio;

    /**
     *
     */
    public static String ocup, div;
    public static double carg;

    public String getOcup() {
        return ocup;
    }

    public void setOcup(String ocup) {
        this.ocup = ocup;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public double getCarg() {
        return carg;
    }

    public void setCarg(double carg) {
        this.carg = carg;
    }

    private void pesquisar() {
        String slq = "select * from depositos where material like ?";
        try {
            pst = conexao.prepareStatement(slq);
            pst.setString(1, txtPesquisaAltura.getText() + "%");
            rs = pst.executeQuery();
            tblAlturaDeposito.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCampos() {
        int setar = tblAlturaDeposito.getSelectedRow();
        if (rbtn1.isSelected() || rbtn2.isSelected() || rbtn4.isSelected() || rbtn6.isSelected() || rbtn8.isSelected() || rbtn10.isSelected()) {
            txtMaterial.setText(tblAlturaDeposito.getModel().getValueAt(setar, 1).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione a altura!");
        }
        if (rbtn1.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 2).toString());
        } else if (rbtn2.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 3).toString());
        } else if (rbtn4.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 4).toString());
        } else if (rbtn6.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 5).toString());
        } else if (rbtn8.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 6).toString());
        } else if (rbtn10.isSelected()) {
            txtCargaIncendio.setText(tblAlturaDeposito.getModel().getValueAt(setar, 7).toString());
        }
    }

    public void verificar() {
        cargadeIncendio = Double.parseDouble(txtCargaIncendio.getText());
        if (cargadeIncendio <= 300) {
            ocupacao = "Depósitos";
            divisao = "J-2";
        } else if ((300 < cargadeIncendio) & (cargadeIncendio <= 1200)) {
            ocupacao = "Depósitos";
            divisao = "J-3";

        } else if (cargadeIncendio > 1200) {
            ocupacao = "Depósitos";
            divisao = "J-4";
        }
        txtOcupa.setText(ocupacao);
        txtDiv.setText(divisao);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlturaDeposito = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtPesquisaAltura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbtn1 = new javax.swing.JRadioButton();
        rbtn2 = new javax.swing.JRadioButton();
        rbtn4 = new javax.swing.JRadioButton();
        rbtn6 = new javax.swing.JRadioButton();
        rbtn8 = new javax.swing.JRadioButton();
        rbtn10 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCargaIncendio = new javax.swing.JTextField();
        btnVerifica = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtDiv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtOcupa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(900, 775));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAlturaDeposito.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAlturaDeposito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlturaDepositoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlturaDeposito);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 59, 882, 105));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel1.setText("Pesquisar");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 17, 83, -1));

        txtPesquisaAltura.setBackground(new java.awt.Color(102, 255, 102));
        txtPesquisaAltura.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPesquisaAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaAlturaKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisaAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 11, 242, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel2.setText("Altura:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 182, 47, 27));

        buttonGroup1.add(rbtn1);
        rbtn1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn1.setText("1m");
        getContentPane().add(rbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 186, -1, -1));

        buttonGroup1.add(rbtn2);
        rbtn2.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn2.setText("2m");
        getContentPane().add(rbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 186, 40, -1));

        buttonGroup1.add(rbtn4);
        rbtn4.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn4.setText("4m");
        getContentPane().add(rbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 186, 40, -1));

        buttonGroup1.add(rbtn6);
        rbtn6.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn6.setText("6m");
        getContentPane().add(rbtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 186, 40, -1));

        buttonGroup1.add(rbtn8);
        rbtn8.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn8.setText("8m");
        getContentPane().add(rbtn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 186, 40, -1));

        buttonGroup1.add(rbtn10);
        rbtn10.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtn10.setText("10m");
        getContentPane().add(rbtn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 186, 47, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel3.setText("Material:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 232, 80, -1));

        txtMaterial.setEditable(false);
        txtMaterial.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 227, 461, 25));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel4.setText("Carga de Incêndio (MJ/m³):");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 279, -1, -1));

        txtCargaIncendio.setEditable(false);
        txtCargaIncendio.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCargaIncendio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtCargaIncendio, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 274, 120, 25));

        btnVerifica.setText("Verificar");
        btnVerifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificaActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 273, 139, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel5.setText("Divisão:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 319, 66, -1));

        txtDiv.setEditable(false);
        txtDiv.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 314, 120, 25));

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel6.setText("Ocupação:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 358, -1, -1));

        txtOcupa.setEditable(false);
        txtOcupa.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtOcupa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtOcupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 353, 120, 25));

        jButton1.setText("Exportar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 313, 160, -1));

        setBounds(0, 0, 900, 650);
    }// </editor-fold>//GEN-END:initComponents

    // chamar enquanto digita
    private void txtPesquisaAlturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaAlturaKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtPesquisaAlturaKeyReleased

    // chamada para setar os campos
    private void tblAlturaDepositoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlturaDepositoMouseClicked
        try {

            setarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_tblAlturaDepositoMouseClicked

    private void btnVerificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificaActionPerformed
        verificar();
    }//GEN-LAST:event_btnVerificaActionPerformed

    public void envia() {
        TelaDepositos.ocup = ocupacao;
        TelaDepositos.div = divisao;
        TelaDepositos.carg = cargadeIncendio;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja salvar e exportar para o projeto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                envia();
                JOptionPane.showMessageDialog(null, "Dados exportados com sucesso! Na tela cálculo hidráulico clique em (Importar de Depósitos)");
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

           
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerifica;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtn1;
    private javax.swing.JRadioButton rbtn10;
    private javax.swing.JRadioButton rbtn2;
    private javax.swing.JRadioButton rbtn4;
    private javax.swing.JRadioButton rbtn6;
    private javax.swing.JRadioButton rbtn8;
    private javax.swing.JTable tblAlturaDeposito;
    private javax.swing.JTextField txtCargaIncendio;
    private javax.swing.JTextField txtDiv;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtOcupa;
    private javax.swing.JTextField txtPesquisaAltura;
    // End of variables declaration//GEN-END:variables
}
