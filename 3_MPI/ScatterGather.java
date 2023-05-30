import mpi.MPI;
import java.util.*;
import mpi.*;

//The code you provided seems to be a Java program that uses the MPI (Message Passing Interface) library for parallel computing. MPI is commonly used in distributed computing environments, such as clusters or supercomputers, to enable communication and coordination among multiple processes.

public class ScatterGather {
	public static void main(String[] args) {
		MPI.Init(args);

		// These lines retrieve the rank (identification number) and size (total number
		// of processes) of the current MPI process. The COMM_WORLD object represents
		// the communicator that encompasses all processes.
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

		int unitsize = 5;
		// This variable specifies the rank of the root process, which is responsible
		// for scattering or gathering data.
		int root = 0;
		int send_buffer[] = null;

		send_buffer = new int[unitsize * size];
		// This array will hold the data to be sent from the root process during
		// scattering or receive the gathered data at the root process.
		int receive_buffer[] = new int[unitsize];
		int new_receive_buffer[] = new int[size];

		if (rank == root) {
			int total_elements = unitsize * size;
			System.out.println(total_elements + " Elements to be scattered in " + size + " cores.");
			for (int i = 0; i < total_elements; i++) {
				System.out.println("Element " + i + " = " + i);
				send_buffer[i] = i;
			}
		}

		MPI.COMM_WORLD.Scatter(
				send_buffer, 0, unitsize, MPI.INT, receive_buffer, 0, unitsize, MPI.INT, root);
		// The send buffer, which is an array containing the data to be scattered
		// The starting index in the send_buffer array
		// The number of elements to be scattered
		// Type
		// The receive buffer, that will hold the received elements after scattering.
		// The starting index in the receive_buffer array
		// The number of elements to be scattered
		// Type
		// The rank of the root process, which is responsible for scattering the data

		// The Scatter() method divides the elements in the send_buffer array among all
		// processes in MPI.COMM_WORLD, including the root process. Each process
		// receives a portion of the data into its respective receive_buffer array.

		for (int i = 1; i < unitsize; i++) {
			receive_buffer[0] += receive_buffer[i];
		}

		System.out.println("Intermediate sum at " + rank + " is " + receive_buffer[0]);

		// The Gather() method is called to gather the calculated values from each
		// process, including the root process (root

		// receive_buffer: The send buffer, which contains the calculated value
		// (receive_buffer[0]) for the current process.
		// 0: The starting index in the receive_buffer array from where the data should
		// be gathered.
		// 1: The number of elements to be gathered from the receive_buffer array (in
		// this case, only the calculated value).
		// MPI.INT: The type of the elements in the send buffer.
		// new_receive_buffer: The receive buffer, which is an array that will hold the
		// gathered elements at the root process.
		// 0: The starting index in the new_receive_buffer array where the gathered data
		// should be stored.
		// 1: The number of elements to be received and stored in the new_receive_buffer
		// array (in this case, only one element).
		// MPI.INT: The type of the elements in the receive buffer.
		// root: The rank of the root process, which is responsible for gathering the
		// data.

		MPI.COMM_WORLD.Gather(
				receive_buffer, 0, 1, MPI.INT, new_receive_buffer, 0, 1, MPI.INT, root);

		if (rank == root) {
			int total_sum = 0;
			for (int i = 0; i < size; i++) {
				total_sum += new_receive_buffer[i];
			}
			System.out.println("Total sum after gathering is " + total_sum);
		}
		MPI.Finalize();
		// The MPI.Finalize() method is used to finalize the MPI environment and should
		// be called at the end of any MPI program. It ensures that all processes in the
		// MPI communicator have completed their tasks and releases any resources
		// allocated by the MPI library.
	}
}
