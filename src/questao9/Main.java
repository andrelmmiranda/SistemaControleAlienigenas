package questao9;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SistemaControleEntradaAlienigena sis = new SistemaControleEntradaAlienigena();

        sis.start();
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
// espécie, status de quarentena e avaliação de periculosidade.
//
//Encerramento do Programa: Permitir ao usuário encerrar o programa de forma adequada.
//
//Funções extras:
//        -Ranking de periculosidade
//-Verificar todos planetas, espécies e alienígenas cadastrados.
//        -Verificar alienígenas em quarentena
//-Sistema de pesquisa como:
//        -Mostrar todos os alienígenas de uma espécie específica.
//        -Mostrar todos os alienígenas que entraram no planeta nos últimos 6 meses