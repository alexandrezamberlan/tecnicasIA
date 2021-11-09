using System;
using System.Net;  
using System.Net.Sockets;  
using System.Text; 

namespace SocketCSharp
{
    class Comunicador
    {
        public static String recebeMensagem(Socket s) 
        {
            try
            {
                byte[] rcvLenBytes = new byte[4];
                s.Receive(rcvLenBytes);
                int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
                byte[] rcvBytes = new byte[rcvLen];
                s.Receive(rcvBytes);
                String rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);            
                return rcv;
            } catch (Exception e) {
                Console.WriteLine(e);
                return null;
            }
        }

        public static void enviaMensagem(Socket socket, String mensagem) 
        {
            try 
            {
                // Sending
                int toSendLen = System.Text.Encoding.ASCII.GetByteCount(mensagem);
                byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(mensagem);
                byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
                socket.Send(toSendLenBytes);
                socket.Send(toSendBytes);
            } catch (Exception e) {
                Console.WriteLine(e);
            }
        }
    }
}