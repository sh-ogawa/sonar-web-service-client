package jp.sh4.ooga.sonar.client;

import static org.junit.Assert.*;

import jp.sh4.ooga.sonar.client.response.IssuesSearchDto;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * SonarApiClientのテストクラス
 * @version 1.0
 * @author ogawa
 */
public class SonarApiClientTest {

    @Before
    public void setting() throws IOException {
        //出力先のディレクトリ、ファイルを準備する
        Path dir = Paths.get("src/test/resources/out");
        if(!Files.exists(dir)){
            Files.createDirectories(dir);
        }
        Path outPath = Paths.get("src/test/resources/out/issue.csv");
        Files.deleteIfExists(outPath);
        Files.createFile(outPath);
    }

    @Test
    public void コード違反の検索を行う() throws IOException {

        List<IssuesSearchDto> issueSearch = SonarApiClient.requestSearchIssues();

        assertNotNull(issueSearch);
        assertNotEquals(issueSearch.size(), 0);

        Path outPath = Paths.get("src/test/resources/out/issue.csv");
        try(
                OutputStream out = Files.newOutputStream(outPath)
        ){
            for(IssuesSearchDto issues : issueSearch){
                StringBuilder builder = new StringBuilder(512);
                List<IssuesSearchDto.Issue> issueList = issues.getIssues();
                for (IssuesSearchDto.Issue issue : issueList){
                    String comp = issue.getComponent();
                    builder.append(issue.getRule()).append(",").append(issue.getProject()).append(",")
                            .append(issue.getComponent()).append(",").append(issue.getLine()).append("\n");
                    out.write(builder.toString().getBytes());
                    builder.delete(0, builder.length());
                }
            }
        }





    }

}
