/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Usuario
 */
public class deleteEditorialControlador 
{
    private JdbcTemplate template;

    public deleteEditorialControlador() 
    {
        conexion con=new conexion();
        template=new JdbcTemplate(con.conectar());        
    }
    
    @RequestMapping("deleteEditorial.htm")
    public ModelAndView home(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("idEditorial"));
        template.update("delete from editorial where idEditorial=?",id);
        return new ModelAndView("redirect:/editorial.htm");
    }
    
    
}
