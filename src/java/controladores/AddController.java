
package controladores;

import conexion.conexion;
import modelos.Usuarios;
import modelos.UsuariosValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {
    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() 
    {
        this.usuariosValidar=new UsuariosValidar();
        conexion con=new conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form()
    {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("add");
        mav.addObject("usuarios",new Usuarios());
        return mav;
    }
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("usuarios") Usuarios u,
                BindingResult result,
                SessionStatus status
        )
    {
        this.usuariosValidar.validate(u, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
            mav.setViewName("add");
            mav.addObject("usuarios",new Usuarios());
            return mav;
        }else
        {
        this.jdbcTemplate.update
        (
        "insert into usuario (nombre,apellido,correo,contrasena,nomUsuario,telefono,tipo) values (?,?,?,?,?,?,?)",
         u.getNombre(),u.getApellido(),u.getCorreo(),u.getContrasena(),u.getNomUsuario(),u.getTelefono(),u.getTipo()
        );
         return new ModelAndView("redirect:/home.htm");
        }
       
    }
}




