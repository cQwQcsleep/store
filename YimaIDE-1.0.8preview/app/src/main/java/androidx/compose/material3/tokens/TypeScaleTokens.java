package androidx.compose.material3.tokens;

import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0003\b¥\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\r\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u000f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0018\u0010\u000bR\u0013\u0010\u0019\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001a\u0010\u000bR\u0013\u0010\u001b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001c\u0010\u000bR\u0011\u0010\u001d\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\"\u0010\u000bR\u0013\u0010#\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b$\u0010\u000bR\u0013\u0010%\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b&\u0010\u000bR\u0011\u0010'\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0014R\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0007R\u0013\u0010+\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b,\u0010\u000bR\u0013\u0010-\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b.\u0010\u000bR\u0013\u0010/\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b0\u0010\u000bR\u0011\u00101\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0014R\u0011\u00103\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0007R\u0013\u00105\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b6\u0010\u000bR\u0013\u00107\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b8\u0010\u000bR\u0013\u00109\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b:\u0010\u000bR\u0011\u0010;\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0014R\u0011\u0010=\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0007R\u0013\u0010?\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b@\u0010\u000bR\u0013\u0010A\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bB\u0010\u000bR\u0013\u0010C\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bD\u0010\u000bR\u0011\u0010E\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0014R\u0011\u0010G\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0007R\u0013\u0010I\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bJ\u0010\u000bR\u0013\u0010K\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bL\u0010\u000bR\u0013\u0010M\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bN\u0010\u000bR\u0011\u0010O\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0014R\u0011\u0010Q\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0007R\u0013\u0010S\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bT\u0010\u000bR\u0013\u0010U\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bV\u0010\u000bR\u0013\u0010W\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bX\u0010\u000bR\u0011\u0010Y\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0014R\u0011\u0010[\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0007R\u0013\u0010]\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b^\u0010\u000bR\u0013\u0010_\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b`\u0010\u000bR\u0013\u0010a\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bb\u0010\u000bR\u0011\u0010c\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0014R\u0011\u0010e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0007R\u0013\u0010g\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bh\u0010\u000bR\u0013\u0010i\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bj\u0010\u000bR\u0013\u0010k\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bl\u0010\u000bR\u0011\u0010m\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0014R\u0011\u0010o\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0007R\u0013\u0010q\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\br\u0010\u000bR\u0013\u0010s\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bt\u0010\u000bR\u0013\u0010u\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\bv\u0010\u000bR\u0011\u0010w\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\u0014R\u0011\u0010y\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\u0007R\u0013\u0010{\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b|\u0010\u000bR\u0013\u0010}\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b~\u0010\u000bR\u0014\u0010\u007f\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0080\u0001\u0010\u000bR\u0013\u0010\u0081\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0014R\u0013\u0010\u0083\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0007R\u0015\u0010\u0085\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0086\u0001\u0010\u000bR\u0015\u0010\u0087\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0088\u0001\u0010\u000bR\u0015\u0010\u0089\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u008a\u0001\u0010\u000bR\u0013\u0010\u008b\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010\u0014R\u0013\u0010\u008d\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0007R\u0015\u0010\u008f\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0090\u0001\u0010\u000bR\u0015\u0010\u0091\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0092\u0001\u0010\u000bR\u0015\u0010\u0093\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0094\u0001\u0010\u000bR\u0013\u0010\u0095\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010\u0014R\u0013\u0010\u0097\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010\u0007R\u0015\u0010\u0099\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u009a\u0001\u0010\u000bR\u0015\u0010\u009b\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u009c\u0001\u0010\u000bR\u0015\u0010\u009d\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u009e\u0001\u0010\u000bR\u0013\u0010\u009f\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010\u0014R\u0013\u0010¡\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\u0007R\u0015\u0010£\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¤\u0001\u0010\u000bR\u0015\u0010¥\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¦\u0001\u0010\u000bR\u0015\u0010§\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¨\u0001\u0010\u000bR\u0013\u0010©\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\u0014R\u0013\u0010«\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¬\u0001\u0010\u0007R\u0015\u0010\u00ad\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b®\u0001\u0010\u000bR\u0015\u0010¯\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b°\u0001\u0010\u000bR\u0015\u0010±\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b²\u0001\u0010\u000bR\u0013\u0010³\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b´\u0001\u0010\u0014R\u0013\u0010µ\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¶\u0001\u0010\u0007R\u0015\u0010·\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¸\u0001\u0010\u000bR\u0015\u0010¹\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bº\u0001\u0010\u000bR\u0015\u0010»\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¼\u0001\u0010\u000bR\u0013\u0010½\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b¾\u0001\u0010\u0014R\u0013\u0010¿\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÀ\u0001\u0010\u0007R\u0015\u0010Á\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÂ\u0001\u0010\u000bR\u0015\u0010Ã\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÄ\u0001\u0010\u000bR\u0015\u0010Å\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÆ\u0001\u0010\u000bR\u0013\u0010Ç\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bÈ\u0001\u0010\u0014R\u0013\u0010É\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÊ\u0001\u0010\u0007R\u0015\u0010Ë\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÌ\u0001\u0010\u000bR\u0015\u0010Í\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÎ\u0001\u0010\u000bR\u0015\u0010Ï\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÐ\u0001\u0010\u000bR\u0013\u0010Ñ\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bÒ\u0001\u0010\u0014R\u0013\u0010Ó\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÔ\u0001\u0010\u0007R\u0015\u0010Õ\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÖ\u0001\u0010\u000bR\u0015\u0010×\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bØ\u0001\u0010\u000bR\u0015\u0010Ù\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bÚ\u0001\u0010\u000bR\u0013\u0010Û\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bÜ\u0001\u0010\u0014R\u0013\u0010Ý\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÞ\u0001\u0010\u0007R\u0015\u0010ß\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bà\u0001\u0010\u000bR\u0015\u0010á\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bâ\u0001\u0010\u000bR\u0015\u0010ã\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bä\u0001\u0010\u000bR\u0013\u0010å\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bæ\u0001\u0010\u0014R\u0013\u0010ç\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bè\u0001\u0010\u0007R\u0015\u0010é\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bê\u0001\u0010\u000bR\u0015\u0010ë\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bì\u0001\u0010\u000bR\u0015\u0010í\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bî\u0001\u0010\u000bR\u0013\u0010ï\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bð\u0001\u0010\u0014R\u0013\u0010ñ\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bò\u0001\u0010\u0007R\u0015\u0010ó\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bô\u0001\u0010\u000bR\u0015\u0010õ\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bö\u0001\u0010\u000bR\u0015\u0010÷\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bø\u0001\u0010\u000bR\u0013\u0010ù\u0001\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\bú\u0001\u0010\u0014R\u0013\u0010û\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bü\u0001\u0010\u0007R\u0015\u0010ý\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bþ\u0001\u0010\u000bR\u0015\u0010ÿ\u0001\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0080\u0002\u0010\u000bR\u0015\u0010\u0081\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0082\u0002\u0010\u000bR\u0013\u0010\u0083\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0002\u0010\u0014R\u0013\u0010\u0085\u0002\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0002\u0010\u0007R\u0015\u0010\u0087\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0088\u0002\u0010\u000bR\u0015\u0010\u0089\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u008a\u0002\u0010\u000bR\u0015\u0010\u008b\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u008c\u0002\u0010\u000bR\u0013\u0010\u008d\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0002\u0010\u0014R\u0013\u0010\u008f\u0002\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0090\u0002\u0010\u0007R\u0015\u0010\u0091\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0092\u0002\u0010\u000bR\u0015\u0010\u0093\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0094\u0002\u0010\u000bR\u0015\u0010\u0095\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u0096\u0002\u0010\u000bR\u0013\u0010\u0097\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0002\u0010\u0014R\u0013\u0010\u0099\u0002\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0002\u0010\u0007R\u0015\u0010\u009b\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u009c\u0002\u0010\u000bR\u0015\u0010\u009d\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b\u009e\u0002\u0010\u000bR\u0015\u0010\u009f\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b \u0002\u0010\u000bR\u0013\u0010¡\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b¢\u0002\u0010\u0014R\u0013\u0010£\u0002\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¤\u0002\u0010\u0007R\u0015\u0010¥\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¦\u0002\u0010\u000bR\u0015\u0010§\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b¨\u0002\u0010\u000bR\u0015\u0010©\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\bª\u0002\u0010\u000bR\u0013\u0010«\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b¬\u0002\u0010\u0014R\u0013\u0010\u00ad\u0002\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b®\u0002\u0010\u0007R\u0015\u0010¯\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b°\u0002\u0010\u000bR\u0015\u0010±\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b²\u0002\u0010\u000bR\u0015\u0010³\u0002\u001a\u00020\t¢\u0006\u000b\n\u0002\u0010\f\u001a\u0005\b´\u0002\u0010\u000bR\u0013\u0010µ\u0002\u001a\u00020\u0012¢\u0006\t\n\u0000\u001a\u0005\b¶\u0002\u0010\u0014¨\u0006·\u0002"}, d2 = {"Landroidx/compose/material3/tokens/TypeScaleTokens;", "", "<init>", "()V", "BodyLargeFont", "Landroidx/compose/ui/text/font/GenericFontFamily;", "getBodyLargeFont", "()Landroidx/compose/ui/text/font/GenericFontFamily;", "BodyLargeLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "getBodyLargeLineHeight-XSAIIZE", "()J", "J", "BodyLargeSize", "getBodyLargeSize-XSAIIZE", "BodyLargeTracking", "getBodyLargeTracking-XSAIIZE", "BodyLargeWeight", "Landroidx/compose/ui/text/font/FontWeight;", "getBodyLargeWeight", "()Landroidx/compose/ui/text/font/FontWeight;", "BodyMediumFont", "getBodyMediumFont", "BodyMediumLineHeight", "getBodyMediumLineHeight-XSAIIZE", "BodyMediumSize", "getBodyMediumSize-XSAIIZE", "BodyMediumTracking", "getBodyMediumTracking-XSAIIZE", "BodyMediumWeight", "getBodyMediumWeight", "BodySmallFont", "getBodySmallFont", "BodySmallLineHeight", "getBodySmallLineHeight-XSAIIZE", "BodySmallSize", "getBodySmallSize-XSAIIZE", "BodySmallTracking", "getBodySmallTracking-XSAIIZE", "BodySmallWeight", "getBodySmallWeight", "DisplayLargeFont", "getDisplayLargeFont", "DisplayLargeLineHeight", "getDisplayLargeLineHeight-XSAIIZE", "DisplayLargeSize", "getDisplayLargeSize-XSAIIZE", "DisplayLargeTracking", "getDisplayLargeTracking-XSAIIZE", "DisplayLargeWeight", "getDisplayLargeWeight", "DisplayMediumFont", "getDisplayMediumFont", "DisplayMediumLineHeight", "getDisplayMediumLineHeight-XSAIIZE", "DisplayMediumSize", "getDisplayMediumSize-XSAIIZE", "DisplayMediumTracking", "getDisplayMediumTracking-XSAIIZE", "DisplayMediumWeight", "getDisplayMediumWeight", "DisplaySmallFont", "getDisplaySmallFont", "DisplaySmallLineHeight", "getDisplaySmallLineHeight-XSAIIZE", "DisplaySmallSize", "getDisplaySmallSize-XSAIIZE", "DisplaySmallTracking", "getDisplaySmallTracking-XSAIIZE", "DisplaySmallWeight", "getDisplaySmallWeight", "HeadlineLargeFont", "getHeadlineLargeFont", "HeadlineLargeLineHeight", "getHeadlineLargeLineHeight-XSAIIZE", "HeadlineLargeSize", "getHeadlineLargeSize-XSAIIZE", "HeadlineLargeTracking", "getHeadlineLargeTracking-XSAIIZE", "HeadlineLargeWeight", "getHeadlineLargeWeight", "HeadlineMediumFont", "getHeadlineMediumFont", "HeadlineMediumLineHeight", "getHeadlineMediumLineHeight-XSAIIZE", "HeadlineMediumSize", "getHeadlineMediumSize-XSAIIZE", "HeadlineMediumTracking", "getHeadlineMediumTracking-XSAIIZE", "HeadlineMediumWeight", "getHeadlineMediumWeight", "HeadlineSmallFont", "getHeadlineSmallFont", "HeadlineSmallLineHeight", "getHeadlineSmallLineHeight-XSAIIZE", "HeadlineSmallSize", "getHeadlineSmallSize-XSAIIZE", "HeadlineSmallTracking", "getHeadlineSmallTracking-XSAIIZE", "HeadlineSmallWeight", "getHeadlineSmallWeight", "LabelLargeFont", "getLabelLargeFont", "LabelLargeLineHeight", "getLabelLargeLineHeight-XSAIIZE", "LabelLargeSize", "getLabelLargeSize-XSAIIZE", "LabelLargeTracking", "getLabelLargeTracking-XSAIIZE", "LabelLargeWeight", "getLabelLargeWeight", "LabelMediumFont", "getLabelMediumFont", "LabelMediumLineHeight", "getLabelMediumLineHeight-XSAIIZE", "LabelMediumSize", "getLabelMediumSize-XSAIIZE", "LabelMediumTracking", "getLabelMediumTracking-XSAIIZE", "LabelMediumWeight", "getLabelMediumWeight", "LabelSmallFont", "getLabelSmallFont", "LabelSmallLineHeight", "getLabelSmallLineHeight-XSAIIZE", "LabelSmallSize", "getLabelSmallSize-XSAIIZE", "LabelSmallTracking", "getLabelSmallTracking-XSAIIZE", "LabelSmallWeight", "getLabelSmallWeight", "TitleLargeFont", "getTitleLargeFont", "TitleLargeLineHeight", "getTitleLargeLineHeight-XSAIIZE", "TitleLargeSize", "getTitleLargeSize-XSAIIZE", "TitleLargeTracking", "getTitleLargeTracking-XSAIIZE", "TitleLargeWeight", "getTitleLargeWeight", "TitleMediumFont", "getTitleMediumFont", "TitleMediumLineHeight", "getTitleMediumLineHeight-XSAIIZE", "TitleMediumSize", "getTitleMediumSize-XSAIIZE", "TitleMediumTracking", "getTitleMediumTracking-XSAIIZE", "TitleMediumWeight", "getTitleMediumWeight", "TitleSmallFont", "getTitleSmallFont", "TitleSmallLineHeight", "getTitleSmallLineHeight-XSAIIZE", "TitleSmallSize", "getTitleSmallSize-XSAIIZE", "TitleSmallTracking", "getTitleSmallTracking-XSAIIZE", "TitleSmallWeight", "getTitleSmallWeight", "BodyLargeEmphasizedFont", "getBodyLargeEmphasizedFont", "BodyLargeEmphasizedLineHeight", "getBodyLargeEmphasizedLineHeight-XSAIIZE", "BodyLargeEmphasizedSize", "getBodyLargeEmphasizedSize-XSAIIZE", "BodyLargeEmphasizedTracking", "getBodyLargeEmphasizedTracking-XSAIIZE", "BodyLargeEmphasizedWeight", "getBodyLargeEmphasizedWeight", "BodyMediumEmphasizedFont", "getBodyMediumEmphasizedFont", "BodyMediumEmphasizedLineHeight", "getBodyMediumEmphasizedLineHeight-XSAIIZE", "BodyMediumEmphasizedSize", "getBodyMediumEmphasizedSize-XSAIIZE", "BodyMediumEmphasizedTracking", "getBodyMediumEmphasizedTracking-XSAIIZE", "BodyMediumEmphasizedWeight", "getBodyMediumEmphasizedWeight", "BodySmallEmphasizedFont", "getBodySmallEmphasizedFont", "BodySmallEmphasizedLineHeight", "getBodySmallEmphasizedLineHeight-XSAIIZE", "BodySmallEmphasizedSize", "getBodySmallEmphasizedSize-XSAIIZE", "BodySmallEmphasizedTracking", "getBodySmallEmphasizedTracking-XSAIIZE", "BodySmallEmphasizedWeight", "getBodySmallEmphasizedWeight", "DisplayLargeEmphasizedFont", "getDisplayLargeEmphasizedFont", "DisplayLargeEmphasizedLineHeight", "getDisplayLargeEmphasizedLineHeight-XSAIIZE", "DisplayLargeEmphasizedSize", "getDisplayLargeEmphasizedSize-XSAIIZE", "DisplayLargeEmphasizedTracking", "getDisplayLargeEmphasizedTracking-XSAIIZE", "DisplayLargeEmphasizedWeight", "getDisplayLargeEmphasizedWeight", "DisplayMediumEmphasizedFont", "getDisplayMediumEmphasizedFont", "DisplayMediumEmphasizedLineHeight", "getDisplayMediumEmphasizedLineHeight-XSAIIZE", "DisplayMediumEmphasizedSize", "getDisplayMediumEmphasizedSize-XSAIIZE", "DisplayMediumEmphasizedTracking", "getDisplayMediumEmphasizedTracking-XSAIIZE", "DisplayMediumEmphasizedWeight", "getDisplayMediumEmphasizedWeight", "DisplaySmallEmphasizedFont", "getDisplaySmallEmphasizedFont", "DisplaySmallEmphasizedLineHeight", "getDisplaySmallEmphasizedLineHeight-XSAIIZE", "DisplaySmallEmphasizedSize", "getDisplaySmallEmphasizedSize-XSAIIZE", "DisplaySmallEmphasizedTracking", "getDisplaySmallEmphasizedTracking-XSAIIZE", "DisplaySmallEmphasizedWeight", "getDisplaySmallEmphasizedWeight", "HeadlineLargeEmphasizedFont", "getHeadlineLargeEmphasizedFont", "HeadlineLargeEmphasizedLineHeight", "getHeadlineLargeEmphasizedLineHeight-XSAIIZE", "HeadlineLargeEmphasizedSize", "getHeadlineLargeEmphasizedSize-XSAIIZE", "HeadlineLargeEmphasizedTracking", "getHeadlineLargeEmphasizedTracking-XSAIIZE", "HeadlineLargeEmphasizedWeight", "getHeadlineLargeEmphasizedWeight", "HeadlineMediumEmphasizedFont", "getHeadlineMediumEmphasizedFont", "HeadlineMediumEmphasizedLineHeight", "getHeadlineMediumEmphasizedLineHeight-XSAIIZE", "HeadlineMediumEmphasizedSize", "getHeadlineMediumEmphasizedSize-XSAIIZE", "HeadlineMediumEmphasizedTracking", "getHeadlineMediumEmphasizedTracking-XSAIIZE", "HeadlineMediumEmphasizedWeight", "getHeadlineMediumEmphasizedWeight", "HeadlineSmallEmphasizedFont", "getHeadlineSmallEmphasizedFont", "HeadlineSmallEmphasizedLineHeight", "getHeadlineSmallEmphasizedLineHeight-XSAIIZE", "HeadlineSmallEmphasizedSize", "getHeadlineSmallEmphasizedSize-XSAIIZE", "HeadlineSmallEmphasizedTracking", "getHeadlineSmallEmphasizedTracking-XSAIIZE", "HeadlineSmallEmphasizedWeight", "getHeadlineSmallEmphasizedWeight", "LabelLargeEmphasizedFont", "getLabelLargeEmphasizedFont", "LabelLargeEmphasizedLineHeight", "getLabelLargeEmphasizedLineHeight-XSAIIZE", "LabelLargeEmphasizedSize", "getLabelLargeEmphasizedSize-XSAIIZE", "LabelLargeEmphasizedTracking", "getLabelLargeEmphasizedTracking-XSAIIZE", "LabelLargeEmphasizedWeight", "getLabelLargeEmphasizedWeight", "LabelMediumEmphasizedFont", "getLabelMediumEmphasizedFont", "LabelMediumEmphasizedLineHeight", "getLabelMediumEmphasizedLineHeight-XSAIIZE", "LabelMediumEmphasizedSize", "getLabelMediumEmphasizedSize-XSAIIZE", "LabelMediumEmphasizedTracking", "getLabelMediumEmphasizedTracking-XSAIIZE", "LabelMediumEmphasizedWeight", "getLabelMediumEmphasizedWeight", "LabelSmallEmphasizedFont", "getLabelSmallEmphasizedFont", "LabelSmallEmphasizedLineHeight", "getLabelSmallEmphasizedLineHeight-XSAIIZE", "LabelSmallEmphasizedSize", "getLabelSmallEmphasizedSize-XSAIIZE", "LabelSmallEmphasizedTracking", "getLabelSmallEmphasizedTracking-XSAIIZE", "LabelSmallEmphasizedWeight", "getLabelSmallEmphasizedWeight", "TitleLargeEmphasizedFont", "getTitleLargeEmphasizedFont", "TitleLargeEmphasizedLineHeight", "getTitleLargeEmphasizedLineHeight-XSAIIZE", "TitleLargeEmphasizedSize", "getTitleLargeEmphasizedSize-XSAIIZE", "TitleLargeEmphasizedTracking", "getTitleLargeEmphasizedTracking-XSAIIZE", "TitleLargeEmphasizedWeight", "getTitleLargeEmphasizedWeight", "TitleMediumEmphasizedFont", "getTitleMediumEmphasizedFont", "TitleMediumEmphasizedLineHeight", "getTitleMediumEmphasizedLineHeight-XSAIIZE", "TitleMediumEmphasizedSize", "getTitleMediumEmphasizedSize-XSAIIZE", "TitleMediumEmphasizedTracking", "getTitleMediumEmphasizedTracking-XSAIIZE", "TitleMediumEmphasizedWeight", "getTitleMediumEmphasizedWeight", "TitleSmallEmphasizedFont", "getTitleSmallEmphasizedFont", "TitleSmallEmphasizedLineHeight", "getTitleSmallEmphasizedLineHeight-XSAIIZE", "TitleSmallEmphasizedSize", "getTitleSmallEmphasizedSize-XSAIIZE", "TitleSmallEmphasizedTracking", "getTitleSmallEmphasizedTracking-XSAIIZE", "TitleSmallEmphasizedWeight", "getTitleSmallEmphasizedWeight", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TypeScaleTokens {
    public static final int $stable = 0;
    private static final GenericFontFamily BodyLargeEmphasizedFont;
    private static final long BodyLargeEmphasizedLineHeight;
    private static final long BodyLargeEmphasizedSize;
    private static final long BodyLargeEmphasizedTracking;
    private static final FontWeight BodyLargeEmphasizedWeight;
    private static final GenericFontFamily BodyLargeFont;
    private static final long BodyLargeLineHeight;
    private static final long BodyLargeSize;
    private static final long BodyLargeTracking;
    private static final FontWeight BodyLargeWeight;
    private static final GenericFontFamily BodyMediumEmphasizedFont;
    private static final long BodyMediumEmphasizedLineHeight;
    private static final long BodyMediumEmphasizedSize;
    private static final long BodyMediumEmphasizedTracking;
    private static final FontWeight BodyMediumEmphasizedWeight;
    private static final GenericFontFamily BodyMediumFont;
    private static final long BodyMediumLineHeight;
    private static final long BodyMediumSize;
    private static final long BodyMediumTracking;
    private static final FontWeight BodyMediumWeight;
    private static final GenericFontFamily BodySmallEmphasizedFont;
    private static final long BodySmallEmphasizedLineHeight;
    private static final long BodySmallEmphasizedSize;
    private static final long BodySmallEmphasizedTracking;
    private static final FontWeight BodySmallEmphasizedWeight;
    private static final GenericFontFamily BodySmallFont;
    private static final long BodySmallLineHeight;
    private static final long BodySmallSize;
    private static final long BodySmallTracking;
    private static final FontWeight BodySmallWeight;
    private static final GenericFontFamily DisplayLargeEmphasizedFont;
    private static final long DisplayLargeEmphasizedLineHeight;
    private static final long DisplayLargeEmphasizedSize;
    private static final long DisplayLargeEmphasizedTracking;
    private static final FontWeight DisplayLargeEmphasizedWeight;
    private static final GenericFontFamily DisplayLargeFont;
    private static final long DisplayLargeLineHeight;
    private static final long DisplayLargeSize;
    private static final long DisplayLargeTracking;
    private static final FontWeight DisplayLargeWeight;
    private static final GenericFontFamily DisplayMediumEmphasizedFont;
    private static final long DisplayMediumEmphasizedLineHeight;
    private static final long DisplayMediumEmphasizedSize;
    private static final long DisplayMediumEmphasizedTracking;
    private static final FontWeight DisplayMediumEmphasizedWeight;
    private static final GenericFontFamily DisplayMediumFont;
    private static final long DisplayMediumLineHeight;
    private static final long DisplayMediumSize;
    private static final long DisplayMediumTracking;
    private static final FontWeight DisplayMediumWeight;
    private static final GenericFontFamily DisplaySmallEmphasizedFont;
    private static final long DisplaySmallEmphasizedLineHeight;
    private static final long DisplaySmallEmphasizedSize;
    private static final long DisplaySmallEmphasizedTracking;
    private static final FontWeight DisplaySmallEmphasizedWeight;
    private static final GenericFontFamily DisplaySmallFont;
    private static final long DisplaySmallLineHeight;
    private static final long DisplaySmallSize;
    private static final long DisplaySmallTracking;
    private static final FontWeight DisplaySmallWeight;
    private static final GenericFontFamily HeadlineLargeEmphasizedFont;
    private static final long HeadlineLargeEmphasizedLineHeight;
    private static final long HeadlineLargeEmphasizedSize;
    private static final long HeadlineLargeEmphasizedTracking;
    private static final FontWeight HeadlineLargeEmphasizedWeight;
    private static final GenericFontFamily HeadlineLargeFont;
    private static final long HeadlineLargeLineHeight;
    private static final long HeadlineLargeSize;
    private static final long HeadlineLargeTracking;
    private static final FontWeight HeadlineLargeWeight;
    private static final GenericFontFamily HeadlineMediumEmphasizedFont;
    private static final long HeadlineMediumEmphasizedLineHeight;
    private static final long HeadlineMediumEmphasizedSize;
    private static final long HeadlineMediumEmphasizedTracking;
    private static final FontWeight HeadlineMediumEmphasizedWeight;
    private static final GenericFontFamily HeadlineMediumFont;
    private static final long HeadlineMediumLineHeight;
    private static final long HeadlineMediumSize;
    private static final long HeadlineMediumTracking;
    private static final FontWeight HeadlineMediumWeight;
    private static final GenericFontFamily HeadlineSmallEmphasizedFont;
    private static final long HeadlineSmallEmphasizedLineHeight;
    private static final long HeadlineSmallEmphasizedSize;
    private static final long HeadlineSmallEmphasizedTracking;
    private static final FontWeight HeadlineSmallEmphasizedWeight;
    private static final GenericFontFamily HeadlineSmallFont;
    private static final long HeadlineSmallLineHeight;
    private static final long HeadlineSmallSize;
    private static final long HeadlineSmallTracking;
    private static final FontWeight HeadlineSmallWeight;
    public static final TypeScaleTokens INSTANCE = new TypeScaleTokens();
    private static final GenericFontFamily LabelLargeEmphasizedFont;
    private static final long LabelLargeEmphasizedLineHeight;
    private static final long LabelLargeEmphasizedSize;
    private static final long LabelLargeEmphasizedTracking;
    private static final FontWeight LabelLargeEmphasizedWeight;
    private static final GenericFontFamily LabelLargeFont;
    private static final long LabelLargeLineHeight;
    private static final long LabelLargeSize;
    private static final long LabelLargeTracking;
    private static final FontWeight LabelLargeWeight;
    private static final GenericFontFamily LabelMediumEmphasizedFont;
    private static final long LabelMediumEmphasizedLineHeight;
    private static final long LabelMediumEmphasizedSize;
    private static final long LabelMediumEmphasizedTracking;
    private static final FontWeight LabelMediumEmphasizedWeight;
    private static final GenericFontFamily LabelMediumFont;
    private static final long LabelMediumLineHeight;
    private static final long LabelMediumSize;
    private static final long LabelMediumTracking;
    private static final FontWeight LabelMediumWeight;
    private static final GenericFontFamily LabelSmallEmphasizedFont;
    private static final long LabelSmallEmphasizedLineHeight;
    private static final long LabelSmallEmphasizedSize;
    private static final long LabelSmallEmphasizedTracking;
    private static final FontWeight LabelSmallEmphasizedWeight;
    private static final GenericFontFamily LabelSmallFont;
    private static final long LabelSmallLineHeight;
    private static final long LabelSmallSize;
    private static final long LabelSmallTracking;
    private static final FontWeight LabelSmallWeight;
    private static final GenericFontFamily TitleLargeEmphasizedFont;
    private static final long TitleLargeEmphasizedLineHeight;
    private static final long TitleLargeEmphasizedSize;
    private static final long TitleLargeEmphasizedTracking;
    private static final FontWeight TitleLargeEmphasizedWeight;
    private static final GenericFontFamily TitleLargeFont;
    private static final long TitleLargeLineHeight;
    private static final long TitleLargeSize;
    private static final long TitleLargeTracking;
    private static final FontWeight TitleLargeWeight;
    private static final GenericFontFamily TitleMediumEmphasizedFont;
    private static final long TitleMediumEmphasizedLineHeight;
    private static final long TitleMediumEmphasizedSize;
    private static final long TitleMediumEmphasizedTracking;
    private static final FontWeight TitleMediumEmphasizedWeight;
    private static final GenericFontFamily TitleMediumFont;
    private static final long TitleMediumLineHeight;
    private static final long TitleMediumSize;
    private static final long TitleMediumTracking;
    private static final FontWeight TitleMediumWeight;
    private static final GenericFontFamily TitleSmallEmphasizedFont;
    private static final long TitleSmallEmphasizedLineHeight;
    private static final long TitleSmallEmphasizedSize;
    private static final long TitleSmallEmphasizedTracking;
    private static final FontWeight TitleSmallEmphasizedWeight;
    private static final GenericFontFamily TitleSmallFont;
    private static final long TitleSmallLineHeight;
    private static final long TitleSmallSize;
    private static final long TitleSmallTracking;
    private static final FontWeight TitleSmallWeight;

    static {
        TypefaceTokens typefaceTokens = TypefaceTokens.INSTANCE;
        BodyLargeFont = typefaceTokens.getPlain();
        BodyLargeLineHeight = TextUnitKt.getSp(24.0d);
        BodyLargeSize = TextUnitKt.getSp(16);
        BodyLargeTracking = TextUnitKt.getSp(0.5d);
        BodyLargeWeight = typefaceTokens.getWeightRegular();
        BodyMediumFont = typefaceTokens.getPlain();
        BodyMediumLineHeight = TextUnitKt.getSp(20.0d);
        BodyMediumSize = TextUnitKt.getSp(14);
        BodyMediumTracking = TextUnitKt.getSp(0.2d);
        BodyMediumWeight = typefaceTokens.getWeightRegular();
        BodySmallFont = typefaceTokens.getPlain();
        BodySmallLineHeight = TextUnitKt.getSp(16.0d);
        BodySmallSize = TextUnitKt.getSp(12);
        BodySmallTracking = TextUnitKt.getSp(0.4d);
        BodySmallWeight = typefaceTokens.getWeightRegular();
        DisplayLargeFont = typefaceTokens.getBrand();
        DisplayLargeLineHeight = TextUnitKt.getSp(64.0d);
        DisplayLargeSize = TextUnitKt.getSp(57);
        long sp = TextUnitKt.getSp(0.2d);
        TextUnitKt.m6229checkArithmeticR2X_6o(sp);
        DisplayLargeTracking = TextUnitKt.pack(TextUnit.m6214getRawTypeimpl(sp), -TextUnit.m6216getValueimpl(sp));
        DisplayLargeWeight = typefaceTokens.getWeightRegular();
        DisplayMediumFont = typefaceTokens.getBrand();
        DisplayMediumLineHeight = TextUnitKt.getSp(52.0d);
        DisplayMediumSize = TextUnitKt.getSp(45);
        DisplayMediumTracking = TextUnitKt.getSp(0.0d);
        DisplayMediumWeight = typefaceTokens.getWeightRegular();
        DisplaySmallFont = typefaceTokens.getBrand();
        DisplaySmallLineHeight = TextUnitKt.getSp(44.0d);
        DisplaySmallSize = TextUnitKt.getSp(36);
        DisplaySmallTracking = TextUnitKt.getSp(0.0d);
        DisplaySmallWeight = typefaceTokens.getWeightRegular();
        HeadlineLargeFont = typefaceTokens.getBrand();
        HeadlineLargeLineHeight = TextUnitKt.getSp(40.0d);
        HeadlineLargeSize = TextUnitKt.getSp(32);
        HeadlineLargeTracking = TextUnitKt.getSp(0.0d);
        HeadlineLargeWeight = typefaceTokens.getWeightRegular();
        HeadlineMediumFont = typefaceTokens.getBrand();
        HeadlineMediumLineHeight = TextUnitKt.getSp(36.0d);
        HeadlineMediumSize = TextUnitKt.getSp(28);
        HeadlineMediumTracking = TextUnitKt.getSp(0.0d);
        HeadlineMediumWeight = typefaceTokens.getWeightRegular();
        HeadlineSmallFont = typefaceTokens.getBrand();
        HeadlineSmallLineHeight = TextUnitKt.getSp(32.0d);
        HeadlineSmallSize = TextUnitKt.getSp(24);
        HeadlineSmallTracking = TextUnitKt.getSp(0.0d);
        HeadlineSmallWeight = typefaceTokens.getWeightRegular();
        LabelLargeFont = typefaceTokens.getPlain();
        LabelLargeLineHeight = TextUnitKt.getSp(20.0d);
        LabelLargeSize = TextUnitKt.getSp(14);
        LabelLargeTracking = TextUnitKt.getSp(0.1d);
        LabelLargeWeight = typefaceTokens.getWeightMedium();
        LabelMediumFont = typefaceTokens.getPlain();
        LabelMediumLineHeight = TextUnitKt.getSp(16.0d);
        LabelMediumSize = TextUnitKt.getSp(12);
        LabelMediumTracking = TextUnitKt.getSp(0.5d);
        LabelMediumWeight = typefaceTokens.getWeightMedium();
        LabelSmallFont = typefaceTokens.getPlain();
        LabelSmallLineHeight = TextUnitKt.getSp(16.0d);
        LabelSmallSize = TextUnitKt.getSp(11);
        LabelSmallTracking = TextUnitKt.getSp(0.5d);
        LabelSmallWeight = typefaceTokens.getWeightMedium();
        TitleLargeFont = typefaceTokens.getBrand();
        TitleLargeLineHeight = TextUnitKt.getSp(28.0d);
        TitleLargeSize = TextUnitKt.getSp(22);
        TitleLargeTracking = TextUnitKt.getSp(0.0d);
        TitleLargeWeight = typefaceTokens.getWeightRegular();
        TitleMediumFont = typefaceTokens.getPlain();
        TitleMediumLineHeight = TextUnitKt.getSp(24.0d);
        TitleMediumSize = TextUnitKt.getSp(16);
        TitleMediumTracking = TextUnitKt.getSp(0.2d);
        TitleMediumWeight = typefaceTokens.getWeightMedium();
        TitleSmallFont = typefaceTokens.getPlain();
        TitleSmallLineHeight = TextUnitKt.getSp(20.0d);
        TitleSmallSize = TextUnitKt.getSp(14);
        TitleSmallTracking = TextUnitKt.getSp(0.1d);
        TitleSmallWeight = typefaceTokens.getWeightMedium();
        BodyLargeEmphasizedFont = typefaceTokens.getPlain();
        BodyLargeEmphasizedLineHeight = TextUnitKt.getSp(24.0d);
        BodyLargeEmphasizedSize = TextUnitKt.getSp(16);
        BodyLargeEmphasizedTracking = TextUnitKt.getSp(0.15d);
        BodyLargeEmphasizedWeight = typefaceTokens.getWeightMedium();
        BodyMediumEmphasizedFont = typefaceTokens.getPlain();
        BodyMediumEmphasizedLineHeight = TextUnitKt.getSp(20.0d);
        BodyMediumEmphasizedSize = TextUnitKt.getSp(14);
        BodyMediumEmphasizedTracking = TextUnitKt.getSp(0.25d);
        BodyMediumEmphasizedWeight = typefaceTokens.getWeightMedium();
        BodySmallEmphasizedFont = typefaceTokens.getPlain();
        BodySmallEmphasizedLineHeight = TextUnitKt.getSp(16.0d);
        BodySmallEmphasizedSize = TextUnitKt.getSp(12);
        BodySmallEmphasizedTracking = TextUnitKt.getSp(0.4d);
        BodySmallEmphasizedWeight = typefaceTokens.getWeightMedium();
        DisplayLargeEmphasizedFont = typefaceTokens.getBrand();
        DisplayLargeEmphasizedLineHeight = TextUnitKt.getSp(64.0d);
        DisplayLargeEmphasizedSize = TextUnitKt.getSp(57);
        DisplayLargeEmphasizedTracking = TextUnitKt.getSp(0);
        DisplayLargeEmphasizedWeight = typefaceTokens.getWeightMedium();
        DisplayMediumEmphasizedFont = typefaceTokens.getBrand();
        DisplayMediumEmphasizedLineHeight = TextUnitKt.getSp(52.0d);
        DisplayMediumEmphasizedSize = TextUnitKt.getSp(45);
        DisplayMediumEmphasizedTracking = TextUnitKt.getSp(0);
        DisplayMediumEmphasizedWeight = typefaceTokens.getWeightMedium();
        DisplaySmallEmphasizedFont = typefaceTokens.getBrand();
        DisplaySmallEmphasizedLineHeight = TextUnitKt.getSp(44.0d);
        DisplaySmallEmphasizedSize = TextUnitKt.getSp(36);
        DisplaySmallEmphasizedTracking = TextUnitKt.getSp(0);
        DisplaySmallEmphasizedWeight = typefaceTokens.getWeightMedium();
        HeadlineLargeEmphasizedFont = typefaceTokens.getBrand();
        HeadlineLargeEmphasizedLineHeight = TextUnitKt.getSp(40.0d);
        HeadlineLargeEmphasizedSize = TextUnitKt.getSp(32);
        HeadlineLargeEmphasizedTracking = TextUnitKt.getSp(0);
        HeadlineLargeEmphasizedWeight = typefaceTokens.getWeightMedium();
        HeadlineMediumEmphasizedFont = typefaceTokens.getBrand();
        HeadlineMediumEmphasizedLineHeight = TextUnitKt.getSp(36.0d);
        HeadlineMediumEmphasizedSize = TextUnitKt.getSp(28);
        HeadlineMediumEmphasizedTracking = TextUnitKt.getSp(0);
        HeadlineMediumEmphasizedWeight = typefaceTokens.getWeightMedium();
        HeadlineSmallEmphasizedFont = typefaceTokens.getBrand();
        HeadlineSmallEmphasizedLineHeight = TextUnitKt.getSp(32.0d);
        HeadlineSmallEmphasizedSize = TextUnitKt.getSp(24);
        HeadlineSmallEmphasizedTracking = TextUnitKt.getSp(0);
        HeadlineSmallEmphasizedWeight = typefaceTokens.getWeightMedium();
        LabelLargeEmphasizedFont = typefaceTokens.getPlain();
        LabelLargeEmphasizedLineHeight = TextUnitKt.getSp(20.0d);
        LabelLargeEmphasizedSize = TextUnitKt.getSp(14);
        LabelLargeEmphasizedTracking = TextUnitKt.getSp(0.1d);
        LabelLargeEmphasizedWeight = typefaceTokens.getWeightBold();
        LabelMediumEmphasizedFont = typefaceTokens.getPlain();
        LabelMediumEmphasizedLineHeight = TextUnitKt.getSp(16.0d);
        LabelMediumEmphasizedSize = TextUnitKt.getSp(12);
        LabelMediumEmphasizedTracking = TextUnitKt.getSp(0.5d);
        LabelMediumEmphasizedWeight = typefaceTokens.getWeightBold();
        LabelSmallEmphasizedFont = typefaceTokens.getPlain();
        LabelSmallEmphasizedLineHeight = TextUnitKt.getSp(16.0d);
        LabelSmallEmphasizedSize = TextUnitKt.getSp(11);
        LabelSmallEmphasizedTracking = TextUnitKt.getSp(0.5d);
        LabelSmallEmphasizedWeight = typefaceTokens.getWeightBold();
        TitleLargeEmphasizedFont = typefaceTokens.getBrand();
        TitleLargeEmphasizedLineHeight = TextUnitKt.getSp(28.0d);
        TitleLargeEmphasizedSize = TextUnitKt.getSp(22);
        TitleLargeEmphasizedTracking = TextUnitKt.getSp(0);
        TitleLargeEmphasizedWeight = typefaceTokens.getWeightMedium();
        TitleMediumEmphasizedFont = typefaceTokens.getPlain();
        TitleMediumEmphasizedLineHeight = TextUnitKt.getSp(24.0d);
        TitleMediumEmphasizedSize = TextUnitKt.getSp(16);
        TitleMediumEmphasizedTracking = TextUnitKt.getSp(0.15d);
        TitleMediumEmphasizedWeight = typefaceTokens.getWeightBold();
        TitleSmallEmphasizedFont = typefaceTokens.getPlain();
        TitleSmallEmphasizedLineHeight = TextUnitKt.getSp(20.0d);
        TitleSmallEmphasizedSize = TextUnitKt.getSp(14);
        TitleSmallEmphasizedTracking = TextUnitKt.getSp(0.1d);
        TitleSmallEmphasizedWeight = typefaceTokens.getWeightBold();
    }

    private TypeScaleTokens() {
    }

    public final GenericFontFamily getBodyLargeEmphasizedFont() {
        return BodyLargeEmphasizedFont;
    }

    /* JADX INFO: renamed from: getBodyLargeEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2213getBodyLargeEmphasizedLineHeightXSAIIZE() {
        return BodyLargeEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getBodyLargeEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2214getBodyLargeEmphasizedSizeXSAIIZE() {
        return BodyLargeEmphasizedSize;
    }

    /* JADX INFO: renamed from: getBodyLargeEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2215getBodyLargeEmphasizedTrackingXSAIIZE() {
        return BodyLargeEmphasizedTracking;
    }

    public final FontWeight getBodyLargeEmphasizedWeight() {
        return BodyLargeEmphasizedWeight;
    }

    public final GenericFontFamily getBodyLargeFont() {
        return BodyLargeFont;
    }

    /* JADX INFO: renamed from: getBodyLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2216getBodyLargeLineHeightXSAIIZE() {
        return BodyLargeLineHeight;
    }

    /* JADX INFO: renamed from: getBodyLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2217getBodyLargeSizeXSAIIZE() {
        return BodyLargeSize;
    }

    /* JADX INFO: renamed from: getBodyLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2218getBodyLargeTrackingXSAIIZE() {
        return BodyLargeTracking;
    }

    public final FontWeight getBodyLargeWeight() {
        return BodyLargeWeight;
    }

    public final GenericFontFamily getBodyMediumEmphasizedFont() {
        return BodyMediumEmphasizedFont;
    }

    /* JADX INFO: renamed from: getBodyMediumEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2219getBodyMediumEmphasizedLineHeightXSAIIZE() {
        return BodyMediumEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getBodyMediumEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2220getBodyMediumEmphasizedSizeXSAIIZE() {
        return BodyMediumEmphasizedSize;
    }

    /* JADX INFO: renamed from: getBodyMediumEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2221getBodyMediumEmphasizedTrackingXSAIIZE() {
        return BodyMediumEmphasizedTracking;
    }

    public final FontWeight getBodyMediumEmphasizedWeight() {
        return BodyMediumEmphasizedWeight;
    }

    public final GenericFontFamily getBodyMediumFont() {
        return BodyMediumFont;
    }

    /* JADX INFO: renamed from: getBodyMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2222getBodyMediumLineHeightXSAIIZE() {
        return BodyMediumLineHeight;
    }

    /* JADX INFO: renamed from: getBodyMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2223getBodyMediumSizeXSAIIZE() {
        return BodyMediumSize;
    }

    /* JADX INFO: renamed from: getBodyMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2224getBodyMediumTrackingXSAIIZE() {
        return BodyMediumTracking;
    }

    public final FontWeight getBodyMediumWeight() {
        return BodyMediumWeight;
    }

    public final GenericFontFamily getBodySmallEmphasizedFont() {
        return BodySmallEmphasizedFont;
    }

    /* JADX INFO: renamed from: getBodySmallEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2225getBodySmallEmphasizedLineHeightXSAIIZE() {
        return BodySmallEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getBodySmallEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2226getBodySmallEmphasizedSizeXSAIIZE() {
        return BodySmallEmphasizedSize;
    }

    /* JADX INFO: renamed from: getBodySmallEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2227getBodySmallEmphasizedTrackingXSAIIZE() {
        return BodySmallEmphasizedTracking;
    }

    public final FontWeight getBodySmallEmphasizedWeight() {
        return BodySmallEmphasizedWeight;
    }

    public final GenericFontFamily getBodySmallFont() {
        return BodySmallFont;
    }

    /* JADX INFO: renamed from: getBodySmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2228getBodySmallLineHeightXSAIIZE() {
        return BodySmallLineHeight;
    }

    /* JADX INFO: renamed from: getBodySmallSize-XSAIIZE, reason: not valid java name */
    public final long m2229getBodySmallSizeXSAIIZE() {
        return BodySmallSize;
    }

    /* JADX INFO: renamed from: getBodySmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2230getBodySmallTrackingXSAIIZE() {
        return BodySmallTracking;
    }

    public final FontWeight getBodySmallWeight() {
        return BodySmallWeight;
    }

    public final GenericFontFamily getDisplayLargeEmphasizedFont() {
        return DisplayLargeEmphasizedFont;
    }

    /* JADX INFO: renamed from: getDisplayLargeEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2231getDisplayLargeEmphasizedLineHeightXSAIIZE() {
        return DisplayLargeEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayLargeEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2232getDisplayLargeEmphasizedSizeXSAIIZE() {
        return DisplayLargeEmphasizedSize;
    }

    /* JADX INFO: renamed from: getDisplayLargeEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2233getDisplayLargeEmphasizedTrackingXSAIIZE() {
        return DisplayLargeEmphasizedTracking;
    }

    public final FontWeight getDisplayLargeEmphasizedWeight() {
        return DisplayLargeEmphasizedWeight;
    }

    public final GenericFontFamily getDisplayLargeFont() {
        return DisplayLargeFont;
    }

    /* JADX INFO: renamed from: getDisplayLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2234getDisplayLargeLineHeightXSAIIZE() {
        return DisplayLargeLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2235getDisplayLargeSizeXSAIIZE() {
        return DisplayLargeSize;
    }

    /* JADX INFO: renamed from: getDisplayLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2236getDisplayLargeTrackingXSAIIZE() {
        return DisplayLargeTracking;
    }

    public final FontWeight getDisplayLargeWeight() {
        return DisplayLargeWeight;
    }

    public final GenericFontFamily getDisplayMediumEmphasizedFont() {
        return DisplayMediumEmphasizedFont;
    }

    /* JADX INFO: renamed from: getDisplayMediumEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2237getDisplayMediumEmphasizedLineHeightXSAIIZE() {
        return DisplayMediumEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayMediumEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2238getDisplayMediumEmphasizedSizeXSAIIZE() {
        return DisplayMediumEmphasizedSize;
    }

    /* JADX INFO: renamed from: getDisplayMediumEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2239getDisplayMediumEmphasizedTrackingXSAIIZE() {
        return DisplayMediumEmphasizedTracking;
    }

    public final FontWeight getDisplayMediumEmphasizedWeight() {
        return DisplayMediumEmphasizedWeight;
    }

    public final GenericFontFamily getDisplayMediumFont() {
        return DisplayMediumFont;
    }

    /* JADX INFO: renamed from: getDisplayMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2240getDisplayMediumLineHeightXSAIIZE() {
        return DisplayMediumLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2241getDisplayMediumSizeXSAIIZE() {
        return DisplayMediumSize;
    }

    /* JADX INFO: renamed from: getDisplayMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2242getDisplayMediumTrackingXSAIIZE() {
        return DisplayMediumTracking;
    }

    public final FontWeight getDisplayMediumWeight() {
        return DisplayMediumWeight;
    }

    public final GenericFontFamily getDisplaySmallEmphasizedFont() {
        return DisplaySmallEmphasizedFont;
    }

    /* JADX INFO: renamed from: getDisplaySmallEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2243getDisplaySmallEmphasizedLineHeightXSAIIZE() {
        return DisplaySmallEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getDisplaySmallEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2244getDisplaySmallEmphasizedSizeXSAIIZE() {
        return DisplaySmallEmphasizedSize;
    }

    /* JADX INFO: renamed from: getDisplaySmallEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2245getDisplaySmallEmphasizedTrackingXSAIIZE() {
        return DisplaySmallEmphasizedTracking;
    }

    public final FontWeight getDisplaySmallEmphasizedWeight() {
        return DisplaySmallEmphasizedWeight;
    }

    public final GenericFontFamily getDisplaySmallFont() {
        return DisplaySmallFont;
    }

    /* JADX INFO: renamed from: getDisplaySmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2246getDisplaySmallLineHeightXSAIIZE() {
        return DisplaySmallLineHeight;
    }

    /* JADX INFO: renamed from: getDisplaySmallSize-XSAIIZE, reason: not valid java name */
    public final long m2247getDisplaySmallSizeXSAIIZE() {
        return DisplaySmallSize;
    }

    /* JADX INFO: renamed from: getDisplaySmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2248getDisplaySmallTrackingXSAIIZE() {
        return DisplaySmallTracking;
    }

    public final FontWeight getDisplaySmallWeight() {
        return DisplaySmallWeight;
    }

    public final GenericFontFamily getHeadlineLargeEmphasizedFont() {
        return HeadlineLargeEmphasizedFont;
    }

    /* JADX INFO: renamed from: getHeadlineLargeEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2249getHeadlineLargeEmphasizedLineHeightXSAIIZE() {
        return HeadlineLargeEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineLargeEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2250getHeadlineLargeEmphasizedSizeXSAIIZE() {
        return HeadlineLargeEmphasizedSize;
    }

    /* JADX INFO: renamed from: getHeadlineLargeEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2251getHeadlineLargeEmphasizedTrackingXSAIIZE() {
        return HeadlineLargeEmphasizedTracking;
    }

    public final FontWeight getHeadlineLargeEmphasizedWeight() {
        return HeadlineLargeEmphasizedWeight;
    }

    public final GenericFontFamily getHeadlineLargeFont() {
        return HeadlineLargeFont;
    }

    /* JADX INFO: renamed from: getHeadlineLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2252getHeadlineLargeLineHeightXSAIIZE() {
        return HeadlineLargeLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2253getHeadlineLargeSizeXSAIIZE() {
        return HeadlineLargeSize;
    }

    /* JADX INFO: renamed from: getHeadlineLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2254getHeadlineLargeTrackingXSAIIZE() {
        return HeadlineLargeTracking;
    }

    public final FontWeight getHeadlineLargeWeight() {
        return HeadlineLargeWeight;
    }

    public final GenericFontFamily getHeadlineMediumEmphasizedFont() {
        return HeadlineMediumEmphasizedFont;
    }

    /* JADX INFO: renamed from: getHeadlineMediumEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2255getHeadlineMediumEmphasizedLineHeightXSAIIZE() {
        return HeadlineMediumEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineMediumEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2256getHeadlineMediumEmphasizedSizeXSAIIZE() {
        return HeadlineMediumEmphasizedSize;
    }

    /* JADX INFO: renamed from: getHeadlineMediumEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2257getHeadlineMediumEmphasizedTrackingXSAIIZE() {
        return HeadlineMediumEmphasizedTracking;
    }

    public final FontWeight getHeadlineMediumEmphasizedWeight() {
        return HeadlineMediumEmphasizedWeight;
    }

    public final GenericFontFamily getHeadlineMediumFont() {
        return HeadlineMediumFont;
    }

    /* JADX INFO: renamed from: getHeadlineMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2258getHeadlineMediumLineHeightXSAIIZE() {
        return HeadlineMediumLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2259getHeadlineMediumSizeXSAIIZE() {
        return HeadlineMediumSize;
    }

    /* JADX INFO: renamed from: getHeadlineMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2260getHeadlineMediumTrackingXSAIIZE() {
        return HeadlineMediumTracking;
    }

    public final FontWeight getHeadlineMediumWeight() {
        return HeadlineMediumWeight;
    }

    public final GenericFontFamily getHeadlineSmallEmphasizedFont() {
        return HeadlineSmallEmphasizedFont;
    }

    /* JADX INFO: renamed from: getHeadlineSmallEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2261getHeadlineSmallEmphasizedLineHeightXSAIIZE() {
        return HeadlineSmallEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineSmallEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2262getHeadlineSmallEmphasizedSizeXSAIIZE() {
        return HeadlineSmallEmphasizedSize;
    }

    /* JADX INFO: renamed from: getHeadlineSmallEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2263getHeadlineSmallEmphasizedTrackingXSAIIZE() {
        return HeadlineSmallEmphasizedTracking;
    }

    public final FontWeight getHeadlineSmallEmphasizedWeight() {
        return HeadlineSmallEmphasizedWeight;
    }

    public final GenericFontFamily getHeadlineSmallFont() {
        return HeadlineSmallFont;
    }

    /* JADX INFO: renamed from: getHeadlineSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2264getHeadlineSmallLineHeightXSAIIZE() {
        return HeadlineSmallLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2265getHeadlineSmallSizeXSAIIZE() {
        return HeadlineSmallSize;
    }

    /* JADX INFO: renamed from: getHeadlineSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2266getHeadlineSmallTrackingXSAIIZE() {
        return HeadlineSmallTracking;
    }

    public final FontWeight getHeadlineSmallWeight() {
        return HeadlineSmallWeight;
    }

    public final GenericFontFamily getLabelLargeEmphasizedFont() {
        return LabelLargeEmphasizedFont;
    }

    /* JADX INFO: renamed from: getLabelLargeEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2267getLabelLargeEmphasizedLineHeightXSAIIZE() {
        return LabelLargeEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getLabelLargeEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2268getLabelLargeEmphasizedSizeXSAIIZE() {
        return LabelLargeEmphasizedSize;
    }

    /* JADX INFO: renamed from: getLabelLargeEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2269getLabelLargeEmphasizedTrackingXSAIIZE() {
        return LabelLargeEmphasizedTracking;
    }

    public final FontWeight getLabelLargeEmphasizedWeight() {
        return LabelLargeEmphasizedWeight;
    }

    public final GenericFontFamily getLabelLargeFont() {
        return LabelLargeFont;
    }

    /* JADX INFO: renamed from: getLabelLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2270getLabelLargeLineHeightXSAIIZE() {
        return LabelLargeLineHeight;
    }

    /* JADX INFO: renamed from: getLabelLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2271getLabelLargeSizeXSAIIZE() {
        return LabelLargeSize;
    }

    /* JADX INFO: renamed from: getLabelLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2272getLabelLargeTrackingXSAIIZE() {
        return LabelLargeTracking;
    }

    public final FontWeight getLabelLargeWeight() {
        return LabelLargeWeight;
    }

    public final GenericFontFamily getLabelMediumEmphasizedFont() {
        return LabelMediumEmphasizedFont;
    }

    /* JADX INFO: renamed from: getLabelMediumEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2273getLabelMediumEmphasizedLineHeightXSAIIZE() {
        return LabelMediumEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getLabelMediumEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2274getLabelMediumEmphasizedSizeXSAIIZE() {
        return LabelMediumEmphasizedSize;
    }

    /* JADX INFO: renamed from: getLabelMediumEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2275getLabelMediumEmphasizedTrackingXSAIIZE() {
        return LabelMediumEmphasizedTracking;
    }

    public final FontWeight getLabelMediumEmphasizedWeight() {
        return LabelMediumEmphasizedWeight;
    }

    public final GenericFontFamily getLabelMediumFont() {
        return LabelMediumFont;
    }

    /* JADX INFO: renamed from: getLabelMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2276getLabelMediumLineHeightXSAIIZE() {
        return LabelMediumLineHeight;
    }

    /* JADX INFO: renamed from: getLabelMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2277getLabelMediumSizeXSAIIZE() {
        return LabelMediumSize;
    }

    /* JADX INFO: renamed from: getLabelMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2278getLabelMediumTrackingXSAIIZE() {
        return LabelMediumTracking;
    }

    public final FontWeight getLabelMediumWeight() {
        return LabelMediumWeight;
    }

    public final GenericFontFamily getLabelSmallEmphasizedFont() {
        return LabelSmallEmphasizedFont;
    }

    /* JADX INFO: renamed from: getLabelSmallEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2279getLabelSmallEmphasizedLineHeightXSAIIZE() {
        return LabelSmallEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getLabelSmallEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2280getLabelSmallEmphasizedSizeXSAIIZE() {
        return LabelSmallEmphasizedSize;
    }

    /* JADX INFO: renamed from: getLabelSmallEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2281getLabelSmallEmphasizedTrackingXSAIIZE() {
        return LabelSmallEmphasizedTracking;
    }

    public final FontWeight getLabelSmallEmphasizedWeight() {
        return LabelSmallEmphasizedWeight;
    }

    public final GenericFontFamily getLabelSmallFont() {
        return LabelSmallFont;
    }

    /* JADX INFO: renamed from: getLabelSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2282getLabelSmallLineHeightXSAIIZE() {
        return LabelSmallLineHeight;
    }

    /* JADX INFO: renamed from: getLabelSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2283getLabelSmallSizeXSAIIZE() {
        return LabelSmallSize;
    }

    /* JADX INFO: renamed from: getLabelSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2284getLabelSmallTrackingXSAIIZE() {
        return LabelSmallTracking;
    }

    public final FontWeight getLabelSmallWeight() {
        return LabelSmallWeight;
    }

    public final GenericFontFamily getTitleLargeEmphasizedFont() {
        return TitleLargeEmphasizedFont;
    }

    /* JADX INFO: renamed from: getTitleLargeEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2285getTitleLargeEmphasizedLineHeightXSAIIZE() {
        return TitleLargeEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getTitleLargeEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2286getTitleLargeEmphasizedSizeXSAIIZE() {
        return TitleLargeEmphasizedSize;
    }

    /* JADX INFO: renamed from: getTitleLargeEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2287getTitleLargeEmphasizedTrackingXSAIIZE() {
        return TitleLargeEmphasizedTracking;
    }

    public final FontWeight getTitleLargeEmphasizedWeight() {
        return TitleLargeEmphasizedWeight;
    }

    public final GenericFontFamily getTitleLargeFont() {
        return TitleLargeFont;
    }

    /* JADX INFO: renamed from: getTitleLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2288getTitleLargeLineHeightXSAIIZE() {
        return TitleLargeLineHeight;
    }

    /* JADX INFO: renamed from: getTitleLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2289getTitleLargeSizeXSAIIZE() {
        return TitleLargeSize;
    }

    /* JADX INFO: renamed from: getTitleLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2290getTitleLargeTrackingXSAIIZE() {
        return TitleLargeTracking;
    }

    public final FontWeight getTitleLargeWeight() {
        return TitleLargeWeight;
    }

    public final GenericFontFamily getTitleMediumEmphasizedFont() {
        return TitleMediumEmphasizedFont;
    }

    /* JADX INFO: renamed from: getTitleMediumEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2291getTitleMediumEmphasizedLineHeightXSAIIZE() {
        return TitleMediumEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getTitleMediumEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2292getTitleMediumEmphasizedSizeXSAIIZE() {
        return TitleMediumEmphasizedSize;
    }

    /* JADX INFO: renamed from: getTitleMediumEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2293getTitleMediumEmphasizedTrackingXSAIIZE() {
        return TitleMediumEmphasizedTracking;
    }

    public final FontWeight getTitleMediumEmphasizedWeight() {
        return TitleMediumEmphasizedWeight;
    }

    public final GenericFontFamily getTitleMediumFont() {
        return TitleMediumFont;
    }

    /* JADX INFO: renamed from: getTitleMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2294getTitleMediumLineHeightXSAIIZE() {
        return TitleMediumLineHeight;
    }

    /* JADX INFO: renamed from: getTitleMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2295getTitleMediumSizeXSAIIZE() {
        return TitleMediumSize;
    }

    /* JADX INFO: renamed from: getTitleMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2296getTitleMediumTrackingXSAIIZE() {
        return TitleMediumTracking;
    }

    public final FontWeight getTitleMediumWeight() {
        return TitleMediumWeight;
    }

    public final GenericFontFamily getTitleSmallEmphasizedFont() {
        return TitleSmallEmphasizedFont;
    }

    /* JADX INFO: renamed from: getTitleSmallEmphasizedLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2297getTitleSmallEmphasizedLineHeightXSAIIZE() {
        return TitleSmallEmphasizedLineHeight;
    }

    /* JADX INFO: renamed from: getTitleSmallEmphasizedSize-XSAIIZE, reason: not valid java name */
    public final long m2298getTitleSmallEmphasizedSizeXSAIIZE() {
        return TitleSmallEmphasizedSize;
    }

    /* JADX INFO: renamed from: getTitleSmallEmphasizedTracking-XSAIIZE, reason: not valid java name */
    public final long m2299getTitleSmallEmphasizedTrackingXSAIIZE() {
        return TitleSmallEmphasizedTracking;
    }

    public final FontWeight getTitleSmallEmphasizedWeight() {
        return TitleSmallEmphasizedWeight;
    }

    public final GenericFontFamily getTitleSmallFont() {
        return TitleSmallFont;
    }

    /* JADX INFO: renamed from: getTitleSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2300getTitleSmallLineHeightXSAIIZE() {
        return TitleSmallLineHeight;
    }

    /* JADX INFO: renamed from: getTitleSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2301getTitleSmallSizeXSAIIZE() {
        return TitleSmallSize;
    }

    /* JADX INFO: renamed from: getTitleSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2302getTitleSmallTrackingXSAIIZE() {
        return TitleSmallTracking;
    }

    public final FontWeight getTitleSmallWeight() {
        return TitleSmallWeight;
    }
}
