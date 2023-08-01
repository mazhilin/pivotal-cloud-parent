package com.pivotal.cloud.datasource.boot.utils;

import com.pivotal.cloud.datasource.boot.transaction.ConnectionFactory;
import com.pivotal.cloud.datasource.boot.transaction.TransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @className: com.pivotal.cloud.datasource.boot.utils.TransactionToolsUtil
 * @title: 封装SamrtCloud项目-TransactionToolsUtil类
 * @description: <p>
 *         SamrtCloud项目-TransactionToolsUtil
 *         </p>
 * @content: TransactionToolsUtil
 * @author: Powered by marklin
 * @datetime: 2023-06-06 00:41
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 SamrtCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class TransactionToolsUtil {
    /**
     * 手动开启事务
     */
    public static String startTransaction() {
        String xid = TransactionContext.getXID();
        if (!StringUtils.isEmpty(xid)) {
            log.debug("dynamic-datasource exist local tx [{}]", xid);
        } else {
            xid = UUID.randomUUID().toString();
            TransactionContext.bind(xid);
            log.debug("dynamic-datasource start local tx [{}]", xid);
        }
        return xid;
    }

    /**
     * 手动提交事务
     */
    public static void commit(String xid) throws Exception {
        boolean hasSavepoint = ConnectionFactory.hasSavepoint(xid);
        try {
            ConnectionFactory.notify(xid, true);
        } finally {
            if (!hasSavepoint) {
                log.debug("dynamic-datasource commit local tx [{}]", TransactionContext.getXID());
                TransactionContext.remove();
            }
        }
    }

    /**
     * 手动回滚事务
     */
    public static void rollback(String xid) throws Exception {
        boolean hasSavepoint = ConnectionFactory.hasSavepoint(xid);
        try {
            ConnectionFactory.notify(xid, false);
        } finally {
            if (!hasSavepoint) {
                log.debug("dynamic-datasource rollback local tx [{}]", TransactionContext.getXID());
                TransactionContext.remove();
            }
        }
    }
}
