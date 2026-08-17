package com.reandroid.dex.dexopt;

import com.reandroid.archive.ByteInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.dexopt.ProfileDataFile;
import com.reandroid.utils.Crc32;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileDataFile extends ProfileFile {
    private final ProfileDataBody body;
    private final ProfileMagic magic;
    private final ProfileVersion version;

    public ProfileDataFile() {
        super(6);
        ProfileMagic profileMagic = new ProfileMagic();
        this.magic = profileMagic;
        ProfileVersion profileVersion = new ProfileVersion();
        this.version = profileVersion;
        ByteItem byteItem = new ByteItem();
        IntegerItem integerItemNewBodySize = profileVersion.newBodySize();
        IntegerItem integerItemNewBodySize2 = profileVersion.newBodySize();
        ProfileDataBody profileDataBody = new ProfileDataBody(profileVersion, byteItem, integerItemNewBodySize, integerItemNewBodySize2);
        this.body = profileDataBody;
        addChild(0, profileMagic);
        addChild(1, profileVersion);
        addChild(2, byteItem);
        addChild(3, integerItemNewBodySize);
        addChild(4, integerItemNewBodySize2);
        addChild(5, profileDataBody);
        profileMagic.set(ProfileMagic.MAGIC_PROF);
        profileVersion.set(ProfileVersion.V010_P);
    }

    public static /* synthetic */ boolean o(ZipEntryMap zipEntryMap, String str) {
        return !zipEntryMap.contains(str);
    }

    public static ProfileDataFile read(File file) throws IOException {
        ProfileDataFile profileDataFile = new ProfileDataFile();
        profileDataFile.readBytes(new BlockReader(file));
        return profileDataFile;
    }

    public static void update(ZipEntryMap zipEntryMap) throws IOException {
        String str = ProfileFile.PATH_PROF;
        InputSource inputSource = zipEntryMap.getInputSource(str);
        if (inputSource == null) {
            return;
        }
        ProfileDataFile profileDataFile = read(inputSource.openStream());
        profileDataFile.updateChecksum(zipEntryMap);
        profileDataFile.refresh();
        ByteInputSource byteInputSource = new ByteInputSource(profileDataFile.getBytes(), str);
        byteInputSource.copyAttributes(inputSource);
        zipEntryMap.add(byteInputSource);
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public DexProfileData get(String str) {
        return (DexProfileData) super.get(str);
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public Iterator<DexProfileData> iterator() {
        return body().iterator();
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileMagic magic() {
        return this.magic;
    }

    public void updateChecksum(final ZipEntryMap zipEntryMap) throws IOException {
        removeIfName(new Predicate() { // from class: eab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileDataFile.o(zipEntryMap, (String) obj);
            }
        });
        for (DexProfileData dexProfileData : this) {
            dexProfileData.setChecksum(zipEntryMap.getInputSource(dexProfileData.getName()).getCrc());
        }
    }

    public void updateFileChecksum(File file) throws IOException {
        DexProfileData dexProfileData = get(file.getName());
        if (dexProfileData != null) {
            dexProfileData.setChecksum(Crc32.of(file));
        }
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileVersion version() {
        return this.version;
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileDataBody body() {
        return this.body;
    }

    public static ProfileDataFile read(InputStream inputStream) throws IOException {
        ProfileDataFile profileDataFile = new ProfileDataFile();
        profileDataFile.readBytes(new BlockReader(inputStream));
        return profileDataFile;
    }
}
