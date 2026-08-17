package io.github.rosemoe.sora.widget.ext;

import io.github.rosemoe.sora.lang.styling.span.SpanInteractionInfo;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* synthetic */ class EditorSpanInteractionHandler$2$1 extends FunctionReferenceImpl implements Function1<SpanInteractionInfo, Boolean> {
    public static final EditorSpanInteractionHandler$2$1 INSTANCE = new EditorSpanInteractionHandler$2$1();

    public EditorSpanInteractionHandler$2$1() {
        super(1, SpanInteractionInfo.class, "isDoubleClickable", "isDoubleClickable()Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(SpanInteractionInfo spanInteractionInfo) {
        spanInteractionInfo.getClass();
        return Boolean.valueOf(spanInteractionInfo.isDoubleClickable());
    }
}
