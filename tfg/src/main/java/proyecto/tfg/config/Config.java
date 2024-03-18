package proyecto.tfg.config;

public final class Config {
    
    // URL POR DEFECTO
    public static final String base_url = "http://localhost:8080";

    // VARIABLES DE LA APP
    public static final String nombreAPP = "Nombre APP";

    // VARIABLES DEL SERVICIO DE CORREO
    public static final String NOMBRE_EMISOR = "NOMBRE APP";
    public static final String CORREO_EMISOR = "junquerdaviddaw@gmail.com";
    public static final String CLAVE_API = "hnor ysui rocd fqrz";

    // VARIABLES DE SIGNUP
    public static final int longitud_codigo_activacion = 20;

    // VARIABLES DEL WEBSOCKET
    public static final String ws_url = removeProtocol(base_url);



    // FUNCIONES CUIDADO AL TOCAR
    private static String removeProtocol(String url)
    {
        if (url.substring(0,5).equals("http:")) return url.substring(7, url.length());
        else return url.substring(8, url.length());
    }
}
