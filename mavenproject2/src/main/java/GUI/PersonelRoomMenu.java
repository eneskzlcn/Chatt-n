/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Messages.FileMessage;
import Messages.Message;
import Messages.RoomMessage;
import Utilities.FileUtilities;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class PersonelRoomMenu extends javax.swing.JPanel {

    /**
     * Creates new form PersonelRoomMenu
     */
    ChattApp mainFrame;
    DefaultListModel chatPanelModel;

    public PersonelRoomMenu(ChattApp mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        chatPanelModel = new DefaultListModel();
        this.personelRoomChatJL.setModel(chatPanelModel);
    }
    //add text message formatted to the chat.
    public void AddTextMessageToChat(String senderName, String content) {
        String message = senderName + ": " + content;
        chatPanelModel.addElement(message);
    }
    //add message directly to the chat
    public void AddTextMessageToChat(String message) {
        chatPanelModel.addElement(message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personelRoomHeaderLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personelRoomChatJL = new javax.swing.JList<>();
        personelRoomMessageINP = new javax.swing.JTextField();
        personelRoomMessageSendBTN = new javax.swing.JButton();
        personelRoomSendFileBTN = new javax.swing.JButton();
        leavePersonelRoomBTN = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));

        personelRoomHeaderLBL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        personelRoomHeaderLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        personelRoomHeaderLBL.setText("Personel Room");

        personelRoomChatJL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personelRoomChatJLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(personelRoomChatJL);

        personelRoomMessageSendBTN.setBackground(new java.awt.Color(204, 255, 255));
        personelRoomMessageSendBTN.setForeground(new java.awt.Color(51, 51, 51));
        personelRoomMessageSendBTN.setText("Send");
        personelRoomMessageSendBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personelRoomMessageSendBTNActionPerformed(evt);
            }
        });

        personelRoomSendFileBTN.setBackground(new java.awt.Color(204, 255, 255));
        personelRoomSendFileBTN.setForeground(new java.awt.Color(51, 51, 51));
        personelRoomSendFileBTN.setText("+");
        personelRoomSendFileBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personelRoomSendFileBTNActionPerformed(evt);
            }
        });

        leavePersonelRoomBTN.setBackground(new java.awt.Color(204, 255, 255));
        leavePersonelRoomBTN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        leavePersonelRoomBTN.setForeground(new java.awt.Color(0, 0, 0));
        leavePersonelRoomBTN.setText("LEAVE");
        leavePersonelRoomBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leavePersonelRoomBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(personelRoomHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(personelRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personelRoomMessageSendBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personelRoomSendFileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(leavePersonelRoomBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(personelRoomHeaderLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personelRoomMessageSendBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(personelRoomSendFileBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personelRoomMessageINP))
                .addGap(43, 43, 43)
                .addComponent(leavePersonelRoomBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void personelRoomMessageSendBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personelRoomMessageSendBTNActionPerformed
        // this buttons sends given inp text to the personel room chat.
        //control input
        //...
        String textMessageINP = personelRoomMessageINP.getText().toString();
        if (textMessageINP.isBlank()) {
            return;
        }
        //...
        //finish control input
        // then create a personel room message and send to server.
        RoomMessage roomMsg = new RoomMessage("personel",
                this.mainFrame.client.userName, RoomMessage.RoomMessageType.TEXT, textMessageINP);
        Message msg = new Message(Message.MessageTypes.PERSONEL_ROOM_MESSAGE);
        msg.content = roomMsg;
        this.mainFrame.client.Send(msg);
        this.personelRoomMessageINP.setText("");
    }//GEN-LAST:event_personelRoomMessageSendBTNActionPerformed
    
    // if a message contains text below , it is a downloadable text.
    public boolean isMessageDownloadsFile(String message) {
        if (message.contains("Click to see download button!")) {
            return true;
        }
        return false;
    }

    public String getFileNameFromMessageByGivenType(String userMessage) {
        return userMessage.split(" ")[2];
    }
    private void personelRoomChatJLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personelRoomChatJLMouseClicked
        // if a downloadable message is clicked on list, then shows a input dialogue
        //if user wants to download the file or not.
        //if it is yes, send a download file request to server.
        String userMessage = personelRoomChatJL.getSelectedValue();
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
                personelRoomChatJL.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_personelRoomChatJLMouseClicked


    private void personelRoomSendFileBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personelRoomSendFileBTNActionPerformed

        String chosenFilePath = FileUtilities.chooseFileAndGetItsPath();

        String nameOfChosenFile = FileUtilities.getNameOfFileOnGivenPath(chosenFilePath);

        byte[] contentOfChosenFile = FileUtilities.getContentOfFileOnGivenPath(chosenFilePath);

        FileMessage fileMessage = new FileMessage(nameOfChosenFile, contentOfChosenFile);

        RoomMessage roomMessage = new RoomMessage("personel", this.mainFrame.client.userName, RoomMessage.RoomMessageType.FILE, fileMessage);

        Message message = new Message(Message.MessageTypes.UPLOAD_FILE_REQUEST);
        message.content = roomMessage;

        this.mainFrame.client.Send(message);

        
    }//GEN-LAST:event_personelRoomSendFileBTNActionPerformed
    public void clearPersonelRoom()
    {
        this.chatPanelModel.removeAllElements();
        this.personelRoomMessageINP.setText("");
    }
    private void leavePersonelRoomBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leavePersonelRoomBTNActionPerformed
        // TODO add your handling code here:
        Message leavePersonelRoomMsg = new Message(Message.MessageTypes.PERSONEL_ROOM_MESSAGE);
        RoomMessage leaveRoomMsg = new RoomMessage("personel",this.mainFrame.client.userName,RoomMessage.RoomMessageType.LEAVE,null);
        leavePersonelRoomMsg.content = leaveRoomMsg;
        this.mainFrame.client.Send(leavePersonelRoomMsg);
        
        Message allUserListMsg = new Message(Message.MessageTypes.ALL_USERS);
        this.mainFrame.changeMenu(this.mainFrame.personelRoomMenu,this.mainFrame.allUsersMenu);
        this.mainFrame.client.Send(allUserListMsg);
    }//GEN-LAST:event_leavePersonelRoomBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leavePersonelRoomBTN;
    private javax.swing.JList<String> personelRoomChatJL;
    public javax.swing.JLabel personelRoomHeaderLBL;
    private javax.swing.JTextField personelRoomMessageINP;
    private javax.swing.JButton personelRoomMessageSendBTN;
    private javax.swing.JButton personelRoomSendFileBTN;
    // End of variables declaration//GEN-END:variables
}
