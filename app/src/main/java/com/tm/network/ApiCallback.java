package com.tm.network;


import com.tm.models.BaseResponseModal;

public interface ApiCallback<T> {

    void onSuccess(T t);

    void onError(BaseResponseModal responseModal);
}
