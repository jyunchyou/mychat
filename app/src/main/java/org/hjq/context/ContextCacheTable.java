package org.hjq.context;


import android.content.Context;


import java.util.concurrent.ConcurrentHashMap;


public class ContextCacheTable {

    private static ConcurrentHashMap<String,Context> contextCacheTable = new ConcurrentHashMap(20);

    public static Context getContext(String activityName){

        return contextCacheTable.get(activityName);
    }
    public static void setContext(String activityName,Context context){

        contextCacheTable.put(activityName,context);
    }

}
