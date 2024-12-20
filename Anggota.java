public abstract class Anggota {
    private String nama;
    private String nomorAnggota;
    private String tipeKeanggotaan;
    private String email;

    public Anggota(String nama, String nomorAnggota, String tipeKeanggotaan, String email) {
        this.nama = nama;
        this.nomorAnggota = nomorAnggota;
        this.email = email;
        this.tipeKeanggotaan = tipeKeanggotaan;

    }

    public String getNama() {
        return nama;
    }

    public String getNomorAnggota() {
        return nomorAnggota;
    }

    public String getTipeKeanggotaan() {
        return tipeKeanggotaan;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Nomor: " + nomorAnggota + ", Nama: " + nama + ", Tipe: " + tipeKeanggotaan + ", Email: " + email;
    }
}
