package com.consulta.cnpj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
 * Classe principal responsável pela visão do software
 */

public class Principal {
    private JPanel painelMae;
    private JButton consultarBotao;
    private JTextField entradaCnpj;
    private JScrollPane scroll;
    private JTextArea respostaText;
    private String resposta;


    public Principal() {
        //Botão de Consulta
        consultarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verificadores de entrada de dados
                if(entradaCnpj.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Preencha o campo CNPJ",
                            "Erro Preenchimento", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!entradaCnpj.getText().matches("^[0-9]+$")){
                    JOptionPane.showMessageDialog(null,"Preencha o campo CNPJ somente usando números",
                            "Erro Preenchimento", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!entradaCnpj.getText().equals("34238864000168") && !entradaCnpj.getText().equals("54447820000155")){
                    JOptionPane.showMessageDialog(null,"Preencha o campo CNPJ somente usando 34238864000168 ou 54447820000155",
                            "Erro Preenchimento", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Atribui a string o valor da entradaCnpj
                String cnpj = entradaCnpj.getText();

                //Verifica se tem 14 digitos, executa a consulta e coloca na tela a resposta
                if (cnpj.length() == 14) {
                    CnpjConsulta consulta = new CnpjConsulta();
                    try {
                        resposta = consulta.consultar(cnpj);
                        respostaText.setText(resposta);
                    } catch (IOException | InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar CNPJ: " + ex.getMessage(),
                                "Erro Consulta", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Preencha corretamente o campo CNPJ com 14 números",
                            "Erro Preenchimento", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Principal().painelMae);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
