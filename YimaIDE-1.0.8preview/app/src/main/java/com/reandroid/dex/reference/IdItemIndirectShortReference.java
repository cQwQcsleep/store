package com.reandroid.dex.reference;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.HexUtil;
import defpackage.jq6;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IdItemIndirectShortReference<T extends IdItem> extends IdItemIndirectReference<T> {
    public IdItemIndirectShortReference(SectionType<T> sectionType, SectionItem sectionItem, int i, int i2) {
        super(sectionType, sectionItem, i, i2);
        Block.putShort(getBytesInternal(), getOffset(), 65535);
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public int get() {
        return Block.getShortUnsigned(getBytesInternal(), getOffset());
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public T pullItem(int i) {
        if (i == 65535) {
            return null;
        }
        return (T) super.pullItem(i);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public void set(int i) throws DexException {
        if (((-65536) & i) == 0) {
            Block.putShort(getBytesInternal(), getOffset(), i);
        } else {
            jq6.a("Short value out of range ", HexUtil.toHex(i, 4), " > 0xffff");
        }
    }
}
