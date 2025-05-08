import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class WebCalculatorServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new CalculatorHandler());
        server.start();
        System.out.println("Server running at http://localhost:8000/");
    }

    static class CalculatorHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            String method=ex.getRequestMethod();
            String response;

            if (method.equalsIgnoreCase("GET")) {
                response = """
                        <html><body>
                        <h2>Simple Calculator</h2>
                        <form method='POST'>
                        Number 1: <input name='a'><br>
                        Number 2: <input name='b'><br>
                        <button name='op' value='add'>Add</button>
                        <button name='op' value='sub'>Subtract</button>
                        </form></body></html>""";
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(ex.getRequestBody()));
                String data = in.readLine();  // simple, one-line form data
                String a = "", b = "", op = "";

                if (data != null) {
                    for (String part : data.split("&")) {
                        if (part.contains("a=")) a = part.split("=")[1];
                        if (part.contains("b=")) b = part.split("=")[1];
                        if (part.contains("op=")) op = part.split("=")[1];
                    }
                }

                try {
                    int x = Integer.parseInt(URLDecoder.decode(a, "UTF-8"));
                    int y = Integer.parseInt(URLDecoder.decode(b, "UTF-8"));
                    int result = op.equals("add") ? x + y : x - y;

                    response = "<html><body><h2>Result: " + result + "</h2><a href='/'>Try Again</a></body></html>";
                } catch (Exception e) {
                    response = "<html><body><h2>Error: Invalid input.</h2><a href='/'>Try Again</a></body></html>";
                }
            }

            ex.sendResponseHeaders(200, response.length());
            OutputStream os = ex.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
