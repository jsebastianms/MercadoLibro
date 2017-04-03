package controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import modelos.Autor;
import modelos.ValidacionesAutor;
import conexion.conexion;
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
 * @author camilo
 */
@Controller
@RequestMapping("editAutor.htm")
public class editAutorControlador {
    ValidacionesAutor AutorValidar;
    private JdbcTemplate jdbcTemplate;

    public editAutorControlador() {
        this.AutorValidar = new ValidacionesAutor();
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        int id= Integer.parseInt(request.getParameter("id"));
        Autor datos=this.selectUsuario(id);
        mav.setViewName("editAutor");
        mav.addObject("autor",new Autor(id, datos.getNombre(),datos.getDescripcion(),datos.getRecomendado()));
        return mav;
    }
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("autor") Autor u,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.AutorValidar.validate(u, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
        int id= Integer.parseInt(request.getParameter("id"));
        Autor datos=this.selectUsuario(id);
            mav.setViewName("editAutor");
            mav.addObject("autor",new Autor(id, datos.getNombre(),datos.getDescripcion(),datos.getRecomendado()));
            return mav;
        }else
        {
           int id= Integer.parseInt(request.getParameter("id"));
        this.jdbcTemplate.update(
                    "update autor "
                + "set nombre=?,"
                + "descripcion=?,"
                + "recomendado=?"      
                + "where "
                + "idAutor=? ",
         u.getNombre(),u.getDescripcion(),u.getRecomendado(),id);
         return new ModelAndView("redirect:/autor.htm");
        }
       
    }
    public Autor selectUsuario(int id) 
    {
        final Autor user = new Autor();
        String quer = "SELECT * FROM autor WHERE idAutor=" +id;
        return (Autor) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Autor>() 
            {
                public Autor extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        user.setNombre(rs.getString("nombre"));
                        user.setDescripcion(rs.getString("descripcion"));
                        user.setRecomendado(rs.getString("recomendado"));
                    }
                    return user;
                }


            }
        );
    }
}

