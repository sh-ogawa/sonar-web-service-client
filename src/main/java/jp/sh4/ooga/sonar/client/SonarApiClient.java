package jp.sh4.ooga.sonar.client;

import jp.sh4.ooga.sonar.client.response.IssuesSearchDto;
import jp.sh4.ooga.sonar.client.util.UniversalConfigFile;
import net.arnx.jsonic.JSON;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * SonarQubeのWEB-APIクライアント(参照のみ)
 * @version 1.0
 * @author ogawa
 */
public final class SonarApiClient {

    /**
     * 「/api/issuers/search」で違反一覧を取得する
     */
    public static List<IssuesSearchDto> requestSearchIssues() throws IOException {

        URL resource = SonarApiClient.class.getResource("/jp/sh4/ooga/sonar/client/sonar-web.properties");

        UniversalConfigFile conf = new UniversalConfigFile(resource.getPath());
        StringBuilder builder = new StringBuilder(1024);
        builder.append(conf.getString("url"));
        builder.append(conf.getString("sonar-api-uri"));
        builder.append(conf.getString("sonar-common-option"));

        String url = builder.toString();

        List<IssuesSearchDto> issueList = new LinkedList<IssuesSearchDto>();
        String[] params = conf.getKeys("sonar-option");
        if(params.length == 0){
            IssuesSearchDto issue = (IssuesSearchDto) requestSonarServer(url, IssuesSearchDto.class);
            issueList.add(issue);
        }else{
            for(String param : params){
                builder.delete(0, builder.length());
                builder.append(url).append(conf.getString(param));
                IssuesSearchDto issue = (IssuesSearchDto) requestSonarServer(builder.toString(), IssuesSearchDto.class);
                issueList.add(issue);
            }
        }

        return issueList;

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
