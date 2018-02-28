package com.example.demo.form;

/**
 *对象值转换
 * @param <S> 原对象
 * @param <T>目标对象
 */
public interface FormConvert<S,T> {
    T convert(S s);
}
