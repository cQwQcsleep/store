package com.reandroid.dex.dexopt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexProfileDataVersionP extends DexProfileData {
    private final ProfileClassList classList;
    private final ProfileDataHeader header;
    private final HotMethodRegionList hotMethodList;
    private final MethodBitmap methodBitmap;

    public DexProfileDataVersionP(ProfileDataHeader profileDataHeader) {
        super(3);
        this.header = profileDataHeader;
        HotMethodRegionList hotMethodRegionList = new HotMethodRegionList(profileDataHeader.hotMethodRegionSize);
        this.hotMethodList = hotMethodRegionList;
        ProfileClassList profileClassList = new ProfileClassList(profileDataHeader.classSetSize);
        this.classList = profileClassList;
        MethodBitmap methodBitmap = new MethodBitmap(profileDataHeader.numMethodIds);
        this.methodBitmap = methodBitmap;
        addChild(0, hotMethodRegionList);
        addChild(1, profileClassList);
        addChild(2, methodBitmap);
    }

    @Override // com.reandroid.dex.dexopt.DexProfileData
    public ProfileClassList classList() {
        return this.classList;
    }

    @Override // com.reandroid.dex.dexopt.DexProfileData
    public long getChecksum() {
        return ((long) this.header.dexChecksum.get()) & 4294967295L;
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public String getName() {
        return header().name.get();
    }

    public ProfileDataHeader header() {
        return this.header;
    }

    @Override // com.reandroid.dex.dexopt.DexProfileData
    public HotMethodRegionList hotMethodList() {
        return this.hotMethodList;
    }

    @Override // com.reandroid.dex.dexopt.DexProfileData
    public MethodBitmap methodBitmap() {
        return this.methodBitmap;
    }

    @Override // com.reandroid.dex.dexopt.DexProfileData
    public void setChecksum(long j) {
        header().dexChecksum.set((int) j);
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public void setName(String str) {
        header().name.set(str);
    }
}
