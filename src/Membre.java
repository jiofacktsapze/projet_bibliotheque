import java.time.LocalDate;

public class Membre {
    private int membreId;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate adhesionDate;

    // Constructeur
    public Membre(int membreId, String nom, String prenom, String email, LocalDate adhesionDate) {
        this.membreId = membreId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }
    // Getter and Setter
    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDate adhesionDate) {
        this.adhesionDate = adhesionDate;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "membreId=" + membreId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adhesionDate=" + adhesionDate +
                '}';
    }
}
