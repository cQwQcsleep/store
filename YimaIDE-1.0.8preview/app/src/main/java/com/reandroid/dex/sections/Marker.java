package com.reandroid.dex.sections;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.model.DexLayout;
import com.reandroid.dex.sections.Marker;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Marker {
    private static final String ANDROID_PLATFORM_BUILD = "platform";
    public static final String BACKEND = "backend";
    public static final String COMPILATION_MODE = "compilation-mode";
    public static final String D8_TEMPLATE = "~~D8{\"backend\":\"dex\",\"compilation-mode\":\"release\",\"has-checksums\":false,\"min-api\":24,\"version\":\"4.0.48\"}";
    public static final String DESUGARED_LIBRARY_IDENTIFIERS = "desugared-library-identifiers";
    public static final String HAS_CHECKSUMS = "has-checksums";
    public static final String MIN_API = "min-api";
    public static final String PG_MAP_ID = "pg-map-id";
    private static final char PREFIX_CHAR = '~';
    public static final String R8_MODE = "r8-mode";
    public static final String R8_TEMPLATE = "~~R8{\"backend\":\"dex\",\"compilation-mode\":\"release\",\"has-checksums\":false,\"r8-mode\":\"compatibility\",\"version\":\"3.2.74\"}";
    public static final String SHA1 = "sha-1";
    public static final String VERSION = "version";
    private JSONObject jsonObject;
    private StringId stringId;
    private Tool tool;
    private static final String PREFIX = "~~";
    private static final String D8_PREFIX = PREFIX + Tool.D8 + "{";
    private static final String R8_PREFIX = PREFIX + Tool.R8 + "{";
    private static final String L8_PREFIX = PREFIX + Tool.L8 + "{";

    public enum Backend {
        CF,
        DEX
    }

    public enum Tool {
        D8,
        GlobalSyntheticsGenerator,
        L8,
        R8,
        Relocator,
        TraceReferences;

        public static Tool[] valuesR8andD8() {
            return new Tool[]{D8, R8};
        }
    }

    public Marker(Tool tool) {
        this(tool, new JSONObject());
    }

    public static Marker createD8() {
        return parse(D8_TEMPLATE);
    }

    public static Marker createR8() {
        return parse(R8_TEMPLATE);
    }

    public static boolean hasMarkerPrefix(String str) {
        return str != null && str.length() >= 3 && str.charAt(0) == '~' && str.charAt(1) == '~';
    }

    private static Marker internalParse(Tool tool, String str) {
        try {
            return new Marker(tool, new JSONObject(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public static Marker parse(StringId stringId) {
        String string = stringId.getString();
        Marker markerInternalParse = null;
        if (hasMarkerPrefix(string)) {
            String str = D8_PREFIX;
            if (string.startsWith(str)) {
                markerInternalParse = internalParse(Tool.D8, string.substring(str.length() - 1));
            } else {
                String str2 = R8_PREFIX;
                if (string.startsWith(str2)) {
                    markerInternalParse = internalParse(Tool.R8, string.substring(str2.length() - 1));
                } else {
                    String str3 = L8_PREFIX;
                    if (string.startsWith(str3)) {
                        markerInternalParse = internalParse(Tool.L8, string.substring(str3.length() - 1));
                    }
                }
            }
            if (markerInternalParse != null) {
                markerInternalParse.setStringId(stringId);
            }
        }
        return markerInternalParse;
    }

    public String buildString() {
        this.jsonObject.sort(CompareUtil.getComparableComparator(), true);
        return PREFIX + this.tool + this.jsonObject;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Marker) {
            Marker marker = (Marker) obj;
            if (this.tool == marker.tool && this.jsonObject.equals(marker.jsonObject)) {
                return true;
            }
        }
        return false;
    }

    public String getBackend() {
        if (hasBackend()) {
            return this.jsonObject.getString(BACKEND);
        }
        return hasMinApi() ? StringsUtil.toLowercase(Backend.DEX.name()) : StringsUtil.toLowercase(Backend.CF.name());
    }

    public String getCompilationMode() {
        return this.jsonObject.optString(COMPILATION_MODE, null);
    }

    public String[] getDesugaredLibraryIdentifiers() {
        if (!this.jsonObject.has(DESUGARED_LIBRARY_IDENTIFIERS)) {
            return new String[0];
        }
        JSONArray jSONArray = this.jsonObject.getJSONArray(DESUGARED_LIBRARY_IDENTIFIERS);
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = jSONArray.getString(i);
        }
        return strArr;
    }

    public Boolean getHasChecksums() {
        if (this.jsonObject.has(HAS_CHECKSUMS)) {
            return Boolean.valueOf(this.jsonObject.getBoolean(HAS_CHECKSUMS));
        }
        return null;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public Long getMinApi() {
        return Long.valueOf(this.jsonObject.getLong(MIN_API));
    }

    public String getPgMapId() {
        return this.jsonObject.optString(PG_MAP_ID, null);
    }

    public String getR8Mode() {
        return this.jsonObject.getString(R8_MODE);
    }

    public String getSha1() {
        return this.jsonObject.optString(SHA1, null);
    }

    public StringId getStringId() {
        return this.stringId;
    }

    public Tool getTool() {
        return this.tool;
    }

    public String getVersion() {
        return this.jsonObject.optString("version", null);
    }

    public boolean hasBackend() {
        return this.jsonObject.has(BACKEND);
    }

    public boolean hasDesugaredLibraryIdentifiers() {
        return this.jsonObject.has(DESUGARED_LIBRARY_IDENTIFIERS);
    }

    public boolean hasMinApi() {
        return this.jsonObject.has(MIN_API);
    }

    public int hashCode() {
        return this.tool.hashCode() + (this.jsonObject.hashCode() * 3);
    }

    public Boolean isAndroidPlatformBuild() {
        if (this.jsonObject.has(ANDROID_PLATFORM_BUILD)) {
            return Boolean.valueOf(this.jsonObject.getBoolean(ANDROID_PLATFORM_BUILD));
        }
        return null;
    }

    public boolean isCfBackend() {
        return getBackend().equals(StringsUtil.toLowercase(Backend.CF.name()));
    }

    public boolean isD8() {
        return this.tool == Tool.D8;
    }

    public boolean isDesugared() {
        return hasMinApi();
    }

    public boolean isDexBackend() {
        return getBackend().equals(StringsUtil.toLowercase(Backend.DEX.name()));
    }

    public boolean isL8() {
        return this.tool == Tool.L8;
    }

    public boolean isR8() {
        return this.tool == Tool.R8;
    }

    public boolean isRelocator() {
        return this.tool == Tool.Relocator;
    }

    public void removeSelf() {
        StringId stringId = getStringId();
        if (stringId != null) {
            setStringId(null);
            stringId.removeSelf();
        }
    }

    public void save() {
        StringId stringId = getStringId();
        if (stringId != null) {
            stringId.setString(buildString());
        }
    }

    public Marker setAndroidPlatformBuild(Boolean bool) {
        this.jsonObject.put(ANDROID_PLATFORM_BUILD, bool);
        return this;
    }

    public Marker setBackend(Backend backend) {
        JSONObject jSONObject = this.jsonObject;
        if (backend == null) {
            jSONObject.remove(BACKEND);
            return this;
        }
        jSONObject.put(BACKEND, StringsUtil.toLowercase(backend.name()));
        return this;
    }

    public Marker setCompilationMode(String str) {
        this.jsonObject.put(COMPILATION_MODE, str);
        return this;
    }

    public Marker setDesugaredLibraryIdentifiers(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            this.jsonObject.remove(DESUGARED_LIBRARY_IDENTIFIERS);
            return this;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : strArr) {
            jSONArray.put(str);
        }
        this.jsonObject.put(DESUGARED_LIBRARY_IDENTIFIERS, jSONArray);
        return this;
    }

    public Marker setHasChecksums(Boolean bool) {
        this.jsonObject.put(HAS_CHECKSUMS, bool);
        return this;
    }

    public void setJsonObject(JSONObject jSONObject) {
        this.jsonObject = jSONObject;
    }

    public Marker setMinApi(Long l) {
        this.jsonObject.put(MIN_API, l);
        return this;
    }

    public Marker setPgMapId(String str) {
        this.jsonObject.put(PG_MAP_ID, str);
        return this;
    }

    public Marker setR8Mode(String str) {
        this.jsonObject.put(R8_MODE, str);
        return this;
    }

    public Marker setSha1(String str) {
        this.jsonObject.put(SHA1, str);
        return this;
    }

    public void setStringId(StringId stringId) {
        this.stringId = stringId;
        if (stringId != null) {
            stringId.addUsageType(UsageMarker.USAGE_MARKER);
        }
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Marker setVersion(String str) {
        this.jsonObject.put("version", str);
        return this;
    }

    public String toString() {
        return PREFIX + this.tool + this.jsonObject;
    }

    public Marker(Tool tool, JSONObject jSONObject) {
        this.tool = tool;
        this.jsonObject = jSONObject;
    }

    public static Iterator<Marker> parse(Section<StringId> section) {
        if (section == null) {
            return EmptyIterator.of();
        }
        return parse((Iterator<StringId>) section.iterator());
    }

    public static Iterator<Marker> parse(Iterator<StringId> it) {
        return ComputeIterator.of(it, new Function() { // from class: gv9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Marker.parse((StringId) obj);
            }
        });
    }

    public static Iterator<Marker> parse(DexLayout dexLayout) {
        return parse((Section<StringId>) dexLayout.getSection(SectionType.STRING_ID));
    }

    public static Marker parse(String str) {
        if (!hasMarkerPrefix(str)) {
            return null;
        }
        String str2 = D8_PREFIX;
        if (str.startsWith(str2)) {
            return internalParse(Tool.D8, str.substring(str2.length() - 1));
        }
        String str3 = R8_PREFIX;
        if (str.startsWith(str3)) {
            return internalParse(Tool.R8, str.substring(str3.length() - 1));
        }
        String str4 = L8_PREFIX;
        if (str.startsWith(str4)) {
            return internalParse(Tool.L8, str.substring(str4.length() - 1));
        }
        return null;
    }
}
