import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {
    private Connection connection;
    // Constructeur
    public LivreDAO(Connection connection) {
        this.connection = connection;
    }
    // Méthode pour ajouter un livre
    public void addLivre(Livre livre) throws SQLException {
        String query = "INSERT INTO livres (titre, auteur, categorie, nombre_exemplaires) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getCategorie());
            pstmt.setInt(4, livre.getNombreExemplaires());
            pstmt.executeUpdate();
        }
    }
    public void updateLivre(Livre modifierLivre) throws SQLException {
        String query = "UPDATE livres SET titre = ?, auteur = ?, categorie = ?, nombre_exemplaires = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, modifierLivre.getTitre());
            statement.setString(2, modifierLivre.getAuteur());
            statement.setString(3, modifierLivre.getCategorie());
            statement.setInt(4, modifierLivre.getNombreExemplaires());
            statement.setInt(5, modifierLivre.getLivreId());
            statement.executeUpdate();
        }
    }
    public List<Livre> searchLivreByTitre(String searchTitre) throws SQLException {
        String query = "SELECT * FROM livres WHERE LOWER(titre) LIKE LOWER(?)";
        List<Livre> livres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, "%" + searchTitre + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                livres.add(new Livre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("categorie"),
                        resultSet.getInt("nombre_exemplaires")
                ));
            }
        }
        return livres;
    }
    public List<Livre> searchLivreByCategorie(String searchCategorie) throws SQLException {
        String query = "SELECT * FROM livres WHERE LOWER(categorie) LIKE LOWER(?)";
        List<Livre> livres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, "%" + searchCategorie + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                livres.add(new Livre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("categorie"),
                        resultSet.getInt("nombre_exemplaires")
                ));
            }
        }
        return livres;
    }
    // Méthode pour afficher tous les livres disponible
    public List<Livre> afficherDetailsLivres() throws SQLException {
        String query = "SELECT * FROM livres";
        List<Livre> livres = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombre_exemplaires")
                ));
            }
        }
        return livres;
    }
}
