package questao9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaControleEntradaAlienigena {
    public Scanner scanner;
    public List<Alienigena> alienigenas;
    public List<Especie> especies;
    public List<Alienigena> quarentenados;
    public boolean logout;


    public SistemaControleEntradaAlienigena() {
        this.scanner = new Scanner(System.in);
        this.alienigenas = new ArrayList<>();
        this.especies = new ArrayList<>();
        this.quarentenados = new ArrayList<>();
        this.logout = false;
    }

    public void start() throws IOException {
        menu();
    }

    public void menu() throws IOException {

        do {

            System.out.println("BEM VINDO AO SISTEMA DE CADASTRO DE ALIENS");

            System.out.println("1 - CADASTRAR ESPECIES");
            System.out.println("2 - CADASTRAR ENTRADAS");
            System.out.println("3 - LISTAR ESPECIES");
            System.out.println("4 - LISTAR ALIENS CADASTRADOS");
            System.out.println("5 - LISTAR QUARENTENADOS");
            System.out.println("6 - PESQUISAR POR ESPÉCIE");
            System.out.println("7 - MOVER PARA CADASTRADOS");
            System.out.println("8 - RANKING POR PERICULOSIDADE");
            System.out.println("9 - SAIR");

            String input = scanner.nextLine();

            switch (input){
                case "1":
                    registrarEspecie();
                    break;
                case "2":
                    registrarAlienigena();
                    break;
                case "3":
                    listarEspecies();
                    break;
                case "4":
                    listarAlienCadastrados(false);
                    break;
                case "5":
                    listarAlienCadastrados(true);
                    break;
                case "6":
                    pesquisarPorEspecie();
                case "7":
                    moverParaCadastrados();
                    break;
                case "8":
                    rankingPericulosidade();
                    break;
                case "9":
                    sair();
                    break;
                default:
                    System.out.println("Houve algum problema");
            }
        } while (!logout);

    }

    private boolean adicionarQuarentena(Alienigena alienigena){
        if(alienigena.nivelPericulosidade > 5){
            quarentenados.add(alienigena);
            return true;
        }
        return false;
    }

    public void registrarAlienigena() throws IOException {

        System.out.println("REGISTRO DE ENTRADA DE ALIENÍGENA");

        if(especies.isEmpty()){
            System.out.println("Não existem espécies cadastradas. ");
            System.out.println("Realize o cadastro de espécies antes de cadastrar as entradas. ");
        } else {
            System.out.println("Digite o nome: ");
            String nomeAlien = scanner.nextLine();

            System.out.println("Escolha uma das espécies: ");
            int i = 1;

            for (Especie e: especies){
                System.out.printf("%s - %s %n", i, e.nomeEspecie);
                i++;
            }

            String indiceEspecie = scanner.nextLine();

            System.out.println("Digite o nível de periculosidade: ");
            String nivelPericulisidade = scanner.nextLine();

            Alienigena a = new Alienigena(nomeAlien, especies.get(Integer.parseInt(indiceEspecie) - 1), Integer.parseInt(nivelPericulisidade));

            boolean quarentenado = adicionarQuarentena(a);

            if(quarentenado){
                System.out.println("O alienígena foi considerado perigoso e foi colocado em quarentena");
            } else {
                alienigenas.add(a);

                System.out.println("Alienigena cadastrado com sucesso!");
            }
        }

    }

    public void registrarEspecie() throws IOException {

        System.out.println("REGISTRO DE ESPÉCIE ALIENIGENA");

        System.out.println("Digite o nome da espécie: ");
        String nomeEspecie = scanner.nextLine();

        System.out.println("Digite o planeta de origem: ");
        String planetaOrigem = scanner.nextLine();

        System.out.println("Digite o nível de periculosidade base: ");
        String periculosidadeBase = scanner.nextLine();

        especies.add(new Especie(nomeEspecie, planetaOrigem, Integer.parseInt(periculosidadeBase)));

        System.out.println("Espécie cadastrada com sucesso!");
    }

    public void listarEspecies() throws IOException {

        if(especies.isEmpty()){
            System.out.println("Não há espécies cadastradas.");
        } else{
            for (Especie e: especies){
                System.out.printf("""
                        Espécie: %s
                        Origem: %s
                        Perigo Base: %s
                        """, e.nomeEspecie, e.planetaOrigem, e.nivelPericulosidadeBase);
            }
        }
    }

    public void moverParaCadastrados(){
        System.out.println("Mova o alienígena sob quarentena para a lista de cadastrados");
        System.out.println("Escolha o id do Alien desejado");
        String id = scanner.nextLine();
        for (Alienigena a: quarentenados){
            if(a.id == Long.parseLong(id)){
                alienigenas.add(a);
                quarentenados.remove(a);
            }
        }
    };

    public void listarAlienCadastrados(boolean quarentenado) throws IOException {

        if(especies.isEmpty()){
            System.out.println("Não há alienígenas cadastrados.");
        } else{
            for (Alienigena a: quarentenado ? quarentenados: alienigenas){
                printAlienigena(a);
            }
        }
    }

    private String formatarEspecie(Especie especie){
        return String.format("%s | %s | %s", especie.nomeEspecie, especie.planetaOrigem, especie.nivelPericulosidadeBase);
    }

    public void sair() throws IOException {

        logout = true;
        System.out.println("saindo do sistema. ");
    }

    public void rankingPericulosidade(){
        List<Alienigena> listaAliens = new ArrayList<>();
        listaAliens.addAll(alienigenas);
        listaAliens.addAll(quarentenados);

        if(listaAliens.isEmpty()){
            System.out.println("Não há alienígenas cadastrados.");
        } else{
            List<Alienigena> aliens = bubbleSort(listaAliens);

            for(Alienigena a: aliens){
                printAlienigena(a);
            }
        }
    }

    public void pesquisarPorEspecie(){
        System.out.println("Encontre alienígenas pela sua espécie");
        System.out.println("Digite a espécie desejada: ");

        List<Alienigena> aliens = juntarAlienigenas();

        if(aliens.isEmpty()){
            System.out.println("Ainda não existem espécies catalogadas.");
            System.out.println("Tente pesquisar em um outro momento.");
        } else {
            String especie = scanner.nextLine();

            for(Alienigena a: aliens){
                if(a.especie.nomeEspecie.equalsIgnoreCase(especie)){
                    printAlienigena(a);
                }
            }
        }
    }

    private void printAlienigena(Alienigena a){
        System.out.printf("""
                        ID: %s
                        nome: %s
                        Especie: %s
                        periculosidade: %s
                        d/h entrada: %s
                        """, a.id, a.nome, formatarEspecie(a.especie), a.nivelPericulosidade, a.dataHoraEntrada);
    }

    private List<Alienigena> juntarAlienigenas(){
        List<Alienigena> listaAliens = new ArrayList<>();
        listaAliens.addAll(alienigenas);
        listaAliens.addAll(quarentenados);

        return listaAliens;
    }

    private List<Alienigena> bubbleSort(List<Alienigena> array){

        Alienigena aux = null;

        for(int i = 0; i < array.size(); i++){

            for(int j = 0; j < array.size() - 1; j++){

                if(array.get(i).nivelPericulosidade > array.get(j + 1).nivelPericulosidade){
                    aux = array.get(i);
                    array.add(j, array.get(j + 1));
                    array.add(j + 1, aux);
                }
            }
        }
        return array;
    }
}




//
//Sistema de Controle de Entrada de Alienígenas:
//
//Você deve implementar um sistema de controle para monitorar a entrada de alienígenas no planeta Terra, permitindo aos
// usuários realizar as seguintes operações:
//
//Registro de Alienígenas: Cada alienígena tem características como id, nome, espécie, nível de periculosidade e
// data/hora de entrada. [OK]
//
//Registo de espécie: Cada espécie tem um planeta de origem e um nivel de periculosidade base. [OK]
//
//Avaliação de Periculosidade: O sistema deve avaliar a periculosidade de cada alienígena com base em sua espécie e
// atributos específicos.
//
//Quarentena e Monitoramento: Alienígenas considerados perigosos devem ser colocados em quarentena e monitorados
// continuamente.
//
//Relatório de Entradas: Mostrar um relatório com todos os alienígenas registrados, incluindo detalhes como nome,
// espécie, status de quarentena e avaliação de periculosidade. [OK]
//
//Encerramento do Programa: Permitir ao usuário encerrar o programa de forma adequada. [OK]
//
//Funções extras:
//-Ranking de periculosidade [matsumoto]
//-Verificar todos planetas, espécies e alienígenas cadastrados.
// -Verificar alienígenas em quarentena [OK]
//-Sistema de pesquisa como:
//        -Mostrar todos os alienígenas de uma espécie específica. [OK]
//        -Mostrar todos os alienígenas que entraram no planeta nos últimos 6 meses