package TRIWAHYU;
// MUH.TRI WAHYU MARJIN
// 22116012

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {

    private JPanel panelMain;
    private JList jListMahasiswa;
    private JButton buttonfilter;
    private JTextField textFieldfilter;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;

    private List<Mahasiswa> arrayListMahasiswa=new ArrayList<>();
    private DefaultListModel defaultListModel=new DefaultListModel();

    class Mahasiswa{
        private String Nama;
        private String Nim;
        private float Ipk;

        public String getNama() {
            return Nama;
        }

        public void setNama(String nama) {
            Nama = nama;
        }

        public String getNim() {
            return Nim;
        }

        public void setNim(String nim) {
            Nim = nim;
        }

        public float getIpk() {
            return Ipk;
        }

        public void setIpk(float ipk) {
            Ipk = ipk;
        }
    }

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nama= textFieldNama.getText();
                String Nim=textFieldNim.getText();
                Float Ipk= Float.parseFloat(textFieldIpk.getText());

                Mahasiswa mahasiswa=new Mahasiswa();
                mahasiswa.setNama(Nama);
                mahasiswa.setNim(Nim);
                mahasiswa.setIpk(Ipk);

                arrayListMahasiswa.add(mahasiswa);
                Clearform();

                fromListMahasiswaToListModel();

            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index<0)
                    return;

                String Nama= jListMahasiswa.getSelectedValue().toString();
                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(Nama)){
                        Mahasiswa mahasiswa= arrayListMahasiswa.get(i);
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        break;

                    }
                }
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clearform();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index<0)
                    return;

                String Nama= jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(Nama)){
                        arrayListMahasiswa.remove(i);
                        break;
                    }
                }
                Clearform();
                fromListMahasiswaToListModel();
            }
        });
        buttonfilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = textFieldfilter.getText();
                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().contains(keyWord)) ;
                    {
                        filtered.add(arrayListMahasiswa.get(i).getNama());
                    }
                }
                refreshListModel(filtered);
            }
        });
    }
    private void fromListMahasiswaToListModel(){
        List<String> listNamaMahasiswa= new ArrayList<>();

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            listNamaMahasiswa.add(
                    arrayListMahasiswa.get(i).getNama());
        }
        refreshListModel(listNamaMahasiswa);
    }
    private void Clearform(){
        textFieldNama.setText("");
        textFieldNim.setText("");
        textFieldIpk.setText("");
    }

    private  void refreshListModel(List<String>list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        MainScreen mainScreen=new MainScreen();
        mainScreen.setVisible(true);
    }
}
