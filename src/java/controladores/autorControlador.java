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
public class autorControlador {

    private JdbcTemplate jdbcTemplate;

    public autorControlador() {
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping("autor.htm")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from autor order by idAutor desc";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("autor");
        return mav;
    }

}
