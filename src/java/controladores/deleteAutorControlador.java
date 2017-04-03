/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.servlet.http.HttpServletRequest;
import conexion.conexion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author camilo
 */
public class deleteAutorControlador {
    private JdbcTemplate jdbcTemplate;
    public deleteAutorControlador()
    {
        conexion con=new conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    
    @RequestMapping("deleteAutor.htm")
    public ModelAndView home(HttpServletRequest request) 
    {
        int id=Integer.parseInt(request.getParameter("id"));
        this.jdbcTemplate.update(
                    "delete from autor "
                + "where "
                + "idAutor=? ",
        id);
        return new ModelAndView("redirect:/autor.htm");
    }
}
