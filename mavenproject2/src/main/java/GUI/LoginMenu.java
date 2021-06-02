/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ClientSide.Client;
import javax.swing.JOptionPane;
import Messages.Message;
/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class LoginMenu extends javax.swing.JPanel {

    /**
     * Creates new form LoginMenu
     */
    ChattApp mainFrame;
    public LoginMenu(ChattApp mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginMenuHeaderLBL = new javax.swing.JLabel();
        userNameInputLBL = new javax.swing.JLabel();
        loginBTN = new javax.swing.JButton();
        userNameINP = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));

        loginMenuHeaderLBL.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        loginMenuHeaderLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginMenuHeaderLBL.setText("CHAT'N");

        userNameInputLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userNameInputLBL.setText("Username");

        loginBTN.setBackground(new java.awt.Color(204, 255, 255));
        loginBTN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        loginBTN.setForeground(new java.awt.Color(0, 0, 0));
        loginBTN.setText("Login");
        loginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBTNActionPerformed(evt);
            }
        });

        userNameINP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameINPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(userNameInputLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userNameINP, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(126, 126, 126)
                            .addComponent(loginMenuHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginMenuHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameInputLBL)
                    .addComponent(userNameINP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userNameINPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameINPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameINPActionPerformed

    private void loginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBTNActionPerformed
        String userName = userNameINP.getText().toString();
        if(userName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "You need to input a username");
            return;
        }
        Message message = new Message(Message.MessageTypes.USERNAME);
        message.content = userName;
        this.mainFrame.client.Send(message);
        this.mainFrame.client.userName =userName;

    }//GEN-LAST:event_loginBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBTN;
    private javax.swing.JLabel loginMenuHeaderLBL;
    private javax.swing.JTextField userNameINP;
    private javax.swing.JLabel userNameInputLBL;
    // End of variables declaration//GEN-END:variables
}
