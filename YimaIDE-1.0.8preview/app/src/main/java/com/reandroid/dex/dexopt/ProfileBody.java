package com.reandroid.dex.dexopt;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.dexopt.ProfileData;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ProfileBody extends DeflatedBlockContainer implements LinkableProfileItem, JSONConvert<JSONArray> {
    private final ProfileVersion version;

    public ProfileBody(int i, ProfileVersion profileVersion, IntegerReference integerReference, IntegerReference integerReference2) {
        super(i, profileVersion.isDeflatedBody(), integerReference, integerReference2);
        this.version = profileVersion;
    }

    public ProfileData createNew() {
        int size = size();
        setSize(size + 1);
        return get(size);
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONArray jSONArray) {
        int length = jSONArray == null ? 0 : jSONArray.length();
        setSize(length);
        for (int i = 0; i < length; i++) {
            get(i).fromJson(jSONArray.getJSONObject(i));
        }
    }

    public abstract ProfileData get(int i);

    public ProfileData get(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            ProfileData profileData = get(i);
            if (ObjectsUtil.equals(profileData.getName(), str)) {
                return profileData;
            }
        }
        return null;
    }

    public abstract Iterator<? extends ProfileData> iterator();

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        ProfileData profileData = get(dexFile.getSimpleName());
        if (profileData != null) {
            profileData.link(dexFile);
        }
    }

    public abstract boolean removeData(int i);

    public boolean removeData(final String str) {
        return removeIfName(new Predicate() { // from class: n9b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ObjectsUtil.equals((String) obj, str);
            }
        });
    }

    public abstract boolean removeIfName(Predicate<String> predicate);

    public abstract void setSize(int i);

    public abstract int size();

    @Override // com.reandroid.json.JSONConvert
    public JSONArray toJson() {
        int size = size();
        JSONArray jSONArray = new JSONArray(size);
        for (int i = 0; i < size; i++) {
            jSONArray.put(get(i).toJson());
        }
        return jSONArray;
    }

    public String toString() {
        return "size = " + size() + " [" + StringsUtil.join((Iterator<?>) ComputeIterator.of(iterator(), new Function() { // from class: o9b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ProfileData) obj).getName();
            }
        }), ", ") + "]";
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        ProfileData profileData = get(dexFile.getSimpleName());
        if (profileData != null) {
            profileData.update(dexFile);
        }
    }

    public ProfileVersion version() {
        return this.version;
    }
}
