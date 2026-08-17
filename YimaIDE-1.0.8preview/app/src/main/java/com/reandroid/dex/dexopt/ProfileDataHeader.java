package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.arsc.item.StringReference;
import com.reandroid.dex.dexopt.ProfileDataHeader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileDataHeader extends FixedBlockContainer {
    public static final Creator<ProfileDataHeader> CREATOR = new Creator() { // from class: fab
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new ProfileDataHeader();
        }
    };
    public final IntegerReference classSetSize;
    public final IntegerReference dexChecksum;
    public final IntegerReference hotMethodRegionSize;
    public final StringReference name;
    public final IntegerReference numMethodIds;

    public ProfileDataHeader() {
        super(6);
        ShortItem shortItem = new ShortItem();
        Block shortItem2 = new ShortItem();
        this.classSetSize = shortItem2;
        Block integerItem = new IntegerItem();
        this.hotMethodRegionSize = integerItem;
        Block integerItem2 = new IntegerItem();
        this.dexChecksum = integerItem2;
        Block integerItem3 = new IntegerItem();
        this.numMethodIds = integerItem3;
        Block profString = new ProfString(shortItem);
        this.name = profString;
        addChild(0, shortItem);
        addChild(1, shortItem2);
        addChild(2, integerItem);
        addChild(3, integerItem2);
        addChild(4, integerItem3);
        addChild(5, profString);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
    }

    public String toString() {
        return "ProfileDataHeader{name=" + this.name + ", classSetSize=" + this.classSetSize + ", hotMethodRegionSize=" + this.hotMethodRegionSize + ", dexChecksum=" + this.dexChecksum + ", numMethodIds=" + this.numMethodIds + '}';
    }
}
