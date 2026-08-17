package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XC extends AbstractC0706Nu {
    public final transient Map f;
    public final transient AbstractC0551Hu g;

    public XC(HashMap map, AbstractC0551Hu abstractC0551Hu) {
        this.f = map;
        this.g = abstractC0551Hu;
    }

    public static XC a(int i, Map.Entry[] entryArr) {
        HashMap map = new HashMap(AbstractC1739iN.a(i));
        for (int i2 = 0; i2 < i; i2++) {
            Map.Entry entry = entryArr[i2];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            T40 t40 = T40.i;
            C0784Qu c0784QuA = T40.a(entry2, entry2.getKey(), entry2.getValue());
            entryArr[i2] = c0784QuA;
            Object objPut = map.put(c0784QuA.b, c0784QuA.getValue());
            if (objPut != null) {
                throw AbstractC0706Nu.a("key", entryArr[i2], entryArr[i2].getKey() + "=" + objPut);
            }
        }
        return new XC(map, AbstractC0551Hu.b(i, entryArr));
    }

    @Override // java.util.Map
    public final void forEach(final BiConsumer biConsumer) {
        biConsumer.getClass();
        this.g.forEach(new Consumer() { // from class: qxf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        return this.f.get(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        return new C0810Ru(this, this.g);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        return new C0862Tu(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC3066xu k() {
        return new C0940Wu(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // java.util.Map
    public final int size() {
        return this.g.size();
    }
}
