package kr.co.m2m.framework.web.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

/**
 * Created by minjae on 2016-04-07.
 */
@ToString
public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = -2152396874183804360L;
	
	private boolean isSuccess;
    private String message;

    private T data;

    private Map<String, Object> extraData;
    private String extraString;

    public ResultModel() {
        this.isSuccess = true;
        this.message = null;
        this.data = null;
        this.extraData = new HashMap<>();
    }

    public ResultModel(T data) {
        this.data = data;
        this.isSuccess = true;
        this.message = null;
        this.extraData = new HashMap<>();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void put(String key, Object value) {
        extraData.put(key, value);
    }

    public Object get(String key) {
        return extraData.get(key);
    }

    public String getExtraString() {
        return extraString;
    }

    public void setExtraString(String extraString) {
        this.extraString = extraString;
    }
}
