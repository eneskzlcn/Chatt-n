using System;
using System.Collections.Generic;
using System.Text;

namespace Utilities
{
    public static class Utilities
    {

        //this utility makes an array from given list 
        public static T[] ListToArray<T>(List<T> list)
        {
            T[] array = new T[list.Count];
            int i = 0;
            foreach(var item in list)
            {
                array[i] = item;
                i++;
            }
            return array;
        }
    }
}
