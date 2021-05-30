/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Messages.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class InRoomMenu extends javax.swing.JPanel {

    /**
     * Creates new form InRoomMenu
     */
    ChattApp mainFrame;
    DefaultListModel messageList;
    public InRoomMenu(ChattApp mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.messageList = new DefaultListModel();
        this.inRoomChatPanelJL.setModel(messageList);
    }

    public void AddMessageToChat(String senderName, String content)
    {
        String message = senderName + ": "+content;
        messageList.addElement(message);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inRoomNameLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inRoomChatPanelJL = new javax.swing.JList<>();
        inRoomMessageINP = new javax.swing.JTextField();
        inRoomSendTextMessageBTN = new javax.swing.JButton();
        leaveRoomBTN = new javax.swing.JButton();
        inRoomSendFileBTN = new javax.swing.JButton();

        inRoomNameLBL.setText("Room Name");

        jScrollPane1.setViewportView(inRoomChatPanelJL);

        inRoomSendTextMessageBTN.setText("Send");
        inRoomSendTextMessageBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inRoomSendTextMessageBTNActionPerformed(evt);
            }
        });

        leaveRoomBTN.setText("Leave");
        leaveRoomBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRoomBTNActionPerformed(evt);
            }
        });

        inRoomSendFileBTN.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inRoomNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(inRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inRoomSendTextMessageBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(inRoomSendFileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(leaveRoomBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(inRoomNameLBL)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inRoomSendTextMessageBTN)
                    .addComponent(inRoomMessageINP)
                    .addComponent(inRoomSendFileBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(leaveRoomBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void leaveRoomBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRoomBTNActionPerformed
        // TODO add your handling code here:
        this.mainFrame.changeMenu(this.mainFrame.inRoomMenu, this.mainFrame.roomsMenu);
        Message msg = new Message(Message.MessageTypes.ALL_ROOMS);
        this.mainFrame.client.Send(msg);
    }//GEN-LAST:event_leaveRoomBTNActionPerformed

    private void inRoomSendTextMessageBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inRoomSendTextMessageBTNActionPerformed
        // TODO add your handling code here:
        String textMessageINP = inRoomMessageINP.getText().toString();
        String roomName = inRoomNameLBL.getText().toString();
        if(textMessageINP.isBlank())
        {
            return;
        }
        RoomMessage roomMsg = new RoomMessage(roomName,
                this.mainFrame.client.userName,RoomMessage.RoomMessageType.TEXT,textMessageINP);
        Message msg = new Message(Message.MessageTypes.IN_ROOM_MESSAGE);
        msg.content = roomMsg;
        this.mainFrame.client.Send(msg);
    }//GEN-LAST:event_inRoomSendTextMessageBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> inRoomChatPanelJL;
    private javax.swing.JTextField inRoomMessageINP;
    public javax.swing.JLabel inRoomNameLBL;
    private javax.swing.JButton inRoomSendFileBTN;
    private javax.swing.JButton inRoomSendTextMessageBTN;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leaveRoomBTN;
    // End of variables declaration//GEN-END:variables
}