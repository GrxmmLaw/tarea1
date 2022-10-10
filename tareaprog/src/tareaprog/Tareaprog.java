
package tareaprog;
import java.util.ArrayList;
import java.util.Date;

class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;
    public Articulo(float p, String n, String d, float pr){
        peso = p;
        nombre = n;
        descripcion = d;
        precio = pr;
    }
    public float getPrecio(){
        return precio;
    }
    public float getPeso(){
        return peso;
    }
    public String toStringArticulo(){
        return "Producto: "+nombre+"                                Precio: "+precio+"\nDescripcion: " + descripcion +"                   Peso: "+peso+"\n";
    }
}

class DetalleOrden {
    private ArrayList<Articulo> a;
    private float precio;
    private float precioSinIVA;
    private float IVA;
    private float peso;
    public DetalleOrden(){
        a = new ArrayList<>();
    }
    public void addArticulo(Articulo x){
        a.add(x);
    }
    public String toStringDetalle(){
        String detalle = "";
        for(int i = 0; i<a.size(); i++){
            detalle += a.get(i).toStringArticulo()+"-------------------------------------------------------------------\n";
        }
        detalle += "Total: " + precio + "\nTotal (Sin IVA): " + precioSinIVA + "\nIVA: "+IVA+"\nPeso Total: "+peso+"\n-------------------------------------------------------------------\n"; 
        return detalle;
    }
    public float calcPrecio(){
        float precioTotal = 0;
        for(int i = 0; i<a.size(); i++){
            precioTotal += a.get(i).getPrecio();
        }
        precio = precioTotal;
        return precioTotal;
    }
    public float calcPrecioSinIva(){
        float precioTotal = 0;
        for(int i = 0; i<a.size(); i++){
            precioTotal += a.get(i).getPrecio()/1.19;
        }
        precioSinIVA = precioTotal;
        return precioTotal;
    }
    public float calcIVA(){
        float IVAtotal = 0;
        for(int i = 0; i<a.size(); i++){
            IVAtotal += a.get(i).getPrecio()*0.19;
        }
        IVA = IVAtotal;
        return IVAtotal;
    }
    public float calcPeso(){
        float pesoTotal = 0;
        for(int i = 0; i<a.size(); i++){
            pesoTotal += a.get(i).getPeso();
        }
        peso = pesoTotal;
        return pesoTotal;
    }
}

public class Tareaprog {
    public static void main(String[] args) {
    }
    
}
