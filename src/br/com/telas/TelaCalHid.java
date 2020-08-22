package br.com.telas;

import java.sql.*;
import br.com.connection.ModuloConexao;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCalHid extends javax.swing.JInternalFrame {

    // conexão com o banco 
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // objeto para formatação decimal
    DecimalFormat df = new DecimalFormat("###.##");
    // objeto da tela de seleção de tipo 1 ou 2
    Tipo tp = new Tipo();
    TelaDepositos td = new TelaDepositos();
    TelaEspeciais te = new TelaEspeciais();

    // método construtor 
    public TelaCalHid() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    // variáveis para verificação do tipo de sistema 
    public String divisao, sistema;
    public float area, cargadeincendio, altura, rti;

    String tipos, divi, ocupa, riscoDaOcupacao;
    int cf;
    double cargaInc;

    //variaveis para exportação
    public static String risco, ocupacao, tipoSistema, matTub, areaT, alturaT, divisaoT;

    public static double qtdHidrantes, vazaoTH, vazaoTH1, vazaoTHM, vazaoTR, vazaoTS, vazaoTB,
            altMH, altMH1, altMHM, altMB, altMR, altMS, diaH, diaH1, diaHM, diaR, diaS, velH,
            velH1, velHM, velR, velS, pcH, pcH1, pcHM, pcR, pcS, desH, desH1, desHM, desR, desS,
            volRti, diaMangH, compTotH, compTotH1, compTotHM, compTotR, compTotS, compMangH, compMangH1,
            compMangHM, diaMangH1, diaMangHM, difPressa;

    public void exportarProj() {
        risco = riscoDaOcupacao;
        ocupacao = txtOcupa.getText();
        tipoSistema = txtSistema.getText();
        matTub = cbMaterialTubulacao.getSelectedItem().toString();
        qtdHidrantes = Double.parseDouble(txtNumeroHidrantes.getText());
        vazaoTH = vazaoH;
        vazaoTH1 = vazaoH1;
        vazaoTHM = vazaoHM;
        vazaoTR = vazaoR;
        vazaoTS = vazaoS;
        vazaoTB = vazaoBomba;
        altMH = pressaoH;
        altMH1 = pressaoH1;
        altMHM = pressaoHM;
        altMB = alturaManometricaBomba;
        altMS = alturaManometricaS;
        altMR = alturaManometricaR;
        diaH = diametroTubulacao;
        diaH1 = diametroTubulacao;
        diaHM = diametroTubulacao;
        diaR = diametroTubulacao;
        diaS = diametroS;
        diaMangH = diametroMangueiraH;
        velH = velocidadeH;
        velH1 = velocidadeH1;
        velHM = velocidadeHM;
        velR = velocidadeR;
        velS = velocidadeS;
        pcH = alturaMH - pressaoH - desnivelH;
        pcH1 = alturaMH1 - pressaoH1 - desnivelH1;
        pcHM = alturaMHM - Math.abs(desnivelHM);
        pcR = alturaMR - desnivelR;
        pcS = alturaManometricaBomba - alturaManometricaS;
        desH = desnivelH;
        desH1 = desnivelH1;
        desHM = desnivelHM;
        desR = desnivelR;
        desS = desnivelS;
        volRti = Double.parseDouble(txtRti.getText());
        compTotH = comprimentoTotalH;
        compTotH1 = comprimentoTotalH1;
        compTotHM = comprimentoTotalHM;
        compTotR = comprimentoTotalR;
        compTotS = comprimentoTotalS;
        compMangH = comprimentoMangueiraH;
        compMangH1 = comprimentoMangueiraH1;
        compMangHM = comprimentoMangueiraHM;
        diaMangH1 = diametroMangueiraH1;
        diaMangHM = diametroMangueiraHM;
        difPressa = diferencaPressao;
        areaT = txtArea.getText();
        alturaT = txtAltura.getText();
        divisaoT = txtDivisao.getText();

    }

    // variáveis para cálculo hidraulico
    double diametroTubulacao, comprimentoTotalAteBomba, coeficienteHW, perdaCargaMangueiraH,
            perdaCargaMangueiraH1, perdaCargaMangueiraHM, alturaMH, alturaMH1, alturaMHM,
            alturaMS, alturaMR, alturaManometricaBomba, vazaoBomba, alturaManometricaS, alturaManometricaR, diferencaPressao;

    // trecho h1 até A
    double vazaoH, vazaoHM3, pressaoH, velocidadeH, desnivelH, comprimentoH, comprimentoMangueiraH, diametroMangueiraH,
            valvRetLeveH, valvRetPesadaH, valvAnguloH, tePassagemH, teLateralH,
            curva45H, valvGavetaH, valvulaGloboH, cotovelo45H, curva9015H,
            curva901H, cotovelo90RLH, cotovelo90RMH, cotovelo90RCH,
            comprimentoTotalH;
    // trecho h1' A
    double vazaoH1, vazaoH1M3, pressaoH1, velocidadeH1, desnivelH1, comprimentoH1, comprimentoMangueiraH1, diametroMangueiraH1,
            valvRetLeveH1, valvRetPesadaH1, valvAnguloH1, tePassagemH1, teLateralH1,
            curva45H1, valvGavetaH1, valvulaGloboH1, cotovelo45H1, curva9015H1,
            curva901H1, cotovelo90RLH1, cotovelo90RMH1, cotovelo90RCH1,
            comprimentoTotalH1;
    // trecho H mais favoravel até a bomba
    double vazaoHM, vazaoHMM3, pressaoHM, velocidadeHM, desnivelHM, comprimentoHM, comprimentoMangueiraHM, diametroMangueiraHM,
            valvRetLeveHM, valvRetPesadaHM, valvAnguloHM, tePassagemHM, teLateralHM,
            curva45HM, valvGavetaHM, valvulaGloboHM, cotovelo45HM, curva9015HM,
            curva901HM, cotovelo90RLHM, cotovelo90RMHM, cotovelo90RCHM,
            comprimentoTotalHM;

    // trecho A até a bomba
    double vazaoR, vazaoRM3, pressaoR, velocidadeR, desnivelR, comprimentoR,
            valvRetLeveR, valvRetPesadaR, valvAnguloR, tePassagemR, teLateralR,
            curva45R, valvGavetaR, valvulaGloboR, cotovelo45R, curva9015R,
            curva901R, cotovelo90RLR, cotovelo90RMR, cotovelo90RCR,
            comprimentoTotalR, esguicho1, esguicho2, esguicho3;

    // trecho da bomba até o reservatório
    double vazaoS, vazaoSM3, pressaoS, velocidadeS, desnivelS, comprimentoS, diametroS,
            valvRetLeveS, valvRetPesadaS, valvAnguloS, tePassagemS, teLateralS,
            curva45S, valvGavetaS, valvulaGloboS, cotovelo45S, curva9015S,
            curva901S, cotovelo90RLS, cotovelo90RMS, cotovelo90RCS, entradaS, comprimentoTotalS;

    // método para pegar os valores da tela depósitos
    public void recebe() {
        divi = TelaDepositos.div;
        ocupa = TelaDepositos.ocup;
        cargaInc = TelaDepositos.carg;
        txtDivisao.setText(divi);
        txtOcupa.setText(ocupa);
        txtCargaIncendio.setText(String.valueOf(cargaInc));

    }

    // método para pegar os valoers da tela especiais
    public void recebeEsp() {
        divi = TelaEspeciais.divi;
        ocupa = TelaEspeciais.ocupacaoEsp;
        cargaInc = TelaEspeciais.cargadeincendio;
        txtDivisao.setText(divi);
        txtOcupa.setText(ocupa);
        txtCargaIncendio.setText(String.valueOf(cargaInc));
    }

    // método para pegar os desníveis
    public void desnivel() {
        desnivelH = Double.parseDouble(txtDesnivelH.getText().replaceAll(",", "."));
        desnivelH1 = Double.parseDouble(txtDesnivelH1.getText().replaceAll(",", "."));
        desnivelR = Double.parseDouble(txtDesnivelR.getText().replaceAll(",", "."));
        desnivelS = Double.parseDouble(txtDesnivelS.getText().replaceAll(",", "."));
    }

    // método para setar as velocidades
    public void velocidade() {
        velocidadeH = vazaoHM3 / (((Math.pow(diametroTubulacao, 2)) * Math.PI) / 4);
        velocidadeH1 = vazaoH1M3 / (((Math.pow(diametroTubulacao, 2)) * Math.PI) / 4);
        velocidadeR = vazaoRM3 / (((Math.pow(diametroTubulacao, 2)) * Math.PI) / 4);
        velocidadeS = vazaoSM3 / (((Math.pow(diametroS, 2)) * Math.PI) / 4);

        txtVelocidadeH.setText(String.valueOf(df.format(velocidadeH)));
        txtVelocidadeH1.setText(String.valueOf(df.format(velocidadeH1)));
        txtVelocidadeR.setText(String.valueOf(df.format(velocidadeR)));
        txtVelocidadeS.setText(String.valueOf(df.format(velocidadeS)));
    }

    // metodo para calcuar a perda de carga nos trechos
    public void perdadeCarga() {

        // vazaoS= vazaoH + vazaoH1;
        //vazaoR= vazaoH + vazaoH1;
        esguicho1 = Double.parseDouble(txtEsg1.getText());
        esguicho2 = Double.parseDouble(txtEsg2.getText());
        
        vazaoHM3 = vazaoH * (1.7 * (Math.pow(10, -5)));
        vazaoH1M3 = vazaoH1 * (1.7 * (Math.pow(10, -5)));
        //vazaoSM3 = vazaoS * (1.7*(Math.pow(10, -5)));
        // vazaoRM3 = vazaoR * (1.7*(Math.pow(10, -5)));
        perdaCargaMangueiraH = (comprimentoMangueiraH * 10.65 * (Math.pow(vazaoHM3, 1.85)) * (Math.pow(140, -1.85))
                * (Math.pow(diametroMangueiraH, -4.87)));
        
         perdaCargaMangueiraH1 = (comprimentoMangueiraH1 * 10.65 * (Math.pow(vazaoH1M3, 1.85)) * (Math.pow(140, -1.85))
                * (Math.pow(diametroMangueiraH1, -4.87)));

        alturaMH = (comprimentoTotalH * 10.65 * (Math.pow(vazaoHM3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                * (Math.pow(diametroTubulacao, -4.87))) + (perdaCargaMangueiraH) + (desnivelH) + (esguicho1)+ 15;

        alturaMH1 = (comprimentoTotalH1 * 10.65 * (Math.pow(vazaoH1M3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                * (Math.pow(diametroTubulacao, -4.87))) + (perdaCargaMangueiraH1) + (desnivelH1) + (esguicho2)+ 15;
        //System.out.println(vazaoH1M3);

        while (Math.abs(alturaMH - alturaMH1) > (0.5)) {
            if (alturaMH > alturaMH1) {
                vazaoH1M3 = vazaoH1M3 + 0.00002;
            } else {
                vazaoH1M3 = vazaoH1M3 - 0.00002;
            }
            perdaCargaMangueiraH1 = (comprimentoMangueiraH1 * 10.65 * (Math.pow(vazaoH1M3, 1.85)) * (Math.pow(140, -1.85))
                * (Math.pow(diametroMangueiraH1, -4.87)));
            
            alturaMH1 = (comprimentoTotalH1 * 10.65 * (Math.pow(vazaoH1M3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                    * (Math.pow(diametroTubulacao, -4.87))) + (perdaCargaMangueiraH1) + (desnivelH1) + (esguicho2)+ 15;
            // System.out.println(alturaMH);
            //System.out.println(alturaMH1);

        }
        // System.out.println(vazaoH1M3);
        //System.out.println(alturaMH);
        System.out.println(alturaMH1);
        System.out.println(esguicho2);

        vazaoRM3 = vazaoHM3 + vazaoH1M3;
        vazaoSM3 = vazaoRM3;
        vazaoR = (vazaoRM3) / (1.7 * (Math.pow(10, -5)));
        vazaoS = (vazaoSM3) / (1.7 * (Math.pow(10, -5)));
        vazaoH1 = (vazaoH1M3) / (1.7 * (Math.pow(10, -5)));

        alturaMR = (comprimentoTotalR * 10.65 * (Math.pow(vazaoRM3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                * (Math.pow(diametroTubulacao, -4.87))) + (desnivelR);

        alturaMS = (comprimentoTotalS * 10.65 * (Math.pow(vazaoSM3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                * (Math.pow(diametroS, -4.87))) + (desnivelS);

        diferencaPressao = alturaMH - alturaMH1;

        alturaManometricaBomba = alturaMH + alturaMH1 + alturaMR + alturaMS;
        alturaManometricaR = alturaMH + alturaMH1;
        alturaManometricaS = alturaManometricaBomba - alturaMS;
        vazaoBomba = vazaoR;
        //pressaoH1 = Math.floor(alturaMH1 - perdaCargaMangueiraH1 - desnivelH1);
        txtPressaoBomba.setText(String.valueOf(df.format(alturaManometricaBomba)));
        txtVazaoBomba.setText(String.valueOf(df.format(vazaoBomba)));
        txtPressaoR.setText(String.valueOf(df.format(alturaManometricaR)));
        txtVazaoR.setText(String.valueOf(df.format(vazaoR)));
        txtPressaoS.setText(String.valueOf(df.format(alturaManometricaS)));
        txtVazaoS.setText(String.valueOf(df.format(vazaoS)));
        txtVazaoH.setText(String.valueOf(df.format(vazaoH)));
        txtVazaoH1.setText(String.valueOf(df.format(vazaoH1)));
        txtPressaoH.setText(String.valueOf(df.format(pressaoH)));
        txtPressaoH1.setText(String.valueOf(df.format(pressaoH1)));
        txtDiferencaPressao.setText((df.format(diferencaPressao)));

    }

    
    // perda de carga nas mangueiras
   /* public void perdaCargaMangueiraH() {
        if ("25mm".equals(cbDiametroMangueiraH.getSelectedItem())) {
            perdaCargaMangueiraH = ((9.41 * comprimentoMangueiraH)/ 100);
        } else if ("40mm".equals(cbDiametroMangueiraH.getSelectedItem())) {
            perdaCargaMangueiraH = ((19.6* comprimentoMangueiraH)/ 100);
        } else if ("65mm".equals(cbDiametroMangueiraH.getSelectedItem())) {
            perdaCargaMangueiraH = ((10.6 *comprimentoMangueiraH)/ 100);
        }
        if ("25mm".equals(cbDiametroMangueiraH1.getSelectedItem())) {
            perdaCargaMangueiraH1 = ((9.41 * comprimentoMangueiraH1)/ 100);
        } else if ("40mm".equals(cbDiametroMangueiraH1.getSelectedItem())) {
            perdaCargaMangueiraH1 = ((19.6 * comprimentoMangueiraH1)/ 100);
        } else if ("65mm".equals(cbDiametroMangueiraH1.getSelectedItem())) {
            perdaCargaMangueiraH1 = ((10.6 *comprimentoMangueiraH1)/ 100);
        }
        if ("25mm".equals(cbDiametroMangueiraHM.getSelectedItem())) {
            perdaCargaMangueiraHM = ((9.41 * comprimentoMangueiraHM)/ 100);
        } else if ("40mm".equals(cbDiametroMangueiraHM.getSelectedItem())) {
            perdaCargaMangueiraHM = ((19.6 * comprimentoMangueiraHM)/ 100);
        } else if ("65mm".equals(cbDiametroMangueiraHM.getSelectedItem())) {
            perdaCargaMangueiraHM = ((10.6 *comprimentoMangueiraHM)/ 100);
        }
    }*/

    // metodo para calcular o comprimento equivalente total 
    public void comprimentoH() {
        if ("50mm".equals(cbDiametroTubulacao.getSelectedItem())) {
            // parâmetros para H1'
            valvRetLeveH1 = 4.2 * Double.parseDouble(spValvRetLeveH1.getValue().toString());
            valvRetPesadaH1 = 6.4 * Double.parseDouble(spValvRetPesadaH1.getValue().toString());
            valvAnguloH1 = 8.5 * Double.parseDouble(spValvAnguloH1.getValue().toString());
            tePassagemH1 = 1.1 * Double.parseDouble(spTePassagemH1.getValue().toString());
            teLateralH1 = 3.5 * Double.parseDouble(spTeLateralH1.getValue().toString());
            curva45H1 = 0.4 * Double.parseDouble(spCurva45H1.getValue().toString());
            valvGavetaH1 = 0.4 * Double.parseDouble(spValvGavetaH1.getValue().toString());
            valvulaGloboH1 = 17.4 * Double.parseDouble(spValvGloboH1.getValue().toString());
            cotovelo45H1 = 0.8 * Double.parseDouble(spCotovelo45H1.getValue().toString());
            curva9015H1 = 0.6 * Double.parseDouble(spCurva9015H1.getValue().toString());
            curva901H1 = 0.9 * Double.parseDouble(spCurva901H1.getValue().toString());
            cotovelo90RLH1 = 1.1 * Double.parseDouble(spCotovelo90RLH1.getValue().toString());
            cotovelo90RMH1 = 1.4 * Double.parseDouble(spCotovelo90RMH1.getValue().toString());
            cotovelo90RCH1 = 1.7 * Double.parseDouble(spCotovelo90RCH1.getValue().toString());

            //parâmetros para H1
            valvRetLeveH = 4.2 * Double.parseDouble(spValvRetLeveH.getValue().toString());
            valvRetPesadaH = 6.4 * Double.parseDouble(spValvRetPesadaH.getValue().toString());
            valvAnguloH = 8.5 * Double.parseDouble(spValvAnguloH.getValue().toString());
            tePassagemH = 1.1 * Double.parseDouble(spTePassagemH.getValue().toString());
            teLateralH = 3.5 * Double.parseDouble(spTeLateralH.getValue().toString());
            curva45H = 0.4 * Double.parseDouble(spCurva45H.getValue().toString());
            valvGavetaH = 0.4 * Double.parseDouble(spValvGavetaH.getValue().toString());
            valvulaGloboH = 17.4 * Double.parseDouble(spValvGloboH.getValue().toString());
            cotovelo45H = 0.8 * Double.parseDouble(spCotovelo45H.getValue().toString());
            curva9015H = 0.6 * Double.parseDouble(spCurva9015H.getValue().toString());
            curva901H = 0.9 * Double.parseDouble(spCurva901H.getValue().toString());
            cotovelo90RLH = 1.1 * Double.parseDouble(spCotovelo90RLH.getValue().toString());
            cotovelo90RMH = 1.4 * Double.parseDouble(spCotovelo90RMH.getValue().toString());
            cotovelo90RCH = 1.7 * Double.parseDouble(spCotovelo90RCH.getValue().toString());

            //parâmetros para H mais favorável
            valvRetLeveHM = 4.2 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 6.4 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 8.5 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.1 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 3.5 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.4 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.4 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 17.4 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 0.8 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 0.6 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 0.9 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.1 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 1.4 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 1.7 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());

            // parâmetros do recalque
            valvRetLeveR = 4.2 * Double.parseDouble(spValvRetLeveR.getValue().toString());
            valvRetPesadaR = 6.4 * Double.parseDouble(spValvRetPesadaR.getValue().toString());
            valvAnguloR = 8.5 * Double.parseDouble(spValvAnguloR.getValue().toString());
            tePassagemR = 1.1 * Double.parseDouble(spTePassagemR.getValue().toString());
            teLateralR = 3.5 * Double.parseDouble(spTeLateralR.getValue().toString());
            curva45R = 0.4 * Double.parseDouble(spCurva45R.getValue().toString());
            valvGavetaR = 0.4 * Double.parseDouble(spValvGavetaR.getValue().toString());
            valvulaGloboR = 17.4 * Double.parseDouble(spValvGloboR.getValue().toString());
            cotovelo45R = 0.8 * Double.parseDouble(spCotovelo45R.getValue().toString());
            curva9015R = 0.6 * Double.parseDouble(spCurva9015R.getValue().toString());
            curva901R = 0.9 * Double.parseDouble(spCurva901R.getValue().toString());
            cotovelo90RLR = 1.1 * Double.parseDouble(spCotovelo90RLR.getValue().toString());
            cotovelo90RMR = 1.4 * Double.parseDouble(spCotovelo90RMR.getValue().toString());
            cotovelo90RCR = 1.7 * Double.parseDouble(spCotovelo90RCR.getValue().toString());

        } else if ("65mm".equals(cbDiametroTubulacao.getSelectedItem())) {
            // parâmetros para H1
            valvRetLeveH = 5.2 * Double.parseDouble(spValvRetLeveH.getValue().toString());
            valvRetPesadaH = 8.1 * Double.parseDouble(spValvRetPesadaH.getValue().toString());
            valvAnguloH = 10.0 * Double.parseDouble(spValvAnguloH.getValue().toString());
            tePassagemH = 1.3 * Double.parseDouble(spTePassagemH.getValue().toString());
            teLateralH = 4.3 * Double.parseDouble(spTeLateralH.getValue().toString());
            curva45H = 0.5 * Double.parseDouble(spCurva45H.getValue().toString());
            valvGavetaH = 0.4 * Double.parseDouble(spValvGavetaH.getValue().toString());
            valvulaGloboH = 21.0 * Double.parseDouble(spValvGloboH.getValue().toString());
            cotovelo45H = 0.9 * Double.parseDouble(spCotovelo45H.getValue().toString());
            curva9015H = 0.8 * Double.parseDouble(spCurva9015H.getValue().toString());
            curva901H = 1.0 * Double.parseDouble(spCurva901H.getValue().toString());
            cotovelo90RLH = 1.3 * Double.parseDouble(spCotovelo90RLH.getValue().toString());
            cotovelo90RMH = 1.7 * Double.parseDouble(spCotovelo90RMH.getValue().toString());
            cotovelo90RCH = 2.0 * Double.parseDouble(spCotovelo90RCH.getValue().toString());

            //parâmetros para H1'
            valvRetLeveH1 = 5.2 * Double.parseDouble(spValvRetLeveH1.getValue().toString());
            valvRetPesadaH1 = 8.1 * Double.parseDouble(spValvRetPesadaH1.getValue().toString());
            valvAnguloH1 = 10.0 * Double.parseDouble(spValvAnguloH1.getValue().toString());
            tePassagemH1 = 1.3 * Double.parseDouble(spTePassagemH1.getValue().toString());
            teLateralH1 = 4.3 * Double.parseDouble(spTeLateralH1.getValue().toString());
            curva45H1 = 0.5 * Double.parseDouble(spCurva45H1.getValue().toString());
            valvGavetaH1 = 0.4 * Double.parseDouble(spValvGavetaH1.getValue().toString());
            valvulaGloboH1 = 21.0 * Double.parseDouble(spValvGloboH1.getValue().toString());
            cotovelo45H1 = 0.9 * Double.parseDouble(spCotovelo45H1.getValue().toString());
            curva9015H1 = 0.8 * Double.parseDouble(spCurva9015H1.getValue().toString());
            curva901H1 = 1.0 * Double.parseDouble(spCurva901H1.getValue().toString());
            cotovelo90RLH1 = 1.3 * Double.parseDouble(spCotovelo90RLH1.getValue().toString());
            cotovelo90RMH1 = 1.7 * Double.parseDouble(spCotovelo90RMH1.getValue().toString());
            cotovelo90RCH1 = 2.0 * Double.parseDouble(spCotovelo90RCH1.getValue().toString());

            //parâmetros para H mais favorável
            valvRetLeveHM = 5.2 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 8.1 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 10.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.3 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 4.3 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.5 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.4 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 21.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 0.9 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 0.8 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.0 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.3 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 1.7 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 2.0 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());

            // parâmetros para recalque 
            valvRetLeveR = 5.2 * Double.parseDouble(spValvRetLeveR.getValue().toString());
            valvRetPesadaR = 8.1 * Double.parseDouble(spValvRetPesadaR.getValue().toString());
            valvAnguloR = 10.0 * Double.parseDouble(spValvAnguloR.getValue().toString());
            tePassagemR = 1.3 * Double.parseDouble(spTePassagemR.getValue().toString());
            teLateralR = 4.3 * Double.parseDouble(spTeLateralR.getValue().toString());
            curva45R = 0.5 * Double.parseDouble(spCurva45R.getValue().toString());
            valvGavetaR = 0.4 * Double.parseDouble(spValvGavetaR.getValue().toString());
            valvulaGloboR = 21.0 * Double.parseDouble(spValvGloboR.getValue().toString());
            cotovelo45R = 0.9 * Double.parseDouble(spCotovelo45R.getValue().toString());
            curva9015R = 0.8 * Double.parseDouble(spCurva9015R.getValue().toString());
            curva901R = 1.0 * Double.parseDouble(spCurva901R.getValue().toString());
            cotovelo90RLR = 1.3 * Double.parseDouble(spCotovelo90RLR.getValue().toString());
            cotovelo90RMR = 1.7 * Double.parseDouble(spCotovelo90RMR.getValue().toString());
            cotovelo90RCR = 2.0 * Double.parseDouble(spCotovelo90RCR.getValue().toString());

        } else if ("75mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveH = 6.3 * Double.parseDouble(spValvRetLeveH.getValue().toString());
            valvRetPesadaH = 9.7 * Double.parseDouble(spValvRetPesadaH.getValue().toString());
            valvAnguloH = 13.0 * Double.parseDouble(spValvAnguloH.getValue().toString());
            tePassagemH = 1.6 * Double.parseDouble(spTePassagemH.getValue().toString());
            teLateralH = 5.2 * Double.parseDouble(spTeLateralH.getValue().toString());
            curva45H = 0.6 * Double.parseDouble(spCurva45H.getValue().toString());
            valvGavetaH = 0.5 * Double.parseDouble(spValvGavetaH.getValue().toString());
            valvulaGloboH = 26.0 * Double.parseDouble(spValvGloboH.getValue().toString());
            cotovelo45H = 1.2 * Double.parseDouble(spCotovelo45H.getValue().toString());
            curva9015H = 1.0 * Double.parseDouble(spCurva9015H.getValue().toString());
            curva901H = 1.3 * Double.parseDouble(spCurva901H.getValue().toString());
            cotovelo90RLH = 1.6 * Double.parseDouble(spCotovelo90RLH.getValue().toString());
            cotovelo90RMH = 2.1 * Double.parseDouble(spCotovelo90RMH.getValue().toString());
            cotovelo90RCH = 2.5 * Double.parseDouble(spCotovelo90RCH.getValue().toString());

            //parâmetros para H1'
            valvRetLeveH1 = 6.3 * Double.parseDouble(spValvRetLeveH1.getValue().toString());
            valvRetPesadaH1 = 9.7 * Double.parseDouble(spValvRetPesadaH1.getValue().toString());
            valvAnguloH1 = 13.0 * Double.parseDouble(spValvAnguloH1.getValue().toString());
            tePassagemH1 = 1.6 * Double.parseDouble(spTePassagemH1.getValue().toString());
            teLateralH1 = 5.2 * Double.parseDouble(spTeLateralH1.getValue().toString());
            curva45H1 = 0.6 * Double.parseDouble(spCurva45H1.getValue().toString());
            valvGavetaH1 = 0.5 * Double.parseDouble(spValvGavetaH1.getValue().toString());
            valvulaGloboH1 = 26.0 * Double.parseDouble(spValvGloboH1.getValue().toString());
            cotovelo45H1 = 1.2 * Double.parseDouble(spCotovelo45H1.getValue().toString());
            curva9015H1 = 1.0 * Double.parseDouble(spCurva9015H1.getValue().toString());
            curva901H1 = 1.3 * Double.parseDouble(spCurva901H1.getValue().toString());
            cotovelo90RLH1 = 1.6 * Double.parseDouble(spCotovelo90RLH1.getValue().toString());
            cotovelo90RMH1 = 2.1 * Double.parseDouble(spCotovelo90RMH1.getValue().toString());
            cotovelo90RCH1 = 2.5 * Double.parseDouble(spCotovelo90RCH1.getValue().toString());

            //parâmetros para H mais favorável
            valvRetLeveHM = 6.3 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 9.7 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 13.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.6 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 5.2 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.6 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.5 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 26.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 1.2 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 1.0 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.3 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.6 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 2.1 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 2.5 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());

            // parâmetros para recalque 
            valvRetLeveR = 6.3 * Double.parseDouble(spValvRetLeveR.getValue().toString());
            valvRetPesadaR = 9.7 * Double.parseDouble(spValvRetPesadaR.getValue().toString());
            valvAnguloR = 13.0 * Double.parseDouble(spValvAnguloR.getValue().toString());
            tePassagemR = 1.6 * Double.parseDouble(spTePassagemR.getValue().toString());
            teLateralR = 5.2 * Double.parseDouble(spTeLateralR.getValue().toString());
            curva45R = 0.6 * Double.parseDouble(spCurva45R.getValue().toString());
            valvGavetaR = 0.5 * Double.parseDouble(spValvGavetaR.getValue().toString());
            valvulaGloboR = 26.0 * Double.parseDouble(spValvGloboR.getValue().toString());
            cotovelo45R = 1.2 * Double.parseDouble(spCotovelo45R.getValue().toString());
            curva9015R = 1.0 * Double.parseDouble(spCurva9015R.getValue().toString());
            curva901R = 1.3 * Double.parseDouble(spCurva901R.getValue().toString());
            cotovelo90RLR = 1.6 * Double.parseDouble(spCotovelo90RLR.getValue().toString());
            cotovelo90RMR = 2.1 * Double.parseDouble(spCotovelo90RMR.getValue().toString());
            cotovelo90RCR = 2.5 * Double.parseDouble(spCotovelo90RCR.getValue().toString());

        } else if ("100mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveH = 6.4 * Double.parseDouble(spValvRetLeveH.getValue().toString());
            valvRetPesadaH = 12.9 * Double.parseDouble(spValvRetPesadaH.getValue().toString());
            valvAnguloH = 17.0 * Double.parseDouble(spValvAnguloH.getValue().toString());
            tePassagemH = 2.1 * Double.parseDouble(spTePassagemH.getValue().toString());
            teLateralH = 6.7 * Double.parseDouble(spTeLateralH.getValue().toString());
            curva45H = 0.7 * Double.parseDouble(spCurva45H.getValue().toString());
            valvGavetaH = 0.7 * Double.parseDouble(spValvGavetaH.getValue().toString());
            valvulaGloboH = 34.0 * Double.parseDouble(spValvGloboH.getValue().toString());
            cotovelo45H = 1.5 * Double.parseDouble(spCotovelo45H.getValue().toString());
            curva9015H = 1.3 * Double.parseDouble(spCurva9015H.getValue().toString());
            curva901H = 1.6 * Double.parseDouble(spCurva901H.getValue().toString());
            cotovelo90RLH = 2.1 * Double.parseDouble(spCotovelo90RLH.getValue().toString());
            cotovelo90RMH = 2.8 * Double.parseDouble(spCotovelo90RMH.getValue().toString());
            cotovelo90RCH = 3.4 * Double.parseDouble(spCotovelo90RCH.getValue().toString());

            //parâmetros para H1'
            valvRetLeveH1 = 6.4 * Double.parseDouble(spValvRetLeveH1.getValue().toString());
            valvRetPesadaH1 = 12.9 * Double.parseDouble(spValvRetPesadaH1.getValue().toString());
            valvAnguloH1 = 17.0 * Double.parseDouble(spValvAnguloH1.getValue().toString());
            tePassagemH1 = 2.1 * Double.parseDouble(spTePassagemH1.getValue().toString());
            teLateralH1 = 6.7 * Double.parseDouble(spTeLateralH1.getValue().toString());
            curva45H1 = 0.7 * Double.parseDouble(spCurva45H1.getValue().toString());
            valvGavetaH1 = 0.7 * Double.parseDouble(spValvGavetaH1.getValue().toString());
            valvulaGloboH1 = 34.0 * Double.parseDouble(spValvGloboH1.getValue().toString());
            cotovelo45H1 = 1.5 * Double.parseDouble(spCotovelo45H1.getValue().toString());
            curva9015H1 = 1.3 * Double.parseDouble(spCurva9015H1.getValue().toString());
            curva901H1 = 1.6 * Double.parseDouble(spCurva901H1.getValue().toString());
            cotovelo90RLH1 = 2.1 * Double.parseDouble(spCotovelo90RLH1.getValue().toString());
            cotovelo90RMH1 = 2.8 * Double.parseDouble(spCotovelo90RMH1.getValue().toString());
            cotovelo90RCH1 = 3.4 * Double.parseDouble(spCotovelo90RCH1.getValue().toString());

            //parâmetros para H mais favorável
            valvRetLeveHM = 6.4 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 12.9 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 17.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 2.1 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 6.7 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.7 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.7 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 34.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 1.5 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 1.3 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.6 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 2.1 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 2.8 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 3.4 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());

            // parâmetros para recalque 
            valvRetLeveR = 6.4 * Double.parseDouble(spValvRetLeveR.getValue().toString());
            valvRetPesadaR = 12.9 * Double.parseDouble(spValvRetPesadaR.getValue().toString());
            valvAnguloR = 17.0 * Double.parseDouble(spValvAnguloR.getValue().toString());
            tePassagemR = 2.1 * Double.parseDouble(spTePassagemR.getValue().toString());
            teLateralR = 6.7 * Double.parseDouble(spTeLateralR.getValue().toString());
            curva45R = 0.7 * Double.parseDouble(spCurva45R.getValue().toString());
            valvGavetaR = 0.7 * Double.parseDouble(spValvGavetaR.getValue().toString());
            valvulaGloboR = 34.0 * Double.parseDouble(spValvGloboR.getValue().toString());
            cotovelo45R = 1.5 * Double.parseDouble(spCotovelo45R.getValue().toString());
            curva9015R = 1.3 * Double.parseDouble(spCurva9015R.getValue().toString());
            curva901R = 1.6 * Double.parseDouble(spCurva901R.getValue().toString());
            cotovelo90RLR = 2.1 * Double.parseDouble(spCotovelo90RLR.getValue().toString());
            cotovelo90RMR = 2.8 * Double.parseDouble(spCotovelo90RMR.getValue().toString());
            cotovelo90RCR = 3.4 * Double.parseDouble(spCotovelo90RCR.getValue().toString());

        }
        // comprimento total de H1
        comprimentoTotalH = valvRetLeveH + valvRetPesadaH + valvAnguloH + tePassagemH + teLateralH
                + curva45H + valvGavetaH + valvulaGloboH + cotovelo45H + curva9015H
                + curva901H + cotovelo90RLH + cotovelo90RMH + cotovelo90RCH
                + (comprimentoH = Double.parseDouble(txtComprimentoH.getText().replaceAll(",", ".")));
        // comprimento total de H1'
        comprimentoTotalH1 = valvRetLeveH1 + valvRetPesadaH1 + valvAnguloH1 + tePassagemH1 + teLateralH1
                + curva45H1 + valvGavetaH1 + valvulaGloboH1 + cotovelo45H1 + curva9015H1
                + curva901H1 + cotovelo90RLH1 + cotovelo90RMH1 + cotovelo90RCH1
                + (comprimentoH1 = Double.parseDouble(txtComprimentoH1.getText().replaceAll(",", ".")));
        //comprimento total do recalque
        comprimentoTotalR = valvRetLeveR + valvRetPesadaR + valvAnguloR + tePassagemR + teLateralR
                + curva45R + valvGavetaR + valvulaGloboR + cotovelo45R + curva9015R
                + curva901R + cotovelo90RLR + cotovelo90RMR + cotovelo90RCR
                + (comprimentoR = Double.parseDouble(txtComprimentoR.getText().replaceAll(",", ".")));
        // comprimento total de H mais favorável
        comprimentoTotalHM = valvRetLeveHM + valvRetPesadaHM + valvAnguloHM + tePassagemHM + teLateralHM
                + curva45HM + valvGavetaHM + valvulaGloboHM + cotovelo45HM + curva9015HM
                + curva901HM + cotovelo90RLHM + cotovelo90RMHM + cotovelo90RCHM
                + (comprimentoHM = Double.parseDouble(txtComprimentoHM.getText().replaceAll(",", ".")));

        comprimentoTotalAteBomba = comprimentoTotalH + comprimentoTotalH1 + comprimentoTotalR;

    }

    //mátodo para calcular o comprimento da succao
    public void comprimentoS() {
        if ("65mm".equals(cbDiametroS.getSelectedItem())) {

            valvRetLeveS = 5.2 * Double.parseDouble(spValvRetLeveS.getValue().toString());
            valvRetPesadaS = 8.1 * Double.parseDouble(spValvRetPesadaS.getValue().toString());
            valvAnguloS = 10.0 * Double.parseDouble(spValvAnguloS.getValue().toString());
            tePassagemS = 1.3 * Double.parseDouble(spTePassagemS.getValue().toString());
            teLateralS = 4.3 * Double.parseDouble(spTeLateralS.getValue().toString());
            curva45S = 0.5 * Double.parseDouble(spCurva45S.getValue().toString());
            valvGavetaS = 0.4 * Double.parseDouble(spValvGavetaS.getValue().toString());
            valvulaGloboS = 21.0 * Double.parseDouble(spValvGloboS.getValue().toString());
            cotovelo45S = 0.9 * Double.parseDouble(spCotovelo45S.getValue().toString());
            curva9015S = 0.8 * Double.parseDouble(spCurva9015S.getValue().toString());
            curva901S = 1.0 * Double.parseDouble(spCurva901S.getValue().toString());
            cotovelo90RLS = 1.3 * Double.parseDouble(spCotovelo90RLS.getValue().toString());
            cotovelo90RMS = 1.7 * Double.parseDouble(spCotovelo90RMS.getValue().toString());
            cotovelo90RCS = 2.0 * Double.parseDouble(spCotovelo90RCS.getValue().toString());

            if (rbtnEntradaNormalS.isSelected()) {
                entradaS = (double) 0.9;
            } else if (rbtnEntradadeBordaS.isSelected()) {
                entradaS = (double) 1.9;
            } else if (rbtnValvPeCrivoS.isSelected()) {
                entradaS = (double) 17.0;
            }
        } else if ("75mm".equals(cbDiametroS.getSelectedItem())) {

            valvRetLeveS = 6.3 * Double.parseDouble(spValvRetLeveS.getValue().toString());
            valvRetPesadaS = 9.7 * Double.parseDouble(spValvRetPesadaS.getValue().toString());
            valvAnguloS = 13.0 * Double.parseDouble(spValvAnguloS.getValue().toString());
            tePassagemS = 1.6 * Double.parseDouble(spTePassagemS.getValue().toString());
            teLateralS = 5.2 * Double.parseDouble(spTeLateralS.getValue().toString());
            curva45S = 0.6 * Double.parseDouble(spCurva45S.getValue().toString());
            valvGavetaS = 0.5 * Double.parseDouble(spValvGavetaS.getValue().toString());
            valvulaGloboS = 26.0 * Double.parseDouble(spValvGloboS.getValue().toString());
            cotovelo45S = 1.2 * Double.parseDouble(spCotovelo45S.getValue().toString());
            curva9015S = 1.0 * Double.parseDouble(spCurva9015S.getValue().toString());
            curva901S = 1.3 * Double.parseDouble(spCurva901S.getValue().toString());
            cotovelo90RLS = 1.6 * Double.parseDouble(spCotovelo90RLS.getValue().toString());
            cotovelo90RMS = 2.1 * Double.parseDouble(spCotovelo90RMS.getValue().toString());
            cotovelo90RCS = 2.5 * Double.parseDouble(spCotovelo90RCS.getValue().toString());

            if (rbtnEntradaNormalS.isSelected()) {
                entradaS = (double) 1.1;
            } else if (rbtnEntradadeBordaS.isSelected()) {
                entradaS = (double) 2.2;
            } else if (rbtnValvPeCrivoS.isSelected()) {
                entradaS = (double) 20.0;
            }

        } else if ("100mm".equals(cbDiametroS.getSelectedItem())) {

            valvRetLeveS = 6.4 * Double.parseDouble(spValvRetLeveS.getValue().toString());
            valvRetPesadaS = 12.9 * Double.parseDouble(spValvRetPesadaS.getValue().toString());
            valvAnguloS = 17.0 * Double.parseDouble(spValvAnguloS.getValue().toString());
            tePassagemS = 2.1 * Double.parseDouble(spTePassagemS.getValue().toString());
            teLateralS = 6.7 * Double.parseDouble(spTeLateralS.getValue().toString());
            curva45S = 0.7 * Double.parseDouble(spCurva45S.getValue().toString());
            valvGavetaS = 0.7 * Double.parseDouble(spValvGavetaS.getValue().toString());
            valvulaGloboS = 34.0 * Double.parseDouble(spValvGloboS.getValue().toString());
            cotovelo45S = 1.5 * Double.parseDouble(spCotovelo45S.getValue().toString());
            curva9015S = 1.3 * Double.parseDouble(spCurva9015S.getValue().toString());
            curva901S = 1.6 * Double.parseDouble(spCurva901S.getValue().toString());
            cotovelo90RLS = 2.1 * Double.parseDouble(spCotovelo90RLS.getValue().toString());
            cotovelo90RMS = 2.8 * Double.parseDouble(spCotovelo90RMS.getValue().toString());
            cotovelo90RCS = 3.4 * Double.parseDouble(spCotovelo90RCS.getValue().toString());

            if (rbtnEntradaNormalS.isSelected()) {
                entradaS = (double) 1.6;
            } else if (rbtnEntradadeBordaS.isSelected()) {
                entradaS = (double) 3.2;
            } else if (rbtnValvPeCrivoS.isSelected()) {
                entradaS = (double) 23.0;
            }

        } else if ("125mm".equals(cbDiametroS.getSelectedItem())) {

            valvRetLeveS = 10.4 * Double.parseDouble(spValvRetLeveS.getValue().toString());
            valvRetPesadaS = 16.1 * Double.parseDouble(spValvRetPesadaS.getValue().toString());
            valvAnguloS = 21.0 * Double.parseDouble(spValvAnguloS.getValue().toString());
            tePassagemS = 2.7 * Double.parseDouble(spTePassagemS.getValue().toString());
            teLateralS = 8.4 * Double.parseDouble(spTeLateralS.getValue().toString());
            curva45S = 0.9 * Double.parseDouble(spCurva45S.getValue().toString());
            valvGavetaS = 0.9 * Double.parseDouble(spValvGavetaS.getValue().toString());
            valvulaGloboS = 43.0 * Double.parseDouble(spValvGloboS.getValue().toString());
            cotovelo45S = 1.9 * Double.parseDouble(spCotovelo45S.getValue().toString());
            curva9015S = 1.6 * Double.parseDouble(spCurva9015S.getValue().toString());
            curva901S = 2.1 * Double.parseDouble(spCurva901S.getValue().toString());
            cotovelo90RLS = 2.7 * Double.parseDouble(spCotovelo90RLS.getValue().toString());
            cotovelo90RMS = 3.7 * Double.parseDouble(spCotovelo90RMS.getValue().toString());
            cotovelo90RCS = 4.2 * Double.parseDouble(spCotovelo90RCS.getValue().toString());

            if (rbtnEntradaNormalS.isSelected()) {
                entradaS = (double) 2.0;
            } else if (rbtnEntradadeBordaS.isSelected()) {
                entradaS = (double) 4.0;
            } else if (rbtnValvPeCrivoS.isSelected()) {
                entradaS = (double) 30.0;
            }

        }
        comprimentoTotalS = valvRetLeveS + valvRetPesadaS + valvAnguloS + tePassagemS + teLateralS
                + curva45S + valvGavetaS + valvulaGloboS + cotovelo45S + curva9015S
                + curva901S + cotovelo90RLS + cotovelo90RMS + cotovelo90RCS + entradaS
                + (comprimentoS = Double.parseDouble(txtComprimentoS.getText().replaceAll(",", ".")));

    }

    // método para verificar o tipo de sistema 
    public void tipoSistema() {
        area = Float.parseFloat(txtArea.getText().replaceAll(",", ".")); // pegar o valor da area
        cargadeincendio = Float.parseFloat(txtCargaIncendio.getText()); // valor da carga de incêndio
        altura = Float.parseFloat(txtAltura.getText().replaceAll(",", ".")); // valor da altura
        divisao = txtDivisao.getText();

        cf = tp.getCf();

        // verificação do tipo de sistema 
        if ((("A-2".equals(divisao))
                || ("A-3".equals(divisao))
                || ("C-1".equals(divisao))
                || ("D-1".equals(divisao) & (cargadeincendio <= 300))
                || ("D-2".equals(divisao) & (cargadeincendio <= 300))
                || ("D-3".equals(divisao) & (cargadeincendio <= 300))
                || ("D-4".equals(divisao) & (cargadeincendio <= 300))
                || ("E-1".equals(divisao))
                || ("E-2".equals(divisao))
                || ("E-3".equals(divisao))
                || ("E-4".equals(divisao))
                || ("E-5".equals(divisao))
                || ("E-6".equals(divisao))
                || ("F-1".equals(divisao) & (cargadeincendio <= 300))
                || ("F-2".equals(divisao))
                || ("F-3".equals(divisao))
                || ("F-4".equals(divisao))
                || ("F-8".equals(divisao))
                || ("G-1".equals(divisao))
                || ("G-2".equals(divisao))
                || ("G-3".equals(divisao))
                || ("G-4".equals(divisao))
                || ("H-1".equals(divisao))
                || ("H-2".equals(divisao))
                || ("H-3".equals(divisao))
                || ("H-5".equals(divisao))
                || ("H-6".equals(divisao))
                || ("I-1".equals(divisao))
                || ("J-1".equals(divisao))
                || ("J-2".equals(divisao))
                || ("M-3".equals(divisao)))) {

            if (cf == 0) {
                tp.setVisible(true);
            } else {
                tipos = tp.getTipo();
                txtSistema.setText(tipos);

            }

        } else if (("B-1".equals(divisao))
                || ("B-2".equals(divisao))
                || ("C-2".equals(divisao) & (cargadeincendio <= 1000))
                || ("C-3".equals(divisao))
                || ("D-1".equals(divisao) & (cargadeincendio > 300))
                || ("D-2".equals(divisao) & (cargadeincendio > 300))
                || ("D-3".equals(divisao) & (cargadeincendio > 300))
                || ("D-4".equals(divisao) & (cargadeincendio > 300))
                || ("F-1".equals(divisao) & (cargadeincendio > 300))
                || ("F-5".equals(divisao))
                || ("F-6".equals(divisao))
                || ("F-7".equals(divisao))
                || ("F-9".equals(divisao))
                || ("F-10".equals(divisao))
                || ("F-11".equals(divisao))
                || ("H-4".equals(divisao))
                || ("I-2".equals(divisao) & (cargadeincendio <= 800))
                || ("J-3".equals(divisao) & (cargadeincendio <= 800))) {

            txtSistema.setText("TIPO-3");

        } else if (("C-2".equals(divisao) & (cargadeincendio > 1000))
                || ("I-2".equals(divisao) & (cargadeincendio > 800))
                || ("J-3".equals(divisao) & (cargadeincendio > 800))
                || ("L-1".equals(divisao))
                || ("M-1".equals(divisao))
                || ("M-5".equals(divisao))) {

            txtSistema.setText("TIPO-4");

        } else if (("G-5".equals(divisao))
                || ("I-3".equals(divisao))
                || ("J-4".equals(divisao))
                || ("L-2".equals(divisao))
                || ("L-3".equals(divisao))) {

            txtSistema.setText("TIPO-5");

        } else {
            txtSistema.setText("Indeterminado");
        }

    }

    // comprimento HM
    public void comprimentoHM() {
        if ("50mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveHM = 4.2 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 6.4 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 8.5 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.1 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 3.5 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.4 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.4 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 17.4 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 0.8 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 0.6 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 0.9 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.1 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 1.4 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 1.7 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());

        } else if ("65mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveHM = 5.2 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 8.1 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 10.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.3 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 4.3 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.5 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.4 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 21.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 0.9 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 0.8 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.0 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.3 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 1.7 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 2.0 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());
        } else if ("75mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveHM = 6.3 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 9.7 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 13.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 1.6 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 5.2 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.6 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.5 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 26.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 1.2 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 1.0 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.3 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 1.6 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 2.1 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 2.5 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());
        } else if ("100mm".equals(cbDiametroTubulacao.getSelectedItem())) {

            valvRetLeveHM = 6.4 * Double.parseDouble(spValvRetLeveHM.getValue().toString());
            valvRetPesadaHM = 12.9 * Double.parseDouble(spValvRetPesadaHM.getValue().toString());
            valvAnguloHM = 17.0 * Double.parseDouble(spValvAnguloHM.getValue().toString());
            tePassagemHM = 2.1 * Double.parseDouble(spTePassagemHM.getValue().toString());
            teLateralHM = 6.7 * Double.parseDouble(spTeLateralHM.getValue().toString());
            curva45HM = 0.7 * Double.parseDouble(spCurva45HM.getValue().toString());
            valvGavetaHM = 0.7 * Double.parseDouble(spValvGavetaHM.getValue().toString());
            valvulaGloboHM = 34.0 * Double.parseDouble(spValvGloboHM.getValue().toString());
            cotovelo45HM = 1.5 * Double.parseDouble(spCotovelo45HM.getValue().toString());
            curva9015HM = 1.3 * Double.parseDouble(spCurva9015HM.getValue().toString());
            curva901HM = 1.6 * Double.parseDouble(spCurva901HM.getValue().toString());
            cotovelo90RLHM = 2.1 * Double.parseDouble(spCotovelo90RLHM.getValue().toString());
            cotovelo90RMHM = 2.8 * Double.parseDouble(spCotovelo90RMHM.getValue().toString());
            cotovelo90RCHM = 3.4 * Double.parseDouble(spCotovelo90RCHM.getValue().toString());
        }
        comprimentoTotalHM = valvRetLeveHM + valvRetPesadaHM + valvAnguloHM + tePassagemHM + teLateralHM
                + curva45HM + valvGavetaHM + valvulaGloboHM + cotovelo45HM + curva9015HM
                + curva901HM + cotovelo90RLHM + cotovelo90RMHM + cotovelo90RCHM
                + (comprimentoHM = Double.parseDouble(txtComprimentoHM.getText().replaceAll(",", ".")));
    }

    // método para conferir HM
    public void confHM() {
        desnivelHM = Double.parseDouble(txtDesnivelHM.getText().replaceAll(",", "."));
        vazaoHMM3 = vazaoBomba * (1.7 * (Math.pow(10, -5)));
         esguicho3 = Double.parseDouble(txtEsg3.getText());
        perdaCargaMangueiraHM = (comprimentoMangueiraHM * 10.65 * (Math.pow(vazaoHMM3, 1.85)) * (Math.pow(140, -1.85))
                * (Math.pow(diametroMangueiraHM, -4.87)));
        
        alturaMHM = (comprimentoTotalHM * 10.65 * (Math.pow(vazaoHMM3, 1.85)) * (Math.pow(coeficienteHW, -1.85))
                * (Math.pow(diametroTubulacao, -4.87))) + (perdaCargaMangueiraHM) - (desnivelHM)+ (esguicho3);

        pressaoHM = alturaManometricaBomba - alturaMHM;

        vazaoHM = vazaoBomba;

        velocidadeHM = vazaoHMM3 / (((Math.pow(diametroTubulacao, 2)) * Math.PI) / 4);

        txtVazaoHM.setText(String.valueOf(df.format(vazaoHM)));
        txtPressaoHM.setText(String.valueOf(df.format(pressaoHM)));
        txtVelocidadeHM.setText(String.valueOf(df.format(velocidadeHM)));

    }

    //método para configurar a vazão nos hidrantes H1 e H1'
    public void configurarVazao() {
        String configVazao = txtSistema.getText();
        if (null != configVazao) {
            switch (configVazao) {
                case "TIPO-1":
                    vazaoH = (double) 130;
                    pressaoH = (double) 15;
                    vazaoH1 = (double) 130;
                    pressaoH1 = (double) 15;
                    break;
                case "TIPO-2":
                    vazaoH = (double) 180;
                    pressaoH = (double) 15;
                    vazaoH1 = (double) 180;
                    pressaoH1 = (double) 15;
                    break;
                case "TIPO-3":
                    vazaoH = (double) 230;
                    pressaoH = (double) 15;
                    vazaoH1 = (double) 230;
                    pressaoH1 = (double) 15;
                    break;
                case "TIPO-4":
                    vazaoH = (double) 330;
                    pressaoH = (double) 15;
                    vazaoH1 = (double) 330;
                    pressaoH1 = (double) 15;
                    break;
                case "TIPO-5":
                    vazaoH = (double) 630;
                    pressaoH = (double) 15;
                    vazaoH1 = (double) 630;
                    pressaoH1 = (double) 15;
                    break;
            }
        }
        //txtVazaoH.setText(String.valueOf(vazaoH));
        //txtVazaoH1.setText(String.valueOf(vazaoH1));
        //txtPressaoH.setText(String.valueOf(pressaoH));
        //txtPressaoH1.setText(String.valueOf(pressaoH1));

    }

    // risco de incendio
    public void risco() {
        if (cargadeincendio <= 300) {
            riscoDaOcupacao = "Leve";
        } else if ((cargadeincendio > 300) & (cargadeincendio <= 1200)) {
            riscoDaOcupacao = "Moderado";
        } else if (cargadeincendio > 1200) {
            riscoDaOcupacao = "Alto";
        }

    }

    // método para calcular a RTI
    public void rti() {
        area = Float.parseFloat(txtArea.getText().replaceAll(",", "."));
        sistema = txtSistema.getText();

        // verificaão da RTI
        if (("TIPO-1".equals(sistema) & (area <= 2500))) {
            txtRti.setText("5");
        } else if ("TIPO-2".equals(sistema) & (area <= 2500)) {
            txtRti.setText("8");
        } else if ("TIPO-2".equals(sistema) & (2500 < area) & (area <= 5000)) {
            txtRti.setText("12");
        } else if ("TIPO-2".equals(sistema) & (5000 < area) & (area <= 10000)) {
            txtRti.setText("18");
        } else if ("TIPO-2".equals(sistema) & (10000 < area) & (area <= 20000)) {
            txtRti.setText("25");
        } else if ("TIPO-2".equals(sistema) & (20000 < area) & (area <= 50000)) {
            txtRti.setText("35");
        } else if ("TIPO-2".equals(sistema) & (area > 50000)) {
            txtRti.setText("48");
        } else if ("TIPO-3".equals(sistema) & (area <= 2500)) {
            txtRti.setText("12");
        } else if ("TIPO-3".equals(sistema) & (2500 < area) & (area <= 5000)) {
            txtRti.setText("18");
        } else if ("TIPO-3".equals(sistema) & (5000 < area) & (area <= 10000)) {
            txtRti.setText("25");
        } else if ("TIPO-3".equals(sistema) & (10000 < area) & (area <= 20000)) {
            txtRti.setText("35");
        } else if ("TIPO-3".equals(sistema) & (20000 < area) & (area <= 50000)) {
            txtRti.setText("48");
        } else if ("TIPO-3".equals(sistema) & (area > 50000)) {
            txtRti.setText("70");
        } else if ("TIPO-4".equals(sistema) & (area <= 2500)) {
            txtRti.setText("28");
        } else if ("TIPO-4".equals(sistema) & (2500 < area) & (area <= 5000)) {
            txtRti.setText("32");
        } else if ("TIPO-4".equals(sistema) & (5000 < area) & (area <= 10000)) {
            txtRti.setText("48");
        } else if ("TIPO-4".equals(sistema) & (10000 < area) & (area <= 20000)) {
            txtRti.setText("64");
        } else if ("TIPO-4".equals(sistema) & (20000 < area) & (area <= 50000)) {
            txtRti.setText("96");
        } else if ("TIPO-4".equals(sistema) & (area > 50000)) {
            txtRti.setText("120");
        } else if ("TIPO-5".equals(sistema) & (area <= 2500)) {
            txtRti.setText("32");
        } else if ("TIPO-5".equals(sistema) & (2500 < area) & (area <= 5000)) {
            txtRti.setText("48");
        } else if ("TIPO-5".equals(sistema) & (5000 < area) & (area <= 10000)) {
            txtRti.setText("64");
        } else if ("TIPO-5".equals(sistema) & (10000 < area) & (area <= 20000)) {
            txtRti.setText("96");
        } else if ("TIPO-5".equals(sistema) & (20000 < area) & (area <= 50000)) {
            txtRti.setText("120");
        } else if ("TIPO-5".equals(sistema) & (area > 50000)) {
            txtRti.setText("180");
        }
    }

    // método de pesquisa por tipo de ocupação
    private void pesquisar() {
        String sql = "select * from cargaincendio where descricao like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // lembrar sempre de concatenar o % com a string
            pst.setString(1, txtOcupacao.getText() + "%");
            rs = pst.executeQuery();
            tblCargaIncendio.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método para setar os campos do formulário da tabela
    public void setar_campos() {
        int setar = tblCargaIncendio.getSelectedRow();
        txtOcupa.setText(tblCargaIncendio.getModel().getValueAt(setar, 1).toString());
        txtDivisao.setText(tblCargaIncendio.getModel().getValueAt(setar, 3).toString());
        txtCargaIncendio.setText(tblCargaIncendio.getModel().getValueAt(setar, 4).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtOcupacao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargaIncendio = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtCargaIncendio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSistema = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroHidrantes = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtRti = new javax.swing.JTextField();
        cbMaterialTubulacao = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txtOcupa = new javax.swing.JTextField();
        txtDivisao = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        cbDiametroTubulacao = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        spValvRetLeveH = new javax.swing.JSpinner();
        spValvRetPesadaH = new javax.swing.JSpinner();
        spValvAnguloH = new javax.swing.JSpinner();
        spTePassagemH = new javax.swing.JSpinner();
        spTeLateralH = new javax.swing.JSpinner();
        spCurva45H = new javax.swing.JSpinner();
        spValvGavetaH = new javax.swing.JSpinner();
        spValvGloboH = new javax.swing.JSpinner();
        spCotovelo45H = new javax.swing.JSpinner();
        spCurva9015H = new javax.swing.JSpinner();
        spCurva901H = new javax.swing.JSpinner();
        spCotovelo90RLH = new javax.swing.JSpinner();
        spCotovelo90RMH = new javax.swing.JSpinner();
        spCotovelo90RCH = new javax.swing.JSpinner();
        txtDesnivelH = new javax.swing.JTextField();
        txtVazaoH = new javax.swing.JTextField();
        txtPressaoH = new javax.swing.JTextField();
        txtVelocidadeH = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        txtComprimentoH = new javax.swing.JTextField();
        cbComprimentoMangueiraH = new javax.swing.JComboBox<>();
        cbDiametroMangueiraH = new javax.swing.JComboBox<>();
        jLabel119 = new javax.swing.JLabel();
        txtEsg1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        spValvRetLeveH1 = new javax.swing.JSpinner();
        spValvRetPesadaH1 = new javax.swing.JSpinner();
        spValvAnguloH1 = new javax.swing.JSpinner();
        spTePassagemH1 = new javax.swing.JSpinner();
        spTeLateralH1 = new javax.swing.JSpinner();
        spCurva45H1 = new javax.swing.JSpinner();
        spValvGavetaH1 = new javax.swing.JSpinner();
        spValvGloboH1 = new javax.swing.JSpinner();
        spCotovelo45H1 = new javax.swing.JSpinner();
        spCurva9015H1 = new javax.swing.JSpinner();
        spCurva901H1 = new javax.swing.JSpinner();
        spCotovelo90RLH1 = new javax.swing.JSpinner();
        spCotovelo90RMH1 = new javax.swing.JSpinner();
        spCotovelo90RCH1 = new javax.swing.JSpinner();
        txtDesnivelH1 = new javax.swing.JTextField();
        txtVazaoH1 = new javax.swing.JTextField();
        txtPressaoH1 = new javax.swing.JTextField();
        txtVelocidadeH1 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        txtComprimentoH1 = new javax.swing.JTextField();
        cbComprimentoMangueiraH1 = new javax.swing.JComboBox<>();
        cbDiametroMangueiraH1 = new javax.swing.JComboBox<>();
        jLabel122 = new javax.swing.JLabel();
        txtEsg2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        spValvRetLeveR = new javax.swing.JSpinner();
        spValvRetPesadaR = new javax.swing.JSpinner();
        spValvAnguloR = new javax.swing.JSpinner();
        spTePassagemR = new javax.swing.JSpinner();
        spTeLateralR = new javax.swing.JSpinner();
        spCurva45R = new javax.swing.JSpinner();
        spValvGavetaR = new javax.swing.JSpinner();
        spValvGloboR = new javax.swing.JSpinner();
        spCotovelo45R = new javax.swing.JSpinner();
        spCurva9015R = new javax.swing.JSpinner();
        spCurva901R = new javax.swing.JSpinner();
        spCotovelo90RLR = new javax.swing.JSpinner();
        spCotovelo90RMR = new javax.swing.JSpinner();
        spCotovelo90RCR = new javax.swing.JSpinner();
        txtDesnivelR = new javax.swing.JTextField();
        txtVazaoR = new javax.swing.JTextField();
        txtPressaoR = new javax.swing.JTextField();
        txtVelocidadeR = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        txtComprimentoR = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        spValvRetLeveS = new javax.swing.JSpinner();
        spValvRetPesadaS = new javax.swing.JSpinner();
        spValvAnguloS = new javax.swing.JSpinner();
        spTePassagemS = new javax.swing.JSpinner();
        spTeLateralS = new javax.swing.JSpinner();
        spCurva45S = new javax.swing.JSpinner();
        spValvGavetaS = new javax.swing.JSpinner();
        spValvGloboS = new javax.swing.JSpinner();
        spCotovelo45S = new javax.swing.JSpinner();
        spCurva9015S = new javax.swing.JSpinner();
        spCurva901S = new javax.swing.JSpinner();
        spCotovelo90RLS = new javax.swing.JSpinner();
        spCotovelo90RMS = new javax.swing.JSpinner();
        spCotovelo90RCS = new javax.swing.JSpinner();
        txtDesnivelS = new javax.swing.JTextField();
        txtVazaoS = new javax.swing.JTextField();
        txtPressaoS = new javax.swing.JTextField();
        txtVelocidadeS = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        txtComprimentoS = new javax.swing.JTextField();
        rbtnEntradaNormalS = new javax.swing.JRadioButton();
        rbtnEntradadeBordaS = new javax.swing.JRadioButton();
        rbtnValvPeCrivoS = new javax.swing.JRadioButton();
        cbDiametroS = new javax.swing.JComboBox<>();
        btnVerificarSistema = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        spValvRetLeveHM = new javax.swing.JSpinner();
        spValvRetPesadaHM = new javax.swing.JSpinner();
        spValvAnguloHM = new javax.swing.JSpinner();
        spTePassagemHM = new javax.swing.JSpinner();
        spTeLateralHM = new javax.swing.JSpinner();
        spCurva45HM = new javax.swing.JSpinner();
        spValvGavetaHM = new javax.swing.JSpinner();
        spValvGloboHM = new javax.swing.JSpinner();
        spCotovelo45HM = new javax.swing.JSpinner();
        spCurva9015HM = new javax.swing.JSpinner();
        spCurva901HM = new javax.swing.JSpinner();
        spCotovelo90RLHM = new javax.swing.JSpinner();
        spCotovelo90RMHM = new javax.swing.JSpinner();
        spCotovelo90RCHM = new javax.swing.JSpinner();
        txtDesnivelHM = new javax.swing.JTextField();
        txtVazaoHM = new javax.swing.JTextField();
        txtPressaoHM = new javax.swing.JTextField();
        txtVelocidadeHM = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        txtComprimentoHM = new javax.swing.JTextField();
        cbComprimentoMangueiraHM = new javax.swing.JComboBox<>();
        cbDiametroMangueiraHM = new javax.swing.JComboBox<>();
        txtEsg3 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        btnVerificarHM = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        txtVazaoBomba = new javax.swing.JTextField();
        txtPressaoBomba = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        txtDiferencaPressao = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CÁLCULO DE HIDRANTES");
        setPreferredSize(new java.awt.Dimension(900, 775));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados do Projeto"));
        jPanel1.setMaximumSize(new java.awt.Dimension(889, 32767));
        jPanel1.setPreferredSize(new java.awt.Dimension(880, 153));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel1.setText("Pesquisa");

        txtOcupacao.setBackground(new java.awt.Color(153, 255, 153));
        txtOcupacao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtOcupacao.setToolTipText("Digite a descriçã da edificação ou tipo de material!");
        txtOcupacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOcupacaoKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel2.setText("Área m²");

        txtArea.setBackground(new java.awt.Color(51, 153, 255));
        txtArea.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel3.setText("Altura m");

        txtAltura.setBackground(new java.awt.Color(51, 153, 255));
        txtAltura.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        tblCargaIncendio.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCargaIncendio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCargaIncendioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCargaIncendio);

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel4.setText("Carga de incêndio MJ/M²");

        txtCargaIncendio.setEditable(false);
        txtCargaIncendio.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtCargaIncendio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel5.setText("Sistema");

        txtSistema.setEditable(false);
        txtSistema.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtSistema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel6.setText("Nº Hidrantes");

        txtNumeroHidrantes.setBackground(new java.awt.Color(51, 153, 255));
        txtNumeroHidrantes.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel7.setText("Diâmetro Tubulação");

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel8.setText("RTI m³");

        txtRti.setEditable(false);
        txtRti.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtRti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbMaterialTubulacao.setBackground(new java.awt.Color(51, 153, 255));
        cbMaterialTubulacao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbMaterialTubulacao.setForeground(new java.awt.Color(51, 153, 255));
        cbMaterialTubulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ferro fundido ou dúctil sem revestimento ", "Aço preto (tubo seco)", "Aço preto (tubo molhado)", "Aço galvanizado", "Ferro fundido ou dúctil com revestimento", "Cobre" }));
        cbMaterialTubulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaterialTubulacaoActionPerformed(evt);
            }
        });

        jLabel9.setText("Material Tubulação");

        jLabel115.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel115.setText("Ocupação");

        txtOcupa.setEditable(false);
        txtOcupa.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtOcupa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDivisao.setEditable(false);
        txtDivisao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDivisao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel116.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel116.setText("Divisão");

        cbDiametroTubulacao.setBackground(new java.awt.Color(51, 153, 255));
        cbDiametroTubulacao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbDiametroTubulacao.setForeground(new java.awt.Color(51, 153, 255));
        cbDiametroTubulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "50mm", "65mm", "75mm", "100mm" }));
        cbDiametroTubulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiametroTubulacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel115))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtOcupa, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCargaIncendio, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbDiametroTubulacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRti, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSistema, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(15, 15, 15)
                                .addComponent(txtNumeroHidrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMaterialTubulacao, 0, 1, Short.MAX_VALUE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargaIncendio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumeroHidrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbDiametroTubulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRti, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMaterialTubulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOcupa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(880, 130));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hidrante mais desfavorável até o ponto A"));
        jPanel3.setPreferredSize(new java.awt.Dimension(888, 100));

        jLabel28.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel28.setText("Válv Ret Leve");

        jLabel29.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel29.setText("Válv Ret Pesada");

        jLabel30.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel30.setText("Desnível");

        jLabel31.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel31.setText("Válv Ângulo");

        jLabel32.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel32.setText("Tê passagem");

        jLabel33.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel33.setText("Tê S/ Lateral");

        jLabel34.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel34.setText("Curva 45°");

        jLabel35.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel35.setText("Válv Gaveta");

        jLabel36.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel36.setText("Válv Globo");

        jLabel37.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel37.setText("Cotovelo 45°");

        jLabel38.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel38.setText("Curva 90° R/D 1 ¹/² ''");

        jLabel39.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel39.setText("Curva 90° R/D 1 ''");

        jLabel40.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel40.setText("Cotovelo 90° R L");

        jLabel41.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel41.setText("Cotovelo 90° R M");

        jLabel42.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel42.setText("Cotovelo 90° R C");

        jLabel43.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel43.setText("Vazão l/min");

        jLabel44.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel44.setText("Pressão mca");

        jLabel45.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel45.setText("Velocidade m/s");

        spValvRetLeveH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetLeveH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetLeveH.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvRetPesadaH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetPesadaH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetPesadaH.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvAnguloH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvAnguloH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvAnguloH.setPreferredSize(new java.awt.Dimension(55, 28));

        spTePassagemH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTePassagemH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTePassagemH.setPreferredSize(new java.awt.Dimension(55, 28));

        spTeLateralH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTeLateralH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTeLateralH.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva45H.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva45H.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva45H.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGavetaH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGavetaH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGavetaH.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGloboH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGloboH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGloboH.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo45H.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo45H.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo45H.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva9015H.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva9015H.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva9015H.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva901H.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva901H.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva901H.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo90RLH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RLH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RMH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RMH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RCH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RCH.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo90RCH.setPreferredSize(new java.awt.Dimension(55, 28));

        txtDesnivelH.setBackground(new java.awt.Color(51, 153, 255));
        txtDesnivelH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnivelH.setText("0");

        txtVazaoH.setEditable(false);
        txtVazaoH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoH.setEditable(false);
        txtPressaoH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVelocidadeH.setEditable(false);
        txtVelocidadeH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVelocidadeH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel88.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel88.setText("Diâmetro mm");

        jLabel89.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel89.setText("Comprimento m");

        jLabel90.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel90.setText("Mangueira:");

        jLabel110.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel110.setText("Comprimento da Tubulação m");

        txtComprimentoH.setBackground(new java.awt.Color(51, 153, 255));
        txtComprimentoH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtComprimentoH.setText("0");

        cbComprimentoMangueiraH.setBackground(new java.awt.Color(51, 153, 255));
        cbComprimentoMangueiraH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbComprimentoMangueiraH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "15m", "30m", "60m" }));
        cbComprimentoMangueiraH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComprimentoMangueiraHActionPerformed(evt);
            }
        });

        cbDiametroMangueiraH.setBackground(new java.awt.Color(51, 153, 255));
        cbDiametroMangueiraH.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbDiametroMangueiraH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "25mm", "40mm", "65mm" }));
        cbDiametroMangueiraH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiametroMangueiraHActionPerformed(evt);
            }
        });

        jLabel119.setText("Esguicho (mca)");

        txtEsg1.setBackground(new java.awt.Color(51, 102, 255));
        txtEsg1.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPressaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVazaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVelocidadeH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvRetLeveH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvRetPesadaH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnivelH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvAnguloH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTePassagemH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTeLateralH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCurva45H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGavetaH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGloboH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDiametroMangueiraH, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel89)
                        .addGap(5, 5, 5)
                        .addComponent(cbComprimentoMangueiraH, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComprimentoH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo45H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva9015H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva901H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo90RMH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RLH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RCH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtEsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetLeveH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvAnguloH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva45H, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo45H, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RLH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetPesadaH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTePassagemH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGavetaH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva9015H, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnivelH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTeLateralH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGloboH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva901H, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbDiametroMangueiraH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbComprimentoMangueiraH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtComprimentoH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel119)
                                .addComponent(txtEsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(1, 1, 1))))
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("H1", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Segundo hidrante mais desfavorável até o ponto A"));
        jPanel5.setPreferredSize(new java.awt.Dimension(888, 100));

        jLabel64.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel64.setText("Válv Ret Leve");

        jLabel65.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel65.setText("Válv Ret Pesada");

        jLabel66.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel66.setText("Desnível");

        jLabel67.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel67.setText("Válv Ângulo");

        jLabel68.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel68.setText("Tê passagem");

        jLabel69.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel69.setText("Tê S/ Lateral");

        jLabel70.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel70.setText("Curva 45°");

        jLabel71.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel71.setText("Válv Gaveta");

        jLabel72.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel72.setText("Válv Globo");

        jLabel73.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel73.setText("Cotovelo 45°");

        jLabel74.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel74.setText("Curva 90° R/D 1 ¹/² ''");

        jLabel75.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel75.setText("Curva 90° R/D 1 ''");

        jLabel76.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel76.setText("Cotovelo 90° R L");

        jLabel77.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel77.setText("Cotovelo 90° R M");

        jLabel78.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel78.setText("Cotovelo 90° R C");

        jLabel79.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel79.setText("Vazão l/min");

        jLabel80.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel80.setText("Pressão mca");

        jLabel81.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel81.setText("Velocidade m/s");

        spValvRetLeveH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetLeveH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetLeveH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvRetPesadaH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetPesadaH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetPesadaH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvAnguloH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvAnguloH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvAnguloH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spTePassagemH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTePassagemH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTePassagemH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spTeLateralH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTeLateralH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTeLateralH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva45H1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva45H1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva45H1.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGavetaH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGavetaH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGavetaH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGloboH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGloboH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGloboH1.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo45H1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo45H1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo45H1.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva9015H1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva9015H1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva9015H1.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva901H1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva901H1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva901H1.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo90RLH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RLH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RMH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RMH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RCH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RCH1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo90RCH1.setPreferredSize(new java.awt.Dimension(55, 28));

        txtDesnivelH1.setBackground(new java.awt.Color(51, 153, 255));
        txtDesnivelH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnivelH1.setText("0");

        txtVazaoH1.setEditable(false);
        txtVazaoH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoH1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoH1.setEditable(false);
        txtPressaoH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoH1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVelocidadeH1.setEditable(false);
        txtVelocidadeH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVelocidadeH1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel83.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel83.setText("Diâmetro mm");

        jLabel84.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel84.setText("Comprimento m");

        jLabel82.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel82.setText("Mangueira:");

        jLabel111.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel111.setText("Comprimento da Tubulação m");

        txtComprimentoH1.setBackground(new java.awt.Color(51, 153, 255));
        txtComprimentoH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtComprimentoH1.setText("0");

        cbComprimentoMangueiraH1.setBackground(new java.awt.Color(51, 153, 255));
        cbComprimentoMangueiraH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbComprimentoMangueiraH1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "15m", "30m", "60m" }));
        cbComprimentoMangueiraH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComprimentoMangueiraH1ActionPerformed(evt);
            }
        });

        cbDiametroMangueiraH1.setBackground(new java.awt.Color(51, 153, 255));
        cbDiametroMangueiraH1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbDiametroMangueiraH1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "25mm", "40mm", "65mm" }));
        cbDiametroMangueiraH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiametroMangueiraH1ActionPerformed(evt);
            }
        });

        jLabel122.setText("Esguicho (mca)");

        txtEsg2.setBackground(new java.awt.Color(51, 102, 255));
        txtEsg2.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtVazaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPressaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVelocidadeH1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel64)
                                .addComponent(jLabel65)
                                .addComponent(jLabel66))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(spValvRetLeveH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spValvRetPesadaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDesnivelH1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel67)
                                .addComponent(jLabel68)
                                .addComponent(jLabel69)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel84)
                            .addGap(4, 4, 4)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDiametroMangueiraH1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvAnguloH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTePassagemH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTeLateralH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCurva45H1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGavetaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGloboH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo45H1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva9015H1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva901H1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbComprimentoMangueiraH1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel111)
                        .addGap(18, 18, 18)
                        .addComponent(txtComprimentoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel122)))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtEsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spCotovelo90RLH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCH1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetLeveH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvAnguloH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva45H1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo45H1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RLH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetPesadaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTePassagemH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGavetaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva9015H1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnivelH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTeLateralH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGloboH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva901H1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDiametroMangueiraH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbComprimentoMangueiraH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComprimentoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122)
                    .addComponent(txtEsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("H2", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ponto A até a Bomba"));
        jPanel4.setPreferredSize(new java.awt.Dimension(880, 100));

        jLabel46.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel46.setText("Válv Ret Leve");

        jLabel47.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel47.setText("Válv Ret Pesada");

        jLabel48.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel48.setText("Desnível");

        jLabel49.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel49.setText("Válv Ângulo");

        jLabel50.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel50.setText("Tê passagem");

        jLabel51.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Tê S/ Lateral");

        jLabel52.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel52.setText("Curva 45°");

        jLabel53.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel53.setText("Válv Gaveta");

        jLabel54.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel54.setText("Válv Globo");

        jLabel55.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel55.setText("Cotovelo 45°");

        jLabel56.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel56.setText("Curva 90° R/D 1 ¹/² ''");

        jLabel57.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel57.setText("Curva 90° R/D 1 ''");

        jLabel58.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel58.setText("Cotovelo 90° R L");

        jLabel59.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel59.setText("Cotovelo 90° R M");

        jLabel60.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel60.setText("Cotovelo 90° R C");

        jLabel61.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel61.setText("Vazão l/min");

        jLabel62.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel62.setText("Pressão mca");

        jLabel63.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel63.setText("Velocidade m/s");

        spValvRetLeveR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetLeveR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetLeveR.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvRetPesadaR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetPesadaR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetPesadaR.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvAnguloR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvAnguloR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvAnguloR.setPreferredSize(new java.awt.Dimension(55, 28));

        spTePassagemR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTePassagemR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTePassagemR.setPreferredSize(new java.awt.Dimension(55, 28));

        spTeLateralR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTeLateralR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTeLateralR.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva45R.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva45R.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva45R.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGavetaR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGavetaR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGavetaR.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGloboR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGloboR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGloboR.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo45R.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo45R.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo45R.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva9015R.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva9015R.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva9015R.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva901R.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva901R.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva901R.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo90RLR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RLR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RMR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RMR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RCR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RCR.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo90RCR.setPreferredSize(new java.awt.Dimension(55, 28));

        txtDesnivelR.setBackground(new java.awt.Color(51, 153, 255));
        txtDesnivelR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnivelR.setText("0");

        txtVazaoR.setEditable(false);
        txtVazaoR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoR.setEditable(false);
        txtPressaoR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVelocidadeR.setEditable(false);
        txtVelocidadeR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVelocidadeR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel113.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel113.setText("Comprimento da Tubulação m");

        txtComprimentoR.setBackground(new java.awt.Color(51, 153, 255));
        txtComprimentoR.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtComprimentoR.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(jLabel62)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVazaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPressaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVelocidadeR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvRetLeveR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvRetPesadaR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnivelR, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvAnguloR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTePassagemR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTeLateralR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53)
                            .addComponent(jLabel54))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCurva45R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGavetaR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGloboR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo45R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva9015R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva901R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo90RLR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RMR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RCR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComprimentoR, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetLeveR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvAnguloR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva45R, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo45R, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RLR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetPesadaR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTePassagemR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGavetaR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva9015R, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnivelR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTeLateralR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGloboR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva901R, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComprimentoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Recalque", jPanel4);

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bomba até o reservatório"));
        jPanel6.setPreferredSize(new java.awt.Dimension(888, 100));

        jLabel91.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel91.setText("Válv Ret Leve");

        jLabel92.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel92.setText("Válv Ret Pesada");

        jLabel93.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel93.setText("Desnível");

        jLabel94.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel94.setText("Válv Ângulo");

        jLabel95.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel95.setText("Tê passagem");

        jLabel96.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel96.setText("Tê S/ Lateral");

        jLabel97.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel97.setText("Curva 45°");

        jLabel98.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel98.setText("Válv Gaveta");

        jLabel99.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel99.setText("Válv Globo");

        jLabel100.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel100.setText("Cotovelo 45°");

        jLabel101.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel101.setText("Curva 90° R/D 1 ¹/² ''");

        jLabel102.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel102.setText("Curva 90° R/D 1 ''");

        jLabel103.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel103.setText("Cotovelo 90° R L");

        jLabel104.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel104.setText("Cotovelo 90° R M");

        jLabel105.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel105.setText("Cotovelo 90° R C");

        jLabel106.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel106.setText("Vazão l/min");

        jLabel107.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel107.setText("Pressão mca");

        jLabel108.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel108.setText("Velocidade m/s");

        spValvRetLeveS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetLeveS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetLeveS.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvRetPesadaS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetPesadaS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetPesadaS.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvAnguloS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvAnguloS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvAnguloS.setPreferredSize(new java.awt.Dimension(55, 28));

        spTePassagemS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTePassagemS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTePassagemS.setPreferredSize(new java.awt.Dimension(55, 28));

        spTeLateralS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTeLateralS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTeLateralS.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva45S.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva45S.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva45S.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGavetaS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGavetaS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGavetaS.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGloboS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGloboS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGloboS.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo45S.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo45S.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo45S.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva9015S.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva9015S.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva9015S.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva901S.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva901S.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva901S.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo90RLS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RLS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RMS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RMS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RCS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RCS.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo90RCS.setPreferredSize(new java.awt.Dimension(55, 28));

        txtDesnivelS.setBackground(new java.awt.Color(51, 153, 255));
        txtDesnivelS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnivelS.setText("0");

        txtVazaoS.setEditable(false);
        txtVazaoS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoS.setEditable(false);
        txtPressaoS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVelocidadeS.setEditable(false);
        txtVelocidadeS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVelocidadeS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel109.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel109.setText("Diâmetro");

        jLabel114.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel114.setText("Comprimento da Tubulação m");

        txtComprimentoS.setBackground(new java.awt.Color(51, 153, 255));
        txtComprimentoS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtComprimentoS.setText("0");

        rbtnEntradaNormalS.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup2.add(rbtnEntradaNormalS);
        rbtnEntradaNormalS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtnEntradaNormalS.setText("Entrada normal");

        rbtnEntradadeBordaS.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup2.add(rbtnEntradadeBordaS);
        rbtnEntradadeBordaS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtnEntradadeBordaS.setText("Entrada de borda");

        rbtnValvPeCrivoS.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup2.add(rbtnValvPeCrivoS);
        rbtnValvPeCrivoS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        rbtnValvPeCrivoS.setText("Válv pé com crivo");

        cbDiametroS.setBackground(new java.awt.Color(51, 153, 255));
        cbDiametroS.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbDiametroS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "65mm", "75mm", "100mm", "125mm" }));
        cbDiametroS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiametroSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106)
                    .addComponent(jLabel107)
                    .addComponent(jLabel108))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVazaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeS, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jLabel92)
                            .addComponent(jLabel93))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvRetLeveS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvRetPesadaS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnivelS, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvAnguloS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTePassagemS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTeLateralS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addComponent(jLabel98)
                            .addComponent(jLabel99)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtComprimentoS, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnEntradaNormalS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCurva45S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGavetaS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGloboS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101)
                            .addComponent(jLabel102))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo45S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva9015S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva901S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103)
                            .addComponent(jLabel104)
                            .addComponent(jLabel105))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo90RLS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RMS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RCS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(rbtnEntradadeBordaS)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnValvPeCrivoS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel109)
                .addGap(15, 15, 15)
                .addComponent(cbDiametroS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetLeveS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvAnguloS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva45S, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo45S, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RLS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetPesadaS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTePassagemS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGavetaS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva9015S, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesnivelS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96)
                    .addComponent(spTeLateralS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGloboS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102)
                    .addComponent(spCurva901S, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDiametroS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComprimentoS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnEntradaNormalS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnEntradadeBordaS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnValvPeCrivoS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sucção", jPanel6);

        btnVerificarSistema.setBackground(new java.awt.Color(255, 153, 153));
        btnVerificarSistema.setText("Verificar tipo de sistema");
        btnVerificarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarSistemaActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setText("Cálcular");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hidrante mais favorável até a bomba"));
        jPanel2.setPreferredSize(new java.awt.Dimension(888, 100));

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel10.setText("Válv Ret Leve");

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel11.setText("Válv Ret Pesada");

        jLabel12.setText("Desnível");

        jLabel13.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel13.setText("Válv Ângulo");

        jLabel14.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel14.setText("Tê passagem");

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel15.setText("Tê S/ Lateral");

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel16.setText("Curva 45°");

        jLabel17.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel17.setText("Válv Gaveta");

        jLabel18.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel18.setText("Válv Globo");

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel19.setText("Cotovelo 45°");

        jLabel20.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel20.setText("Curva 90° R/D 1 ¹/² ''");

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel21.setText("Curva 90° R/D 1 ''");

        jLabel22.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel22.setText("Cotovelo 90° R L");

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel23.setText("Cotovelo 90° R M");

        jLabel24.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel24.setText("Cotovelo 90° R C");

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel25.setText("Vazão l/min");

        jLabel26.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel26.setText("Pressão mca");

        jLabel27.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel27.setText("Velocidade m/s");

        spValvRetLeveHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetLeveHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetLeveHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvRetPesadaHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvRetPesadaHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvRetPesadaHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvAnguloHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvAnguloHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvAnguloHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spTePassagemHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTePassagemHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTePassagemHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spTeLateralHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spTeLateralHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spTeLateralHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva45HM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva45HM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva45HM.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGavetaHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGavetaHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGavetaHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spValvGloboHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spValvGloboHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spValvGloboHM.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo45HM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo45HM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo45HM.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva9015HM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva9015HM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva9015HM.setPreferredSize(new java.awt.Dimension(55, 28));

        spCurva901HM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCurva901HM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCurva901HM.setPreferredSize(new java.awt.Dimension(55, 28));

        spCotovelo90RLHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RLHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RMHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RMHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spCotovelo90RCHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        spCotovelo90RCHM.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spCotovelo90RCHM.setPreferredSize(new java.awt.Dimension(55, 28));

        txtDesnivelHM.setBackground(new java.awt.Color(51, 153, 255));
        txtDesnivelHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDesnivelHM.setText("0");

        txtVazaoHM.setEditable(false);
        txtVazaoHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoHM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoHM.setEditable(false);
        txtPressaoHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoHM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVelocidadeHM.setEditable(false);
        txtVelocidadeHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVelocidadeHM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel85.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel85.setText("Diâmetro mm");

        jLabel86.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel86.setText("Comprimento m");

        jLabel87.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel87.setText("Mangueira:");

        jLabel112.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel112.setText("Comrpimento da Tubulação m");

        txtComprimentoHM.setBackground(new java.awt.Color(51, 153, 255));
        txtComprimentoHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtComprimentoHM.setText("0");

        cbComprimentoMangueiraHM.setBackground(new java.awt.Color(51, 153, 255));
        cbComprimentoMangueiraHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbComprimentoMangueiraHM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "15m", "30m", "60m" }));
        cbComprimentoMangueiraHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbComprimentoMangueiraHMActionPerformed(evt);
            }
        });

        cbDiametroMangueiraHM.setBackground(new java.awt.Color(51, 153, 255));
        cbDiametroMangueiraHM.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cbDiametroMangueiraHM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "25mm", "40mm", "65mm" }));
        cbDiametroMangueiraHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiametroMangueiraHMActionPerformed(evt);
            }
        });

        txtEsg3.setBackground(new java.awt.Color(51, 102, 255));
        txtEsg3.setText("0");

        jLabel120.setText("Esguicho (mca)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVazaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPressaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVelocidadeHM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvRetLeveHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvRetPesadaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesnivelHM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spValvAnguloHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTePassagemHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTeLateralHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCurva45HM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGavetaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spValvGloboHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo45HM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva9015HM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCurva901HM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCotovelo90RMHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RLHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spCotovelo90RCHM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDiametroMangueiraHM, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel86)
                        .addGap(18, 18, 18)
                        .addComponent(cbComprimentoMangueiraHM, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel112)
                        .addGap(18, 18, 18)
                        .addComponent(txtComprimentoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel120)
                        .addGap(40, 40, 40)
                        .addComponent(txtEsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetLeveHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvAnguloHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva45HM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo45HM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RLHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvRetPesadaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTePassagemHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGavetaHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva9015HM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RMHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVelocidadeHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtDesnivelHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spTeLateralHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spValvGloboHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCurva901HM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spCotovelo90RCHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtComprimentoHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbComprimentoMangueiraHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbDiametroMangueiraHM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel120)))))
        );

        btnVerificarHM.setBackground(new java.awt.Color(255, 153, 255));
        btnVerificarHM.setText("Verificar HM");
        btnVerificarHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarHMActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setText("Importar de Depósitos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setText("Iportar de Especiais");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 204, 102));
        jButton4.setText("Exportar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel117.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel117.setText("Vazão da Bomba l/min:");

        jLabel118.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel118.setText("Pressão da Bomba mca:");

        txtVazaoBomba.setEditable(false);
        txtVazaoBomba.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtVazaoBomba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPressaoBomba.setEditable(false);
        txtPressaoBomba.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtPressaoBomba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel121.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jLabel121.setText("Diferença de pressão no ponto A mca");

        txtDiferencaPressao.setEditable(false);
        txtDiferencaPressao.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        txtDiferencaPressao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtVazaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel118)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPressaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel121)
                .addGap(33, 33, 33)
                .addComponent(txtDiferencaPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVazaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiferencaPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPressaoBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnVerificarSistema)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnVerificarHM, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerificarSistema)
                    .addComponent(jButton2)
                    .addComponent(btnVerificarHM)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setBounds(0, 0, 900, 629);
    }// </editor-fold>//GEN-END:initComponents
    // chama o método para pesquisar pelo nome
    private void txtOcupacaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacaoKeyReleased
        // Enquanto digita chama o resultado
        pesquisar();
    }//GEN-LAST:event_txtOcupacaoKeyReleased
    // chama o método para setar os campos de texto com as informações
    private void tblCargaIncendioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCargaIncendioMouseClicked
        // Evento para setar os campos com o clique
        setar_campos();
    }//GEN-LAST:event_tblCargaIncendioMouseClicked
    // chama os métodos para verificar o tipo de sistema
    private void btnVerificarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarSistemaActionPerformed
        // TODO add your handling code here:
        System.out.println(perdaCargaMangueiraH);
        System.out.println(perdaCargaMangueiraH1);
        System.out.println(perdaCargaMangueiraHM);
        try {
            tipoSistema();
            rti();
            risco();
            tp.reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!\nCaso tenha preenchido verifique os dados digitados!");
        }

    }//GEN-LAST:event_btnVerificarSistemaActionPerformed

    // métodos para efetuar o cálculo
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            configurarVazao();
            desnivel();
            comprimentoH();
            comprimentoS();
           // perdaCargaMangueiraH();
            perdadeCarga();
            velocidade();
            if ("�".equals(txtPressaoBomba.getText())) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos obrigatórios foram preenchidos!");
            }
            if ((Math.abs(Double.parseDouble(txtVazaoH.getText().replaceAll(",", ".")) - (Double.parseDouble(txtVazaoH1.getText().replaceAll(",", "."))))) > 60) {
                JOptionPane.showMessageDialog(null, "A diferença de vazão entre os hidrantes H1 e H2 está muito alta\n"
                        + "verifique se todos os dados estão corretos, caso estejam\n"
                        + "verifique a possibilidade de inserir um acessório em H2 \n"
                        + "para aumetar a perda de carga e diminuir a diferença de vazão. ");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    // metodo para setar o diâmetro da tubulação
    private void cbDiametroTubulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiametroTubulacaoActionPerformed
        String tamanhoDiametro = (String) cbDiametroTubulacao.getSelectedItem();
        if (null != tamanhoDiametro) {
            switch (tamanhoDiametro) {
                case "50mm":
                    diametroTubulacao = (double) 0.05;
                    break;
                case "65mm":
                    diametroTubulacao = (double) 0.065;
                    break;
                case "75mm":
                    diametroTubulacao = (double) 0.075;
                    break;
                case "100mm":
                    diametroTubulacao = (double) 0.1;
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_cbDiametroTubulacaoActionPerformed
    //método para setar o diamento da sucção
    private void cbDiametroSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiametroSActionPerformed
        String tamanhoDiametroS = (String) cbDiametroS.getSelectedItem();
        if (null != tamanhoDiametroS) {
            switch (tamanhoDiametroS) {
                case "65mm":
                    diametroS = (double) 0.065;
                    break;
                case "75mm":
                    diametroS = (double) 0.075;
                    break;
                case "100mm":
                    diametroS = (double) 0.1;
                    break;
                case "125mm":
                    diametroS = (double) 0.125;
                    break;
                default:
                    break;

            }
        }
    }//GEN-LAST:event_cbDiametroSActionPerformed
    //método para setar o coeficiente de hazen Willians
    private void cbMaterialTubulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaterialTubulacaoActionPerformed
        String coeficienteHazen = (String) cbMaterialTubulacao.getSelectedItem();
        if (null != coeficienteHazen) {
            switch (coeficienteHazen) {
                case "Ferro fundio ou dúctil sem revestimento":
                    coeficienteHW = (double) 100;
                    break;
                case "Aço preto (tubo seco)":
                    coeficienteHW = (double) 100;
                    break;
                case "Aço preto (tubo molhado)":
                    coeficienteHW = (double) 120;
                    break;
                case "Aço galvanizado":
                    coeficienteHW = (double) 120;
                    break;
                case "Ferro fundido ou dúctil com revestimento":
                    coeficienteHW = (double) 140;
                    break;
                case "Cobre":
                    coeficienteHW = (double) 150;
                    break;
                default:
                    coeficienteHW = (double) 100;
                    break;

            }
        }
    }//GEN-LAST:event_cbMaterialTubulacaoActionPerformed
    // comprimento da mangueira de H1
    private void cbComprimentoMangueiraHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbComprimentoMangueiraHActionPerformed
        String comprimentoMangH = (String) cbComprimentoMangueiraH.getSelectedItem();
        if (null != comprimentoMangH) {
            switch (comprimentoMangH) {
                case "15m":
                    comprimentoMangueiraH = (double) 15;
                    break;
                case "30m":
                    comprimentoMangueiraH = (double) 30;
                    break;
                case "60m":
                    comprimentoMangueiraH = (double) 60;
                    break;
                default:
                    comprimentoMangueiraH = (double) 30;
                    break;
            }
        }
    }//GEN-LAST:event_cbComprimentoMangueiraHActionPerformed
    // comprimento mangueira H1'
    private void cbComprimentoMangueiraH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbComprimentoMangueiraH1ActionPerformed
        String comprimentoMangH1 = (String) cbComprimentoMangueiraH1.getSelectedItem();
        if (null != comprimentoMangH1) {
            switch (comprimentoMangH1) {
                case "15m":
                    comprimentoMangueiraH1 = (double) 15;
                    break;
                case "30m":
                    comprimentoMangueiraH1 = (double) 30;
                    break;
                case "60m":
                    comprimentoMangueiraH1 = (double) 60;
                    break;
                default:
                    comprimentoMangueiraH1 = (double) 30;
                    break;
            }
        }
    }//GEN-LAST:event_cbComprimentoMangueiraH1ActionPerformed
    // Comprimento mangueira HM
    private void cbComprimentoMangueiraHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbComprimentoMangueiraHMActionPerformed
        String comprimentoMangHM = (String) cbComprimentoMangueiraHM.getSelectedItem();
        if (null != comprimentoMangHM) {
            switch (comprimentoMangHM) {
                case "15m":
                    comprimentoMangueiraHM = (double) 15;
                    break;
                case "30m":
                    comprimentoMangueiraHM = (double) 30;
                    break;
                case "60m":
                    comprimentoMangueiraHM = (double) 60;
                    break;
                default:
                    comprimentoMangueiraHM = (double) 30;
                    break;
            }
        }
    }//GEN-LAST:event_cbComprimentoMangueiraHMActionPerformed
    // diametro mangueira H1
    private void cbDiametroMangueiraHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiametroMangueiraHActionPerformed
        String diametroMangH = (String) cbDiametroMangueiraH.getSelectedItem();
        if (null != diametroMangH) {
            switch (diametroMangH) {
                case "25mm":
                    diametroMangueiraH = (double) 0.025;
                    break;
                case "40mm":
                    diametroMangueiraH = (double) 0.040;
                    break;
                case "65mm":
                    diametroMangueiraH = (double) 0.065;
                    break;
                default:
                    diametroMangueiraH = (double) 0.040;
                    break;
            }
        }
    }//GEN-LAST:event_cbDiametroMangueiraHActionPerformed
    // diametro mangueira H1'
    private void cbDiametroMangueiraH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiametroMangueiraH1ActionPerformed
        String diametroMangH1 = (String) cbDiametroMangueiraH1.getSelectedItem();
        if (null != diametroMangH1) {
            switch (diametroMangH1) {
                case "25mm":
                    diametroMangueiraH1 = (double) 0.025;
                    break;
                case "40mm":
                    diametroMangueiraH1 = (double) 0.040;
                    break;
                case "65mm":
                    diametroMangueiraH1 = (double) 0.065;
                    break;
                default:
                    diametroMangueiraH1 = (double) 0.040;
                    break;
            }
        }
    }//GEN-LAST:event_cbDiametroMangueiraH1ActionPerformed
    // diâmetro mangueira HM
    private void cbDiametroMangueiraHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiametroMangueiraHMActionPerformed
        String diametroMangHM = (String) cbDiametroMangueiraHM.getSelectedItem();
        if (null != diametroMangHM) {
            switch (diametroMangHM) {
                case "25mm":
                    diametroMangueiraHM = (double) 0.025;
                    break;
                case "40mm":
                    diametroMangueiraHM = (double) 0.040;
                    break;
                case "65mm":
                    diametroMangueiraHM = (double) 0.065;
                    break;
                default:
                    diametroMangueiraHM = (double) 0.040;
                    break;
            }
        }
    }//GEN-LAST:event_cbDiametroMangueiraHMActionPerformed

    // confhm
    private void btnVerificarHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarHMActionPerformed
        try {
           // perdaCargaMangueiraH();
            comprimentoHM();
            confHM();
            //System.out.println(comprimentoTotalHM);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Faltando Informações");
        }
    }//GEN-LAST:event_btnVerificarHMActionPerformed
    // envento para receber os dados da tela depositos
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        recebe();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        recebeEsp();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (txtVazaoHM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Prencha todos os dados e realize todos os cálculos!");
        } else {
            try {
                int confirma = JOptionPane.showConfirmDialog(null, "Deseja salvar e exportar para o projeto?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    exportarProj();
                    JOptionPane.showMessageDialog(null, "Dados exportados com sucesso! Na tela de projetos clique em (Importar)");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os dados estão preenchidos\n"
                        + "Log da excessão:" + e);
            }
        }
        System.out.println(pcH1 );
        System.out.println(alturaMH1);
        System.out.println(pressaoH1);
        System.out.println(desnivelH1);
        System.out.println("---------------"); 
        System.out.println(pcH );
        System.out.println(alturaMH);
        System.out.println(pressaoH);
        System.out.println(desnivelH);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerificarHM;
    private javax.swing.JButton btnVerificarSistema;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbComprimentoMangueiraH;
    private javax.swing.JComboBox<String> cbComprimentoMangueiraH1;
    private javax.swing.JComboBox<String> cbComprimentoMangueiraHM;
    private javax.swing.JComboBox<String> cbDiametroMangueiraH;
    private javax.swing.JComboBox<String> cbDiametroMangueiraH1;
    private javax.swing.JComboBox<String> cbDiametroMangueiraHM;
    private javax.swing.JComboBox<String> cbDiametroS;
    private javax.swing.JComboBox<String> cbDiametroTubulacao;
    private javax.swing.JComboBox<String> cbMaterialTubulacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbtnEntradaNormalS;
    private javax.swing.JRadioButton rbtnEntradadeBordaS;
    private javax.swing.JRadioButton rbtnValvPeCrivoS;
    private javax.swing.JSpinner spCotovelo45H;
    private javax.swing.JSpinner spCotovelo45H1;
    private javax.swing.JSpinner spCotovelo45HM;
    private javax.swing.JSpinner spCotovelo45R;
    private javax.swing.JSpinner spCotovelo45S;
    private javax.swing.JSpinner spCotovelo90RCH;
    private javax.swing.JSpinner spCotovelo90RCH1;
    private javax.swing.JSpinner spCotovelo90RCHM;
    private javax.swing.JSpinner spCotovelo90RCR;
    private javax.swing.JSpinner spCotovelo90RCS;
    private javax.swing.JSpinner spCotovelo90RLH;
    private javax.swing.JSpinner spCotovelo90RLH1;
    private javax.swing.JSpinner spCotovelo90RLHM;
    private javax.swing.JSpinner spCotovelo90RLR;
    private javax.swing.JSpinner spCotovelo90RLS;
    private javax.swing.JSpinner spCotovelo90RMH;
    private javax.swing.JSpinner spCotovelo90RMH1;
    private javax.swing.JSpinner spCotovelo90RMHM;
    private javax.swing.JSpinner spCotovelo90RMR;
    private javax.swing.JSpinner spCotovelo90RMS;
    private javax.swing.JSpinner spCurva45H;
    private javax.swing.JSpinner spCurva45H1;
    private javax.swing.JSpinner spCurva45HM;
    private javax.swing.JSpinner spCurva45R;
    private javax.swing.JSpinner spCurva45S;
    private javax.swing.JSpinner spCurva9015H;
    private javax.swing.JSpinner spCurva9015H1;
    private javax.swing.JSpinner spCurva9015HM;
    private javax.swing.JSpinner spCurva9015R;
    private javax.swing.JSpinner spCurva9015S;
    private javax.swing.JSpinner spCurva901H;
    private javax.swing.JSpinner spCurva901H1;
    private javax.swing.JSpinner spCurva901HM;
    private javax.swing.JSpinner spCurva901R;
    private javax.swing.JSpinner spCurva901S;
    private javax.swing.JSpinner spTeLateralH;
    private javax.swing.JSpinner spTeLateralH1;
    private javax.swing.JSpinner spTeLateralHM;
    private javax.swing.JSpinner spTeLateralR;
    private javax.swing.JSpinner spTeLateralS;
    private javax.swing.JSpinner spTePassagemH;
    private javax.swing.JSpinner spTePassagemH1;
    private javax.swing.JSpinner spTePassagemHM;
    private javax.swing.JSpinner spTePassagemR;
    private javax.swing.JSpinner spTePassagemS;
    private javax.swing.JSpinner spValvAnguloH;
    private javax.swing.JSpinner spValvAnguloH1;
    private javax.swing.JSpinner spValvAnguloHM;
    private javax.swing.JSpinner spValvAnguloR;
    private javax.swing.JSpinner spValvAnguloS;
    private javax.swing.JSpinner spValvGavetaH;
    private javax.swing.JSpinner spValvGavetaH1;
    private javax.swing.JSpinner spValvGavetaHM;
    private javax.swing.JSpinner spValvGavetaR;
    private javax.swing.JSpinner spValvGavetaS;
    private javax.swing.JSpinner spValvGloboH;
    private javax.swing.JSpinner spValvGloboH1;
    private javax.swing.JSpinner spValvGloboHM;
    private javax.swing.JSpinner spValvGloboR;
    private javax.swing.JSpinner spValvGloboS;
    private javax.swing.JSpinner spValvRetLeveH;
    private javax.swing.JSpinner spValvRetLeveH1;
    private javax.swing.JSpinner spValvRetLeveHM;
    private javax.swing.JSpinner spValvRetLeveR;
    private javax.swing.JSpinner spValvRetLeveS;
    private javax.swing.JSpinner spValvRetPesadaH;
    private javax.swing.JSpinner spValvRetPesadaH1;
    private javax.swing.JSpinner spValvRetPesadaHM;
    private javax.swing.JSpinner spValvRetPesadaR;
    private javax.swing.JSpinner spValvRetPesadaS;
    private javax.swing.JTable tblCargaIncendio;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCargaIncendio;
    private javax.swing.JTextField txtComprimentoH;
    private javax.swing.JTextField txtComprimentoH1;
    private javax.swing.JTextField txtComprimentoHM;
    private javax.swing.JTextField txtComprimentoR;
    private javax.swing.JTextField txtComprimentoS;
    private javax.swing.JTextField txtDesnivelH;
    private javax.swing.JTextField txtDesnivelH1;
    private javax.swing.JTextField txtDesnivelHM;
    private javax.swing.JTextField txtDesnivelR;
    private javax.swing.JTextField txtDesnivelS;
    private javax.swing.JTextField txtDiferencaPressao;
    private javax.swing.JTextField txtDivisao;
    private javax.swing.JTextField txtEsg1;
    private javax.swing.JTextField txtEsg2;
    private javax.swing.JTextField txtEsg3;
    private javax.swing.JTextField txtNumeroHidrantes;
    private javax.swing.JTextField txtOcupa;
    private javax.swing.JTextField txtOcupacao;
    private javax.swing.JTextField txtPressaoBomba;
    private javax.swing.JTextField txtPressaoH;
    private javax.swing.JTextField txtPressaoH1;
    private javax.swing.JTextField txtPressaoHM;
    private javax.swing.JTextField txtPressaoR;
    private javax.swing.JTextField txtPressaoS;
    private javax.swing.JTextField txtRti;
    private javax.swing.JTextField txtSistema;
    private javax.swing.JTextField txtVazaoBomba;
    private javax.swing.JTextField txtVazaoH;
    private javax.swing.JTextField txtVazaoH1;
    private javax.swing.JTextField txtVazaoHM;
    private javax.swing.JTextField txtVazaoR;
    private javax.swing.JTextField txtVazaoS;
    private javax.swing.JTextField txtVelocidadeH;
    private javax.swing.JTextField txtVelocidadeH1;
    private javax.swing.JTextField txtVelocidadeHM;
    private javax.swing.JTextField txtVelocidadeR;
    private javax.swing.JTextField txtVelocidadeS;
    // End of variables declaration//GEN-END:variables

}
