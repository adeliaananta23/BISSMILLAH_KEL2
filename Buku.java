public abstract class Buku extends Item_Perpustakaan {
    private String kategori;

    public Buku(String judul, String idItem, String pengarang, String ISBN, String kategori) {
        super(judul, idItem, pengarang, ISBN);
        this.kategori = kategori;
    }

    public String getKategori() {

        return kategori;
    }
}
