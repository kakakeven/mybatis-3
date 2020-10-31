/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ibatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.ibatis.reflection.ReflectionException;

/**
 * Ambiguous：模糊不清的
 * <p>
 * 模糊不清的调用方法执行器，直接抛出反射异常
 */
public class AmbiguousMethodInvoker extends MethodInvoker {
  private final String exceptionMessage;

  public AmbiguousMethodInvoker(Method method, String exceptionMessage) {
    super(method);
    this.exceptionMessage = exceptionMessage;
  }

  @Override
  public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
    throw new ReflectionException(exceptionMessage);
  }
}
