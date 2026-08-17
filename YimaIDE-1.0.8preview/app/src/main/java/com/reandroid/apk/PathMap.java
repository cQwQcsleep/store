package com.reandroid.apk;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.PathTree;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class PathMap implements JSONConvert<JSONArray> {
    public static final String JSON_FILE = "path-map.json";
    public static final String NAME_alias = "alias";
    public static final String NAME_name = "name";
    private final Object mLock = new Object();
    private final Map<String, String> mNameAliasMap = new HashMap();
    private final Map<String, String> mAliasNameMap = new HashMap();

    public void add(String str, String str2) {
        if (str == null || str2 == null || str.equals(str2)) {
            return;
        }
        synchronized (this.mLock) {
            this.mNameAliasMap.remove(str);
            this.mNameAliasMap.put(str, str2);
            this.mAliasNameMap.remove(str2);
            this.mAliasNameMap.put(str2, str);
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            this.mNameAliasMap.clear();
            this.mAliasNameMap.clear();
        }
    }

    public void fromJson(JSONArray jSONArray) {
        clear();
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            add(jSONArray.optJSONObject(i));
        }
    }

    public String getAlias(String str) {
        String str2;
        synchronized (this.mLock) {
            str2 = this.mNameAliasMap.get(str);
        }
        return str2;
    }

    public String getName(String str) {
        String str2;
        synchronized (this.mLock) {
            str2 = this.mAliasNameMap.get(str);
        }
        return str2;
    }

    public String restore(InputSource inputSource) {
        if (inputSource == null) {
            return null;
        }
        String name = getName(inputSource.getName());
        if (name == null) {
            name = getName(inputSource.getAlias());
        }
        if (name == null || name.equals(inputSource.getAlias())) {
            return null;
        }
        inputSource.setAlias(name);
        return name;
    }

    public void restoreResFile(Collection<ResFile> collection) {
        if (collection == null) {
            return;
        }
        Iterator<ResFile> it = collection.iterator();
        while (it.hasNext()) {
            restoreResFile(it.next());
        }
    }

    public int size() {
        int size;
        synchronized (this.mLock) {
            size = this.mNameAliasMap.size();
        }
        return size;
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m6458toJson() {
        JSONArray jSONArray = new JSONArray();
        Map<String, String> map = this.mNameAliasMap;
        Iterator<String> itSortPaths = PathTree.sortPaths(map.keySet().iterator());
        while (itSortPaths.hasNext()) {
            String next = itSortPaths.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", next);
            jSONObject.put(NAME_alias, map.get(next));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public String toString() {
        return "PathMap size=" + size();
    }

    public void restoreResFile(ResFile resFile) {
        String strRestore = restore(resFile.getInputSource());
        if (strRestore == null) {
            return;
        }
        resFile.setFilePath(strRestore);
    }

    private void restore(InputSource[] inputSourceArr) {
        for (InputSource inputSource : inputSourceArr) {
            restore(inputSource);
        }
    }

    public void add(InputSource[] inputSourceArr) {
        if (inputSourceArr == null) {
            return;
        }
        for (InputSource inputSource : inputSourceArr) {
            add(inputSource);
        }
    }

    public void restore(ApkModule apkModule) {
        if (apkModule.getLoadedTableBlock() != null) {
            restoreResFile(apkModule.listResFiles());
        }
        restore(apkModule.getInputSources());
    }

    public void add(InputSource inputSource) {
        if (inputSource == null) {
            return;
        }
        add(inputSource.getName(), inputSource.getAlias());
    }

    public void add(ZipEntryMap zipEntryMap) {
        if (zipEntryMap == null) {
            return;
        }
        add(zipEntryMap.toArray());
    }

    private void add(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        add(jSONObject.optString("name"), jSONObject.optString(NAME_alias));
    }
}
