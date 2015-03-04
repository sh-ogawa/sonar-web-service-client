package jp.sh4.ooga.sonar.client.util;

import java.io.*;
import java.util.*;

/**
 * 国際化対応したコンフィグファイルクラス
 */
public class UniversalConfigFile {

    private ResourceBundle resource = null;

    /**
     * プロパティファイルを生成・ロードする。
     * @param path プロパティファイルパス
     */
    public UniversalConfigFile(final String path) throws IOException {
        this(path, "utf-8");
    }

    /**
     * プロパティファイルを文字コード指定のして生成・ロードする。
     * @param path プロパティファイルパス
     * @param charset 文字コード
     */
    public UniversalConfigFile(final String path, final String charset) throws IOException {
        //ファイルを取得
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException(path);
        }
        try(//プロパティファイル読み込みんでこのインスタンス変数に貼り付ける
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), charset)){
            resource = new PropertyResourceBundle(reader);
        }

    }

    /**
     * キーについて値を読み込みStringで返却する
     * @param key プロパティキー
     * @return String
     */
    public String getString(final String key){
        return resource.getString(key);
    }

    /**
     * キーについて値を読み込みshortで返却する
     * @param key プロパティキー
     * @return short
     */
    public short getShort(final String key){
        return Short.parseShort(resource.getString(key));
    }

    /**
     * キーについて値を読み込みintで返却する
     * @param key プロパティキー
     * @return int
     */
    public int getInt(final String key){
        return Integer.parseInt(resource.getString(key));
    }

    /**
     * キーについて値を読み込みlongで返却する
     * @param key プロパティキー
     * @return long
     */
    public long getLong(final String key){
        return Long.parseLong(resource.getString(key));
    }

    /**
     * キーについて値を読み込みfloatで返却する
     * @param key プロパティキー
     * @return float
     */
    public float getFloat(final String key){
        return Float.parseFloat(resource.getString(key));
    }

    /**
     * キーについて値を読み込みdoubleで返却する
     * @param key プロパティキー
     * @return double
     */
    public double getDouble(final String key){
        return Double.parseDouble(resource.getString(key));
    }

    /**
     * Resourceプロパティ内のすべてのキーを取得する
     * @return Resourceプロパティ内のすべてのキー
     */
    public Enumeration<String> getKeys(){
        return resource.getKeys();
    }

    /**
     * keyNameで開始するキーをすべて取得する
     * @param keyName start with key name
     * @return key array
     */
    public String[] getKeys(String keyName){
        List<String> list = new ArrayList<String>();
        Enumeration<String> allKeys = resource.getKeys();
        while(allKeys.hasMoreElements()){
            String key = allKeys.nextElement();
            if(key.startsWith(keyName)){
                list.add(key);
            }
        }

        return list.toArray(new String[list.size()]);

    }

}
