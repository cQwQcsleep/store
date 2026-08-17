package com.reandroid.dex.dexopt;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.common.BytesOutputStream;
import com.reandroid.dex.dexopt.ProfileFile;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.io.FileUtil;
import defpackage.aca;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ProfileFile extends FixedBlockContainer implements LinkableProfileItem, JSONConvert<JSONObject> {
    public static final String DECODE_DIR_NAME = ObjectsUtil.of("dexopt");
    public static final String NAME_PROF = ObjectsUtil.of("baseline.prof");
    public static final String NAME_PROFM = ObjectsUtil.of("baseline.profm");
    public static final String JSON_NAME_PROF = ObjectsUtil.of("baseline.prof.json");
    public static final String JSON_NAME_PROFM = ObjectsUtil.of("baseline.profm.json");
    public static final String PATH_PROF = ObjectsUtil.of("assets/dexopt/baseline.prof");
    public static final String PATH_PROFM = ObjectsUtil.of("assets/dexopt/baseline.profm");

    public ProfileFile(int i) {
        super(i);
    }

    public static /* synthetic */ boolean b(ZipEntryMap zipEntryMap, String str) {
        return !zipEntryMap.contains(str);
    }

    public static /* synthetic */ boolean k(String str) {
        return DexFile.getDexFileNumber(str) >= 0;
    }

    public abstract ProfileBody body();

    public void ensureDex(DexFile dexFile) {
        String simpleName = dexFile.getSimpleName();
        if (StringsUtil.isEmpty(simpleName)) {
            aca.a("Unnamed dex: ", dexFile);
        } else {
            if (get(simpleName) != null) {
                return;
            }
            getOrCreate(simpleName).update(dexFile);
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        magic().set(jSONObject.getInt("magic"));
        version().name(jSONObject.getString("version"));
        body().fromJson(jSONObject.getJSONArray("body"));
    }

    public ProfileData get(String str) {
        return body().get(str);
    }

    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream();
        try {
            writeBytes(bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public ProfileData getOrCreate(String str) {
        ProfileData profileData = get(str);
        if (profileData != null) {
            return profileData;
        }
        ProfileData profileDataCreateNew = body().createNew();
        profileDataCreateNew.setName(str);
        return profileDataCreateNew;
    }

    public Iterator<? extends ProfileData> iterator() {
        return body().iterator();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        body().link(dexFile);
    }

    public abstract ProfileMagic magic();

    public void onRefreshed() {
        super.onRefreshed();
        body().refresh();
    }

    public boolean removeData(String str) {
        return body().removeData(str);
    }

    public boolean removeIfName(Predicate<String> predicate) {
        return body().removeIfName(predicate);
    }

    public void syncApk(final ZipEntryMap zipEntryMap) {
        removeIfName(new Predicate() { // from class: lab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileFile.b(zipEntryMap, (String) obj);
            }
        });
        Iterator itIteratorWithPath = zipEntryMap.iteratorWithPath(new Predicate() { // from class: mab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileFile.k((String) obj);
            }
        });
        while (itIteratorWithPath.hasNext()) {
            getOrCreate(((InputSource) itIteratorWithPath.next()).getAlias());
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic", magic().get());
        jSONObject.put("version", version().name());
        jSONObject.put("body", body().toJson());
        return jSONObject;
    }

    public String toString() {
        return "magic=" + magic() + ", version=" + version() + ", body=" + body();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        body().update(dexFile);
    }

    public abstract ProfileVersion version();

    public void write(File file) throws IOException {
        OutputStream outputStream = FileUtil.outputStream(file);
        writeBytes(outputStream);
        outputStream.close();
    }
}
