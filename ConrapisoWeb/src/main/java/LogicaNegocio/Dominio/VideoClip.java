package LogicaNegocio.Dominio;

public class VideoClip {
    private int id;
    private String titulo;
    private Artista artista;
    private Album album;
    private String url;

    public VideoClip(String titulo, Artista artista, Album album, String url) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.url = url;
    }

    // region Getters

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public Album getAlbum() {
        return album;
    }

    public String getUrl() {
        return url;
    }


    //endregion

}
