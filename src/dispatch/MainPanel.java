package dispatch;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/*
 * @author Ismael
 */
public class MainPanel extends javax.swing.JFrame 
{
    /**
     * Creates new form MainPanel
     */
    public MainPanel()
    {
        this.m_dispatch = new Dispatch();
        if(m_dispatch.isFreshStart())
        {
            try 
            {
                initComponents();
                Image i = ImageIO.read(getClass().getResource("logo-douanes.png"));
                this.setIconImage(i);
            } catch (IOException ex) {
                Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            InitMainPanel panel = new InitMainPanel(m_dispatch, this);
            panel.setVisible(true);
        }
        else
        {
            try 
            {
                initComponents();
                updateScreen(m_dispatch.printResult());
                Image i = ImageIO.read(getClass().getResource("logo-douanes.png"));
                this.setIconImage(i);
                this.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        modifyButton = new javax.swing.JButton();
        rotationButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        readyCheckBox = new javax.swing.JCheckBox();
        resetButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Screen = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("L-16");
        setBackground(new java.awt.Color(96, 98, 0));
        setResizable(false);

        canvas1.setVisible(false);

        modifyButton.setBackground(new java.awt.Color(0, 255, 64));
        modifyButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        modifyButton.setText("MODIFIER LES NOMS");
        modifyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        rotationButton.setBackground(new java.awt.Color(255, 255, 0));
        rotationButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rotationButton.setText("ROTATION");
        rotationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rotationButton.setEnabled(false);
        rotationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotationButtonActionPerformed(evt);
            }
        });

        quitButton.setBackground(new java.awt.Color(255, 0, 0));
        quitButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        quitButton.setText("QUITTER");
        quitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        readyCheckBox.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        readyCheckBox.setText("PRET");
        readyCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                readyCheckBoxStateChanged(evt);
            }
        });
        readyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readyCheckBoxActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        resetButton.setText("Reset");
        resetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        Screen.setEditable(false);
        Screen.setColumns(20);
        Screen.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        Screen.setRows(5);
        Screen.setText("   ************* LOGICIEL DE REPARTITION *************\n\n              \n\t\t Bonjour mon Commandant !\n\n\t      Chargement du logiciel terminé.\n\n\n\t  Cilquez sur \"PRET\" puis sur \"ROTATION\"\n\t    pour lancer une nouvelle assignation.");
        jScrollPane1.setViewportView(Screen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(modifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rotationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(readyCheckBox)
                                    .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(283, 283, 283)
                                .addComponent(resetButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(readyCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Used Functions
    public void updateScreen(String text)
    {        
        Screen.setText(text);
    }
    
    //User's actions
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        EditMembersPanel editMembersPanel = new EditMembersPanel(m_dispatch, this);
        editMembersPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void rotationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotationButtonActionPerformed
        m_dispatch.run();
        updateScreen(m_dispatch.printResult());
        readyCheckBox.setSelected(false);
        rotationButton.setEnabled(false);
    }//GEN-LAST:event_rotationButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (response == JOptionPane.YES_OPTION) 
        {
            // Reset elements
            m_dispatch.resetMembersName();
            m_dispatch.resetJoinedSquadList();
            m_dispatch.resetSquadsLeaderName();
            m_dispatch.resetSquadsMembersList();
            m_dispatch.resetSquadsHistoryList();
            m_dispatch.resetRoundIndex();

            InitMembersPanel resetPanel = new InitMembersPanel(m_dispatch, this);
            resetPanel.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void readyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readyCheckBoxActionPerformed
        if(readyCheckBox.isSelected())
        {
            rotationButton.setEnabled(true);
        }
        else
        {
            rotationButton.setEnabled(false);
        }
    }//GEN-LAST:event_readyCheckBoxActionPerformed

    private void readyCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_readyCheckBoxStateChanged
        
    }//GEN-LAST:event_readyCheckBoxStateChanged
    
    private Dispatch m_dispatch;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Screen;
    private java.awt.Canvas canvas1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JCheckBox readyCheckBox;
    private javax.swing.JToggleButton resetButton;
    private javax.swing.JButton rotationButton;
    // End of variables declaration//GEN-END:variables
}
