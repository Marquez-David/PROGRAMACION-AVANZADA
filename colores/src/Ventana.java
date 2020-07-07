import javax.swing.*;

public class Ventana extends JFrame {

 

    private JButton jButton1;

    private JButton jButton2;

    private JButton jButton3;

    private JButton jButton4;

 

    public Ventana() {

        initComponents();

        setVisible(true);

        new Pintor(jButton1).start();

        new Pintor(jButton2).start();

        new Pintor(jButton3).start();

        new Pintor(jButton4).start();

    }

 

    private void initComponents() {

        jButton1 = new JButton();

        jButton2 = new JButton();

        jButton3 = new JButton();

        jButton4 = new JButton();

 

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Colores");

        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

 

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(

            layout.createParallelGroup(GroupLayout.Alignment.LEADING)

            .addGroup(layout.createSequentialGroup()

                .addGap(116, 116, 116)

                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)

                .addGap(43, 43, 43)

                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)

                .addGap(42, 42, 42)

                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)

                .addGap(38, 38, 38)

                .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)

                .addContainerGap(124, Short.MAX_VALUE))

        );

        layout.setVerticalGroup(

            layout.createParallelGroup(GroupLayout.Alignment.LEADING)

            .addGroup(layout.createSequentialGroup()

                .addGap(118, 118, 118)

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)

                   .addComponent(jButton4,GroupLayout.Alignment.LEADING,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                   .addComponent(jButton3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                   .addComponent(jButton2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                   .addComponent(jButton1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))

                .addContainerGap(130, Short.MAX_VALUE))

        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width-961)/2, (screenSize.height-422)/2, 961, 422);

    }// </editor-fold>

 

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Ventana();

            }

        });

    }

}