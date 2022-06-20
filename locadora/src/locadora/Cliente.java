package locadora;

public class Cliente {

	
	private int codCliente;	
	private String nome;
	private String endereco;
	private String telefone;
	private String rg;
	private String cpf;
	private String dataNasc;
	private String carroAlugado;
	private String dataAluguel;
	private String dataDevolucao;
	
	private static int totalClientes=0;
	public static final int MAX_CLIENTES=20;

	
	public Cliente() {
		this.codCliente=0;
		this.nome="";
		this.endereco="";
		this.telefone="";
		this.rg="";
		this.cpf="";
		this.dataNasc="";
		this.carroAlugado="";
		this.dataAluguel="";
		this.dataDevolucao="";
	};
	


	
	public int getIdade(){
		java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 
		java.time.LocalDate birthDate = java.time.LocalDate.parse(this.dataNasc, dtf);
		java.time.LocalDate dataAtual = java.time.LocalDate.now();
        if ((birthDate != null) && (dataAtual != null)) {
            return java.time.Period.between(birthDate, dataAtual).getYears();
        } else {
            return 0;
        }
	};
	
	
	private boolean validarData(String data){
		java.text.DateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");

	        format.setLenient(false);

	        try {
	            format.parse(data);
	        } catch (java.text.ParseException e) {
	             return false;
	        }		
		return true;
		
	};
	

 private boolean validarTelefone(String telefone) {

    telefone = telefone.replaceAll("\\D","");
    
    
    if (!(telefone.length() >= 10 && telefone.length() <= 11)) return false;

    
    if (telefone.length() == 11 && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;

    
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
    java.util.regex.Matcher m = p.matcher(telefone);
    if(m.find()) return false;
    
    //DDDs validos
    Integer[] codigosDDD = {
    	11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 31, 32, 33, 34,
        35, 37, 38, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 61, 62,
        64, 63, 65, 66, 67, 68, 69, 71, 73, 74, 75, 77, 79, 81, 82, 83, 84, 85,
        86, 87, 88, 89, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    
    if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.parseInt(telefone.substring(0, 2))) == -1) return false;
    
   
     if (telefone.length() == 10 &&  "23457".indexOf(telefone.substring(2, 3)) == -1) return false;

    
    return true;
}
 
 private String formatarNumero(String tipo, String numero) {
 
    numero=numero.replaceAll("\\D", ""); 
    String mascara="";
    
    if (tipo.equalsIgnoreCase("telefone")) {
		
		mascara= "(##) #####-####";
		if (numero.length()==10) mascara="(##) ####-####"; 
    }
    if (tipo.equalsIgnoreCase("cpf")) {
		mascara="###.###.###-##";  
    }	    
	try {
		javax.swing.text.MaskFormatter formatadorNumero = new javax.swing.text.MaskFormatter(mascara);
		javax.swing.JFormattedTextField txtNumero = new javax.swing.JFormattedTextField(formatadorNumero);
		txtNumero.setText(numero);
		return txtNumero.getText();
	} catch (java.text.ParseException e) {
		e.printStackTrace();
		return "";
	}
	 
 }
 
  
	public int getCodCliente(){
		return this.codCliente;
	};
	
	public boolean setCodCliente(int numero){
		
		if (numero==0) {
			return false;
		}
		this.codCliente=numero;
		return true;	
		
	};	
  
	public String getNome(){
		return this.nome;
	};
	
	public boolean setNome(String nome){
		
		if (nome.equals("")) {
			return false;
		}
		this.nome=nome;
		return true;	
		
	};	
	public String getEndereco(){
		return this.endereco;
	};
	
	public boolean setEndereco(String endereco){
		
		if (endereco.equals("")) {
			return false;
		}
		this.endereco=endereco;
		return true;	
	};
	
	public String getTelefone(){
		return this.telefone;
	};
	
	public boolean setTelefone(String telefone){
		
	    if (telefone.contentEquals("")) return true;
	    
		if (validarTelefone(telefone)) {
			this.telefone=formatarNumero("telefone", telefone);
			return true;
		}

		return false;		
		
		
	};	
	public String getRg(){
		return this.rg;
	};
	
	public boolean setRg(String rg){
	
		if (rg.equals("")) {
			return false;
		}
		this.rg=rg;
		return true;	
		
	};	

	
	
	public String getDataNasc(){
		return this.dataNasc;
	};
	
	public boolean setDataNasc(String dataNasc){
	
		if (validarData(dataNasc)) {
			this.dataNasc=dataNasc;
			return true;
		}

		return false;
		
	};
	
	
	public static int getTotalClientes(){
		return totalClientes;
	};
	
	public static void setTotalClientes(int num){
		totalClientes=num;
	};
	
	public static void incTotalClientes(){
		totalClientes++;
		
	};
	
	public String getCarroAlugado() {
		return this.carroAlugado;
	}
	
	public void setCarroAlugado(String carroAlugado) {
		this.carroAlugado=carroAlugado;
	
	}
	
	public String getDataAluguel() {
		return this.dataAluguel;
	}
	
	public boolean setDataAluguel(String dataAluguel) {
		
		if (validarData(dataAluguel)) {
			this.dataAluguel=dataAluguel;
			return true;
		}

		return false;
	
	}

	public String getDataDevolucao() {
		return this.dataDevolucao;
	}
	
	public boolean setDataDevolucao(String dataDevolucao) {
		
		if (validarData(dataDevolucao)) {
			this.dataDevolucao=dataDevolucao;
			return true;
		}

		return false;	

	}	
	
	
	private int calcularDigito(String str) {
		int soma = 0;
		for(int indice = str.length()-1, digito; indice >= 0; indice--) {
			digito = Character.getNumericValue(str.charAt(indice));
			int valorPeso = str.length()+1 - indice;
			soma += digito*valorPeso;
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

   
   private boolean isValidCPF(String cpf) {
	   cpf=cpf.replaceAll("\\D","");
	   if ((cpf==null) || (cpf.length()!=11) ||
            cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) return false; 
	       Integer digito1 = calcularDigito(cpf.substring(0,9));
	       Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1);
	       return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	    }

	public boolean setCpf(String cpf){
		if(isValidCPF(cpf)) {
			this.cpf = formatarNumero("cpf", cpf);
			return true;
		} else {
			return false;
		}
	}	
	
	public String getCpf(){
		return this.cpf;
	};
}
