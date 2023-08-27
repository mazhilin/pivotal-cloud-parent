package com.pivotal.cloud.boot;

import org.springframework.boot.CommandLineRunner;

/**
 * @className: com.pivotal.cloud.boot.CommandRunner
 * @projectName: 封装PivotalCloud项目-CommandRunner类
 * @module: PivotalCloud项目-CommandRunner类，主要位于CommandRunner模块的业务场景
 * @content: CommandRunner类，主要用于完成CommandRunner的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-10 02:46
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface CommandRunner extends CommandLineRunner {

    void run() throws Exception;
}
