/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import modelos.Editorial;
import modelos.ValidacionesEditorial;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("editEditorial.htm")
public class editEditorialControlador 
{
    private ValidacionesEditorial validar;
    private JdbcTemplate template;

    public editEditorialControlador() 
    {
        validar=new ValidacionesEditorial();
        conexion con=new conexion();
        template=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("idEditorial"));
        Editorial datos=selectEditorial(id); 
        mav.setViewName("editEditorial");
        mav.addObject("editorial", new Editorial(id, datos.getNombre(), datos.getCorreoContacto(), datos.getTelefonoContacto(), datos.getDireccion()));
        return mav;
    } 

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("editorial") Editorial e,BindingResult result,SessionStatus status,HttpServletRequest request)
    {
        validar.validate(e, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("idEditorial"));
            Editorial datos = this.selectEditorial(id);
            mav.setViewName("editEditorial");
            mav.addObject("editorial", new Editorial(id, datos.getNombre(), datos.getCorreoContacto(), datos.getTelefonoContacto(), datos.getDireccion()));
            return mav;
        } else {
            int id = Integer.parseInt(request.getParameter("idEditorial"));
            this.template.update("update editorial "
                    + "set nombre=?,"
                    + " correoContacto=?,telefonoContacto=?,direccion=?"
                    + "where "
                    + "idEditorial=? ",
                    e.getNombre(), e.getCorreoContacto(), e.getTelefonoContacto(),e.getDireccion(),id);
            return new ModelAndView("redirect:/editorial.htm");
        }
        

    }
    private Editorial selectEditorial(int id)
    {
        final Editorial editorial = new Editorial();
        String quer = "SELECT * FROM editorial WHERE idEditorial='" + id + "'";
        return (Editorial) template.query(quer, new ResultSetExtractor<Editorial>()
        {
            @Override
            public Editorial extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    editorial.setNombre(rs.getString("nombre"));
                    editorial.setCorreoContacto(rs.getString("correoContacto"));
                    editorial.setTelefonoContacto(rs.getString("telefonoContacto"));
                    editorial.setDireccion(rs.getString("direccion"));
                }
                return editorial;
            }
        }
        );
    }
    
    
}
