package com.baidu.liuTalk.AlterController;



import com.baidu.liuTalk.AlterDomin.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/json"})

public class SendController {
    @Value("${header.toid}")
    int toid;
    @Value("${body.type}")
    String type;
    @RequestMapping(value = "/send")
    @ResponseBody
    public void Send(@RequestBody Alerts json) throws Exception {
        System.out.println(json);
        List<Alert> alerts = json.getAlerts();
        Alert alert = alerts.get(0);
        Annotations annotations = alert.getAnnotations();
        Labels labels = alert.getLabels();
        Body body = new Body();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("##### ");
        stringBuffer.append(labels.getAlertname());
        stringBuffer.append("\\n");
        stringBuffer.append("description:");
        stringBuffer.append("Cluster name : "+labels.getJob()+"; "+annotations.getDescription());
        stringBuffer.append("\\n");
        stringBuffer.append("summary:");
        stringBuffer.append("Cluster name : "+labels.getJob()+"; "+annotations.getSummary());
        body.setType(type);
        body.setContent(stringBuffer.toString());
        Header header = new Header();
        header.setToid(toid);
        Hi hi =new Hi();
        hi.setBody(body);
        hi.setHeader(header);
        System.out.println(hi);
        sendHttpPost(hi.toString());

    }
    public static String sendHttpPost(String JSONBody) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://apiin.im.baidu.com/api/msg/groupmsgsend?access_token=d8ed33d206ef819761327d3c30df4b1a1");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(JSONBody));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        response.close();
        httpClient.close();
        return responseContent;
    }
}
