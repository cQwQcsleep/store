package com.reandroid.dex.dexopt;

import com.reandroid.arsc.item.StringReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMetadataV1 extends ProfileMetadata {
    private final ProfileClassList classList;
    private final ProfileMetadataHeaderV1 header;

    public ProfileMetadataV1(ProfileMetadataHeaderV1 profileMetadataHeaderV1) {
        super(1);
        this.header = profileMetadataHeaderV1;
        ProfileClassList profileClassList = new ProfileClassList(profileMetadataHeaderV1.classSetSize);
        this.classList = profileClassList;
        addChild(0, profileClassList);
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata
    public ProfileClassList classList() {
        return this.classList;
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata
    public StringReference name() {
        return this.header.name;
    }
}
