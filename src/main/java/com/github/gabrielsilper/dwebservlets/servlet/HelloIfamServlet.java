package com.github.gabrielsilper.dwebservlets.servlet;

import com.github.gabrielsilper.dwebservlets.model.City;
import com.github.gabrielsilper.dwebservlets.model.Person;
import com.github.gabrielsilper.dwebservlets.model.State;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HelloIfamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = this.getPersonsList();
        String personsTable = this.getPersonsTable(persons);

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

    private String getPersonsTable(List<Person> persons) {
        String personDataFieldsRows = this.getPersonDataFieldsRows(persons);

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
                """, personDataFieldsRows);
    }

    private String getPersonDataFieldsRows(List<Person> persons) {
        StringBuilder sb = new StringBuilder();

        for (Person person : persons) {
            sb.append("<tr>\n")
                    .append("\t<td>").append(person.getName()).append("</td>\n")
                    .append("\t<td>").append(person.getPhone()).append("</td>\n")
                    .append("\t<td>").append(person.getEmail()).append("</td>\n")
                    .append("\t<td>").append(person.getCity().getName()).append("</td>\n")
                    .append("\t<td>").append(person.getCity().getState().getName()).append("</td>\n")
                    .append("</tr>\n");
        }

        return sb.toString();
    }

    private List<Person> getPersonsList() {
        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");

        City cityManaus = new City();
        cityManaus.setName("Manaus");
        cityManaus.setState(stateAmazonas);

        Person person1 = new Person(
                "Gabriel",
                "gabriel@test.com",
                "92 99999-9999",
                cityManaus
        );

        Person person2 = new Person(
                "Fulano",
                "fulano@test.com",
                "92 99888-8888",
                cityManaus
        );

        Person person3 = new Person(
                "Beltrana",
                "beltrana@test.com",
                "92 99777-7777",
                cityManaus
        );

        return List.of(person1, person2, person3);
    }
}
