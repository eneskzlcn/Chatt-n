using System;
using System.Collections.Generic;
using System.Text;

namespace Server
{
    public class Room
    {
        public string name;
        public int capacity;
        private List<string> users = new List<string>();

        public Room(string name,string creatorName,int capacity)
        {
            this.name = name;
            this.capacity = capacity;
            users.Add(creatorName);
        }

        public int userCount()
        {
            return users.Count;
        }
    }
}
