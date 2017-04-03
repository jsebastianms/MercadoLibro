package controladores;

import java.util.List;
import conexion.conexion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author camilo
 */
@Controller
public class categoriaControlador {

    private JdbcTemplate jdbcTemplate;

    public categoriaControlador() {
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping("categoria.htm")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from categoria order by idCategoria desc";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("categoria");
        return mav;
    }

}
