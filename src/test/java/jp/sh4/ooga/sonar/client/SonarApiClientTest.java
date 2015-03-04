package jp.sh4.ooga.sonar.client;

import static org.junit.Assert.*;

import jp.sh4.ooga.sonar.client.response.IssuesSearchDto;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


/**
 * SonarApiClientのテストクラス
 * @version 1.0
 * @author ogawa
 */
public class SonarApiClientTest {

    SonarApiClient sonarClient = null;

    @Test
    public void コード違反の検索を行う() throws IOException {

        List<IssuesSearchDto> issueSearch = SonarApiClient.requestSearchIssues();

        assertNotNull(issueSearch);

        for(IssuesSearchDto issues : issueSearch){
            StringBuilder builder = new StringBuilder(512);
            List<IssuesSearchDto.Issue> issueList = issues.getIssues();
            for (IssuesSearchDto.Issue issue : issueList){
                String comp = issue.getComponent();
                builder.append(issue.getRule()).append(",").append(issue.getProject()).append(",")
                        .append(issue.getComponent()).append(",").append(issue.getLine());
                System.out.println(builder.toString());
                builder.delete(0, builder.length());
            }
        }



    }

}
