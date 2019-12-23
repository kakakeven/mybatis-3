/**
 * Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.parsing;

import java.util.Properties;

/**
 * 属性解析 Parser
 *
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
public class PropertyParser {

  /**
   * 解析器配置前缀
   */
  private static final String KEY_PREFIX = "org.apache.ibatis.parsing.PropertyParser.";
  /**
   * 指定在占位符替换时，是否使用默认值；例如 ${db.username:postgres}，如果 db.username 没有则默认使用 postgres;
   * 该值默认为 false
   *
   * @since 3.4.2
   */
  public static final String KEY_ENABLE_DEFAULT_VALUE = KEY_PREFIX + "enable-default-value";

  /**
   * 占位符和默认值的分隔符
   *
   * @since 3.4.2
   */
  public static final String KEY_DEFAULT_VALUE_SEPARATOR = KEY_PREFIX + "default-value-separator";

  private static final String ENABLE_DEFAULT_VALUE = "false";
  private static final String DEFAULT_VALUE_SEPARATOR = ":";

  private PropertyParser() {
    // Prevent Instantiation
    // 私有的构造方法
  }

  public static String parse(String string, Properties variables) {
    // 指定属性处理类
    VariableTokenHandler handler = new VariableTokenHandler(variables);
    // 初始化通用的占位符解析器，指定占位符格式例: ${name}
    GenericTokenParser parser = new GenericTokenParser("${", "}", handler);
    return parser.parse(string);
  }

  private static class VariableTokenHandler implements TokenHandler {
    private final Properties variables;
    private final boolean enableDefaultValue;
    private final String defaultValueSeparator;

    private VariableTokenHandler(Properties variables) {
      this.variables = variables;
      // 获取是否启用默认值
      this.enableDefaultValue = Boolean.parseBoolean(getPropertyValue(KEY_ENABLE_DEFAULT_VALUE, ENABLE_DEFAULT_VALUE));
      // 获取参数默认值之间的分割符
      this.defaultValueSeparator = getPropertyValue(KEY_DEFAULT_VALUE_SEPARATOR, DEFAULT_VALUE_SEPARATOR);
    }

    /**
     * 如果属性值为空，或者没有配置相应的key，则返回默认值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private String getPropertyValue(String key, String defaultValue) {
      return (variables == null) ? defaultValue : variables.getProperty(key, defaultValue);
    }

    @Override
    public String handleToken(String content) {
      // 处理占位符中变量的值，例如 ${name}，content 内容为 name,如果属性配置中配置了 name = xxx，则返回 xxx
      if (variables != null) {
        String key = content;
        if (enableDefaultValue) {
          final int separatorIndex = content.indexOf(defaultValueSeparator);
          String defaultValue = null;
          if (separatorIndex >= 0) {
            key = content.substring(0, separatorIndex);
            defaultValue = content.substring(separatorIndex + defaultValueSeparator.length());
          }
          if (defaultValue != null) {
            return variables.getProperty(key, defaultValue);
          }
        }
        // 如果配置了相应的属性值，则返回
        if (variables.containsKey(key)) {
          return variables.getProperty(key);
        }
      }
      return "${" + content + "}";
    }
  }

}
