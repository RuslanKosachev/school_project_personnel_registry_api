package ru.bellintegrator.school.personnelregistry.api.view.wrapper;

/**
 * Используется с целью оборачивания сообщения ответа от контроллера об успешности изменения данных
 * в свойство “result”:{...} при сериализации в json
 *
 * Если данныее изменены успешно в поле {@like Result#result} должно содержаться сообщение {@link Result#RESULT_TRUE}
 * инече - {@link Result#RESULT_FALSE}
 *
 */
public class Result {

    public static final String RESULT_TRUE = "success";
    public static final String RESULT_FALSE = "no success";

    private String result;

    public Result() {
    }

    public Result(String result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
