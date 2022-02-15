package com.baidu.liuTalk.AlterDomin;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Alerts {

    private String version;
    private String groupKey;
    private String status;
    private String receiver;
    private Map<String, String> groupLabels;
    private Map<String, String> commonLabels;
    private String externalURL;
    private List<Alert> alerts;
}
