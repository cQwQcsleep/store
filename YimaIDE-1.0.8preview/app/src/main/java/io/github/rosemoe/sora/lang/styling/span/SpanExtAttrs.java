package io.github.rosemoe.sora.lang.styling.span;

import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SpanExtAttrs {
    public static final int EXT_COLOR_RESOLVER = 0;
    public static final int EXT_EXTERNAL_RENDERER = 1;
    public static final int EXT_INTERACTION_INFO = 2;
    public static final int EXT_UNDERLINE_COLOR = 3;

    public static boolean checkType(int i, SpanExt spanExt) {
        if (spanExt == null) {
            return true;
        }
        if (i == 0) {
            return spanExt instanceof SpanColorResolver;
        }
        if (i == 1) {
            return spanExt instanceof SpanExternalRenderer;
        }
        if (i == 2) {
            return spanExt instanceof SpanInteractionInfo;
        }
        if (i != 3) {
            return true;
        }
        return spanExt instanceof ResolvableColor;
    }
}
