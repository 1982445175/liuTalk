package com.baidu.liuTalk.AlterService;

import com.baidu.liuTalk.AlterDomin.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class SendServiceImpl implements SendService{

    @Value("${header.toid}")
    int toid;

    @Value("${body.type}")
    String type;

    @Value("${webhook.token}")
    String token;

    @Override
    public void Send(List<Alert> alertList) {
        Alert alert = alertList.get(0);
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

    public String sendHttpPost(String JSONBody) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://apiin.im.baidu.com/api/msg/groupmsgsend?access_token="+token);
        httpPost.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            httpPost.setEntity(new StringEntity(JSONBody));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }
}
