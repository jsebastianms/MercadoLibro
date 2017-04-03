
package controladores;

import conexion.conexion;
import modelos.Usuarios;
import modelos.UsuariosValidar;
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


@Controller
@RequestMapping("edit.htm")
public class EditController 
{
    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
     
    
    public EditController() 
    {
        this.usuariosValidar=new UsuariosValidar();
        conexion con=new conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        int id= Integer.parseInt(request.getParameter("idUsuario"));
        Usuarios datos=this.selectUsuario(id);
        mav.setViewName("edit");
        mav.addObject("usuarios",new Usuarios(id, datos.getNombre(),datos.getApellido(),datos.getCorreo(),datos.getContrasena(),datos.getNomUsuario(),datos.getTelefono(),datos.getTipo()));
        return mav;
    }
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("usuarios") Usuarios u,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.usuariosValidar.validate(u, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
        int id= Integer.parseInt(request.getParameter("idUsuario"));
        Usuarios datos=this.selectUsuario(id);
            mav.setViewName("edit");
            mav.addObject("usuarios",new Usuarios(id, datos.getNombre(),datos.getApellido(),datos.getCorreo(),datos.getContrasena(),datos.getNomUsuario(),datos.getTelefono(),datos.getTipo()));
            return mav;
        }else
        {
           int id= Integer.parseInt(request.getParameter("idUsuario"));
        this.jdbcTemplate.update(
                    "update usuario "
                + "set nombre=?,"
                + "apellido=?,"
                + "correo=?,"
                + "contrasena=?,"
                + "nomUsuario=?,"
                + "telefono=?,"
                + "tipo=?"           
                + "where "
                + "idUsuario=? ",
         u.getNombre(),u.getApellido(),u.getCorreo(),u.getContrasena(),u.getNomUsuario(),u.getTelefono(),u.getTipo(),id);
         return new ModelAndView("redirect:/home.htm");
        }
       
    }
    public Usuarios selectUsuario(int id) 
    {
        final Usuarios user = new Usuarios();
        String quer = "SELECT * FROM usuario WHERE idUsuario=" +id;
        return (Usuarios) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Usuarios>() 
            {
                public Usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        user.setNombre(rs.getString("nombre"));
                        user.setApellido(rs.getString("apellido"));
                        user.setCorreo(rs.getString("correo"));
                        user.setContrasena(rs.getString("contrasena"));
                        user.setNomUsuario(rs.getString("nomUsuario"));
                        user.setTelefono(rs.getString("telefono"));
                        user.setTipo(rs.getString("tipo"));
                    }
                    return user;
                }


            }
        );
    }
}

