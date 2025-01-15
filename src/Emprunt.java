import java.time.LocalDate;

public class Emprunt {
    private int idEmprunt;
    private int livreId;
    private int membreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur
    public Emprunt(int idEmprunt, int livreId, int membreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective) {
        this.idEmprunt = idEmprunt;
        this.livreId = livreId;
        this.membreId = membreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }
    // Getter and Setter
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getLivreid() {
        return livreId;
    }

    public void setLivreid(int livreid) {
        this.livreId = livreid;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "idEmprunt=" + idEmprunt +
                ", livreid=" + livreId +
                ", membreId=" + membreId +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourEffective=" + dateRetourEffective +
                '}';
    }
}
