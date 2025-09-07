/**
 * Lab0: Leitura de Base de Dados N�o-Distribuida
 * 
 *Nome : Julio Cesar Farias
 *Nome : Lucas Santos Souza
 * 
 *
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("src\\fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
					System.out.println(fortune.toString());

					System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			 System.out.println("\n--- Lendo uma fortuna aleatoria ---");

			    if (hm.isEmpty()) {
			        System.out.println("O banco de fortunas esta vazio. Nao ha nada para ler.");
			        return;
			    }
			    
			    
			    SecureRandom random = new SecureRandom();
			    
			    
			    int sortedIndex = random.nextInt(NUM_FORTUNES);

			   
			    String fortune = hm.get(sortedIndex);
			    
			    System.out.println("Fortuna Sorteada (No. " + sortedIndex + "):");
			    System.out.println(fortune);
		}

		public void write(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			System.out.println("\n--- Escrevendo uma nova fortuna ---");
		    System.out.println("Digite a sua nova fortuna. Pressione Enter duas vezes para finalizar.");

		    Scanner scanner = new Scanner(System.in);
		    StringBuilder novaFortuna = new StringBuilder();
		    String linha;

		    
		    while (scanner.hasNextLine()) {
		        linha = scanner.nextLine();
		        if (linha.isEmpty()) {
		            break;
		        }
		        novaFortuna.append(linha).append("\n");
		    }

		   
		    try (FileWriter fw = new FileWriter(path.toString(), true);
		         BufferedWriter bw = new BufferedWriter(fw);
		         PrintWriter out = new PrintWriter(bw)) {

		       
		        out.println("%");
		        
		        out.print(novaFortuna.toString());
		        
		        System.out.println("Nova fortuna adicionada com sucesso!");

		    } catch (IOException e) {
		        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
		    } finally {
		        scanner.close(); 
		    }
		}
	}

	public void iniciar() {

		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Principal_v0().iniciar();
	}

}


