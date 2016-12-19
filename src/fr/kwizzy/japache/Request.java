package fr.kwizzy.japache;

import java.util.Map;

/**
 * Par Alexis le 18/12/2016.
 */

public class Request
{
    private String method;
    private String requestURI; //
    private String httpVersion;
    private Map<String , String> headers;
    private String data;

    public Request(String method, String requestURI, String httpVersion, Map<String, String> headers, String data)
    {
        this.method = method;
        this.requestURI = requestURI;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.data = data;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getRequestURI()
    {
        return requestURI;
    }

    public void setRequestURI(String requestURI)
    {
        this.requestURI = requestURI;
    }

    public String getHttpVersion()
    {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion)
    {
        this.httpVersion = httpVersion;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "method='" + method + '\'' +
                ", requestURI='" + requestURI + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                ", headers=" + headers +
                ", data='" + data + '\'' +
                '}';
    }
}
