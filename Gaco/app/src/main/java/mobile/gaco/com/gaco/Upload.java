package mobile.gaco.com.gaco;

import com.google.firebase.database.Exclude;

public class Upload {

    private String imageUrl;
    private String judul;
    private String isi;
    private String key;
    private int position;

    public Upload(){

    }

    public Upload(int position){
        this.position = position;
    }

    public Upload(String judul, String imageUrl, String isi){

        this.judul = judul;
        this.imageUrl = imageUrl;
        this.isi = isi;

        if(judul.trim().equals("")){
            judul = "No Judul";
        }

    }

    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }
    public void setIsi(String isi) {
        this.isi = isi;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

}
