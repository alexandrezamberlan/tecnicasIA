using System;
using System.Net;  
using System.Net.Sockets;  
using System.Threading;

namespace SocketCSharp
{
    class Program
    {
        static Socket socket;

        static void criaClientSocket() 
        {
            try 
            {
                // Establish the remote endpoint for the socket.  
               
                IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());  
                IPAddress ipAddress = ipHostInfo.AddressList[0];  
                IPEndPoint remoteEP = new IPEndPoint(ipAddress,1234);  
           
                //cria um socket TCP ou UDP para se conectar ao servidor de ip "localhost" porta
                socket = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);  

                socket.Connect(remoteEP);  
  
                Console.WriteLine("Cliente conectado em {0}",  
                    socket.RemoteEndPoint.ToString());

                // Release the socket.  
                // socket.Shutdown(SocketShutdown.Both);  
                // socket.Close();
                
            } catch (ArgumentNullException ane) {  
                Console.WriteLine("ArgumentNullException : {0}",ane.ToString());  
            } catch (SocketException se) {  
                Console.WriteLine("Problemas na conxão: {0}",se.ToString());  
            } catch (Exception e) {  
                Console.WriteLine("Unexpected exception : {0}", e.ToString());  
            }
        }

        static void enviaMsg() 
        {
            try {
                while (true) 
                {
                    String mensagem = Console.ReadLine();
                    Comunicador.enviaMensagem(socket, mensagem); //envia uma mensagem pela rede
                }
            } catch (Exception e) {
                Console.WriteLine(e);
            }
        }

        static void recebeMsg() 
        {
            try {
                String msg;
                do 
                {
                    msg = "SMA mandou: " + Comunicador.recebeMensagem(socket); 
                
                    Console.WriteLine(msg);
                } while(true);
                
            } catch (Exception e) {
                Console.WriteLine(e);
            }
        }

        static void Main(string[] args)
        {
            criaClientSocket();
            Thread te = new Thread(new ThreadStart(enviaMsg));
            Thread tr = new Thread(new ThreadStart(recebeMsg));
            te.Start();
            tr.Start();
        }
    }
}
