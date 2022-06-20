package locadora;


import java.util.Scanner;

public class Funcoes {
	
	//Em v�rios lugares eu declaro vari�veis dentro de loops por ser mais conveniente. 
	//Eu imaginei que poderia ser uma m� pr�tica, mas isto aqui me fez mudar de id�ia:
	//https://stackoverflow.com/questions/4501482/java-declaring-variables-in-for-loops
	
	//Os m�todos e atributos desta classe s�o declarados como static porque s�o chamados 
	// a partir de main, que � declarado como static. Isso evita o erro:
	//Cannot make a static reference to the non-static method
	// Para evitar isso seria necess�rio instanciar um objeto desta classe  
	// e chamar os m�todos do objeto, que � a forma como operamos com Carro e Cliente
	//Em outras palavras, um m�todo static pode ser chamado sem a necessidade de criar uma inst�ncia da classe.
	
	public static final String ARQ_VEICULOS="carros.txt";
	public static final String ARQ_CLIENTES="clientes.txt";
	
	
	public static void clrscr(){
		System.out.println("Se voc� est� vendo esta mensagem, este programa est� rodando");
		System.out.println("no Eclipse. A rotina de apagamento de tela n�o funciona aqui.");
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (java.io.IOException | InterruptedException ex) {}
	}	
	
	
	public static void waitForEnter(Scanner scan, boolean apagarTela) {

		System.out.println("Tecle ENTER para voltar ao menu...");
		scan.nextLine();
		if (apagarTela) clrscr(); 
	}
	
	static String getDataAtual() {
		java.util.Date date = java.util.Calendar.getInstance().getTime();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);	
        return strDate;
	}
	
	
	public static void imprimirCarroPeloIndice(Carro car[], int indice) {

		System.out.print("Carro ");
		System.out.print(car[indice].getRegistroLoc()+" : ");
		System.out.print(car[indice].getFabricante()+" ");
		System.out.print(car[indice].getModelo()+" ");
		System.out.print(car[indice].getMotor()+" ");
		System.out.print(car[indice].getCor()+" ");
		System.out.print(car[indice].getTipoCombustivel()+" ");
		System.out.println(car[indice].getKmRodados()+"Km");
		}
	
	public static void imprimirFrota(Carro carros[], boolean todos) {
	    //Imprime a frota
		for (int i=0; i<(Carro.getTotalCarros()); i++) {

			boolean alugado= !(carros[i].getAlugadoPor().equalsIgnoreCase(""));
				if (todos || !alugado ) {
					imprimirCarroPeloIndice(carros, i);
				}
			
		}
		
	}

	public static void imprimirAlugados(Carro carros[], Cliente clientes[]) {

		for (int i=0; i<(Carro.getTotalCarros()); i++) {
			String alugadoPor=carros[i].getAlugadoPor();
			boolean alugado= !(alugadoPor.equalsIgnoreCase(""));
				if (alugado) {
					imprimirCarroPeloIndice(carros, i);
					System.out.print("Alugado para: ");
					System.out.println(clientes[Integer.parseInt(alugadoPor)].getNome());
					System.out.println("");
				}
		
		}
		
	}	
	
	public static boolean encerrarAluguel(Carro car[], Cliente cl[], Scanner scan) {
		imprimirAlugados(car, cl);
		
		String resposta="";
		do {
			System.out.println("Digite o c�digo do carro que deseja devolver:");
			resposta=scan.nextLine();
			resposta=resposta.replaceAll("\\D",""); //removo tudo o que n�o for num�rico
		} while (resposta.equals("")|| (Integer.parseInt(resposta)>Carro.getTotalCarros())); 
		
		String veiculo= resposta;
		
		boolean achei=false;
		for (int i=0; i<(Carro.getTotalCarros()); i++) {
			String registroLoc=car[i].getRegistroLoc();
			
		        if (registroLoc.equalsIgnoreCase(veiculo)) {
		        	
		        	if (!(car[i].getAlugadoPor().equalsIgnoreCase(""))) {
		        		do {
		        			achei=true;
		        			System.out.println("Qual a kilometragem atual do ve�culo?");
		        			resposta=scan.nextLine();
		        			resposta=resposta.replaceAll("\\D",""); //removo tudo o que n�o for num�rico
		        			
		        			if (Integer.parseInt(resposta)< car[i].getKmRodados()) {
		        				System.out.println("Este n�mero � menor que o anterior.");
		        				resposta="";
		        			}
		        		} while (resposta.equals("")); 		        		
		        		car[i].setKmRodados(Integer.parseInt(resposta));
			        	car[i].setAlugadoPor("");
			        	
		        	}
				}
		}
		if (achei) {
			System.out.println("Aluguel encerrado");
			return true;
		}else {
			System.out.println("O c�digo fornecido n�o � de um ve�culo atualmente alugado.");
			return false;
		}
		
	}
	
	public static int imprimirCarroPeloRegistro(Carro car[], String registro) {
	
		for (int i=0; i<(Carro.getTotalCarros()); i++) {
			String registroLoc=car[i].getRegistroLoc();
			
		        if (registroLoc.equalsIgnoreCase(registro)) {
		        	imprimirCarroPeloIndice(car, i);
		        	return i;
		        	
				}
		}
		return -1; //carro n�o encontrado
		
	}
	
	public static void imprimirClientes(Cliente cl[], Carro car[]) {

		for (int i=0; i<(Cliente.getTotalClientes()); i++) {
			System.out.println("Nome: " + cl[i].getNome());
			System.out.println("Endere�o: "+ cl[i].getEndereco());
			System.out.println("Telefone: "+ cl[i].getTelefone());
			System.out.println("Idade: "+ cl[i].getIdade()+" anos");
			System.out.println("CPF: "+ cl[i].getCpf());
			System.out.println("RG: "+ cl[i].getRg());
			
			
		    for (int j=0; j<Carro.getTotalCarros(); j++) {
	    	
				if (car[j].getAlugadoPor().equalsIgnoreCase(Integer.toString(i))) {
					System.out.print("Alugando: "); 
				    imprimirCarroPeloIndice(car,j);
				}
			    	
				
		    }				

			System.out.println("");
		}		
		
	}
	
	public static void imprimirClientesSimplificado(Cliente cl[], Carro car[]) {

		for (int i=0; i<(Cliente.getTotalClientes()); i++) {
			System.out.print( i+1+ " - " + cl[i].getNome()+", ");
			System.out.println("CPF: "+ cl[i].getCpf());
			
		    for (int j=0; j<Carro.getTotalCarros(); j++) {
		    	
				if (car[j].getAlugadoPor().equalsIgnoreCase(Integer.toString(i))) {
					System.out.print("      Alugando: "); 
				    imprimirCarroPeloIndice(car,j);
				}
			
		    }				
						
		}		
		
	}
	
	public static String buscarClientePeloCPF(Cliente cl[], String cpf) {
		
		String valFornecido=cpf.replaceAll("\\D",""); 
		for (int i=0; i<(Cliente.getTotalClientes()); i++) {
			String valCadastrado=cl[i].getCpf().replaceAll("\\D",""); 
			if (valFornecido.contentEquals(valCadastrado)) {
			String texto= Integer.toString(i+1).concat(" - ").concat(cl[i].getNome());	
			return texto; 
						
			}
		
	}
		return ""; //Se nada foi achado;
	}
	
	public static boolean alugarVeiculo(Carro car[], Cliente cl[], Scanner scan) {
		
		int cliente= obterNumCliente(cl, car, scan);
		if (cliente==0) return false;
		//--------------------------------------------------------------------------------
		
		imprimirFrota(car, false); //com false eu imprimo apenas os carros dispon�veis

		String resposta="";
		do {
			System.out.println("Digite o c�digo do carro que deseja alugar:");
			resposta=scan.nextLine();
			resposta=resposta.replaceAll("\\D",""); //removo tudo o que n�o for num�rico
		} while (resposta.equals("")|| (Integer.parseInt(resposta)>Carro.getTotalCarros())); 
		
		String veiculo= resposta;

		System.out.println(cl[cliente].getNome());		
		System.out.println("Voc� escolheu o ve�culo: ");
		int indexVeiculo=imprimirCarroPeloRegistro(car, veiculo);
		System.out.println("Confirma o aluguel deste ve�culo? (s,n) ");
		String opcao=scan.nextLine();
		if (opcao.equalsIgnoreCase("s")) {
			cl[cliente].setDataAluguel(getDataAtual());	
			//TODO: usar o indice do cliente vai criar problemas quando um cliente for apagado. 
			//O correto � usar um c�digo de cliente
			car[indexVeiculo].setAlugadoPor(Integer.toString(cliente));
			System.out.println("Aluguel registrado.");
			return true;
		}else 
			{
			System.out.println("Aluguel cancelado.");
			return false;
			}
	}
	
	public static void carregarVeiculos(Carro car[]) {
		
		try {
			java.io.FileReader reader = new java.io.FileReader(ARQ_VEICULOS);
		    java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
		    int numCarrosToImport= Integer.parseInt(bufferedReader.readLine());
		    
		    for (int i=0; i<numCarrosToImport; i++) {
		    	Carro v = new Carro();
		    	
		    	String fabricante=bufferedReader.readLine();
		    	String modelo=bufferedReader.readLine();
		    	String motor=bufferedReader.readLine();
		    	String cor=bufferedReader.readLine();
		    	String tipoCombustivel=bufferedReader.readLine();
		    	int kmRodados=Integer.parseInt(bufferedReader.readLine());
		    	String alugadoPor=bufferedReader.readLine();
		    	
				v.setRegistroLoc(Integer.toString(i+1));
				v.setFabricante(fabricante);
				v.setModelo(modelo);
				v.setMotor(motor);
				v.setCor(cor);
				v.setTipoCombustivel(tipoCombustivel);
				v.setKmRodados(kmRodados);
				v.setAlugadoPor(alugadoPor);	
				
				car[i]=v;
				Carro.incTotalCarros();
				
		    }		    
		    
		    reader.close();
		   } catch (java.io.IOException e) {
			    e.printStackTrace();
		   }
		/*TODO: Fazer com que o c�digo possa detectar que o arquivo acabou e assim
		 * evitar esse NullPointerException
		 */
	   		catch (java.lang.NullPointerException e1) {
			    //e1.printStackTrace();
			    System.out.println("NullPointerException em carregarVeiculos()");
			    System.out.println("A causa mais comum para isso � a primeira linha de carros.txt");
			    System.out.println("estar definindo um n�mero maior de ve�culos do que o real.");
			    System.out.println("O programa vai prosseguir mas a opera��o n�o � confi�vel.");	
		   }	
	}
	
	public static void carregarClientes(Cliente cl[]) {
		
		try {
			java.io.FileReader reader = new java.io.FileReader(ARQ_CLIENTES);
		    java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
		    //A primeira linha do arquivo armazena o n�mero de clientes a ler
		    int numCLientesToImport=Integer.parseInt(bufferedReader.readLine());
		    
		    for (int i=0; i<numCLientesToImport; i++) {
		    	
		    	Cliente cli= new Cliente();

		    	String nome=bufferedReader.readLine();
		    	String endereco=bufferedReader.readLine();
		    	String telefone=bufferedReader.readLine();
		    	String dataNasc=bufferedReader.readLine();
		    	String cpf=bufferedReader.readLine();
		    	String rg=bufferedReader.readLine();
 
				cli.setNome(nome);
				cli.setEndereco(endereco);
				cli.setTelefone(telefone);
				cli.setDataNasc(dataNasc);
				cli.setCpf(cpf);
				cli.setRg(rg);
			    	
				cl[i]=cli;	
				Cliente.incTotalClientes();
		    }
		    reader.close();
		   } catch (java.io.IOException e) {
		    e.printStackTrace();
		   }
		/*TODO: Fazer com que o c�digo possa detectar que o arquivo acabou e assim
		 * evitar esse NullPointerException
		 */
   		catch (java.lang.NullPointerException e1) {
		    //e1.printStackTrace();
		    System.out.println("NullPointerException em carregarClientes()");
		    System.out.println("A causa mais comum para isso � a primeira linha de clientes.txt");
		    System.out.println("estar definindo um n�mero maior de clientes do que o real.");
		    System.out.println("O programa vai prosseguir mas a opera��o n�o � confi�vel.");		    
	   }	
	}	
	

	public static boolean adicionarCliente(Cliente cl[], Scanner scan) {
		if (Cliente.getTotalClientes()==Cliente.MAX_CLIENTES) {
			System.out.println("J� foi atingido o n�mero m�ximo de clientes: "+Cliente.MAX_CLIENTES);
			return false;
		}

	
		Cliente cli = new Cliente();
		                                 
		System.out.println("Nome: ");
		while(!cli.setNome(scan.nextLine())) {
			System.out.println("Nome inv�lido.");
			System.out.println("Nome: ");
		}	
	
		System.out.println("Endere�o: ");
		while(!cli.setEndereco(scan.nextLine())) {
			System.out.println("Endere�o inv�lido.");
			System.out.println("Endere�o: ");
		}
		
		System.out.println("Telefone com DDD (exemplo: 81 987654321): ");
		while(!cli.setTelefone(scan.nextLine())) {
			System.out.println("Telefone inv�lido.");
			System.out.println("Telefone com DDD (exemplo: 81 987654321): ");
		}	
		
		System.out.println("Data de Nascimento (dd/mm/aaaa): ");
		while(!cli.setDataNasc(scan.nextLine())) {
			System.out.println("Data inv�lida.");
			System.out.println("Data de Nascimento (dd/mm/aaaa): ");
		}
		
		System.out.println("CPF: ");
		String resposta= scan.nextLine();
		boolean sucesso=false;
		do {
			String cliente=Funcoes.buscarClientePeloCPF(cl, resposta);
			if (!cliente.isEmpty()) {
				
				System.out.print("J� existe um cliente cadastrado com este CPF: ");
				System.out.println(cliente);
				sucesso=false;
			} else sucesso=cli.setCpf(resposta);

		if (!sucesso) {
			System.out.println("CPF inv�lido.");
			System.out.println("CPF: ");
			resposta= scan.nextLine();	
		}
		}while (!sucesso);
	
		
		System.out.println("RG: ");
		while(!cli.setRg(scan.nextLine())) {
			System.out.println("RG inv�lido.");
			System.out.println("RG: ");
		}
		
		
		System.out.println("Confirma a inclus�o deste cliente? (s,n) ");
		String opcao=scan.nextLine();
		if (opcao.equalsIgnoreCase("s")) {
			int indice= Cliente.getTotalClientes();
			cl[indice]=cli; //aplico o objeto inteiro na �ltima posi��o livre do array
			
			System.out.println("Cliente Cadastrado.");
			
			Cliente.incTotalClientes();
			return true;
		}else {
			System.out.println("Cadastro cancelado.");
			return false;
		}
	}
	
	public static int obterNumCliente(Cliente cl[], Carro car[], Scanner scan) {
		
		//TODO: esta fun��o recebe o array car[] apenas para poder repassar para a fun��o seguinte
		//Existe um jeito de evitar isso?
		
		imprimirClientesSimplificado(cl, car);
		
		int numero=0;
		do {
			String resposta="";
			System.out.println("\nDigite o n�mero do cliente (ou apenas ENTER para cancelar):");
			resposta=scan.nextLine();
			resposta=resposta.replaceAll("\\D",""); //removo tudo o que n�o for num�rico
			if (resposta.equals("")) return 0;
			numero=Integer.parseInt(resposta);
		} while ((numero<1)||(numero> Cliente.getTotalClientes()));
		return (numero-1);
	}
	
	public static boolean editarCliente(Cliente cl[], Carro car[], Scanner scan) {
	
		Cliente clEditado = new Cliente();
		
		//TODO: esta fun��o recebe o array car[] apenas para poder repassar para a fun��o seguinte
		//Existe um jeito de evitar isso?
		int numero= obterNumCliente(cl, car, scan);	
		if (numero==0) return false;
		
		System.out.println("O valor atual de cada campo ser� exibido entre par�nteses");
		System.out.println("Se quiser deixar o campo inalterado basta teclar ENTER\n");
		
		String resposta="";
		String valorAtual= cl[numero].getNome();
		System.out.println("Nome ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setNome(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			while(!clEditado.setNome(resposta)) {
				System.out.println("Nome inv�lido.");
				System.out.println("Nome: ");
				resposta= scan.nextLine();
			}
		}
	
		valorAtual= cl[numero].getEndereco();
		System.out.println("Endere�o: ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setEndereco(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			while(!clEditado.setEndereco(resposta)) {
				System.out.println("Endere�o inv�lido.");
				System.out.println("Endere�o: ");
				resposta= scan.nextLine();
			}
		}
		
		
		valorAtual= cl[numero].getTelefone();
		System.out.println("Telefone: ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setTelefone(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			while(!clEditado.setTelefone(resposta)) {
				System.out.println("Telefone inv�lido.");
				System.out.println("Telefone com DDD (exemplo: 81 987654321): ");
				resposta= scan.nextLine();
			}
		}		
		
		
		valorAtual= cl[numero].getDataNasc();
		System.out.println("Data de Nascimento (dd/mm/aaaa): ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setDataNasc(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			while(!clEditado.setDataNasc(resposta)) {
				System.out.println("Data inv�lida.");
				System.out.println("Data de Nascimento (dd/mm/aaaa): ");
				resposta= scan.nextLine();
			}
		}
		
		valorAtual= cl[numero].getCpf();
		System.out.println("CPF: ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setCpf(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			boolean sucesso=false;
			do {
				String cliente=Funcoes.buscarClientePeloCPF(cl, resposta);
				if (!cliente.isEmpty()) {
					
					System.out.print("J� existe um cliente cadastrado com este CPF: ");
					System.out.println(cliente);
					sucesso=false;
				} else sucesso=clEditado.setCpf(resposta);
				
			if (!sucesso) {
				System.out.println("CPF inv�lido.");
				System.out.println("CPF: ");
				resposta= scan.nextLine();	
			}
			}while (!sucesso);

		}		
		
		valorAtual= cl[numero].getRg();
		System.out.println("RG: ( "+valorAtual+" ): ");
		resposta= scan.nextLine();
		if (resposta.isEmpty()) clEditado.setRg(valorAtual); //preservo o valor atual se o usu�rio respondeu com ENTER
		else {
			while(!clEditado.setRg(resposta)) {
				System.out.println("RG inv�lido.");
				System.out.println("RG: ");
				resposta= scan.nextLine();
			}
		}	
		
		System.out.println("Confirma as altera��es feitas? (s,n) ");
		String opcao=scan.nextLine();
		if (opcao.equalsIgnoreCase("s")) {

			cl[numero]=clEditado; //substituo o objeto inteiro 
			
			System.out.println("Altera��es aplicadas.");
			return true;
		}else {
			System.out.println("Altera��es descartadas.");
			return false;
		}
	}	
	
	/*
	public static int obterNovoCodCliente(Cliente[] c) {
		if (numClientesCadastrados==0) return 1;
		else
		{
			int codUltimoCliente= c[numClientesCadastrados-1].getCodCliente(); 
			return codUltimoCliente+1;
		}
		
		
	}
	*/
	public static void salvarClientes (Cliente[] cl){
	  
	try {
	java.io.BufferedWriter outputWriter = null;
	  outputWriter = new java.io.BufferedWriter(new java.io.FileWriter(ARQ_CLIENTES));
	  outputWriter.write(Integer.toString(Cliente.getTotalClientes()));
	  outputWriter.newLine(); //A primeira linha registra o n�mero de elementos
	  for (int i = 0; i < Cliente.getTotalClientes(); i++) {

	    outputWriter.write(cl[i].getNome());
	    outputWriter.newLine();
	    outputWriter.write(cl[i].getEndereco());
	    outputWriter.newLine();
	    outputWriter.write(cl[i].getTelefone());
	    outputWriter.newLine();
	    outputWriter.write(cl[i].getDataNasc());
	    outputWriter.newLine();
	    outputWriter.write(cl[i].getCpf());
	    outputWriter.newLine();
	    outputWriter.write(cl[i].getRg());
	    outputWriter.newLine();
	  }
	  outputWriter.flush();  
	  outputWriter.close();  
	   } catch (java.io.IOException e) {
		    e.printStackTrace();
		   }
	}


	public static void salvarVeiculos (Carro[] car){
	  
	try {
		java.io.BufferedWriter outputWriter = null;
	  outputWriter = new java.io.BufferedWriter(new java.io.FileWriter(ARQ_VEICULOS));
	  outputWriter.write(Integer.toString(Carro.getTotalCarros()));
	  outputWriter.newLine(); //A primeira linha registra o n�mero de elementos
	  for (int i = 0; i < Carro.getTotalCarros(); i++) {

	    outputWriter.write(car[i].getFabricante());
	    outputWriter.newLine();
	    outputWriter.write(car[i].getModelo());
	    outputWriter.newLine();
	    outputWriter.write(car[i].getMotor());
	    outputWriter.newLine();
	    outputWriter.write(car[i].getCor());
	    outputWriter.newLine();
	    outputWriter.write(car[i].getTipoCombustivel());
	    outputWriter.newLine();
	    outputWriter.write(Integer.toString(car[i].getKmRodados()));
	    outputWriter.newLine();
	    outputWriter.write(car[i].getAlugadoPor());
	    outputWriter.newLine();
	  }
	  outputWriter.flush();  
	  outputWriter.close();  
	   } catch (java.io.IOException e) {
		    e.printStackTrace();
		   }
	}
}
