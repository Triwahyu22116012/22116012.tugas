package TUGAS_3;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel PanelMain;
    private JList listMahasiswa;
    private JButton buttonFilter;
    private JTextField textFieldMahasiswa;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;

    private List<Mahasiswa> arraydata =new ArrayList<>();

    private DefaultListModel defaultListModel= new DefaultListModel<>();


      class Mahasiswa {
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
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Nama = textFieldNama.getText();
                String Nim= textFieldNim.getText();
                Float Ipk= Float.valueOf(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(Nama);
                mahasiswa.setNim(Nim);
                mahasiswa.setIpk(Ipk);

                arraydata.add(mahasiswa);

                setButtonClear();
                fromMahasiswaTOlistModel();
            }
        });
        listMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = listMahasiswa.getSelectedIndex();
                if (index<0)
                    return;

                String Nama = listMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(Nama)){
                        Mahasiswa mahasiswa= arraydata.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                    }

                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = listMahasiswa.getSelectedIndex();
                if (index<0)
                    return;

                String Nama = listMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(Nama)){
                        arraydata.remove(i);
                        break;

                    }
                }
                setButtonClear();
                fromMahasiswaTOlistModel();
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonClear();
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyWord =textFieldMahasiswa.getText();
                List<String> Filtered = new ArrayList<>();

                for (int i = 0; i < arraydata.size(); i++) {
                    if ( arraydata.get(i).getNama().contains(keyWord)){
                        Filtered.add(arraydata.get(i).getNama());
                    }
                }
                refreshlistModel(Filtered);
            }
        });
    }
     private void fromMahasiswaTOlistModel(){

          List<String> listNamaMahasiswa = new ArrayList<>();
         for (int i = 0; i < arraydata.size(); i++) {
             listNamaMahasiswa.add(arraydata.get(i).getNama());
         }
          refreshlistModel(listNamaMahasiswa);
     }
     private void setButtonClear(){
          textFieldNama.setText("");
          textFieldNim.setText("");
          textFieldIpk.setText("");
     }

     private void  refreshlistModel(List<String>list){
          defaultListModel.clear();
          defaultListModel.addAll(list);
          listMahasiswa.setModel(defaultListModel);
     }


    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
