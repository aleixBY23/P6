import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Aleix Esteve, Kevin Costes
 */
public class Clients extends javax.swing.JFrame {

    /**
     * Creates new form Clients
     */
    Connection con;
    public static Statement stmt = null;
    public static ResultSet rs = null;
    DefaultTableModel tableModel = new DefaultTableModel();


    public Clients(Connection c) {
        initComponents();
        this.setLocationRelativeTo(null);
        con = c;
        fillTableData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTableQueryResult = new javax.swing.JTable();
        jButtonInsert = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonFactures = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CLIENTS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jTableQueryResult.setAutoCreateRowSorter(true);
        jTableQueryResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableQueryResult.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jTableQueryResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableQueryResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableQueryResult.setShowGrid(false);
        jTableQueryResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableQueryResultMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableQueryResult);

        jButtonInsert.setText("Inserir");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Modificar");
        jButtonUpdate.setEnabled(false);
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Eliminar");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonFactures.setText("Consultar factures");
        jButtonFactures.setEnabled(false);
        jButtonFactures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFacturesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonInsert)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(80, 80, 80)
                        .addComponent(jButtonFactures)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsert)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonFactures))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableQueryResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQueryResultMouseClicked
        jButtonUpdate.setEnabled(true);
        jButtonDelete.setEnabled(true);        
        jButtonFactures.setEnabled(true);

    }//GEN-LAST:event_jTableQueryResultMouseClicked

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        String DNI = (String)tableModel.getValueAt(jTableQueryResult.getSelectedRow(), 0);
        ClientUpdate obj = new ClientUpdate(con, DNI);
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        ClientInsert obj = new ClientInsert(con);
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        String DNI = (String)tableModel.getValueAt(jTableQueryResult.getSelectedRow(), 0);
        System.out.println(DNI);
        
        try {
            stmt = con.createStatement();
            String query = "DELETE FROM Client WHERE DNI='" + DNI + "'";
            System.out.println(query);
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Client eliminat correctament", "Status", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            FacturesClient obj = new FacturesClient(con, DNI);
            obj.setVisible(true);
        } catch (Exception e) {
            String txt = "Error al eliminar el client: " + e;
            JOptionPane.showMessageDialog(this, txt, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonFacturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacturesActionPerformed
        String DNI = (String)tableModel.getValueAt(jTableQueryResult.getSelectedRow(), 0);
        FacturesClient obj = new FacturesClient(con, DNI);
        obj.setVisible(true);                      
    }//GEN-LAST:event_jButtonFacturesActionPerformed

    private ResultSet executeQuery(Connection con, String query) {

        try {

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            return rs;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            return null;
        }
    }

    private void fillTableData() {
        try {
            ResultSet result = executeQuery(con, "SELECT * FROM client");

            ResultSetMetaData resultMetaData = result.getMetaData();
            //System.out.println("List of column names in the current table: ");

            int nCols = resultMetaData.getColumnCount();
            String cols[] = new String[nCols];

            

            for (int i = 1; i <= nCols; i++) {
                tableModel.addColumn(resultMetaData.getColumnName(i));
            }

            while (result.next()) {

                Object[] rowData = new Object[nCols];

                for (int i = 0; i < nCols; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(rowData);

            }

            jTableQueryResult.setModel(tableModel);
            jTableQueryResult.setColumnSelectionAllowed(false);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonFactures;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableQueryResult;
    // End of variables declaration//GEN-END:variables
}
