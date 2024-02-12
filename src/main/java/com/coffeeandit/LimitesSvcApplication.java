package com.coffeeandit;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.repository.LimiteDiarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = LimiteDiarioRepository.class)
@EntityScan(basePackageClasses = LimiteDiario.class)
public class LimitesSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitesSvcApplication.class, args);

		/*
		String url = "jdbc:postgresql://10.89.1.100:5432/limites";
		String usuario = "limite";
		String senha = "limite";
		try {
			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException e) {
			// Erro caso o driver JDBC não foi instalado
			e.printStackTrace();
		} catch (SQLException e) {
			// Erro caso haja problemas para se conectar ao banco de dados
			e.printStackTrace();
		}
		 */
	}

}
