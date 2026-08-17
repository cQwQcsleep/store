package io.github.rosemoe.sora.lang.diagnostic;

import android.content.Context;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB#\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lio/github/rosemoe/sora/lang/diagnostic/Quickfix;", "", "title", "", "documentVersion", "", "fixAction", "Ljava/lang/Runnable;", "<init>", "(Ljava/lang/CharSequence;JLjava/lang/Runnable;)V", "titleRes", "", "(IJLjava/lang/Runnable;)V", "getDocumentVersion", "()J", "resourceId", "resolveTitle", "context", "Landroid/content/Context;", "executeQuickfix", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class Quickfix {
    private final long documentVersion;
    private final Runnable fixAction;
    private int resourceId;
    private final CharSequence title;

    public /* synthetic */ Quickfix(CharSequence charSequence, long j, Runnable runnable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(charSequence, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? null : runnable);
    }

    public void executeQuickfix() {
        Runnable runnable = this.fixAction;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final long getDocumentVersion() {
        return this.documentVersion;
    }

    public CharSequence resolveTitle(Context context) {
        context.getClass();
        CharSequence charSequence = this.title;
        if (charSequence != null) {
            return charSequence;
        }
        String string = context.getString(this.resourceId);
        string.getClass();
        return string;
    }

    public Quickfix(CharSequence charSequence, long j, Runnable runnable) {
        this.title = charSequence;
        this.documentVersion = j;
        this.fixAction = runnable;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Quickfix(int i, long j, Runnable runnable) {
        this((CharSequence) null, j, runnable);
        runnable.getClass();
        this.resourceId = i;
    }

    public /* synthetic */ Quickfix(int i, long j, Runnable runnable, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 0L : j, runnable);
    }
}
