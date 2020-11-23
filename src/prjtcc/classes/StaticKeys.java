package prjtcc.classes;

import java.awt.image.BufferedImage;

public class StaticKeys {
    private static String nome;
    private static String senha; 
    private static String id;
    private static BufferedImage foto;
    
    public static void resetKeys() {
        StaticKeys.nome = "";
        StaticKeys.senha = "";
        StaticKeys.id = "";
        StaticKeys.foto = null;
    }
    
    
     public static String getId() {
        return id;
    }

    public static void setId(String id) {
        StaticKeys.id = id;
    }
    
    
    
    public static BufferedImage getFoto() {
        return foto;
    }

    public static void setFoto(BufferedImage foto) {
        StaticKeys.foto = foto;
    }
    
    public static void setNome(String nome) {
        StaticKeys.nome = nome;
    }

    public static void setSenha(String senha) {
        StaticKeys.senha = senha;
    }

    public static String getNome() {
        return nome;
    }

    public static String getSenha() {
        return senha;
    }
}
