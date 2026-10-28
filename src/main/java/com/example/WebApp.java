package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class WebApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", 8081), 0
        );

        server.createContext("/", exchange -> {
            String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Java Calculator</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            max-width: 500px;
                            margin: 60px auto;
                            padding: 20px;
                            background: #f3f6fa;
                        }
                        main {
                            background: white;
                            padding: 25px;
                            border-radius: 12px;
                        }
                        input, button {
                            padding: 10px;
                            margin: 6px 0;
                        }
                        button {
                            cursor: pointer;
                        }
                    </style>
                </head>
                <body>
                    <main>
                        <h1>Java Calculator</h1>
                        <p>Enter two numbers to perform a calculation.</p>
                        <label for="a">First number:</label><br>
                        <input id="a" type="number" value="5"><br>
                        <label for="b">Second number:</label><br>
                        <input id="b" type="number" value="3"><br>
                        <button onclick="calculate('add')">Add</button>
                        <button onclick="calculate('multiply')">Multiply</button>
                        <h2 id="result">Result will appear here.</h2>
                    </main>
                    <script>
                        function calculate(operation) {
                            const a = Number(document.getElementById('a').value);
                            const b = Number(document.getElementById('b').value);
                            const result = operation === 'add' ? a + b : a * b;
                            document.getElementById('result').textContent =
                                'Result: ' + result;
                        }
                    </script>
                </body>
                </html>
                """;

            byte[] response = html.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set(
                "Content-Type", "text/html; charset=UTF-8"
            );
            exchange.sendResponseHeaders(200, response.length);

            try (var output = exchange.getResponseBody()) {
                output.write(response);
            }
        });

        server.start();
        System.out.println("Web application started on port 8081");
    }
}

