package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.BooleanReference;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileVersion extends BlockItem {
    public static final int V015_S = ObjectsUtil.of(3486000);
    public static final int V010_P = ObjectsUtil.of(3158320);
    public static final int V009_O_MR1 = ObjectsUtil.of(3747888);
    public static final int V005_O = ObjectsUtil.of(3485744);
    public static final int V001_N = ObjectsUtil.of(3223600);
    public static final int METADATA_V001_N = ObjectsUtil.of(3223600);
    public static final int METADATA_V002 = ObjectsUtil.of(3289136);

    public ProfileVersion() {
        super(4);
    }

    public static boolean isSupported(int i) {
        return i == V010_P || i == METADATA_V002;
    }

    public int get() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    public boolean hasDeflatedBody() {
        int i = get();
        return i == V010_P || i == METADATA_V001_N || i == METADATA_V002;
    }

    public BooleanReference isDeflatedBody() {
        return new BooleanReference() { // from class: com.reandroid.dex.dexopt.ProfileVersion.3
            @Override // com.reandroid.arsc.item.BooleanReference
            public boolean get() {
                return ProfileVersion.this.hasDeflatedBody();
            }

            @Override // com.reandroid.arsc.item.BooleanReference
            public void set(boolean z) {
            }

            public String toString() {
                return Boolean.toString(get());
            }
        };
    }

    public BooleanReference isMetadataV001() {
        return new BooleanReference() { // from class: com.reandroid.dex.dexopt.ProfileVersion.2
            @Override // com.reandroid.arsc.item.BooleanReference
            public boolean get() {
                return ProfileVersion.this.get() == ProfileVersion.METADATA_V001_N;
            }

            @Override // com.reandroid.arsc.item.BooleanReference
            public void set(boolean z) {
            }

            public String toString() {
                return Boolean.toString(get());
            }
        };
    }

    public void name(String str) {
        int i;
        if ("V015_S".equals(str)) {
            i = V015_S;
        } else if ("V010_P".equals(str)) {
            i = V010_P;
        } else if ("V009_O_MR1".equals(str)) {
            i = V009_O_MR1;
        } else if ("V005_O".equals(str)) {
            i = V005_O;
        } else if ("V001_N".equals(str)) {
            i = V001_N;
        } else if ("METADATA_V001_N".equals(str)) {
            i = METADATA_V001_N;
        } else {
            if (!"METADATA_V002".equals(str)) {
                obi.a("Unknown version name: '", str, "'");
                return;
            }
            i = METADATA_V002;
        }
        set(i);
    }

    public IntegerItem newBodySize() {
        return new IntegerItem() { // from class: com.reandroid.dex.dexopt.ProfileVersion.1
            public boolean isNull() {
                return super/*com.reandroid.arsc.base.Block*/.isNull() || !ProfileVersion.this.hasDeflatedBody();
            }
        };
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        if (isSupported(get())) {
            return;
        }
        throw new IOException("Unsupported version: " + get() + " (" + toString() + ")");
    }

    public void set(byte[] bArr) {
        System.arraycopy(bArr, 0, getBytesInternal(), 0, 4);
    }

    public String toString() {
        return name();
    }

    public void set(int i) {
        Block.putInteger(getBytesInternal(), 0, i);
    }

    public String name() {
        int i = get();
        if (i == V015_S) {
            return "V015_S";
        }
        if (i == V010_P) {
            return "V010_P";
        }
        if (i == V009_O_MR1) {
            return "V009_O_MR1";
        }
        if (i == V005_O) {
            return "V005_O";
        }
        if (i == V001_N && getParentInstance(ProfileDataFile.class) != null) {
            return "V001_N";
        }
        if (i == METADATA_V001_N && getParentInstance(ProfileMetadataFile.class) != null) {
            return "METADATA_V001_N";
        }
        if (i == METADATA_V002) {
            return "METADATA_V002";
        }
        return HexUtil.toHex8(i);
    }
}
