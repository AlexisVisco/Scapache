package Japache;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Par Alexis le 18/12/2016.
 */

public class Client implements CompletionHandler<Integer, ByteBuffer>
{
    public static final String SP = " ";
    public static final String CRLF = "\r\n";

    private final AsynchronousSocketChannel client;

    public Client(AsynchronousSocketChannel client)
    {
        this.client = client;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if(result == -1)
            return;
        byte[] read = new byte[result];
        buffer.flip();
        buffer.get(read);
        Request request = parseRequest(new String(read));
        System.out.println(request.getMethod() + " " + request.getHeaders().get("Host") + request.getRequestURI());
        Response response = new Response(request.getHttpVersion() , 200 , "OK" , new HashMap<>(), "<h1>Hello World</h1>");

        response.getHeaders().put("Content-Type" , "text/html");
        client.write(ByteBuffer.wrap(serializeResponse(response).getBytes()) , client , CloseCompletionHandler.INSTANCE);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        exc.printStackTrace();
    }

    public static Request parseRequest(String d){
        String[] lines = d.split(CRLF);
        String[] requestLine = lines[0].split(SP);
        String data = "";
        Map<String , String> headers = new HashMap<>();
        int i = 1;
        for (; i < lines.length; i++) {
            String[] header = lines[i].split(":" , 2);
            if(header.length != 2)
               break;
            headers.put(header[0] , header[1].replaceFirst(" " , ""));
        }
        for(; i < lines.length; i++)
            data += lines[i];
        return new Request(requestLine[0] , requestLine[1] , requestLine[2] , headers , data);
    }

    public static String serializeResponse(Response response){
        // Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
        String s =  response.getHttpVersion() + SP + response.getStatusCode() + SP + response.getReasonMessage() + CRLF
                + response.getHeaders().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(CRLF)) + CRLF
                + CRLF + response.getBody() + CRLF;
        System.out.println("=> " + s + " <=");
        return s;
    }
}
