package com.baidu.liuTalk.AlterService;

import com.baidu.liuTalk.AlterDomin.Alert;

import java.util.List;

public interface SendService {
    void Send(List<Alert> alertList);
}
