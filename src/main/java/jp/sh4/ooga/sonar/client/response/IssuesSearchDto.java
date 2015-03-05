package jp.sh4.ooga.sonar.client.response;

import net.arnx.jsonic.JSONHint;

import java.util.List;

/**
 * /api/issues/search APIのレスポンスDTO
 */
public class IssuesSearchDto {

    @JSONHint(name = "securityExclusions")
    private String securityExclusions = null;

    @JSONHint(name = "maxResultsReached")
    private String maxResultsReached = null;

    @JSONHint(name = "paging")
    private Paging paging = null;

    @JSONHint(name = "issues", type = Issue.class)
    private List<Issue> issues = null;

    @JSONHint(name = "components", type = Component.class)
    private List<Component> components = null;

    @JSONHint(name = "projects", type = Project.class)
    private List<Project> projects = null;

    @JSONHint(name = "rules", type = Rule.class)
    private List<Rule> rules = null;

    public String getSecurityExclusions() {
        return securityExclusions;
    }

    public void setSecurityExclusions(String securityExclusions) {
        this.securityExclusions = securityExclusions;
    }

    public String getMaxResultsReached() {
        return maxResultsReached;
    }

    public void setMaxResultsReached(String maxResultsReached) {
        this.maxResultsReached = maxResultsReached;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public class Paging{

        boolean hasNext = false;

        @JSONHint(name = "pageIndex")
        int pageIndex = 0;
        @JSONHint(name = "pageSize")
        int pageSize = 0;
        @JSONHint(name = "total")
        int total = 0;
        @JSONHint(name = "pages")
        int pages = 0;

        public boolean hasNext(){
            return pageIndex < pages;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }

    public class Issue{

        @JSONHint(name = "key")
        String key = null;

        @JSONHint(name = "component")
        String component = null;

        @JSONHint(name = "project")
        String project = null;

        @JSONHint(name = "rule")
        String rule = null;

        @JSONHint(name = "status")
        String status = null;

        @JSONHint(name = "resolution")
        String resolution = null;

        @JSONHint(name = "severity")
        String severity = null;

        @JSONHint(name = "message")
        String message = null;

        @JSONHint(name = "line")
        String line = null;

        @JSONHint(name = "author")
        String author = null;

        @JSONHint(name = "creationDate")
        String creationDate = null;

        @JSONHint(name = "updateDate")
        String updateDate = null;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getRule() { return rule; }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCreationDate() { return creationDate; }

        public void setCreationDate(String creationDate) { this.creationDate = creationDate; }

    }

    public class Component{

        @JSONHint(name = "key")
        String key = null;

        @JSONHint(name = "qualifier")
        String qualifier = null;

        @JSONHint(name = "name")
        String name = null;

        @JSONHint(name = "longName")
        String longName = null;

        public String getKey() { return key; }

        public void setKey(String key) { this.key = key; }

        public String getQualifier() { return qualifier; }

        public void setQualifier(String qualifier) { this.qualifier = qualifier; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public String getLongName() { return longName; }

        public void setLongName(String longName) { this.longName = longName; }

    }

    public class Project{
        @JSONHint(name = "key")
        String key = null;

        @JSONHint(name = "qualifier")
        String qualifier = null;

        @JSONHint(name = "name")
        String name = null;

        @JSONHint(name = "longName")
        String longName = null;

        public String getKey() { return key; }

        public void setKey(String key) { this.key = key; }

        public String getQualifier() { return qualifier; }

        public void setQualifier(String qualifier) { this.qualifier = qualifier; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public String getLongName() { return longName; }

        public void setLongName(String longName) { this.longName = longName; }
    }

    public class Rule{

        @JSONHint(name = "key")
        String key = null;

        @JSONHint(name = "name")
        String name = null;

        @JSONHint(name = "desc")
        String desc = null;

        @JSONHint(name = "status")
        String status = null;

        public String getKey() { return key; }

        public void setKey(String key) { this.key = key; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
