package com.pivotal.cloud.datasource.boot.transaction;


import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.Savepoint;
import java.util.LinkedList;
import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.boot.transaction.SavePointHolder
 * @projectName: Coocaloud
 * @className: SavePointHolder
 * @title: 封装Coocaloud项目-SavePointHolder类
 * @content: SavePointHolder
 * @description: Coocaloud项目-SavePointHolder类,主要用作SavePointHolder。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 12:40
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 Coocaloud Systems Incorporated. All rights reserved.
 */
public class SavePointHolder {
    private static final String SAVEPOINT_NAME_PREFIX = "DYNAMIC_";
    private ConnectionProxy connectionProxy;
    private LinkedList<Savepoint> savepoints;

    public SavePointHolder(ConnectionProxy connectionProxy) {
        this.connectionProxy = connectionProxy;
        this.savepoints = new LinkedList<>();
    }

    public void conversionSavePointHolder() throws SQLException {
        if (connectionProxy == null) {
            throw new SQLTransientConnectionException();
        }
        int savepointCounter = connectionProxy.getSavepointCounter();
        Savepoint savepoint = connectionProxy.setSavepoint(SAVEPOINT_NAME_PREFIX + savepointCounter);
        connectionProxy.setSavepointCounter(savepointCounter + 1);
        savepoints.addLast(savepoint);
    }

    public int releaseSavepoint() throws SQLException {
        Savepoint savepoint = savepoints.pollLast();
        if (savepoint != null) {
            connectionProxy.releaseSavepoint(savepoint);
            String savepointName = savepoint.getSavepointName();
            return Integer.parseInt(savepointName.substring(SAVEPOINT_NAME_PREFIX.length()));
        }
        return -1;
    }

    public int rollbackSavePoint() throws SQLException {
        Savepoint savepoint = savepoints.pollLast();
        if (savepoint != null) {
            connectionProxy.rollback(savepoint);
            String savepointName = savepoint.getSavepointName();
            return Integer.parseInt(savepointName.substring(SAVEPOINT_NAME_PREFIX.length()));
        }
        return -1;
    }

    public ConnectionProxy getConnectionProxy() {
        return this.connectionProxy;
    }

    public List<Savepoint> getSavePoints() {
        return this.savepoints;
    }
}
