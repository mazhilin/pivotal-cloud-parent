package com.pivotal.cloud.datasource.boot.processor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @packageName com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceExpressionProcessor
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceExpressionProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceExpressionProcessor类
 * @content: DynamicDatasourceExpressionProcessor
 * @description:
 *     pivotalCloud项目-DynamicDatasourceExpressionProcessor类,主要用作DynamicDatasourceExpressionProcessor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:24
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceExpressionProcessor extends DynamicDatasourceProcessor {
  /** 参数发现器 */
  private static final ParameterNameDiscoverer NAME_DISCOVERER =
      new DefaultParameterNameDiscoverer();
  /** Express语法解析器 */
  private static final ExpressionParser PARSER = new SpelExpressionParser();
  /**
   * 解析上下文的模板 对于默认不设置的情况下,从参数中取值的方式 #param1 设置指定模板 ParserContext.TEMPLATE_EXPRESSION 后的取值方式:
   * #{#param1} issues:
   * https://github.com/baomidou/dynamic-datasource-spring-boot-starter/issues/199
   */
  private ParserContext parserContext =
      new ParserContext() {

        @Override
        public boolean isTemplate() {
          return false;
        }

        @Override
        public String getExpressionPrefix() {
          return null;
        }

        @Override
        public String getExpressionSuffix() {
          return null;
        }
      };

  private BeanResolver beanResolver;

  public void setParserContext(ParserContext parserContext) {
    this.parserContext = parserContext;
  }

  public void setBeanResolver(BeanResolver beanResolver) {
    this.beanResolver = beanResolver;
  }

  /**
   * 抽象匹配条件 匹配才会走当前执行器否则走下一级执行器
   *
   * @param identifier @DynamicDatasource注解里的内容
   * @return 是否匹配
   */
  @Override
  public boolean matches(String identifier) {
    return true;
  }

  /**
   * 抽象最终决定数据源
   *
   * @param invocation 方法执行信息
   * @param identifier DS注解里的内容
   * @return 数据源名称
   */
  @Override
  public String finalizeDatasource(MethodInvocation invocation, String identifier) {
    Method method = invocation.getMethod();
    Object[] arguments = invocation.getArguments();
    ExpressionRootObject rootObject =
        new ExpressionRootObject(method, arguments, invocation.getThis());
    StandardEvaluationContext context =
        new MethodBasedEvaluationContext(rootObject, method, arguments, NAME_DISCOVERER);
    context.setBeanResolver(beanResolver);
    final Object value = PARSER.parseExpression(identifier, parserContext).getValue(context);
    return value == null ? null : value.toString();
  }

  @Getter
  @RequiredArgsConstructor
  public static class ExpressionRootObject {
    private final Method method;

    private final Object[] args;

    private final Object target;
  }

}
