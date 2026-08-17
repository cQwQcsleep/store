package io.github.rosemoe.sora.lang.styling.patching;

import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0000H\u0096\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010#\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!¨\u0006("}, d2 = {"Lio/github/rosemoe/sora/lang/styling/patching/StylePatch;", "", "startLine", "", "startColumn", "endLine", "endColumn", "<init>", "(IIII)V", "getStartLine", "()I", "setStartLine", "(I)V", "getStartColumn", "setStartColumn", "getEndLine", "setEndLine", "getEndColumn", "setEndColumn", "overrideForeground", "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "getOverrideForeground", "()Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "setOverrideForeground", "(Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "overrideBackground", "getOverrideBackground", "setOverrideBackground", "overrideItalics", "", "getOverrideItalics", "()Ljava/lang/Boolean;", "setOverrideItalics", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "overrideBold", "getOverrideBold", "setOverrideBold", "compareTo", "other", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class StylePatch implements Comparable<StylePatch> {
    private int endColumn;
    private int endLine;
    private ResolvableColor overrideBackground;
    private Boolean overrideBold;
    private ResolvableColor overrideForeground;
    private Boolean overrideItalics;
    private int startColumn;
    private int startLine;

    public StylePatch(int i, int i2, int i3, int i4) {
        this.startLine = i;
        this.startColumn = i2;
        this.endLine = i3;
        this.endColumn = i4;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0) {
            w01.a("negative number");
            throw null;
        }
        if (i3 < i || (i3 == i && i4 < i2)) {
            w01.a("end < start");
            throw null;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(StylePatch other) {
        other.getClass();
        int iCompare = Intrinsics.compare(this.startLine, other.startLine);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Intrinsics.compare(this.startColumn, other.startColumn);
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = Intrinsics.compare(this.endLine, other.endLine);
        return iCompare3 != 0 ? iCompare3 : Intrinsics.compare(this.endColumn, other.endColumn);
    }

    public final int getEndColumn() {
        return this.endColumn;
    }

    public final int getEndLine() {
        return this.endLine;
    }

    public final ResolvableColor getOverrideBackground() {
        return this.overrideBackground;
    }

    public final Boolean getOverrideBold() {
        return this.overrideBold;
    }

    public final ResolvableColor getOverrideForeground() {
        return this.overrideForeground;
    }

    public final Boolean getOverrideItalics() {
        return this.overrideItalics;
    }

    public final int getStartColumn() {
        return this.startColumn;
    }

    public final int getStartLine() {
        return this.startLine;
    }

    public final void setEndColumn(int i) {
        this.endColumn = i;
    }

    public final void setEndLine(int i) {
        this.endLine = i;
    }

    public final void setOverrideBackground(ResolvableColor resolvableColor) {
        this.overrideBackground = resolvableColor;
    }

    public final void setOverrideBold(Boolean bool) {
        this.overrideBold = bool;
    }

    public final void setOverrideForeground(ResolvableColor resolvableColor) {
        this.overrideForeground = resolvableColor;
    }

    public final void setOverrideItalics(Boolean bool) {
        this.overrideItalics = bool;
    }

    public final void setStartColumn(int i) {
        this.startColumn = i;
    }

    public final void setStartLine(int i) {
        this.startLine = i;
    }
}
