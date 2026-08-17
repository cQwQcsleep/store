package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.dexopt.DexProfileData;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileDataBody extends ProfileBody {
    private final CountedBlockList<DexProfileData> dataList;
    private final CountedBlockList<ProfileDataHeader> headerList;

    public ProfileDataBody(ProfileVersion profileVersion, IntegerReference integerReference, IntegerReference integerReference2, IntegerReference integerReference3) {
        super(2, profileVersion, integerReference2, integerReference3);
        CountedBlockList<ProfileDataHeader> countedBlockList = new CountedBlockList<>(ProfileDataHeader.CREATOR, integerReference);
        this.headerList = countedBlockList;
        CountedBlockList<DexProfileData> countedBlockList2 = new CountedBlockList<>(new Creator<DexProfileData>() { // from class: com.reandroid.dex.dexopt.ProfileDataBody.1
            @Override // com.reandroid.arsc.base.Creator
            public DexProfileData newInstance() {
                throw new RuntimeException("Must call newInstanceAt");
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.reandroid.arsc.base.Creator
            public DexProfileData newInstanceAt(int i) {
                if (ProfileDataBody.this.headerList.size() <= i) {
                    ProfileDataBody.this.headerList.setSize(i + 1);
                }
                return new DexProfileDataVersionP((ProfileDataHeader) ProfileDataBody.this.headerList.get(i));
            }
        }, integerReference);
        this.dataList = countedBlockList2;
        addChild(0, countedBlockList);
        addChild(1, countedBlockList2);
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public DexProfileData get(int i) {
        return this.dataList.get(i);
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public Iterator<DexProfileData> iterator() {
        return this.dataList.clonedIterator();
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public boolean removeData(int i) {
        return this.dataList.remove(i) != null;
    }

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public boolean removeIfName(final Predicate<String> predicate) {
        return this.dataList.removeIf(new Predicate() { // from class: dab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((DexProfileData) obj).getName());
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

    @Override // com.reandroid.dex.dexopt.ProfileBody
    public DexProfileData get(String str) {
        return (DexProfileDataVersionP) super.get(str);
    }
}
