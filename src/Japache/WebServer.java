package Japache;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Par Alexis le 18/12/2016.
 */

public class WebServer implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>
{

    @Override
    public void completed(AsynchronousSocketChannel client, AsynchronousServerSocketChannel server) {
        server.accept(server , this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer , buffer , new Client(client));
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel server)
    {
        exc.printStackTrace();
    }
}
