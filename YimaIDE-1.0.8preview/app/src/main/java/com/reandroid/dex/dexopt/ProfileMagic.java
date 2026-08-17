package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMagic extends BlockItem implements IntegerReference {
    public static final int MAGIC_PROF = ObjectsUtil.of(7303792);
    public static final int MAGIC_PROFM = ObjectsUtil.of(7172720);

    public ProfileMagic(int i) {
        super(4);
        set(i);
    }

    public int get() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    public boolean isProf() {
        return MAGIC_PROF == get();
    }

    public boolean isProfM() {
        return MAGIC_PROFM == get();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        int i = get();
        if ((i == MAGIC_PROF) != (getParentInstance(ProfileDataFile.class) == null)) {
            return;
        }
        u8g.a("Invalid magic: ", HexUtil.toHex8(i));
    }

    public void set(int i) {
        Block.putInteger(getBytesInternal(), 0, i);
    }

    public String toString() {
        int i = get();
        if (i == MAGIC_PROF) {
            return "pro\\0";
        }
        return i == MAGIC_PROFM ? "prm\\0" : HexUtil.toHexString(getBytesInternal());
    }

    public ProfileMagic() {
        this(MAGIC_PROF);
    }
}
