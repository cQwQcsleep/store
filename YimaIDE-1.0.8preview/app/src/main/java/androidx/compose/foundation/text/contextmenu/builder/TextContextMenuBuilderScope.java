package androidx.compose.foundation.text.contextmenu.builder;

import androidx.collection.MutableObjectList;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSeparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ!\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0013J\u0006\u0010\u0014\u001a\u00020\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "", "<init>", "()V", "components", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuComponent;", "filters", "Lkotlin/Function1;", "", "build", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "build$foundation", "addFilter", "", "filter", "addFilter$foundation", "addComponent", "component", "addComponent$foundation", "separator", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuBuilderScope {
    public static final int $stable = 8;
    private final MutableObjectList<TextContextMenuComponent> components = new MutableObjectList<>(0, 1, null);
    private final MutableObjectList<Function1<TextContextMenuComponent, Boolean>> filters = new MutableObjectList<>(0, 1, null);

    public final void addComponent$foundation(TextContextMenuComponent component) {
        this.components.add(component);
    }

    public final void addFilter$foundation(Function1<? super TextContextMenuComponent, Boolean> filter) {
        this.filters.add(filter);
    }

    public final TextContextMenuData build$foundation() {
        MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
        MutableObjectList<TextContextMenuComponent> mutableObjectList2 = this.components;
        Object[] objArr = mutableObjectList2.content;
        int i = mutableObjectList2._size;
        boolean z = true;
        TextContextMenuComponent textContextMenuComponent = null;
        for (int i2 = 0; i2 < i; i2++) {
            TextContextMenuComponent textContextMenuComponent2 = (TextContextMenuComponent) objArr[i2];
            if (!z || textContextMenuComponent2 != TextContextMenuSeparator.INSTANCE) {
                if (TextContextMenuBuilderScopeKt.isSeparator(textContextMenuComponent2) && TextContextMenuBuilderScopeKt.isSeparator(textContextMenuComponent)) {
                    z = false;
                } else {
                    if (!TextContextMenuBuilderScopeKt.isSeparator(textContextMenuComponent2)) {
                        MutableObjectList<Function1<TextContextMenuComponent, Boolean>> mutableObjectList3 = this.filters;
                        Object[] objArr2 = mutableObjectList3.content;
                        int i3 = mutableObjectList3._size;
                        int i4 = 0;
                        while (true) {
                            if (i4 < i3) {
                                if (((Boolean) ((Function1) objArr2[i4]).invoke(textContextMenuComponent2)).booleanValue()) {
                                    i4++;
                                } else {
                                    z = false;
                                }
                            }
                        }
                    }
                    mutableObjectList.add(textContextMenuComponent2);
                    z = false;
                    textContextMenuComponent = textContextMenuComponent2;
                }
            }
        }
        if (TextContextMenuBuilderScopeKt.isSeparator((TextContextMenuComponent) (mutableObjectList.isEmpty() ? null : mutableObjectList.content[mutableObjectList._size - 1]))) {
            mutableObjectList.removeAt(mutableObjectList._size - 1);
        }
        return new TextContextMenuData(mutableObjectList.asList());
    }

    public final void separator() {
        this.components.add(TextContextMenuSeparator.INSTANCE);
    }
}
