package com.reandroid.dex.dexopt;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.utils.ObjectsUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMetadataFile extends ProfileFile {
    public final ProfileMetadataBody body;
    public final ProfileMagic magic;
    public final ProfileVersion version;

    public ProfileMetadataFile() {
        super(6);
        ProfileMagic profileMagic = new ProfileMagic();
        this.magic = profileMagic;
        ProfileVersion profileVersion = new ProfileVersion();
        this.version = profileVersion;
        ByteOrShortItem byteOrShortItem = new ByteOrShortItem(version().isMetadataV001());
        IntegerItem integerItemNewBodySize = profileVersion.newBodySize();
        IntegerItem integerItemNewBodySize2 = profileVersion.newBodySize();
        ProfileMetadataBody profileMetadataBody = new ProfileMetadataBody(profileVersion, byteOrShortItem, integerItemNewBodySize, integerItemNewBodySize2);
        this.body = profileMetadataBody;
        addChild(0, profileMagic);
        addChild(1, profileVersion);
        addChild(2, byteOrShortItem);
        addChild(3, integerItemNewBodySize);
        addChild(4, integerItemNewBodySize2);
        addChild(5, profileMetadataBody);
        profileMagic.set(ProfileMagic.MAGIC_PROFM);
        profileVersion.set(ProfileVersion.METADATA_V001_N);
    }

    public static ProfileMetadataFile read(File file) throws IOException {
        ProfileMetadataFile profileMetadataFile = new ProfileMetadataFile();
        profileMetadataFile.readBytes(new BlockReader(file));
        return profileMetadataFile;
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileMetadata get(String str) {
        return (ProfileMetadata) super.get(str);
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public Iterator<ProfileMetadata> iterator() {
        return (Iterator) ObjectsUtil.cast(super.iterator());
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileMagic magic() {
        return this.magic;
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileVersion version() {
        return this.version;
    }

    @Override // com.reandroid.dex.dexopt.ProfileFile
    public ProfileMetadataBody body() {
        return this.body;
    }

    public static ProfileMetadataFile read(InputStream inputStream) throws IOException {
        ProfileMetadataFile profileMetadataFile = new ProfileMetadataFile();
        profileMetadataFile.readBytes(new BlockReader(inputStream));
        return profileMetadataFile;
    }
}
