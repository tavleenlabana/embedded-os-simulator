public class ResourceManager {
    int totalMemory = 500;
    int usedMemory = 0;
     boolean allocate(int mem) {
         if (usedMemory + mem <= totalMemory){
             usedMemory += mem;
             return true;
         }
         return false;
     }

     void release( int mem ) {
         usedMemory -= mem;
     }
}
