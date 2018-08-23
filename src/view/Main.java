package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();
		String so = System.getProperty("os.name");
		//rede.ip(so);
		rede.ping(so);

	}

}
