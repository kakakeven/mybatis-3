/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * Parsing utils
 *
 * XML 配置解析模块
 *
 * <p>
 *   parsing 模块的依赖关系：
 *   1 XPathParser 对外提供服务，提供通用的构造方法；普通的值类型 boolean、integer 等都可以通过 eval* 方法获取；
 *   2 对于字符串的解析 XPathParser 通过调用 PropertyParser 来实现变量替换；
 *   3 PropertyParser 中默认使用 GenericTokenParser 来解析变量名，使用 TokenHandler 来处理变量；
 *   4 TokenHandler 的默认实现为 VariableTokenHandler;
 * </p>
 */
package org.apache.ibatis.parsing;
