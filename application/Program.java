package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Combate;
import domain.Heroes;
import domain.enums.Universe;

public class Program {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Olá, Mundo");
        System.out.println("Seja bem vindo ao Super Heroes Program!");
        System.out.println();

        HeroesFileRepository repository = new HeroesFileRepository();
        List<Heroes> herois = repository.findAll();

        int n = 0;
        int codigo = herois.size() + 1;

        while (n != 4){

            System.out.println("Escolha a opção desejada:");
            System.out.println("1 - Cadastro de herói");
            System.out.println("2 - Listar heróis");
            System.out.println("3 - Combate");
            System.out.println("4 - Sair do programa");
            n = sc.nextInt();
            sc.nextLine();

            if (n == 1) {
                System.out.print("Nome do herói: ");
                String nome = sc.nextLine();
                System.out.print("Coeficiente de força do herói(1-1000): ");
                int forca = sc.nextInt();
                System.out.print("Universo do Herói(DC/MARVEL): ");
                Universe universo = Universe.valueOf(sc.next());
                herois.add(new Heroes(nome, codigo, forca, universo));
                codigo++;
                System.out.println("Herói cadastrado com sucesso!");
            }

            if (n==2) {
                for (Heroes c : herois){
                    System.out.println(c.toString());
                }
            }

            if (n==3){
                Heroes player1 = null;
                Heroes player2 = null;
                System.out.println("Escolha o primeiro herói do combate(código):");
                int p1 = sc.nextInt();
                for(Heroes c : herois){
                    if (p1 == c.getCodigo()){
                        player1 = new Heroes(c.getNome(), c.getCodigo(), c.getForca(), c.getUniverso());
                        break;
                    }
                }
                System.out.println("Escolha o segundo heroi do combate(código):");
                int p2 = sc.nextInt();
                for(Heroes c : herois){
                    if (p2 == c.getCodigo()){
                        player2 = new Heroes(c.getNome(), c.getCodigo(), c.getForca(), c.getUniverso());
                        break;
                    }
                }
                if(player1.getCodigo() != player2.getCodigo()) {
                    Combate combate = new Combate(player1, player2);
                } else {
                    System.out.println("Você não pode selecionar herois iguais");
                }


            }
            if (n==4) {
                repository.saveAll(herois);
            }
            System.out.println();

        }

        sc.close();
    }

public static final String NOME_ARQUIVO = "dados.csv";
 
}
