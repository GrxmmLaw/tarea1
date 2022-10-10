
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

class OrdenCompra {
    private Date fecha;
    private String estado;
    private ArrayList<Pago> Pagos;
    private ArrayList<DetalleOrden> Do;
    private Cliente usuario;
    private Direccion dir;
    private DocTributario DT;
    private float Total;
    private float TotalSinIVA;
    private float IVA;
    private float peso;
    private float pagado = 0;
    private float vuelto = 0;
    public OrdenCompra(Cliente c, DocTributario D, Date f, Direccion dr){
        Do = new ArrayList<>();
        Pagos = new ArrayList<>();
        usuario = c;
        DT = D;
        fecha = f;
        dir = dr;
        estado = "Pendiente";
        pagado = (float)0;
    }
    public void addDetalleOrden(DetalleOrden n){
        Do.add(n);
    }
    public void addPago(Pago p){
        if(pagado < Total){
            Pagos.add(p);
            pagado +=p.getMonto();
        }if(pagado >= Total){
            if(p.getTipoPago()==1){
                vuelto = p.Devolucion(Total, pagado);
            }
            estado = "Pagado";
            pagado = Total;
        }
    }
    public float calcPrecio(){
        float precioTotal = 0;
        for(int i = 0; i< Do.size(); i++){
            precioTotal += Do.get(i).calcPrecio();
        }
        Total = precioTotal;
        return precioTotal;
    }
    public float calcPrecioSinIVA(){
        float precioTotal = 0;
        for(int i = 0; i< Do.size(); i++){
            precioTotal += Do.get(i).calcPrecio()/1.19;
        }
        TotalSinIVA = precioTotal;
        return precioTotal;
    }
    public float calcIVA(){
        float precioTotal = 0;
        for(int i = 0; i< Do.size(); i++){
            precioTotal += Do.get(i).calcPrecio();
        }
        IVA = precioTotal*(float)0.19;
        return precioTotal*(float)0.19;
    }
    public float calcPeso(){
        float pesoTotal = 0;
        for(int i = 0; i< Do.size(); i++){
            pesoTotal += Do.get(i).calcPeso();
        }
        peso = pesoTotal;
        return pesoTotal;
    }
    public String toStringOrdenCompra(){
        String oc = "";
        oc += "---------------------------------------------\n";
        oc += usuario.toStringCliente();
        oc += dir.toStringDireccion()+"\n";
        oc += "---------------------------------------------\n";
        oc += DT.toStringDoc();
        oc += "---------------------------------------------\n";
        oc += "Estado: "+estado+"\n";
        oc += "Fecha: "+fecha+"\n";
        oc += "---------------------------------------------\n";
        oc += "Lista de Pagos: \n";
        for(int i = 0; i < Pagos.size(); i++){
            oc += "---------------------------------------------\n";
            oc += Pagos.get(i).toStringPago();
        }
        oc += "---------------------------------------------\n";
        for(int i = 0; i < Do.size(); i++){
            oc += "Detalle de Orden numero " + i+"\n";
            oc += "---------------------------------------------\n";
            oc += Do.get(i).toStringDetalle();
        }
        oc += "PRECIO TOTAL: " + Total+"\nPAGADO:"+pagado+"\nVUELTO: "+vuelto+"\nTOTAL SIN IVA: "+TotalSinIVA+"\nIVA TOTAL: "+IVA+"\nPESO TOTAL: "+peso+"\n";
        return oc;
    }
    
}

public class Tareaprog {
    public static void main(String[] args) {
    }
    
}
