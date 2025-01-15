public class Livre {
    private int livreId;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaires;

    // Constructeur
    public Livre(int livreId, String titre, String auteur, String categorie, int nombreExemplaires) {
        this.livreId = livreId;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }
    // Getter and Setter
    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public String getTitre(){
        return titre;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "livreId=" + livreId +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", categorie='" + categorie + '\'' +
                ", nombreExemplaires=" + nombreExemplaires +
                '}';
    }
}

