/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Autor;
import modelos.ValidacionesAutor;
import conexion.conexion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author camilo
 */
@Controller
@RequestMapping("addAutor.htm")
public class addAutorControlador {

    ValidacionesAutor AutorValidar;
    private JdbcTemplate jdbcTemplate;

    public addAutorControlador() {
        this.AutorValidar = new ValidacionesAutor();
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addAutor");
        mav.addObject("autor", new Autor());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("autor") Autor a,
            BindingResult result,
            SessionStatus status
    ) {
        this.AutorValidar.validate(a, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("addAutor");
            mav.addObject("autor", new Autor());
            return mav;
        } else {
            this.jdbcTemplate.update(
                    "insert into autor (nombre,descripcion,recomendado) values (?,?,?)",
                    a.getNombre(), a.getDescripcion(),a.getRecomendado()
            );
            return new ModelAndView("redirect:/autor.htm");
        }

    }
}
