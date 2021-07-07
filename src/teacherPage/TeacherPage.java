/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherPage;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nati
 */
public class TeacherPage extends javax.swing.JFrame {

    /**
     * Creates new form TeacherPage
     */
    public static int currentTeacher;
    public static JFrame parent;
    public int subjectId;
    public int classId;
    public String teacherName;
    public int gradeS;
    public String subject;
    public String sec;
    public TeacherPage(JFrame parent, int current) {
        this.parent = parent;
        currentTeacher=current;
        initComponents();
        getSubjectId();
        getClassId();
        try {
            loadData("");
        } catch (SQLException ex) {
            Logger.getLogger(TeacherPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        initProfile();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    public void initProfile(){
        subj.setText("Subject: "+subject);
        name.setText(""+teacherName);
        section.setText("Section: "+sec);
        grade.setText("Grade: "+gradeS);
    }
    public void getSubjectId(){
        
        try {
            conn=DriverManager.getConnection(url,usr,pass);
            stmnt=conn.createStatement();
            rs=stmnt.executeQuery("select * from teacher where id="+currentTeacher);
            while(rs.next()){
                subjectId=rs.getInt("subj_id");
                teacherName=rs.getString("Fname");
                teacherName=teacherName+" "+rs.getString("Lname");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherPage.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                conn.close();
                stmnt.close();
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void getClassId(){
        
        try {
            conn=DriverManager.getConnection(url,usr,pass);
            stmnt=conn.createStatement();
            rs=stmnt.executeQuery("select * from subject where id="+subjectId);
            while(rs.next()){
                classId=rs.getInt("class_id");
                subject=rs.getString("name");
            }
            rs=stmnt.executeQuery("select * from class where id="+classId);
            while(rs.next()){
                sec=rs.getString("section");
                gradeS=rs.getInt("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherPage.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                conn.close();
                stmnt.close();
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    Connection conn = null;
    Statement stmnt = null;
    Statement stmnt2 = null;
    PreparedStatement ps;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost/student_management";
    String usr = "root";
    String pass = "root";
    DefaultTableModel model;

    public void loadData(String like) throws SQLException {
        model = (DefaultTableModel) listTableA.getModel();
        conn = DriverManager.getConnection(url, usr, pass);
        stmnt2 = conn.createStatement();
        stmnt = conn.createStatement();
        int count = model.getRowCount();
        for (int i = 0; i < count; i++) {
            model.removeRow(0);
        }
        ResultSet rs2;
        if (like.equals("")) {
            rs = stmnt.executeQuery("select * from student where class_id="+classId);

        } else {
            rs = stmnt.executeQuery("select * from student where Fname like \"" + like + "%\"");
        }
        while (rs.next()) {
            int d = rs.getInt("id");
            String[] data = new String[8];
            data[0] = Integer.toString(d);
            data[1] = rs.getString("Fname");
            data[1] = data[1] + " " + rs.getString("Mname");
            data[1] = data[1] + " " + rs.getString("Lname");
            data[2] = rs.getString("sex");
            ResultSet rs3=stmnt2.executeQuery("SELECT COUNT(id) FROM mark where subject="+subjectId+" and stud_id="+d);
            int ct=0;
            while(rs3.next()){
                ct=rs3.getInt(1);
            }
            rs2 = stmnt2.executeQuery("select * from mark where stud_id=" + d+" and subject=\'"+subjectId+"\'");

            if (ct!= 0) {
                while(rs2.next()){
                    data[3] = Double.toString(rs2.getDouble("quiz(10)"));
                    data[4] = Double.toString(rs2.getDouble("test(10)"));
                    data[5] = Double.toString(rs2.getDouble("mid_test(30)"));
                    data[6] = Double.toString(rs2.getDouble("final(50)"));
                    data[7] = Double.toString(rs2.getDouble("total(100)"));
                }
            } else {
                data[3] = "-";
                data[4] = "-";
                data[5] = "-";
                data[6] = "-";
                data[7] = "-";
            }
            model.addRow(data);
        }
        conn.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        leftPanel = new javax.swing.JPanel();
        markList = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        uploadMaterial = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logOut = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        submitResult = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        markTab = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        section = new javax.swing.JLabel();
        grade = new javax.swing.JLabel();
        subj = new javax.swing.JLabel();
        listTable = new javax.swing.JScrollPane();
        listTableA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        leftPanel.setBackground(new java.awt.Color(0, 102, 255));

        markList.setBackground(new java.awt.Color(0, 100, 180));
        markList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                markListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                markListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                markListMouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mark List");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_pencil_40px.png"))); // NOI18N

        javax.swing.GroupLayout markListLayout = new javax.swing.GroupLayout(markList);
        markList.setLayout(markListLayout);
        markListLayout.setHorizontalGroup(
            markListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(markListLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(markListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(markListLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        markListLayout.setVerticalGroup(
            markListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, markListLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        uploadMaterial.setBackground(new java.awt.Color(0, 100, 180));
        uploadMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadMaterialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uploadMaterialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uploadMaterialMouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Upload Materials");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_upload_40px_1.png"))); // NOI18N

        javax.swing.GroupLayout uploadMaterialLayout = new javax.swing.GroupLayout(uploadMaterial);
        uploadMaterial.setLayout(uploadMaterialLayout);
        uploadMaterialLayout.setHorizontalGroup(
            uploadMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadMaterialLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uploadMaterialLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(25, 25, 25))
        );
        uploadMaterialLayout.setVerticalGroup(
            uploadMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uploadMaterialLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(19, 19, 19))
        );

        logOut.setBackground(new java.awt.Color(0, 100, 180));
        logOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logOutMouseExited(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Log Out");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_shutdown_40px_2.png"))); // NOI18N

        javax.swing.GroupLayout logOutLayout = new javax.swing.GroupLayout(logOut);
        logOut.setLayout(logOutLayout);
        logOutLayout.setHorizontalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logOutLayout.createSequentialGroup()
                .addGroup(logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logOutLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel8))
                    .addGroup(logOutLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        logOutLayout.setVerticalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logOutLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(19, 19, 19))
        );

        submitResult.setBackground(new java.awt.Color(0, 100, 180));
        submitResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitResultMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitResultMouseExited(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Submit Result");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_send_file_40px.png"))); // NOI18N

        javax.swing.GroupLayout submitResultLayout = new javax.swing.GroupLayout(submitResult);
        submitResult.setLayout(submitResultLayout);
        submitResultLayout.setHorizontalGroup(
            submitResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submitResultLayout.createSequentialGroup()
                .addGroup(submitResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(submitResultLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel12))
                    .addGroup(submitResultLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        submitResultLayout.setVerticalGroup(
            submitResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submitResultLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submitResult, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(markList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(uploadMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(markList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(uploadMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setLayout(new java.awt.BorderLayout());

        markTab.setBackground(new java.awt.Color(255, 255, 255));
        markTab.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(70, 70, 70));
        jPanel2.setForeground(new java.awt.Color(70, 70, 70));

        jLabel9.setBackground(new java.awt.Color(0, 100, 180));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(50, 150, 230));
        jLabel9.setText("Search");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_24px.png"))); // NOI18N

        jTextField1.setBackground(new java.awt.Color(70, 70, 70));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setForeground(java.awt.Color.white);
        jTextField1.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10)
                        .addGap(0, 67, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(70, 70, 70));
        jPanel4.setForeground(new java.awt.Color(70, 70, 70));

        jButton2.setBackground(java.awt.Color.white);
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(70, 70, 70));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        markTab.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(java.awt.Color.white);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_50px.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(70, 70, 70));
        name.setText("Temesgen");

        section.setText("Section: ");

        grade.setText("Grade: ");

        subj.setText("Subject: Physics");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(subj)
                .addGap(18, 18, 18)
                .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name)
                    .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subj, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        markTab.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        listTable.setBackground(java.awt.Color.white);

        listTableA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listTableA.setForeground(new java.awt.Color(51, 51, 51));
        listTableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Section", "Quiz (10%)", "Test (20%)", "Mid Test (20%) ", "Final Test (50%)", "Total (100%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listTableA.setFillsViewportHeight(true);
        listTable.setViewportView(listTableA);

        markTab.add(listTable, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Mark List", markTab);

        rightPanel.add(tabbedPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(rightPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void markListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_markListMouseEntered
        markList.setBackground(new Color(0, 80, 165));        // TODO add your handling code here:
    }//GEN-LAST:event_markListMouseEntered

    private void markListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_markListMouseExited
        markList.setBackground(new Color(0, 100, 180)); // TODO add your handling code here:
    }//GEN-LAST:event_markListMouseExited

    private void uploadMaterialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadMaterialMouseEntered
        uploadMaterial.setBackground(new Color(0, 80, 165));    // TODO add your handling code here:
    }//GEN-LAST:event_uploadMaterialMouseEntered

    private void uploadMaterialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadMaterialMouseExited
        uploadMaterial.setBackground(new Color(0, 100, 180)); // TODO add your handling code here:
    }//GEN-LAST:event_uploadMaterialMouseExited

    private void logOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseEntered
        logOut.setBackground(new Color(0, 80, 165)); // TODO add your handling code here:
    }//GEN-LAST:event_logOutMouseEntered

    private void logOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseExited
        logOut.setBackground(new Color(0, 100, 180)); // TODO add your handling code here:
    }//GEN-LAST:event_logOutMouseExited

    private void submitResultMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitResultMouseEntered
        submitResult.setBackground(new Color(0, 80, 165));// TODO add your handling code here:
    }//GEN-LAST:event_submitResultMouseEntered

    private void submitResultMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitResultMouseExited
        submitResult.setBackground(new Color(0, 100, 180)); // TODO add your handling code here:
    }//GEN-LAST:event_submitResultMouseExited

    private void markListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_markListMouseClicked
        tabbedPane.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_markListMouseClicked

    private void uploadMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadMaterialMouseClicked
       FileChooser chooser=new FileChooser(this); // TODO add your handling code here:
       String[] arg={};
        setEnabled(false);
       chooser.main(arg);
    }//GEN-LAST:event_uploadMaterialMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        parent.setVisible(true);        // TODO add your handling code here:
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        double quiz;
        double test;
        double mid_test;
        double fiNal;
        double total;
        int id;
        int row = model.getRowCount();quiz = 0;
        for (int i = 0; i < row; i++) {
            quiz = 0;
            test = 0;
            mid_test=0;
            fiNal=0;
            total=0;
            
            id=Integer.parseInt((String) model.getValueAt(i, 0));
            if (!((String) model.getValueAt(i, 3)).equals("-")) {
                quiz = Double.parseDouble((String) model.getValueAt(i, 3));
            }
            if (!((String) model.getValueAt(i, 4)).equals("-")) {
                test = Double.parseDouble((String) model.getValueAt(i, 4));
            }
            if (!((String) model.getValueAt(i, 5)).equals("-")) {
                mid_test = Double.parseDouble((String) model.getValueAt(i, 5));
            }
            if (!((String) model.getValueAt(i, 6)).equals("-")) {
                fiNal = Double.parseDouble((String) model.getValueAt(i, 6));
            }
            total=quiz+test+mid_test+fiNal+total;
            try {
                conn = DriverManager.getConnection(url, usr, pass);
                stmnt = conn.createStatement();
                rs=stmnt.executeQuery("SELECT COUNT(id) FROM mark where subject="+subjectId+" and stud_id="+id);
                int ct=0;
                while(rs.next()){
                    ct=rs.getInt(1);
                }
                if(ct==0){
                     ps = conn.prepareStatement("insert into mark values(?,?,?,?,?,?,?,0)");
                }else{
                     ps = conn.prepareStatement("UPDATE mark SET subject=?,`quiz(10)`=? ,`test(10)`=?, `mid_test(30)`"
                             + "=?,`final(50)`=?,`total(100)`=?,`stud_id`=? where subject="+subjectId+" and stud_id="+id);
                }
               
                ps.setInt(1,subjectId);
                ps.setDouble(2, quiz);
                ps.setDouble(3, test);
                ps.setDouble(4, mid_test);
                ps.setDouble(5, fiNal);
                ps.setDouble(6, total);
                ps.setInt(7, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully updated!");
            } catch (SQLException ex) {
                Logger.getLogger(TeacherPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

                // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    public void getSectionSubject(int subjId) {
        try {
            conn = DriverManager.getConnection(url, usr, pass);
            stmnt = conn.createStatement();
        } catch (Exception e) {

        }
    }

    public void getSectionClass(int classId) {
        try {

        } catch (Exception e) {

        }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeacherPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherPage(parent, currentTeacher).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel grade;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JScrollPane listTable;
    private javax.swing.JTable listTableA;
    private javax.swing.JPanel logOut;
    private javax.swing.JPanel markList;
    private javax.swing.JPanel markTab;
    private javax.swing.JLabel name;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel section;
    private javax.swing.JLabel subj;
    private javax.swing.JPanel submitResult;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel uploadMaterial;
    // End of variables declaration//GEN-END:variables
}
