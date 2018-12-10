package ru.bellintegrator.school.personnelregistry.api.view.wrapper;

public class Result {

    public static final String RESULT_TRUE = "success";
    public static final String RESULT_FALSE = "no success";

    private final Object  result;

    public Result(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
