package cn.productai.api.examples;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.ClassifyType;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.classify.ClassifyByImageFileRequest;
import cn.productai.api.pai.entity.classify.ClassifyResponse;
import cn.productai.api.pai.response.ClassifyResult;

import java.io.File;

/**
 * Created by Zhong Wang on 2017/7/5.
 * 场景分析与标注
 * https://api-doc.productai.cn/doc/pai.html#场景分析与标注
 */
public class ClassifyByFileExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("==>  Demo - 场景分析与标注  <==");
        System.out.println("See https://api-doc.productai.cn/doc/pai.html#场景分析与标注 for details.\r\n");

        ClassifyByImageFileRequest request = new ClassifyByImageFileRequest(ClassifyType.Gneral);
        request.setImageFile(new File(this.getClass().getResource("/").getPath() + "images/f10.jpg"));
        request.setLanguage(LanguageType.Chinese);

        try {
            ClassifyResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            for (ClassifyResult r : response.getResults()) {
                // access the response directly
                System.out.println(String.format("%s - %s", r.getCategory(), r.getScore()));
            }

            System.out.println("==============================Result==============================");
        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                    e.getErrorCode(),
                    e.getErrorMessage()));
            e.printStackTrace();

        } catch (ClientException e) {
            System.out.println(String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                    e.getErrorCode(),
                    e.getErrorMessage(),
                    e.getRequestId()));
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getTypeName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}