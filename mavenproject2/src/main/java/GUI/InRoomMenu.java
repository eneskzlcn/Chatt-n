/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Messages.*;
import Utilities.FileUtilities;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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
    public void AddMessageToChat(String message)
    {
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

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));

        inRoomNameLBL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        inRoomNameLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inRoomNameLBL.setText("Room Name");

        inRoomChatPanelJL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inRoomChatPanelJLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inRoomChatPanelJL);

        inRoomSendTextMessageBTN.setBackground(new java.awt.Color(204, 255, 255));
        inRoomSendTextMessageBTN.setForeground(new java.awt.Color(51, 51, 51));
        inRoomSendTextMessageBTN.setText("Send");
        inRoomSendTextMessageBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inRoomSendTextMessageBTNActionPerformed(evt);
            }
        });

        leaveRoomBTN.setBackground(new java.awt.Color(204, 255, 255));
        leaveRoomBTN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        leaveRoomBTN.setForeground(new java.awt.Color(0, 0, 0));
        leaveRoomBTN.setText("Leave");
        leaveRoomBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRoomBTNActionPerformed(evt);
            }
        });

        inRoomSendFileBTN.setBackground(new java.awt.Color(204, 255, 255));
        inRoomSendFileBTN.setForeground(new java.awt.Color(51, 51, 51));
        inRoomSendFileBTN.setText("+");
        inRoomSendFileBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inRoomSendFileBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(leaveRoomBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inRoomSendTextMessageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inRoomSendFileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(inRoomNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(inRoomNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inRoomSendTextMessageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inRoomSendFileBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addComponent(leaveRoomBTN)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void leaveRoomBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRoomBTNActionPerformed
        // TODO add your handling code here:
        
        RoomMessage leaveRoomMsg = new RoomMessage(this.inRoomNameLBL.getText().toString(),this.mainFrame.client.userName,RoomMessage.RoomMessageType.LEAVE,null);
        Message leaveMsg  = new Message(Message.MessageTypes.IN_ROOM_MESSAGE);
        leaveMsg.content = leaveRoomMsg;
        this.mainFrame.client.Send(leaveMsg);
        
        this.mainFrame.changeMenu(this.mainFrame.inRoomMenu, this.mainFrame.roomsMenu);
        Message msg = new Message(Message.MessageTypes.ALL_ROOMS);
        this.mainFrame.client.Send(msg);
        
     
    }//GEN-LAST:event_leaveRoomBTNActionPerformed
    public void clearInRoom()
    {
        this.messageList.removeAllElements();
        this.inRoomMessageINP.setText("");
    }
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
        this.inRoomMessageINP.setText("");
    }//GEN-LAST:event_inRoomSendTextMessageBTNActionPerformed

    private void inRoomSendFileBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inRoomSendFileBTNActionPerformed
        // TODO add your handling code here:
        String chosenFilePath = FileUtilities.chooseFileAndGetItsPath();

        String nameOfChosenFile = FileUtilities.getNameOfFileOnGivenPath(chosenFilePath);

        byte[] contentOfChosenFile = FileUtilities.getContentOfFileOnGivenPath(chosenFilePath);

        FileMessage fileMessage = new FileMessage(nameOfChosenFile, contentOfChosenFile);

        RoomMessage roomMessage = new RoomMessage(inRoomNameLBL.getText().toString(), this.mainFrame.client.userName, RoomMessage.RoomMessageType.FILE, fileMessage);

        Message message = new Message(Message.MessageTypes.UPLOAD_FILE_REQUEST);
        message.content = roomMessage;

        this.mainFrame.client.Send(message);
    }//GEN-LAST:event_inRoomSendFileBTNActionPerformed

     public boolean isMessageDownloadsFile(String message) {
        if (message.contains("Click to see download button!")) {
            return true;
        }
        return false;
    }

    public String getFileNameFromMessageByGivenType(String userMessage) {
        return userMessage.split(" ")[2];
    }
    
    private void inRoomChatPanelJLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inRoomChatPanelJLMouseClicked
        // TODO add your handling code here:
         String userMessage = inRoomChatPanelJL.getSelectedValue();
        if (userMessage == null) {
            return;
        }
        if (isMessageDownloadsFile(userMessage)) {

            int inputDialogue = JOptionPane.showConfirmDialog(null, "Do you want to download the file " + "?",
                    "Download File", JOptionPane.YES_NO_OPTION);

            if (inputDialogue == 0) {
                //yes
                String fileName = getFileNameFromMessageByGivenType(userMessage);
                Message message = new Message(Message.MessageTypes.DOWNLOAD_FILE_REQUEST);
                message.content = fileName;
                this.mainFrame.client.Send(message);
            } else {
                inRoomChatPanelJL.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_inRoomChatPanelJLMouseClicked


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
