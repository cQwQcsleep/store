package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0003\bê\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0098\u0002\u001a\u00030\u0099\u0002H\u0014R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR*\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR*\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR4\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R&\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\b\"\u0004\b\u001d\u0010\nR4\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019R*\u0010!\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\nR4\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010\u0019R4\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R&\u0010+\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R&\u00100\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R*\u00103\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\nR&\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\b\"\u0004\b8\u0010\nR4\u00109\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b:\u0010\u0017\"\u0004\b;\u0010\u0019R*\u0010<\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\b\"\u0004\b>\u0010\nR*\u0010?\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\b\"\u0004\bA\u0010\nR*\u0010B\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\b\"\u0004\bD\u0010\nR&\u0010E\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010-\"\u0004\bG\u0010/R4\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\bI\u0010\u0017\"\u0004\bJ\u0010\u0019R*\u0010K\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\b\"\u0004\bM\u0010\nR&\u0010N\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010-\"\u0004\bP\u0010/R4\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\bR\u0010\u0017\"\u0004\bS\u0010\u0019R4\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\bU\u0010\u0017\"\u0004\bV\u0010\u0019R*\u0010W\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\b\"\u0004\bY\u0010\nR,\u0010Z\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b[\u0010\u0003\u001a\u0004\b\\\u0010-\"\u0004\b]\u0010/R*\u0010^\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\b\"\u0004\b`\u0010\nR*\u0010a\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\b\"\u0004\bc\u0010\nR*\u0010d\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\b\"\u0004\bf\u0010\nR4\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\bh\u0010\u0017\"\u0004\bi\u0010\u0019R*\u0010j\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\b\"\u0004\bl\u0010\nR*\u0010m\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\b\"\u0004\bo\u0010\nR*\u0010p\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\b\"\u0004\br\u0010\nR*\u0010s\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010\b\"\u0004\bu\u0010\nR*\u0010v\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010\b\"\u0004\bx\u0010\nR&\u0010y\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010-\"\u0004\b{\u0010/R4\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b}\u0010\u0017\"\u0004\b~\u0010\u0019R(\u0010\u007f\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010-\"\u0005\b\u0081\u0001\u0010/R)\u0010\u0082\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0001\u0010-\"\u0005\b\u0084\u0001\u0010/R7\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\b\u0086\u0001\u0010\u0017\"\u0005\b\u0087\u0001\u0010\u0019R7\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\b\u0089\u0001\u0010\u0017\"\u0005\b\u008a\u0001\u0010\u0019R-\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010\b\"\u0005\b\u008d\u0001\u0010\nR)\u0010\u008e\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0001\u0010-\"\u0005\b\u0090\u0001\u0010/R)\u0010\u0091\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010-\"\u0005\b\u0093\u0001\u0010/R)\u0010\u0094\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0001\u0010-\"\u0005\b\u0096\u0001\u0010/R)\u0010\u0097\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0098\u0001\u0010-\"\u0005\b\u0099\u0001\u0010/R-\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010\b\"\u0005\b\u009c\u0001\u0010\nR7\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\b\u009e\u0001\u0010\u0017\"\u0005\b\u009f\u0001\u0010\u0019R-\u0010 \u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010\b\"\u0005\b¢\u0001\u0010\nR-\u0010£\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u0010\b\"\u0005\b¥\u0001\u0010\nR7\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\b§\u0001\u0010\u0017\"\u0005\b¨\u0001\u0010\u0019R-\u0010©\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bª\u0001\u0010\b\"\u0005\b«\u0001\u0010\nR-\u0010¬\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\b\"\u0005\b®\u0001\u0010\nR)\u0010¯\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b°\u0001\u0010-\"\u0005\b±\u0001\u0010/R-\u0010²\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b³\u0001\u0010\b\"\u0005\b´\u0001\u0010\nR)\u0010µ\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¶\u0001\u0010-\"\u0005\b·\u0001\u0010/R-\u0010¸\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¹\u0001\u0010\b\"\u0005\bº\u0001\u0010\nR-\u0010»\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¼\u0001\u0010\b\"\u0005\b½\u0001\u0010\nR-\u0010¾\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¿\u0001\u0010\b\"\u0005\bÀ\u0001\u0010\nR-\u0010Á\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÂ\u0001\u0010\b\"\u0005\bÃ\u0001\u0010\nR)\u0010Ä\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÅ\u0001\u0010-\"\u0005\bÆ\u0001\u0010/R-\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÈ\u0001\u0010\b\"\u0005\bÉ\u0001\u0010\nR-\u0010Ê\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bË\u0001\u0010\b\"\u0005\bÌ\u0001\u0010\nR)\u0010Í\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÎ\u0001\u0010-\"\u0005\bÏ\u0001\u0010/R)\u0010Ð\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÑ\u0001\u0010-\"\u0005\bÒ\u0001\u0010/R)\u0010Ó\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÔ\u0001\u0010-\"\u0005\bÕ\u0001\u0010/R)\u0010Ö\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b×\u0001\u0010-\"\u0005\bØ\u0001\u0010/R7\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\bÚ\u0001\u0010\u0017\"\u0005\bÛ\u0001\u0010\u0019R7\u0010Ü\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\bÝ\u0001\u0010\u0017\"\u0005\bÞ\u0001\u0010\u0019R4\u0010ß\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0017\n\u0000\u0012\u0005\bà\u0001\u0010\u0003\u001a\u0005\bá\u0001\u0010\b\"\u0005\bâ\u0001\u0010\nR7\u0010ã\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\bä\u0001\u0010\u0017\"\u0005\bå\u0001\u0010\u0019R7\u0010æ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\bç\u0001\u0010\u0017\"\u0005\bè\u0001\u0010\u0019R)\u0010é\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bê\u0001\u0010-\"\u0005\bë\u0001\u0010/R-\u0010ì\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bí\u0001\u0010\b\"\u0005\bî\u0001\u0010\nR-\u0010ï\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bð\u0001\u0010\b\"\u0005\bñ\u0001\u0010\nR-\u0010ò\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bó\u0001\u0010\b\"\u0005\bô\u0001\u0010\nR7\u0010õ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u001a\u001a\u0005\bö\u0001\u0010\u0017\"\u0005\b÷\u0001\u0010\u0019R)\u0010ø\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bù\u0001\u0010-\"\u0005\bú\u0001\u0010/R0\u0010û\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0017\n\u0000\u0012\u0005\bü\u0001\u0010\u0003\u001a\u0005\bý\u0001\u0010-\"\u0005\bþ\u0001\u0010/R)\u0010ÿ\u0001\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0002\u0010-\"\u0005\b\u0081\u0002\u0010/R)\u0010\u0082\u0002\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0002\u0010-\"\u0005\b\u0084\u0002\u0010/R)\u0010\u0085\u0002\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0002\u0010-\"\u0005\b\u0087\u0002\u0010/R)\u0010\u0088\u0002\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020*8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0002\u0010-\"\u0005\b\u008a\u0002\u0010/R-\u0010\u008b\u0002\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0002\u0010\b\"\u0005\b\u008d\u0002\u0010\nR-\u0010\u008e\u0002\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0002\u0010\b\"\u0005\b\u0090\u0002\u0010\nR-\u0010\u0091\u0002\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0002\u0010\b\"\u0005\b\u0093\u0002\u0010\nR\u001a\u0010\u0094\u0002\u001a\u00030\u0095\u00028\u0017X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0096\u0002\u0010\u0097\u0002¨\u0006\u009a\u0002"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2NativeCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "libraryToAddToCache", "getLibraryToAddToCache", "()Ljava/lang/String;", "setLibraryToAddToCache", "(Ljava/lang/String;)V", "lightDebugString", "getLightDebugString", "setLightDebugString", "allocator", "getAllocator", "setAllocator", "autoCacheDir", "getAutoCacheDir", "setAutoCacheDir", Argument.Delimiters.none, "autoCacheableFrom", "getAutoCacheableFrom", "()[Ljava/lang/String;", "setAutoCacheableFrom", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "backendThreads", "getBackendThreads", "setBackendThreads", "binaryOptions", "getBinaryOptions", "setBinaryOptions", "bundleId", "getBundleId", "setBundleId", "cacheDirectories", "getCacheDirectories", "setCacheDirectories", "cachedLibraries", "getCachedLibraries", "setCachedLibraries", Argument.Delimiters.none, "checkDependencies", "getCheckDependencies", "()Z", "setCheckDependencies", "(Z)V", "checkExternalCalls", "getCheckExternalCalls", "setCheckExternalCalls", "compileFromBitcode", "getCompileFromBitcode", "setCompileFromBitcode", "debugInfoFormatVersion", "getDebugInfoFormatVersion", "setDebugInfoFormatVersion", "debugPrefixMap", "getDebugPrefixMap", "setDebugPrefixMap", "destroyRuntimeMode", "getDestroyRuntimeMode", "setDestroyRuntimeMode", "testDumpOutputPath", "getTestDumpOutputPath", "setTestDumpOutputPath", "emitLazyObjCHeader", "getEmitLazyObjCHeader", "setEmitLazyObjCHeader", "exportKDoc", "getExportKDoc", "setExportKDoc", "exportedLibraries", "getExportedLibraries", "setExportedLibraries", "externalDependencies", "getExternalDependencies", "setExternalDependencies", "fakeOverrideValidator", "getFakeOverrideValidator", "setFakeOverrideValidator", "filesToCache", "getFilesToCache", "setFilesToCache", "frameworkImportHeaders", "getFrameworkImportHeaders", "setFrameworkImportHeaders", "generateDebugTrampolineString", "getGenerateDebugTrampolineString", "setGenerateDebugTrampolineString", "lightDebugDeprecated", "getLightDebugDeprecated$annotations", "getLightDebugDeprecated", "setLightDebugDeprecated", "gc", "getGc", "setGc", "headerKlibPath", "getHeaderKlibPath", "setHeaderKlibPath", "incrementalCacheDir", "getIncrementalCacheDir", "setIncrementalCacheDir", "includes", "getIncludes", "setIncludes", "propertyLazyInitialization", "getPropertyLazyInitialization", "setPropertyLazyInitialization", "konanDataDir", "getKonanDataDir", "setKonanDataDir", "llvmLTOPasses", "getLlvmLTOPasses", "setLlvmLTOPasses", "llvmModulePasses", "getLlvmModulePasses", "setLlvmModulePasses", "llvmVariant", "getLlvmVariant", "setLlvmVariant", "makePerFileCache", "getMakePerFileCache", "setMakePerFileCache", "manifestNativeTargets", "getManifestNativeTargets", "setManifestNativeTargets", "noObjcGenerics", "getNoObjcGenerics", "setNoObjcGenerics", "omitFrameworkBinary", "getOmitFrameworkBinary", "setOmitFrameworkBinary", "clangOptions", "getClangOptions", "setClangOptions", "overrideKonanProperties", "getOverrideKonanProperties", "setOverrideKonanProperties", "preLinkCaches", "getPreLinkCaches", "setPreLinkCaches", "printBitCode", "getPrintBitCode", "setPrintBitCode", "printFiles", "getPrintFiles", "setPrintFiles", "printIr", "getPrintIr", "setPrintIr", "purgeUserLibs", "getPurgeUserLibs", "setPurgeUserLibs", "serializedDependencies", "getSerializedDependencies", "setSerializedDependencies", "refinesPaths", "getRefinesPaths", "setRefinesPaths", "runtimeFile", "getRuntimeFile", "setRuntimeFile", "runtimeLogs", "getRuntimeLogs", "setRuntimeLogs", "saveLlvmIrAfter", "getSaveLlvmIrAfter", "setSaveLlvmIrAfter", "saveLlvmIrDirectory", "getSaveLlvmIrDirectory", "setSaveLlvmIrDirectory", "shortModuleName", "getShortModuleName", "setShortModuleName", "staticFramework", "getStaticFramework", "setStaticFramework", "temporaryFilesDir", "getTemporaryFilesDir", "setTemporaryFilesDir", "verifyBitCode", "getVerifyBitCode", "setVerifyBitCode", "verifyCompiler", "getVerifyCompiler", "setVerifyCompiler", "workerExceptionHandling", "getWorkerExceptionHandling", "setWorkerExceptionHandling", "writeDependenciesOfProducedKlibTo", "getWriteDependenciesOfProducedKlibTo", "setWriteDependenciesOfProducedKlibTo", "saveDependenciesPath", "getSaveDependenciesPath", "setSaveDependenciesPath", "enableAssertions", "getEnableAssertions", "setEnableAssertions", "mainPackage", "getMainPackage", "setMainPackage", "friendModules", "getFriendModules", "setFriendModules", "debug", "getDebug", "setDebug", "generateNoExitTestRunner", "getGenerateNoExitTestRunner", "setGenerateNoExitTestRunner", "generateTestRunner", "getGenerateTestRunner", "setGenerateTestRunner", "generateWorkerTestRunner", "getGenerateWorkerTestRunner", "setGenerateWorkerTestRunner", "includeBinaries", "getIncludeBinaries", "setIncludeBinaries", "libraries", "getLibraries", "setLibraries", "libraryVersion", "getLibraryVersion$annotations", "getLibraryVersion", "setLibraryVersion", "singleLinkerArguments", "getSingleLinkerArguments", "setSingleLinkerArguments", "linkerArguments", "getLinkerArguments", "setLinkerArguments", "listTargets", "getListTargets", "setListTargets", "manifestFile", "getManifestFile", "setManifestFile", "memoryModel", "getMemoryModel", "setMemoryModel", "moduleName", "getModuleName", "setModuleName", "nativeLibraries", "getNativeLibraries", "setNativeLibraries", "nodefaultlibs", "getNodefaultlibs", "setNodefaultlibs", "noendorsedlibs", "getNoendorsedlibs$annotations", "getNoendorsedlibs", "setNoendorsedlibs", "nomain", "getNomain", "setNomain", "nopack", "getNopack", "setNopack", "nostdlib", "getNostdlib", "setNostdlib", "optimization", "getOptimization", "setOptimization", "outputName", "getOutputName", "setOutputName", "produce", "getProduce", "setProduce", "target", "getTarget", "setTarget", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2NativeCompilerArguments extends CommonKlibBasedCompilerArguments {

    @Argument(description = "Allocator used at runtime.", value = "-Xallocator", valueDescription = "std | mimalloc | custom")
    private String allocator;

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to the directory where caches for auto-cacheable dependencies should be put.", value = "-Xauto-cache-dir", valueDescription = "<path>")
    private String autoCacheDir;

    @Argument(description = "Bundle ID to be set in the Info.plist file of the produced framework. This option is deprecated. Please use '-Xbinary=bundleId=<id>'.", value = "-Xbundle-id", valueDescription = "<id>")
    private String bundleId;

    @Argument(deprecatedName = "--check_dependencies", description = "Check dependencies and download the missing ones.", value = "-Xcheck-dependencies")
    private boolean checkDependencies;

    @Argument(description = "Ensure that all calls of possibly long external functions are done in the native thread state.", value = "-Xcheck-state-at-external-calls")
    private boolean checkExternalCalls;

    @Argument(description = "Continue compilation from the given bitcode file.", value = "-Xcompile-from-bitcode", valueDescription = "<path>")
    private String compileFromBitcode;

    @Argument(description = "Enable the emission of debug information.", value = "-g")
    private boolean debug;

    @Argument(description = "When to destroy the runtime – 'legacy' and 'on-shutdown' are currently supported. Note that 'legacy' mode is deprecated and will be removed.", value = "-Xdestroy-runtime-mode", valueDescription = "<mode>")
    private String destroyRuntimeMode;

    @Argument(description = Argument.Delimiters.none, value = "-Xemit-lazy-objc-header")
    private String emitLazyObjCHeader;

    @Argument(deprecatedName = "-enable_assertions", description = "Enable runtime assertions in generated code.", shortName = "-ea", value = "-enable-assertions")
    private boolean enableAssertions;

    @Argument(description = "Export KDoc entries in the framework header.", value = "-Xexport-kdoc")
    @Enables(feature = LanguageFeature.ExportKDocDocumentationToKlib)
    private boolean exportKDoc;

    @Argument(description = "Path to the file containing external dependencies.", value = "-Xexternal-dependencies", valueDescription = "<path>")
    private String externalDependencies;

    @Argument(description = "Enable the IR fake override validator.", value = "-Xfake-override-validator")
    private boolean fakeOverrideValidator;

    @Argument(description = "Paths to friend modules.", value = "-friend-modules", valueDescription = "<path>")
    private String friendModules;

    @Argument(description = "GC to use – 'noop', 'stms', and 'cms' are currently supported. This works only with '-memory-model experimental'.", value = "-Xgc", valueDescription = "<gc>")
    private String gc;

    @Argument(description = "Generate trampolines to make debugger breakpoint resolution more accurate (inlines, 'when', etc.).", value = "-Xg-generate-debug-trampoline", valueDescription = "{disable|enable}")
    private String generateDebugTrampolineString;

    @Argument(description = "Produce a runner for unit tests that doesn't force an exit.", shortName = "-trn", value = "-generate-no-exit-test-runner")
    private boolean generateNoExitTestRunner;

    @Argument(deprecatedName = "-generate_test_runner", description = "Produce a runner for unit tests.", shortName = "-tr", value = "-generate-test-runner")
    private boolean generateTestRunner;

    @Argument(description = "Produce a worker runner for unit tests.", shortName = "-trw", value = "-generate-worker-test-runner")
    private boolean generateWorkerTestRunner;

    @Argument(description = "Save a klib that only contains the public ABI to the given path.", value = "-Xheader-klib-path")
    private String headerKlibPath;

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to the directory where incremental build caches should be put.", value = "-Xic-cache-dir", valueDescription = "<path>")
    private String incrementalCacheDir;

    @Argument(description = "Custom path to the location of konan distributions.", value = "-Xkonan-data-dir")
    private String konanDataDir;

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to a library to be added to the cache.", value = "-Xadd-cache", valueDescription = "<path>")
    private String libraryToAddToCache;

    @Argument(description = "The library version.\nNote: This option is deprecated and will be removed in one of the future releases.", shortName = "-lv", value = "-library-version", valueDescription = "<version>")
    private String libraryVersion;

    @Argument(description = "Add light debug information. This option has been deprecated. Please use '-Xadd-light-debug=enable' instead.", value = "-Xg0")
    private boolean lightDebugDeprecated;

    @Argument(description = "Add light debug information for optimized builds. This option is skipped in debug builds.\nIt's enabled by default on Darwin platforms where collected debug information is stored in a .dSYM file.\nCurrently this option is disabled by default on other platforms.", value = "-Xadd-light-debug", valueDescription = "{disable|enable}")
    private String lightDebugString;

    @Argument(deprecatedName = "-list_targets", description = "List available hardware targets.", value = "-list-targets")
    private boolean listTargets;

    @Argument(description = "Custom set of LLVM passes to run as the LTOOptimizationPipeline.", value = "-Xllvm-lto-passes")
    private String llvmLTOPasses;

    @Argument(description = "Custom set of LLVM passes to run as the ModuleOptimizationPipeline.", value = "-Xllvm-module-passes")
    private String llvmModulePasses;

    @Argument(description = "Choose the LLVM distribution that will be used during compilation.", value = "-Xllvm-variant", valueDescription = "{dev|user|absolute path to llvm}")
    private String llvmVariant;

    @Argument(description = "Qualified entry point name.", shortName = "-e", value = "-entry", valueDescription = "<name>")
    private String mainPackage;

    @Argument(description = "Force the compiler to produce per-file caches.", value = "-Xmake-per-file-cache")
    private boolean makePerFileCache;

    @Argument(description = "Provide a manifest addend file.", value = "-manifest", valueDescription = "<path>")
    private String manifestFile;

    @Argument(description = "Choose the memory model to be used – 'strict' and 'experimental' are currently supported.", value = "-memory-model", valueDescription = "<model>")
    private String memoryModel;

    @Argument(deprecatedName = "-module_name", description = "Specify a name for the compilation module.", value = "-module-name", valueDescription = "<name>")
    private String moduleName;

    @Argument(description = "Disable generics support for framework header.", value = "-Xno-objc-generics")
    private boolean noObjcGenerics;

    @Argument(deprecatedName = "-nodefaultlibs", description = "Don't link the libraries from dist/klib automatically.", value = "-no-default-libs")
    private boolean nodefaultlibs;

    @Argument(description = "Don't link endorsed libraries from the dist automatically. This option has been deprecated, as the dist no longer has any endorsed libraries.", value = "-no-endorsed-libs")
    private boolean noendorsedlibs;

    @Argument(description = "Assume the 'main' entry point will be provided by external libraries.", value = "-nomain")
    private boolean nomain;

    @Argument(description = "Don't pack the library into a klib file.", value = "-nopack")
    private boolean nopack;

    @Argument(description = "Don't link with the stdlib.", value = "-nostdlib")
    private boolean nostdlib;

    @Argument(description = "Omit binary when compiling the framework.", value = "-Xomit-framework-binary")
    private boolean omitFrameworkBinary;

    @Argument(description = "Enable optimizations during compilation.", value = "-opt")
    private boolean optimization;

    @Argument(description = "Output name.", shortName = "-o", value = "-output", valueDescription = "<name>")
    private String outputName;

    @Argument(description = "Perform caches pre-linking.", value = "-Xpre-link-caches", valueDescription = "{disable|enable}")
    private String preLinkCaches;

    @Argument(deprecatedName = "--print_bitcode", description = "Print LLVM bitcode.", value = "-Xprint-bitcode")
    private boolean printBitCode;

    @Argument(description = "Print files.", value = "-Xprint-files")
    private boolean printFiles;

    @Argument(deprecatedName = "--print_ir", description = "Print IR.", value = "-Xprint-ir")
    private boolean printIr;

    @Argument(description = "Specify the output file kind.", shortName = "-p", value = "-produce", valueDescription = "{program|static|dynamic|framework|library|bitcode}")
    private String produce;

    @Argument(description = "Initialize top level properties lazily per file.", value = "-Xir-property-lazy-initialization", valueDescription = "{disable|enable}")
    private String propertyLazyInitialization;

    @Argument(deprecatedName = "--purge_user_libs", description = "Don't link unused libraries even if explicitly specified.", value = "-Xpurge-user-libs")
    private boolean purgeUserLibs;

    @Argument(deprecatedName = "--runtime", description = "Override the standard 'runtime.bc' location.", value = "-Xruntime", valueDescription = "<path>")
    private String runtimeFile;

    @Argument(description = "Enable logging of Native runtime internals.", value = "-Xruntime-logs", valueDescription = "<tag1=level1,tag2=level2,...>")
    private String runtimeLogs;

    @Argument(description = "Path for writing backend dependencies.", value = "-Xwrite-dependencies-to")
    private String saveDependenciesPath;

    @Argument(description = "Directory that should contain the results of '-Xsave-llvm-ir-after=<phase>'.", value = "-Xsave-llvm-ir-directory")
    private String saveLlvmIrDirectory;

    @Argument(description = "Serialized dependencies to use for linking.", value = "-Xread-dependencies-from", valueDescription = "<path>")
    private String serializedDependencies;

    @Argument(description = "A short name used to denote this library in the IDE and in a generated Objective-C header.", value = "-Xshort-module-name", valueDescription = "<name>")
    private String shortModuleName;

    @Argument(description = "Create a framework with a static library instead of a dynamic one.", value = "-Xstatic-framework")
    private boolean staticFramework;

    @Argument(description = "Set the hardware target.", value = "-target", valueDescription = "<target>")
    private String target;

    @Argument(deprecatedName = "--temporary_files_dir", description = "Save temporary files to the given directory.", value = "-Xtemporary-files-dir", valueDescription = "<path>")
    private String temporaryFilesDir;

    @Argument(description = "Path to a file for dumping the list of all available tests.", value = "-Xdump-tests-to", valueDescription = "<path>")
    private String testDumpOutputPath;

    @Argument(deprecatedName = "--verify_bitcode", description = "Verify LLVM bitcode after each method.", value = "-Xverify-bitcode")
    private boolean verifyBitCode;

    @Argument(description = "Verify the compiler.", value = "-Xverify-compiler")
    private String verifyCompiler;

    @Argument(description = "Unhandled exception processing in 'Worker.executeAfter'. Possible values: 'legacy' and 'use-hook'. The default value is 'legacy' and for '-memory-model experimental', the default value is 'use-hook'.", value = "-Xworker-exception-handling", valueDescription = "<mode>")
    private String workerExceptionHandling;

    @Argument(description = "Write file containing the paths of dependencies used during klib compilation to the provided path", value = "-Xwrite-dependencies-of-produced-klib-to", valueDescription = "<path>")
    private String writeDependenciesOfProducedKlibTo;

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to the root directory from which dependencies are to be cached automatically.\nBy default caches will be placed into the kotlin-native system cache directory.", value = "-Xauto-cache-from", valueDescription = "<path>")
    private String[] autoCacheableFrom = new String[0];

    @Argument(description = "Run codegen by file in N parallel threads.\n0 means use one thread per processor core.\nThe default value is 1.", value = "-Xbackend-threads", valueDescription = "<N>")
    private String backendThreads = "1";

    @Argument(description = "Specify a binary option.", value = "-Xbinary", valueDescription = "<option=value>")
    private String[] binaryOptions = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to the directory containing caches.", value = "-Xcache-directory", valueDescription = "<path>")
    private String[] cacheDirectories = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Paths to a library and its cache, separated by a comma.", value = "-Xcached-library", valueDescription = "<library path>,<cache path>")
    private String[] cachedLibraries = new String[0];

    @Argument(description = "Generate debug info of the given version (1, 2).", value = "-Xdebug-info-version")
    private String debugInfoFormatVersion = "1";

    @Argument(description = "Remap file source directory paths in debug info.", value = "-Xdebug-prefix-map", valueDescription = "<old1=new1,old2=new2,...>")
    private String[] debugPrefixMap = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "A library to be included in the produced framework API.\nThis library must be one of the ones passed with '-library'.", value = "-Xexport-library", valueDescription = "<path>")
    private String[] exportedLibraries = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Path to the file to cache.", value = "-Xfile-to-cache", valueDescription = "<path>")
    private String[] filesToCache = new String[0];

    @Argument(description = "Add an additional header import to the framework header.", value = "-Xframework-import-header", valueDescription = "<header>")
    private String[] frameworkImportHeaders = new String[0];

    @Argument(description = "A path to an intermediate library that should be processed in the same manner as source files.", value = "-Xinclude", valueDescription = "<path>")
    private String[] includes = new String[0];

    @Argument(description = "Comma-separated list that will be written as the value of 'native_targets' property in the .klib manifest. Unknown values are discarded.", value = "-Xmanifest-native-targets")
    private String[] manifestNativeTargets = new String[0];

    @Argument(description = "Explicit list of Clang options.", value = "-Xoverride-clang-options", valueDescription = "<arg1,arg2,...>")
    private String[] clangOptions = new String[0];

    @Argument(delimiter = Argument.Delimiters.semicolon, description = "Override values from 'konan.properties' with the given ones.", value = "-Xoverride-konan-properties", valueDescription = "key1=value1;key2=value2;...")
    private String[] overrideKonanProperties = new String[0];

    @Argument(description = "Paths to output directories for refined modules (modules whose 'expect' declarations this module can actualize).", value = "-Xrefines-paths", valueDescription = "<path>")
    private String[] refinesPaths = new String[0];

    @Argument(description = "Save the result of the Kotlin IR to LLVM IR translation to '-Xsave-llvm-ir-directory'.", value = "-Xsave-llvm-ir-after")
    private String[] saveLlvmIrAfter = new String[0];

    @Argument(deprecatedName = "-includeBinary", description = "Pack the given external binary into the klib.", shortName = "-ib", value = "-include-binary", valueDescription = "<path>")
    private String[] includeBinaries = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Link with the given library.", shortName = "-l", value = "-library", valueDescription = "<path>")
    private String[] libraries = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Pass the given argument to the linker.", value = "-linker-option", valueDescription = "<arg>")
    private String[] singleLinkerArguments = new String[0];

    @Argument(delimiter = Argument.Delimiters.space, deprecatedName = "-linkerOpts", description = "Pass arguments to the linker.", value = "-linker-options", valueDescription = "<arg>")
    private String[] linkerArguments = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, deprecatedName = "-nativelibrary", description = "Include the given native bitcode library.", shortName = "-nl", value = "-native-library", valueDescription = "<path>")
    private String[] nativeLibraries = new String[0];
    private final transient CommonCompilerArgumentsConfigurator configurator = new K2NativeCompilerArgumentsConfigurator();

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getLibraryVersion$annotations() {
    }

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getLightDebugDeprecated$annotations() {
    }

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getNoendorsedlibs$annotations() {
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() {
        return K2NativeCompilerArgumentsCopyGeneratedKt.copyK2NativeCompilerArguments(this, new K2NativeCompilerArguments());
    }

    public final String getAllocator() {
        return this.allocator;
    }

    public final String getAutoCacheDir() {
        return this.autoCacheDir;
    }

    public final String[] getAutoCacheableFrom() {
        return this.autoCacheableFrom;
    }

    public final String getBackendThreads() {
        return this.backendThreads;
    }

    public final String[] getBinaryOptions() {
        return this.binaryOptions;
    }

    public final String getBundleId() {
        return this.bundleId;
    }

    public final String[] getCacheDirectories() {
        return this.cacheDirectories;
    }

    public final String[] getCachedLibraries() {
        return this.cachedLibraries;
    }

    public final boolean getCheckDependencies() {
        return this.checkDependencies;
    }

    public final boolean getCheckExternalCalls() {
        return this.checkExternalCalls;
    }

    public final String[] getClangOptions() {
        return this.clangOptions;
    }

    public final String getCompileFromBitcode() {
        return this.compileFromBitcode;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final boolean getDebug() {
        return this.debug;
    }

    public final String getDebugInfoFormatVersion() {
        return this.debugInfoFormatVersion;
    }

    public final String[] getDebugPrefixMap() {
        return this.debugPrefixMap;
    }

    public final String getDestroyRuntimeMode() {
        return this.destroyRuntimeMode;
    }

    public final String getEmitLazyObjCHeader() {
        return this.emitLazyObjCHeader;
    }

    public final boolean getEnableAssertions() {
        return this.enableAssertions;
    }

    public final boolean getExportKDoc() {
        return this.exportKDoc;
    }

    public final String[] getExportedLibraries() {
        return this.exportedLibraries;
    }

    public final String getExternalDependencies() {
        return this.externalDependencies;
    }

    public final boolean getFakeOverrideValidator() {
        return this.fakeOverrideValidator;
    }

    public final String[] getFilesToCache() {
        return this.filesToCache;
    }

    public final String[] getFrameworkImportHeaders() {
        return this.frameworkImportHeaders;
    }

    public final String getFriendModules() {
        return this.friendModules;
    }

    public final String getGc() {
        return this.gc;
    }

    public final String getGenerateDebugTrampolineString() {
        return this.generateDebugTrampolineString;
    }

    public final boolean getGenerateNoExitTestRunner() {
        return this.generateNoExitTestRunner;
    }

    public final boolean getGenerateTestRunner() {
        return this.generateTestRunner;
    }

    public final boolean getGenerateWorkerTestRunner() {
        return this.generateWorkerTestRunner;
    }

    public final String getHeaderKlibPath() {
        return this.headerKlibPath;
    }

    public final String[] getIncludeBinaries() {
        return this.includeBinaries;
    }

    public final String[] getIncludes() {
        return this.includes;
    }

    public final String getIncrementalCacheDir() {
        return this.incrementalCacheDir;
    }

    public final String getKonanDataDir() {
        return this.konanDataDir;
    }

    public final String[] getLibraries() {
        return this.libraries;
    }

    public final String getLibraryToAddToCache() {
        return this.libraryToAddToCache;
    }

    public final String getLibraryVersion() {
        return this.libraryVersion;
    }

    public final boolean getLightDebugDeprecated() {
        return this.lightDebugDeprecated;
    }

    public final String getLightDebugString() {
        return this.lightDebugString;
    }

    public final String[] getLinkerArguments() {
        return this.linkerArguments;
    }

    public final boolean getListTargets() {
        return this.listTargets;
    }

    public final String getLlvmLTOPasses() {
        return this.llvmLTOPasses;
    }

    public final String getLlvmModulePasses() {
        return this.llvmModulePasses;
    }

    public final String getLlvmVariant() {
        return this.llvmVariant;
    }

    public final String getMainPackage() {
        return this.mainPackage;
    }

    public final boolean getMakePerFileCache() {
        return this.makePerFileCache;
    }

    public final String getManifestFile() {
        return this.manifestFile;
    }

    public final String[] getManifestNativeTargets() {
        return this.manifestNativeTargets;
    }

    public final String getMemoryModel() {
        return this.memoryModel;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final String[] getNativeLibraries() {
        return this.nativeLibraries;
    }

    public final boolean getNoObjcGenerics() {
        return this.noObjcGenerics;
    }

    public final boolean getNodefaultlibs() {
        return this.nodefaultlibs;
    }

    public final boolean getNoendorsedlibs() {
        return this.noendorsedlibs;
    }

    public final boolean getNomain() {
        return this.nomain;
    }

    public final boolean getNopack() {
        return this.nopack;
    }

    public final boolean getNostdlib() {
        return this.nostdlib;
    }

    public final boolean getOmitFrameworkBinary() {
        return this.omitFrameworkBinary;
    }

    public final boolean getOptimization() {
        return this.optimization;
    }

    public final String getOutputName() {
        return this.outputName;
    }

    public final String[] getOverrideKonanProperties() {
        return this.overrideKonanProperties;
    }

    public final String getPreLinkCaches() {
        return this.preLinkCaches;
    }

    public final boolean getPrintBitCode() {
        return this.printBitCode;
    }

    public final boolean getPrintFiles() {
        return this.printFiles;
    }

    public final boolean getPrintIr() {
        return this.printIr;
    }

    public final String getProduce() {
        return this.produce;
    }

    public final String getPropertyLazyInitialization() {
        return this.propertyLazyInitialization;
    }

    public final boolean getPurgeUserLibs() {
        return this.purgeUserLibs;
    }

    public final String[] getRefinesPaths() {
        return this.refinesPaths;
    }

    public final String getRuntimeFile() {
        return this.runtimeFile;
    }

    public final String getRuntimeLogs() {
        return this.runtimeLogs;
    }

    public final String getSaveDependenciesPath() {
        return this.saveDependenciesPath;
    }

    public final String[] getSaveLlvmIrAfter() {
        return this.saveLlvmIrAfter;
    }

    public final String getSaveLlvmIrDirectory() {
        return this.saveLlvmIrDirectory;
    }

    public final String getSerializedDependencies() {
        return this.serializedDependencies;
    }

    public final String getShortModuleName() {
        return this.shortModuleName;
    }

    public final String[] getSingleLinkerArguments() {
        return this.singleLinkerArguments;
    }

    public final boolean getStaticFramework() {
        return this.staticFramework;
    }

    public final String getTarget() {
        return this.target;
    }

    public final String getTemporaryFilesDir() {
        return this.temporaryFilesDir;
    }

    public final String getTestDumpOutputPath() {
        return this.testDumpOutputPath;
    }

    public final boolean getVerifyBitCode() {
        return this.verifyBitCode;
    }

    public final String getVerifyCompiler() {
        return this.verifyCompiler;
    }

    public final String getWorkerExceptionHandling() {
        return this.workerExceptionHandling;
    }

    public final String getWriteDependenciesOfProducedKlibTo() {
        return this.writeDependenciesOfProducedKlibTo;
    }

    public final void setAllocator(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.allocator = str;
    }

    public final void setAutoCacheDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.autoCacheDir = str;
    }

    public final void setAutoCacheableFrom(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.autoCacheableFrom = strArr;
    }

    public final void setBackendThreads(String str) {
        str.getClass();
        checkFrozen();
        this.backendThreads = str;
    }

    public final void setBinaryOptions(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.binaryOptions = strArr;
    }

    public final void setBundleId(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.bundleId = str;
    }

    public final void setCacheDirectories(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.cacheDirectories = strArr;
    }

    public final void setCachedLibraries(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.cachedLibraries = strArr;
    }

    public final void setCheckDependencies(boolean z) {
        checkFrozen();
        this.checkDependencies = z;
    }

    public final void setCheckExternalCalls(boolean z) {
        checkFrozen();
        this.checkExternalCalls = z;
    }

    public final void setClangOptions(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.clangOptions = strArr;
    }

    public final void setCompileFromBitcode(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.compileFromBitcode = str;
    }

    public final void setDebug(boolean z) {
        checkFrozen();
        this.debug = z;
    }

    public final void setDebugInfoFormatVersion(String str) {
        str.getClass();
        checkFrozen();
        this.debugInfoFormatVersion = str;
    }

    public final void setDebugPrefixMap(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.debugPrefixMap = strArr;
    }

    public final void setDestroyRuntimeMode(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.destroyRuntimeMode = str;
    }

    public final void setEmitLazyObjCHeader(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.emitLazyObjCHeader = str;
    }

    public final void setEnableAssertions(boolean z) {
        checkFrozen();
        this.enableAssertions = z;
    }

    public final void setExportKDoc(boolean z) {
        checkFrozen();
        this.exportKDoc = z;
    }

    public final void setExportedLibraries(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.exportedLibraries = strArr;
    }

    public final void setExternalDependencies(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.externalDependencies = str;
    }

    public final void setFakeOverrideValidator(boolean z) {
        checkFrozen();
        this.fakeOverrideValidator = z;
    }

    public final void setFilesToCache(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.filesToCache = strArr;
    }

    public final void setFrameworkImportHeaders(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.frameworkImportHeaders = strArr;
    }

    public final void setFriendModules(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.friendModules = str;
    }

    public final void setGc(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.gc = str;
    }

    public final void setGenerateDebugTrampolineString(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.generateDebugTrampolineString = str;
    }

    public final void setGenerateNoExitTestRunner(boolean z) {
        checkFrozen();
        this.generateNoExitTestRunner = z;
    }

    public final void setGenerateTestRunner(boolean z) {
        checkFrozen();
        this.generateTestRunner = z;
    }

    public final void setGenerateWorkerTestRunner(boolean z) {
        checkFrozen();
        this.generateWorkerTestRunner = z;
    }

    public final void setHeaderKlibPath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.headerKlibPath = str;
    }

    public final void setIncludeBinaries(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.includeBinaries = strArr;
    }

    public final void setIncludes(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.includes = strArr;
    }

    public final void setIncrementalCacheDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.incrementalCacheDir = str;
    }

    public final void setKonanDataDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.konanDataDir = str;
    }

    public final void setLibraries(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.libraries = strArr;
    }

    public final void setLibraryToAddToCache(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.libraryToAddToCache = str;
    }

    public final void setLibraryVersion(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.libraryVersion = str;
    }

    public final void setLightDebugDeprecated(boolean z) {
        checkFrozen();
        this.lightDebugDeprecated = z;
    }

    public final void setLightDebugString(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.lightDebugString = str;
    }

    public final void setLinkerArguments(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.linkerArguments = strArr;
    }

    public final void setListTargets(boolean z) {
        checkFrozen();
        this.listTargets = z;
    }

    public final void setLlvmLTOPasses(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.llvmLTOPasses = str;
    }

    public final void setLlvmModulePasses(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.llvmModulePasses = str;
    }

    public final void setLlvmVariant(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.llvmVariant = str;
    }

    public final void setMainPackage(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.mainPackage = str;
    }

    public final void setMakePerFileCache(boolean z) {
        checkFrozen();
        this.makePerFileCache = z;
    }

    public final void setManifestFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.manifestFile = str;
    }

    public final void setManifestNativeTargets(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.manifestNativeTargets = strArr;
    }

    public final void setMemoryModel(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.memoryModel = str;
    }

    public final void setModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleName = str;
    }

    public final void setNativeLibraries(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.nativeLibraries = strArr;
    }

    public final void setNoObjcGenerics(boolean z) {
        checkFrozen();
        this.noObjcGenerics = z;
    }

    public final void setNodefaultlibs(boolean z) {
        checkFrozen();
        this.nodefaultlibs = z;
    }

    public final void setNoendorsedlibs(boolean z) {
        checkFrozen();
        this.noendorsedlibs = z;
    }

    public final void setNomain(boolean z) {
        checkFrozen();
        this.nomain = z;
    }

    public final void setNopack(boolean z) {
        checkFrozen();
        this.nopack = z;
    }

    public final void setNostdlib(boolean z) {
        checkFrozen();
        this.nostdlib = z;
    }

    public final void setOmitFrameworkBinary(boolean z) {
        checkFrozen();
        this.omitFrameworkBinary = z;
    }

    public final void setOptimization(boolean z) {
        checkFrozen();
        this.optimization = z;
    }

    public final void setOutputName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.outputName = str;
    }

    public final void setOverrideKonanProperties(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.overrideKonanProperties = strArr;
    }

    public final void setPreLinkCaches(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.preLinkCaches = str;
    }

    public final void setPrintBitCode(boolean z) {
        checkFrozen();
        this.printBitCode = z;
    }

    public final void setPrintFiles(boolean z) {
        checkFrozen();
        this.printFiles = z;
    }

    public final void setPrintIr(boolean z) {
        checkFrozen();
        this.printIr = z;
    }

    public final void setProduce(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.produce = str;
    }

    public final void setPropertyLazyInitialization(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.propertyLazyInitialization = str;
    }

    public final void setPurgeUserLibs(boolean z) {
        checkFrozen();
        this.purgeUserLibs = z;
    }

    public final void setRefinesPaths(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.refinesPaths = strArr;
    }

    public final void setRuntimeFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.runtimeFile = str;
    }

    public final void setRuntimeLogs(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.runtimeLogs = str;
    }

    public final void setSaveDependenciesPath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.saveDependenciesPath = str;
    }

    public final void setSaveLlvmIrAfter(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.saveLlvmIrAfter = strArr;
    }

    public final void setSaveLlvmIrDirectory(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.saveLlvmIrDirectory = str;
    }

    public final void setSerializedDependencies(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.serializedDependencies = str;
    }

    public final void setShortModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.shortModuleName = str;
    }

    public final void setSingleLinkerArguments(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.singleLinkerArguments = strArr;
    }

    public final void setStaticFramework(boolean z) {
        checkFrozen();
        this.staticFramework = z;
    }

    public final void setTarget(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.target = str;
    }

    public final void setTemporaryFilesDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.temporaryFilesDir = str;
    }

    public final void setTestDumpOutputPath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.testDumpOutputPath = str;
    }

    public final void setVerifyBitCode(boolean z) {
        checkFrozen();
        this.verifyBitCode = z;
    }

    public final void setVerifyCompiler(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.verifyCompiler = str;
    }

    public final void setWorkerExceptionHandling(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.workerExceptionHandling = str;
    }

    public final void setWriteDependenciesOfProducedKlibTo(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.writeDependenciesOfProducedKlibTo = str;
    }
}
