package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class RedesController {

	public RedesController() {
		super();
	}

	public static void ip(String so) {
		if (so.contains("Windows")) {

			String comando = "ipconfig";
			String adap[] = {};

			try {
				Process proc = Runtime.getRuntime().exec(comando);
				InputStream fluxo = proc.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("Ethernet")) {
						adap = linha.split(" ");
					}
					if (linha.contains("IPv4")) {
						if (adap[2].contains(":")) {
							System.out.println(adap[2].substring(0,adap[2].length() - 1));
						} else {
							System.out.println(adap[2]);
						}
						System.out.println(linha.substring(linha.lastIndexOf(":") + 2));
					}
					linha = buffer.readLine();
				}
			} catch (IOException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, erro, "ERRO",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		if (so.contains("Linux")) {

			String comando = "ifconfig";
			String adap[] = {};
			String inet[] = {};

			try {
				Process proc = Runtime.getRuntime().exec(comando);
				InputStream fluxo = proc.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("flags")) {
						adap = linha.split(" ");
					}
					if (linha.contains("inet 1")) {
						
						inet = linha.trim().split(" ");
						System.out.println(adap[0].substring(0,adap[0].length() - 1));
						System.out.println(inet[1]);
						
					}
					linha = buffer.readLine();
				}
			} catch (IOException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, erro, "ERRO",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

	}
	
	public static void ping (String so)
	{
		if (so.contains("Windows")) {
			
		String comando = "ping www.google.com.br -n 10";
		String tempo[] = {};
		int media=0;

		try {
			Process proc = Runtime.getRuntime().exec(comando);
			InputStream fluxo = proc.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if(linha.contains("Resposta")) {
				tempo = linha.split(" ");
				tempo[4] = tempo[4].replaceAll("[^0-9]*", "");
				System.out.println(tempo[4]+"ms");
				media = media + (Integer.parseInt(tempo[4]));
				
				}
				linha = buffer.readLine();
				
			}
		} catch (IOException e) {
			String erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		System.out.println("M�dia: " + media/10 + "ms");
	}
	
		if (so.contains("Linux")) {
			DecimalFormat df = new DecimalFormat("0.00");
			String comando = "ping www.google.com.br -c 10";
			String tempo[] = {};
			double media=0;

			try {
				Process proc = Runtime.getRuntime().exec(comando);
				InputStream fluxo = proc.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if(linha.contains("from")) {
					tempo = linha.split(" ");
					tempo[7] = tempo[7].replaceAll("[^0-9.]*", "");
					System.out.println(tempo[7]+"ms");
					media = media + (Double.parseDouble(tempo[7]));	
					}
					linha = buffer.readLine();
					
				}
			} catch (IOException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			System.out.println("M�dia: " + df.format(media/10) + "ms");
		}
}
}
