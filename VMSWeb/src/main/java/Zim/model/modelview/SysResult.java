package Zim.model.modelview;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class SysResult<T> {
    private boolean result = false;
    private String message = "";
    private T content;


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
