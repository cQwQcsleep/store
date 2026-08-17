package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.arsc.item.StringReference;
import com.reandroid.dex.dexopt.ProfileMetadataHeaderV1;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMetadataHeaderV1 extends FixedBlockContainer {
    public static final Creator<ProfileMetadataHeaderV1> CREATOR = new Creator() { // from class: xab
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new ProfileMetadataHeaderV1();
        }
    };
    public final IntegerReference classSetSize;
    public final StringReference name;

    public ProfileMetadataHeaderV1() {
        super(3);
        ShortItem shortItem = new ShortItem();
        Block profString = new ProfString(shortItem);
        this.name = profString;
        Block shortItem2 = new ShortItem();
        this.classSetSize = shortItem2;
        addChild(0, shortItem);
        addChild(1, profString);
        addChild(2, shortItem2);
    }

    public String toString() {
        return "ProfileMetadataHeaderV1{name=" + this.name + ", classSetSize=" + this.classSetSize + '}';
    }
}
