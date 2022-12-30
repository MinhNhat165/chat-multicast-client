package component;

import models.ChatMessage;
import swing.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(ChatMessage chatMessage) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(chatMessage.getText());
        item.setTime(chatMessage.getCreatedAt());
        item.setUserProfile(chatMessage.getSender());
        body.add(item, "wrap, w 100::80%");
        // ::80% set max with 80%
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }

    public void addItemRight(ChatMessage chatMessage) {
        Chat_Right item = new Chat_Right();
        item.setText(chatMessage.getText());
        body.add(item, "wrap, al right, w 100::80%");
        // ::80% set max with 80%
        body.repaint();
        body.revalidate();
        item.setTime();
        scrollToBottom();
    }

    public void addItemCenter(ChatMessage chatMessage) {
        Chat_Center item = new Chat_Center();
        item.setText(chatMessage.getSender() + " " +chatMessage.getText()+ " chat at " + chatMessage.getCreatedAt());
        body.add(item, "wrap, al center, w 100::80%");
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }

    public void setItems(ArrayList<ChatMessage> chatMessages) {
        body.removeAll();
        chatMessages.forEach(this::addItem);
    }

    public void addItem(ChatMessage chatMessage) {
        switch(chatMessage.getPosition()) {
            case "center":
                addItemCenter(chatMessage);
                break;
            case "left":
                addItemLeft(chatMessage);
                break;
            case "right":
                addItemRight(chatMessage);
            default:
        }

    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
                bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 826, Short.MAX_VALUE));
        bodyLayout.setVerticalGroup(
                bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 555, Short.MAX_VALUE));

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sp));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sp));
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
