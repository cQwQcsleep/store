package androidx.compose.material3.tokens;

import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\b¸\u0001\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007R\u0013\u0010#\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b$\u0010\u0007R\u0013\u0010%\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b&\u0010\u0007R\u0013\u0010'\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b(\u0010\u0007R\u0013\u0010)\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b*\u0010\u0007R\u0013\u0010+\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b,\u0010\u0007R\u0013\u0010-\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b.\u0010\u0007R\u0013\u0010/\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b0\u0010\u0007R\u0013\u00101\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b2\u0010\u0007R\u0013\u00103\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b4\u0010\u0007R\u0013\u00105\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b6\u0010\u0007R\u0013\u00107\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b8\u0010\u0007R\u0013\u00109\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b:\u0010\u0007R\u0013\u0010;\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b<\u0010\u0007R\u0013\u0010=\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b>\u0010\u0007R\u0013\u0010?\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b@\u0010\u0007R\u0013\u0010A\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bB\u0010\u0007R\u0013\u0010C\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bD\u0010\u0007R\u0013\u0010E\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bF\u0010\u0007R\u0013\u0010G\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bH\u0010\u0007R\u0013\u0010I\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bJ\u0010\u0007R\u0013\u0010K\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bL\u0010\u0007R\u0013\u0010M\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bN\u0010\u0007R\u0013\u0010O\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bP\u0010\u0007R\u0013\u0010Q\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bR\u0010\u0007R\u0013\u0010S\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bT\u0010\u0007R\u0013\u0010U\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bV\u0010\u0007R\u0013\u0010W\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bX\u0010\u0007R\u0013\u0010Y\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bZ\u0010\u0007R\u0013\u0010[\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\\\u0010\u0007R\u0013\u0010]\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b^\u0010\u0007R\u0013\u0010_\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b`\u0010\u0007R\u0013\u0010a\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bb\u0010\u0007R\u0013\u0010c\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bd\u0010\u0007R\u0013\u0010e\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bf\u0010\u0007R\u0013\u0010g\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bh\u0010\u0007R\u0013\u0010i\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bj\u0010\u0007R\u0013\u0010k\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bl\u0010\u0007R\u0013\u0010m\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bn\u0010\u0007R\u0013\u0010o\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bp\u0010\u0007R\u0013\u0010q\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\br\u0010\u0007R\u0013\u0010s\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bt\u0010\u0007R\u0013\u0010u\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bv\u0010\u0007R\u0013\u0010w\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bx\u0010\u0007R\u0013\u0010y\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\bz\u0010\u0007R\u0013\u0010{\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b|\u0010\u0007R\u0013\u0010}\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b~\u0010\u0007R\u0014\u0010\u007f\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0080\u0001\u0010\u0007R\u0015\u0010\u0081\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0082\u0001\u0010\u0007R\u0015\u0010\u0083\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0084\u0001\u0010\u0007R\u0015\u0010\u0085\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0086\u0001\u0010\u0007R\u0015\u0010\u0087\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0088\u0001\u0010\u0007R\u0015\u0010\u0089\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u008a\u0001\u0010\u0007R\u0015\u0010\u008b\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u008c\u0001\u0010\u0007R\u0015\u0010\u008d\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u008e\u0001\u0010\u0007R\u0015\u0010\u008f\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0090\u0001\u0010\u0007R\u0015\u0010\u0091\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0092\u0001\u0010\u0007R\u0015\u0010\u0093\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0094\u0001\u0010\u0007R\u0015\u0010\u0095\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0096\u0001\u0010\u0007R\u0015\u0010\u0097\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u0098\u0001\u0010\u0007R\u0015\u0010\u0099\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u009a\u0001\u0010\u0007R\u0015\u0010\u009b\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u009c\u0001\u0010\u0007R\u0015\u0010\u009d\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b\u009e\u0001\u0010\u0007R\u0015\u0010\u009f\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b \u0001\u0010\u0007R\u0015\u0010¡\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¢\u0001\u0010\u0007R\u0015\u0010£\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¤\u0001\u0010\u0007R\u0015\u0010¥\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¦\u0001\u0010\u0007R\u0015\u0010§\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¨\u0001\u0010\u0007R\u0015\u0010©\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\bª\u0001\u0010\u0007R\u0015\u0010«\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¬\u0001\u0010\u0007R\u0015\u0010\u00ad\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b®\u0001\u0010\u0007R\u0015\u0010¯\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b°\u0001\u0010\u0007R\u0015\u0010±\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b²\u0001\u0010\u0007R\u0015\u0010³\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b´\u0001\u0010\u0007R\u0015\u0010µ\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¶\u0001\u0010\u0007R\u0015\u0010·\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¸\u0001\u0010\u0007R\u0015\u0010¹\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\bº\u0001\u0010\u0007R\u0015\u0010»\u0001\u001a\u00020\u0005¢\u0006\u000b\n\u0002\u0010\b\u001a\u0005\b¼\u0001\u0010\u0007¨\u0006½\u0001"}, d2 = {"Landroidx/compose/material3/tokens/PaletteTokens;", "", "<init>", "()V", "Black", "Landroidx/compose/ui/graphics/Color;", "getBlack-0d7_KjU", "()J", "J", "Error0", "getError0-0d7_KjU", "Error10", "getError10-0d7_KjU", "Error100", "getError100-0d7_KjU", "Error20", "getError20-0d7_KjU", "Error30", "getError30-0d7_KjU", "Error40", "getError40-0d7_KjU", "Error50", "getError50-0d7_KjU", "Error60", "getError60-0d7_KjU", "Error70", "getError70-0d7_KjU", "Error80", "getError80-0d7_KjU", "Error90", "getError90-0d7_KjU", "Error95", "getError95-0d7_KjU", "Error99", "getError99-0d7_KjU", "Neutral0", "getNeutral0-0d7_KjU", "Neutral10", "getNeutral10-0d7_KjU", "Neutral100", "getNeutral100-0d7_KjU", "Neutral12", "getNeutral12-0d7_KjU", "Neutral17", "getNeutral17-0d7_KjU", "Neutral20", "getNeutral20-0d7_KjU", "Neutral22", "getNeutral22-0d7_KjU", "Neutral24", "getNeutral24-0d7_KjU", "Neutral30", "getNeutral30-0d7_KjU", "Neutral4", "getNeutral4-0d7_KjU", "Neutral40", "getNeutral40-0d7_KjU", "Neutral50", "getNeutral50-0d7_KjU", "Neutral6", "getNeutral6-0d7_KjU", "Neutral60", "getNeutral60-0d7_KjU", "Neutral70", "getNeutral70-0d7_KjU", "Neutral80", "getNeutral80-0d7_KjU", "Neutral87", "getNeutral87-0d7_KjU", "Neutral90", "getNeutral90-0d7_KjU", "Neutral92", "getNeutral92-0d7_KjU", "Neutral94", "getNeutral94-0d7_KjU", "Neutral95", "getNeutral95-0d7_KjU", "Neutral96", "getNeutral96-0d7_KjU", "Neutral98", "getNeutral98-0d7_KjU", "Neutral99", "getNeutral99-0d7_KjU", "NeutralVariant0", "getNeutralVariant0-0d7_KjU", "NeutralVariant10", "getNeutralVariant10-0d7_KjU", "NeutralVariant100", "getNeutralVariant100-0d7_KjU", "NeutralVariant20", "getNeutralVariant20-0d7_KjU", "NeutralVariant30", "getNeutralVariant30-0d7_KjU", "NeutralVariant40", "getNeutralVariant40-0d7_KjU", "NeutralVariant50", "getNeutralVariant50-0d7_KjU", "NeutralVariant60", "getNeutralVariant60-0d7_KjU", "NeutralVariant70", "getNeutralVariant70-0d7_KjU", "NeutralVariant80", "getNeutralVariant80-0d7_KjU", "NeutralVariant90", "getNeutralVariant90-0d7_KjU", "NeutralVariant95", "getNeutralVariant95-0d7_KjU", "NeutralVariant99", "getNeutralVariant99-0d7_KjU", "Primary0", "getPrimary0-0d7_KjU", "Primary10", "getPrimary10-0d7_KjU", "Primary100", "getPrimary100-0d7_KjU", "Primary20", "getPrimary20-0d7_KjU", "Primary30", "getPrimary30-0d7_KjU", "Primary40", "getPrimary40-0d7_KjU", "Primary50", "getPrimary50-0d7_KjU", "Primary60", "getPrimary60-0d7_KjU", "Primary70", "getPrimary70-0d7_KjU", "Primary80", "getPrimary80-0d7_KjU", "Primary90", "getPrimary90-0d7_KjU", "Primary95", "getPrimary95-0d7_KjU", "Primary99", "getPrimary99-0d7_KjU", "Secondary0", "getSecondary0-0d7_KjU", "Secondary10", "getSecondary10-0d7_KjU", "Secondary100", "getSecondary100-0d7_KjU", "Secondary20", "getSecondary20-0d7_KjU", "Secondary30", "getSecondary30-0d7_KjU", "Secondary40", "getSecondary40-0d7_KjU", "Secondary50", "getSecondary50-0d7_KjU", "Secondary60", "getSecondary60-0d7_KjU", "Secondary70", "getSecondary70-0d7_KjU", "Secondary80", "getSecondary80-0d7_KjU", "Secondary90", "getSecondary90-0d7_KjU", "Secondary95", "getSecondary95-0d7_KjU", "Secondary99", "getSecondary99-0d7_KjU", "Tertiary0", "getTertiary0-0d7_KjU", "Tertiary10", "getTertiary10-0d7_KjU", "Tertiary100", "getTertiary100-0d7_KjU", "Tertiary20", "getTertiary20-0d7_KjU", "Tertiary30", "getTertiary30-0d7_KjU", "Tertiary40", "getTertiary40-0d7_KjU", "Tertiary50", "getTertiary50-0d7_KjU", "Tertiary60", "getTertiary60-0d7_KjU", "Tertiary70", "getTertiary70-0d7_KjU", "Tertiary80", "getTertiary80-0d7_KjU", "Tertiary90", "getTertiary90-0d7_KjU", "Tertiary95", "getTertiary95-0d7_KjU", "Tertiary99", "getTertiary99-0d7_KjU", "White", "getWhite-0d7_KjU", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PaletteTokens {
    public static final int $stable = 0;
    public static final PaletteTokens INSTANCE = new PaletteTokens();
    private static final long Black = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Error0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Error10 = ColorKt.Color$default(65, 14, 11, 0, 8, null);
    private static final long Error100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long Error20 = ColorKt.Color$default(96, 20, 16, 0, 8, null);
    private static final long Error30 = ColorKt.Color$default(140, 29, 24, 0, 8, null);
    private static final long Error40 = ColorKt.Color$default(179, 38, 30, 0, 8, null);
    private static final long Error50 = ColorKt.Color$default(220, 54, 46, 0, 8, null);
    private static final long Error60 = ColorKt.Color$default(228, 105, 98, 0, 8, null);
    private static final long Error70 = ColorKt.Color$default(236, 146, 142, 0, 8, null);
    private static final long Error80 = ColorKt.Color$default(242, 184, 181, 0, 8, null);
    private static final long Error90 = ColorKt.Color$default(249, 222, 220, 0, 8, null);
    private static final long Error95 = ColorKt.Color$default(252, 238, 238, 0, 8, null);
    private static final long Error99 = ColorKt.Color$default(255, 251, 249, 0, 8, null);
    private static final long Neutral0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Neutral10 = ColorKt.Color$default(29, 27, 32, 0, 8, null);
    private static final long Neutral100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long Neutral12 = ColorKt.Color$default(33, 31, 38, 0, 8, null);
    private static final long Neutral17 = ColorKt.Color$default(43, 41, 48, 0, 8, null);
    private static final long Neutral20 = ColorKt.Color$default(50, 47, 53, 0, 8, null);
    private static final long Neutral22 = ColorKt.Color$default(54, 52, 59, 0, 8, null);
    private static final long Neutral24 = ColorKt.Color$default(59, 56, 62, 0, 8, null);
    private static final long Neutral30 = ColorKt.Color$default(72, 70, 76, 0, 8, null);
    private static final long Neutral4 = ColorKt.Color$default(15, 13, 19, 0, 8, null);
    private static final long Neutral40 = ColorKt.Color$default(96, 93, 100, 0, 8, null);
    private static final long Neutral50 = ColorKt.Color$default(121, 118, 125, 0, 8, null);
    private static final long Neutral6 = ColorKt.Color$default(20, 18, 24, 0, 8, null);
    private static final long Neutral60 = ColorKt.Color$default(147, 143, 150, 0, 8, null);
    private static final long Neutral70 = ColorKt.Color$default(174, 169, 177, 0, 8, null);
    private static final long Neutral80 = ColorKt.Color$default(ComposerKt.compositionLocalMapKey, 197, 205, 0, 8, null);
    private static final long Neutral87 = ColorKt.Color$default(222, 216, 225, 0, 8, null);
    private static final long Neutral90 = ColorKt.Color$default(230, BERTags.FLAGS, 233, 0, 8, null);
    private static final long Neutral92 = ColorKt.Color$default(236, 230, 240, 0, 8, null);
    private static final long Neutral94 = ColorKt.Color$default(243, 237, 247, 0, 8, null);
    private static final long Neutral95 = ColorKt.Color$default(245, 239, 247, 0, 8, null);
    private static final long Neutral96 = ColorKt.Color$default(247, 242, ProgressIndicatorKt.FirstLineTailDelay, 0, 8, null);
    private static final long Neutral98 = ColorKt.Color$default(254, 247, 255, 0, 8, null);
    private static final long Neutral99 = ColorKt.Color$default(255, 251, 255, 0, 8, null);
    private static final long NeutralVariant0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long NeutralVariant10 = ColorKt.Color$default(29, 26, 34, 0, 8, null);
    private static final long NeutralVariant100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long NeutralVariant20 = ColorKt.Color$default(50, 47, 55, 0, 8, null);
    private static final long NeutralVariant30 = ColorKt.Color$default(73, 69, 79, 0, 8, null);
    private static final long NeutralVariant40 = ColorKt.Color$default(96, 93, LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY, 0, 8, null);
    private static final long NeutralVariant50 = ColorKt.Color$default(121, 116, 126, 0, 8, null);
    private static final long NeutralVariant60 = ColorKt.Color$default(147, 143, 153, 0, 8, null);
    private static final long NeutralVariant70 = ColorKt.Color$default(174, 169, 180, 0, 8, null);
    private static final long NeutralVariant80 = ColorKt.Color$default(ComposerKt.compositionLocalMapKey, 196, 208, 0, 8, null);
    private static final long NeutralVariant90 = ColorKt.Color$default(231, BERTags.FLAGS, 236, 0, 8, null);
    private static final long NeutralVariant95 = ColorKt.Color$default(245, 238, ProgressIndicatorKt.FirstLineTailDelay, 0, 8, null);
    private static final long NeutralVariant99 = ColorKt.Color$default(255, 251, 254, 0, 8, null);
    private static final long Primary0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Primary10 = ColorKt.Color$default(33, 0, 93, 0, 8, null);
    private static final long Primary100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long Primary20 = ColorKt.Color$default(56, 30, 114, 0, 8, null);
    private static final long Primary30 = ColorKt.Color$default(79, 55, 139, 0, 8, null);
    private static final long Primary40 = ColorKt.Color$default(103, 80, 164, 0, 8, null);
    private static final long Primary50 = ColorKt.Color$default(127, 103, 190, 0, 8, null);
    private static final long Primary60 = ColorKt.Color$default(154, 130, 219, 0, 8, null);
    private static final long Primary70 = ColorKt.Color$default(182, 157, 248, 0, 8, null);
    private static final long Primary80 = ColorKt.Color$default(208, 188, 255, 0, 8, null);
    private static final long Primary90 = ColorKt.Color$default(234, 221, 255, 0, 8, null);
    private static final long Primary95 = ColorKt.Color$default(246, 237, 255, 0, 8, null);
    private static final long Primary99 = ColorKt.Color$default(255, 251, 254, 0, 8, null);
    private static final long Secondary0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Secondary10 = ColorKt.Color$default(29, 25, 43, 0, 8, null);
    private static final long Secondary100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long Secondary20 = ColorKt.Color$default(51, 45, 65, 0, 8, null);
    private static final long Secondary30 = ColorKt.Color$default(74, 68, 88, 0, 8, null);
    private static final long Secondary40 = ColorKt.Color$default(98, 91, 113, 0, 8, null);
    private static final long Secondary50 = ColorKt.Color$default(122, 114, 137, 0, 8, null);
    private static final long Secondary60 = ColorKt.Color$default(149, 141, 165, 0, 8, null);
    private static final long Secondary70 = ColorKt.Color$default(176, 167, BERTags.PRIVATE, 0, 8, null);
    private static final long Secondary80 = ColorKt.Color$default(ComposerKt.providerMapsKey, 194, 220, 0, 8, null);
    private static final long Secondary90 = ColorKt.Color$default(232, 222, 248, 0, 8, null);
    private static final long Secondary95 = ColorKt.Color$default(246, 237, 255, 0, 8, null);
    private static final long Secondary99 = ColorKt.Color$default(255, 251, 254, 0, 8, null);
    private static final long Tertiary0 = ColorKt.Color$default(0, 0, 0, 0, 8, null);
    private static final long Tertiary10 = ColorKt.Color$default(49, 17, 29, 0, 8, null);
    private static final long Tertiary100 = ColorKt.Color$default(255, 255, 255, 0, 8, null);
    private static final long Tertiary20 = ColorKt.Color$default(73, 37, 50, 0, 8, null);
    private static final long Tertiary30 = ColorKt.Color$default(99, 59, 72, 0, 8, null);
    private static final long Tertiary40 = ColorKt.Color$default(125, 82, 96, 0, 8, null);
    private static final long Tertiary50 = ColorKt.Color$default(152, 105, 119, 0, 8, null);
    private static final long Tertiary60 = ColorKt.Color$default(181, 131, 146, 0, 8, null);
    private static final long Tertiary70 = ColorKt.Color$default(210, 157, 172, 0, 8, null);
    private static final long Tertiary80 = ColorKt.Color$default(239, 184, ComposerKt.invocationKey, 0, 8, null);
    private static final long Tertiary90 = ColorKt.Color$default(255, 216, 228, 0, 8, null);
    private static final long Tertiary95 = ColorKt.Color$default(255, 236, 241, 0, 8, null);
    private static final long Tertiary99 = ColorKt.Color$default(255, 251, ProgressIndicatorKt.FirstLineTailDelay, 0, 8, null);
    private static final long White = ColorKt.Color$default(255, 255, 255, 0, 8, null);

    private PaletteTokens() {
    }

    /* JADX INFO: renamed from: getBlack-0d7_KjU, reason: not valid java name */
    public final long m1987getBlack0d7_KjU() {
        return Black;
    }

    /* JADX INFO: renamed from: getError0-0d7_KjU, reason: not valid java name */
    public final long m1988getError00d7_KjU() {
        return Error0;
    }

    /* JADX INFO: renamed from: getError10-0d7_KjU, reason: not valid java name */
    public final long m1989getError100d7_KjU() {
        return Error10;
    }

    /* JADX INFO: renamed from: getError100-0d7_KjU, reason: not valid java name */
    public final long m1990getError1000d7_KjU() {
        return Error100;
    }

    /* JADX INFO: renamed from: getError20-0d7_KjU, reason: not valid java name */
    public final long m1991getError200d7_KjU() {
        return Error20;
    }

    /* JADX INFO: renamed from: getError30-0d7_KjU, reason: not valid java name */
    public final long m1992getError300d7_KjU() {
        return Error30;
    }

    /* JADX INFO: renamed from: getError40-0d7_KjU, reason: not valid java name */
    public final long m1993getError400d7_KjU() {
        return Error40;
    }

    /* JADX INFO: renamed from: getError50-0d7_KjU, reason: not valid java name */
    public final long m1994getError500d7_KjU() {
        return Error50;
    }

    /* JADX INFO: renamed from: getError60-0d7_KjU, reason: not valid java name */
    public final long m1995getError600d7_KjU() {
        return Error60;
    }

    /* JADX INFO: renamed from: getError70-0d7_KjU, reason: not valid java name */
    public final long m1996getError700d7_KjU() {
        return Error70;
    }

    /* JADX INFO: renamed from: getError80-0d7_KjU, reason: not valid java name */
    public final long m1997getError800d7_KjU() {
        return Error80;
    }

    /* JADX INFO: renamed from: getError90-0d7_KjU, reason: not valid java name */
    public final long m1998getError900d7_KjU() {
        return Error90;
    }

    /* JADX INFO: renamed from: getError95-0d7_KjU, reason: not valid java name */
    public final long m1999getError950d7_KjU() {
        return Error95;
    }

    /* JADX INFO: renamed from: getError99-0d7_KjU, reason: not valid java name */
    public final long m2000getError990d7_KjU() {
        return Error99;
    }

    /* JADX INFO: renamed from: getNeutral0-0d7_KjU, reason: not valid java name */
    public final long m2001getNeutral00d7_KjU() {
        return Neutral0;
    }

    /* JADX INFO: renamed from: getNeutral10-0d7_KjU, reason: not valid java name */
    public final long m2002getNeutral100d7_KjU() {
        return Neutral10;
    }

    /* JADX INFO: renamed from: getNeutral100-0d7_KjU, reason: not valid java name */
    public final long m2003getNeutral1000d7_KjU() {
        return Neutral100;
    }

    /* JADX INFO: renamed from: getNeutral12-0d7_KjU, reason: not valid java name */
    public final long m2004getNeutral120d7_KjU() {
        return Neutral12;
    }

    /* JADX INFO: renamed from: getNeutral17-0d7_KjU, reason: not valid java name */
    public final long m2005getNeutral170d7_KjU() {
        return Neutral17;
    }

    /* JADX INFO: renamed from: getNeutral20-0d7_KjU, reason: not valid java name */
    public final long m2006getNeutral200d7_KjU() {
        return Neutral20;
    }

    /* JADX INFO: renamed from: getNeutral22-0d7_KjU, reason: not valid java name */
    public final long m2007getNeutral220d7_KjU() {
        return Neutral22;
    }

    /* JADX INFO: renamed from: getNeutral24-0d7_KjU, reason: not valid java name */
    public final long m2008getNeutral240d7_KjU() {
        return Neutral24;
    }

    /* JADX INFO: renamed from: getNeutral30-0d7_KjU, reason: not valid java name */
    public final long m2009getNeutral300d7_KjU() {
        return Neutral30;
    }

    /* JADX INFO: renamed from: getNeutral4-0d7_KjU, reason: not valid java name */
    public final long m2010getNeutral40d7_KjU() {
        return Neutral4;
    }

    /* JADX INFO: renamed from: getNeutral40-0d7_KjU, reason: not valid java name */
    public final long m2011getNeutral400d7_KjU() {
        return Neutral40;
    }

    /* JADX INFO: renamed from: getNeutral50-0d7_KjU, reason: not valid java name */
    public final long m2012getNeutral500d7_KjU() {
        return Neutral50;
    }

    /* JADX INFO: renamed from: getNeutral6-0d7_KjU, reason: not valid java name */
    public final long m2013getNeutral60d7_KjU() {
        return Neutral6;
    }

    /* JADX INFO: renamed from: getNeutral60-0d7_KjU, reason: not valid java name */
    public final long m2014getNeutral600d7_KjU() {
        return Neutral60;
    }

    /* JADX INFO: renamed from: getNeutral70-0d7_KjU, reason: not valid java name */
    public final long m2015getNeutral700d7_KjU() {
        return Neutral70;
    }

    /* JADX INFO: renamed from: getNeutral80-0d7_KjU, reason: not valid java name */
    public final long m2016getNeutral800d7_KjU() {
        return Neutral80;
    }

    /* JADX INFO: renamed from: getNeutral87-0d7_KjU, reason: not valid java name */
    public final long m2017getNeutral870d7_KjU() {
        return Neutral87;
    }

    /* JADX INFO: renamed from: getNeutral90-0d7_KjU, reason: not valid java name */
    public final long m2018getNeutral900d7_KjU() {
        return Neutral90;
    }

    /* JADX INFO: renamed from: getNeutral92-0d7_KjU, reason: not valid java name */
    public final long m2019getNeutral920d7_KjU() {
        return Neutral92;
    }

    /* JADX INFO: renamed from: getNeutral94-0d7_KjU, reason: not valid java name */
    public final long m2020getNeutral940d7_KjU() {
        return Neutral94;
    }

    /* JADX INFO: renamed from: getNeutral95-0d7_KjU, reason: not valid java name */
    public final long m2021getNeutral950d7_KjU() {
        return Neutral95;
    }

    /* JADX INFO: renamed from: getNeutral96-0d7_KjU, reason: not valid java name */
    public final long m2022getNeutral960d7_KjU() {
        return Neutral96;
    }

    /* JADX INFO: renamed from: getNeutral98-0d7_KjU, reason: not valid java name */
    public final long m2023getNeutral980d7_KjU() {
        return Neutral98;
    }

    /* JADX INFO: renamed from: getNeutral99-0d7_KjU, reason: not valid java name */
    public final long m2024getNeutral990d7_KjU() {
        return Neutral99;
    }

    /* JADX INFO: renamed from: getNeutralVariant0-0d7_KjU, reason: not valid java name */
    public final long m2025getNeutralVariant00d7_KjU() {
        return NeutralVariant0;
    }

    /* JADX INFO: renamed from: getNeutralVariant10-0d7_KjU, reason: not valid java name */
    public final long m2026getNeutralVariant100d7_KjU() {
        return NeutralVariant10;
    }

    /* JADX INFO: renamed from: getNeutralVariant100-0d7_KjU, reason: not valid java name */
    public final long m2027getNeutralVariant1000d7_KjU() {
        return NeutralVariant100;
    }

    /* JADX INFO: renamed from: getNeutralVariant20-0d7_KjU, reason: not valid java name */
    public final long m2028getNeutralVariant200d7_KjU() {
        return NeutralVariant20;
    }

    /* JADX INFO: renamed from: getNeutralVariant30-0d7_KjU, reason: not valid java name */
    public final long m2029getNeutralVariant300d7_KjU() {
        return NeutralVariant30;
    }

    /* JADX INFO: renamed from: getNeutralVariant40-0d7_KjU, reason: not valid java name */
    public final long m2030getNeutralVariant400d7_KjU() {
        return NeutralVariant40;
    }

    /* JADX INFO: renamed from: getNeutralVariant50-0d7_KjU, reason: not valid java name */
    public final long m2031getNeutralVariant500d7_KjU() {
        return NeutralVariant50;
    }

    /* JADX INFO: renamed from: getNeutralVariant60-0d7_KjU, reason: not valid java name */
    public final long m2032getNeutralVariant600d7_KjU() {
        return NeutralVariant60;
    }

    /* JADX INFO: renamed from: getNeutralVariant70-0d7_KjU, reason: not valid java name */
    public final long m2033getNeutralVariant700d7_KjU() {
        return NeutralVariant70;
    }

    /* JADX INFO: renamed from: getNeutralVariant80-0d7_KjU, reason: not valid java name */
    public final long m2034getNeutralVariant800d7_KjU() {
        return NeutralVariant80;
    }

    /* JADX INFO: renamed from: getNeutralVariant90-0d7_KjU, reason: not valid java name */
    public final long m2035getNeutralVariant900d7_KjU() {
        return NeutralVariant90;
    }

    /* JADX INFO: renamed from: getNeutralVariant95-0d7_KjU, reason: not valid java name */
    public final long m2036getNeutralVariant950d7_KjU() {
        return NeutralVariant95;
    }

    /* JADX INFO: renamed from: getNeutralVariant99-0d7_KjU, reason: not valid java name */
    public final long m2037getNeutralVariant990d7_KjU() {
        return NeutralVariant99;
    }

    /* JADX INFO: renamed from: getPrimary0-0d7_KjU, reason: not valid java name */
    public final long m2038getPrimary00d7_KjU() {
        return Primary0;
    }

    /* JADX INFO: renamed from: getPrimary10-0d7_KjU, reason: not valid java name */
    public final long m2039getPrimary100d7_KjU() {
        return Primary10;
    }

    /* JADX INFO: renamed from: getPrimary100-0d7_KjU, reason: not valid java name */
    public final long m2040getPrimary1000d7_KjU() {
        return Primary100;
    }

    /* JADX INFO: renamed from: getPrimary20-0d7_KjU, reason: not valid java name */
    public final long m2041getPrimary200d7_KjU() {
        return Primary20;
    }

    /* JADX INFO: renamed from: getPrimary30-0d7_KjU, reason: not valid java name */
    public final long m2042getPrimary300d7_KjU() {
        return Primary30;
    }

    /* JADX INFO: renamed from: getPrimary40-0d7_KjU, reason: not valid java name */
    public final long m2043getPrimary400d7_KjU() {
        return Primary40;
    }

    /* JADX INFO: renamed from: getPrimary50-0d7_KjU, reason: not valid java name */
    public final long m2044getPrimary500d7_KjU() {
        return Primary50;
    }

    /* JADX INFO: renamed from: getPrimary60-0d7_KjU, reason: not valid java name */
    public final long m2045getPrimary600d7_KjU() {
        return Primary60;
    }

    /* JADX INFO: renamed from: getPrimary70-0d7_KjU, reason: not valid java name */
    public final long m2046getPrimary700d7_KjU() {
        return Primary70;
    }

    /* JADX INFO: renamed from: getPrimary80-0d7_KjU, reason: not valid java name */
    public final long m2047getPrimary800d7_KjU() {
        return Primary80;
    }

    /* JADX INFO: renamed from: getPrimary90-0d7_KjU, reason: not valid java name */
    public final long m2048getPrimary900d7_KjU() {
        return Primary90;
    }

    /* JADX INFO: renamed from: getPrimary95-0d7_KjU, reason: not valid java name */
    public final long m2049getPrimary950d7_KjU() {
        return Primary95;
    }

    /* JADX INFO: renamed from: getPrimary99-0d7_KjU, reason: not valid java name */
    public final long m2050getPrimary990d7_KjU() {
        return Primary99;
    }

    /* JADX INFO: renamed from: getSecondary0-0d7_KjU, reason: not valid java name */
    public final long m2051getSecondary00d7_KjU() {
        return Secondary0;
    }

    /* JADX INFO: renamed from: getSecondary10-0d7_KjU, reason: not valid java name */
    public final long m2052getSecondary100d7_KjU() {
        return Secondary10;
    }

    /* JADX INFO: renamed from: getSecondary100-0d7_KjU, reason: not valid java name */
    public final long m2053getSecondary1000d7_KjU() {
        return Secondary100;
    }

    /* JADX INFO: renamed from: getSecondary20-0d7_KjU, reason: not valid java name */
    public final long m2054getSecondary200d7_KjU() {
        return Secondary20;
    }

    /* JADX INFO: renamed from: getSecondary30-0d7_KjU, reason: not valid java name */
    public final long m2055getSecondary300d7_KjU() {
        return Secondary30;
    }

    /* JADX INFO: renamed from: getSecondary40-0d7_KjU, reason: not valid java name */
    public final long m2056getSecondary400d7_KjU() {
        return Secondary40;
    }

    /* JADX INFO: renamed from: getSecondary50-0d7_KjU, reason: not valid java name */
    public final long m2057getSecondary500d7_KjU() {
        return Secondary50;
    }

    /* JADX INFO: renamed from: getSecondary60-0d7_KjU, reason: not valid java name */
    public final long m2058getSecondary600d7_KjU() {
        return Secondary60;
    }

    /* JADX INFO: renamed from: getSecondary70-0d7_KjU, reason: not valid java name */
    public final long m2059getSecondary700d7_KjU() {
        return Secondary70;
    }

    /* JADX INFO: renamed from: getSecondary80-0d7_KjU, reason: not valid java name */
    public final long m2060getSecondary800d7_KjU() {
        return Secondary80;
    }

    /* JADX INFO: renamed from: getSecondary90-0d7_KjU, reason: not valid java name */
    public final long m2061getSecondary900d7_KjU() {
        return Secondary90;
    }

    /* JADX INFO: renamed from: getSecondary95-0d7_KjU, reason: not valid java name */
    public final long m2062getSecondary950d7_KjU() {
        return Secondary95;
    }

    /* JADX INFO: renamed from: getSecondary99-0d7_KjU, reason: not valid java name */
    public final long m2063getSecondary990d7_KjU() {
        return Secondary99;
    }

    /* JADX INFO: renamed from: getTertiary0-0d7_KjU, reason: not valid java name */
    public final long m2064getTertiary00d7_KjU() {
        return Tertiary0;
    }

    /* JADX INFO: renamed from: getTertiary10-0d7_KjU, reason: not valid java name */
    public final long m2065getTertiary100d7_KjU() {
        return Tertiary10;
    }

    /* JADX INFO: renamed from: getTertiary100-0d7_KjU, reason: not valid java name */
    public final long m2066getTertiary1000d7_KjU() {
        return Tertiary100;
    }

    /* JADX INFO: renamed from: getTertiary20-0d7_KjU, reason: not valid java name */
    public final long m2067getTertiary200d7_KjU() {
        return Tertiary20;
    }

    /* JADX INFO: renamed from: getTertiary30-0d7_KjU, reason: not valid java name */
    public final long m2068getTertiary300d7_KjU() {
        return Tertiary30;
    }

    /* JADX INFO: renamed from: getTertiary40-0d7_KjU, reason: not valid java name */
    public final long m2069getTertiary400d7_KjU() {
        return Tertiary40;
    }

    /* JADX INFO: renamed from: getTertiary50-0d7_KjU, reason: not valid java name */
    public final long m2070getTertiary500d7_KjU() {
        return Tertiary50;
    }

    /* JADX INFO: renamed from: getTertiary60-0d7_KjU, reason: not valid java name */
    public final long m2071getTertiary600d7_KjU() {
        return Tertiary60;
    }

    /* JADX INFO: renamed from: getTertiary70-0d7_KjU, reason: not valid java name */
    public final long m2072getTertiary700d7_KjU() {
        return Tertiary70;
    }

    /* JADX INFO: renamed from: getTertiary80-0d7_KjU, reason: not valid java name */
    public final long m2073getTertiary800d7_KjU() {
        return Tertiary80;
    }

    /* JADX INFO: renamed from: getTertiary90-0d7_KjU, reason: not valid java name */
    public final long m2074getTertiary900d7_KjU() {
        return Tertiary90;
    }

    /* JADX INFO: renamed from: getTertiary95-0d7_KjU, reason: not valid java name */
    public final long m2075getTertiary950d7_KjU() {
        return Tertiary95;
    }

    /* JADX INFO: renamed from: getTertiary99-0d7_KjU, reason: not valid java name */
    public final long m2076getTertiary990d7_KjU() {
        return Tertiary99;
    }

    /* JADX INFO: renamed from: getWhite-0d7_KjU, reason: not valid java name */
    public final long m2077getWhite0d7_KjU() {
        return White;
    }
}
