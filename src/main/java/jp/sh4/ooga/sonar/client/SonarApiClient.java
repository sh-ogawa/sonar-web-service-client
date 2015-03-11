package jp.sh4.ooga.sonar.client;

import jp.sh4.ooga.sonar.client.response.IssuesSearchDto;
import jp.sh4.ooga.sonar.client.response.RulesResponseDto;
import jp.sh4.ooga.sonar.client.util.UniversalConfigFile;
import net.arnx.jsonic.JSON;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
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

        URL resource = SonarApiClient.class.getResource("/jp/sh4/ooga/sonar/client/sonar-web-search-issues.properties");

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

            int totalPage = issue.getPaging().getPages();
            for(int page = 2; page <= totalPage; page++){
                issue = (IssuesSearchDto) requestSonarServer((url + "&pageIndex=" + page), IssuesSearchDto.class);
                issueList.add(issue);
            }
        }else{
            for(String param : params){
                IssuesSearchDto issue = (IssuesSearchDto) requestSonarServer(url + conf.getString(param), IssuesSearchDto.class);
                issueList.add(issue);
            }
        }

        return issueList;

    }

    /**
     * 「/api/rules」でルール一覧を取得する
     */
    public static List<RulesResponseDto> requestRules() throws IOException {

        URL resource = SonarApiClient.class.getResource("/jp/sh4/ooga/sonar/client/sonar-web-rules.properties");
        UniversalConfigFile conf = new UniversalConfigFile(resource.getPath());
        StringBuilder builder = new StringBuilder(1024);
        builder.append(conf.getString("url"));
        builder.append(conf.getString("sonar-api-uri"));
        builder.append(conf.getString("sonar-common-option"));

        String url = builder.toString();

        List<RulesResponseDto> rules = new LinkedList<>();
        String[] params = conf.getKeys("sonar-option");

        if(params.length == 0){
            rules.addAll(Arrays.asList((RulesResponseDto[]) requestSonarServer(url, RulesResponseDto[].class)));
        } else{
            for(String param : params){
                rules.addAll(Arrays.asList((RulesResponseDto[]) requestSonarServer(url + conf.getString(param), RulesResponseDto[].class)));
            }
        }

        return rules;

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
