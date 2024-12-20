import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final List<Buku> daftarBuku = new ArrayList<>();
    private static final List<Anggota> daftarAnggota = new ArrayList<>();
    private static final List<peminjaman> daftarPeminjaman = new ArrayList<>();
    private static final List<Pengembalian> daftarPengembalian = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistem Perpustakaan");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(9, 1, 10, 10));

            JButton btnTambahBuku = new JButton("Tambah Buku");
            JButton btnTambahAnggota = new JButton("Tambah Anggota");
            JButton btnPinjamBuku = new JButton("Pinjam Buku");
            JButton btnKembalikanBuku = new JButton("Kembalikan Buku");
            JButton btnTampilkanBuku = new JButton("Tampilkan Buku");
            JButton btnTampilkanAnggota = new JButton("Tampilkan Anggota");
            JButton btnTampilkanPeminjaman = new JButton("Tampilkan Peminjaman");
            JButton btnTampilkanPengembalian = new JButton("Tampilkan Pengembalian");
            JButton btnKeluar = new JButton("Keluar");

            panel.add(btnTambahBuku);
            panel.add(btnTambahAnggota);
            panel.add(btnPinjamBuku);
            panel.add(btnKembalikanBuku);
            panel.add(btnTampilkanBuku);
            panel.add(btnTampilkanAnggota);
            panel.add(btnTampilkanPeminjaman);
            panel.add(btnTampilkanPengembalian);
            panel.add(btnKeluar);

            btnTambahBuku.addActionListener(e -> tambahBukuDialog(frame));
            btnTambahAnggota.addActionListener(e -> tambahAnggotaDialog(frame));
            btnPinjamBuku.addActionListener(e -> pinjamBukuDialog(frame));
            btnKembalikanBuku.addActionListener(e -> kembalikanBukuDialog(frame));
            btnTampilkanBuku.addActionListener(e -> tampilkanData("Daftar Buku", daftarBuku, frame));
            btnTampilkanAnggota.addActionListener(e -> tampilkanData("Daftar Anggota", daftarAnggota, frame));
            btnTampilkanPeminjaman.addActionListener(e -> tampilkanData("Daftar Peminjaman", daftarPeminjaman, frame));
            btnTampilkanPengembalian.addActionListener(e -> tampilkanData("Daftar Pengembalian", daftarPengembalian, frame));
            btnKeluar.addActionListener(e -> System.exit(0));

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void tambahBukuDialog(JFrame parent) {
        JTextField txtJudul = new JTextField();
        JTextField txtID = new JTextField();
        JTextField txtPengarang = new JTextField();
        JTextField txtISBN = new JTextField();
        JComboBox<String> comboJenis = new JComboBox<>(new String[]{"Fiksi", "Non-Fiksi", "Referensi"});

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Judul:"));
        panel.add(txtJudul);
        panel.add(new JLabel("ID Item:"));
        panel.add(txtID);
        panel.add(new JLabel("Pengarang:"));
        panel.add(txtPengarang);
        panel.add(new JLabel("ISBN:"));
        panel.add(txtISBN);
        panel.add(new JLabel("Jenis Buku:"));
        panel.add(comboJenis);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Tambah Buku", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String jenis = (String) comboJenis.getSelectedItem();
            Buku buku = switch (jenis) {
                case "Fiksi" -> new BukuFiksi(txtJudul.getText(), txtID.getText(), txtPengarang.getText(), txtISBN.getText());
                case "Non-Fiksi" -> new BukuNonFiksi(txtJudul.getText(), txtID.getText(), txtPengarang.getText(), txtISBN.getText());
                case "Referensi" -> new BukuReferensi(txtJudul.getText(), txtID.getText(), txtPengarang.getText(), txtISBN.getText());
                default -> null;
            };
            if (buku != null) daftarBuku.add(buku);
        }
    }

    private static void tambahAnggotaDialog(JFrame parent) {
        JTextField txtNama = new JTextField();
        JTextField txtNomor = new JTextField();
        JTextField txtEmail = new JTextField();
        JComboBox<String> comboTipe = new JComboBox<>(new String[]{"Umum", "Mahasiswa", "Siswa"});


        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nama:"));
        panel.add(txtNama);
        panel.add(new JLabel("Nomor Anggota:"));
        panel.add(txtNomor);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Tipe Keanggotaan:"));
        panel.add(comboTipe);


        int result = JOptionPane.showConfirmDialog(parent, panel, "Tambah Anggota", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String tipe = (String) comboTipe.getSelectedItem();
            Anggota anggota = switch (tipe) {
                case "Umum" -> new Umum(txtNama.getText(), txtNomor.getText(), txtEmail.getText());
                case "Mahasiswa" -> new Mahasiswa(txtNama.getText(), txtNomor.getText(),txtEmail.getText() );
                case "Siswa" -> new Siswa(txtNama.getText(), txtNomor.getText(),txtEmail.getText());
                default -> null;
            };
            if (anggota != null) daftarAnggota.add(anggota);
        }
    }

    private static void pinjamBukuDialog(JFrame parent) {
        if (daftarBuku.isEmpty() || daftarAnggota.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Daftar buku atau anggota kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<Buku> comboBuku = new JComboBox<>(daftarBuku.toArray(new Buku[0]));
        JComboBox<Anggota> comboAnggota = new JComboBox<>(daftarAnggota.toArray(new Anggota[0]));
        JTextField txtTanggalPinjam = new JTextField();
        JTextField txtTanggalBatasKembali = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Pilih Buku:"));
        panel.add(comboBuku);
        panel.add(new JLabel("Pilih Anggota:"));
        panel.add(comboAnggota);
        panel.add(new JLabel("Tanggal Pinjam (YYYY-MM-DD):"));
        panel.add(txtTanggalPinjam);
        panel.add(new JLabel("Batas Kembali (YYYY-MM-DD):"));
        panel.add(txtTanggalBatasKembali);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Pinjam Buku", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Buku buku = (Buku) comboBuku.getSelectedItem();
                Anggota anggota = (Anggota) comboAnggota.getSelectedItem();
                LocalDate tanggalPinjam = LocalDate.parse(txtTanggalPinjam.getText());
                LocalDate tanggalBatasKembali = LocalDate.parse(txtTanggalBatasKembali.getText());

                if (buku != null && anggota != null) {
                    daftarPeminjaman.add(new peminjaman(buku, anggota, tanggalPinjam, tanggalBatasKembali));
                    JOptionPane.showMessageDialog(parent, "Buku berhasil dipinjam!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void kembalikanBukuDialog(JFrame parent) {
        if (daftarPeminjaman.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Tidak ada buku yang sedang dipinjam!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<peminjaman> comboPeminjaman = new JComboBox<>(daftarPeminjaman.toArray(new peminjaman[0]));
        JTextField txtTanggalKembali = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Pilih Peminjaman:"));
        panel.add(comboPeminjaman);
        panel.add(new JLabel("Tanggal Kembali (YYYY-MM-DD):"));
        panel.add(txtTanggalKembali);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Kembalikan Buku", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                peminjaman peminjaman = (peminjaman) comboPeminjaman.getSelectedItem();
                LocalDate tanggalKembali = LocalDate.parse(txtTanggalKembali.getText());

                if (peminjaman != null) {
                    Pengembalian pengembalian = new Pengembalian(peminjaman, tanggalKembali);
                    daftarPengembalian.add(pengembalian);
                    daftarPeminjaman.remove(peminjaman);
                    JOptionPane.showMessageDialog(parent, "Buku berhasil dikembalikan!\nDenda: Rp " + pengembalian.getDenda());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void tampilkanData(String title, List<?> data, JFrame parent) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        data.forEach(item -> textArea.append(item.toString() + "\n"));

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(parent, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
