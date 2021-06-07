/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Classes.DateTimePicker;
import Classes.Check;
import Classes.MySqlMannager;
import static Classes.MySqlMannager.connect;
import com.sun.beans.editors.IntegerEditor;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sachintha
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    
    int x,y;
    
    public frmMain() {
        initComponents();  
        pnlHider();
        tableLoad();
        tableLoadMembers();
        IsBookTableLad();
        tableIssuedBook();
        bookCount();
//        tableDashView();
        lblMemberCount.setText(Integer.toString(tblMembers.getRowCount()));
        
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        this.setMaximizedBounds(env.getMaximumWindowBounds());
//        this.setExtendedState(frmMain.MAXIMIZED_BOTH);
    }
    
    
    private void bookCount()
    {
        int bCount = tblBooks.getRowCount();
        int tot = 0;
        
        for(int i =0; i<bCount; i++)
        {
            int value = Integer.valueOf(tblBooks.getValueAt(i, 4).toString());
            tot += value;
        }
        lblBookCount.setText(Integer.toString(tot));
    }
    
    private void tableDashView()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM member_book,author");
        tblDash.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    
    private void tableIssuedBook()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM member_book ");
        tblIssueBooks.setModel(DbUtils.resultSetToTableModel(rs));
    }
    private void tableIssueSearch()
    {   
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM issue_book WHERE Member_Id  LIKE '"+btnIssuBookSearch.getText()+"%' OR Member_name LIKE '"+btnIssuBookSearch.getText()+"%' OR Book_Name LIKE '"+btnIssuBookSearch.getText()+"%' ");
        tblIssueBooks.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    private void IsBookTableLad()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM book ");
        tblBookIssueSelect.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    private void tableLoadMembers()
    {
        try
        {
            ResultSet rs = MySqlMannager.viewData("SELECT * FROM member");
            if(rs != null)
            {
                tblMembers.setModel(DbUtils.resultSetToTableModel(rs));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Data not found");
            }
            
        }
        catch(NullPointerException e)
        {
            
        }
    }
    
    
    private void tableLoad()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM book,author WHERE book.Book_ID = author.Author_Id");
        tblBooks.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    private void tableSearch()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM book,author WHERE ( book.Book_Name LIKE '"+txtSearch.getText()+"%' OR book.Book_Type LIKE '"+txtSearch.getText()+"%' OR author.Author_Name LIKE '"+txtSearch.getText()+"%') AND book.Book_ID = author.Author_Id " );
        tblBooks.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    private void tableSearchMembers()
    {
        ResultSet rs = MySqlMannager.viewData("SELECT * FROM member WHERE Member_Id LIKE '"+txtMemberSearch.getText()+"%' OR First_Name LIKE '"+txtMemberSearch.getText()+"%' OR Middle_Name LIKE '"+txtMemberSearch.getText()+"%' OR Last_Name LIKE '"+txtMemberSearch.getText()+"%' OR NIC LIKE '"+txtMemberSearch.getText()+"%'");
        tblMembers.setModel(DbUtils.resultSetToTableModel(rs));
    }
    
    private void getData()
    {
        int r = tblBooks.getSelectedRow();
        String bname,btype,author,quantity;
        
        bname = tblBooks.getValueAt(r, 2).toString();
        btype = tblBooks.getValueAt(r, 1).toString();
        author = tblBooks.getValueAt(r, 3).toString();
        quantity = tblBooks.getValueAt(r, 4).toString();
        
        
        cmbBookType.setSelectedItem(btype);
        txtBookName.setText(bname);
        txtAuthor.setText(author);
        txtBookQuntity.setText(quantity);
    }
    
    private void getDataMembers()
    {
        int r2 = tblMembers.getSelectedRow();
        
        String fname,mname,lname,address,tpnumber,nic;
        
        fname = tblMembers.getValueAt(r2, 1).toString();
        mname = tblMembers.getValueAt(r2, 2).toString();
        lname = tblMembers.getValueAt(r2, 3).toString();
        address = tblMembers.getValueAt(r2, 4).toString();
        tpnumber = tblMembers.getValueAt(r2, 5).toString();
        nic = tblMembers.getValueAt(r2, 6).toString();
        
        txtFname.setText(fname);
        txtMname.setText(mname);
        txtLname.setText(lname);
        txtAddress.setText(address);
        txtTpnumber.setText(tpnumber);
        txtNic.setText(nic);
    }
    
    private void getIsseData()
    {
        int r2 = tblIssueBooks.getSelectedRow();
        
        String memberid,membername,bookType,bookname,issueDate,returnDate;
        
        memberid = tblIssueBooks.getValueAt(r2, 1).toString();
        membername = tblIssueBooks.getValueAt(r2, 2).toString();
        bookType = tblIssueBooks.getValueAt(r2, 3).toString();
        bookname = tblIssueBooks.getValueAt(r2, 4).toString();
        issueDate = tblIssueBooks.getValueAt(r2, 5).toString();
        returnDate = tblIssueBooks.getValueAt(r2, 6).toString();
        
        txtMemberId.setText(memberid);
        txtMemberName.setText(membername);
        cmbTypeofBook.setSelectedItem(bookType);
        txtIssueBookName.setText(bookname);
        txtIssueDate.setText(issueDate);
        txtReturnDate.setText(returnDate);
    }
    
    private void update()
    {
        MySqlMannager.insertUpdateDelete("UPDATE book SET Book_Type = 'non', WHERE Book_Id = '2'  ");
        tableLoad();
        IsBookTableLad();
        
    }
    
    private void pnlHider()
    {
        pnlLeft.setVisible(false);
        pnlLeft.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        btnDash = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnBooks = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMembers = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnIssueBooks = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnReturnBooks = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMenubtn = new javax.swing.JLabel();
        btnExit = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        tbpnlFrmChng = new javax.swing.JTabbedPane();
        pnlDash = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblMemberCount = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblBookCount = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDash = new javax.swing.JTable();
        pnlBooks = new javax.swing.JPanel();
        pnlAddData = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbBookType = new javax.swing.JComboBox<>();
        txtBookQuntity = new javax.swing.JTextField();
        txtBookName = new javax.swing.JTextField();
        btnDelete = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnAdd = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JPanel();
        lblUpdate1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pnlMembers = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNic = new javax.swing.JTextField();
        txtFname = new javax.swing.JTextField();
        txtMname = new javax.swing.JTextField();
        txtLname = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTpnumber = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnMAdd = new javax.swing.JPanel();
        lblAdd1 = new javax.swing.JLabel();
        btnMupdate = new javax.swing.JPanel();
        lblUpdate2 = new javax.swing.JLabel();
        btnMdelete = new javax.swing.JPanel();
        lblDelete1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMembers = new javax.swing.JTable();
        txtMemberSearch = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        pnlIssueBooks = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblIssueBooks = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        btnIssuBookSearch = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtMemberId = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMemberName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtIssueDate = new javax.swing.JTextField();
        txtReturnDate = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cmbTypeofBook = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBookIssueSelect = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        txtIssueBookName = new javax.swing.JTextField();
        btnUpdateIssue = new javax.swing.JPanel();
        lblUpdate3 = new javax.swing.JLabel();
        btnAddIssu = new javax.swing.JPanel();
        lblAdd2 = new javax.swing.JLabel();
        btnDeleteIssue = new javax.swing.JPanel();
        lblDelete2 = new javax.swing.JLabel();
        btnReturnDate = new javax.swing.JButton();
        btnIssueDate = new javax.swing.JButton();
        pnlReturnBooks = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlMain.setBackground(new java.awt.Color(41, 45, 54));
        pnlMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMainMouseDragged(evt);
            }
        });
        pnlMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMainMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMainMousePressed(evt);
            }
        });
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLeft.setBackground(new java.awt.Color(26, 28, 36));
        pnlLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDash.setBackground(new java.awt.Color(26, 28, 36));
        btnDash.setForeground(new java.awt.Color(255, 255, 255));
        btnDash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDashMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDashMouseExited(evt);
            }
        });
        btnDash.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DASHBOARD");
        btnDash.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 130, 30));

        pnlLeft.add(btnDash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 400, 70));

        btnBooks.setBackground(new java.awt.Color(26, 28, 36));
        btnBooks.setForeground(new java.awt.Color(255, 255, 255));
        btnBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBooksMouseExited(evt);
            }
        });
        btnBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BOOKS");
        btnBooks.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 70, 30));

        pnlLeft.add(btnBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 400, 70));

        btnMembers.setBackground(new java.awt.Color(26, 28, 36));
        btnMembers.setForeground(new java.awt.Color(255, 255, 255));
        btnMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMembersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMembersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMembersMouseExited(evt);
            }
        });
        btnMembers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MEMBERS");
        btnMembers.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 90, 30));

        pnlLeft.add(btnMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 400, 70));

        btnIssueBooks.setBackground(new java.awt.Color(26, 28, 36));
        btnIssueBooks.setForeground(new java.awt.Color(255, 255, 255));
        btnIssueBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIssueBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIssueBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIssueBooksMouseExited(evt);
            }
        });
        btnIssueBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ISSUE BOOKS");
        btnIssueBooks.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 150, 30));

        pnlLeft.add(btnIssueBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 400, 70));

        btnReturnBooks.setBackground(new java.awt.Color(26, 28, 36));
        btnReturnBooks.setForeground(new java.awt.Color(255, 255, 255));
        btnReturnBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReturnBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReturnBooksMouseExited(evt);
            }
        });
        btnReturnBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBack.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBack.setText("RETURN BOOKS");
        btnReturnBooks.add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 170, 30));

        pnlLeft.add(btnReturnBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 800, 400, 70));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/maleAvatar.png"))); // NOI18N
        pnlLeft.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 210, 210));

        pnlMain.add(pnlLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 1000));

        lblMenubtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/menu.png"))); // NOI18N
        lblMenubtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenubtnMouseClicked(evt);
            }
        });
        pnlMain.add(lblMenubtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 40));

        btnExit.setBackground(new java.awt.Color(41, 45, 54));
        btnExit.setForeground(new java.awt.Color(58, 111, 162));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
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

        lblExit.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(58, 111, 162));
        lblExit.setText("X");
        lblExit.setAlignmentY(0.8F);
        btnExit.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        pnlMain.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1760, 0, 70, 40));

        tbpnlFrmChng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpnlFrmChngMouseClicked(evt);
            }
        });

        pnlDash.setBackground(new java.awt.Color(41, 45, 54));
        pnlDash.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlDashMouseDragged(evt);
            }
        });
        pnlDash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlDashMousePressed(evt);
            }
        });
        pnlDash.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/dashBoard.png"))); // NOI18N
        pnlDash.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 300, 830, 700));

        jPanel6.setBackground(new java.awt.Color(26, 28, 36));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setText("Join with us to improve your knowledge");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 470, 40));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(38, 74, 108));
        jLabel31.setText("WELCOME TO DREAM READER...");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 470, 40));

        pnlDash.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 840, 240));

        jPanel7.setBackground(new java.awt.Color(38, 74, 108));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        lblMemberCount.setFont(new java.awt.Font("Verdana", 1, 80)); // NOI18N
        lblMemberCount.setForeground(new java.awt.Color(255, 255, 255));
        lblMemberCount.setText("0+");
        jPanel7.add(lblMemberCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 160, 180));

        jLabel34.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("ALL MEMBERS");
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        pnlDash.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 60, 240, 250));

        jPanel11.setBackground(new java.awt.Color(25, 49, 72));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 290, 230));

        lblBookCount.setFont(new java.awt.Font("Verdana", 1, 80)); // NOI18N
        lblBookCount.setForeground(new java.awt.Color(255, 255, 255));
        lblBookCount.setText("0+");
        jPanel11.add(lblBookCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 160, 180));

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("ALL BOOKS");
        jPanel11.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        pnlDash.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 60, 240, 250));

        jPanel15.setBackground(new java.awt.Color(26, 28, 36));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ALL THE DETAILS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 24), new java.awt.Color(58, 111, 162))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDash.setAutoCreateRowSorter(true);
        tblDash.setBackground(new java.awt.Color(41, 45, 54));
        tblDash.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        tblDash.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        tblDash.setForeground(new java.awt.Color(49, 167, 253));
        tblDash.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book Type", "Book Name", "Author", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDash.setGridColor(new java.awt.Color(26, 28, 36));
        tblDash.setRowHeight(30);
        tblDash.setRowMargin(2);
        tblDash.setSelectionBackground(new java.awt.Color(38, 74, 108));
        tblDash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDashMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDash);
        if (tblDash.getColumnModel().getColumnCount() > 0) {
            tblDash.getColumnModel().getColumn(0).setHeaderValue("Book Type");
            tblDash.getColumnModel().getColumn(1).setHeaderValue("Book Name");
            tblDash.getColumnModel().getColumn(2).setHeaderValue("Author");
            tblDash.getColumnModel().getColumn(3).setHeaderValue("Quantity");
            tblDash.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jPanel15.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 810, 440));

        pnlDash.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 830, 510));

        tbpnlFrmChng.addTab("tab1", pnlDash);

        pnlBooks.setBackground(new java.awt.Color(41, 45, 54));
        pnlBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddData.setBackground(new java.awt.Color(26, 28, 36));
        pnlAddData.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ADD NEW BOOK", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 24), new java.awt.Color(58, 111, 162))); // NOI18N
        pnlAddData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(38, 74, 108));
        jLabel7.setText("Book Quantity:");
        pnlAddData.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 180, 40));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(38, 74, 108));
        jLabel8.setText("Book Name:");
        pnlAddData.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 200, 40));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(38, 74, 108));
        jLabel9.setText("Author:");
        pnlAddData.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 120, 40));

        cmbBookType.setBackground(new java.awt.Color(58, 111, 162));
        cmbBookType.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        cmbBookType.setForeground(new java.awt.Color(255, 255, 255));
        cmbBookType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adventure", "Love Story", "Short Story", "Historical", "Religious ", "Mysteories" }));
        cmbBookType.setBorder(null);
        cmbBookType.setFocusable(false);
        cmbBookType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBookTypeActionPerformed(evt);
            }
        });
        pnlAddData.add(cmbBookType, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 250, 40));

        txtBookQuntity.setBackground(new java.awt.Color(58, 111, 162));
        txtBookQuntity.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtBookQuntity.setForeground(new java.awt.Color(255, 255, 255));
        txtBookQuntity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        pnlAddData.add(txtBookQuntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 230, 40));

        txtBookName.setBackground(new java.awt.Color(58, 111, 162));
        txtBookName.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtBookName.setForeground(new java.awt.Color(255, 255, 255));
        txtBookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        pnlAddData.add(txtBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 510, 40));

        btnDelete.setBackground(new java.awt.Color(26, 28, 36));
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDelete.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblDelete.setForeground(new java.awt.Color(255, 255, 255));
        lblDelete.setText("DELETE");
        btnDelete.add(lblDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        pnlAddData.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 670, 150, 50));

        btnAdd.setBackground(new java.awt.Color(26, 28, 36));
        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        btnAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAdd.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd.setText("ADD");
        btnAdd.add(lblAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 13, 42, 24));

        pnlAddData.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 150, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/book.png"))); // NOI18N
        pnlAddData.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 280, 220));

        txtAuthor.setBackground(new java.awt.Color(58, 111, 162));
        txtAuthor.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtAuthor.setForeground(new java.awt.Color(255, 255, 255));
        txtAuthor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        pnlAddData.add(txtAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 510, 40));

        btnUpdate.setBackground(new java.awt.Color(26, 28, 36));
        btnUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateMouseExited(evt);
            }
        });
        btnUpdate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUpdate1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUpdate1.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdate1.setText("UPDATE");
        btnUpdate.add(lblUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        pnlAddData.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 670, 150, 50));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(38, 74, 108));
        jLabel12.setText("Book Type:");
        pnlAddData.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 120, 40));

        pnlBooks.add(pnlAddData, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 600, 750));

        jPanel2.setBackground(new java.awt.Color(26, 28, 36));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBooks.setAutoCreateRowSorter(true);
        tblBooks.setBackground(new java.awt.Color(41, 45, 54));
        tblBooks.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        tblBooks.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        tblBooks.setForeground(new java.awt.Color(49, 167, 253));
        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book Type", "Book Name", "Author", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBooks.setGridColor(new java.awt.Color(26, 28, 36));
        tblBooks.setRowHeight(30);
        tblBooks.setRowMargin(2);
        tblBooks.setSelectionBackground(new java.awt.Color(38, 74, 108));
        tblBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBooks);
        if (tblBooks.getColumnModel().getColumnCount() > 0) {
            tblBooks.getColumnModel().getColumn(0).setHeaderValue("Book Type");
            tblBooks.getColumnModel().getColumn(1).setHeaderValue("Book Name");
            tblBooks.getColumnModel().getColumn(2).setHeaderValue("Author");
            tblBooks.getColumnModel().getColumn(3).setHeaderValue("Quantity");
            tblBooks.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 170, 1040, 560));

        txtSearch.setBackground(new java.awt.Color(58, 111, 162));
        txtSearch.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 410, 40));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(38, 74, 108));
        jLabel10.setText("Search Books");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 170, 40));

        pnlBooks.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 1090, 750));

        tbpnlFrmChng.addTab("tab2", pnlBooks);

        pnlMembers.setBackground(new java.awt.Color(41, 45, 54));
        pnlMembers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(26, 28, 36));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEMBERS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 24), new java.awt.Color(38, 74, 108))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNic.setBackground(new java.awt.Color(58, 111, 162));
        txtNic.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtNic.setForeground(new java.awt.Color(255, 255, 255));
        txtNic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 360, 40));

        txtFname.setBackground(new java.awt.Color(58, 111, 162));
        txtFname.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtFname.setForeground(new java.awt.Color(255, 255, 255));
        txtFname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtFname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 290, 40));

        txtMname.setBackground(new java.awt.Color(58, 111, 162));
        txtMname.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtMname.setForeground(new java.awt.Color(255, 255, 255));
        txtMname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtMname, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 290, 40));

        txtLname.setBackground(new java.awt.Color(58, 111, 162));
        txtLname.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtLname.setForeground(new java.awt.Color(255, 255, 255));
        txtLname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtLname, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 290, 40));

        txtAddress.setBackground(new java.awt.Color(58, 111, 162));
        txtAddress.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(255, 255, 255));
        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 510, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/information add.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, 380, 300));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(58, 111, 162));
        jLabel14.setText("NIC Number");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(58, 111, 162));
        jLabel15.setText("First Name");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(58, 111, 162));
        jLabel16.setText("Middle Name");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(58, 111, 162));
        jLabel17.setText("Last Name");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(58, 111, 162));
        jLabel18.setText("Address");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        txtTpnumber.setBackground(new java.awt.Color(58, 111, 162));
        txtTpnumber.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtTpnumber.setForeground(new java.awt.Color(255, 255, 255));
        txtTpnumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(txtTpnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 360, 40));

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(58, 111, 162));
        jLabel19.setText("Telephone Number");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, -1, -1));

        btnMAdd.setBackground(new java.awt.Color(26, 28, 36));
        btnMAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnMAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMAddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMAddMouseExited(evt);
            }
        });
        btnMAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAdd1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblAdd1.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd1.setText("ADD");
        btnMAdd.add(lblAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 13, 42, 24));

        jPanel1.add(btnMAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 150, 50));

        btnMupdate.setBackground(new java.awt.Color(26, 28, 36));
        btnMupdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnMupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMupdateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMupdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMupdateMouseExited(evt);
            }
        });
        btnMupdate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUpdate2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUpdate2.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdate2.setText("UPDATE");
        btnMupdate.add(lblUpdate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        jPanel1.add(btnMupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, 150, 50));

        btnMdelete.setBackground(new java.awt.Color(26, 28, 36));
        btnMdelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnMdelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMdeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMdeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMdeleteMouseExited(evt);
            }
        });
        btnMdelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDelete1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblDelete1.setForeground(new java.awt.Color(255, 255, 255));
        lblDelete1.setText("DELETE");
        btnMdelete.add(lblDelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        jPanel1.add(btnMdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 280, 150, 50));

        pnlMembers.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 1510, 360));

        jPanel3.setBackground(new java.awt.Color(26, 28, 36));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMembers.setAutoCreateRowSorter(true);
        tblMembers.setBackground(new java.awt.Color(41, 45, 54));
        tblMembers.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        tblMembers.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        tblMembers.setForeground(new java.awt.Color(49, 167, 253));
        tblMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book Type", "Book Name", "Author", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMembers.setGridColor(new java.awt.Color(26, 28, 36));
        tblMembers.setRowHeight(30);
        tblMembers.setRowMargin(2);
        tblMembers.setSelectionBackground(new java.awt.Color(38, 74, 108));
        tblMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMembersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMembers);
        if (tblMembers.getColumnModel().getColumnCount() > 0) {
            tblMembers.getColumnModel().getColumn(0).setHeaderValue("Book Type");
            tblMembers.getColumnModel().getColumn(1).setHeaderValue("Book Name");
            tblMembers.getColumnModel().getColumn(2).setHeaderValue("Author");
            tblMembers.getColumnModel().getColumn(3).setHeaderValue("Quantity");
            tblMembers.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 1040, 430));

        txtMemberSearch.setBackground(new java.awt.Color(58, 111, 162));
        txtMemberSearch.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtMemberSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtMemberSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtMemberSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMemberSearchKeyReleased(evt);
            }
        });
        jPanel3.add(txtMemberSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 360, 40));

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(58, 111, 162));
        jLabel20.setText("Search");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        pnlMembers.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 1510, 490));

        tbpnlFrmChng.addTab("tab3", pnlMembers);

        pnlIssueBooks.setBackground(new java.awt.Color(41, 45, 54));
        pnlIssueBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(26, 28, 36));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblIssueBooks.setAutoCreateRowSorter(true);
        tblIssueBooks.setBackground(new java.awt.Color(41, 45, 54));
        tblIssueBooks.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        tblIssueBooks.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        tblIssueBooks.setForeground(new java.awt.Color(49, 167, 253));
        tblIssueBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book Type", "Book Name", "Author", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblIssueBooks.setGridColor(new java.awt.Color(26, 28, 36));
        tblIssueBooks.setRowHeight(30);
        tblIssueBooks.setRowMargin(2);
        tblIssueBooks.setSelectionBackground(new java.awt.Color(38, 74, 108));
        tblIssueBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIssueBooksMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblIssueBooks);
        if (tblIssueBooks.getColumnModel().getColumnCount() > 0) {
            tblIssueBooks.getColumnModel().getColumn(0).setHeaderValue("Book Type");
            tblIssueBooks.getColumnModel().getColumn(1).setHeaderValue("Book Name");
            tblIssueBooks.getColumnModel().getColumn(2).setHeaderValue("Author");
            tblIssueBooks.getColumnModel().getColumn(3).setHeaderValue("Quantity");
            tblIssueBooks.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 770, 470));

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(38, 74, 108));
        jLabel27.setText("Search Books");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 170, 40));

        btnIssuBookSearch.setBackground(new java.awt.Color(58, 111, 162));
        btnIssuBookSearch.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        btnIssuBookSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnIssuBookSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnIssuBookSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnIssuBookSearchKeyReleased(evt);
            }
        });
        jPanel4.add(btnIssuBookSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 350, 40));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/issueBook.png"))); // NOI18N
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 380, 290));

        pnlIssueBooks.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 830, 810));

        jPanel5.setBackground(new java.awt.Color(26, 28, 36));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BOOK ISSUE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 24), new java.awt.Color(38, 74, 108))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMemberId.setBackground(new java.awt.Color(58, 111, 162));
        txtMemberId.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtMemberId.setForeground(new java.awt.Color(255, 255, 255));
        txtMemberId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtMemberId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMemberIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMemberIdKeyTyped(evt);
            }
        });
        jPanel5.add(txtMemberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 290, 40));

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(58, 111, 162));
        jLabel21.setText("Member Id");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(58, 111, 162));
        jLabel22.setText("Member Name");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        txtMemberName.setBackground(new java.awt.Color(58, 111, 162));
        txtMemberName.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtMemberName.setForeground(new java.awt.Color(255, 255, 255));
        txtMemberName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel5.add(txtMemberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 300, 40));

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(58, 111, 162));
        jLabel23.setText("Book Type");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(58, 111, 162));
        jLabel24.setText("Issue Date");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        txtIssueDate.setBackground(new java.awt.Color(58, 111, 162));
        txtIssueDate.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtIssueDate.setForeground(new java.awt.Color(255, 255, 255));
        txtIssueDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtIssueDate.setEnabled(false);
        jPanel5.add(txtIssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 250, 40));

        txtReturnDate.setBackground(new java.awt.Color(58, 111, 162));
        txtReturnDate.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtReturnDate.setForeground(new java.awt.Color(255, 255, 255));
        txtReturnDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtReturnDate.setEnabled(false);
        jPanel5.add(txtReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 250, 40));

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(58, 111, 162));
        jLabel25.setText("Return Date");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        cmbTypeofBook.setBackground(new java.awt.Color(58, 111, 162));
        cmbTypeofBook.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        cmbTypeofBook.setForeground(new java.awt.Color(255, 255, 255));
        cmbTypeofBook.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adventure", "Love Story", "Short Story", "Historical", "Religious ", "Mysteories" }));
        cmbTypeofBook.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        cmbTypeofBook.setFocusable(false);
        cmbTypeofBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeofBookActionPerformed(evt);
            }
        });
        jPanel5.add(cmbTypeofBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 290, 40));

        tblBookIssueSelect.setAutoCreateRowSorter(true);
        tblBookIssueSelect.setBackground(new java.awt.Color(41, 45, 54));
        tblBookIssueSelect.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        tblBookIssueSelect.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        tblBookIssueSelect.setForeground(new java.awt.Color(49, 167, 253));
        tblBookIssueSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book Type", "Book Name", "Author", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBookIssueSelect.setGridColor(new java.awt.Color(26, 28, 36));
        tblBookIssueSelect.setRowHeight(30);
        tblBookIssueSelect.setRowMargin(2);
        tblBookIssueSelect.setSelectionBackground(new java.awt.Color(38, 74, 108));
        tblBookIssueSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookIssueSelectMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBookIssueSelect);
        if (tblBookIssueSelect.getColumnModel().getColumnCount() > 0) {
            tblBookIssueSelect.getColumnModel().getColumn(0).setHeaderValue("Book Type");
            tblBookIssueSelect.getColumnModel().getColumn(1).setHeaderValue("Book Name");
            tblBookIssueSelect.getColumnModel().getColumn(2).setHeaderValue("Author");
            tblBookIssueSelect.getColumnModel().getColumn(3).setHeaderValue("Quantity");
            tblBookIssueSelect.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 730, 290));

        jLabel26.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(58, 111, 162));
        jLabel26.setText("Book Name");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        txtIssueBookName.setBackground(new java.awt.Color(58, 111, 162));
        txtIssueBookName.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtIssueBookName.setForeground(new java.awt.Color(255, 255, 255));
        txtIssueBookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtIssueBookName.setEnabled(false);
        jPanel5.add(txtIssueBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 300, 40));

        btnUpdateIssue.setBackground(new java.awt.Color(26, 28, 36));
        btnUpdateIssue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnUpdateIssue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateIssueMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateIssueMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateIssueMouseExited(evt);
            }
        });
        btnUpdateIssue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUpdate3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUpdate3.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdate3.setText("UPDATE");
        btnUpdateIssue.add(lblUpdate3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        jPanel5.add(btnUpdateIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 150, 50));

        btnAddIssu.setBackground(new java.awt.Color(26, 28, 36));
        btnAddIssu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnAddIssu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddIssuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddIssuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddIssuMouseExited(evt);
            }
        });
        btnAddIssu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAdd2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblAdd2.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd2.setText("ADD");
        btnAddIssu.add(lblAdd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 13, 42, 24));

        jPanel5.add(btnAddIssu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 150, 50));

        btnDeleteIssue.setBackground(new java.awt.Color(26, 28, 36));
        btnDeleteIssue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 74, 108), 2));
        btnDeleteIssue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteIssueMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteIssueMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteIssueMouseExited(evt);
            }
        });
        btnDeleteIssue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDelete2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblDelete2.setForeground(new java.awt.Color(255, 255, 255));
        lblDelete2.setText("DELETE");
        btnDeleteIssue.add(lblDelete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        jPanel5.add(btnDeleteIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 150, 50));

        btnReturnDate.setBackground(new java.awt.Color(26, 28, 36));
        btnReturnDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/DateTimePicker.png"))); // NOI18N
        btnReturnDate.setBorder(null);
        btnReturnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnDateActionPerformed(evt);
            }
        });
        jPanel5.add(btnReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 40, 40));

        btnIssueDate.setBackground(new java.awt.Color(26, 28, 36));
        btnIssueDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/DateTimePicker.png"))); // NOI18N
        btnIssueDate.setBorder(null);
        btnIssueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIssueDateActionPerformed(evt);
            }
        });
        jPanel5.add(btnIssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 40, 40));

        pnlIssueBooks.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 790, 810));

        tbpnlFrmChng.addTab("tab4", pnlIssueBooks);

        pnlReturnBooks.setBackground(new java.awt.Color(41, 45, 54));
        pnlReturnBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tbpnlFrmChng.addTab("tab5", pnlReturnBooks);

        pnlMain.add(tbpnlFrmChng, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1830, 1010));

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1830, 981));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseClicked
        pnlHider();
    }//GEN-LAST:event_pnlMainMouseClicked

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xLoc = evt.getXOnScreen();
        int yLoc = evt.getYOnScreen();

        this.setLocation(xLoc - x, yLoc - y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        System.exit(0);
    }//GEN-LAST:event_btnExitMousePressed

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(41,45,54));
        lblExit.setForeground(new Color(58, 111, 162));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(58, 111, 162));
        lblExit.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void lblMenubtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenubtnMouseClicked
        active();
    }//GEN-LAST:event_lblMenubtnMouseClicked

    private void btnIssueBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIssueBooksMouseExited
        btnIssueBooks.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnIssueBooksMouseExited

    private void btnIssueBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIssueBooksMouseEntered
        btnIssueBooks.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnIssueBooksMouseEntered

    private void btnMembersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMembersMouseExited
        btnMembers.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnMembersMouseExited

    private void btnMembersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMembersMouseEntered
        btnMembers.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnMembersMouseEntered

    private void btnBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBooksMouseExited
        btnBooks.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnBooksMouseExited

    private void btnBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBooksMouseEntered
        btnBooks.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnBooksMouseEntered

    private void btnDashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashMouseEntered
        btnDash.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnDashMouseEntered

    private void btnDashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashMouseExited
        btnDash.setBackground(new Color(26, 28, 36));
    }//GEN-LAST:event_btnDashMouseExited

    private void btnDashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashMouseClicked
        pnlHider();
        tbpnlFrmChng.setSelectedIndex(0);
    }//GEN-LAST:event_btnDashMouseClicked

    private void tbpnlFrmChngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpnlFrmChngMouseClicked
        pnlHider();
    }//GEN-LAST:event_tbpnlFrmChngMouseClicked

    private void btnBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBooksMouseClicked
        pnlHider();
        tbpnlFrmChng.setSelectedIndex(1);
    }//GEN-LAST:event_btnBooksMouseClicked

    private void btnMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMembersMouseClicked
        pnlHider();
        tbpnlFrmChng.setSelectedIndex(2);
    }//GEN-LAST:event_btnMembersMouseClicked

    private void btnIssueBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIssueBooksMouseClicked
        pnlHider();
        tbpnlFrmChng.setSelectedIndex(3);
    }//GEN-LAST:event_btnIssueBooksMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        if(txtBookName.getText().equals("") || txtAuthor.getText().equals("") || txtBookQuntity.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Insert All value");
        }
        else if(Check.IsInteger(txtBookName.getText()) || Check.IsInteger(txtAuthor.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the String value");
        }
        else if(Check.IsString(txtBookQuntity.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the Integer value");
        }
        else{
            String bookType,bookName,author;
            int bookQuantity;

            bookType = cmbBookType.getSelectedItem().toString();
            bookName = txtBookName.getText();
            author = txtAuthor.getText();
            bookQuantity = Integer.parseInt(txtBookQuntity.getText());
            
            int count = MySqlMannager.insertUpdateDelete("INSERT INTO book (Book_Name,Book_Type,Book_Quantity) VALUES ('"+bookName+"','"+bookType+"','"+bookQuantity+"')");
            int count1 = MySqlMannager.insertUpdateDelete("INSERT INTO author (Author_Name) VALUES ('"+author+"')");
            if(count != 0 && count1 != 0)
            {
                tableLoad();
                IsBookTableLad();
                JOptionPane.showMessageDialog(null, count+" added data");
                txtBookName.setText(null);
                txtAuthor.setText(null);
                txtBookQuntity.setText(null);
                cmbBookType.setSelectedItem(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "data not added");
            }
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        tableSearch();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        btnAdd.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        btnAdd.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        int r = tblBooks.getSelectedRow();
        int id = Integer.parseInt(tblBooks.getValueAt(r, 0).toString());
        
        int count = MySqlMannager.insertUpdateDelete("DELETE FROM book WHERE Book_Id = '"+id+"'");
        int count1 = MySqlMannager.insertUpdateDelete("DELETE FROM author WHERE Author_Id  = '"+id+"'");
        if(count != 0 && count1 != 0)
            {
                tableLoad();
                IsBookTableLad();
                JOptionPane.showMessageDialog(null, count+" Delete data");
                txtBookName.setText(null);
                txtAuthor.setText(null);
                txtBookQuntity.setText(null);
                cmbBookType.setSelectedItem(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "data not delete");
            }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        btnDelete.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        int r2 = tblBooks.getSelectedRow();
        int id = Integer.parseInt(tblBooks.getValueAt(r2, 0).toString());
        
        String bookType = cmbBookType.getSelectedItem().toString();
        String bookName = txtBookName.getText();
        String author = txtAuthor.getText();
        int bookQuantity = Integer.parseInt(txtBookQuntity.getText());
        int count = MySqlMannager.insertUpdateDelete("UPDATE book SET Book_Name = '"+bookName+"' Book_Type='"+bookType+"' Book_Quantity='"+bookQuantity+"' WHERE Book_ID='"+id+"'");
//        int count1 = MySqlMannager.insertUpdateDelete("UPDATE book SET author ");
        if(count!=0)
        {
            JOptionPane.showMessageDialog(null, "Update successful");
            txtBookName.setText(null);
            txtAuthor.setText(null);
            txtBookQuntity.setText(null);
            cmbBookType.setSelectedItem(null);
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        btnUpdate.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        btnUpdate.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnUpdateMouseExited

    private void tblBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBooksMouseClicked
        getData();
    }//GEN-LAST:event_tblBooksMouseClicked

    private void btnMAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMAddMouseClicked
        if(txtFname.getText().equals("") || txtMname.getText().equals("") || txtLname.getText().equals("") || txtAddress.getText().equals("") || txtTpnumber.getText().equals("") || txtNic.getText().equals(""))
        {
            
        }
        else if(Check.IsInteger(txtFname.getText()) || Check.IsInteger(txtMname.getText()) || Check.IsInteger(txtLname.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the String value");
        }
        else if(Check.IsString(txtTpnumber.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the Integer Value");
        }
        else
        {
            String fname,mname,lname,address,nic;
            int tp;

            fname = txtFname.getText();
            mname = txtMname.getText();
            lname = txtLname.getText();
            address = txtAddress.getText();
            nic = txtNic.getText();
            tp = Integer.parseInt(txtTpnumber.getText());

            int count = MySqlMannager.insertUpdateDelete("INSERT INTO member (First_Name,Middle_Name,Last_Name,Permanent_Address,Tele_Number,NIC) VALUES ('"+fname+"','"+mname+"','"+lname+"','"+address+"','"+tp+"','"+nic+"')");
            if(count != 0)
            {
                tableLoadMembers();
                JOptionPane.showMessageDialog(null, count+" added data");
                txtFname.setText(null);
                txtMname.setText(null);
                txtLname.setText(null);
                txtAddress.setText(null);
                txtNic.setText(null);
                txtTpnumber.setText(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "data not added");
            }
        }
    }//GEN-LAST:event_btnMAddMouseClicked

    private void btnMAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMAddMouseEntered
        btnMAdd.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnMAddMouseEntered

    private void btnMAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMAddMouseExited
        btnMAdd.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnMAddMouseExited

    private void btnMupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMupdateMouseClicked
        int r2 = tblMembers.getSelectedRow();
        int id = Integer.parseInt(tblMembers.getValueAt(r2, 0).toString());
        
        String fname = txtFname.getText();
        String mname = txtMname.getText();
        String lname = txtLname.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        int tp = Integer.parseInt(txtTpnumber.getText());
        int count = MySqlMannager.insertUpdateDelete("UPDATE member SET First_Name = '"+fname+"' Middle_Name='"+mname+"' Last_Name='"+lname+"' NIC='"+nic+"' Permanent_Address='"+address+"' WHERE Member_Id="+id+"");
        if(count!=0)
        {
            JOptionPane.showMessageDialog(null, "Update successful");
            txtFname.setText(null);
            txtMname.setText(null);
            txtLname.setText(null);
            txtAddress.setText(null);
            txtNic.setText(null);
            txtTpnumber.setText(null);
        }
    }//GEN-LAST:event_btnMupdateMouseClicked

    private void btnMupdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMupdateMouseEntered
        btnMupdate.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnMupdateMouseEntered

    private void btnMupdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMupdateMouseExited
        btnMupdate.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnMupdateMouseExited

    private void btnMdeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMdeleteMouseClicked
        int r2 = tblMembers.getSelectedRow();
        int id = Integer.parseInt(tblMembers.getValueAt(r2, 0).toString());
        int count = MySqlMannager.insertUpdateDelete("DELETE FROM member WHERE Member_Id = '"+id+"'");
        if(count != 0)
            {
                tableLoadMembers();
                JOptionPane.showMessageDialog(null, count+" delete data");
                txtFname.setText(null);
                txtMname.setText(null);
                txtLname.setText(null);
                txtAddress.setText(null);
                txtNic.setText(null);
                txtTpnumber.setText(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "data not deleted");
            }
    }//GEN-LAST:event_btnMdeleteMouseClicked

    private void btnMdeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMdeleteMouseEntered
        btnMdelete.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnMdeleteMouseEntered

    private void btnMdeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMdeleteMouseExited
        btnMdelete.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnMdeleteMouseExited

    private void tblMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMembersMouseClicked
        getDataMembers();
    }//GEN-LAST:event_tblMembersMouseClicked

    private void tblIssueBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIssueBooksMouseClicked
        getIsseData();
    }//GEN-LAST:event_tblIssueBooksMouseClicked

    private void txtMemberSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMemberSearchKeyReleased
        tableSearchMembers();
    }//GEN-LAST:event_txtMemberSearchKeyReleased

    private void tblBookIssueSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookIssueSelectMouseClicked
        int row = tblBookIssueSelect.getSelectedRow();
        txtIssueBookName.setText(tblBookIssueSelect.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tblBookIssueSelectMouseClicked

    private void btnUpdateIssueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateIssueMouseClicked
        int r2 = tblBookIssueSelect.getSelectedRow();
        int bookId = Integer.parseInt(tblBookIssueSelect.getValueAt(r2, 0).toString());

        int memberID = Integer.parseInt(txtMemberId.getText().toString());
        String memberName = txtMemberName.getText();
        String bookType = cmbTypeofBook.getSelectedItem().toString();
        String bookName = txtIssueBookName.getText();
        String issueDate = txtIssueDate.getText();
        String returnDate = txtReturnDate.getText();
        
        int count = MySqlMannager.insertUpdateDelete("UPDATE member_book SET Member_Id = "+memberID+" Member_name='"+memberName+"' Book_Type='"+bookType+"' Book_Name='"+bookName+"' Issue_Date='"+issueDate+"' Return_Date='"+returnDate+"' WHERE Member_Id="+bookId+"");
        if(count!=0)
        {
            JOptionPane.showMessageDialog(null, "Update successful");
            txtMemberId.setText(null);
            txtMemberName.setText(null);
            txtIssueBookName.setText(null);
            txtIssueDate.setText(null);
            txtReturnDate.setText(null);
        }
    }//GEN-LAST:event_btnUpdateIssueMouseClicked

    private void btnUpdateIssueMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateIssueMouseEntered
        btnUpdateIssue.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnUpdateIssueMouseEntered

    private void btnUpdateIssueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateIssueMouseExited
        btnUpdateIssue.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnUpdateIssueMouseExited

    private void btnAddIssuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddIssuMouseClicked
        if(txtMemberId.getText().equals("") || txtMemberName.getText().equals("") || txtIssueBookName.getText().equals("") || txtIssueDate.getText().equals("") || txtReturnDate.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please insert the all value");
        }
        else if(Check.IsInteger(txtMemberName.getText()) || Check.IsInteger(txtMemberName.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the String");
        }
        else if(Check.IsString(txtMemberId.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please use the Integer");
        }
        else
        {
            String memberName,bookType,bookName,issueDate,returnDate;
            int memberID, bookId;
            
            int r2 = tblBookIssueSelect.getSelectedRow();
            bookId = Integer.parseInt(tblBookIssueSelect.getValueAt(r2, 0).toString());

            memberID = Integer.parseInt(txtMemberId.getText().toString());
            memberName = txtMemberName.getText();
            bookType = cmbTypeofBook.getSelectedItem().toString();
            bookName = txtIssueBookName.getText();
            issueDate = txtIssueDate.getText();
            returnDate = txtReturnDate.getText();

            int count = MySqlMannager.insertUpdateDelete("INSERT INTO member_book (Book_ID,Member_Id ,Member_name,Book_Type,Book_Name,Issue_Date,Return_Date) VALUES ("+bookId+","+memberID+",'"+memberName+"','"+bookType+"','"+bookName+"','"+issueDate+"','"+returnDate+"')");
            
            if(count != 0)
            {   
                int row = tblBookIssueSelect.getSelectedRow();
                int quantity = Integer.parseInt(tblBooks.getValueAt(r2, 3).toString());
                MySqlMannager.insertUpdateDelete("UPDATE book SET Book_Quantity="+(--quantity)+"");
                IsBookTableLad();
                tableLoad();
                tableIssuedBook();
                JOptionPane.showMessageDialog(null, count+" added data");
                txtMemberId.setText(null);
                txtMemberName.setText(null);
                txtIssueBookName.setText(null);
                txtIssueDate.setText(null);
                txtReturnDate.setText(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "data not added");
            }
        }
    }//GEN-LAST:event_btnAddIssuMouseClicked

    private void btnAddIssuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddIssuMouseEntered
        btnAddIssu.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnAddIssuMouseEntered

    private void btnAddIssuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddIssuMouseExited
        btnAddIssu.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnAddIssuMouseExited

    private void btnDeleteIssueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteIssueMouseClicked
        int r = tblIssueBooks.getSelectedRow();
        int id = Integer.parseInt(tblIssueBooks.getValueAt(r, 0).toString());
        int count = MySqlMannager.insertUpdateDelete("DELETE FROM issue_book WHERE Issue_Bookid  = '"+id+"'");
        if(count!=0)
        {
            tableIssuedBook();
            JOptionPane.showMessageDialog(null, "Deleted Successfull !!!");
            txtMemberId.setText(null);
            txtMemberName.setText(null);
            txtIssueBookName.setText(null);
            txtIssueDate.setText(null);
            txtReturnDate.setText(null);
        }
        
    }//GEN-LAST:event_btnDeleteIssueMouseClicked

    private void btnDeleteIssueMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteIssueMouseEntered
        btnDeleteIssue.setBackground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnDeleteIssueMouseEntered

    private void btnDeleteIssueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteIssueMouseExited
        btnDeleteIssue.setBackground(new Color(26,28,36));
    }//GEN-LAST:event_btnDeleteIssueMouseExited

    private void btnIssuBookSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIssuBookSearchKeyReleased
        tableIssueSearch();
    }//GEN-LAST:event_btnIssuBookSearchKeyReleased

    private void btnIssueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIssueDateActionPerformed
        DateTimePicker dt = new DateTimePicker(this);
        txtIssueDate.setText(dt.setPickedDate());
    }//GEN-LAST:event_btnIssueDateActionPerformed

    private void btnReturnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnDateActionPerformed
        DateTimePicker dt = new DateTimePicker(this);
        txtReturnDate.setText(dt.setPickedDate());
    }//GEN-LAST:event_btnReturnDateActionPerformed

    private void cmbTypeofBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeofBookActionPerformed

        ResultSet rs = MySqlMannager.viewData("SELECT * FROM book WHERE Book_Type ='"+cmbTypeofBook.getSelectedItem().toString()+"'");
        tblBookIssueSelect.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_cmbTypeofBookActionPerformed

    private void tblDashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDashMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDashMouseClicked

    private void pnlDashMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDashMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlDashMousePressed

    private void pnlDashMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDashMouseDragged
        int locX = evt.getXOnScreen();
        int locY = evt.getYOnScreen();
        
        this.setLocation(locX-x, locY-y);
    }//GEN-LAST:event_pnlDashMouseDragged

    private void cmbBookTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBookTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBookTypeActionPerformed

    private void btnReturnBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnBooksMouseExited
         lblBack.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnReturnBooksMouseExited

    private void btnReturnBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnBooksMouseEntered
        lblBack.setForeground(new Color(38, 74, 108));
    }//GEN-LAST:event_btnReturnBooksMouseEntered

    private void btnReturnBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnBooksMouseClicked
        frmLogin frm = new frmLogin();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReturnBooksMouseClicked

    private void txtMemberIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMemberIdKeyReleased
//        ResultSet rs = MySqlMannager.viewData("SELECT * FROM member");
//        try {
//            while(rs.next())
//            {
//                if(txtMemberId.getText().equals(rs.getInt(1)))
//                {
//                    JOptionPane.showMessageDialog(null, rs.getString(2));
//                }
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "not found");
//        }
    }//GEN-LAST:event_txtMemberIdKeyReleased

    private void txtMemberIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMemberIdKeyTyped

        
    }//GEN-LAST:event_txtMemberIdKeyTyped

    private void active()
    {
        pnlLeft.setVisible(true);
        pnlLeft.setEnabled(true);
    }
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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAdd;
    private javax.swing.JPanel btnAddIssu;
    private javax.swing.JPanel btnBooks;
    private javax.swing.JPanel btnDash;
    private javax.swing.JPanel btnDelete;
    private javax.swing.JPanel btnDeleteIssue;
    private javax.swing.JPanel btnExit;
    private javax.swing.JTextField btnIssuBookSearch;
    private javax.swing.JPanel btnIssueBooks;
    private javax.swing.JButton btnIssueDate;
    private javax.swing.JPanel btnMAdd;
    private javax.swing.JPanel btnMdelete;
    private javax.swing.JPanel btnMembers;
    private javax.swing.JPanel btnMupdate;
    private javax.swing.JPanel btnReturnBooks;
    private javax.swing.JButton btnReturnDate;
    private javax.swing.JPanel btnUpdate;
    private javax.swing.JPanel btnUpdateIssue;
    private javax.swing.JComboBox<String> cmbBookType;
    private javax.swing.JComboBox<String> cmbTypeofBook;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblAdd1;
    private javax.swing.JLabel lblAdd2;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBookCount;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblDelete1;
    private javax.swing.JLabel lblDelete2;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMemberCount;
    private javax.swing.JLabel lblMenubtn;
    private javax.swing.JLabel lblUpdate1;
    private javax.swing.JLabel lblUpdate2;
    private javax.swing.JLabel lblUpdate3;
    private javax.swing.JPanel pnlAddData;
    private javax.swing.JPanel pnlBooks;
    private javax.swing.JPanel pnlDash;
    private javax.swing.JPanel pnlIssueBooks;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMembers;
    private javax.swing.JPanel pnlReturnBooks;
    private javax.swing.JTable tblBookIssueSelect;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTable tblDash;
    private javax.swing.JTable tblIssueBooks;
    private javax.swing.JTable tblMembers;
    private javax.swing.JTabbedPane tbpnlFrmChng;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookName;
    private javax.swing.JTextField txtBookQuntity;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtIssueBookName;
    private javax.swing.JTextField txtIssueDate;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtMemberId;
    private javax.swing.JTextField txtMemberName;
    private javax.swing.JTextField txtMemberSearch;
    private javax.swing.JTextField txtMname;
    private javax.swing.JTextField txtNic;
    private javax.swing.JTextField txtReturnDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTpnumber;
    // End of variables declaration//GEN-END:variables
}
