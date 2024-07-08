package ar.com.code24101.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.code24101.domain.Movie;
import ar.com.code24101.dto.MovieDTO;
import ar.com.code24101.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ActualizarMovieController")
public class ActualizarMovieController extends HttpServlet {

    protected void doPut(
        HttpServletRequest req,//aca viene todo desde el front
        HttpServletResponse resp//aca respondemos al front
    ) throws ServletException, IOException {

        //el json que viene, se atrapa así:
        String json = req.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
            
        //System.out.println(json);

        //en java manejo objetos
        //usando jackson: pasamos de texto a objetos
        ObjectMapper mapper = new ObjectMapper();

        MovieDTO movieDto = mapper.readValue(json, MovieDTO.class);

        MovieService service = new MovieService();

        System.out.println(movieDto);

        service.actualizar(movieDto);

        Movie actualizado = service.obtener(movieDto.getId());
        String jsonResponse = mapper.writeValueAsString(actualizado);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(jsonResponse);

    }
}
