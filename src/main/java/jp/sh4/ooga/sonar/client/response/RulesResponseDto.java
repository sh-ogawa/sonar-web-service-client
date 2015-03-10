package jp.sh4.ooga.sonar.client.response;

import net.arnx.jsonic.JSONHint;

import java.util.List;

/**
 * /api/rules APIのレスポンスDTO
 */
public class RulesResponseDto {

    @JSONHint(name = "title")
    String title = null;
    @JSONHint(name = "key")
    String key = null;
    @JSONHint(name = "plugin")
    String plugin = null;
    @JSONHint(name = "config_key")
    String config_key = null;
    @JSONHint(name = "description")
    String description = null;
    @JSONHint(name = "priority")
    String priority = null;
    @JSONHint(name = "params", type=RuleParameter.class)
    List<RuleParameter> params = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getConfig_key() {
        return config_key;
    }

    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<RuleParameter> getParams() {
        return params;
    }

    public void setParams(List<RuleParameter> params) {
        this.params = params;
    }

    public class RuleParameter{
        @JSONHint(name = "null")
        String name = null;
        @JSONHint(name = "description")
        String description = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }




}
