package com.pivotal.cloud.boot.pojo;

import cn.hutool.core.util.ObjectUtil;
import com.pivotal.cloud.boot.constant.Constants;
import com.pivotal.cloud.boot.utils.ApiResultUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @className: com.pivotal.cloud.boot.pojo.ReturnResult
 * @projectName: 封装PivotalCloud项目-ReturnResult类
 * @module: PivotalCloud项目-ReturnResult类，主要位于ReturnResult模块的业务场景
 * @content: ReturnResult类，主要用于完成ReturnResult的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 02:50
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public class ReturnResult<T> implements Serializable {
    /**
     * 状态码为成功
     */
    public static final Predicate<Payload<?>> CODE_SUCCESS = payload -> Constants.SUCCESS == payload.getCode();

    /**
     * 数据有值
     */
    public static final Predicate<Payload<?>> HAS_DATA = payload -> ObjectUtil.isNotEmpty(payload.getData());

    /**
     * 数据有值,并且包含元素
     */
    public static final Predicate<Payload<?>> HAS_ELEMENT = payload -> ObjectUtil.isNotEmpty(payload.getData());

    /**
     * 状态码为成功并且有值
     */
    public static final Predicate<Payload<?>> DATA_AVAILABLE = CODE_SUCCESS.and(HAS_DATA);


    private final Payload<T> original;


    ReturnResult(Payload<T> original) {
        this.original = original;
    }


    public static <T> ReturnResult<T> of(Payload<T> original) {
        return new ReturnResult<>(Objects.requireNonNull(original));
    }


    /**
     * 观察原始值
     *
     * @return R
     */
    public Payload<T> peek() {
        return original;
    }

    /**
     * 读取{@code code}的值
     *
     * @return 返回code的值
     */
    public int getCode() {
        return original.getCode();
    }

    /**
     * 读取{@code data}的值
     *
     * @return 返回 Optional 包装的data
     */
    public Optional<T> getData() {
        return Optional.ofNullable(original.getData());
    }

    /**
     * 有条件地读取{@code data}的值
     *
     * @param predicate
     *         断言函数
     *
     * @return 返回 Optional 包装的data,如果断言失败返回empty
     */
    public Optional<T> getDataIf(Predicate<? super Payload<?>> predicate) {
        return predicate.test(original) ? getData() : Optional.empty();
    }

    /**
     * 读取{@code msg}的值
     *
     * @return 返回Optional包装的 msg
     */
    public Optional<String> getMessage() {
        return Optional.ofNullable(original.getMessage());
    }

    /**
     * 对{@code code}的值进行相等性测试
     *
     * @param value
     *         基准值
     *
     * @return 返回ture表示相等
     */
    public boolean codeEquals(int value) {
        return original.getCode() == value;
    }


    /**
     * 对{@code code}的值进行相等性测试
     *
     * @param value
     *         基准值
     *
     * @return 返回ture表示不相等
     */
    public boolean codeNotEquals(int value) {
        return !codeEquals(value);
    }

    /**
     * 是否成功
     *
     * @return 返回ture表示成功
     *
     * @see Constants#SUCCESS
     */
    public boolean isSuccess() {
        return codeEquals(Constants.SUCCESS);
    }

    /**
     * 是否失败
     *
     * @return 返回ture表示失败
     */
    public boolean notSuccess() {
        return !isSuccess();
    }

    /**
     * 断言{@code code}的值
     *
     * @param expect
     *         预期的值
     * @param func
     *         用户函数,负责创建异常对象
     * @param <Ex>
     *         异常类型
     *
     * @return 返回实例，以便于继续进行链式操作
     *
     * @throws Ex
     *         断言失败时抛出
     */
    public <Ex extends Exception> ReturnResult<T> assertCode(int expect, Function<? super Payload<T>, ? extends Ex> func)
            throws Ex {
        if (codeNotEquals(expect)) {
            throw func.apply(original);
        }
        return this;
    }

    /**
     * 断言成功
     *
     * @param func
     *         用户函数,负责创建异常对象
     * @param <Ex>
     *         异常类型
     *
     * @return 返回实例，以便于继续进行链式操作
     *
     * @throws Ex
     *         断言失败时抛出
     */
    public <Ex extends Exception> ReturnResult<T> assertSuccess(Function<? super Payload<T>, ? extends Ex> func) throws Ex {
        return assertCode(Constants.SUCCESS, func);
    }

    /**
     * 断言业务数据有值
     *
     * @param func
     *         用户函数,负责创建异常对象
     * @param <Ex>
     *         异常类型
     *
     * @return 返回实例，以便于继续进行链式操作
     *
     * @throws Ex
     *         断言失败时抛出
     */
    public <Ex extends Exception> ReturnResult<T> assertDataNotNull(Function<? super Payload<T>, ? extends Ex> func) throws Ex {
        if (Objects.isNull(original.getData())) {
            throw func.apply(original);
        }
        return this;
    }

    /**
     * 断言业务数据有值,并且包含元素
     *
     * @param func
     *         用户函数,负责创建异常对象
     * @param <Ex>
     *         异常类型
     *
     * @return 返回实例，以便于继续进行链式操作
     *
     * @throws Ex
     *         断言失败时抛出
     */
    public <Ex extends Exception> ReturnResult<T> assertDataNotEmpty(Function<? super Payload<T>, ? extends Ex> func) throws Ex {
        if (ObjectUtil.isEmpty(original.getData())) {
            throw func.apply(original);
        }
        return this;
    }

    /**
     * 对业务数据(data)转换
     *
     * @param mapper
     *         业务数据转换函数
     * @param <U>
     *         数据类型
     *
     * @return 返回新实例，以便于继续进行链式操作
     */
    public <U> ReturnResult<U> map(Function<? super T, ? extends U> mapper) {
        Payload<U> result = ApiResultUtil.restResult(original.getCode(), original.getSuccess(), original.getMessage(), mapper.apply(original.getData()));
        return of(result);
    }

    /**
     * 对业务数据(data)转换
     *
     * @param predicate
     *         断言函数
     * @param mapper
     *         业务数据转换函数
     * @param <U>
     *         数据类型
     *
     * @return 返回新实例，以便于继续进行链式操作
     *
     * @see ReturnResult#CODE_SUCCESS
     * @see ReturnResult#HAS_DATA
     * @see ReturnResult#HAS_ELEMENT
     * @see ReturnResult#DATA_AVAILABLE
     */
    public <U> ReturnResult<U> mapIf(Predicate<? super Payload<T>> predicate, Function<? super T, ? extends U> mapper) {
        Payload<U> result = ApiResultUtil.restResult(original.getCode(), original.getSuccess(), original.getMessage(), mapper.apply(original.getData()));
        return of(result);
    }

    // ~ 数据消费
    // ===================================================================================================

    /**
     * 消费数据,注意此方法保证数据可用
     *
     * @param consumer
     *         消费函数
     */
    public void useData(Consumer<? super T> consumer) {
        consumer.accept(original.getData());
    }

    /**
     * 条件消费(错误代码匹配某个值)
     *
     * @param consumer
     *         消费函数
     * @param codes
     *         错误代码集合,匹配任意一个则调用消费函数
     */
    public void useDataOnCode(Consumer<? super T> consumer, int... codes) {
        useDataIf(o -> Arrays.stream(codes).filter(c -> original.getCode() == c).findFirst().isPresent(), consumer);
    }

    /**
     * 条件消费(错误代码表示成功)
     *
     * @param consumer
     *         消费函数
     */
    public void useDataIfSuccess(Consumer<? super T> consumer) {
        useDataIf(CODE_SUCCESS, consumer);
    }

    /**
     * 条件消费
     *
     * @param predicate
     *         断言函数
     * @param consumer
     *         消费函数,断言函数返回{@code true}时被调用
     *
     * @see ReturnResult#CODE_SUCCESS
     * @see ReturnResult#HAS_DATA
     * @see ReturnResult#HAS_ELEMENT
     * @see ReturnResult#DATA_AVAILABLE
     */
    public void useDataIf(Predicate<? super Payload<T>> predicate, Consumer<? super T> consumer) {
        if (predicate.test(original)) {
            consumer.accept(original.getData());
        }
    }

}
