package com.accenture.demo.JerseyDemoProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlianRepository {
	public List<Alian> alian;

	Connection con = null;
	Statement stmt = null;
	ResultSet res = null;
	PreparedStatement pstmt = null;

	public AlianRepository() {
		String url = "jdbc:mysql://localhost:3306/jerseyproject";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to Datatabase...........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Alian> getAlians() {
		List<Alian> alianList = new ArrayList<Alian>();
		try {
			stmt = con.createStatement();
			String sql = "select * from empdata";
			res = stmt.executeQuery(sql);
			while (res.next()) {
				Alian alian = new Alian();
				alian.setName(res.getString(1));
				alian.setPoints(res.getInt(2));
				alianList.add(alian);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alianList;
	}

	public Alian getAlian(String name) {
		Alian alian = new Alian();
		try {
			String sql = "select * from empdata where name=" + "'" + name + "'";
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				alian.setName(res.getString(1));
				alian.setPoints(res.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alian;

	}

	public void createAlian(Alian alian2) {
		try {
			String sql = "insert into empdata(name, points) values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, alian2.getName());
			pstmt.setInt(2, alian2.getPoints());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAlian(Alian alian2) {
		try {
			String sql = "update empdata set name=? where points=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, alian2.getName());
			pstmt.setInt(2, alian2.getPoints());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteAlian(String name) {
		try {
			String sql = "delete from empdata where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
