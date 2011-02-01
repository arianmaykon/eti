package br.ufc.eti.poo.exercicios.exercicio4;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HelloWorldSwing {

  public static void main(String[] args) {
    final JFrame frame = new JFrame("HelloWorldSwing");
    
    //Usando JLabel
    final JLabel label = new JLabel("Hello World");
    label.setBounds(35, 28, 105, 28);
    label.setToolTipText( "This is label1" );
    frame.getContentPane().add(label);
    
    
    
    //Construtor JLabel com string, Icon e argumentos de alinhamento  
     //Icon duke = new ImageIcon( getClass().getResource(  "dukeWaveRed.gif" ) );
     Icon duke = new HelloWorldSwing().getIcon("dukeWaveRed.gif");
     JLabel label2 = new JLabel( "Label with text and icon", duke,             
         SwingConstants.LEFT );                                         
     label2.setToolTipText( "This is label2" );                        
     frame.getContentPane().add( label2 ); // adiciona label2 ao JFrame
     label2.setBounds(150, 150, 300, 150);
     
     
    
     
     
     //JLabel: Principais Métodos
     JLabel label3 = new JLabel(); // Construtor JLabel sem argumentos
     label3.setText( "Label with icon and text at bottom" );   
     label3.setIcon( duke ); // adiciona o ícone ao JLabel             
     label3.setHorizontalTextPosition( SwingConstants.CENTER );
     label3.setVerticalTextPosition( SwingConstants.BOTTOM );  
     label3.setToolTipText( "This is label3" );                
     frame.getContentPane().add( label3 );
    
    
    
    //Usando JTextField
    JTextField jtfNumero1 = new JTextField();
    // constrói textfield com 10 colunas
    //JTextField jtfNumero2 = new JTextField(10);
    //Constrói campo de texto com texto padrão  
    JTextField jtfNumero2 = new JTextField("Texto Padrão");
    jtfNumero2.setEditable( false );
    jtfNumero2.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        
    jtfNumero1.setBounds(35, 75, 105, 28);
    jtfNumero2.setBounds(35, 140, 105, 28);
    
    jtfNumero2.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",1,12)));  
    
    frame.getContentPane().add(jtfNumero1);
    frame.getContentPane().add(jtfNumero2);
    
    
    
    //Constrói passwordfield com o texto padrão
    JPasswordField passwordField = new JPasswordField( "Hidden text" );
    frame.getContentPane().add(passwordField);
    
    
    
    passwordField.addKeyListener(new KeyAdapter(){
       // trata pressionamento de qualquer tecla
	   public void keyPressed( KeyEvent event )
	   {
	      String line1 = String.format( "Key pressed: %s", 
	         event.getKeyText( event.getKeyCode() )); // gera saída de tecla pressionada
	      JOptionPane.showMessageDialog(null, line1);
	   } // fim do método keyPressed
	
	   // trata liberação de qualquer tecla
	   public void keyReleased( KeyEvent event )
	   {
	      String line1 = String.format( "Key released: %s", 
	         event.getKeyText( event.getKeyCode() )); // gera saída de tecla pressionada
	      JOptionPane.showMessageDialog(null, line1);
	   } // fim do método keyReleased
	
	   // trata pressionamento de uma tecla de ação
	   public void keyTyped( KeyEvent event )
	   {
	      String line1 = String.format( "Key typed: %s", event.getKeyChar());
	      JOptionPane.showMessageDialog(null, line1);
	   } // fim do método keyTyped

    } );
    
    
    
    //Usando JCheckBox
    JCheckBox boldJCheckBox = new JCheckBox( "Bold" ); 
    JCheckBox italicJCheckBox = new JCheckBox( "Italic" ); 
    
    frame.getContentPane().add(boldJCheckBox);
    frame.getContentPane().add(italicJCheckBox);
    
    boldJCheckBox.addItemListener(new ItemListener(){
      public void itemStateChanged( ItemEvent event )
      {
        JOptionPane.showMessageDialog(null, "Bold Clicado!!!");
      }
    } );
    
    
    //Usando JRadioButton
    JRadioButton plainJRadioButton; // seleciona texto simples
    JRadioButton boldJRadioButton; // seleciona texto em negrito
    JRadioButton italicJRadioButton; // seleciona texto em itálico
    JRadioButton boldItalicJRadioButton; // negrito e itálico
    ButtonGroup radioGroup; // buttongroup para armazenar botões de opção
    
    
    
    // cria botões de opção
	  plainJRadioButton = new JRadioButton( "Plain", true );            
	  boldJRadioButton = new JRadioButton( "Bold", false );             
	  italicJRadioButton = new JRadioButton( "Italic", false );         
	  boldItalicJRadioButton = new JRadioButton( "Bold/Italic", false );
	  frame.getContentPane().add( plainJRadioButton ); // adiciona botão no estilo simples ao JFrame
	  frame.getContentPane().add( boldJRadioButton ); // adiciona botão de negrito ao JFrame
	  frame.getContentPane().add( italicJRadioButton ); // adiciona botão de itálico ao JFrame
	  frame.getContentPane().add( boldItalicJRadioButton ); // adiciona botão de negrito e itálico
	  
	  // cria relacionamento lógico entre JRadioButtons
	  radioGroup = new ButtonGroup(); // cria ButtonGroup
	  radioGroup.add( plainJRadioButton ); // adiciona simples ao grupo
	  radioGroup.add( boldJRadioButton ); // adiciona negrito ao grupo
	  radioGroup.add( italicJRadioButton ); // adiciona itálico ao grupo
	  radioGroup.add( boldItalicJRadioButton ); // adiciona negrito e itálico
	  
	  Font italicFont = new Font( "Serif", Font.ITALIC, 14 );
	  italicJRadioButton.setFont(italicFont);
	  
	  
    
    //Usando JComboBox
    JComboBox imagesJComboBox;
    
    String names[] = 
      { "bug1.gif", "bug2.gif",  "travelbug.gif", "buganim.gif" };
    
    imagesJComboBox = new JComboBox( names ); // configura JComboBox
     
    imagesJComboBox.setMaximumRowCount( 3 ); // exibe três linhas
    
    frame.getContentPane().add(imagesJComboBox);
    
    imagesJComboBox.addItemListener(new ItemListener(){
      public void itemStateChanged( ItemEvent event )
      {
        if ( event.getStateChange() == ItemEvent.SELECTED ){
        	JOptionPane.showMessageDialog(null, ((JComboBox)event.getSource()).getSelectedIndex());
        }
      }
    } );
    
    
    
    //Usando JList
    JList colorJList;
    
    String colorNames[] = { "Black", "Blue", "Cyan", 
      "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
      "Orange", "Pink", "Red", "White", "Yellow" };
      
    colorJList = new JList( colorNames ); // cria com colorNames
      
    colorJList.setVisibleRowCount( 5 ); // exibe cinco linhas de uma vez
    
    //frame.getContentPane().add(colorJList);
    frame.getContentPane().add(new JScrollPane( colorJList ));
      
    // não permite múltiplas seleções
    //colorJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    //colorJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
    colorJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
    
    colorJList.setFixedCellWidth( 100 ); // configura a largura
    colorJList.setFixedCellHeight( 15 ); // configura a altura
    
    colorJList.addListSelectionListener(new ListSelectionListener(){
      public void valueChanged( ListSelectionEvent event )
      {
        JOptionPane.showMessageDialog(null, ((JList)event.getSource()).getSelectedValue());
      }
    } );
    
    
    
    //Trabalhando com JMenuBar
    JMenuBar bar = new JMenuBar(); // cria a barra de menus
    frame.setJMenuBar( bar ); // adiciona uma barra de menus ao aplicativo
    
    JMenu fileMenu = new JMenu( "File" ); // cria o menu File
    fileMenu.setMnemonic( 'F' ); // configura o mnemônico como F        

    // cria item de menu About... 
    JMenuItem aboutItem = new JMenuItem( "About..." );       
    aboutItem.setMnemonic( 'A' ); // configura o mnemônico com A       
    fileMenu.add( aboutItem ); // adiciona o item about ao menu File
    
    aboutItem.addActionListener(
         new ActionListener() // classe interna anônima
         {  
            // exibe um diálogo de mensagem quando o usuário seleciona About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( null,
                  "Clicou no About Me",
                  "About", JOptionPane.PLAIN_MESSAGE );
            } // fim do método actionPerformed
         } // fim da classe interna anônima
      ); // fim da chamada para addActionListener
 
 	fileMenu.addSeparator();
 
    JMenuItem exitItem = new JMenuItem( "Exit" ); // cria o item exit
    exitItem.setMnemonic( 'x' ); // configura o mnemônico como x                
      
    fileMenu.add( exitItem ); // adiciona o item exit ao menu File          
      
    exitItem.addActionListener(
         new ActionListener() // classe interna anônima
         {  
            // termina o aplicativo quando o usuário clica exitItem
            public void actionPerformed( ActionEvent event )
            {
               System.exit( 0 ); // encerra o aplicativo
            } // fim do método actionPerformed
         } // fim da classe interna anônima
    ); // fim da chamada para addActionListener

      
    JMenu colorMenu = new JMenu( "Color" ); // cria o menu Color
    colorMenu.setMnemonic( 'C' );
      
    bar.add( fileMenu ); // adiciona o menu File à barra de menus
    bar.add( colorMenu );

    
    //Trabalhando com JPopupMenu
    final JPopupMenu popupMenu = new JPopupMenu();
    JButton amarelo = new JButton("Amarelo");
    JRadioButtonMenuItem vermelho = new JRadioButtonMenuItem("Vermelho");
    popupMenu.add("Azul");
    popupMenu.add(amarelo);
    popupMenu.add(vermelho);
    
    amarelo.addActionListener(
         new ActionListener() // classe interna anônima
         {  
            // termina o aplicativo quando o usuário clica exitItem
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( null, "Clicou no popup Amarelo!!");
               frame.getContentPane().setBackground( Color.YELLOW ); 
            } // fim do método actionPerformed
         } // fim da classe interna anônima
    ); // fim da chamada para addActionListener
    
    vermelho.addActionListener(
         new ActionListener() // classe interna anônima
         {  
            // termina o aplicativo quando o usuário clica exitItem
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( null, "Clicou no popup Vermelho!!");
               frame.getContentPane().setBackground( Color.RED ); 
            } // fim do método actionPerformed
         } // fim da classe interna anônima
    ); // fim da chamada para addActionListener
    
    frame.getContentPane().add(popupMenu);
    
    // declara um MouseListener para a janela a fim de exibir o menu pop-up
    frame.getContentPane().addMouseListener(
         new MouseAdapter() // classe interna anônima
         {  
            // trata eventos de pressionamento do mouse
            public void mousePressed( MouseEvent event )
            { 
               checkForTriggerEvent( event ); // verifica o acionamento
            } // fim do método mousePressed

            // trata eventos de liberação de botão do mouse
            public void mouseReleased( MouseEvent event )
            { 
               checkForTriggerEvent( event ); // verifica o acionamento
            } // fim do método mouseReleased

            // determina se o evento deve acionar o menu de pop-up
            private void checkForTriggerEvent( MouseEvent event )
            {
               if ( event.isPopupTrigger() )                            
                  popupMenu.show(                                       
                     event.getComponent(), event.getX(), event.getY() );
            } // fim do método checkForTriggerEvent
         } // fim da classe interna anônima
      ); // fim da chamada para addMouseListener
    
    
    //Trabalhando com JInternalFrame
    //Cria o quadro interno
    JInternalFrame frameInt = new JInternalFrame(    
                  "Internal Frame", true, true, true, true );
    
    //ImageIcon picture = new ImageIcon("dukeWaveRed.gif");
    MyJPanel p = new MyJPanel();
    frameInt.add(p);
    
    frameInt.getContentPane().setBackground( Color.BLACK ); 
    
    frameInt.toFront();
    frameInt.show();
    
    frame.add(frameInt);
    frameInt.setVisible( true ); 
        
    //Evento Fechar Janela
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Demais Configurações
    frame.setSize(400,400);
    
    //frame.getContentPane().setLayout(null);
    frame.getContentPane().setLayout(new FlowLayout());
    
    //frame.getContentPane().setLayout(null);
    frame.getContentPane().setLayout(new FlowLayout());
    frame.pack();
    frame.setVisible(true);
    frame.repaint();
    
    /*
    //Usando JOptionPane
    Object[] options = {"Yes!", "No, I'll pass",  "Well, if I must"}; 
	int n = JOptionPane.showOptionDialog(
    frame, "Duke is a cartoon mascot. \n" + 
    "Do you still want to cast your vote?", 
    "A Follow-up Question",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null, 
    options,
    options[2]); 
    System.out.println(n);  
      
    JOptionPane.showMessageDialog(null,"Célula já escolhida!!! Escolha uma célula ainda não marcada!!!",
	        "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
	        //ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, PLAIN_MESSAGE

	//Obtém a entrada de usuário a partir dos diálogos de entrada JOptionPane      
      String firstNumber =                                      
         JOptionPane.showInputDialog( "Enter first integer" );  
      
    JOptionPane.showMessageDialog(null, firstNumber);	  

	JOptionPane.showMessageDialog( null, "The firts number is " + firstNumber,
         "Número Lido", JOptionPane.PLAIN_MESSAGE );

	*/
	
  }
  public Icon getIcon(String path){
  	return new ImageIcon( getClass().getResource(path) );
  }
}

// classe para exibir um ImageIcon em um painel
class MyJPanel extends JPanel 
{ 
   private ImageIcon picture; // imagem a ser exibida
   
   // carrega a imagem
   public MyJPanel()
   {  
      picture = new ImageIcon( "dukeWaveRed.gif" ); // configura o ícone
   } // fim do construtor MyJPanel

   // exibe imageIcon no painel
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );
      picture.paintIcon( this, g, 0, 0 ); // exibe o ícone
   } // fim do método paintComponent

   // retorna as dimensões da imagem
   public Dimension getPreferredSize()
   {
      return new Dimension( picture.getIconWidth(),
         picture.getIconHeight() );                
   } // fim do método getPreferredSize         
} // fim da classe MyJPanel

