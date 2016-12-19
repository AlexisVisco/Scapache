package fr.kwizzy.japache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Scanner;

/**
 * Par Alexis le 18/12/2016.
 */

public class Application
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(3000));
        server.accept(server , new WebServer());
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            switch(scanner.nextLine().toLowerCase()) {
                case "exit":
                    server.close();
                    return;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
