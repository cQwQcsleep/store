package io.github.rosemoe.sora.lang.styling.inlayHint;

import io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/inlayHint/InlayHint;", "Lio/github/rosemoe/sora/lang/styling/util/PointAnchoredObject;", "line", "", "column", "type", "", "displaySide", "Lio/github/rosemoe/sora/lang/styling/inlayHint/CharacterSide;", "<init>", "(IILjava/lang/String;Lio/github/rosemoe/sora/lang/styling/inlayHint/CharacterSide;)V", "getLine", "()I", "setLine", "(I)V", "getColumn", "setColumn", "getType", "()Ljava/lang/String;", "getDisplaySide", "()Lio/github/rosemoe/sora/lang/styling/inlayHint/CharacterSide;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class InlayHint implements PointAnchoredObject {
    private int column;
    private final CharacterSide displaySide;
    private int line;
    private final String type;

    public InlayHint(int i, int i2, String str, CharacterSide characterSide) {
        str.getClass();
        characterSide.getClass();
        this.line = i;
        this.column = i2;
        this.type = str;
        this.displaySide = characterSide;
        if (getLine() < 0 || getColumn() < 0) {
            w01.a("negative number");
            throw null;
        }
    }

    @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
    public int getColumn() {
        return this.column;
    }

    public final CharacterSide getDisplaySide() {
        return this.displaySide;
    }

    @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
    public int getLine() {
        return this.line;
    }

    public final String getType() {
        return this.type;
    }

    @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
    public void setColumn(int i) {
        this.column = i;
    }

    @Override // io.github.rosemoe.sora.lang.styling.util.PointAnchoredObject
    public void setLine(int i) {
        this.line = i;
    }

    public /* synthetic */ InlayHint(int i, int i2, String str, CharacterSide characterSide, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, str, (i3 & 8) != 0 ? CharacterSide.LEFT : characterSide);
    }
}
