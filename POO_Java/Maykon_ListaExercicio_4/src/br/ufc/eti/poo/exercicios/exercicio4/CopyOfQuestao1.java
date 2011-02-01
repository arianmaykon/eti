package br.ufc.eti.poo.exercicios.exercicio4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questão 1.
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class CopyOfQuestao1 extends JFrame {	
	
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel mainPanel;
	private JTextField txtFatorial;
	private JTextField txtNumero;


	public CopyOfQuestao1() {
		this.setLayout(new BorderLayout());
/*		Questao1 q1 = new Questao1();
		q1.setSize(640, 480);


		JButton btn = new JButton();
		btn.setText("Teste");
		btn.setSize(100, 100);
		q1.add(btn);
		
		
		q1.setVisible(true);*/


		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setSize(640, 480);
		this.setTitle("Cálculo Fatorial");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocationRelativeTo(null);


        mainPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        txtNumero = new JTextField();
        txtFatorial = new JTextField();

        mainPanel.setName("mainPanel");

        
        jLabel1.setText("Número:");
        jLabel2.setText("Fatorial:");
        
        txtNumero.setSize(20, 50);
        txtNumero.setBounds(20, 200, 20, 20);
//        txtNumero.setLocation(20, 0);
        
        txtFatorial.setBounds(20, 200, 20, 20);

        jButton1.setText("Calcular");
        jButton1.setBounds(100, 100, 100, 100);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


        Container content = this.getContentPane();
        content.setBackground(Color.WHITE);
//        content.setLayout(new FlowLayout());
        content.setLayout(new BorderLayout());

        content.add(jLabel1, BorderLayout.PAGE_START);
        content.add(jLabel2, BorderLayout.AFTER_LAST_LINE);
        content.add(txtNumero);
        content.add(txtFatorial);
        content.add(jButton1, BorderLayout.PAGE_END);
        
//        this.pack();
//        this.add(mainPanel);
	}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int  numero = Integer.parseInt(this.txtNumero.getText());
        int aux = numero;
        for (int i = numero-1; i>0; i--) {
            aux *= i;
        }
        this.txtFatorial.setText(String.valueOf(aux));
    }

    public static void main(String args[]) {
    	CopyOfQuestao1 q1 = new CopyOfQuestao1();
    	q1.setVisible(true);
    }
}