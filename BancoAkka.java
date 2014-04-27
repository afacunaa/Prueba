package banco;
import java.util.Scanner;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class BancoAkka {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int n=key.nextInt();
		int m=key.nextInt();
		int arreglo[] = new int[n];
		int arreglo2[] = new int[m];

		for (int i = 0; i < arreglo.length; i++) {
			arreglo[i]=key.nextInt();
		}
		for (int i = 0; i < arreglo2.length; i++) {
			arreglo2[i]=key.nextInt();
		}

		ActorSystem system=ActorSystem.create("BancoAkka");
		ActorRef elbanco = system.actorOf(Props.create(ActorBanco.class), "elbanco");
		ActorRef a = system.actorOf(Props.create(ActorCliente.class), "a");
		ActorRef b = system.actorOf(Props.create(ActorCliente.class), "b");

		for (int i = 0; i < arreglo.length; i++) {
			a.tell(arreglo[i], elbanco);
		}
		for (int i = 0; i < arreglo2.length; i++) {
			b.tell(arreglo2[i], elbanco);
		}
		system.shutdown();
		system.awaitTermination();//JOIN
	}
}
