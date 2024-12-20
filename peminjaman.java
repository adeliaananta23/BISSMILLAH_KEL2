import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class peminjaman {
    private Buku buku;
    private Anggota anggota;
    private boolean statusDipinjam;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalBatasKembali;
    private static final int DENDA_PER_HARI = 5000; // Rp 5000 per hari keterlambatan

    public peminjaman(Buku buku, Anggota anggota, LocalDate tanggalPinjam, LocalDate tanggalBatasKembali) {
        this.buku = buku;
        this.anggota = anggota;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalBatasKembali = tanggalBatasKembali;
        this.statusDipinjam = true;
    }

    public Buku getBuku() {
        return buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public boolean isStatusDipinjam() {
        return statusDipinjam;
    }

    public void setStatusDipinjam(boolean statusDipinjam) {
        this.statusDipinjam = statusDipinjam;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalBatasKembali() {
        return tanggalBatasKembali;
    }

    public int hitungDenda(LocalDate tanggalKembali) {
        if (tanggalKembali.isAfter(tanggalBatasKembali)) {
            long hariTerlambat = ChronoUnit.DAYS.between(tanggalBatasKembali, tanggalKembali);
            return (int) hariTerlambat * DENDA_PER_HARI;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Buku: " + buku.getJudul() + " | Dipinjam oleh: " + anggota.getNama() +
                " | Tanggal Pinjam: " + tanggalPinjam + " | Batas Kembali: " + tanggalBatasKembali +
                " | Status: " + (statusDipinjam ? "Dipinjam" : "Tersedia");
    }
}
