package controladores;

import conexion.conexion;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class DeleteController {

    private JdbcTemplate jdbcTemplate;

    public DeleteController() {
        conexion con = new conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping("delete.htm")
    public ModelAndView home(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        this.jdbcTemplate.update(
                "delete from usuario "
                + "where "
                + "idUsuario=? ",
                id);
        return new ModelAndView("redirect:/home.htm");
    }
}
