/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Messages.Message;
import Messages.RoomMessage;
import javax.swing.DefaultListModel;

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
    
     public void AddTextMessageToChat(String senderName, String content)
    {
        String message = senderName + ": "+content;
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

        personelRoomHeaderLBL.setText("Personel Room");

        jScrollPane1.setViewportView(personelRoomChatJL);

        personelRoomMessageSendBTN.setText("Send");
        personelRoomMessageSendBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personelRoomMessageSendBTNActionPerformed(evt);
            }
        });

        personelRoomSendFileBTN.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(personelRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(personelRoomMessageSendBTN)
                                .addGap(18, 18, 18)
                                .addComponent(personelRoomSendFileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(personelRoomHeaderLBL)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(personelRoomHeaderLBL)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personelRoomMessageINP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personelRoomMessageSendBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personelRoomSendFileBTN))
                .addContainerGap(140, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void personelRoomMessageSendBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personelRoomMessageSendBTNActionPerformed
        // TODO add your handling code here:
        String textMessageINP = personelRoomMessageINP.getText().toString();
        if(textMessageINP.isBlank())
        {
            return;
        }
        RoomMessage roomMsg = new RoomMessage("personel",
                this.mainFrame.client.userName,RoomMessage.RoomMessageType.TEXT,textMessageINP);
        Message msg = new Message(Message.MessageTypes.PERSONEL_ROOM_MESSAGE);
        msg.content = roomMsg;
        this.mainFrame.client.Send(msg);
    }//GEN-LAST:event_personelRoomMessageSendBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> personelRoomChatJL;
    public javax.swing.JLabel personelRoomHeaderLBL;
    private javax.swing.JTextField personelRoomMessageINP;
    private javax.swing.JButton personelRoomMessageSendBTN;
    private javax.swing.JButton personelRoomSendFileBTN;
    // End of variables declaration//GEN-END:variables
}