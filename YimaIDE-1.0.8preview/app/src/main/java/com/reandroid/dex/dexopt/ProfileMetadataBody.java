package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.dexopt.ProfileMetadata;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMetadataBody extends ProfileBody implements LinkableProfileItem {
    private final CountedBlockList<ProfileMetadata> dataList;
    private final CountedBlockList<ProfileMetadataHeaderV1> headerList;

    public ProfileMetadataBody(final ProfileVersion profileVersion, final IntegerReference integerReference, IntegerReference integerReference2, IntegerReference integerReference3) {
        super(2, profileVersion, integerReference2, integerReference3);
        CountedBlockList<ProfileMetadataHeaderV1> countedBlockList = new CountedBlockList<>(ProfileMetadataHeaderV1.CREATOR, new IntegerReference() { // from class: com.reandroid.dex.dexopt.ProfileMetadataBody.1
            public int get() {
                if (profileVersion.get() == ProfileVersion.METADATA_V001_N) {
                    return integerReference.get();
                }
                return 0;
            }

            public void set(int i) {
                if (profileVersion.get() == ProfileVersion.METADATA_V001_N) {
                    integerReference.set(i);
                }
            }

            public String toString() {
                return Integer.toString(get());
            }
        });
        this.headerList = countedBlockList;
        CountedBlockList<ProfileMetadata> countedBlockList2 = new CountedBlockList<>(new Creator<ProfileMetadata>() { // from class: com.reandroid.dex.dexopt.ProfileMetadataBody.2
            @Override // com.reandroid.arsc.base.Creator
            public ProfileMetadata newInstance() {
                if (profileVersion.get() != ProfileVersion.METADATA_V001_N) {
                    return new ProfileMetadataV2();
                }
                f63.a("Must call newInstanceAt");
                return null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.reandroid.arsc.base.Creator
            public ProfileMetadata newInstanceAt(int i) {
                if (profileVersion.get() != ProfileVersion.METADATA_V001_N) {
                    return new ProfileMetadataV2();
                }
                if (ProfileMetadataBody.this.headerList.size() <= i) {
                    ProfileMetadataBody.this.headerList.setSize(i + 1);
                }
                return new ProfileMetadataV1((ProfileMetadataHeaderV1) ProfileMetadataBody.this.headerList.get(i));
            }
        }, integerReference);
        this.dataList = countedBlockList2;
        addChild(0, countedBlockList);
        addChild(1, countedBlockList2);
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public ProfileMetadata get(int i) {
        return this.dataList.get(i);
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public Iterator<ProfileMetadata> iterator() {
        return this.dataList.iterator();
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public boolean removeData(int i) {
        return this.dataList.remove(i) != null;
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public boolean removeIfName(final Predicate<String> predicate) {
        return this.dataList.removeIf(new Predicate() { // from class: wab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((ProfileMetadata) obj).getName());
            }
        });
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public void setSize(int i) {
        this.dataList.setSize(i);
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public int size() {
        return this.dataList.size();
    }
}
