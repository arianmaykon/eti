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
import javax.swing.SwingConstants;

/**
 * Questão 1.
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class Questao1 extends JFrame {	
	
	private JButton jButton1;
	private JLabel jLblNumero;
	private JLabel jLblFatorial;
	private JPanel mainPanel;
	private JTextField txtFatorial;
	private JTextField txtNumero;


	Questao1() {
		this.setLayout(new BorderLayout());
/*		Questao1 q1 = new Questao1();
		q1.setSize(640, 480);


		JButton btn = new JButton();
		btn.setText("Teste");
		btn.setSize(100, 100);
		q1.add(btn);
		
		
		q1.setVisible(true);*/


//		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setSize(640, 480);
		this.setTitle("Cálculo Fatorial");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocationRelativeTo(null);


        mainPanel = new JPanel();
        jLblNumero = new JLabel("Número:");
        jLblFatorial = new JLabel("Fatorial:");
        jButton1 = new JButton();
        txtNumero = new JTextField(10);
        txtFatorial = new JTextField("aloha", 20);

        mainPanel.setName("mainPanel");

        
        txtNumero.setSize(20, 50);
//        txtNumero.setBounds(20, 200, 20, 20);
        txtNumero.setLocation(50, 50);
//        txtNumero.setLocation(20, 0);
        
        txtFatorial.setBounds(20, 200, 20, 20);

        jButton1.setText("Calcular");
        jButton1.setBounds(100, 100, 100, 100);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


/*        Container content = this.getContentPane();
        content.setBackground(Color.GRAY);
//        content.setLayout(new FlowLayout());
        content.setLayout(new BorderLayout());

//        content.add(jLblNumero, BorderLayout.PAGE_START);
        content.add(jLblNumero);
//        content.add(jLabel2, BorderLayout.AFTER_LAST_LINE);
        content.add(txtNumero);
//        content.add(txtFatorial);
//        content.add(jButton1, BorderLayout.PAGE_END);*/





        this.add(jLblNumero);
        this.add(jLblFatorial, SwingConstants.NEXT);
//##############################################################################
/*        this.getContentPane().setLayout(new FlowLayout());
//        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(jLblNumero);
        this.getContentPane().add(jLblFatorial);
        this.getContentPane().add(txtNumero);
        this.getContentPane().add(txtFatorial);*/


//        this.pack();
//        this.add(mainPanel);
        
        this.setVisible(true);
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
    	new Questao1();
    }
}