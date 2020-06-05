package ru.aora.erp.domain.model;


public final class MsgServiceResult {
    private final boolean success;
    private final String msg;

    public MsgServiceResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static MsgServiceResult success(String msg) {
        return new MsgServiceResult(true, msg);
    }

    public static MsgServiceResult failed(String msg) {
        return new MsgServiceResult(false, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
