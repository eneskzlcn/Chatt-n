

namespace InGameResources
{

    public static class ResourceManager {
        

        // when user names has read from server this array contains them as buffer.
        private static string[] readedUserNames  = null;
        // when room names has read from server this array contains them as buffer.
        private static string[] readedRoomNames = null;

        //getter and setters starts
        public static string[] getReadedUserNames()
        {
            return readedUserNames;
        }
        public static void setReadedUserNames(string[] newUserNames)
        {
            readedUserNames = newUserNames;
        }
        public static string[] getReadedRoomNames()
        {
            return readedRoomNames;
        }
        public static void setReadedRoomNames(string[] newRoomNames)
        {
            readedRoomNames = newRoomNames;
        }
        //getter setters finishes...
    }
    
}