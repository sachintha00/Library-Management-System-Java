/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Classes.MySqlMannager;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Sachintha
 */
public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    
//    public Preferences pref = Preferences.systemRoot().node("Rememberme");
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    int x,y;
    public frmLogin() {
        initComponents();
        
//        String user = null;
//        user = pref.get("Username", user);
//        txtUserName.setText(user);
//        
//        String pass = null;
//        pass = pref.get("Password", pass);
//        txtPassword.setText(pass);
    }
    
    public void rememberlogin(String username, String password)
    {
//        if(!(username == null || password == null))
//        {
//            String user = username;
//            pref.put("Username", username);
//            String pass = password;
//            pref.put("Password", password);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        Username = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnExit = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(26, 28, 36));
        jPanel1.setMinimumSize(new java.awt.Dimension(1274, 1000));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(41, 45, 54));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUserName.setBackground(new java.awt.Color(58, 111, 162));
        txtUserName.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(255, 255, 255));
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 343, 40));

        txtPassword.setBackground(new java.awt.Color(58, 111, 162));
        txtPassword.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setEchoChar('\u25cf');
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 343, 40));

        Username.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        Username.setForeground(new java.awt.Color(255, 255, 255));
        Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Username.setText("Username");
        jPanel2.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        Password.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        Password.setText("Password");
        jPanel2.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 111, 162));
        jLabel2.setText("User Login");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        btnLogin.setBackground(new java.awt.Color(38, 74, 108));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Login");
        btnLogin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 25, -1, -1));

        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 343, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 570, 700));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/login 1.png"))); // NOI18N
        jPanel1.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 65)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 111, 162));
        jLabel1.setText("Dream Reader");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 530, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(58, 111, 162));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 200, 565, -1));
        jLabel3.getAccessibleContext().setAccessibleName("Sachintha \nmadhwa"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(163, 163, 163));
        jLabel5.setText("Integer consectetur tellus ipsum, id laoreet neque");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 760, 370, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(163, 163, 163));
        jLabel6.setText("Integer consectetur tellus ipsum");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 780, 246, -1));

        btnExit.setBackground(new java.awt.Color(26, 28, 36));
        btnExit.setForeground(new java.awt.Color(58, 111, 162));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
        });
        btnExit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExit.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lblExit.setForeground(new java.awt.Color(58, 111, 162));
        lblExit.setText("X");
        btnExit.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 21, -1));

        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1241, 0, 90, 50));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1330, 882));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int xLoc = evt.getXOnScreen();
        int yLoc = evt.getYOnScreen();
        
        this.setLocation(xLoc - x, yLoc - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    
    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(new Color(49, 167, 253));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(new Color(58,111,162));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked

        try
        {
            String queary = "SELECT * From login WHERE Username='"+txtUserName.getText()+"' OR Password = '"+txtPassword.getText()+"'";
            rs = MySqlMannager.viewData(queary);
            
            if(rs.next())
            {
                frmMain frm = new frmMain();
                frm.setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Username Or Password incorrect!!!");
                txtUserName.setText(null);
                txtPassword.setText(null);
            }
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(58, 111, 162));
        lblExit.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        System.exit(0);
    }//GEN-LAST:event_btnExitMousePressed

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(26,28,36));
        lblExit.setForeground(new Color(58, 111, 162));
    }//GEN-LAST:event_btnExitMouseExited

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Password;
    private javax.swing.JLabel Username;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
