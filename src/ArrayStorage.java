import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    final private int MAX_COUNT = 10000;
    private Resume[] storage = new Resume[MAX_COUNT];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        int sizeResume = size();
      if (sizeResume<MAX_COUNT)  storage[sizeResume]=r;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index>=0) return storage[index];
        else return null;
    }

    void delete(String uuid) {

        int index = getIndex(uuid);

        // проверка на наличие элемента
       if (index>=0) {
           // сдвиг массива на элемент начиная с удаляемого элемента до первого null или до конца массива если весь массив заполнен
           while (storage[index+1]!=null && index<MAX_COUNT-1){
               storage[index]=storage[index+1];
               index++;
           }
           storage[index] = null; // убрали последний элемент который уже сдвинут на предпоследнее место
       }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
                 return Arrays.copyOf(storage, size());
         }

    // Вернет 0 если первый элемент null или колличество элементов до первого null

    int size() {
        int i=0;
        while (storage[i]!=null) i++;
        return i;
    }

    // Получаем индекс в массиве по тексту резюме если вхождения нет то возвращает -1

     private int getIndex(String uuid) {
        int index=-1;
        int sizeR = size();
        if (sizeR>=0) {
            for (int i = 0; i<sizeR; i++) {
                if (storage[i].getUuid().equals(uuid))
                    index = i;
            }
        }
        return index;
    }
}
