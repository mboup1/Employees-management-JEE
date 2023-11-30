package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDb {
	
	public Connection init ()
	{
		Connection cnx=null;
		String url = "jdbc:mysql://localhost:3306/employee_db";
		String user="root";
		String password="";
		try {
			cnx= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ ": "+e.getMessage());
		}
		return cnx;
	}
	
	public ArrayList<Employee> getEmployees() throws SQLException
	{
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		Connection cnx = this.init();
		Statement stm = cnx.createStatement();
		String sql = "select * from employee";
		ResultSet rs= stm.executeQuery(sql);
		while (rs.next())
			try {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return employees ;
	}
	
	// Cette méthode ajoute un employé à la base de données.
	public int addEmployee(Employee employee) throws SQLException {
	    // Préparation de la requête SQL pour l'insertion d'employé.
	    
	    String sqlInsert = "INSERT INTO employee (firstName, lastName, email) VALUES (?, ?, ?)";
	    // Établissement de la connexion à la base de données.
	    Connection conx = this.init();
	    // Création de l'objet PreparedStatement pour exécuter la requête.
	    PreparedStatement pstm = conx.prepareStatement(sqlInsert);
	    
	    // Attribution des valeurs aux paramètres de la requête.
	    pstm.setString(1, employee.getFirstName());
	    pstm.setString(2, employee.getLastName());
	    pstm.setString(3, employee.getEmail());
	    
	    // Exécution de la requête et récupération du nombre de lignes affectées.
	    int rowsAffected = pstm.executeUpdate();
	    // Retourne le nombre de lignes affectées par l'insertion.
	    return rowsAffected;
	}
	
	 // Méthode pour supprimer un employé par son ID
    public int deleteEmployee(int id) throws SQLException {
    	String sqlDelete = "DELETE FROM employee WHERE idEmployee = ?";
        try (Connection conx = this.init();
             PreparedStatement pstm = conx.prepareStatement(sqlDelete)) {

            pstm.setInt(1, id);
            return pstm.executeUpdate();
        }
    }

}
