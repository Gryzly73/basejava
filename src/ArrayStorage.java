import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int MAX_COUNT = 10000;
    private int sizeResume = 0;
    private Resume[] storage = new Resume[MAX_COUNT];

    void clear() {
        Arrays.fill(storage, null);
        sizeResume = 0;
    }

    void save(Resume r) {
        if (sizeResume < MAX_COUNT) {
            storage[sizeResume] = r;
            ++sizeResume;
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            return null;
        }
    }

    void delete(String uuid) {

        int index = getIndex(uuid);

        // проверка на наличие элемента
        if (index >= 0) {
            // сдвиг массива на элемент начиная с удаляемого элемента до первого null или до конца массива если весь массив заполнен
            while (storage[index + 1] != null && index < MAX_COUNT - 1) {
                storage[index] = storage[index + 1];
                index++;
            }
            storage[index] = null; // убрали последний элемент который уже сдвинут на предпоследнее место
            --sizeResume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    // Вернет 0 если первый элемент null или колличество элементов до первого null

    int size() {
        int i = 0;
        while (storage[i] != null) i++;
        sizeResume = i;
        return i;
    }


    // Получаем индекс в массиве по тексту резюме если вхождения нет то возвращает -1

    private int getIndex(String uuid) {
        int index = -1;
        if (sizeResume >= 0) {
            for (int i = 0; i < sizeResume; i++) {
                if (storage[i].getUuid().equals(uuid))
                    index = i;
            }
        }
        return index;
    }
}
