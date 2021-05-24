using System;
using System.Collections.Generic;
using System.Text;

namespace Server
{
    public class Room
    {
        public string name;
        private List<string> users = new List<string>();

        public Room(string name,string creatorName)
        {
            this.name = name;
            users.Add(creatorName);
        }

        public int userCount()
        {
            return users.Count;
        }
    }
}
