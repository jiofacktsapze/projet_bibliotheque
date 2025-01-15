import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            LivreDAO livreDAO = new LivreDAO(connection);
            MembreDAO membreDAO = new MembreDAO(connection);
            EmpruntDAO empruntDAO = new EmpruntDAO(connection);

            Scanner sc = new Scanner(System.in);
            boolean quitter = false;

            while (!quitter) {
                System.out.println("=== MENU ===");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Modifier un livre");
                System.out.println("3. Rechercher un livre");
                System.out.println("4. Afficher tous les livres");
                System.out.println("5. Inscrire un nouveau membre");
                System.out.println("6. Rechercher un membre par nom");
                System.out.println("7. Supprimer un membre");
                System.out.println("8. Afficher la liste des membres");
                System.out.println("9. Enregistrer un emprunt");
                System.out.println("10. Gérer le retour d'un livre");
                System.out.println("11. Calculer les pénalités");
                System.out.println("12. Afficher la liste des emprunts");
                System.out.println("13. Quitter");
                System.out.print("Votre Choix: ");

                int choix = sc.nextInt();
                sc.nextLine();

                switch (choix) {

                    case 1:
                        System.out.print("Titre: ");
                        String titre = sc.nextLine();
                        System.out.print("Auteur: ");
                        String auteur = sc.nextLine();
                        System.out.print("Catégorie: ");
                        String categorie = sc.nextLine();
                        System.out.print("Nombre d'exemplaires: ");
                        int nombreExemplaires = sc.nextInt();

                        Livre livre = new Livre(0, titre, auteur, categorie, nombreExemplaires);
                        livreDAO.addLivre(livre);
                        System.out.println("Livre ajouté avec succès!");
                        break;

                    case 2:
                        System.out.print("Identifiant du livre à modifier: ");
                        int idLivre = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nouveau titre: ");
                        String newTitre = sc.nextLine();
                        System.out.print("Nouveau auteur: ");
                        String newAuteur = sc.nextLine();
                        System.out.print("Nouvelle catégorie: ");
                        String newCategorie = sc.nextLine();
                        System.out.print("Nouveau exemplaire: ");
                        int newNombreExemplaires = sc.nextInt();

                        Livre majLivre = new Livre(idLivre, newTitre, newAuteur, newCategorie, newNombreExemplaires);
                        livreDAO.updateLivre(majLivre);
                        System.out.println("Livre modifié avec succès !");
                        break;

                    case 3:
                        System.out.println("Rechercher un livre par: (1. Titre, 2. Catégorie");
                        int critere = sc.nextInt();
                        sc.nextLine();

                        if (critere == 1) {
                            System.out.print("Titre: ");
                            String searchTitre = sc.nextLine();
                            List<Livre> livreByTitre = livreDAO.searchLivreByTitre(searchTitre);
                            livreByTitre.forEach(System.out::println);
                        } else if (critere == 2) {
                            System.out.print("Catégorie: ");
                            String searchCategorie = sc.nextLine();
                            List<Livre> livreByCategorie = livreDAO.searchLivreByCategorie(searchCategorie);
                            livreByCategorie.forEach(System.out::println);
                        } else {
                            System.out.println("Critère de recherche invalide.");
                        }
                        break;

                    case 4:
                        List<Livre> livres = livreDAO.afficherDetailsLivres();
                        if (livres.isEmpty()) {
                            System.out.println("Aucun livre disponible.");
                        } else {
                            livres.forEach(System.out::println);
                        }
                        break;
                    case 5:
                        System.out.print("Nom: ");
                        String nom = sc.nextLine();
                        System.out.print("Prenom: ");
                        String prenom = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        Membre membre = new Membre(0, nom, prenom, email, LocalDate.now());
                        membreDAO.addMembre(membre);
                        System.out.println("Membre inscrit avec succès !");
                        break;

                    case 6:
                        System.out.print("Nom du membre: ");
                        String searchNom = sc.nextLine();
                        List<Membre> membreByName = membreDAO.searchMembreByName(searchNom);
                        membreByName.forEach(System.out::println);
                        break;

                    case 7:
                        System.out.print("Entrez l'identifiant du membre à supprimer: ");
                        int id= sc.nextInt();
                        membreDAO.deleteMembre(id);
                        System.out.println("Membre supprimé avec succès !");
                        break;

                    case 8:
                        List<Membre> membres = membreDAO.getAllMembres();
                        if (membres.isEmpty()) {
                            System.out.println("Aucun membre inscrit.");
                        } else {
                            membres.forEach(System.out::println);
                        }
                        break;

                    case 9:
                        System.out.print("Id du membre: ");
                        int membreId = sc.nextInt();
                        System.out.println("Id du livre: ");
                        int livreId = sc.nextInt();

                        Emprunt emprunt = new Emprunt(0, livreId, membreId, LocalDate.now(), LocalDate.now().plusDays(14), null);
                        empruntDAO.addEmprunt(emprunt);
                        System.out.println("Emprunt enregistré avec succès !");
                        break;

                    case 10:// Retourner un livre
                        System.out.print("ID de l'emprunt : ");
                        int idEmprunt = sc.nextInt();
                        System.out.print("Date de retour effective (yyyy-mm-dd) : ");
                        String dateRetour = sc.next();
                        LocalDate dateRetourEffective = LocalDate.parse(dateRetour);
                        empruntDAO.returnLivre(idEmprunt, dateRetourEffective);
                        break;

                    case 11: // Calculer une pénalité
                        System.out.print("ID de l'emprunt : ");
                        idEmprunt = sc.nextInt();
                        System.out.print("Montant par jour de retard (en FCFA) : ");
                        int montantParJour = sc.nextInt();
                        try {
                            int penalite = empruntDAO.calculatePenalty(idEmprunt, montantParJour);
                            if (penalite > 0){
                                System.out.println("Pénalité calculée : " + penalite + " FCFA.");
                            } else {
                                System.out.println("Aucune pénalité. Pas de retard enregistré ou emprunt introuvable.");
                            }
                        } catch (SQLException e){
                            System.out.println("Erreur lors du calcul de la pénalité : "+ e.getMessage());
                        }
                        break;

                    case 12:
                        List<Emprunt> emprunts = empruntDAO.getAllEmprunts();
                        if (emprunts.isEmpty()){
                            System.out.println("Aucun emprunt en cours.");
                        } else {
                            emprunts.forEach(System.out::println);
                        }
                        break;

                    case 13:
                        quitter = true;
                        System.out.println("Au revoir!");
                        break;

                    default:
                        System.out.println("Choix invalide, veuillez faire un autre choix");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}