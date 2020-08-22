/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telas;

import java.sql.*;
import java.text.DecimalFormat;
import br.com.connection.ModuloConexao;
import java.util.Arrays;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Home
 */
public class TelaProjeto extends javax.swing.JInternalFrame {

    TelaCalHid tch = new TelaCalHid();
    DecimalFormat df = new DecimalFormat("####.##");

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = null;

    /**
     * Creates new form TelaProjeto
     */
    public TelaProjeto() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    int id;

    // metodo para importar
    private void importar() {
        txtRisco.setText(TelaCalHid.risco);
        txtOcupacao.setText(TelaCalHid.ocupacao);
        txtSistema.setText(TelaCalHid.tipoSistema);
        txtMaterialTubulacao.setText(TelaCalHid.matTub);
        txtNumeroHidrante.setText(String.valueOf(df.format(TelaCalHid.qtdHidrantes)));
        txtVazaoH.setText(String.valueOf(df.format(TelaCalHid.vazaoTH)));
        txtVazaoH1.setText(String.valueOf(df.format(TelaCalHid.vazaoTH1)));
        txtVazaoHM.setText(String.valueOf(df.format(TelaCalHid.vazaoTHM)));
        txtVazaoS.setText(String.valueOf(df.format(TelaCalHid.vazaoTS)));
        txtVazaoR.setText(String.valueOf(df.format(TelaCalHid.vazaoTR)));
        txtVazaoBomba.setText(String.valueOf(df.format(TelaCalHid.vazaoTB)));
        txtHMH.setText(String.valueOf(df.format(TelaCalHid.altMH)));
        txtHMH1.setText(String.valueOf(df.format(TelaCalHid.altMH1)));
        txtHMHM.setText(String.valueOf(df.format(TelaCalHid.altMHM)));
        txtHMS.setText(String.valueOf(df.format(TelaCalHid.altMS)));
        txtHMR.setText(String.valueOf(df.format(TelaCalHid.altMR)));
        txtHMBomba.setText(String.valueOf(df.format(TelaCalHid.altMB)));
        txtDiaH.setText(String.valueOf((TelaCalHid.diaH)));
        txtDiaH1.setText(String.valueOf((TelaCalHid.diaH1)));
        txtDiaHM.setText(String.valueOf((TelaCalHid.diaHM)));
        txtDiaS.setText(String.valueOf((TelaCalHid.diaS)));
        txtDiaR.setText(String.valueOf((TelaCalHid.diaR)));
        txtDiametroMangueira.setText(String.valueOf((TelaCalHid.diaMangH)));
        txtVeloH.setText(String.valueOf(df.format(TelaCalHid.velH)));
        txtVeloH1.setText(String.valueOf(df.format(TelaCalHid.velH1)));
        txtVeloHM.setText(String.valueOf(df.format(TelaCalHid.velHM)));
        txtVeloS.setText(String.valueOf(df.format(TelaCalHid.velS)));
        txtVeloR.setText(String.valueOf(df.format(TelaCalHid.velR)));
        txtPCH.setText(String.valueOf(df.format(TelaCalHid.pcH)));
        txtPCH1.setText(String.valueOf(df.format(TelaCalHid.pcH1)));
        txtPCHM.setText(String.valueOf(df.format(TelaCalHid.pcHM)));
        txtPCR.setText(String.valueOf(df.format(TelaCalHid.pcR)));
        txtPCS.setText(String.valueOf(df.format(TelaCalHid.pcS)));
        txtDesnH.setText(String.valueOf(df.format(TelaCalHid.desH)));
        txtDesnH1.setText(String.valueOf(df.format(TelaCalHid.desH1)));
        txtDesnHM.setText(String.valueOf(df.format(TelaCalHid.desHM)));
        txtDesnR.setText(String.valueOf(df.format(TelaCalHid.desR)));
        txtDesnHS.setText(String.valueOf(df.format(TelaCalHid.desS)));
        txtRti.setText(String.valueOf(df.format(TelaCalHid.volRti)));

        txtCompH.setText(String.valueOf(df.format(TelaCalHid.compTotH)));
        txtCompH1.setText(String.valueOf(df.format(TelaCalHid.compTotH1)));
        txtCompHM.setText(String.valueOf(df.format(TelaCalHid.compTotHM)));
        txtCompR.setText(String.valueOf(df.format(TelaCalHid.compTotR)));
        txtCompS.setText(String.valueOf(df.format(TelaCalHid.compTotS)));
        txtCompMangH.setText(String.valueOf(df.format(TelaCalHid.compMangH)));
        txtCompMangH1.setText(String.valueOf(df.format(TelaCalHid.compMangH1)));
        txtCompMangHM.setText(String.valueOf(df.format(TelaCalHid.compMangHM)));
        txtDiaMangH1.setText(String.valueOf(df.format(TelaCalHid.diaMangHM)));
        txtDiaMangHM.setText(String.valueOf(df.format(TelaCalHid.diaMangHM)));
        txtDifPressao.setText(String.valueOf(df.format(TelaCalHid.difPressa)));
        txtArea.setText(TelaCalHid.areaT);
        txtAltura.setText(TelaCalHid.alturaT);
        txtDivisao.setText(TelaCalHid.divisaoT);
    }

    // método para fazer o update
    private void salvar() {
        String sql = "insert into projeto ( proprietario, endereco, municipio, \n"
                + "responsavel,ocupacao ,crea ,risco ,numerodehidrantes, \n"
                + "tipodesistema,materialdatubulacao,rti, vazaobomba, hmbomba, diametromangueira, vazaoH,\n"
                + "vazaoH1, vazaoHM, vazaoR, vazaoS, alturaH, alturaH1,\n"
                + "alturaHM, alturaR,  alturaS, diaH, diaH1, diaHM, diaR,\n"
                + "diaS, pcH, pcH1, pcHM, pcR, pcS, desH, desH1, desHM,\n"
                + "desR, desS, velH, velH1, velHM, velR, velS,compTotH , compTotH1 , compTotHM , compTotR , compTotS , compMangH ,\n"
                + "compMangH1 , compMangHM , diaMangH1 , diaMangHM , difPressao , area , altura, divisao ) values \n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtProprietario.getText());
            pst.setString(2, txtEndereco.getText());
            pst.setString(3, txtMunicipio.getText());
            pst.setString(4, txtResponsavel.getText());
            pst.setString(5, txtOcupacao.getText());
            pst.setString(6, txtCrea.getText());
            pst.setString(7, txtRisco.getText());
            pst.setString(8, txtNumeroHidrante.getText());
            pst.setString(9, txtSistema.getText());
            pst.setString(10, txtMaterialTubulacao.getText());
            pst.setString(11, txtRti.getText());
            pst.setString(12, txtVazaoBomba.getText());
            pst.setString(13, txtHMBomba.getText());
            pst.setString(14, txtDiametroMangueira.getText());
            pst.setString(15, txtVazaoH.getText());
            pst.setString(16, txtVazaoH1.getText());
            pst.setString(17, txtVazaoHM.getText());
            pst.setString(18, txtVazaoR.getText());
            pst.setString(19, txtVazaoS.getText());
            pst.setString(20, txtHMH.getText());
            pst.setString(21, txtHMH1.getText());
            pst.setString(22, txtHMHM.getText());
            pst.setString(23, txtHMR.getText());
            pst.setString(24, txtHMS.getText());
            pst.setString(25, txtDiaH.getText());
            pst.setString(26, txtDiaH1.getText());
            pst.setString(27, txtDiaHM.getText());
            pst.setString(28, txtDiaR.getText());
            pst.setString(29, txtDiaS.getText());
            pst.setString(30, txtPCH.getText());
            pst.setString(31, txtPCH1.getText());
            pst.setString(32, txtPCHM.getText());
            pst.setString(33, txtPCR.getText());
            pst.setString(34, txtPCS.getText());
            pst.setString(35, txtDesnH.getText());
            pst.setString(36, txtDesnH1.getText());
            pst.setString(37, txtDesnHM.getText());
            pst.setString(38, txtDesnHS.getText());
            pst.setString(39, txtDesnR.getText());
            pst.setString(40, txtVeloH.getText());
            pst.setString(41, txtVeloH1.getText());
            pst.setString(42, txtVeloHM.getText());
            pst.setString(43, txtVeloR.getText());
            pst.setString(44, txtVeloS.getText());

            pst.setString(45, txtCompH.getText());
            pst.setString(46, txtCompH1.getText());
            pst.setString(47, txtCompHM.getText());
            pst.setString(48, txtCompR.getText());
            pst.setString(49, txtCompS.getText());
            pst.setString(50, txtCompMangH.getText());
            pst.setString(51, txtCompMangH1.getText());
            pst.setString(52, txtCompMangHM.getText());
            pst.setString(53, txtDiaMangH1.getText());
            pst.setString(54, txtDiaMangHM.getText());
            pst.setString(55, txtDifPressao.getText());
            pst.setString(56, txtArea.getText());
            pst.setString(57, txtAltura.getText());
            pst.setString(58,txtDivisao.getText());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso!");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    // método de pesquisa
    private void pesquisar() {
        String sql = "select id,proprietario from projeto where proprietario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisa.getText() + "%");
            rs = pst.executeQuery();
            tblProprietario.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void confere() {
        int setar = tblProprietario.getSelectedRow();
        id = (int) tblProprietario.getModel().getValueAt(setar, 0);
    }

    // método setar campos
    private void setar() {
        String sql = "select * from projeto where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));
            rs = pst.executeQuery();
            if (rs.next()) {

                txtProprietario.setText(rs.getString(2));
                txtEndereco.setText(rs.getString(3));
                txtMunicipio.setText(rs.getString(4));
                txtResponsavel.setText(rs.getString(5));
                txtOcupacao.setText(rs.getString(6));
                txtCrea.setText(rs.getString(7));
                txtRisco.setText(rs.getString(8));
                txtNumeroHidrante.setText(rs.getString(9));
                txtSistema.setText(rs.getString(10));
                txtMaterialTubulacao.setText(rs.getString(11));
                txtRti.setText(rs.getString(12));
                txtVazaoBomba.setText(rs.getString(13));
                txtHMBomba.setText(rs.getString(14));
                txtDiametroMangueira.setText(rs.getString(15));
                txtVazaoH.setText(rs.getString(16));
                txtVazaoH1.setText(rs.getString(17));
                txtVazaoHM.setText(rs.getString(18));
                txtVazaoR.setText(rs.getString(19));
                txtVazaoS.setText(rs.getString(20));
                txtHMH.setText(rs.getString(21));
                txtHMH1.setText(rs.getString(22));
                txtHMHM.setText(rs.getString(23));
                txtHMR.setText(rs.getString(24));
                txtHMS.setText(rs.getString(25));
                txtDiaH.setText(rs.getString(26));
                txtDiaH1.setText(rs.getString(27));
                txtDiaHM.setText(rs.getString(28));
                txtDiaR.setText(rs.getString(29));
                txtDiaS.setText(rs.getString(30));
                txtPCH.setText(rs.getString(31));
                txtPCH1.setText(rs.getString(32));
                txtPCHM.setText(rs.getString(33));
                txtPCR.setText(rs.getString(34));
                txtPCS.setText(rs.getString(35));
                txtDesnH.setText(rs.getString(36));
                txtDesnH1.setText(rs.getString(37));
                txtDesnHM.setText(rs.getString(38));
                txtDesnHS.setText(rs.getString(39));
                txtDesnR.setText(rs.getString(40));
                txtVeloH.setText(rs.getString(41));
                txtVeloH1.setText(rs.getString(42));
                txtVeloHM.setText(rs.getString(43));
                txtVeloR.setText(rs.getString(44));
                txtVeloS.setText(rs.getString(45));

                txtCompH.setText(rs.getString(46));
                txtCompH1.setText(rs.getString(47));
                txtCompHM.setText(rs.getString(48));
                txtCompR.setText(rs.getString(49));
                txtCompS.setText(rs.getString(50));
                txtCompMangH.setText(rs.getString(51));
                txtCompMangH1.setText(rs.getString(52));
                txtCompMangHM.setText(rs.getString(53));
                txtDiaMangH1.setText(rs.getString(54));
                txtDiaMangHM.setText(rs.getString(55));
                txtDifPressao.setText(rs.getString(56));
                txtArea.setText(rs.getString(57));
                txtAltura.setText(rs.getString(58));
                txtDivisao.setText(rs.getString(59));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    // método para modificar
    private void modificar() {
        String sql = "update projeto set proprietario=?, endereco=?, municipio=?, \n"
                + "responsavel=?,ocupacao=? ,crea=? ,risco=? ,numerodehidrantes=?, \n"
                + "tipodesistema=?,materialdatubulacao=?,rti=?, vazaobomba=?, hmbomba=?, diametromangueira=?, vazaoH=?,\n"
                + "vazaoH1=?, vazaoHM=?, vazaoR=?, vazaoS=?, alturaH=?, alturaH1=?,\n"
                + "alturaHM=?, alturaR=?,  alturaS=?, diaH=?, diaH1=?, diaHM=?, diaR=?,\n"
                + "diaS=?, pcH=?, pcH1=?, pcHM=?, pcR=?, pcS=?, desH=?, desH1=?, desHM=?,\n"
                + "desR=?, desS=?, velH=?, velH1=?, velHM=?, velR=?, velS=?,compTotH=? , compTotH1=? , compTotHM=? , compTotR=? , compTotS=? , compMangH =?,\n" +
"compMangH1=? , compMangHM=? , diaMangH1=? , diaMangHM=? , difPressao=? , area=? , altura=?, divisao=?  where id=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtProprietario.getText());
            pst.setString(2, txtEndereco.getText());
            pst.setString(3, txtMunicipio.getText());
            pst.setString(4, txtResponsavel.getText());
            pst.setString(5, txtOcupacao.getText());
            pst.setString(6, txtCrea.getText());
            pst.setString(7, txtRisco.getText());
            pst.setString(8, txtNumeroHidrante.getText());
            pst.setString(9, txtSistema.getText());
            pst.setString(10, txtMaterialTubulacao.getText());
            pst.setString(11, txtRti.getText());
            pst.setString(12, txtVazaoBomba.getText());
            pst.setString(13, txtHMBomba.getText());
            pst.setString(14, txtDiametroMangueira.getText());
            pst.setString(15, txtVazaoH.getText());
            pst.setString(16, txtVazaoH1.getText());
            pst.setString(17, txtVazaoHM.getText());
            pst.setString(18, txtVazaoR.getText());
            pst.setString(19, txtVazaoS.getText());
            pst.setString(20, txtHMH.getText());
            pst.setString(21, txtHMH1.getText());
            pst.setString(22, txtHMHM.getText());
            pst.setString(23, txtHMR.getText());
            pst.setString(24, txtHMS.getText());
            pst.setString(25, txtDiaH.getText());
            pst.setString(26, txtDiaH1.getText());
            pst.setString(27, txtDiaHM.getText());
            pst.setString(28, txtDiaR.getText());
            pst.setString(29, txtDiaS.getText());
            pst.setString(30, txtPCH.getText());
            pst.setString(31, txtPCH1.getText());
            pst.setString(32, txtPCHM.getText());
            pst.setString(33, txtPCR.getText());
            pst.setString(34, txtPCS.getText());
            pst.setString(35, txtDesnH.getText());
            pst.setString(36, txtDesnH1.getText());
            pst.setString(37, txtDesnHM.getText());
            pst.setString(38, txtDesnHS.getText());
            pst.setString(39, txtDesnR.getText());
            pst.setString(40, txtVeloH.getText());
            pst.setString(41, txtVeloH1.getText());
            pst.setString(42, txtVeloHM.getText());
            pst.setString(43, txtVeloR.getText());
            pst.setString(44, txtVeloS.getText());
            
            pst.setString(45, txtCompH.getText());
            pst.setString(46, txtCompH1.getText());
            pst.setString(47, txtCompHM.getText());
            pst.setString(48, txtCompR.getText());
            pst.setString(49, txtCompS.getText());
            pst.setString(50, txtCompMangH.getText());
            pst.setString(51, txtCompMangH1.getText());
            pst.setString(52, txtCompMangHM.getText());
            pst.setString(53, txtDiaMangH1.getText());
            pst.setString(54, txtDiaMangHM.getText());
            pst.setString(55, txtDifPressao.getText());
            pst.setString(56, txtArea.getText());
            pst.setString(57, txtAltura.getText());
            pst.setString(58, txtDivisao.getText());
           
            pst.setString(59, String.valueOf(id));

            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método remover projeto
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja apagar este projeto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from projeto where id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, String.valueOf(id));
                pst.executeUpdate();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtResponsavel = new javax.swing.JTextField();
        txtMunicipio = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtProprietario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProprietario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCrea = new javax.swing.JTextField();
        txtOcupacao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRisco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumeroHidrante = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtDivisao = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtVazaoH = new javax.swing.JTextField();
        txtVazaoH1 = new javax.swing.JTextField();
        txtVazaoHM = new javax.swing.JTextField();
        txtVazaoR = new javax.swing.JTextField();
        txtHMH = new javax.swing.JTextField();
        txtHMH1 = new javax.swing.JTextField();
        txtHMHM = new javax.swing.JTextField();
        txtHMR = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDiaH = new javax.swing.JTextField();
        txtDiaH1 = new javax.swing.JTextField();
        txtDiaHM = new javax.swing.JTextField();
        txtDiaR = new javax.swing.JTextField();
        txtPCH = new javax.swing.JTextField();
        txtPCH1 = new javax.swing.JTextField();
        txtPCHM = new javax.swing.JTextField();
        txtPCR = new javax.swing.JTextField();
        txtDesnH = new javax.swing.JTextField();
        txtDesnH1 = new javax.swing.JTextField();
        txtDesnHM = new javax.swing.JTextField();
        txtDesnR = new javax.swing.JTextField();
        txtVeloH = new javax.swing.JTextField();
        txtVeloH1 = new javax.swing.JTextField();
        txtVeloHM = new javax.swing.JTextField();
        txtVeloR = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtVazaoS = new javax.swing.JTextField();
        txtHMS = new javax.swing.JTextField();
        txtDiaS = new javax.swing.JTextField();
        txtPCS = new javax.swing.JTextField();
        txtDesnHS = new javax.swing.JTextField();
        txtVeloS = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtCompH = new javax.swing.JTextField();
        txtCompH1 = new javax.swing.JTextField();
        txtCompHM = new javax.swing.JTextField();
        txtCompR = new javax.swing.JTextField();
        txtCompS = new javax.swing.JTextField();
        txtCompMangH = new javax.swing.JTextField();
        txtCompMangH1 = new javax.swing.JTextField();
        txtCompMangHM = new javax.swing.JTextField();
        txtDiametroMangueira = new javax.swing.JTextField();
        txtDiaMangH1 = new javax.swing.JTextField();
        txtDiaMangHM = new javax.swing.JTextField();
        txtDifPressao = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtSistema = new javax.swing.JTextField();
        txtMaterialTubulacao = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtVazaoBomba = new javax.swing.JTextField();
        txtHMBomba = new javax.swing.JTextField();
        txtRti = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PROJETOS");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(900, 775));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel1.setText("Proprietário:");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel2.setText("Endereço:");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel3.setText("Município:");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel4.setText("Responsável Técnico:");

        txtResponsavel.setBackground(new java.awt.Color(51, 153, 255));
        txtResponsavel.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        txtMunicipio.setBackground(new java.awt.Color(51, 153, 255));
        txtMunicipio.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        txtEndereco.setBackground(new java.awt.Color(51, 153, 255));
        txtEndereco.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        txtProprietario.setBackground(new java.awt.Color(51, 153, 255));
        txtProprietario.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel5.setText("Pesquisar:");

        txtPesquisa.setBackground(new java.awt.Color(153, 255, 153));
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        tblProprietario.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProprietario.getTableHeader().setReorderingAllowed(false);
        tblProprietario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProprietarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProprietario);
        if (tblProprietario.getColumnModel().getColumnCount() > 0) {
            tblProprietario.getColumnModel().getColumn(0).setResizable(false);
            tblProprietario.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblProprietario.getColumnModel().getColumn(1).setResizable(false);
            tblProprietario.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblProprietario.getColumnModel().getColumn(2).setResizable(false);
            tblProprietario.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel6.setText("Ocupação:");

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel7.setText("CREA");

        txtCrea.setBackground(new java.awt.Color(51, 153, 255));
        txtCrea.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        txtOcupacao.setEditable(false);
        txtOcupacao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel8.setText("Risco");

        txtRisco.setEditable(false);
        txtRisco.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel9.setText("Número de Hidrantes");

        txtNumeroHidrante.setEditable(false);
        txtNumeroHidrante.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel17.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel17.setText("Altura (m):");

        jLabel22.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel22.setText("Área (m²):");

        txtArea.setEditable(false);
        txtArea.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        txtAltura.setEditable(false);
        txtAltura.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel24.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel24.setText("Divisão");

        txtDivisao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtProprietario)
                        .addComponent(txtEndereco)
                        .addComponent(txtMunicipio)
                        .addComponent(txtResponsavel)
                        .addComponent(txtOcupacao, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtPesquisa)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRisco, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroHidrante, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCrea, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(txtDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProprietario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRisco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroHidrante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 210));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel10.setText("Trecho");

        jLabel11.setText("H1- A");

        jLabel12.setText("H1'- A");

        jLabel13.setText("HM-BI");

        jLabel14.setText("A-BI");

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel15.setText("Vazão (l/m)");

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel16.setText("Pressão (mca)");

        txtVazaoH.setEditable(false);
        txtVazaoH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVazaoH1.setEditable(false);
        txtVazaoH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVazaoHM.setEditable(false);
        txtVazaoHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVazaoR.setEditable(false);
        txtVazaoR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtHMH.setEditable(false);
        txtHMH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtHMH1.setEditable(false);
        txtHMH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtHMHM.setEditable(false);
        txtHMHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtHMR.setEditable(false);
        txtHMR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        jLabel18.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel18.setText("Diâmetro (m)");

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel19.setText("Perda de carga");

        jLabel20.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel20.setText("Desnível (m)");

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel21.setText("Velocidade (m/s)");

        txtDiaH.setEditable(false);
        txtDiaH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiaH1.setEditable(false);
        txtDiaH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiaHM.setEditable(false);
        txtDiaHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiaR.setEditable(false);
        txtDiaR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtPCH.setEditable(false);
        txtPCH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPCH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtPCH1.setEditable(false);
        txtPCH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPCH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtPCHM.setEditable(false);
        txtPCHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPCHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtPCR.setEditable(false);
        txtPCR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPCR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDesnH.setEditable(false);
        txtDesnH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDesnH1.setEditable(false);
        txtDesnH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDesnHM.setEditable(false);
        txtDesnHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDesnR.setEditable(false);
        txtDesnR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVeloH.setEditable(false);
        txtVeloH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVeloH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVeloH1.setEditable(false);
        txtVeloH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVeloH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVeloHM.setEditable(false);
        txtVeloHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVeloHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtVeloR.setEditable(false);
        txtVeloR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVeloR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        jLabel31.setText("RTI-BI");

        txtVazaoS.setEditable(false);
        txtVazaoS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtVazaoS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtVazaoS.setPreferredSize(new java.awt.Dimension(50, 28));

        txtHMS.setEditable(false);
        txtHMS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtHMS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtHMS.setPreferredSize(new java.awt.Dimension(50, 28));

        txtDiaS.setEditable(false);
        txtDiaS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtDiaS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtDiaS.setPreferredSize(new java.awt.Dimension(50, 28));

        txtPCS.setEditable(false);
        txtPCS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPCS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtPCS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtPCS.setPreferredSize(new java.awt.Dimension(50, 28));

        txtDesnHS.setEditable(false);
        txtDesnHS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnHS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtDesnHS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtDesnHS.setPreferredSize(new java.awt.Dimension(50, 28));

        txtVeloS.setEditable(false);
        txtVeloS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVeloS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        txtVeloS.setMinimumSize(new java.awt.Dimension(50, 28));
        txtVeloS.setPreferredSize(new java.awt.Dimension(50, 28));

        jLabel32.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel32.setText("Comp total (m)");

        jLabel33.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel33.setText("Comp Mang (m)");

        jLabel34.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel34.setText("Diâmetro Mang (m)");

        jLabel36.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel36.setText("Diferença de pressão no ponto A mca:");

        txtCompH.setEditable(false);
        txtCompH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompH1.setEditable(false);
        txtCompH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompHM.setEditable(false);
        txtCompHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompR.setEditable(false);
        txtCompR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompR.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompS.setEditable(false);
        txtCompS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompMangH.setEditable(false);
        txtCompMangH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompMangH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompMangH1.setEditable(false);
        txtCompMangH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompMangH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtCompMangHM.setEditable(false);
        txtCompMangHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCompMangHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiametroMangueira.setEditable(false);
        txtDiametroMangueira.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiametroMangueira.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiaMangH1.setEditable(false);
        txtDiaMangH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaMangH1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDiaMangHM.setEditable(false);
        txtDiaMangHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiaMangHM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        txtDifPressao.setEditable(false);
        txtDifPressao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDifPressao.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel31)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15)
                    .addComponent(txtVazaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txtHMH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHMHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHMR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHMS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18)
                    .addComponent(txtDiaH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(txtPCH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20)
                    .addComponent(txtDesnH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnHS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(txtVeloH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVeloH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVeloHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVeloR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVeloS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel32)
                    .addComponent(txtCompH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel33)
                            .addComponent(txtCompMangH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompMangH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompMangHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel34)
                            .addComponent(txtDiametroMangueira, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaMangH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaMangHM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(140, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDifPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtVazaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHMH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPCH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeloH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompMangH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiametroMangueira, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtVazaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPCH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeloH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompMangH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaMangH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtVazaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHMHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPCHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeloHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompMangHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaMangHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtVazaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHMR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPCR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeloR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel31)
                            .addComponent(txtVazaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHMS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPCS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnHS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeloS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(txtDifPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 880, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel23.setText("Tipo de Sistema");

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel25.setText("Material da Tubulação");

        txtSistema.setEditable(false);
        txtSistema.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtSistema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtMaterialTubulacao.setEditable(false);
        txtMaterialTubulacao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtMaterialTubulacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel28.setText("Volume RTI (m³)");

        jLabel29.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel29.setText("Vazão Bomba (l/m)");

        jLabel30.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel30.setText("Pressão Bomba (mca)");

        txtVazaoBomba.setEditable(false);
        txtVazaoBomba.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoBomba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtHMBomba.setEditable(false);
        txtHMBomba.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtHMBomba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtRti.setEditable(false);
        txtRti.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtRti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRti, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtVazaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHMBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaterialTubulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaterialTubulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRti, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHMBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 880, -1));

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, -1, -1));

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, -1, -1));

        jButton3.setText("Apagar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, -1, -1));

        jButton4.setText("Importar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, -1, -1));

        setBounds(0, 0, 900, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        importar();
    }//GEN-LAST:event_jButton4ActionPerformed

    // método salvar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try{ if (txtProprietario.getText().isEmpty() || txtMunicipio.getText().isEmpty() || txtEndereco.getText().isEmpty() || 
               txtResponsavel.getText().isEmpty() || txtCrea.getText().isEmpty() || txtOcupacao.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
       } else {
        int confirma = JOptionPane.showConfirmDialog(null,"Deseja salvar o projeto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
        salvar();
        
        }
       }
      } catch (Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // método para pesquisar
    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    //método para setar os campos
    private void tblProprietarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProprietarioMouseClicked
       try{ confere();
        setar();
       } catch (Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }//GEN-LAST:event_tblProprietarioMouseClicked

    // método modificar
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{ if (txtProprietario.getText().isEmpty() || txtMunicipio.getText().isEmpty() || txtEndereco.getText().isEmpty() || 
               txtResponsavel.getText().isEmpty() || txtCrea.getText().isEmpty() || txtOcupacao.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
       } else {
        int confirma = JOptionPane.showConfirmDialog(null,"Deseja alterar o projeto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
        modificar();
        JOptionPane.showMessageDialog(null,"Projeto alterado com sucesso!");
         }
       }
      } catch (Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    //método remover
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{ 
       
        remover();
        JOptionPane.showMessageDialog(null,"Projeto deletado!");
         
       } catch (Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProprietario;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCompH;
    private javax.swing.JTextField txtCompH1;
    private javax.swing.JTextField txtCompHM;
    private javax.swing.JTextField txtCompMangH;
    private javax.swing.JTextField txtCompMangH1;
    private javax.swing.JTextField txtCompMangHM;
    private javax.swing.JTextField txtCompR;
    private javax.swing.JTextField txtCompS;
    private javax.swing.JTextField txtCrea;
    private javax.swing.JTextField txtDesnH;
    private javax.swing.JTextField txtDesnH1;
    private javax.swing.JTextField txtDesnHM;
    private javax.swing.JTextField txtDesnHS;
    private javax.swing.JTextField txtDesnR;
    private javax.swing.JTextField txtDiaH;
    private javax.swing.JTextField txtDiaH1;
    private javax.swing.JTextField txtDiaHM;
    private javax.swing.JTextField txtDiaMangH1;
    private javax.swing.JTextField txtDiaMangHM;
    private javax.swing.JTextField txtDiaR;
    private javax.swing.JTextField txtDiaS;
    private javax.swing.JTextField txtDiametroMangueira;
    private javax.swing.JTextField txtDifPressao;
    private javax.swing.JTextField txtDivisao;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtHMBomba;
    private javax.swing.JTextField txtHMH;
    private javax.swing.JTextField txtHMH1;
    private javax.swing.JTextField txtHMHM;
    private javax.swing.JTextField txtHMR;
    private javax.swing.JTextField txtHMS;
    private javax.swing.JTextField txtMaterialTubulacao;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNumeroHidrante;
    private javax.swing.JTextField txtOcupacao;
    private javax.swing.JTextField txtPCH;
    private javax.swing.JTextField txtPCH1;
    private javax.swing.JTextField txtPCHM;
    private javax.swing.JTextField txtPCR;
    private javax.swing.JTextField txtPCS;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtProprietario;
    private javax.swing.JTextField txtResponsavel;
    private javax.swing.JTextField txtRisco;
    private javax.swing.JTextField txtRti;
    private javax.swing.JTextField txtSistema;
    private javax.swing.JTextField txtVazaoBomba;
    private javax.swing.JTextField txtVazaoH;
    private javax.swing.JTextField txtVazaoH1;
    private javax.swing.JTextField txtVazaoHM;
    private javax.swing.JTextField txtVazaoR;
    private javax.swing.JTextField txtVazaoS;
    private javax.swing.JTextField txtVeloH;
    private javax.swing.JTextField txtVeloH1;
    private javax.swing.JTextField txtVeloHM;
    private javax.swing.JTextField txtVeloR;
    private javax.swing.JTextField txtVeloS;
    // End of variables declaration//GEN-END:variables
}
