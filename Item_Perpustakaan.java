public class Item_Perpustakaan {
    private String judul;
    private String idItem;
    private String pengarang;
    private String ISBN;

    public Item_Perpustakaan(String judul, String idItem, String pengarang, String ISBN) {
        this.judul = judul;
        this.idItem = idItem;
        this.pengarang = pengarang;
        this.ISBN = ISBN;
    }

    public String getJudul() {
        return judul;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
        return "ID: " + idItem + ", Judul: " + judul + ", Pengarang: " + pengarang + ", ISBN: " + ISBN;
    }
}
