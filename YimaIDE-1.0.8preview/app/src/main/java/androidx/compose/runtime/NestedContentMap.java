package androidx.compose.runtime;

import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006J\u0019\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006H\u0086\u0002J\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\nR$\u0010\u0004\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR$\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0016"}, d2 = {"Landroidx/compose/runtime/NestedContentMap;", "", "<init>", "()V", "contentMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "Landroidx/compose/runtime/MovableContent;", "Landroidx/compose/runtime/NestedMovableContent;", "Landroidx/collection/MutableScatterMap;", "containerMap", "Landroidx/compose/runtime/MovableContentStateReference;", "add", "", "content", "nestedContent", "clear", "removeLast", "key", "contains", "", "usedContainer", "reference", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class NestedContentMap {
    private final MutableScatterMap<Object, Object> contentMap = MultiValueMap.m2468constructorimpl$default(null, 1, null);
    private final MutableScatterMap<Object, Object> containerMap = MultiValueMap.m2468constructorimpl$default(null, 1, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean usedContainer$lambda$0$0(MovableContentStateReference movableContentStateReference, NestedMovableContent nestedMovableContent) {
        return Intrinsics.areEqual(nestedMovableContent.getContainer(), movableContentStateReference);
    }

    public final void add(MovableContent<Object> content, NestedMovableContent nestedContent) {
        MultiValueMap.m2464addimpl(this.contentMap, content, nestedContent);
        MultiValueMap.m2464addimpl(this.containerMap, nestedContent.getContainer(), content);
    }

    public final void clear() {
        MultiValueMap.m2466clearimpl(this.contentMap);
        MultiValueMap.m2466clearimpl(this.containerMap);
    }

    public final boolean contains(MovableContent<Object> key) {
        return MultiValueMap.m2469containsimpl(this.contentMap, key);
    }

    public final NestedMovableContent removeLast(MovableContent<Object> key) {
        NestedMovableContent nestedMovableContent = (NestedMovableContent) MultiValueMap.m2479removeLastimpl(this.contentMap, key);
        if (MultiValueMap.m2476isEmptyimpl(this.contentMap)) {
            MultiValueMap.m2466clearimpl(this.containerMap);
        }
        return nestedMovableContent;
    }

    public final void usedContainer(final MovableContentStateReference reference) {
        Object obj = this.containerMap.get(reference);
        if (obj != null) {
            if (!(obj instanceof MutableObjectList)) {
                MultiValueMap.m2480removeValueIfimpl(this.contentMap, (MovableContent) obj, new Function1() { // from class: androidx.compose.runtime.e
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(NestedContentMap.usedContainer$lambda$0$0(reference, (NestedMovableContent) obj2));
                    }
                });
                return;
            }
            ObjectList objectList = (ObjectList) obj;
            Object[] objArr = objectList.content;
            int i = objectList._size;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj2 = objArr[i2];
                obj2.getClass();
                MultiValueMap.m2480removeValueIfimpl(this.contentMap, (MovableContent) obj2, new Function1() { // from class: androidx.compose.runtime.e
                    public final Object invoke(Object obj3) {
                        return Boolean.valueOf(NestedContentMap.usedContainer$lambda$0$0(reference, (NestedMovableContent) obj3));
                    }
                });
            }
        }
    }
}
