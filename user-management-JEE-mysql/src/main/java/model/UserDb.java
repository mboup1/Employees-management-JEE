package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDb {
	
	public Connection init ()
	{
		Connection cnx=null;
		String url = "jdbc:mysql://localhost:3306/user_jee_db";
		String user="root";
		String password="";
		try {
			cnx= DriverManager.getConnection(url, user, password);
//			System.out.println("Database connection successful!");

		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ ": "+e.getMessage());
			System.out.println("Pas de connexion!");

		}
		return cnx;
	}
	
	
	public ArrayList<User> getUsers() throws SQLException
	{
		ArrayList<User> users = new ArrayList<User>();
		
		Connection cnx = this.init();
		Statement stm = cnx.createStatement();
		String sql = "select * from user";
		ResultSet rs= stm.executeQuery(sql);
		while (rs.next())
			try {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return users ;
	}
	
	// Cette méthode ajoute un user à la base de données.
	public int addUser(User user) throws SQLException {
	    // Préparation de la requête SQL pour l'insertion user.
	    
	    String sqlInsert = "INSERT INTO user (firstname, lastname, email) VALUES (?, ?, ?)";
	    // Établissement de la connexion à la base de données.
	    Connection conx = this.init();
	    // Création de l'objet PreparedStatement pour exécuter la requête.
	    PreparedStatement pstm = conx.prepareStatement(sqlInsert);
	    
	    // Attribution des valeurs aux paramètres de la requête.
	    pstm.setString(1, user.getFirstname());
	    pstm.setString(2, user.getLastname());
	    pstm.setString(3, user.getEmail());
	    
	    // Exécution de la requête et récupération du nombre de lignes affectées.
	    int rowsAffected = pstm.executeUpdate();
	    // Retourne le nombre de lignes affectées par l'insertion.
	    return rowsAffected;
	}
	
	
	 // Méthode pour supprimer un user par son ID
    public int deleteUser(int id) throws SQLException {
    	String sqlDelete = "DELETE FROM user WHERE idUser = ?";
        try (Connection conx = this.init();
             PreparedStatement pstm = conx.prepareStatement(sqlDelete)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate();
        }
    }
    
    
    // Méthode pour récupérer un user par son ID
    public User getUserById(Integer idUser) throws SQLException {
        String sqlQuery = "SELECT * FROM user WHERE idUser = ?";
        try (Connection conx = this.init();
             PreparedStatement pstm = conx.prepareStatement(sqlQuery)) {

            pstm.setInt(1, idUser);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("idUser"), rs.getString("firstname"), 
                                        rs.getString("lastname"), rs.getString("email"));
                } else {
                    return null;
                }
            }
        }
    }
    
    
    // Méthode pour mettre à jour un user
    public int updateUser(User user) throws SQLException {
	    String sqlUpdate = "UPDATE user SET firstname = ?, lastname = ?, email = ? WHERE idUser = ?";

        try (Connection conx = this.init();
        		PreparedStatement pstm = conx.prepareStatement(sqlUpdate)) {
        	
    	    pstm.setString(1, user.getFirstname());
    	    pstm.setString(2, user.getLastname());
    	    pstm.setString(3, user.getEmail());
    	    pstm.setInt(4, user.getIdUser());
            
    	    int rowsAffected = pstm.executeUpdate();

            return rowsAffected;
        }
    }

}
