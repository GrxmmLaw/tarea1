
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

bstract class Pago {
    private float monto;
    private Date fecha;
    private int tipoPago;
    public Pago(float m, Date f, int tp){
        monto = m;
        fecha = f;
        tipoPago = tp;
    }
    public float getMonto(){
        return monto;
    }
    public Date getFecha(){
        return fecha;
    }
    public int getTipoPago(){
        return tipoPago;
    }
    public float Devolucion(float t, float p){
        if(monto + p > t){
            return monto + p - t;
        }
        else{
            return 0;
        }
    }
    abstract public String toStringPago();
}

class Efectivo extends Pago {
    public Efectivo(float m, Date f){
        super(m,f,1);
    }
    public String toStringPago(){
        return "Pago por Efectivo\nMonto: "+super.getMonto()+"                Fecha: "+super.getFecha()+"\n";
    }
}

class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(String b, String n, float m, Date f){
        super(m, f,2);
        banco = b;
        numCuenta = n;
    }
    public String toStringPago(){
        return "Pago por Transferencia\nMonto: "+super.getMonto()+"                Fecha: "+super.getFecha()+"\nBanco: "+banco+"Numero de Cuenta"+numCuenta+ "\n";
    }
}

class Tarjeta extends Pago{
    private final String tipo;
    private final String numTransaccion;
    public Tarjeta(String t, String n, float m, Date f){
        super(m, f,3);
        tipo = t;
        numTransaccion = n;
    }
    public String toStringPago(){
        return "Pago por Tarjeta\nMonto: "+super.getMonto()+"                Fecha: "+super.getFecha()+"Tarjeat Tipo: "+tipo+"\nNumero de Transaccion: "+numTransaccion+"\n";
    }
}

class Cliente {
    private final String nombre;
    private final String rut;
    public Cliente(String n, String r){
        nombre = n;
        rut = r;
    }
    public String getNombre(){
        return nombre;
    }
    public String getRut(){
        return rut;
    }
    public String toStringCliente(){
        return "Nombre: " + nombre + "\n" + "RUT: " + rut + "\n";
    }
}

class DocTributario {
    private String numero;
    private String rut;
    private Date fecha;
    private int tipoDoc;
    DocTributario(String n, String r, Date f, int td){
        numero = n;
        rut = r;
        fecha = f;
        tipoDoc = td;
    }
    public String getNumero(){
        return numero;
    }
    public String getRut(){
        return rut;
    }
    public Date getFecha(){
        return fecha;
    }
    public String toStringDoc(){
        if(tipoDoc == 1){
          return "Documento: Boleta\nNumero: "+numero+"\nRUT:"+rut+"\nFecha: "+fecha+"\n";    
        }
        if(tipoDoc == 2){
          return "Documento: Factura\nNumero: "+numero+"\nRUT:"+rut+"\nFecha: "+fecha+"\n";    
        }
        return "";
    }
}

class Boleta extends DocTributario {
    public Boleta(String n, String r, Date f){
        super(n,r,f,1);
    }
}

class Factura extends DocTributario {
    public Factura(String n, String r, Date f){
        super(n,r,f,2);
    }
}

class Direccion {
    private final String Direccion;
    public Direccion(String d){
        Direccion = d;
    }
    public String toStringDireccion(){
        return Direccion;
    }
}

public class Tareaprog {
    public static void main(String[] args) {
    }
    
}
