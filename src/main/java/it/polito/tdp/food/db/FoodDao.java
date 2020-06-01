package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public void listAllFoods( Map<Integer,Food> map ){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
		
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Food f = new Food(res.getInt("food_code"),
							res.getString("display_name")
							);
					map.put(res.getInt("food_code"), f);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
		

		} catch (SQLException e) {
			e.printStackTrace();
			
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Food> listFoodByPortions(Map<Integer,Food> map, int portion){
		String sql = "select distinct food_code " + 
				" from portion " + 
				" group by food_code " + 
				" having  count(distinct portion_id) = ? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, portion);
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Food f = map.get(res.getInt("food_code"));
					list.add(f);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	
	public List<Adiacenza> listAdiacenze(Map<Integer,Food> map, int portion){
		String sql ="select f1.food_code,f2.food_code , AVG(c.condiment_calories) as tot " + 
				"from food_condiment as f1,food_condiment as f2, condiment as c " + 
				"where f1.condiment_code=f2.condiment_code and f1.food_code>f2.food_code " + 
				"and c.condiment_code=f1.condiment_code " + 
				"and f1.food_code IN (select food_code " + 
				" from portion " + 
				" group by food_code " + 
				" having  count(distinct portion_id) =?) " + 
				"and f2.food_code IN (select food_code " + 
				" from portion " + 
				" group by food_code " + 
				" having  count(distinct portion_id) = ?) " + 
				"group by f1.food_code,f2.food_code ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, portion);
			st.setInt(2, portion);
			
			List<Adiacenza> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Food f1 = map.get(res.getInt("f1.food_code"));
					Food f2 = map.get(res.getInt("f2.food_code"));
					double peso = res.getDouble("tot");
					
					list.add(new Adiacenza(f1, f2, peso));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
}
