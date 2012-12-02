package base;



import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;


/**
 *
 * @author daniel
 */
public class Dao {
  
  /**
   * sesion en base de datos , estatica para solo mantener una sesion
   */
  protected static Session sesion;

  /**
   * @return the sesion
   */
  public static Session getSesion() {
    return sesion;
  }
  
  
  public Dao() {
    sesion = sesion == null ? HibernateUtil.getSessionFactory().openSession() : sesion;
  }

  /**
   * regresa el contenido en una tabla de la base en forma de lista
   * @param tabla , tabla deseada
   * @return 
   */
  public List getTabla(Class tabla) {
    return getSesion().createCriteria(tabla).list();
  }
  
  /**
   * guarda el objeto en base de datos y regresa el id con el que se guardo
   * @param objeto a guardar en base
   * @return 
   */
  public Serializable guardar(Object objeto) {
    Transaction transaction = getSesion().beginTransaction();
    Serializable id = getSesion().save(objeto);
    transaction.commit();
    return id;
  }
  
  public static void main(String[] args) {
        Dao dao=new Dao();
    }

  
}
