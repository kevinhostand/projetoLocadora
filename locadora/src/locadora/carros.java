package locadora;

public class carros {

	/*adicionar qualquer atributo aqui requer alterações também em carros.txt
	  e nas funções que salvam e carregam os dados do arquivo
	*/
	private String registroLoc;
	private String fabricante;
	private String modelo;
	private String cor;
	private String motor;
	private String tipoCombustivel;
	private int kmRodados;
	private String alugadoPor; 
	private static int totalCarros=0;
	public static final int MAX_VEICULOS=30; 
	
    void Carro(){
		this.fabricante="";
		this.modelo="";		
		this.cor="";
		this.motor="";
		this.tipoCombustivel="";
		this.kmRodados=0;
		this.alugadoPor="";
    }
    
    public String getAlugadoPor() {
		return this.alugadoPor;
	}	
	public String getRegistroLoc() {
		return this.registroLoc; 
	}	
	public String getFabricante() {
		return this.fabricante; 
	}
	public String getModelo() {
		return this.modelo; 
	}
	public String getTipoCombustivel() {
		return this.tipoCombustivel;
	}
	
	public String getMotor() {
		return this.motor;
	}
	
	public String getCor() {
		return this.cor;
	}
	
	public int getKmRodados() {
		return this.kmRodados;
	}	
	
	public void setCor(String cor) {
		this.cor=cor;
	}
	
	public void setAlugadoPor(String cliente) {
		this.alugadoPor=cliente;
	}
	
	public void setRegistroLoc(String registroLoc) {
		this.registroLoc=registroLoc;
	}	
	public void setFabricante(String fabricante) {
		this.fabricante=fabricante;
	}
	public void setModelo(String modelo) {
		this.modelo=modelo;
	}
	public void setMotor(String motor) {
		this.motor=motor;
	}
	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel=tipoCombustivel;
	}	
	public void setKmRodados(int kmRodados) {
		this.kmRodados=kmRodados;
	}
	
	public static int getTotalCarros(){
		return totalCarros;
	};
	
	public static void setTotalCarros(int num){
		totalCarros=num;
	};
	
	public static void incTotalCarros(){
		totalCarros++;
		
	};
}

