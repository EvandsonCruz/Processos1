package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
							System.out.println(adap[2].substring(0, adap[2].length() - 1));
						} else {
							System.out.println(adap[2]);
						}
						System.out.println(linha.substring(linha.lastIndexOf(":") + 2));
					}
					linha = buffer.readLine();
				}
			} catch (IOException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		if (so.contains("Linux")) {
			// comandos linux
		}
	}
}
