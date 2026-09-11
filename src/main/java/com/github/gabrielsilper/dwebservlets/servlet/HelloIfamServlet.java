package com.github.gabrielsilper.dwebservlets.servlet;

import com.github.gabrielsilper.dwebservlets.model.City;
import com.github.gabrielsilper.dwebservlets.model.Person;
import com.github.gabrielsilper.dwebservlets.model.State;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HelloIfamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");

        City cityManaus = new City();
        cityManaus.setName("Manaus");
        cityManaus.setState(stateAmazonas);

        Person person = new Person(
                "Gabriel",
                "gabriel@test.com",
                "92 99999-9999",
                cityManaus
        );

        String personsTable = this.getPersonsTable(person);

        resp.setContentType("text/html");
        resp.getWriter().printf("""
                        <!DOCTYPE html>
                        <html lang="pt-BR">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1">
                            <title>Olá IFAM!</title>
                        </head>
                        <body>
                            <h1>Olá IFAM!</h1>
                            %s
                        </body>
                        </html>
                """, personsTable);
    }

    private String getPersonsTable(Person person) {
        String personFieldsRows = this.getPersonFieldsRows(person);

        return String.format("""
                    <table>
                        <caption>Tabela de pessoas</caption>
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Cidade</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            %s
                        </tbody>
                    </table>
                """, personFieldsRows);
    }

    String getPersonFieldsRows(Person person) {
        return String.format("""
                    <tr>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                    </tr>
                """,
                person.getName(), person.getPhone(), person.getEmail(),
                person.getCity().getName(), person.getCity().getState().getName());
    }
}
