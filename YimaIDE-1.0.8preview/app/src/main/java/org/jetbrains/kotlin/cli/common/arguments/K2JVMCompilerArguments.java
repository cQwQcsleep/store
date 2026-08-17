package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0003\bÚ\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010ð\u0001\u001a\u00030ñ\u0001H\u0014R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR4\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R&\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R*\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\b\"\u0004\b \u0010\nR&\u0010!\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\nR*\u0010$\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010\nR&\u0010'\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010\u0017R*\u0010*\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\b\"\u0004\b,\u0010\nR&\u0010-\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0015\"\u0004\b/\u0010\u0017R&\u00100\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0015\"\u0004\b2\u0010\u0017R&\u00103\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0015\"\u0004\b5\u0010\u0017R&\u00106\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0015\"\u0004\b8\u0010\u0017R4\u00109\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b:\u0010\u000e\"\u0004\b;\u0010\u0010R&\u0010<\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R4\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b@\u0010\u000e\"\u0004\bA\u0010\u0010R,\u0010B\u001a\u0004\u0018\u00010\u00122\b\u0010\u0004\u001a\u0004\u0018\u00010\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR&\u0010H\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0015\"\u0004\bJ\u0010\u0017R*\u0010K\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\b\"\u0004\bM\u0010\nR4\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\bO\u0010\u000e\"\u0004\bP\u0010\u0010R*\u0010Q\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\b\"\u0004\bS\u0010\nR*\u0010T\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\b\"\u0004\bV\u0010\nR4\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\bX\u0010\u000e\"\u0004\bY\u0010\u0010R0\u0010Z\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b[\u0010\u0003\u001a\u0004\b\\\u0010\b\"\u0004\b]\u0010\nR&\u0010^\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0015\"\u0004\b`\u0010\u0017R&\u0010a\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0015\"\u0004\bc\u0010\u0017R*\u0010d\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\b\"\u0004\bf\u0010\nR*\u0010g\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\b\"\u0004\bi\u0010\nR,\u0010j\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bk\u0010\u0003\u001a\u0004\bl\u0010\u0015\"\u0004\bm\u0010\u0017R*\u0010n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010\b\"\u0004\bp\u0010\nR&\u0010q\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010\u0015\"\u0004\bs\u0010\u0017R&\u0010t\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\u0015\"\u0004\bv\u0010\u0017R&\u0010w\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u0015\"\u0004\by\u0010\u0017R&\u0010z\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u0015\"\u0004\b|\u0010\u0017R&\u0010}\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u0015\"\u0004\b\u007f\u0010\u0017R)\u0010\u0080\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0015\"\u0005\b\u0082\u0001\u0010\u0017R)\u0010\u0083\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0015\"\u0005\b\u0085\u0001\u0010\u0017R)\u0010\u0086\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u0015\"\u0005\b\u0088\u0001\u0010\u0017R)\u0010\u0089\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0015\"\u0005\b\u008b\u0001\u0010\u0017R7\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0011\u001a\u0005\b\u008d\u0001\u0010\u000e\"\u0005\b\u008e\u0001\u0010\u0010R)\u0010\u008f\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010\u0015\"\u0005\b\u0091\u0001\u0010\u0017R-\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010\b\"\u0005\b\u0094\u0001\u0010\nR-\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0001\u0010\b\"\u0005\b\u0097\u0001\u0010\nR)\u0010\u0098\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0015\"\u0005\b\u009a\u0001\u0010\u0017R7\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0011\u001a\u0005\b\u009c\u0001\u0010\u000e\"\u0005\b\u009d\u0001\u0010\u0010R-\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010\b\"\u0005\b \u0001\u0010\nR-\u0010¡\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0001\u0010\b\"\u0005\b£\u0001\u0010\nR)\u0010¤\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010\u0015\"\u0005\b¦\u0001\u0010\u0017R)\u0010§\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¨\u0001\u0010\u0015\"\u0005\b©\u0001\u0010\u0017R)\u0010ª\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0001\u0010\u0015\"\u0005\b¬\u0001\u0010\u0017R)\u0010\u00ad\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b®\u0001\u0010\u0015\"\u0005\b¯\u0001\u0010\u0017R/\u0010°\u0001\u001a\u0004\u0018\u00010\u00122\b\u0010\u0004\u001a\u0004\u0018\u00010\u00128\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010G\u001a\u0005\b±\u0001\u0010D\"\u0005\b²\u0001\u0010FR)\u0010³\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b´\u0001\u0010\u0015\"\u0005\bµ\u0001\u0010\u0017R)\u0010¶\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b·\u0001\u0010\u0015\"\u0005\b¸\u0001\u0010\u0017R)\u0010¹\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bº\u0001\u0010\u0015\"\u0005\b»\u0001\u0010\u0017R)\u0010¼\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b½\u0001\u0010\u0015\"\u0005\b¾\u0001\u0010\u0017R)\u0010¿\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÀ\u0001\u0010\u0015\"\u0005\bÁ\u0001\u0010\u0017R-\u0010Â\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0001\u0010\b\"\u0005\bÄ\u0001\u0010\nR-\u0010Å\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÆ\u0001\u0010\b\"\u0005\bÇ\u0001\u0010\nR-\u0010È\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÉ\u0001\u0010\b\"\u0005\bÊ\u0001\u0010\nR-\u0010Ë\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÌ\u0001\u0010\b\"\u0005\bÍ\u0001\u0010\nR)\u0010Î\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÏ\u0001\u0010\u0015\"\u0005\bÐ\u0001\u0010\u0017R)\u0010Ñ\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÒ\u0001\u0010\u0015\"\u0005\bÓ\u0001\u0010\u0017R-\u0010Ô\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÕ\u0001\u0010\b\"\u0005\bÖ\u0001\u0010\nR-\u0010×\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bØ\u0001\u0010\b\"\u0005\bÙ\u0001\u0010\nR-\u0010Ú\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÛ\u0001\u0010\b\"\u0005\bÜ\u0001\u0010\nR-\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÞ\u0001\u0010\b\"\u0005\bß\u0001\u0010\nR)\u0010à\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bá\u0001\u0010\u0015\"\u0005\bâ\u0001\u0010\u0017R)\u0010ã\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bä\u0001\u0010\u0015\"\u0005\bå\u0001\u0010\u0017R)\u0010æ\u0001\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bç\u0001\u0010\u0015\"\u0005\bè\u0001\u0010\u0017R7\u0010é\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0011\u001a\u0005\bê\u0001\u0010\u000e\"\u0005\bë\u0001\u0010\u0010R\u001a\u0010ì\u0001\u001a\u00030í\u00018\u0017X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bî\u0001\u0010ï\u0001¨\u0006ò\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "abiStability", "getAbiStability", "()Ljava/lang/String;", "setAbiStability", "(Ljava/lang/String;)V", Argument.Delimiters.none, "additionalJavaModules", "getAdditionalJavaModules", "()[Ljava/lang/String;", "setAdditionalJavaModules", "([Ljava/lang/String;)V", "[Ljava/lang/String;", Argument.Delimiters.none, "allowNoSourceFiles", "getAllowNoSourceFiles", "()Z", "setAllowNoSourceFiles", "(Z)V", "allowUnstableDependencies", "getAllowUnstableDependencies", "setAllowUnstableDependencies", "annotationsInMetadata", "getAnnotationsInMetadata", "setAnnotationsInMetadata", "assertionsMode", "getAssertionsMode", "setAssertionsMode", "backendThreads", "getBackendThreads", "setBackendThreads", "buildFile", "getBuildFile", "setBuildFile", "enableDebugMode", "getEnableDebugMode", "setEnableDebugMode", "defaultScriptExtension", "getDefaultScriptExtension", "setDefaultScriptExtension", "disableStandardScript", "getDisableStandardScript", "setDisableStandardScript", "emitJvmTypeAnnotations", "getEmitJvmTypeAnnotations", "setEmitJvmTypeAnnotations", "enhanceTypeParameterTypesToDefNotNull", "getEnhanceTypeParameterTypesToDefNotNull", "setEnhanceTypeParameterTypesToDefNotNull", "enhancedCoroutinesDebugging", "getEnhancedCoroutinesDebugging", "setEnhancedCoroutinesDebugging", "friendPaths", "getFriendPaths", "setFriendPaths", "strictMetadataVersionSemantics", "getStrictMetadataVersionSemantics", "setStrictMetadataVersionSemantics", "ignoredAnnotationsForBridges", "getIgnoredAnnotationsForBridges", "setIgnoredAnnotationsForBridges", "indyAllowAnnotatedLambdas", "getIndyAllowAnnotatedLambdas", "()Ljava/lang/Boolean;", "setIndyAllowAnnotatedLambdas", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "doNotClearBindingContext", "getDoNotClearBindingContext", "setDoNotClearBindingContext", "javaPackagePrefix", "getJavaPackagePrefix", "setJavaPackagePrefix", ModuleXmlParser.JAVA_SOURCE_ROOTS, "getJavaSourceRoots", "setJavaSourceRoots", "jdkRelease", "getJdkRelease", "setJdkRelease", "jspecifyAnnotations", "getJspecifyAnnotations", "setJspecifyAnnotations", "jsr305", "getJsr305", "setJsr305", "jvmDefault", "getJvmDefault$annotations", "getJvmDefault", "setJvmDefault", "enableJvmPreview", "getEnableJvmPreview", "setEnableJvmPreview", "jvmExposeBoxed", "getJvmExposeBoxed", "setJvmExposeBoxed", "klibLibraries", "getKlibLibraries", "setKlibLibraries", "lambdas", "getLambdas", "setLambdas", "linkViaSignatures", "getLinkViaSignatures$annotations", "getLinkViaSignatures", "setLinkViaSignatures", "javaModulePath", "getJavaModulePath", "setJavaModulePath", "inheritMultifileParts", "getInheritMultifileParts", "setInheritMultifileParts", "noCallAssertions", "getNoCallAssertions", "setNoCallAssertions", "noNewJavaAnnotationTargets", "getNoNewJavaAnnotationTargets", "setNoNewJavaAnnotationTargets", "noOptimize", "getNoOptimize", "setNoOptimize", "noParamAssertions", "getNoParamAssertions", "setNoParamAssertions", "noReceiverAssertions", "getNoReceiverAssertions", "setNoReceiverAssertions", "noResetJarTimestamps", "getNoResetJarTimestamps", "setNoResetJarTimestamps", "noSourceDebugExtension", "getNoSourceDebugExtension", "setNoSourceDebugExtension", "noUnifiedNullChecks", "getNoUnifiedNullChecks", "setNoUnifiedNullChecks", "nullabilityAnnotations", "getNullabilityAnnotations", "setNullabilityAnnotations", "outputBuiltinsMetadata", "getOutputBuiltinsMetadata", "setOutputBuiltinsMetadata", "profileCompilerCommand", "getProfileCompilerCommand", "setProfileCompilerCommand", "samConversions", "getSamConversions", "setSamConversions", "sanitizeParentheses", "getSanitizeParentheses", "setSanitizeParentheses", "scriptResolverEnvironment", "getScriptResolverEnvironment", "setScriptResolverEnvironment", "stringConcat", "getStringConcat", "setStringConcat", "supportCompatqualCheckerFrameworkAnnotations", "getSupportCompatqualCheckerFrameworkAnnotations", "setSupportCompatqualCheckerFrameworkAnnotations", "suppressDeprecatedJvmTargetWarning", "getSuppressDeprecatedJvmTargetWarning", "setSuppressDeprecatedJvmTargetWarning", "suppressMissingBuiltinsError", "getSuppressMissingBuiltinsError", "setSuppressMissingBuiltinsError", "typeEnhancementImprovementsInStrictMode", "getTypeEnhancementImprovementsInStrictMode", "setTypeEnhancementImprovementsInStrictMode", "useOldInlineClassesManglingScheme", "getUseOldInlineClassesManglingScheme", "setUseOldInlineClassesManglingScheme", "useFastJarFileSystem", "getUseFastJarFileSystem", "setUseFastJarFileSystem", "useInlineScopesNumbers", "getUseInlineScopesNumbers", "setUseInlineScopesNumbers", "useOldClassFilesReading", "getUseOldClassFilesReading", "setUseOldClassFilesReading", "useTypeTable", "getUseTypeTable", "setUseTypeTable", "validateBytecode", "getValidateBytecode", "setValidateBytecode", "valueClasses", "getValueClasses", "setValueClasses", "whenExpressionsGeneration", "getWhenExpressionsGeneration", "setWhenExpressionsGeneration", ModuleXmlParser.CLASSPATH, "getClasspath", "setClasspath", "destination", "getDestination", "setDestination", "expression", "getExpression", "setExpression", "includeRuntime", "getIncludeRuntime", "setIncludeRuntime", "javaParameters", "getJavaParameters", "setJavaParameters", "jdkHome", "getJdkHome", "setJdkHome", "jvmDefaultStable", "getJvmDefaultStable", "setJvmDefaultStable", "jvmTarget", "getJvmTarget", "setJvmTarget", "moduleName", "getModuleName", "setModuleName", "noJdk", "getNoJdk", "setNoJdk", "noReflect", "getNoReflect", "setNoReflect", "noStdlib", "getNoStdlib", "setNoStdlib", "scriptTemplates", "getScriptTemplates", "setScriptTemplates", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JVMCompilerArguments extends CommonCompilerArguments {

    @Argument(description = "When using unstable compiler features such as FIR, use 'stable' to mark generated class files as stable\nto prevent diagnostics from being reported when using stable compilers at the call site.\nWhen using the JVM IR backend, conversely, use 'unstable' to mark generated class files as unstable\nto force diagnostics to be reported.", value = "-Xabi-stability", valueDescription = "{stable|unstable}")
    private String abiStability;

    @Argument(description = "Allow the set of source files to be empty.", value = "-Xallow-no-source-files")
    private boolean allowNoSourceFiles;

    @Argument(description = "Do not report errors on classes in dependencies that were compiled by an unstable version of the Kotlin compiler.", value = "-Xallow-unstable-dependencies")
    private boolean allowUnstableDependencies;

    @Argument(description = "Write annotations on declarations into the metadata (in addition to the JVM bytecode), and read annotations from the metadata if they are present.", value = "-Xannotations-in-metadata")
    @Enables(feature = LanguageFeature.AnnotationsInMetadata)
    private boolean annotationsInMetadata;

    @Argument(deprecatedName = "-module", description = "Path to the .xml build file to compile.", value = "-Xbuild-file", valueDescription = "<path>")
    private String buildFile;

    @Argument(description = "List of directories and JAR/ZIP archives to search for user class files.", shortName = "-cp", value = "-classpath", valueDescription = "<path>")
    private String classpath;

    @Argument(description = "Compile expressions and unrecognized scripts passed with the -script argument as scripts with the given filename extension.", value = "-Xdefault-script-extension", valueDescription = "<script filename extension>")
    private String defaultScriptExtension;

    @Argument(description = "Destination for generated class files.", value = "-d", valueDescription = "<directory|jar>")
    private String destination;

    @Argument(description = "Disable standard Kotlin scripting support.", value = "-Xdisable-standard-script")
    private boolean disableStandardScript;

    @Argument(description = "When using the IR backend, do not clear BindingContext between 'psi2ir' and lowerings.", value = "-Xir-do-not-clear-binding-context")
    private boolean doNotClearBindingContext;

    @Argument(description = "Emit JVM type annotations in bytecode.", value = "-Xemit-jvm-type-annotations")
    private boolean emitJvmTypeAnnotations;

    @Argument(description = "Enable debug mode for compilation.\nCurrently this includes spilling all variables in a suspending context regardless of whether they are alive.\nIf API Level >= 2.2 -- no-op.", value = "-Xdebug")
    private boolean enableDebugMode;

    @Argument(description = "Allow using Java features that are in the preview phase.\nThis works like '--enable-preview' in Java. All class files are marked as compiled with preview features, meaning it won't be possible to use them in release environments.", value = "-Xjvm-enable-preview")
    private boolean enableJvmPreview;

    @Argument(description = "Enhance not-null-annotated type parameter types to definitely-non-nullable types ('@NotNull T' => 'T & Any').", value = "-Xenhance-type-parameter-types-to-def-not-null")
    @Enables(feature = LanguageFeature.ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated)
    private boolean enhanceTypeParameterTypesToDefNotNull;

    @Argument(description = "Generate additional linenumber instruction for compiler-generated code\ninside suspend functions and lambdas to distinguish them from user code by debugger.", value = "-Xenhanced-coroutines-debugging")
    private boolean enhancedCoroutinesDebugging;

    @Argument(description = "Evaluate the given string as a Kotlin script.", shortName = "-e", value = "-expression")
    private String expression;

    @Argument(description = "Include the Kotlin runtime in the resulting JAR.", value = "-include-runtime")
    private boolean includeRuntime;

    @Argument(description = "Allow using 'invokedynamic' for lambda expressions with annotations", value = "-Xindy-allow-annotated-lambdas")
    private Boolean indyAllowAnnotatedLambdas;

    @Argument(description = "Compile multifile classes as a hierarchy of parts and a facade.", value = "-Xmultifile-parts-inherit")
    private boolean inheritMultifileParts;

    @Argument(description = "Paths to Java 9+ modules.", value = "-Xmodule-path", valueDescription = "<path>")
    private String javaModulePath;

    @Argument(description = "Package prefix for Java files.", value = "-Xjava-package-prefix")
    private String javaPackagePrefix;

    @Argument(description = "Generate metadata for Java 1.8 reflection on method parameters.", value = "-java-parameters")
    private boolean javaParameters;

    @Argument(description = "Include a custom JDK from the specified location in the classpath instead of the default 'JAVA_HOME'.", value = "-jdk-home", valueDescription = "<path>")
    private String jdkHome;

    @Argument(description = "Compile against the specified JDK API version, similarly to javac's '-release'. This requires JDK 9 or newer.\nThe supported versions depend on the JDK used; for JDK 17+, the supported versions are 1.8 and 9–26.\nThis also sets the value of '-jvm-target' to be equal to the selected JDK version.", value = "-Xjdk-release", valueDescription = "<version>")
    private String jdkRelease;

    @Argument(description = "Specify the behavior of 'jspecify' annotations.\nThe default value is 'strict'.", value = "-Xjspecify-annotations", valueDescription = "ignore|strict|warn")
    private String jspecifyAnnotations;

    @Argument(description = "This option is deprecated. Migrate to -jvm-default as follows:\n-Xjvm-default=disable            -> -jvm-default=disable\n-Xjvm-default=all-compatibility  -> -jvm-default=enable\n-Xjvm-default=all                -> -jvm-default=no-compatibility", value = "-Xjvm-default", valueDescription = "{all|all-compatibility|disable}")
    private String jvmDefault;

    @Argument(description = "Emit JVM default methods for interface declarations with bodies. The default is 'enable'.\n-jvm-default=enable              Generate default methods for non-abstract interface declarations, as well as 'DefaultImpls' classes with\n                                 static methods for compatibility with code compiled in the 'disable' mode.\n                                 This is the default behavior since language version 2.2.\n-jvm-default=no-compatibility    Generate default methods for non-abstract interface declarations. Do not generate 'DefaultImpls' classes.\n-jvm-default=disable             Do not generate JVM default methods. This is the default behavior up to language version 2.1.", value = "-jvm-default", valueDescription = "{enable|no-compatibility|disable}")
    private String jvmDefaultStable;

    @Argument(description = "Expose inline classes and functions, accepting and returning them, to Java.", value = "-Xjvm-expose-boxed")
    private boolean jvmExposeBoxed;

    @Argument(description = "The target version of the generated JVM bytecode (1.8 and 9–26), with 1.8 as the default.", value = "-jvm-target", valueDescription = "<version>")
    private String jvmTarget;

    @Argument(description = "Paths to cross-platform libraries in the .klib format.", value = "-Xklib", valueDescription = "<path>")
    private String klibLibraries;

    @Argument(description = "Select the code generation scheme for lambdas.\n-Xlambdas=indy                  Generate lambdas using 'invokedynamic' with 'LambdaMetafactory.metafactory'.\n                                A lambda object created using 'LambdaMetafactory.metafactory' will have a different 'toString()'.\n-Xlambdas=class                 Generate lambdas as explicit classes.\nThe default value is 'indy' if language version is 2.0+, and 'class' otherwise.", value = "-Xlambdas", valueDescription = "{class|indy}")
    private String lambdas;

    @Argument(description = "Link JVM IR symbols via signatures instead of descriptors.\nThis mode is slower, but it can be useful for troubleshooting problems with the JVM IR backend.\nThis option is deprecated and will be deleted in future versions.\nIt has no effect when -language-version is 2.0 or higher.", value = "-Xlink-via-signatures")
    private boolean linkViaSignatures;

    @Argument(description = "Name of the generated '.kotlin_module' file.", value = "-module-name", valueDescription = "<name>")
    private String moduleName;

    @Argument(description = "Don't generate not-null assertions for arguments of platform types.", value = "-Xno-call-assertions")
    private boolean noCallAssertions;

    @Argument(description = "Don't automatically include the Java runtime in the classpath.", value = "-no-jdk")
    private boolean noJdk;

    @Argument(description = "Don't generate Java 1.8+ targets for Kotlin annotation classes.", value = "-Xno-new-java-annotation-targets")
    private boolean noNewJavaAnnotationTargets;

    @Argument(description = "Disable optimizations.", value = "-Xno-optimize")
    private boolean noOptimize;

    @Argument(description = "Don't generate not-null assertions on parameters of methods accessible from Java.", value = "-Xno-param-assertions")
    private boolean noParamAssertions;

    @Argument(description = "Don't generate not-null assertions for extension receiver arguments of platform types.", value = "-Xno-receiver-assertions")
    private boolean noReceiverAssertions;

    @Argument(description = "Don't automatically include the Kotlin reflection dependency in the classpath.", value = "-no-reflect")
    private boolean noReflect;

    @Argument(description = "Don't reset jar entry timestamps to a fixed date.", value = "-Xno-reset-jar-timestamps")
    private boolean noResetJarTimestamps;

    @Argument(description = "Don't generate the '@kotlin.jvm.internal.SourceDebugExtension' annotation with an SMAP copy on classes.", value = "-Xno-source-debug-extension")
    private boolean noSourceDebugExtension;

    @Argument(description = "Don't automatically include the Kotlin/JVM stdlib and Kotlin reflection dependencies in the classpath.", value = "-no-stdlib")
    private boolean noStdlib;

    @Argument(description = "Use pre-1.4 exception types instead of 'java.lang.NPE' in null checks. See KT-22275 for more details.", value = "-Xno-unified-null-checks")
    private boolean noUnifiedNullChecks;

    @Argument(description = "Output builtins metadata as .kotlin_builtins files", value = "-Xoutput-builtins-metadata")
    private boolean outputBuiltinsMetadata;

    @Argument(description = "Debug option: Run the compiler with the async profiler and save snapshots to `outputDir`; `command` is passed to the async profiler on start.\n`profilerPath` is the path to libasyncProfiler.so; async-profiler.jar should be on the compiler classpath.\nIf it's not on the classpath, the compiler will attempt to load async-profiler.jar from the containing directory of profilerPath. \nIndividual parameter values are separated by the system path separator.\nExample (Unix/Linux): -Xprofile=<PATH_TO_ASYNC_PROFILER>/async-profiler/build/libasyncProfiler.so:event=cpu,interval=1ms,threads,start:<SNAPSHOT_DIR_PATH>\nExample (Windows): -Xprofile=<PATH_TO_ASYNC_PROFILER>\\async-profiler\\build\\libasyncProfiler.so;event=cpu,interval=1ms,threads,start;<SNAPSHOT_DIR_PATH>", value = "-Xprofile", valueDescription = "<profilerPath:command:outputDir>")
    private String profileCompilerCommand;

    @Argument(description = "Select the code generation scheme for SAM conversions.\n-Xsam-conversions=indy          Generate SAM conversions using 'invokedynamic' with 'LambdaMetafactory.metafactory'.\n-Xsam-conversions=class         Generate SAM conversions as explicit classes.\nThe default value is 'indy'.", value = "-Xsam-conversions", valueDescription = "{class|indy}")
    private String samConversions;

    @Argument(description = "Transform '(' and ')' in method names to some other character sequence.\nThis mode can BREAK BINARY COMPATIBILITY and should only be used as a workaround for\nproblems with parentheses in identifiers on certain platforms.", value = "-Xsanitize-parentheses")
    private boolean sanitizeParentheses;

    @Argument(description = "Generate metadata with strict version semantics (see the KDoc entry on 'Metadata.extraInt').", value = "-Xgenerate-strict-metadata-version")
    private boolean strictMetadataVersionSemantics;

    @Argument(description = "Select the code generation scheme for string concatenation:\n-Xstring-concat=indy-with-constants  Concatenate strings using 'invokedynamic' and 'makeConcatWithConstants'. This requires '-jvm-target 9' or greater.\n-Xstring-concat=indy                 Concatenate strings using 'invokedynamic' and 'makeConcat'. This requires '-jvm-target 9' or greater.\n-Xstring-concat=inline               Concatenate strings using 'StringBuilder'\ndefault: 'indy-with-constants' for JVM targets 9 or greater, 'inline' otherwise.", value = "-Xstring-concat", valueDescription = "{indy-with-constants|indy|inline}")
    private String stringConcat;

    @Argument(description = "Specify the behavior for Checker Framework 'compatqual' annotations ('NullableDecl'/'NonNullDecl').\nThe default value is 'enable'.", value = "-Xsupport-compatqual-checker-framework-annotations", valueDescription = "enable|disable")
    private String supportCompatqualCheckerFrameworkAnnotations;

    @Argument(description = "Suppress warnings about deprecated JVM target versions.\nThis option has no effect and will be deleted in a future version.", value = "-Xsuppress-deprecated-jvm-target-warning")
    private boolean suppressDeprecatedJvmTargetWarning;

    @Argument(description = "Suppress the \"cannot access built-in declaration\" error (useful with '-no-stdlib').", value = "-Xsuppress-missing-builtins-error")
    private boolean suppressMissingBuiltinsError;

    @Argument(description = "Enable strict mode for improvements to type enhancement for loaded Java types based on nullability annotations,\nincluding the ability to read type-use annotations from class files.\nSee KT-45671 for more details.", value = "-Xtype-enhancement-improvements-strict-mode")
    @Enables(feature = LanguageFeature.TypeEnhancementImprovementsInStrictMode)
    private boolean typeEnhancementImprovementsInStrictMode;

    @Argument(description = "Use the fast implementation of Jar FS. This may speed up compilation time, but it is experimental.", value = "-Xuse-fast-jar-file-system")
    private Boolean useFastJarFileSystem;

    @Argument(description = "Use inline scopes numbers for inline marker variables.", value = "-Xuse-inline-scopes-numbers")
    private boolean useInlineScopesNumbers;

    @Argument(description = "Use the old implementation for reading class files. This may slow down the compilation and cause problems with Groovy interop.\nThis can be used in the event of problems with the new implementation.", value = "-Xuse-old-class-files-reading")
    private boolean useOldClassFilesReading;

    @Argument(description = "Use the scheme for inline class mangling from version 1.4 instead of the one from 1.4.30.", value = "-Xuse-14-inline-classes-mangling-scheme")
    private boolean useOldInlineClassesManglingScheme;

    @Argument(description = "Use a type table in metadata serialization.", value = "-Xuse-type-table")
    private boolean useTypeTable;

    @Argument(description = "Validate generated JVM bytecode before and after optimizations.", value = "-Xvalidate-bytecode")
    private boolean validateBytecode;

    @Argument(description = "Enable experimental value classes.", value = "-Xvalue-classes")
    @Enables(feature = LanguageFeature.JvmInlineMultiFieldValueClasses)
    private boolean valueClasses;

    @Argument(description = "Select the code generation scheme for type-checking 'when' expressions:\n-Xwhen-expressions=indy         Generate type-checking 'when' expressions using 'invokedynamic' with 'SwitchBootstraps.typeSwitch(..)' and \n                                following 'tableswitch' or 'lookupswitch'. This requires '-jvm-target 21' or greater.\n-Xwhen-expressions=inline       Generate type-checking 'when' expressions as a chain of type checks.\nThe default value is 'inline'.", value = "-Xwhen-expressions", valueDescription = "{indy|inline}")
    private String whenExpressionsGeneration;

    @Argument(description = "Root modules to resolve in addition to the initial modules, or all modules on the module path if <module> is ALL-MODULE-PATH.", value = "-Xadd-modules", valueDescription = "<module[,]>")
    private String[] additionalJavaModules = new String[0];

    @Argument(description = "'kotlin.assert' call behavior:\n-Xassertions=always-enable:  enable, ignore JVM assertion settings;\n-Xassertions=always-disable: disable, ignore JVM assertion settings;\n-Xassertions=jvm:            enable, depend on JVM assertion settings;\n-Xassertions=legacy:         calculate the condition on each call, the behavior depends on JVM assertion settings in the kotlin package;\ndefault: legacy", value = "-Xassertions", valueDescription = "{always-enable|always-disable|jvm|legacy}")
    private String assertionsMode = "legacy";

    @Argument(description = "Run codegen phase in N parallel threads.\n0 means use one thread per processor core.\nThe default value is 1.", value = "-Xbackend-threads", valueDescription = "<N>")
    private String backendThreads = "1";

    @Argument(description = "Paths to output directories for friend modules (modules whose internals should be visible).", value = "-Xfriend-paths", valueDescription = "<path>")
    private String[] friendPaths = new String[0];

    @Argument(description = "Do not copy these annotations to the bridge methods from their targets.", value = "-Xignored-annotations-for-bridges", valueDescription = "<fq.name>|*")
    private String[] ignoredAnnotationsForBridges = new String[0];

    @Argument(description = "Paths to directories with Java source files.", value = "-Xjava-source-roots", valueDescription = "<path>")
    private String[] javaSourceRoots = new String[0];

    @Argument(deprecatedName = "-Xjsr305-annotations", description = "Specify the behavior of 'JSR-305' nullability annotations:\n-Xjsr305={ignore/strict/warn}                   global (all non-@UnderMigration annotations)\n-Xjsr305=under-migration:{ignore/strict/warn}   all @UnderMigration annotations\n-Xjsr305=@<fq.name>:{ignore/strict/warn}        annotation with the given fully qualified class name\nModes:\n* ignore\n* strict (experimental; treat like other supported nullability annotations)\n* warn (report a warning)", value = "-Xjsr305", valueDescription = "{ignore/strict/warn}|under-migration:{ignore/strict/warn}|@<fq.name>:{ignore/strict/warn}")
    private String[] jsr305 = new String[0];

    @Argument(description = "Specify the behavior for specific Java nullability annotations (provided with fully qualified package name).\nModes:\n* ignore\n* strict\n* warn (report a warning)", value = "-Xnullability-annotations", valueDescription = "@<fq.name>:{ignore/strict/warn}")
    private String[] nullabilityAnnotations = new String[0];

    @Argument(description = "Set the script resolver environment in key-value pairs (the value can be quoted and escaped).", value = "-Xscript-resolver-environment", valueDescription = "<key=value[,]>")
    private String[] scriptResolverEnvironment = new String[0];

    @Argument(description = "Script definition template classes.", value = "-script-templates", valueDescription = "<fully qualified class name[,]>")
    private String[] scriptTemplates = new String[0];
    private final transient CommonCompilerArgumentsConfigurator configurator = new K2JVMCompilerArgumentsConfigurator();

    @Deprecated(message = "This flag is deprecated. Use `-jvm-default` instead")
    public static /* synthetic */ void getJvmDefault$annotations() {
    }

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getLinkViaSignatures$annotations() {
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() {
        return K2JVMCompilerArgumentsCopyGeneratedKt.copyK2JVMCompilerArguments(this, new K2JVMCompilerArguments());
    }

    public final String getAbiStability() {
        return this.abiStability;
    }

    public final String[] getAdditionalJavaModules() {
        return this.additionalJavaModules;
    }

    public final boolean getAllowNoSourceFiles() {
        return this.allowNoSourceFiles;
    }

    public final boolean getAllowUnstableDependencies() {
        return this.allowUnstableDependencies;
    }

    public final boolean getAnnotationsInMetadata() {
        return this.annotationsInMetadata;
    }

    public final String getAssertionsMode() {
        return this.assertionsMode;
    }

    public final String getBackendThreads() {
        return this.backendThreads;
    }

    public final String getBuildFile() {
        return this.buildFile;
    }

    public final String getClasspath() {
        return this.classpath;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final String getDefaultScriptExtension() {
        return this.defaultScriptExtension;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final boolean getDisableStandardScript() {
        return this.disableStandardScript;
    }

    public final boolean getDoNotClearBindingContext() {
        return this.doNotClearBindingContext;
    }

    public final boolean getEmitJvmTypeAnnotations() {
        return this.emitJvmTypeAnnotations;
    }

    public final boolean getEnableDebugMode() {
        return this.enableDebugMode;
    }

    public final boolean getEnableJvmPreview() {
        return this.enableJvmPreview;
    }

    public final boolean getEnhanceTypeParameterTypesToDefNotNull() {
        return this.enhanceTypeParameterTypesToDefNotNull;
    }

    public final boolean getEnhancedCoroutinesDebugging() {
        return this.enhancedCoroutinesDebugging;
    }

    public final String getExpression() {
        return this.expression;
    }

    public final String[] getFriendPaths() {
        return this.friendPaths;
    }

    public final String[] getIgnoredAnnotationsForBridges() {
        return this.ignoredAnnotationsForBridges;
    }

    public final boolean getIncludeRuntime() {
        return this.includeRuntime;
    }

    public final Boolean getIndyAllowAnnotatedLambdas() {
        return this.indyAllowAnnotatedLambdas;
    }

    public final boolean getInheritMultifileParts() {
        return this.inheritMultifileParts;
    }

    public final String getJavaModulePath() {
        return this.javaModulePath;
    }

    public final String getJavaPackagePrefix() {
        return this.javaPackagePrefix;
    }

    public final boolean getJavaParameters() {
        return this.javaParameters;
    }

    public final String[] getJavaSourceRoots() {
        return this.javaSourceRoots;
    }

    public final String getJdkHome() {
        return this.jdkHome;
    }

    public final String getJdkRelease() {
        return this.jdkRelease;
    }

    public final String getJspecifyAnnotations() {
        return this.jspecifyAnnotations;
    }

    public final String[] getJsr305() {
        return this.jsr305;
    }

    public final String getJvmDefault() {
        return this.jvmDefault;
    }

    public final String getJvmDefaultStable() {
        return this.jvmDefaultStable;
    }

    public final boolean getJvmExposeBoxed() {
        return this.jvmExposeBoxed;
    }

    public final String getJvmTarget() {
        return this.jvmTarget;
    }

    public final String getKlibLibraries() {
        return this.klibLibraries;
    }

    public final String getLambdas() {
        return this.lambdas;
    }

    public final boolean getLinkViaSignatures() {
        return this.linkViaSignatures;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final boolean getNoCallAssertions() {
        return this.noCallAssertions;
    }

    public final boolean getNoJdk() {
        return this.noJdk;
    }

    public final boolean getNoNewJavaAnnotationTargets() {
        return this.noNewJavaAnnotationTargets;
    }

    public final boolean getNoOptimize() {
        return this.noOptimize;
    }

    public final boolean getNoParamAssertions() {
        return this.noParamAssertions;
    }

    public final boolean getNoReceiverAssertions() {
        return this.noReceiverAssertions;
    }

    public final boolean getNoReflect() {
        return this.noReflect;
    }

    public final boolean getNoResetJarTimestamps() {
        return this.noResetJarTimestamps;
    }

    public final boolean getNoSourceDebugExtension() {
        return this.noSourceDebugExtension;
    }

    public final boolean getNoStdlib() {
        return this.noStdlib;
    }

    public final boolean getNoUnifiedNullChecks() {
        return this.noUnifiedNullChecks;
    }

    public final String[] getNullabilityAnnotations() {
        return this.nullabilityAnnotations;
    }

    public final boolean getOutputBuiltinsMetadata() {
        return this.outputBuiltinsMetadata;
    }

    public final String getProfileCompilerCommand() {
        return this.profileCompilerCommand;
    }

    public final String getSamConversions() {
        return this.samConversions;
    }

    public final boolean getSanitizeParentheses() {
        return this.sanitizeParentheses;
    }

    public final String[] getScriptResolverEnvironment() {
        return this.scriptResolverEnvironment;
    }

    public final String[] getScriptTemplates() {
        return this.scriptTemplates;
    }

    public final boolean getStrictMetadataVersionSemantics() {
        return this.strictMetadataVersionSemantics;
    }

    public final String getStringConcat() {
        return this.stringConcat;
    }

    public final String getSupportCompatqualCheckerFrameworkAnnotations() {
        return this.supportCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getSuppressDeprecatedJvmTargetWarning() {
        return this.suppressDeprecatedJvmTargetWarning;
    }

    public final boolean getSuppressMissingBuiltinsError() {
        return this.suppressMissingBuiltinsError;
    }

    public final boolean getTypeEnhancementImprovementsInStrictMode() {
        return this.typeEnhancementImprovementsInStrictMode;
    }

    public final Boolean getUseFastJarFileSystem() {
        return this.useFastJarFileSystem;
    }

    public final boolean getUseInlineScopesNumbers() {
        return this.useInlineScopesNumbers;
    }

    public final boolean getUseOldClassFilesReading() {
        return this.useOldClassFilesReading;
    }

    public final boolean getUseOldInlineClassesManglingScheme() {
        return this.useOldInlineClassesManglingScheme;
    }

    public final boolean getUseTypeTable() {
        return this.useTypeTable;
    }

    public final boolean getValidateBytecode() {
        return this.validateBytecode;
    }

    public final boolean getValueClasses() {
        return this.valueClasses;
    }

    public final String getWhenExpressionsGeneration() {
        return this.whenExpressionsGeneration;
    }

    public final void setAbiStability(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.abiStability = str;
    }

    public final void setAdditionalJavaModules(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.additionalJavaModules = strArr;
    }

    public final void setAllowNoSourceFiles(boolean z) {
        checkFrozen();
        this.allowNoSourceFiles = z;
    }

    public final void setAllowUnstableDependencies(boolean z) {
        checkFrozen();
        this.allowUnstableDependencies = z;
    }

    public final void setAnnotationsInMetadata(boolean z) {
        checkFrozen();
        this.annotationsInMetadata = z;
    }

    public final void setAssertionsMode(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = "legacy";
        }
        this.assertionsMode = str;
    }

    public final void setBackendThreads(String str) {
        str.getClass();
        checkFrozen();
        this.backendThreads = str;
    }

    public final void setBuildFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.buildFile = str;
    }

    public final void setClasspath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.classpath = str;
    }

    public final void setDefaultScriptExtension(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.defaultScriptExtension = str;
    }

    public final void setDestination(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.destination = str;
    }

    public final void setDisableStandardScript(boolean z) {
        checkFrozen();
        this.disableStandardScript = z;
    }

    public final void setDoNotClearBindingContext(boolean z) {
        checkFrozen();
        this.doNotClearBindingContext = z;
    }

    public final void setEmitJvmTypeAnnotations(boolean z) {
        checkFrozen();
        this.emitJvmTypeAnnotations = z;
    }

    public final void setEnableDebugMode(boolean z) {
        checkFrozen();
        this.enableDebugMode = z;
    }

    public final void setEnableJvmPreview(boolean z) {
        checkFrozen();
        this.enableJvmPreview = z;
    }

    public final void setEnhanceTypeParameterTypesToDefNotNull(boolean z) {
        checkFrozen();
        this.enhanceTypeParameterTypesToDefNotNull = z;
    }

    public final void setEnhancedCoroutinesDebugging(boolean z) {
        checkFrozen();
        this.enhancedCoroutinesDebugging = z;
    }

    public final void setExpression(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.expression = str;
    }

    public final void setFriendPaths(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.friendPaths = strArr;
    }

    public final void setIgnoredAnnotationsForBridges(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.ignoredAnnotationsForBridges = strArr;
    }

    public final void setIncludeRuntime(boolean z) {
        checkFrozen();
        this.includeRuntime = z;
    }

    public final void setIndyAllowAnnotatedLambdas(Boolean bool) {
        checkFrozen();
        this.indyAllowAnnotatedLambdas = bool;
    }

    public final void setInheritMultifileParts(boolean z) {
        checkFrozen();
        this.inheritMultifileParts = z;
    }

    public final void setJavaModulePath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.javaModulePath = str;
    }

    public final void setJavaPackagePrefix(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.javaPackagePrefix = str;
    }

    public final void setJavaParameters(boolean z) {
        checkFrozen();
        this.javaParameters = z;
    }

    public final void setJavaSourceRoots(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.javaSourceRoots = strArr;
    }

    public final void setJdkHome(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jdkHome = str;
    }

    public final void setJdkRelease(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jdkRelease = str;
    }

    public final void setJspecifyAnnotations(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jspecifyAnnotations = str;
    }

    public final void setJsr305(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.jsr305 = strArr;
    }

    public final void setJvmDefault(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jvmDefault = str;
    }

    public final void setJvmDefaultStable(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jvmDefaultStable = str;
    }

    public final void setJvmExposeBoxed(boolean z) {
        checkFrozen();
        this.jvmExposeBoxed = z;
    }

    public final void setJvmTarget(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jvmTarget = str;
    }

    public final void setKlibLibraries(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.klibLibraries = str;
    }

    public final void setLambdas(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.lambdas = str;
    }

    public final void setLinkViaSignatures(boolean z) {
        checkFrozen();
        this.linkViaSignatures = z;
    }

    public final void setModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleName = str;
    }

    public final void setNoCallAssertions(boolean z) {
        checkFrozen();
        this.noCallAssertions = z;
    }

    public final void setNoJdk(boolean z) {
        checkFrozen();
        this.noJdk = z;
    }

    public final void setNoNewJavaAnnotationTargets(boolean z) {
        checkFrozen();
        this.noNewJavaAnnotationTargets = z;
    }

    public final void setNoOptimize(boolean z) {
        checkFrozen();
        this.noOptimize = z;
    }

    public final void setNoParamAssertions(boolean z) {
        checkFrozen();
        this.noParamAssertions = z;
    }

    public final void setNoReceiverAssertions(boolean z) {
        checkFrozen();
        this.noReceiverAssertions = z;
    }

    public final void setNoReflect(boolean z) {
        checkFrozen();
        this.noReflect = z;
    }

    public final void setNoResetJarTimestamps(boolean z) {
        checkFrozen();
        this.noResetJarTimestamps = z;
    }

    public final void setNoSourceDebugExtension(boolean z) {
        checkFrozen();
        this.noSourceDebugExtension = z;
    }

    public final void setNoStdlib(boolean z) {
        checkFrozen();
        this.noStdlib = z;
    }

    public final void setNoUnifiedNullChecks(boolean z) {
        checkFrozen();
        this.noUnifiedNullChecks = z;
    }

    public final void setNullabilityAnnotations(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.nullabilityAnnotations = strArr;
    }

    public final void setOutputBuiltinsMetadata(boolean z) {
        checkFrozen();
        this.outputBuiltinsMetadata = z;
    }

    public final void setProfileCompilerCommand(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.profileCompilerCommand = str;
    }

    public final void setSamConversions(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.samConversions = str;
    }

    public final void setSanitizeParentheses(boolean z) {
        checkFrozen();
        this.sanitizeParentheses = z;
    }

    public final void setScriptResolverEnvironment(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.scriptResolverEnvironment = strArr;
    }

    public final void setScriptTemplates(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.scriptTemplates = strArr;
    }

    public final void setStrictMetadataVersionSemantics(boolean z) {
        checkFrozen();
        this.strictMetadataVersionSemantics = z;
    }

    public final void setStringConcat(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.stringConcat = str;
    }

    public final void setSupportCompatqualCheckerFrameworkAnnotations(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.supportCompatqualCheckerFrameworkAnnotations = str;
    }

    public final void setSuppressDeprecatedJvmTargetWarning(boolean z) {
        checkFrozen();
        this.suppressDeprecatedJvmTargetWarning = z;
    }

    public final void setSuppressMissingBuiltinsError(boolean z) {
        checkFrozen();
        this.suppressMissingBuiltinsError = z;
    }

    public final void setTypeEnhancementImprovementsInStrictMode(boolean z) {
        checkFrozen();
        this.typeEnhancementImprovementsInStrictMode = z;
    }

    public final void setUseFastJarFileSystem(Boolean bool) {
        checkFrozen();
        this.useFastJarFileSystem = bool;
    }

    public final void setUseInlineScopesNumbers(boolean z) {
        checkFrozen();
        this.useInlineScopesNumbers = z;
    }

    public final void setUseOldClassFilesReading(boolean z) {
        checkFrozen();
        this.useOldClassFilesReading = z;
    }

    public final void setUseOldInlineClassesManglingScheme(boolean z) {
        checkFrozen();
        this.useOldInlineClassesManglingScheme = z;
    }

    public final void setUseTypeTable(boolean z) {
        checkFrozen();
        this.useTypeTable = z;
    }

    public final void setValidateBytecode(boolean z) {
        checkFrozen();
        this.validateBytecode = z;
    }

    public final void setValueClasses(boolean z) {
        checkFrozen();
        this.valueClasses = z;
    }

    public final void setWhenExpressionsGeneration(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.whenExpressionsGeneration = str;
    }
}
