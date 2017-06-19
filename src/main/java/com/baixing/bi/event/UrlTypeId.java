package com.baixing.bi.event;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjl on 2017/5/25.
 */
public class UrlTypeId {

private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UrlTypeId.class);

    protected static final String INVALID_URL_TYPE = "99";
    protected static final String DEFAULT_URL_TYPE = "9";

    protected static final String URL_TYPE_CONF_FILE_ENCODING = "utf-8";
    protected static final int URL_TYPE_CONF_FILE_READ_BUFFER = 8192;
    protected static final String URL_TYPE_CONF_FILE_COMMENT_LINE_LEADING_TAG = "#\t";
    protected static final String URL_TYPE_CONF_FILE_TYPE_LINE_LEANDING_TAG = "\t";
    protected static final String URL_TYPE_CONF_FILE_PART_REGEX_DELIMITER = "\t";

    protected static final List<String> l_oUrlTypes = new ArrayList<String>();
    protected static final Map<String, Pattern> m_oUrlPatterns = new TreeMap<String, Pattern>();
    protected static final List<Map<String, Pattern>> l_oUrlTypePatterns = new ArrayList<Map<String, Pattern>>();

    public UrlTypeId() {
    }

    public void loadConfigFile(String file_path) throws IOException{

        BufferedReader oUrlTypeConfFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path),
                URL_TYPE_CONF_FILE_ENCODING),
                URL_TYPE_CONF_FILE_READ_BUFFER);
        String sCurLine = null;
        String sCurUrlType = DEFAULT_URL_TYPE;
        boolean bIsFirstLine = true;
        do {
            sCurLine = oUrlTypeConfFileReader.readLine();
            if (bIsFirstLine) {
                bIsFirstLine = false;
                continue;
            }
            if (sCurLine == null) {
                break;
            }
            if (sCurLine.trim().length() == 0 || sCurLine.startsWith(URL_TYPE_CONF_FILE_COMMENT_LINE_LEADING_TAG)) {
                continue;
            }
            if (sCurLine.startsWith(URL_TYPE_CONF_FILE_TYPE_LINE_LEANDING_TAG)) {
                sCurUrlType = sCurLine.trim();
                l_oUrlTypes.add(sCurUrlType);
                m_oUrlPatterns.clear();
                continue;
            }
            String asCols[] = sCurLine.split(URL_TYPE_CONF_FILE_PART_REGEX_DELIMITER);
            if (asCols.length <= 1 || asCols[1].length() <= 0) {
                continue;
            }
            String sUrlPart = asCols[0].toUpperCase().trim();
            String sUrlPattern = asCols[1];
            sUrlPattern = sUrlPattern.replace("{BX_CITY_NAME_EN}", "(?<city>[^\\/]+)");
            sUrlPattern = sUrlPattern.replace("{BX_CATEGORY_NAME_EN}", "(?<cate>[^\\/]+)");
            sUrlPattern = sUrlPattern.replace("{BX_TOP_CATEGORY_NAME_EN}", "(?<topcate>[^\\/]+)");
            sUrlPattern = sUrlPattern.replace("{BX_ALL_CATEGORY_NAME_EN}", "(?<allcate>[^\\/]+)");
            Pattern pPattern = Pattern.compile(sUrlPattern.toLowerCase());
            Map<String, Pattern> oUrlPartPattern = new TreeMap<String, Pattern>();

            if (l_oUrlTypes.size() == l_oUrlTypePatterns.size()) {
                oUrlPartPattern = l_oUrlTypePatterns.get(l_oUrlTypePatterns.size() - 1);
                l_oUrlTypePatterns.remove(l_oUrlTypePatterns.size() - 1);
            }

            oUrlPartPattern.put(sUrlPart, pPattern);
            l_oUrlTypePatterns.add(oUrlPartPattern);
        } while(true);
        System.out.println("load success");
        System.out.println(m_oUrlPatterns.toString());

        oUrlTypeConfFileReader.close();
    }

    public  String getType(String sInUrl) {

        Map <String, String> m_oUrlPart = new TreeMap<String, String>();
        m_oUrlPart.put("HOST", "");
        m_oUrlPart.put("PATH", "");
        m_oUrlPart.put("QUERY", "");
        m_oUrlPart.put("REF", "");
        m_oUrlPart.put("PROTOCOL", "");
        m_oUrlPart.put("FILE", "");
        m_oUrlPart.put("AUTHORITY", "");
        m_oUrlPart.put("USERINFO", "");

        if (sInUrl.startsWith("http") && sInUrl.length() >= 8) {
            try {
                URL url = new URL(sInUrl);
                m_oUrlPart.put("HOST", url.getHost() == null ? "" : url.getHost());
                m_oUrlPart.put("PATH", url.getPath() == null ? "" : url.getPath());
                m_oUrlPart.put("QUERY", url.getQuery() == null ? "" : url.getQuery());
                m_oUrlPart.put("REF", url.getRef() == null ? "" : url.getRef());
                m_oUrlPart.put("PROTOCOL", url.getProtocol() == null ? "" : url.getProtocol());
                m_oUrlPart.put("FILE", url.getFile() == null ? "" : url.getFile());
                m_oUrlPart.put("AUTHORITY", url.getAuthority() == null ? "" : url.getAuthority());
                m_oUrlPart.put("USERINFO", url.getUserInfo() == null ? "" : url.getUserInfo());
            } catch (Exception oExp) {
                LOG.error("can not parse the url when get type " + sInUrl + " " + oExp);
            }
        }

        String sResult = "";
        boolean bIsCurUrlType = true;

        for (int i = 0; i < l_oUrlTypes.size(); i++) {
            String sCurUrlType = l_oUrlTypes.get(i);
            Map<String, Pattern> mUrlPattern = l_oUrlTypePatterns.get(i);
            bIsCurUrlType = true;

            for (Map.Entry<String, Pattern> entry : mUrlPattern.entrySet()) {
                String sCurUrlPartKey = entry.getKey();
                Pattern pCurPattern = entry.getValue();
                String sCurUrlPart = m_oUrlPart.get(sCurUrlPartKey);
                Matcher matcher = null;

                if (sCurUrlPart != null) {
                    matcher = pCurPattern.matcher(sCurUrlPart);
                }

                if (matcher == null || (!matcher.find())) {
                    bIsCurUrlType = false;
                    break;
                }
                Set<String> s_oMatcherGroups = getNamedGroupCandidates(pCurPattern);
            }

            if (bIsCurUrlType) {
                sResult = sCurUrlType;
                break;
            }
        }

        if (!(bIsCurUrlType)) {
            sResult = INVALID_URL_TYPE;
        }

//        System.out.println("++++ url is " + sInUrl + " , type id is "  + sResult);
        return sResult;

    }

    private static Set<String> getNamedGroupCandidates(Pattern regex) {
        Set<String> namedGroups = new HashSet<String>();

        Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex.toString());

        while (m.find()) {
            namedGroups.add(m.group(1));
        }

        return namedGroups;
    }


}

