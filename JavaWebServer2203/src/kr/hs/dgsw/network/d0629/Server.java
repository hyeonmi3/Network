package kr.hs.dgsw.network.d0629;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Main Class
 */
public class Server {
	private final String DEFAULT_HOSTNAME = "0.0.0.0";
	private final int DEFAULT_PORT = 8080;
	private final int DEFAULT_BACKLOG = 0;
	private HttpServer server = null;

	/**
	 * ������
	 */
	public Server() throws IOException {
		createServer(DEFAULT_HOSTNAME, DEFAULT_PORT);
	}

	public Server(int port) throws IOException {
		createServer(DEFAULT_HOSTNAME, port);
	}

	public Server(String host, int port) throws IOException {
		createServer(host, port);
	}

	/**
	 * ���� ����
	 */
	private void createServer(String host, int port) throws IOException {
		// HTTP Server ����
		this.server = HttpServer.create(new InetSocketAddress(host, port), DEFAULT_BACKLOG);
		// HTTP Server Context ����
		server.createContext("/", new RootHandler());
	}

	/**
	 * ���� ����
	 */
	public void start() {
		server.start();
	}

	/**
	 * ���� ����
	 */
	public void stop(int delay) {
		server.stop(delay);
	}

	public static void main(String[] args) {

		Server httpServerManager = null;

		try {
			// ���� �α�
			System.out.println(String.format("[%s][HTTP SERVER][START]",
					new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())));

			// ���� ����
			httpServerManager = new Server("0.0.0.0", 3000);
			httpServerManager.start();
			// Shutdown Hook
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					// ���� �α�
					System.out.println(String.format("[%s][HTTP SERVER][STOP]",
							new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())));
				}
			}));

			// Enter�� �Է��ϸ� ����
			System.out.print("Please press 'Enter' to stop the server.");
			System.in.read();

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// ����
			// 0�� ����� ����
			httpServerManager.stop(0);
		}
	}

	/**
	 * Sub Class
	 */
	class RootHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {

			// Initialize Response Body
			OutputStream respBody = exchange.getResponseBody();

			try {
				// Write Response Body
				StringBuilder sb = new StringBuilder();
				sb.append("<!DOCTYPE html>");
				sb.append("<html>");
				sb.append("   <head>");
				sb.append("       <meta charset=\"UTF-8\">");
				sb.append("       <meta name=\"author\" content=\"Dochi\">");
				sb.append("       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				sb.append("       <title>Example</title>");
				sb.append("   </head>");
				sb.append("   <body>");
				sb.append("       <h5>Hello, HttpServer!!!</h5>");
				sb.append("       <span>Method: " + (exchange.getRequestMethod()) + "</span></br>");
				sb.append("       <span>URI: " + (exchange.getRequestURI()) + "</span></br>");
				sb.append("       <span>PATH: " + (exchange.getRequestURI().getPath()) + "</span></br>");
				sb.append("       <span>QueryString: " + (exchange.getRequestURI().getQuery()) + "</span></br>");
				sb.append("   </body>");
				sb.append("</html>");

				// Encoding to UTF-8
				ByteBuffer bb = Charset.forName("UTF-8").encode(sb.toString());
				int contentLength = bb.limit();
				byte[] content = new byte[contentLength];
				bb.get(content, 0, contentLength);

				// Set Response Headers
				Headers headers = exchange.getResponseHeaders();
				headers.add("Content-Type", "text/html;charset=UTF-8");
				headers.add("Content-Length", String.valueOf(contentLength));

				// Send Response Headers
				exchange.sendResponseHeaders(200, contentLength);

				respBody.write(content);

				// Close Stream
				// �ݵ��, Response Header�� ���� �Ŀ� �ݾƾ���
				respBody.close();

			} catch (IOException e) {
				e.printStackTrace();

				if (respBody != null) {
					respBody.close();
				}
			} finally {
				exchange.close();
			}
		}
	}
}