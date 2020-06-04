/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restoran;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhsis
 */
public class Transaksi extends javax.swing.JFrame {
    int uid = Session.getS_iduser();
    String greetUser = Session.getS_namauser();
    private Connection koneksi;
    private final Koneksi db = new Koneksi();
    

    /**
     * Creates new form Home
     */
    DefaultTableModel model;
    public Transaksi() {
        initComponents();
        this.setLocationRelativeTo(null);
        labelGreeting.setText(greetUser);
        labelTitle.setText("Transaksi");
        
        model = (DefaultTableModel) tablePesanan.getModel();
        tablePesanan.setModel(model);
        
        LoadData();
        //btnPesanan.getActionCommand();
    }
    public void LoadData() {
        db.dbConnection();
        try {
            Statement st = db.getConnection().createStatement();
            String sql = "SELECT * FROM transaksi";
            ResultSet r = st.executeQuery(sql);
            
            while(r.next()) {
                Object[] obj = new Object[6];
                obj[1] = r.getString("idtransaksi");
                obj[2] = r.getString("total");
                obj[3] = r.getString("bayar");
                obj[4] = r.getString("kembalian");
                obj[5] = r.getString("status");
                
                model.addRow(obj);
                noTable(); //method nomor
            }
            
            r.close();
            st.close();
        } catch(SQLException e) {
            System.out.println("Terjadi Error");
        }
    }
    public void noTable()
	{
		int baris = tablePesanan.getRowCount();
		for(int a=0; a<baris; a++)
		{
			String nomor = String.valueOf(a+1);
			tablePesanan.setValueAt(nomor + ".", a, 0);
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

        panelSidebar = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnPesanan = new javax.swing.JButton();
        labelGreeting = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePesanan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 68, 68));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        panelSidebar.setBackground(new java.awt.Color(0, 35, 71));

        btnLogout.setBackground(new java.awt.Color(0, 51, 102));
        btnLogout.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icon/icons8_Sign_Out_32px.png"))); // NOI18N
        btnLogout.setText("   Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusable(false);

        btnPelanggan.setBackground(new java.awt.Color(0, 51, 102));
        btnPelanggan.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        btnPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icon/icons8_Person_32px.png"))); // NOI18N
        btnPelanggan.setText("Pelanggan");
        btnPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPelanggan.setFocusable(false);
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });

        btnTransaksi.setBackground(new java.awt.Color(0, 51, 102));
        btnTransaksi.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        btnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icon/icons8_Slider__32px.png"))); // NOI18N
        btnTransaksi.setText("   Transaksi");
        btnTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransaksi.setFocusable(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });

        btnMenu.setBackground(new java.awt.Color(0, 51, 102));
        btnMenu.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icon/icons8_Xbox_Menu_32px.png"))); // NOI18N
        btnMenu.setText("       Menu");
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setFocusable(false);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnPesanan.setBackground(new java.awt.Color(0, 51, 102));
        btnPesanan.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnPesanan.setForeground(new java.awt.Color(255, 255, 255));
        btnPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icon/icons8_Open_Envelope_32px.png"))); // NOI18N
        btnPesanan.setText("   Pesanan");
        btnPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesanan.setFocusable(false);
        btnPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesananActionPerformed(evt);
            }
        });

        labelGreeting.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        labelGreeting.setForeground(new java.awt.Color(255, 255, 255));
        labelGreeting.setText("jLabel2");

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPesanan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(labelGreeting)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelGreeting)
                .addGap(55, 55, 55)
                .addComponent(btnPesanan)
                .addGap(18, 18, 18)
                .addComponent(btnTransaksi)
                .addGap(18, 18, 18)
                .addComponent(btnMenu)
                .addGap(18, 18, 18)
                .addComponent(btnPelanggan)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        panelMain.setBackground(new java.awt.Color(0, 10, 20));

        labelTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("Manajemen Restoran");

        tablePesanan.setAutoCreateRowSorter(true);
        tablePesanan.setBackground(new java.awt.Color(0, 10, 20));
        tablePesanan.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        tablePesanan.setForeground(new java.awt.Color(255, 255, 255));
        tablePesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID Transaksi", "Total", "Bayar", "Kembalian", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePesanan.setFocusable(false);
        tablePesanan.setGridColor(new java.awt.Color(0, 10, 20));
        tablePesanan.setPreferredSize(new java.awt.Dimension(675, 300));
        tablePesanan.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tablePesanan.setSelectionForeground(new java.awt.Color(0, 10, 20));
        jScrollPane1.setViewportView(tablePesanan);
        if (tablePesanan.getColumnModel().getColumnCount() > 0) {
            tablePesanan.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablePesanan.getColumnModel().getColumn(0).setMaxWidth(200);
            tablePesanan.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablePesanan.getColumnModel().getColumn(2).setMaxWidth(300);
            tablePesanan.getColumnModel().getColumn(3).setPreferredWidth(50);
            tablePesanan.getColumnModel().getColumn(3).setMaxWidth(300);
            tablePesanan.getColumnModel().getColumn(4).setPreferredWidth(70);
            tablePesanan.getColumnModel().getColumn(4).setMaxWidth(300);
            tablePesanan.getColumnModel().getColumn(5).setPreferredWidth(50);
            tablePesanan.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitle))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
        Pelanggan pl = new Pelanggan();
        pl.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        JOptionPane.showMessageDialog(null, "Anda di bagian Transaksi");
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesananActionPerformed
        Home hm = new Home();
        hm.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnPesananActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        Menu mn = new Menu();
        mn.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPesanan;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelGreeting;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JTable tablePesanan;
    // End of variables declaration//GEN-END:variables
}
