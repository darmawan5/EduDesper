package dev.tito.edudesper;

public class User {
    public String id;
    public String nama;
    public String tgl;
    public String alamat;
    public String email;
    public String password;

    public User(String id, String nama, String tgl, String alamat, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.tgl = tgl;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }


    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
