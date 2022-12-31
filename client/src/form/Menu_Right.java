/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import component.Item_People;
import event.EventMenuRight;
import event.EventSidebarRight;
import event.PublicEvent;
import models.Room;
import net.miginfocom.swing.MigLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author RAVEN
 */
public class Menu_Right extends javax.swing.JPanel {
    public static ArrayList<String> users = new ArrayList<>();

    /**
     * Creates new form Menu_Left
     */
    public Menu_Right() {

        initComponents();
        init();
    }

    private void init() {
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
    PublicEvent.getInstance().addEventMenuRight(new EventMenuRight() {
           @Override
            public void addUser(String user) {
               if(users.contains(user)) {
                   users.remove(user);
               } else {
                   users.add(user);
               };
               showUsers();
            }
        });
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        createBtn = new javax.swing.JButton();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(249, 249, 249));

        jLabel2.setText("Enter your room name");

        createBtn.setBackground(new java.awt.Color(51, 204, 255));
        createBtn.setForeground(new java.awt.Color(51, 51, 51));
        createBtn.setText("Create room");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    createBtnActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input)
                    .addComponent(createBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(menuList))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_createBtnActionPerformed
        // TODO add your handling code here:
        String roomName = input.getText();
        if(!roomName.trim().isEmpty() && users.size() > 1){
            String roomId = UUID.randomUUID().toString();
            Room room = new Room(roomId, roomName, users);
            PublicEvent.getInstance().getEventMainChat().createRoom(room);
            menuList.removeAll();
            input.setText("");
            menuList.repaint();
            menuList.revalidate();
        }

    }//GEN-LAST:event_createBtnActionPerformed

    public void showUsers() {
        menuList.removeAll();
        users.forEach(user -> {
            menuList.add(new Item_People(user), "wrap");
        }
        );
        menuList.repaint();
        menuList.revalidate();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createBtn;
    private javax.swing.JTextField input;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane menuList;
    // End of variables declaration//GEN-END:variables
}
