package ru.aora.erp.model.action;

public class ActionResult {
    public enum Result {
        ERROR,
        WARNING,
        SUCCESS
    }

    private Result result;
    private String message;

    public ActionResult(Result result) {
        this(result, "");
    }

    public ActionResult(Result result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Result getResult() {
        return result;
    }
}
