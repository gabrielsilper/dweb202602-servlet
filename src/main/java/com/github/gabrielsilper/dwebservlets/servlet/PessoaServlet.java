package com.github.gabrielsilper.dwebservlets.servlet;

import com.github.gabrielsilper.dwebservlets.model.City;
import com.github.gabrielsilper.dwebservlets.model.Person;
import com.github.gabrielsilper.dwebservlets.model.State;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PessoaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchNameInputParameter = req.getParameter("search-name-input");
        String actionParameter = req.getParameter("action");

        if (actionParameter == null) {
            actionParameter = "listar";
        }

        List<Person> persons = this.getPersonsList(searchNameInputParameter);

        String personsHeaderContent = "";
        String personsMain = "";

        if (actionParameter.equalsIgnoreCase("listar")) {
            personsHeaderContent = this.getPersonHeaderContent();
            personsMain = this.getPersonsTable(persons);
        } else if (actionParameter.equalsIgnoreCase("cadastrar")) {
            personsMain = this.getFormCadastrarPessoa();
        }


        resp.setContentType("text/html");
        resp.getWriter().printf("""
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Pessoas</title>
                </head>
                <body>
                    <h1>Gerenciamento de Pessoas!</h1>
                    <header>
                        %s
                    </header>
                    %s
                </body>
                </html>
                \s""", personsHeaderContent, personsMain);
    }

    private String getPersonHeaderContent() {
        return """
                <form method="get" action="pessoa">
                    <div>
                        <label for="search-name-input">Filtra por nome:</label>
                        <input type="text" id="search-name-input" name="search-name-input" placeholder="Digite o nome que deseja filtrar..."/>
                    </div>
                    <button type="submit">Filtrar</button>
                </form>
                <a href="pessoa?action=cadastrar">Cadastrar Pessoa<a>
                """;
    }

    private String getPersonsTable(List<Person> persons) {
        String personDataFieldsRows = this.getPersonDataFieldsRows(persons);

        return String.format("""
                    <table border="1">
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

    private String getFormCadastrarPessoa() {
        return """
                <form method="post" action="pessoa">
                    <div>
                        <label for="name-input">Nome:</label>
                        <input type="text" id="name-input" name="name-input" placeholder="Nome da Pessoa"/>
                    </div>
                    <div>
                        <label for="phone-input">Telefone:</label>
                        <input type="text" id="phone-input" name="phone-input" placeholder="Telefone da Pessoa (ex. 92 99999-9999)"/>
                    </div>
                    <div>
                        <label for="email-input">Email:</label>
                        <input type="text" id="email-input" name="email-input" placeholder="Email da pessoa"/>
                    </div>
                    <div>
                        <label for="city-input">Cidade:</label>
                        <input type="text" id="city-input" name="city-input" placeholder="Cidade da pessoa..."/>
                    </div>
                    <div>
                        <label for="state-input">Estado:</label>
                        <input type="text" id="state-input" name="state-input" placeholder="Estado da cidade..."/>
                    </div>
                    <button type="submit">Cadastrar</button>
                </form>
                """;
    }

    private List<Person> getPersonsList(String nameFilter) {
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

        if (nameFilter == null || nameFilter.isEmpty()) {
            return List.of(person1, person2, person3);
        }

        return Stream.of(person1, person2, person3)
                .filter(person -> person.getName().toLowerCase().contains(nameFilter.toLowerCase()))
                .toList();
    }
}
