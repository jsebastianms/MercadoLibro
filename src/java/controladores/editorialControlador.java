/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Usuario
 */
@Controller
public class editorialControlador 
{
    private JdbcTemplate template;

    public editorialControlador() 
    {
        conexion con = new conexion();
        template=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("editorial.htm")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from editorial order by idEditorial asc";
        List datos = this.template.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("editorial");
        return mav;
    }
    
    
}
