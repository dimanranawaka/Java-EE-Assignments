package lk.ijse.json.servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/pages/customer"})
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ass1", "root", "1234");

            PreparedStatement statement = connection.prepareStatement("select * from customer");

            ResultSet resultSet = statement.executeQuery();

            resp.addHeader("Content-Type","application/json");

            JsonArrayBuilder allCustomer = Json.createArrayBuilder();

            JsonObjectBuilder customerObject = Json.createObjectBuilder();

            while (resultSet.next()){

                String id = resultSet.getString(1);

                String name = resultSet.getString(2);

                String address = resultSet.getString(3);

                allCustomer.add(customerObject.build());

            }

            resp.getWriter().print(customerObject.build());

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
