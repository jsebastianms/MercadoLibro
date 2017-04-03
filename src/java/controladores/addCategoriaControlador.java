/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Categoria;
import modelos.ValidacionesCategoria;
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
@RequestMapping("addCategoria.htm")
public class addCategoriaControlador {

    ValidacionesCategoria CategoriaValidar;
    private JdbcTemplate jdbcTemplate;

    public addCategoriaControlador() {
        this.CategoriaValidar = new ValidacionesCategoria();
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addCategoria");
        mav.addObject("categoria", new Categoria());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("categoria") Categoria a,
            BindingResult result,
            SessionStatus status
    ) {
        this.CategoriaValidar.validate(a, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("addCategoria");
            mav.addObject("categoria", new Categoria());
            return mav;
        } else {
            this.jdbcTemplate.update(
                    "insert into categoria (nombre,descripcion,influencia) values (?,?,?)",
                    a.getNombre(), a.getDescripcion(),a.getInfluencia()
            );
            return new ModelAndView("redirect:/categoria.htm");
        }

    }
}
