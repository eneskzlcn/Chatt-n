

namespace InGameResources
{

    public static class ResourceManager {
        

        // when a user name is read
        private static string[] readedUserNames  = null;


        public static string[] getReadedUserNames()
        {
            return readedUserNames;
        }
        public static void setReadedUserNames(string[] newUserNames)
        {
            readedUserNames = newUserNames;
        }

    }
    
}