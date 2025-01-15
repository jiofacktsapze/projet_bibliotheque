import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {
    private Connection connection;
    // Constructeur
    public EmpruntDAO(Connection connection){
        this.connection = connection;
    }
    // Méthode pour enregistrer un emprunt
    public void addEmprunt(Emprunt emprunt) throws SQLException {
        String query = "INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, emprunt.getMembreId());
            pstmt.setInt(2, emprunt.getLivreid());
            pstmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            pstmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            pstmt.executeUpdate();
        }
    }
    // Méthode pour gérer le retour d'un livre
    public void returnLivre(int idEmprunt, LocalDate dateRetourEffective) throws SQLException{
        String query = "UPDATE emprunts SET date_retour_effective = ? WHERE id_emprunts = ?";
        try (PreparedStatement statement= connection.prepareStatement(query)){
            statement.setDate(1, Date.valueOf(dateRetourEffective));
            statement.setInt(2, idEmprunt);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Retour enregistré avec succès !");
            } else {
                System.out.println("Aucun emprunt trouvé avec cet identifiant !");
            }
        }
    }
    // Méthode pour calculer les pénalités en cas de retard
    public int calculatePenalty(int idEmprunt, int montantParJour) throws SQLException{
        String sql = "SELECT date_retour_prevue, date_retour_effective FROM emprunts WHERE id_emprunts = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, idEmprunt);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        LocalDate dateRetourPrevue = resultSet.getDate("date_retour_prevue").toLocalDate();
                        LocalDate dateRetourEffective = resultSet.getDate("date_retour_effective") != null ?
                                resultSet.getDate("date_retour_effective").toLocalDate(): null;

                        if (dateRetourEffective != null && dateRetourEffective.isAfter(dateRetourPrevue)){
                            long daysLate = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
                            return (int) (daysLate * montantParJour);
                        }
                    }
                }
        }
        return 0; //pas de pénalité si pas de retard ou si la date effective est nulle.
    }
    // Méthode pour recupérer tous les emprunts
    public List<Emprunt> getAllEmprunts() throws SQLException{
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM emprunts";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                int idEmprunt = resultSet.getInt("id_emprunts");
                int membreId = resultSet.getInt("membre_id");
                int livreId = resultSet.getInt("livre_id");
                LocalDate dateEmprunt = resultSet.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetourPrevue = resultSet.getDate("date_retour_prevue").toLocalDate();
                LocalDate dateRetourEffective = resultSet.getDate("date_retour_effective") != null ?
                        resultSet.getDate("date_retour_effective").toLocalDate() : null;

                Emprunt emprunt = new Emprunt(idEmprunt, membreId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective);
                emprunts.add(emprunt);
            }
        }
        return emprunts;
    }
}
