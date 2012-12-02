package beans;

import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * Clase que nos ayuda a buscar Beans
 * @author Daniel
 */
public class Beans {
    /**
     * metodo que limpia beans
     * @param beans 
     */
    public static void limpiaBeans(String ... beans){
        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().
                                        getExternalContext().getSessionMap();
        for(String bean:beans){
            sessionMap.remove(bean);
        }
    }
    
    public static Beans getBean(String nombre){
        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().
                                        getExternalContext().getSessionMap();
        return (Beans)sessionMap.get(nombre);
    }
    
    public static void ponerSesion(String nombre , Beans bean){
        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().
                                        getExternalContext().getSessionMap();
        sessionMap.put(nombre, bean);
    }
}