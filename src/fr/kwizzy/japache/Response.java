package fr.kwizzy.japache;

import java.util.Map;

/**
 * Par Alexis le 18/12/2016.
 */

public class Response
{

    private String httpVersion;
    private int statusCode;
    private String reasonMessage;
    private Map<String, String> headers;
    private String body;

    public Response(String httpVersion,
                    int statusCode,
                    String reasonMessage,
                    Map<String, String> headers,
                    String body)
    {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reasonMessage = reasonMessage;
        this.headers = headers;
        this.body = body;
    }

    public String getHttpVersion()
    {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion)
    {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getReasonMessage()
    {
        return reasonMessage;
    }

    public void setReasonMessage(String reasonMessage)
    {
        this.reasonMessage = reasonMessage;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "httpVersion='" + httpVersion + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", reasonMessage='" + reasonMessage + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}

