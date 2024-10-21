package com.pilog.plontology.service.impl;

import java.util.HashMap;

/**
 *
 * @author Andre Scheepers
 */
public class SessionMapBuilder
{

    private HashMap<String, Object> sessionHashMap = new HashMap<String, Object>();
    private String ssUsername;
    private String ssJNDI;

    public SessionMapBuilder(String ssUsername, String ssJNDI)
    {
        this.ssUsername = ssUsername;
        this.ssJNDI = ssJNDI;
        setSessionHashMap();
    }

    private void setSessionHashMap()
    {
        sessionHashMap.put("ssJNDI", ssJNDI);
        sessionHashMap.put("ssUsername", ssUsername);
    }

    public HashMap<String, Object> getSessionHashMap()
    {
        return sessionHashMap;
    }

    public void addKV(String key, Object value)
    {
        sessionHashMap.put(key, value);
    }
}

