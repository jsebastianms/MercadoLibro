
package controladores;

import conexion.conexion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
public class HomeController 
{
    private JdbcTemplate jdbcTemplate;
    
    public HomeController()
    {
        conexion con=new conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("home.htm")
    public ModelAndView home()
    {
        ModelAndView mav=new ModelAndView();
        String sql="select * from usuario";
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        mav.setViewName("home");
        return mav;
    }
}
