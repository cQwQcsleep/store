package com.reandroid.dex.sections;

import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.dex.base.OffsetReceiver;
import com.reandroid.dex.base.PositionedItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionItemContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SpecialItem extends SectionItemContainer implements PositionedItem, OffsetSupplier, OffsetReceiver {
    public SpecialItem(int i) {
        super(i);
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.base.UsageMarker
    public boolean containsUsage(int i) {
        return i != UsageMarker.USAGE_NONE;
    }
}
