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
                            <h2>Pessoa</h2>
                            <p>Nome: %s</p>
                            <p>Telefone: %s</p>
                            <p>Email: %s</p>
                            <p>Cidade: %s</p>
                            <p>Estado: %s</p>
                        </body>
                        </html>
                """,
                person.getName(), person.getPhone(), person.getEmail(),
                person.getCity().getName(), person.getCity().getState().getName());
    }
}
