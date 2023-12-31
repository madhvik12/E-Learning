
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author madhvik
 */
public class Manage_courses extends javax.swing.JFrame {

    /**
     * Creates new form Manage_courses
     */
    File ph;
    JFileChooser jfc;
    ArrayList<course> al=new ArrayList<>();
    myTableModel tm;
    public Manage_courses() {
        initComponents();
        setSize(800,550);
        load_category();
        tm=new myTableModel();
        mytable.setModel(tm);
    }
    void load_category(){
        String ans=myClient.fetchCategories();
        StringTokenizer st=new StringTokenizer(ans,";;");
        int n=st.countTokens();
        cb1.addItem("Select");
        for(int i=1;i<=n;i++){
            String name=st.nextToken();
            cb.addItem(name);
            cb1.addItem(name);
        }
    }
    
    void fetchData(){
         String category=(String)cb1.getSelectedItem();
        String ans=myClient.fetchCourses(category);
        al.clear();
        StringTokenizer st=new StringTokenizer(ans,";;");
        while(st.hasMoreTokens()){
            String row=st.nextToken();
            StringTokenizer st1=new StringTokenizer(row,"$");
            int id=Integer.parseInt(st1.nextToken());
            String name=st1.nextToken();
            String desc=st1.nextToken();
            String photo=st1.nextToken();
            al.add(new course(id,name,desc,photo));
        }
        tm.fireTableDataChanged();
    }
    
    class myTableModel extends AbstractTableModel{

        @Override
        public int getRowCount() {
           return al.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex==0)return al.get(rowIndex).name;
            else if(columnIndex==1)return al.get(rowIndex).desc;
            else return al.get(rowIndex).photo;
        }

        @Override
        public String getColumnName(int column) {
            String names[]={"Name","Description","Photo"};
            return names[column];
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        photolb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        mytable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Courses");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 40);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add course");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 60, 100, 20);

        jLabel3.setText("View Courses");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(580, 50, 100, 30);

        jLabel4.setText("Category");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 90, 100, 16);

        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });
        getContentPane().add(cb);
        cb.setBounds(20, 110, 180, 22);

        jLabel5.setText("Category");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(480, 80, 100, 20);

        getContentPane().add(cb1);
        cb1.setBounds(470, 110, 180, 22);

        jLabel6.setText("Name");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 140, 80, 16);
        getContentPane().add(name);
        name.setBounds(20, 160, 180, 30);

        jLabel7.setText("Description");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 190, 120, 16);

        description.setColumns(20);
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 210, 150, 100);

        jLabel8.setText("Photo");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 330, 80, 16);

        photolb.setText("jLabel9");
        getContentPane().add(photolb);
        photolb.setBounds(20, 360, 120, 100);

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 400, 72, 23);

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(160, 440, 75, 23);

        jButton3.setText("VIew");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(660, 110, 72, 23);

        mytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(mytable);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(430, 150, 340, 260);

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(560, 450, 90, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jfc=new JFileChooser();
        int ans=jfc.showOpenDialog(this);
        if(ans==JFileChooser.APPROVE_OPTION){
            ph=jfc.getSelectedFile();
            ImageIcon ic=new ImageIcon(ph.getPath());
            Image img=ic.getImage().getScaledInstance(photolb.getWidth(), photolb.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ic1=new ImageIcon(img);
            photolb.setIcon(ic1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String course_name=name.getText();
        String course_desc=description.getText();
        String categpry=(String)cb.getSelectedItem();
        String ans=myClient.addCourse(categpry, course_name, course_desc, ph);
        if(ans.trim().equals("success")){
            JOptionPane.showMessageDialog(rootPane, "Course added");
            fetchData();
          
        }
        else{
            JOptionPane.showMessageDialog(rootPane, ans);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        fetchData();
       
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int i=mytable.getSelectedRow();
        if(i==-1)JOptionPane.showMessageDialog(rootPane, "Please select a row");
        else {
            int id=al.get(i).id;
            String ans=myClient.deleteCourse(id);
            if(ans.trim().equals("success")){
                JOptionPane.showMessageDialog(rootPane, "Deleted successfully");
                fetchData();
            }
            else JOptionPane.showMessageDialog(rootPane, ans);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_courses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_courses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_courses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_courses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_courses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JTextArea description;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable mytable;
    private javax.swing.JTextField name;
    private javax.swing.JLabel photolb;
    // End of variables declaration//GEN-END:variables
}
