using System;
using System.Collections.Generic;
using System.Text;
using Server;

namespace Chat_n_Server
{
    class Test
    {
        public static void Main()
        {
            Server.Server.Start();  
            Console.ReadKey();
        }
    }
}
