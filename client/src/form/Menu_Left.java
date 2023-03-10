package form;

import component.Item_People;
import event.EventUser;
import event.PublicEvent;
import models.Room;
import swing.ScrollBar;
import net.miginfocom.swing.MigLayout;

import java.util.ArrayList;
import java.util.Objects;

public class Menu_Left extends javax.swing.JPanel {
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<String> usersConnected = new ArrayList<>();

    public Menu_Left() {
        initComponents();
        init();
    }

    public static int tabActive = 0; // 0 là đang ở group

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        PublicEvent.getInstance().addEventUser(new EventUser() {

            @Override
            public void  removeUserConnected(String user) {
                usersConnected.remove(user);
                menuList.removeAll();
                for (String s : usersConnected) {
                    menuList.add(new Item_People(s), "wrap");
                }
            }
            @Override
            public void addUserConnected(String user) {
                usersConnected.add(user);
            }

            @Override
            public void setUserListConnected(ArrayList<String> arrayList) {
                usersConnected = arrayList;
            }

            @Override
            public int getTabActive() {
               return tabActive;
            }

            @Override
            public void addNewRoom(Room room) {
                System.out.println(room.getName());
                Item_People item_people = new Item_People(room.getName());
                item_people.setId(room.getId());
                menuList.add(item_people, "wrap");
                rooms.add(room);
            }
        });

        showMessage();
    }

    private void showMessage() {
        //  test data
        menuList.removeAll();
        Item_People item_people = new Item_People("General");
        item_people.setId("111111");
        menuList.add(item_people, "wrap");
        rooms.forEach(room -> {
            menuList.add(new Item_People(room.getName()), "wrap");
        });
        refreshMenuList();
    }

    private void showUserConnected() {
        //  test data
        menuList.removeAll();
        for (String s : usersConnected) {
            menuList.add(new Item_People(s), "wrap");
        }
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new component.MenuButton();
        menuGroup = new component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setIconSelected(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/message_selected.png")))); // NOI18N
        menuMessage.setIconSimple(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/message.png")))); // NOI18N
        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuGroup.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/group.png")))); // NOI18N
        menuGroup.setIconSelected(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/group_selected.png")))); // NOI18N
        menuGroup.setIconSimple(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/icon/group.png")))); // NOI18N
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        sp.setBackground(new java.awt.Color(242, 242, 242));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(242, 242, 242));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        tabActive = 0;
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            showMessage();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
       tabActive = 1;
        if (!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            showUserConnected();
        }
    }//GEN-LAST:event_menuGroupActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuList;
    private component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
