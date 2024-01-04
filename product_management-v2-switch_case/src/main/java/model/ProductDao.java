package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
	
	public Connection init ()
	{
		Connection cnx=null;
		String url = "jdbc:mysql://localhost:3306/product_db";
		String user="root";
		String password="";
		try {
			cnx= DriverManager.getConnection(url, user, password);
			System.out.println("Database connection successful!");

		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ ": "+e.getMessage());
			System.out.println("Pas de connexion!");

		}
		return cnx;
	}
	
	
	public ArrayList<Product> getProducts() throws SQLException
	{
		ArrayList<Product> products = new ArrayList<Product>();
		
		Connection cnx = this.init();
		java.sql.Statement stm = cnx.createStatement();
		String sql = "select * from product";
		ResultSet rs= stm.executeQuery(sql);
		while (rs.next())
			try {
				products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return products ;
	}
	
	
	// Cette méthode ajoute un produit à la base de données.
	public int addProduct(Product product) throws SQLException {
	    // Préparation de la requête SQL pour l'insertion produit.
	    
	    String sqlInsert = "INSERT INTO product (name, description, price) VALUES (?, ?, ?)";
	    // Établissement de la connexion à la base de données.
	    Connection conx = this.init();
	    // Création de l'objet PreparedStatement pour exécuter la requête.
	    PreparedStatement pstm = conx.prepareStatement(sqlInsert);
	    
	    // Attribution des valeurs aux paramètres de la requête.
	    pstm.setString(1, product.getName());
	    pstm.setString(2, product.getDescription());
	    pstm.setString(3, product.getPrice());
	    
	    // Exécution de la requête et récupération du nombre de lignes affectées.
	    int rowsAffected = pstm.executeUpdate();
	    // Retourne le nombre de lignes affectées par l'insertion.
	    return rowsAffected;
	}
	
	
	 // Méthode pour supprimer un produit par son ID
    public int deleteProduct(int id) throws SQLException {
    	String sqlDelete = "DELETE FROM product WHERE id = ?";
        try (Connection conx = this.init();
             PreparedStatement pstm = conx.prepareStatement(sqlDelete)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate();
        }
    }
    
    
    // Méthode pour récupérer un product par son ID
    public Product getProductById(Integer id) throws SQLException {
        String sqlQuery = "SELECT * FROM product WHERE id = ?";
        try (Connection conx = this.init();
             PreparedStatement pstm = conx.prepareStatement(sqlQuery)) {

            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), 
                                        rs.getString("description"), rs.getString("price"));
                } else {
                    return null;
                }
            }
        }
    }
    
    
    // Méthode pour mettre à jour un product
    public int updateProduct(Product product) throws SQLException {
	    String sqlUpdate = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";

        try (Connection conx = this.init();
        		PreparedStatement pstm = conx.prepareStatement(sqlUpdate)) {
        	
    	    pstm.setString(1, product.getName());
    	    pstm.setString(2, product.getDescription());
    	    pstm.setString(3, product.getPrice());
    	    pstm.setInt(4, product.getId());
            
    	    int rowsAffected = pstm.executeUpdate();

            return rowsAffected;
        }
    }
	

}
