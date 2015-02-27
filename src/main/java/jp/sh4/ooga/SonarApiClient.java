package jp.sh4.ooga;

import jp.sh4.ooga.api.response.IssuesSearchDto;
import net.arnx.jsonic.JSON;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * SonarQubeのWEB-APIクライアント(参照のみ)
 * @version 1.0
 * @author ogawa
 */
public final class SonarApiClient {

    /**
     * 「/api/issuers/search」で違反一覧を取得する
     */
    public static IssuesSearchDto requestSearchIssue(){

        StringBuilder builder = new StringBuilder(1024);
        builder.append("http://localhost:18080/sonar/api/issues/search?format=json");
        builder.append("&rules=checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.MagicNumberCheck");
        builder.append("&pageSize=-1");

        return (IssuesSearchDto) requestSonarServer(builder.toString(), IssuesSearchDto.class);

    }

    private static Object requestSonarServer(String req, Class<?> cl){

        Object obj = null;

        HttpURLConnection http = null;

        try{
            URL url = new URL(req);

            http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(0);
            http.setReadTimeout(0);

            http.connect();
            int code = http.getResponseCode();
            if(code == 200){
                try(InputStreamReader reader = new InputStreamReader(http.getInputStream(), "utf-8")){
                    obj = JSON.decode(reader, cl);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        if(http != null){
            http.disconnect();
        }

        return obj;

    }


}
