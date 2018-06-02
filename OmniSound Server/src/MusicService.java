import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MusicService implements Runnable 
{
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	MusicPlayer mp = new MusicPlayer();
	
	public MusicService(Socket aSocket)
	{
		s = aSocket;
	}
	
	public void run() 
	{
		try
		{
			try
			{
				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
				doService();
			}
			finally
			{
				s.close();
			}
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void doService() throws IOException
	{
		while (true)
		{
			if (!in.hasNext()) 
			{ 
				return; 
			}
			String command = in.next();
			if (command.equals("QUIT")) 
			{ 
				return; 
			}
			else 
			{ 
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command)
	{
		if (command.equals("START"))
		{
			mp.MusicStart();
		}
		else if (command.equals("STOP"))
		{
			mp.MusicStop();
		}
		else 
		{
			System.out.println("Invalid command");
		}
		out.flush();
	}
}
