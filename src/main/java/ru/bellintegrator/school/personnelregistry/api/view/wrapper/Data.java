package ru.bellintegrator.school.personnelregistry.api.view.wrapper;

/**
 * Используется с целью оборачивания ответа контроллера в свойство  “data”:{...} при сериализации в json
 *
 * Должен содержать возвращаемые данные после контроллера в поле {@like #data}
 *
 * @param <T> тип который возвращает контроллер
 */
public class Data<T> {

    private T data;

    public Data() { }

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
