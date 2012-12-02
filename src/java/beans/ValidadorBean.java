package beans;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;



/**
 *
 * @author Daniel
 */
public class ValidadorBean extends Beans{
    
    
   public void valueChangePorcentaje(AjaxBehaviorEvent event){
        UIComponent component = event.getComponent();
        Map<String, Object> attributes = component.getAttributes();
        Set<String> keySet = attributes.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
            String llave=iterator.next();
            System.out.println(llave+" "+attributes.get(llave));
        }
        String valor=(String) sacarValor(component,"value");
        valor=valor.contains("%") ? valor.substring(0,valor.length()-1) : valor;
        System.out.println("el valorsin es "+valor);
        try{
            Double.parseDouble(valor);
        }catch(NumberFormatException num){
            System.out.println("cai en exception");
            valor="000.0000";
        }
        String[] split = valor.split("\\.");
        String valIzq=agregarLongitud("0",3,false,split[0]);
        String valDer= split.length==2 ? agregarLongitud("0",4,true,split[1]) : "0000";
        ponerValor(component, valIzq+"."+valDer+"%");
       
   }
   
   public void valueChangeNumerico(AjaxBehaviorEvent event){
        UIComponent component = event.getComponent();
        
        String valor= sacarValor(component,"value").toString();
        try{
            Double.parseDouble(valor);
        }catch(NumberFormatException num){
            valor="";
        }
        Integer longitud=(Integer) sacarValor(component, "maxlength");
        String valIzq=agregarLongitud("0",longitud,false,valor);
        ponerValor(component, valIzq);
       
   }
   
   
    private String agregarLongitud(String caracter, int longitud,boolean derecha, String valor) {
        valor=valor==null ? "":valor;
        while(valor.length()<longitud){
            valor = derecha ? valor+caracter : caracter+valor;
        }
        return valor;
    }
   
   public void ponerValor(UIComponent component , Object valor){
       component.getAttributes().put("value", valor);
   }
   
   public UIComponent getComponente(String nombre){
         return FacesContext.getCurrentInstance().getViewRoot().findComponent(nombre);
   }
   
   public Object sacarValor(UIComponent component,String atributo){
        return component.getAttributes().get(atributo);
   }

    public static void main(String[] args) {
        String algo="45.5";
        String[] split = algo.split("\\.");
        System.out.println(split.length);
        System.out.println(split[0]);
    }
}
