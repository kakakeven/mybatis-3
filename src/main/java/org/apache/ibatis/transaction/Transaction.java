/**
 *    Copyright 2009-2020 the original author or authors.
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
package org.apache.ibatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 包装一个数据库连接，处理数据库连接的生命周期:
 * 包括：创建、准备、提交、回滚和关闭
 *
 * Wraps a database connection.
 * Handles the connection lifecycle that comprises: its creation, preparation, commit/rollback and close.
 *
 * @author Clinton Begin
 */
public interface Transaction {

  /**
   * 返回包装的数据库连接
   * Retrieve inner database connection.
   *
   * @return DataBase connection
   * @throws SQLException
   *           the SQL exception
   */
  Connection getConnection() throws SQLException;

  /**
   * 提交事务，如果 autoCommit 为 true，本操作无效
   *
   * Commit inner database connection.
   * @throws SQLException
   *           the SQL exception
   */
  void commit() throws SQLException;

  /**
   * 回滚事务，如果 autoCommit 为 true，本操作无效
   *
   * Rollback inner database connection.
   * @throws SQLException
   *           the SQL exception
   */
  void rollback() throws SQLException;

  /**
   * 关闭数据库连接
   *
   * Close inner database connection.
   * @throws SQLException
   *           the SQL exception
   */
  void close() throws SQLException;

  /**
   * 获取事务超时时间
   *
   * Get transaction timeout if set.
   *
   * @return the timeout
   * @throws SQLException
   *           the SQL exception
   */
  Integer getTimeout() throws SQLException;

}
