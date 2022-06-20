package locadora;


public class Principal {

	public static void main(String[] args){


		Carro carros[] = new Carro[Carro.MAX_VEICULOS] ; 
		Funcoes.carregarVeiculos(carros);	
		
		int numCarros=Carro.getTotalCarros();
		if (numCarros==0){
			System.out.println("\nNenhum carro está cadastrado.\n");
			System.out.println("Isso provavelmente significa que carros.txt não foi encontrado.\n");
			System.out.println("Esse arquivo deveria estar no diretório:");
			System.out.println(new java.io.File(".").getAbsoluteFile());
			System.out.println("\nNeste estágio do desenvolvimento o programa não pode prosseguir.\n");

			System.out.println("\nSaindo do programa...\n");

			System.exit(0);
		}
		
		
		Cliente clientes[] = new Cliente[Cliente.MAX_CLIENTES] ; 
		Funcoes.carregarClientes(clientes);	
		
		String opcao="";
		java.util.Scanner scan = new java.util.Scanner (System.in);
		
		int numCl=Cliente.getTotalClientes();
		if (numCl==0){
			System.out.println("\nNenhum cliente está cadastrado.\n");
			System.out.println("Isso provavelmente significa que clientes.txt não foi encontrado.\n");
			System.out.println("Esse arquivo deveria estar no diretório:");
			System.out.println(new java.io.File(".").getAbsoluteFile());
			System.out.print("\nO programa vai prosseguir, mas se isso era inesperado ");
			System.out.println("saia do programa e corrija o problema.\n");;
			Funcoes.waitForEnter(scan, true);
		}		
		
		do{
			//DEBUG
			//System.out.println("totalClientes= " + Cliente.getTotalClientes());
			//System.out.println("totalCarros= " + Carro.getTotalCarros());
			
			String opcoesValidas="0123456789";	
			
			numCl=Cliente.getTotalClientes();
			if (numCl==0){
				opcoesValidas="014";	
			}
			
			System.out.println("\nDigite a opção desejada seguida de ENTER:\n");
			System.out.println("1 - Listar frota");
			if (numCl>0)System.out.println("2 - Listar clientes com os respectivos carros alugados - versão completa");
			if (numCl>0)System.out.println("3 - Listar clientes com os respectivos carros alugados - versão curta");
			System.out.println("4 - Adicionar cliente");
			if (numCl>0)System.out.println("5 - Editar cliente");
			if (numCl>0)System.out.println("6 - Procurar cliente pelo CPF");
			if (numCl>0)System.out.println("7 - Alugar veículo");	
			if (numCl>0)System.out.println("8 - Listar veículos alugados");	
			if (numCl>0)System.out.println("9 - Encerrar aluguel");				
			System.out.println("0 - Sair do programa");


		opcao = scan.nextLine();
		
		
		while (opcoesValidas.indexOf(opcao) == -1) {
			System.out.print("Opção inválida. As opçoes válidas são ");
			System.out.println(opcoesValidas + ". Tente novamente.");	
			opcao = scan.nextLine();
		}
		
		switch(opcao){
		case "0":			
			System.out.println("Encerrado pelo usuário.");
			break;
		case "1":
			Funcoes.imprimirFrota(carros, true);
			Funcoes.waitForEnter(scan, true);
			break;
		case "2":
			Funcoes.imprimirClientes(clientes, carros);
			Funcoes.waitForEnter(scan, true);
			break;
		case "3":
			Funcoes.imprimirClientesSimplificado(clientes, carros);
			Funcoes.waitForEnter(scan, true);
			break;			
		case "4":
			if(Funcoes.adicionarCliente(clientes, scan)) Funcoes.salvarClientes(clientes);
			Funcoes.waitForEnter(scan, true);
			break;
		case "5":
			if(Funcoes.editarCliente(clientes, carros, scan)) Funcoes.salvarClientes(clientes);
			
			Funcoes.waitForEnter(scan, true);
			break;	
		case "6":
			String resposta="";
			do {
				System.out.println("Digite o CPF:");
				resposta=scan.nextLine();
				resposta=resposta.replaceAll("\\D",""); 
			} while (resposta.equals("")); 
			String cliente=Funcoes.buscarClientePeloCPF(clientes, resposta);
			if (cliente.isEmpty())System.out.println("Não há cliente cadastrado com este CPF");
			else System.out.println(cliente);
			
			Funcoes.waitForEnter(scan, true);
			break;				
		case "7":
			if (Funcoes.alugarVeiculo(carros, clientes, scan)) Funcoes.salvarVeiculos (carros);

			Funcoes.waitForEnter(scan, true);
			break;	
		case "8":			
			Funcoes.imprimirAlugados(carros, clientes);
			Funcoes.waitForEnter(scan, true);
			break;	
		case "9":
			if (Funcoes.encerrarAluguel(carros, clientes, scan)) Funcoes.salvarVeiculos (carros);
	
			Funcoes.waitForEnter(scan, true);
			break;	

		}
		}while(!opcao.contentEquals("0"));
		
		System.out.println("Programa encerrado.");
		scan.close();
		
		}
}