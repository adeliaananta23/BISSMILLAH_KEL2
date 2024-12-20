import java.time.LocalDate;

public class Pengembalian {
    private peminjaman peminjaman;
    private LocalDate tanggalKembali;
    private int denda;

    public Pengembalian(peminjaman peminjaman, LocalDate tanggalKembali) {
        this.peminjaman = peminjaman;
        this.tanggalKembali = tanggalKembali;
        this.denda = peminjaman.hitungDenda(tanggalKembali);
        peminjaman.setStatusDipinjam(false); // Ubah status buku menjadi tersedia
    }

    public int getDenda() {
        return denda;
    }

    @Override
    public String toString() {
        return "Buku: " + peminjaman.getBuku().getJudul() +
                " | Dikembalikan oleh: " + peminjaman.getAnggota().getNama() +
                " | Tanggal Kembali: " + tanggalKembali +
                " | Denda: Rp " + denda;
    }
}
