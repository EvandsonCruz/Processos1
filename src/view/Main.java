package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();
		String so = System.getProperty("os.name");
		int opc = 0;
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Menu\n\n1- Ip\n2- Ping\n9- Sair"));
			switch (opc) {
			case 1:
				rede.ip(so);
				break;
			case 2:
				rede.ping(so);
				break;
			case 9:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opc inválida");
				break;
			}

		} while (opc != 9);
	}
}
