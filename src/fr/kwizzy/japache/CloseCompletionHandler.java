package fr.kwizzy.japache;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Par Alexis le 18/12/2016.
 */

public class CloseCompletionHandler implements CompletionHandler<Integer, AsynchronousSocketChannel>
{
    public static final CloseCompletionHandler INSTANCE = new CloseCompletionHandler();

    private CloseCompletionHandler()
    {}


    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment)
    {
        try {
            attachment.close();
        } catch (IOException e) {
            failed(e , attachment);
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment)
    {
        exc.printStackTrace();
    }
}
