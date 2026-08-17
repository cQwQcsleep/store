package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K3 extends AbstractC3157z0 implements InterfaceC2676tL {
    public final transient int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K3() {
        super(new HashMap(AbstractC1739iN.a(12)));
        int i = WW.a;
        AbstractC0871Ud.a(3, "expectedValuesPerKey");
        this.h = 3;
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a(Object obj) {
        Collection collection = (Collection) this.f.remove(obj);
        if (collection == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(this.h);
        arrayList.addAll(collection);
        this.g -= collection.size();
        collection.clear();
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.android.tools.r8.internal.WP
    public final List get(Object obj) {
        Object arrayList = (Collection) this.f.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList(this.h);
        }
        List list = (List) arrayList;
        return list instanceof RandomAccess ? new C2731u0(this, obj, list, null) : new C3073y0(this, obj, list, null);
    }
}
