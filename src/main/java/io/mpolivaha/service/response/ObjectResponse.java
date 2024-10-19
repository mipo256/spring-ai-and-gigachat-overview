package io.mpolivaha.service.response;

public record ObjectResponse<T>(String object, T data) {

}
