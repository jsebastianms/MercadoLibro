/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import modelos.Editorial;
import modelos.ValidacionesEditorial;
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
 * @author Usuario
 */
@Controller
@RequestMapping("addEditorial.htm")
public class addEditorialControlador
{
    private ValidacionesEditorial validar;
    private JdbcTemplate template;

    public addEditorialControlador() 
    {
        validar=new ValidacionesEditorial();
        conexion con=new conexion();
        template=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addEditorial");
        mav.addObject("editorial", new Editorial());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("editorial") Editorial e,BindingResult result,SessionStatus status)
    {
        validar.validate(e, result);
        if(result.hasErrors())
        {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("addEditorial");
            mav.addObject("editorial", new Editorial());
            return mav;
        }
        else
        {
            template.update("insert into editorial (nombre,correoContacto,telefonoContacto,direccion) values (?,?,?,?)"
                            ,e.getNombre(),e.getCorreoContacto(),e.getTelefonoContacto(),e.getDireccion());
            return new ModelAndView("redirect:/editorial.htm");
        }
    }
    
    
    
    
    
}
