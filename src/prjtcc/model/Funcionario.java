package prjtcc.model;

import java.awt.image.BufferedImage;


public class Funcionario {
	
    private String nome;
    private String senha; 
    private String id;
    private BufferedImage foto;
	
	public Funcionario(String nome, String Senha, String Id) {
		this.nome= nome;
		this.senha = Senha;
		this.id = Id;
		this.foto = null;
	} 
        
        public Funcionario(String nome, String Senha) {
		this.nome= nome;
		this.senha = Senha;
		this.foto = null;
	} 
	
	public Funcionario(String nome, String Senha, String Id, BufferedImage Foto) {
		this.nome= nome;
		this.senha = Senha;
		this.id = Id;
		this.foto = Foto;
	}

    public BufferedImage getFoto() {
        return foto;
    }


    public void setFoto(BufferedImage foto) {
        this.foto = foto;
    }
 public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
    
    
    
}
