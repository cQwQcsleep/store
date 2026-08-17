package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.Savers_androidKt;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\n\" \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0010\"\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0014\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0018\"\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "", "Landroidx/compose/ui/text/PlatformParagraphStyle$Companion;", "getSaver", "(Landroidx/compose/ui/text/PlatformParagraphStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "PlatformParagraphStyleSaver", "Landroidx/compose/ui/text/EmojiSupportMatch;", "Landroidx/compose/ui/text/EmojiSupportMatch$Companion;", "(Landroidx/compose/ui/text/EmojiSupportMatch$Companion;)Landroidx/compose/runtime/saveable/Saver;", "emojiSupportMatchSaver", "getEmojiSupportMatchSaver", "()Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/LineBreak;", "Landroidx/compose/ui/text/style/LineBreak$Companion;", "(Landroidx/compose/ui/text/style/LineBreak$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineBreakSaver", "Landroidx/compose/ui/text/style/TextMotion;", "Landroidx/compose/ui/text/style/TextMotion$Companion;", "(Landroidx/compose/ui/text/style/TextMotion$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextMotionSaver", "Landroidx/compose/ui/text/style/TextMotion$Linearity;", "Landroidx/compose/ui/text/style/TextMotion$Linearity$Companion;", "(Landroidx/compose/ui/text/style/TextMotion$Linearity$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextMotionLinearitySaver", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Savers_androidKt {
    private static final Saver<PlatformParagraphStyle, Object> PlatformParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: muc
        public final Object invoke(Object obj, Object obj2) {
            return Savers_androidKt.d((SaverScope) obj, (PlatformParagraphStyle) obj2);
        }
    }, new Function1() { // from class: nuc
        public final Object invoke(Object obj) {
            return Savers_androidKt.f(obj);
        }
    });
    private static final Saver<EmojiSupportMatch, Object> emojiSupportMatchSaver = SaverKt.Saver(new Function2() { // from class: ouc
        public final Object invoke(Object obj, Object obj2) {
            return Savers_androidKt.i((SaverScope) obj, (EmojiSupportMatch) obj2);
        }
    }, new Function1() { // from class: puc
        public final Object invoke(Object obj) {
            return Savers_androidKt.b(obj);
        }
    });
    private static final Saver<LineBreak, Object> LineBreakSaver = SaverKt.Saver(new Function2() { // from class: quc
        public final Object invoke(Object obj, Object obj2) {
            return Savers_androidKt.g((SaverScope) obj, (LineBreak) obj2);
        }
    }, new Function1() { // from class: ruc
        public final Object invoke(Object obj) {
            return Savers_androidKt.j(obj);
        }
    });
    private static final Saver<TextMotion, Object> TextMotionSaver = SaverKt.Saver(new Function2() { // from class: suc
        public final Object invoke(Object obj, Object obj2) {
            return Savers_androidKt.e((SaverScope) obj, (TextMotion) obj2);
        }
    }, new Function1() { // from class: tuc
        public final Object invoke(Object obj) {
            return Savers_androidKt.a(obj);
        }
    });
    private static final Saver<TextMotion.Linearity, Object> TextMotionLinearitySaver = SaverKt.Saver(new Function2() { // from class: uuc
        public final Object invoke(Object obj, Object obj2) {
            return Savers_androidKt.c((SaverScope) obj, (TextMotion.Linearity) obj2);
        }
    }, new Function1() { // from class: vuc
        public final Object invoke(Object obj) {
            return Savers_androidKt.h(obj);
        }
    });

    public static TextMotion a(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<TextMotion.Linearity, Object> saver = getSaver(TextMotion.Linearity.INSTANCE);
        TextMotion.Linearity linearityRestore = ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        linearityRestore.getClass();
        int value = linearityRestore.getValue();
        Object obj3 = list.get(1);
        Boolean bool = obj3 != null ? (Boolean) obj3 : null;
        bool.getClass();
        return new TextMotion(value, bool.booleanValue(), null);
    }

    public static EmojiSupportMatch b(Object obj) {
        obj.getClass();
        return EmojiSupportMatch.m5326boximpl(EmojiSupportMatch.m5327constructorimpl(((Integer) obj).intValue()));
    }

    public static Object c(SaverScope saverScope, TextMotion.Linearity linearity) {
        return Integer.valueOf(linearity.getValue());
    }

    public static Object d(SaverScope saverScope, PlatformParagraphStyle platformParagraphStyle) {
        return CollectionsKt.arrayListOf(new Object[]{SaversKt.save(Boolean.valueOf(platformParagraphStyle.getIncludeFontPadding())), SaversKt.save(EmojiSupportMatch.m5326boximpl(platformParagraphStyle.getEmojiSupportMatch()), getSaver(EmojiSupportMatch.INSTANCE), saverScope)});
    }

    public static Object e(SaverScope saverScope, TextMotion textMotion) {
        return CollectionsKt.arrayListOf(new Object[]{SaversKt.save(TextMotion.Linearity.m5935boximpl(textMotion.getLinearity()), getSaver(TextMotion.Linearity.INSTANCE), saverScope), SaversKt.save(Boolean.valueOf(textMotion.getSubpixelTextPositioning()))});
    }

    public static PlatformParagraphStyle f(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Boolean bool = obj2 != null ? (Boolean) obj2 : null;
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        Object obj3 = list.get(1);
        Saver<EmojiSupportMatch, Object> saver = getSaver(EmojiSupportMatch.INSTANCE);
        EmojiSupportMatch emojiSupportMatchRestore = ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) ? saver.restore(obj3) : null;
        emojiSupportMatchRestore.getClass();
        return new PlatformParagraphStyle(emojiSupportMatchRestore.getValue(), zBooleanValue, (DefaultConstructorMarker) null);
    }

    public static Object g(SaverScope saverScope, LineBreak lineBreak) {
        return Integer.valueOf(lineBreak.getMask());
    }

    public static final Saver<EmojiSupportMatch, Object> getEmojiSupportMatchSaver() {
        return emojiSupportMatchSaver;
    }

    public static final Saver<PlatformParagraphStyle, Object> getSaver(PlatformParagraphStyle.Companion companion) {
        return PlatformParagraphStyleSaver;
    }

    public static TextMotion.Linearity h(Object obj) {
        obj.getClass();
        return TextMotion.Linearity.m5935boximpl(TextMotion.Linearity.m5936constructorimpl(((Integer) obj).intValue()));
    }

    public static Object i(SaverScope saverScope, EmojiSupportMatch emojiSupportMatch) {
        return Integer.valueOf(emojiSupportMatch.getValue());
    }

    public static LineBreak j(Object obj) {
        obj.getClass();
        return LineBreak.m5798boximpl(LineBreak.m5799constructorimpl(((Integer) obj).intValue()));
    }

    public static final Saver<EmojiSupportMatch, Object> getSaver(EmojiSupportMatch.Companion companion) {
        return emojiSupportMatchSaver;
    }

    public static final Saver<LineBreak, Object> getSaver(LineBreak.Companion companion) {
        return LineBreakSaver;
    }

    public static final Saver<TextMotion, Object> getSaver(TextMotion.Companion companion) {
        return TextMotionSaver;
    }

    private static final Saver<TextMotion.Linearity, Object> getSaver(TextMotion.Linearity.Companion companion) {
        return TextMotionLinearitySaver;
    }
}
