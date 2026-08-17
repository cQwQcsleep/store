package com.reandroid.dex.model;

import com.reandroid.dex.sections.Marker;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexFileInfo implements JSONConvert<JSONObject> {
    public static final String FILE_NAME = ObjectsUtil.of("dex-file.json");
    public static final String NAME_layouts = ObjectsUtil.of("layouts");
    public static final String NAME_markers = ObjectsUtil.of("markers");
    public static final String NAME_version = ObjectsUtil.of("version");
    private int version = 35;
    private final List<String> markerList = new ArrayCollection();
    private final List<DexFileInfo> layoutList = new ArrayCollection();

    public static DexFileInfo convert(JSONObject jSONObject) {
        DexFileInfo dexFileInfo = new DexFileInfo();
        dexFileInfo.fromJson(jSONObject);
        return dexFileInfo;
    }

    public static DexFileInfo fromDex(DexFile dexFile) {
        DexFileInfo dexFileInfo = new DexFileInfo();
        dexFileInfo.setVersion(dexFile.getVersion());
        if (dexFile.isMultiLayout()) {
            int size = dexFile.size();
            for (int i = 0; i < size; i++) {
                dexFileInfo.addLayout(fromDex(dexFile.getLayout(i)));
            }
        } else {
            Iterator<Marker> markers = dexFile.getMarkers();
            while (markers.hasNext()) {
                dexFileInfo.addMarker(markers.next());
            }
        }
        return dexFileInfo;
    }

    public static DexFileInfo readJson(File file) throws IOException {
        return convert(new JSONObject(file));
    }

    public void addLayout(DexFileInfo dexFileInfo) {
        if (dexFileInfo == null || dexFileInfo == this) {
            return;
        }
        this.layoutList.add(dexFileInfo);
    }

    public void addMarker(Marker marker) {
        if (marker != null) {
            addMarker(marker.toString());
        }
    }

    public void applyTo(DexFile dexFile) {
        List<DexFileInfo> layoutList = getLayoutList();
        if (layoutList.isEmpty()) {
            applyTo(dexFile.getOrCreateFirst());
            return;
        }
        int size = layoutList.size();
        for (int i = 0; i < size; i++) {
            layoutList.get(i).applyTo(dexFile.getOrCreateAt(i));
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        setVersion(jSONObject.optInt(NAME_version, getVersion()));
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(NAME_markers);
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                addMarker(jSONArrayOptJSONArray.optString(i));
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(NAME_layouts);
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                addLayout(convert(jSONArrayOptJSONArray2.getJSONObject(i2)));
            }
        }
    }

    public List<DexFileInfo> getLayoutList() {
        return this.layoutList;
    }

    public List<String> getMarkerList() {
        return this.markerList;
    }

    public Iterator<Marker> getMarkers() {
        return ComputeIterator.of(getMarkerList().iterator(), new Function() { // from class: fq3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Marker.parse((String) obj);
            }
        });
    }

    public int getVersion() {
        return this.version;
    }

    public void saveToDirectory(File file) throws IOException {
        toJson().write(new File(file, FILE_NAME));
    }

    public void setVersion(int i) {
        this.version = i;
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_version, getVersion());
        List<String> markerList = getMarkerList();
        if (!markerList.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.putAll((Collection<?>) markerList);
            jSONObject.put(NAME_markers, jSONArray);
        }
        List<DexFileInfo> layoutList = getLayoutList();
        if (!layoutList.isEmpty()) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<DexFileInfo> it = layoutList.iterator();
            while (it.hasNext()) {
                jSONArray2.put(it.next().toJson());
            }
            jSONObject.put(NAME_layouts, jSONArray2);
        }
        return jSONObject;
    }

    public String toString() {
        return toJson().toString(2);
    }

    public void addMarker(String str) {
        if (str != null) {
            this.markerList.add(str);
        }
    }

    public void applyTo(DexLayout dexLayout) {
        dexLayout.setVersion(getVersion());
        Iterator<Marker> markers = getMarkers();
        while (markers.hasNext()) {
            dexLayout.addMarker(markers.next());
        }
    }

    public static DexFileInfo fromDex(DexLayout dexLayout) {
        DexFileInfo dexFileInfo = new DexFileInfo();
        dexFileInfo.setVersion(dexLayout.getVersion());
        Iterator<Marker> markers = dexLayout.getMarkers();
        while (markers.hasNext()) {
            dexFileInfo.addMarker(markers.next());
        }
        return dexFileInfo;
    }
}
