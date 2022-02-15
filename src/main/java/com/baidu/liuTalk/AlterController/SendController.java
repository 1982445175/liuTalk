package com.baidu.liuTalk.AlterController;



import com.baidu.liuTalk.AlterDomin.*;
import com.baidu.liuTalk.AlterService.SendService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/json"})

public class SendController {

    private SendService sendService;

    @RequestMapping(value = "/send")
    @ResponseBody
    public void Send(@RequestBody Alerts json) throws Exception {
        System.out.println(json);
        List<Alert> alerts = json.getAlerts();
        sendService.Send(alerts);
    }

}
