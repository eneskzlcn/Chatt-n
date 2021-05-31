/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Messages.CreatePersonelRoomMessage;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Messages.Message;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class AllUsersMenu extends javax.swing.JPanel {

    /**
     * Creates new form AllUsersMenu
     */
    ChattApp mainFrame;
    DefaultListModel userListModel;
    public AllUsersMenu(ChattApp mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.userListModel = new DefaultListModel();
        this.allUsersJL.setModel(userListModel);
    }
    
    public void UpdateUserListWithGivenList(ArrayList<String> userNames)
    {
        userListModel.removeAllElements();
        for(String name : userNames)
        {
            if(name.isBlank() || name.equals(this.mainFrame.client.userName)) continue;
            userListModel.addElement(name);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allUsersMenuHeaderLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allUsersJL = new javax.swing.JList<>();
        startPersonalMessagingBTN = new javax.swing.JButton();
        refreshBTN = new javax.swing.JButton();
        returnMenuBTN = new javax.swing.JButton();

        allUsersMenuHeaderLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        allUsersMenuHeaderLBL.setText("ALL USERS");

        jScrollPane1.setViewportView(allUsersJL);

        startPersonalMessagingBTN.setText("Start Message");
        startPersonalMessagingBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPersonalMessagingBTNActionPerformed(evt);
            }
        });

        refreshBTN.setText("Refresh");
        refreshBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBTNActionPerformed(evt);
            }
        });

        returnMenuBTN.setText("Return Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(allUsersMenuHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startPersonalMessagingBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refreshBTN))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(returnMenuBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(allUsersMenuHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(refreshBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startPersonalMessagingBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(returnMenuBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBTNActionPerformed
        // TODO add your handling code here:
        Message message = new Message(Message.MessageTypes.ALL_USERS);
        this.mainFrame.client.Send(message);
    }//GEN-LAST:event_refreshBTNActionPerformed

    private void startPersonalMessagingBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPersonalMessagingBTNActionPerformed
        // TODO add your handling code here:
        if(allUsersJL.getSelectedValue() == null) return;
        CreatePersonelRoomMessage cprm = new CreatePersonelRoomMessage();
        cprm.creator = this.mainFrame.client.userName;
        cprm.joiner = allUsersJL.getSelectedValue().toString();
        Message message = new Message(Message.MessageTypes.CREATE_PERSONEL_ROOM);
        message.content = cprm;
        this.mainFrame.client.Send(message);
        
    }//GEN-LAST:event_startPersonalMessagingBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> allUsersJL;
    private javax.swing.JLabel allUsersMenuHeaderLBL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshBTN;
    private javax.swing.JButton returnMenuBTN;
    private javax.swing.JButton startPersonalMessagingBTN;
    // End of variables declaration//GEN-END:variables
}
