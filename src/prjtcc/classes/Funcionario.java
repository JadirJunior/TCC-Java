/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.classes;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jadir
 */
public class Funcionario {
    private static String nome;
    private static String senha; 
    private static int id;
    private static BufferedImage foto;

    public static BufferedImage getFoto() {
        return foto;
    }

    public static void setFoto(BufferedImage foto) {
        Funcionario.foto = foto;
    }
    
    

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Funcionario.id = id;
    }
    
    

    public static void setNome(String nome) {
        Funcionario.nome = nome;
    }

    public static void setSenha(String senha) {
        Funcionario.senha = senha;
    }

    public static String getNome() {
        return nome;
    }

    public static String getSenha() {
        return senha;
    }
    
    
    
}
