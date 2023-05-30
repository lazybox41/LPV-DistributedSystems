import java.util.*;

public class Bully {
	int coordinator;
	int n;
	boolean state[];

	public Bully(int n) {
		this.n = n;
		state = new boolean[n];
		this.coordinator = n;
		for (int i = 0; i < n; i++) {
			state[i] = true;
			System.out.println("Process " + (i + 1) + " created.");
		}
		System.out.println("Process " + coordinator + " is coordinator.");
	}

	public void election(int id) {
		coordinator = id;
		boolean keepGoing = true;
		for (int i = id; i < n && keepGoing; i++) {

			System.out.println("Election message sent from " + id + " to " + (i + 1));
			if (state[i]) {
				keepGoing = false;
				System.out.println("Higher Process taking over election.");
				election(i + 1);
			}
		}
		display();
	}

	public void display() {
		System.out.println("Displaying Processes: ");
		for (int i = 0; i < n; i++) {
			if (state[i])
				System.out.print(" " + (i + 1));
		}
		System.out.println();
		System.out.println("Process " + coordinator + " is the coordinator");
	}

	public void upProcess(int id) {
		if (state[id - 1])
			System.out.println("Already up");
		else {
			state[id - 1] = true;
			System.out.println("Process " + (id) + " brought up.");
			this.election(id - 1);

		}
	}

	public void downProcess(int id) {
		if (!state[id - 1])
			System.out.println("Already down");
		else {
			state[id - 1] = false;
			System.out.println("Process " + (id) + " brought down.");
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		int n = sc.nextInt();
		int choice = 0;
		Bully bully = new Bully(n);
		while (true) {
			System.out.println("1. Display processes");
			System.out.println("2. Up a process");
			System.out.println("3. Down a process");
			System.out.println("4. Run election algorithm");
			System.out.println("5. Exit Program");
			System.out.print("Enter your choice:- ");
			choice = sc.nextInt();
			switch (choice) {
				case 1: {
					bully.display();
					break;
				}
				case 2: {
					int id;
					System.out.println("Enter process to up");
					id = sc.nextInt();
					bully.upProcess(id);
					break;
				}
				case 3: {
					int id;
					System.out.println("Enter process to down");
					id = sc.nextInt();
					bully.downProcess(id);
					break;
				}
				case 4: {
					int id;
					System.out.println("Enter process id conducting the election");
					id = sc.nextInt();
					bully.election(id);
					break;
				}
				case 5:
					break;
			}

		}
	}
}
