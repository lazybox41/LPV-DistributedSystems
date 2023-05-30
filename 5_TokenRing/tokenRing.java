import java.io.*;
import java.lang.*;
import java.util.Scanner;

class TokenRing {
	public static void main(String args[]) throws Throwable {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter number of nodes: ");
		int n = Integer.parseInt(s.nextLine());
		int token = 0;
		int ch = 1;
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		try {
			while (true) {
				System.out.println("Enter sender node");
				int sender = Integer.parseInt(s.nextLine());
				System.out.println("Enter receiver node");
				int receiver = Integer.parseInt(s.nextLine());
				System.out.println("Enter message");
				String msg = s.nextLine();
				for (int i = token; i != sender; i = (i + 1) % n) {
					System.out.print(" " + i + " -> ");
				}
				System.out.print(" " + sender + " ");
				System.out.println("Sending data " + msg);
				for (int i = sender + 1; i != (receiver + 1) % n; i = (i + 1) % n) {
					System.out.println("data forwarded -> " + (i % n));
				}

				System.out.println("Receiver " + receiver + " received data " + msg);
				token = sender;
				System.out.println("Token now with " + token);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}