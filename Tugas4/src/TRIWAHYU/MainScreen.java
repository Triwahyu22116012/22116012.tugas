package TRIWAHYU;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/datamahasiswa";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JPanel PanelMain;
    private JTable JTableMahasiswa;
    private JTextField txtnim;
    private JTextField txtnama;
    private JTextField txtipk;
    private JButton btnadd;
    private JButton btnupdate;
    private JButton btndelete;
    private JButton btnclear;
    private JButton btnfilter;
    private JTextField txtfilter;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        refreshtable(getMahasiswa());


        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nama = txtnama.getText();
               String nim = txtnim.getText();
               double ipk = Double.parseDouble(txtipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                clearfrom();
                insertMahasiswa(mahasiswa);
                refreshtable(getMahasiswa());
            }
        });
        JTableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = JTableMahasiswa.getSelectedRow();
                System.out.println(row);

                String nim = JTableMahasiswa.getValueAt(row,0).toString();
                String nama = JTableMahasiswa.getValueAt(row,1).toString();
                String ipk = JTableMahasiswa.getValueAt(row,2).toString();

                txtnama.setText(nama);
                txtnim.setText(nim);
                txtipk.setText(ipk);
            }
        });
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtnama.getText();
                String nim = txtnim.getText();
                double ipk = Double.parseDouble(txtipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                clearfrom();
                updateMahasiswa(mahasiswa);
                refreshtable(getMahasiswa());
            }
        });
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtnim.getText();

                clearfrom();
                deleteMahasiswa(nim);
                refreshtable(getMahasiswa());
            }
        });
        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearfrom();
            }
        });
        btnfilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtfilter.getText();
                refreshtable(filterMahasiswa(nama));
            }
        });
    }

    private static List<Mahasiswa> filterMahasiswa(String filternama){
        List<Mahasiswa> arrayListMahasiswa =new ArrayList<>();

        ResultSet resultSet = excecuteQuery(" select * from mahasiswa where nama like '%" + filternama + "%'");
        try {
            while (resultSet.next()){
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nim);
                System.out.println(nama);
                System.out.println(ipk);
                System.out.println();

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);
                arrayListMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }
    public void refreshtable(List<Mahasiswa> arrayListMahasiswa){
        Object [][] data = new Object [arrayListMahasiswa.size()][3];

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            data[i]= new Object[]{
                    arrayListMahasiswa.get(i).getNim(),
                    arrayListMahasiswa.get(i).getNama(),
                    arrayListMahasiswa.get(i).getIpk()
            };
        }
        defaultTableModel = new DefaultTableModel(
                data,
                new String[] {"nim","nama","ipk"}
        );
        JTableMahasiswa.setModel(defaultTableModel);
    }

    private static ResultSet excecuteQuery ( String query){

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);
        }
        catch (Exception e){
            return null;
        }
    }

    private static List<Mahasiswa> getMahasiswa(){
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();

        ResultSet resultSet = excecuteQuery("select * from mahasiswa");

        try {
            while (resultSet.next()){
                String nama = resultSet.getString("nama");
                String nim = resultSet.getString("nim");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nim);
                System.out.println(nama);
                System.out.println(ipk);
                System.out.println();

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);
                arrayListMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }

    public static void executesql(String sql) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
        }
        catch (Exception e){
        }
    }
    private static void insertMahasiswa(Mahasiswa mahasiswa){
        String sql = "insert into mahasiswa values (" +
                "'" + mahasiswa.getNim() + "', "+
                "'" + mahasiswa.getNama() + "', "+
                "'" + mahasiswa.getIpk() + "')";

        executesql(sql);
    }
    private static void updateMahasiswa(Mahasiswa mahasiswa){
        String sql = "update mahasiswa set " +
                "nama = '"+ mahasiswa.getNama() +"', "+
                "ipk = '"+ mahasiswa.getIpk() +"' "+
                " where nim = '"+ mahasiswa.getNim() +"'";

        executesql(sql);
    }
    private static void deleteMahasiswa(String nim){
        String sql = "delete from mahasiswa " +
                "where nim = '"+ nim +"'";

        executesql(sql);
    }
    private void clearfrom(){
        txtnama.setText("");
        txtnim.setText("");
        txtipk.setText("");
    }

    public static void main(String[] args) {
      MainScreen mainScreen = new MainScreen();
      mainScreen.setVisible(true);
    }
}
