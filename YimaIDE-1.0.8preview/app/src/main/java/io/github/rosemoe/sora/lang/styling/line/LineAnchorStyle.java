package io.github.rosemoe.sora.lang.styling.line;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "", "line", "", "<init>", "(I)V", "getLine", "()I", "setLine", "customData", "", "getCustomData", "()Ljava/lang/Object;", "setCustomData", "(Ljava/lang/Object;)V", "compareTo", "other", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public abstract class LineAnchorStyle implements Comparable<LineAnchorStyle> {
    private Object customData;
    private int line;

    public LineAnchorStyle(int i) {
        this.line = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(LineAnchorStyle other) {
        other.getClass();
        return Intrinsics.compare(getLine(), other.getLine());
    }

    public final Object getCustomData() {
        return this.customData;
    }

    public int getLine() {
        return this.line;
    }

    public final void setCustomData(Object obj) {
        this.customData = obj;
    }

    public void setLine(int i) {
        this.line = i;
    }
}
