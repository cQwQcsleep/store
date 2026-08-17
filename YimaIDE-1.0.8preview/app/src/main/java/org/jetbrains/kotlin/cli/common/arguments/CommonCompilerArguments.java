package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0003\b¹\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002Ì\u0002B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R&\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR4\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R4\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R&\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\b\"\u0004\b\u001b\u0010\nR*\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R&\u0010!\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R&\u0010$\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010\nR&\u0010'\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\b\"\u0004\b)\u0010\nR&\u0010*\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\b\"\u0004\b,\u0010\nR&\u0010-\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\b\"\u0004\b/\u0010\nR&\u00100\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\b\"\u0004\b2\u0010\nR&\u00103\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\nR&\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\b\"\u0004\b8\u0010\nR&\u00109\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\b\"\u0004\b;\u0010\nR*\u0010<\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001e\"\u0004\b>\u0010 R&\u0010?\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\b\"\u0004\bA\u0010\nR&\u0010B\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\b\"\u0004\bD\u0010\nR&\u0010E\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\b\"\u0004\bG\u0010\nR4\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\bI\u0010\u0012\"\u0004\bJ\u0010\u0014R4\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\bL\u0010\u0012\"\u0004\bM\u0010\u0014R4\u0010N\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\bO\u0010\u0012\"\u0004\bP\u0010\u0014R&\u0010Q\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\b\"\u0004\bS\u0010\nR&\u0010T\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\b\"\u0004\bV\u0010\nR&\u0010W\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\b\"\u0004\bY\u0010\nR&\u0010Z\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\b\"\u0004\b\\\u0010\nR&\u0010]\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\b\"\u0004\b_\u0010\nR&\u0010`\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\b\"\u0004\bb\u0010\nR&\u0010c\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\b\"\u0004\be\u0010\nR&\u0010f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\b\"\u0004\bh\u0010\nR4\u0010i\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\bj\u0010\u0012\"\u0004\bk\u0010\u0014R&\u0010l\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\b\"\u0004\bn\u0010\nR&\u0010o\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\b\"\u0004\bq\u0010\nR*\u0010r\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u001e\"\u0004\bt\u0010 R*\u0010u\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010\u001e\"\u0004\bw\u0010 R*\u0010x\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010\u001e\"\u0004\bz\u0010 R-\u0010{\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0011\n\u0003\u0010\u0080\u0001\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007fR)\u0010\u0081\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\b\"\u0005\b\u0083\u0001\u0010\nR)\u0010\u0084\u0001\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u001e\"\u0005\b\u0086\u0001\u0010 R)\u0010\u0087\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\b\"\u0005\b\u0089\u0001\u0010\nR)\u0010\u008a\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010\b\"\u0005\b\u008c\u0001\u0010\nR7\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u008e\u0001\u0010\u0012\"\u0005\b\u008f\u0001\u0010\u0014R7\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u0091\u0001\u0010\u0012\"\u0005\b\u0092\u0001\u0010\u0014R7\u0010\u0093\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u0094\u0001\u0010\u0012\"\u0005\b\u0095\u0001\u0010\u0014R7\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u0097\u0001\u0010\u0012\"\u0005\b\u0098\u0001\u0010\u0014R7\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u009a\u0001\u0010\u0012\"\u0005\b\u009b\u0001\u0010\u0014R)\u0010\u009c\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010\b\"\u0005\b\u009e\u0001\u0010\nR)\u0010\u009f\u0001\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010\u001e\"\u0005\b¡\u0001\u0010 R)\u0010¢\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010\b\"\u0005\b¤\u0001\u0010\nR)\u0010¥\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¦\u0001\u0010\b\"\u0005\b§\u0001\u0010\nR-\u0010¨\u0001\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b©\u0001\u0010\u001e\"\u0005\bª\u0001\u0010 R)\u0010«\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¬\u0001\u0010\b\"\u0005\b\u00ad\u0001\u0010\nR)\u0010®\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010\b\"\u0005\b°\u0001\u0010\nR)\u0010±\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u0010\b\"\u0005\b³\u0001\u0010\nR)\u0010´\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bµ\u0001\u0010\b\"\u0005\b¶\u0001\u0010\nR-\u0010·\u0001\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0001\u0010\u001e\"\u0005\b¹\u0001\u0010 R)\u0010º\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010\b\"\u0005\b¼\u0001\u0010\nR)\u0010½\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¾\u0001\u0010\b\"\u0005\b¿\u0001\u0010\nR-\u0010À\u0001\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÁ\u0001\u0010\u001e\"\u0005\bÂ\u0001\u0010 R)\u0010Ã\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÄ\u0001\u0010\b\"\u0005\bÅ\u0001\u0010\nR)\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÇ\u0001\u0010\b\"\u0005\bÈ\u0001\u0010\nR)\u0010É\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÊ\u0001\u0010\b\"\u0005\bË\u0001\u0010\nR)\u0010Ì\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÍ\u0001\u0010\b\"\u0005\bÎ\u0001\u0010\nR)\u0010Ï\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÐ\u0001\u0010\b\"\u0005\bÑ\u0001\u0010\nR7\u0010Ò\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bÓ\u0001\u0010\u0012\"\u0005\bÔ\u0001\u0010\u0014R7\u0010Õ\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bÖ\u0001\u0010\u0012\"\u0005\b×\u0001\u0010\u0014R7\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bÙ\u0001\u0010\u0012\"\u0005\bÚ\u0001\u0010\u0014R7\u0010Û\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bÜ\u0001\u0010\u0012\"\u0005\bÝ\u0001\u0010\u0014R7\u0010Þ\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bß\u0001\u0010\u0012\"\u0005\bà\u0001\u0010\u0014R7\u0010á\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bâ\u0001\u0010\u0012\"\u0005\bã\u0001\u0010\u0014R7\u0010ä\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bå\u0001\u0010\u0012\"\u0005\bæ\u0001\u0010\u0014R)\u0010ç\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bè\u0001\u0010\b\"\u0005\bé\u0001\u0010\nR)\u0010ê\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bë\u0001\u0010\b\"\u0005\bì\u0001\u0010\nR)\u0010í\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bî\u0001\u0010\b\"\u0005\bï\u0001\u0010\nR)\u0010ð\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bñ\u0001\u0010\b\"\u0005\bò\u0001\u0010\nR)\u0010ó\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bô\u0001\u0010\b\"\u0005\bõ\u0001\u0010\nR)\u0010ö\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b÷\u0001\u0010\b\"\u0005\bø\u0001\u0010\nR)\u0010ù\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bú\u0001\u0010\b\"\u0005\bû\u0001\u0010\nR)\u0010ü\u0001\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bý\u0001\u0010\u001e\"\u0005\bþ\u0001\u0010 R)\u0010ÿ\u0001\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0002\u0010\b\"\u0005\b\u0081\u0002\u0010\nR)\u0010\u0082\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0002\u0010\b\"\u0005\b\u0084\u0002\u0010\nR)\u0010\u0085\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0002\u0010\b\"\u0005\b\u0087\u0002\u0010\nR)\u0010\u0088\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0002\u0010\b\"\u0005\b\u008a\u0002\u0010\nR)\u0010\u008b\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0002\u0010\b\"\u0005\b\u008d\u0002\u0010\nR)\u0010\u008e\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0002\u0010\b\"\u0005\b\u0090\u0002\u0010\nR7\u0010\u0091\u0002\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b\u0092\u0002\u0010\u0012\"\u0005\b\u0093\u0002\u0010\u0014R)\u0010\u0094\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0002\u0010\b\"\u0005\b\u0096\u0002\u0010\nR0\u0010\u0097\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0017\n\u0000\u0012\u0005\b\u0098\u0002\u0010\u0003\u001a\u0005\b\u0099\u0002\u0010\b\"\u0005\b\u009a\u0002\u0010\nR)\u0010\u009b\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0002\u0010\b\"\u0005\b\u009d\u0002\u0010\nR)\u0010\u009e\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0002\u0010\b\"\u0005\b \u0002\u0010\nR)\u0010¡\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0002\u0010\b\"\u0005\b£\u0002\u0010\nR7\u0010¤\u0002\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b¥\u0002\u0010\u0012\"\u0005\b¦\u0002\u0010\u0014R-\u0010§\u0002\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¨\u0002\u0010\u001e\"\u0005\b©\u0002\u0010 R)\u0010ª\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0002\u0010\b\"\u0005\b¬\u0002\u0010\nR)\u0010\u00ad\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b®\u0002\u0010\b\"\u0005\b¯\u0002\u0010\nR7\u0010°\u0002\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\b±\u0002\u0010\u0012\"\u0005\b²\u0002\u0010\u0014R)\u0010³\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b´\u0002\u0010\b\"\u0005\bµ\u0002\u0010\nR-\u0010¶\u0002\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b·\u0002\u0010\u001e\"\u0005\b¸\u0002\u0010 R-\u0010¹\u0002\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bº\u0002\u0010\u001e\"\u0005\b»\u0002\u0010 R-\u0010¼\u0002\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b½\u0002\u0010\u001e\"\u0005\b¾\u0002\u0010 R7\u0010¿\u0002\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0002\u0010\u0015\u001a\u0005\bÀ\u0002\u0010\u0012\"\u0005\bÁ\u0002\u0010\u0014R)\u0010Â\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0002\u0010\b\"\u0005\bÄ\u0002\u0010\nR)\u0010Å\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÆ\u0002\u0010\b\"\u0005\bÇ\u0002\u0010\nR\u0018\u0010È\u0002\u001a\u00030É\u00028'X¦\u0004¢\u0006\b\u001a\u0006\bÊ\u0002\u0010Ë\u0002¨\u0006Í\u0002"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "autoAdvanceLanguageVersion", "getAutoAdvanceLanguageVersion", "()Z", "setAutoAdvanceLanguageVersion", "(Z)V", "autoAdvanceApiVersion", "getAutoAdvanceApiVersion", "setAutoAdvanceApiVersion", Argument.Delimiters.none, Argument.Delimiters.none, "pluginOptions", "getPluginOptions", "()[Ljava/lang/String;", "setPluginOptions", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "manuallyConfiguredFeatures", "getManuallyConfiguredFeatures", "setManuallyConfiguredFeatures", "debugLevelCompilerChecks", "getDebugLevelCompilerChecks", "setDebugLevelCompilerChecks", "dumpArgumentsDir", "getDumpArgumentsDir", "()Ljava/lang/String;", "setDumpArgumentsDir", "(Ljava/lang/String;)V", "explicitReturnTypes", "getExplicitReturnTypes", "setExplicitReturnTypes", "lenientMode", "getLenientMode", "setLenientMode", "allowAnyScriptsInSourceRoots", "getAllowAnyScriptsInSourceRoots", "setAllowAnyScriptsInSourceRoots", "allowConditionImpliesReturnsContracts", "getAllowConditionImpliesReturnsContracts", "setAllowConditionImpliesReturnsContracts", "allowContractsOnMoreFunctions", "getAllowContractsOnMoreFunctions", "setAllowContractsOnMoreFunctions", "allowHoldsinContract", "getAllowHoldsinContract", "setAllowHoldsinContract", "allowKotlinPackage", "getAllowKotlinPackage", "setAllowKotlinPackage", "allowReifiedTypeInCatch", "getAllowReifiedTypeInCatch", "setAllowReifiedTypeInCatch", "allowReturnsResultOf", "getAllowReturnsResultOf", "setAllowReturnsResultOf", "annotationDefaultTarget", "getAnnotationDefaultTarget", "setAnnotationDefaultTarget", "annotationTargetAll", "getAnnotationTargetAll", "setAnnotationTargetAll", "checkPhaseConditions", "getCheckPhaseConditions", "setCheckPhaseConditions", "collectionLiterals", "getCollectionLiterals", "setCollectionLiterals", ModuleXmlParser.COMMON_SOURCES, "getCommonSources", "setCommonSources", "pluginConfigurations", "getPluginConfigurations", "setPluginConfigurations", "pluginOrderConstraints", "getPluginOrderConstraints", "setPluginOrderConstraints", "consistentDataClassCopyVisibility", "getConsistentDataClassCopyVisibility", "setConsistentDataClassCopyVisibility", "contextParameters", "getContextParameters", "setContextParameters", "contextReceivers", "getContextReceivers", "setContextReceivers", "contextSensitiveResolution", "getContextSensitiveResolution", "setContextSensitiveResolution", "dataFlowBasedExhaustiveness", "getDataFlowBasedExhaustiveness", "setDataFlowBasedExhaustiveness", "detailedPerf", "getDetailedPerf", "setDetailedPerf", "directJavaActualization", "getDirectJavaActualization", "setDirectJavaActualization", "disableDefaultScriptingPlugin", "getDisableDefaultScriptingPlugin", "setDisableDefaultScriptingPlugin", "disablePhases", "getDisablePhases", "setDisablePhases", "dontSortSourceFiles", "getDontSortSourceFiles", "setDontSortSourceFiles", "dontWarnOnErrorSuppression", "getDontWarnOnErrorSuppression", "setDontWarnOnErrorSuppression", "dumpDirectory", "getDumpDirectory", "setDumpDirectory", "dumpOnlyFqName", "getDumpOnlyFqName", "setDumpOnlyFqName", "dumpPerf", "getDumpPerf", "setDumpPerf", "incrementalCompilation", "getIncrementalCompilation", "()Ljava/lang/Boolean;", "setIncrementalCompilation", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "expectActualClasses", "getExpectActualClasses", "setExpectActualClasses", "explicitApi", "getExplicitApi", "setExplicitApi", "explicitBackingFields", "getExplicitBackingFields", "setExplicitBackingFields", "explicitContextArguments", "getExplicitContextArguments", "setExplicitContextArguments", "fragmentDependencies", "getFragmentDependencies", "setFragmentDependencies", "fragmentFriendDependencies", "getFragmentFriendDependencies", "setFragmentFriendDependencies", "fragmentRefines", "getFragmentRefines", "setFragmentRefines", "fragmentSources", "getFragmentSources", "setFragmentSources", "fragments", "getFragments", "setFragments", "headerMode", "getHeaderMode", "setHeaderMode", "headerModeType", "getHeaderModeType", "setHeaderModeType", "ignoreConstOptimizationErrors", "getIgnoreConstOptimizationErrors", "setIgnoreConstOptimizationErrors", "inlineClasses", "getInlineClasses", "setInlineClasses", "intellijPluginRoot", "getIntellijPluginRoot", "setIntellijPluginRoot", "intrinsicConstEvaluation", "getIntrinsicConstEvaluation", "setIntrinsicConstEvaluation", "listPhases", "getListPhases", "setListPhases", "localTypeAliases", "getLocalTypeAliases", "setLocalTypeAliases", "metadataKlib", "getMetadataKlib", "setMetadataKlib", "metadataVersion", "getMetadataVersion", "setMetadataVersion", "multiDollarInterpolation", "getMultiDollarInterpolation", "setMultiDollarInterpolation", "multiPlatform", "getMultiPlatform", "setMultiPlatform", "nameBasedDestructuring", "getNameBasedDestructuring", "setNameBasedDestructuring", "nestedTypeAliases", "getNestedTypeAliases", "setNestedTypeAliases", "newInference", "getNewInference", "setNewInference", "noCheckActual", "getNoCheckActual", "setNoCheckActual", "noInline", "getNoInline", "setNoInline", "nonLocalBreakContinue", "getNonLocalBreakContinue", "setNonLocalBreakContinue", "phasesToDump", "getPhasesToDump", "setPhasesToDump", "phasesToDumpAfter", "getPhasesToDumpAfter", "setPhasesToDumpAfter", "phasesToDumpBefore", "getPhasesToDumpBefore", "setPhasesToDumpBefore", "phasesToValidate", "getPhasesToValidate", "setPhasesToValidate", "phasesToValidateAfter", "getPhasesToValidateAfter", "setPhasesToValidateAfter", "phasesToValidateBefore", "getPhasesToValidateBefore", "setPhasesToValidateBefore", "pluginClasspaths", "getPluginClasspaths", "setPluginClasspaths", "printConfiguration", "getPrintConfiguration", "setPrintConfiguration", "profilePhases", "getProfilePhases", "setProfilePhases", "renderInternalDiagnosticNames", "getRenderInternalDiagnosticNames", "setRenderInternalDiagnosticNames", "repl", "getRepl", "setRepl", "reportAllWarnings", "getReportAllWarnings", "setReportAllWarnings", "reportOutputFiles", "getReportOutputFiles", "setReportOutputFiles", "reportPerf", "getReportPerf", "setReportPerf", "returnValueChecker", "getReturnValueChecker", "setReturnValueChecker", "separateKmpCompilationScheme", "getSeparateKmpCompilationScheme", "setSeparateKmpCompilationScheme", "skipMetadataVersionCheck", "getSkipMetadataVersionCheck", "setSkipMetadataVersionCheck", "skipPrereleaseCheck", "getSkipPrereleaseCheck", "setSkipPrereleaseCheck", "stdlibCompilation", "getStdlibCompilation", "setStdlibCompilation", "suppressApiVersionGreaterThanLanguageVersionError", "getSuppressApiVersionGreaterThanLanguageVersionError", "setSuppressApiVersionGreaterThanLanguageVersionError", "suppressVersionWarnings", "getSuppressVersionWarnings", "setSuppressVersionWarnings", "suppressedDiagnostics", "getSuppressedDiagnostics", "setSuppressedDiagnostics", "unrestrictedBuilderInference", "getUnrestrictedBuilderInference", "setUnrestrictedBuilderInference", "useFirExperimentalCheckers", "getUseFirExperimentalCheckers$annotations", "getUseFirExperimentalCheckers", "setUseFirExperimentalCheckers", "useFirIC", "getUseFirIC", "setUseFirIC", "useFirLT", "getUseFirLT", "setUseFirLT", "useK2", "getUseK2", "setUseK2", "verbosePhases", "getVerbosePhases", "setVerbosePhases", "verifyIr", "getVerifyIr", "setVerifyIr", "verifyIrNestedOffsets", "getVerifyIrNestedOffsets", "setVerifyIrNestedOffsets", "verifyIrVisibility", "getVerifyIrVisibility", "setVerifyIrVisibility", "warningLevels", "getWarningLevels", "setWarningLevels", "whenGuards", "getWhenGuards", "setWhenGuards", "apiVersion", "getApiVersion", "setApiVersion", "kotlinHome", "getKotlinHome", "setKotlinHome", "languageVersion", "getLanguageVersion", "setLanguageVersion", "optIn", "getOptIn", "setOptIn", "progressiveMode", "getProgressiveMode", "setProgressiveMode", "script", "getScript", "setScript", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "DummyImpl", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonCompilerArguments extends CommonToolArguments {

    @Argument(description = "Allow compiling scripts along with regular Kotlin sources.", value = "-Xallow-any-scripts-in-source-roots")
    @Disables(feature = LanguageFeature.SkipStandaloneScriptsInSourceRoots)
    private boolean allowAnyScriptsInSourceRoots;

    @Argument(description = "Allow contracts that specify a limited conditional returns postcondition.", value = "-Xallow-condition-implies-returns-contracts")
    @Enables(feature = LanguageFeature.ConditionImpliesReturnsContracts)
    private boolean allowConditionImpliesReturnsContracts;

    @Argument(description = "Allow contracts on some operators and accessors, and allow checks for erased types.", value = "-Xallow-contracts-on-more-functions")
    @Enables.Container({@Enables(feature = LanguageFeature.AllowCheckForErasedTypesInContracts), @Enables(feature = LanguageFeature.AllowContractsOnSomeOperators), @Enables(feature = LanguageFeature.AllowContractsOnPropertyAccessors)})
    private boolean allowContractsOnMoreFunctions;

    @Argument(description = "Allow contracts that specify a condition that holds true inside a lambda argument.", value = "-Xallow-holdsin-contract")
    @Enables(feature = LanguageFeature.HoldsInContracts)
    private boolean allowHoldsinContract;

    @Argument(description = "Allow compiling code in the 'kotlin' package, and allow not requiring 'kotlin.stdlib' in 'module-info'.", value = "-Xallow-kotlin-package")
    private boolean allowKotlinPackage;

    @Argument(description = "Allow 'catch' parameters to have reified types.", value = "-Xallow-reified-type-in-catch")
    @Enables(feature = LanguageFeature.AllowReifiedTypeInCatchClause)
    private boolean allowReifiedTypeInCatch;

    @Argument(description = "Allows to use `returnsResultOf()` in `contract {}` block of function body. This contract provides additional information for return value checker. Enabling this feature will force compiler to produce pre-release binaries, because this functions with this contract cannot be read correctly by Kotlin 2.3 and lower.", value = "-Xallow-returns-result-of")
    @Enables(feature = LanguageFeature.AllowReturnsResultOfContract)
    private boolean allowReturnsResultOf;

    @Argument(description = "Change the default annotation targets for constructor properties:\n-Xannotation-default-target=first-only:      use the first of the following allowed targets: '@param:', '@property:', '@field:';\n-Xannotation-default-target=first-only-warn: same as first-only, and raise warnings when both '@param:' and either '@property:' or '@field:' are allowed;\n-Xannotation-default-target=param-property:  use '@param:' target if applicable, and also use the first of either '@property:' or '@field:';\ndefault: 'param-property' in language version 2.4+, 'first-only-warn' in language versions 2.2 & 2.3, 'first-only' in version 2.1 and before.", value = "-Xannotation-default-target", valueDescription = "first-only|first-only-warn|param-property")
    @Enables.Container({@Enables(feature = LanguageFeature.AnnotationDefaultTargetMigrationWarning, ifValueIs = "first-only-warn"), @Enables(feature = LanguageFeature.PropertyParamAnnotationDefaultTargetMode, ifValueIs = "param-property")})
    @Disables.Container({@Disables(feature = LanguageFeature.AnnotationDefaultTargetMigrationWarning, ifValueIs = "first-only"), @Disables(feature = LanguageFeature.PropertyParamAnnotationDefaultTargetMode, ifValueIs = "first-only"), @Disables(feature = LanguageFeature.PropertyParamAnnotationDefaultTargetMode, ifValueIs = "first-only-warn")})
    private String annotationDefaultTarget;

    @Argument(description = "Enable experimental language support for @all: annotation use-site target.", value = "-Xannotation-target-all")
    @Enables(feature = LanguageFeature.AnnotationAllUseSiteTarget)
    private boolean annotationTargetAll;

    @Argument(description = "Allow using declarations from only the specified version of bundled libraries.", value = "-api-version", valueDescription = "<version>")
    private String apiVersion;

    @Argument(description = "Check pre- and postconditions of IR lowering phases.", value = "-Xcheck-phase-conditions")
    private boolean checkPhaseConditions;

    @Argument(description = "Enable experimental language support for collection literals.", value = "-Xcollection-literals")
    @Enables(feature = LanguageFeature.CollectionLiterals)
    private boolean collectionLiterals;

    @Argument(description = "The effect of this compiler flag is the same as applying @ConsistentCopyVisibility annotation to all data classes in the module. See https://youtrack.jetbrains.com/issue/KT-11914", value = "-Xconsistent-data-class-copy-visibility")
    @Enables(feature = LanguageFeature.DataClassCopyRespectsConstructorVisibility)
    private boolean consistentDataClassCopyVisibility;

    @Argument(description = "Enable experimental context parameters.", value = "-Xcontext-parameters")
    @Enables(feature = LanguageFeature.ContextParameters)
    private boolean contextParameters;

    @Argument(description = "Enable experimental context receivers.", value = "-Xcontext-receivers")
    @Enables(feature = LanguageFeature.ContextReceivers)
    private boolean contextReceivers;

    @Argument(description = "Enable experimental context-sensitive resolution.", value = "-Xcontext-sensitive-resolution")
    @Enables(feature = LanguageFeature.ContextSensitiveResolutionUsingExpectedType)
    private boolean contextSensitiveResolution;

    @Argument(description = "Enable `when` exhaustiveness improvements that rely on data-flow analysis.", value = "-Xdata-flow-based-exhaustiveness")
    @Enables(feature = LanguageFeature.DataFlowBasedExhaustiveness)
    private boolean dataFlowBasedExhaustiveness;

    @Argument(description = "Enable debug level compiler checks. ATTENTION: these checks can slow compiler down or even crash it.", value = "-XXdebug-level-compiler-checks")
    private boolean debugLevelCompilerChecks;

    @Argument(description = "Enable more detailed performance statistics (Experimental).\nFor Native, the performance report includes execution time and lines processed per second for every individual lowering.\nFor WASM and JS, the performance report includes execution time and lines per second for each lowering of the first stage of compilation.", value = "-Xdetailed-perf")
    private boolean detailedPerf;

    @Argument(description = "Enable experimental direct Java actualization support.", value = "-Xdirect-java-actualization")
    @Enables(feature = LanguageFeature.DirectJavaActualization)
    private boolean directJavaActualization;

    @Argument(description = "Don't enable the scripting plugin by default.", value = "-Xdisable-default-scripting-plugin")
    private boolean disableDefaultScriptingPlugin;

    @Argument(description = "Disable automatic sorting of source files.", value = "-Xdont-sort-source-files")
    private boolean dontSortSourceFiles;

    @Argument(description = "Don't report warnings when errors are suppressed. This only affects K2.", value = "-Xdont-warn-on-error-suppression")
    private boolean dontWarnOnErrorSuppression;

    @Argument(description = "Dump compilation model to specified directory for use in modularized tests.", value = "-XXdump-model", valueDescription = "<dir>")
    private String dumpArgumentsDir;

    @Argument(description = "Dump the backend state into this directory.", value = "-Xdump-directory")
    private String dumpDirectory;

    @Argument(description = "Dump the declaration with the given FqName.", value = "-Xdump-fqname")
    private String dumpOnlyFqName;

    @Argument(description = "Dump detailed performance statistics to the specified file in plain text, JSON or markdown format (it's detected by the file's extension).\nAlso, it supports the placeholder `*` and directory for generating file names based on the module being compiled and the current time stamp.\nExample: `path/to/dir/*.log` creates logs like `path/to/dir/my-module_2025-06-20-12-22-32.log` in plain text format, `path/to/dir/` creates logs like `path/to/dir/my-log_2025-06-20-12-22-32.json`.", value = "-Xdump-perf", valueDescription = "<path>")
    private String dumpPerf;

    @Argument(description = "'expect'/'actual' classes (including interfaces, objects, annotations, enums, and 'actual' typealiases) are in Beta.\nKotlin reports a warning every time you use one of them. You can use this flag to mute the warning.", value = "-Xexpect-actual-classes")
    private boolean expectActualClasses;

    @Argument(description = "Enable experimental language support for explicit backing fields.", value = "-Xexplicit-backing-fields")
    @Enables(feature = LanguageFeature.ExplicitBackingFields)
    private boolean explicitBackingFields;

    @Argument(description = "Enable explicit passing of context arguments using named argument syntax.", value = "-Xexplicit-context-arguments")
    @Enables(feature = LanguageFeature.ExplicitContextArguments)
    private boolean explicitContextArguments;

    @Argument(description = "Enable header compilation mode.\nIn this mode, the compiler produces class files that only contain the 'skeleton' of the classes to be\ncompiled but the method bodies of all the implementations are empty.  This is used to speed up parallel compilation\nbuild systems where header libraries can be used to replace downstream dependencies for which we only need to\nsee the type names and method signatures required to compile a given translation unit. Inline functions are still kept\nwith bodies.", value = "-Xheader-mode")
    private boolean headerMode;

    @Argument(description = "Ignore all compilation exceptions while optimizing some constant expressions.", value = "-Xignore-const-optimization-errors")
    private boolean ignoreConstOptimizationErrors;

    @Argument(description = "Enable incremental compilation.", value = "-Xenable-incremental-compilation")
    private Boolean incrementalCompilation;

    @Argument(description = "Enable experimental inline classes.", value = "-Xinline-classes")
    @Enables(feature = LanguageFeature.InlineClasses)
    private boolean inlineClasses;

    @Argument(description = "Path to 'kotlin-compiler.jar' or the directory where the IntelliJ IDEA configuration files can be found.", value = "-Xintellij-plugin-root", valueDescription = "<path>")
    private String intellijPluginRoot;

    @Argument(description = "Enables `IntrinsicConstEvaluation` language feature.`", value = "-Xintrinsic-const-evaluation")
    @Enables(feature = LanguageFeature.IntrinsicConstEvaluation)
    private boolean intrinsicConstEvaluation;

    @Argument(description = "Path to the Kotlin compiler home directory used for the discovery of runtime libraries.", value = "-kotlin-home", valueDescription = "<path>")
    private String kotlinHome;

    @Argument(description = "Provide source compatibility with the specified version of Kotlin.", value = "-language-version", valueDescription = "<version>")
    private String languageVersion;

    @Argument(description = "Lenient compiler mode. When actuals are missing, placeholder declarations are generated.", value = "-XXlenient-mode")
    private boolean lenientMode;

    @Argument(description = "List backend phases.", value = "-Xlist-phases")
    private boolean listPhases;

    @Argument(description = "Enable experimental language support for local type aliases.", value = "-Xlocal-type-aliases")
    @Enables(feature = LanguageFeature.LocalTypeAliases)
    private boolean localTypeAliases;

    @Argument(deprecatedName = "-Xexpect-actual-linker", description = "Produce a klib that only contains the metadata of declarations.", value = "-Xmetadata-klib")
    private boolean metadataKlib;

    @Argument(description = "Change the metadata version of the generated binary files.", value = "-Xmetadata-version")
    private String metadataVersion;

    @Argument(description = "Enable experimental multi-dollar interpolation.", value = "-Xmulti-dollar-interpolation")
    @Enables(feature = LanguageFeature.MultiDollarInterpolation)
    private boolean multiDollarInterpolation;

    @Argument(description = "Enable language support for multiplatform projects.", value = "-Xmulti-platform")
    @Enables(feature = LanguageFeature.MultiPlatformProjects)
    private boolean multiPlatform;

    @Argument(description = "Enables the following destructuring features:\n-Xname-based-destructuring=only-syntax:   Enables syntax for positional destructuring with square brackets and the full form of name-based destructuring with parentheses;\n-Xname-based-destructuring=name-mismatch: Reports warnings when short form positional destructuring of data classes uses names that don't match the property names;\n-Xname-based-destructuring=complete:      Enables short-form name-based destructuring with parentheses;", value = "-Xname-based-destructuring", valueDescription = "only-syntax|name-mismatch|complete")
    @Enables.Container({@Enables(feature = LanguageFeature.NameBasedDestructuring, ifValueIs = "only-syntax"), @Enables(feature = LanguageFeature.NameBasedDestructuring, ifValueIs = "name-mismatch"), @Enables(feature = LanguageFeature.NameBasedDestructuring, ifValueIs = "complete"), @Enables(feature = LanguageFeature.DeprecateNameMismatchInShortDestructuringWithParentheses, ifValueIs = "name-mismatch"), @Enables(feature = LanguageFeature.DeprecateNameMismatchInShortDestructuringWithParentheses, ifValueIs = "complete"), @Enables(feature = LanguageFeature.EnableNameBasedDestructuringShortForm, ifValueIs = "complete")})
    private String nameBasedDestructuring;

    @Argument(description = "Enable experimental language support for nested type aliases.", value = "-Xnested-type-aliases")
    @Enables(feature = LanguageFeature.NestedTypeAliases)
    private boolean nestedTypeAliases;

    @Argument(description = "Enable the new experimental generic type inference algorithm.", value = "-Xnew-inference")
    @Enables.Container({@Enables(feature = LanguageFeature.NewInference), @Enables(feature = LanguageFeature.SamConversionPerArgument), @Enables(feature = LanguageFeature.FunctionReferenceWithDefaultValueAsOtherType), @Enables(feature = LanguageFeature.DisableCompatibilityModeForNewInference)})
    private boolean newInference;

    @Argument(description = "Do not check for the presence of the 'actual' modifier in multiplatform projects.", value = "-Xno-check-actual")
    private boolean noCheckActual;

    @Argument(description = "Disable method inlining.", value = "-Xno-inline")
    private boolean noInline;

    @Argument(description = "Enable experimental non-local break and continue.", value = "-Xnon-local-break-continue")
    @Enables(feature = LanguageFeature.BreakContinueInInlineLambdas)
    private boolean nonLocalBreakContinue;

    @Argument(description = "Print compiler configuration.", value = "-Xprint-configuration")
    private boolean printConfiguration;

    @Argument(description = "Profile backend phases.", value = "-Xprofile-phases")
    private boolean profilePhases;

    @Argument(deprecatedName = "-Xprogressive", description = "Enable progressive compiler mode.\nIn this mode, deprecations and bug fixes for unstable code take effect immediately\ninstead of going through a graceful migration cycle.\nCode written in progressive mode is backward compatible; however, code written without\nprogressive mode enabled may cause compilation errors in progressive mode.", value = "-progressive")
    private boolean progressiveMode;

    @Argument(description = "Render the internal names of warnings and errors.", value = "-Xrender-internal-diagnostic-names")
    private boolean renderInternalDiagnosticNames;

    @Argument(description = "Run Kotlin REPL (deprecated)", value = "-Xrepl")
    private boolean repl;

    @Argument(description = "Report all warnings even if errors are found.", value = "-Xreport-all-warnings")
    private boolean reportAllWarnings;

    @Argument(description = "Report the source-to-output file mapping.", value = "-Xreport-output-files")
    private boolean reportOutputFiles;

    @Argument(description = "Report detailed performance statistics.", value = "-Xreport-perf")
    private boolean reportPerf;

    @Argument(description = "Evaluate the given Kotlin script (*.kts) file.", value = "-script")
    private boolean script;

    @Argument(description = "Enables the separated compilation scheme, in which common source sets are analyzed against their own dependencies", value = "-Xseparate-kmp-compilation")
    private boolean separateKmpCompilationScheme;

    @Argument(description = "Allow loading classes with bad metadata versions and pre-release classes.", value = "-Xskip-metadata-version-check")
    private boolean skipMetadataVersionCheck;

    @Argument(description = "Allow loading pre-release classes.", value = "-Xskip-prerelease-check")
    private boolean skipPrereleaseCheck;

    @Argument(description = "Enables special features which are relevant only for stdlib compilation.", value = "-Xstdlib-compilation")
    private boolean stdlibCompilation;

    @Argument(description = "Suppress error about API version greater than language version.\nWarning: This is temporary solution (see KT-63712) intended to be used only for stdlib build.", value = "-Xsuppress-api-version-greater-than-language-version-error")
    private boolean suppressApiVersionGreaterThanLanguageVersionError;

    @Argument(description = "Suppress warnings about outdated, inconsistent, or experimental language or API versions.", value = "-Xsuppress-version-warnings")
    private boolean suppressVersionWarnings;

    @Argument(description = "Eliminate builder inference restrictions, for example by allowing type variables to be returned from builder inference calls.", value = "-Xunrestricted-builder-inference")
    @Enables(feature = LanguageFeature.UnrestrictedBuilderInference)
    private boolean unrestrictedBuilderInference;

    @Argument(description = "Enable experimental frontend IR checkers that are not yet ready for production.", value = "-Xuse-fir-experimental-checkers")
    private boolean useFirExperimentalCheckers;

    @Argument(description = "Compile using frontend IR internal incremental compilation.\nWarning: This feature is not yet production-ready.", value = "-Xuse-fir-ic")
    private boolean useFirIC;

    @Argument(description = "Compile using the experimental K2 compiler pipeline. No compatibility guarantees are provided yet.", isObsolete = InlineCodegenUtilsKt.GENERATE_SMAP, value = "-Xuse-k2")
    private boolean useK2;

    @Argument(description = "IR verification mode (no verification by default).", value = "-Xverify-ir", valueDescription = "{none|warning|error}")
    private String verifyIr;

    @Argument(description = "Check that offsets of nested IR elements conform to offsets of their containers. Only has effect if '-Xverify-ir' is not 'none'.", value = "-Xverify-ir-nested-offsets")
    private boolean verifyIrNestedOffsets;

    @Argument(description = "Check for visibility violations in IR when validating it before running any lowerings. Only has effect if '-Xverify-ir' is not 'none'.", value = "-Xverify-ir-visibility")
    private boolean verifyIrVisibility;

    @Argument(description = "Enable experimental language support for when guards.", value = "-Xwhen-guards")
    @Enables(feature = LanguageFeature.WhenGuards)
    private boolean whenGuards;
    private boolean autoAdvanceLanguageVersion = true;
    private boolean autoAdvanceApiVersion = true;

    @Argument(description = "Pass an option to a plugin.", value = "-P", valueDescription = "plugin:<pluginId>:<optionName>=<value>")
    private String[] pluginOptions = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Enables/disables specified language feature.\nWarning: this flag is not intended for production use. If you want to configure the language behaviour use the\n-language-version or corresponding experimental feature flags.", value = "-XXLanguage", valueDescription = "[+-]LanguageFeatureName")
    private String[] manuallyConfiguredFeatures = new String[0];

    @Argument(description = "Force the compiler to report errors on all public API declarations without an explicit return type.\nUse the 'warning' level to issue warnings instead of errors.\nThis flag partially enables functionality of `-Xexplicit-api` flag, so please don't use them altogether", value = "-XXexplicit-return-types", valueDescription = "{strict|warning|disable}")
    private String explicitReturnTypes = "disable";

    @Argument(description = "Sources of the common module that need to be compiled together with this module in multiplatform mode.\nThey should be a subset of sources passed as free arguments.", value = "-Xcommon-sources", valueDescription = "<path>")
    private String[] commonSources = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Register a compiler plugin.", value = "-Xcompiler-plugin", valueDescription = "<path1>,<path2>[=<optionName>=<value>,<optionName>=<value>]")
    private String[] pluginConfigurations = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Specify an execution order constraint for compiler plugins.\nOrder constraint can be specified using the 'pluginId' of compiler plugins.\nThe first specified plugin will be executed before the second plugin.\nMultiple constraints can be specified by repeating this option. Cycles in constraints will cause an error.", value = "-Xcompiler-plugin-order", valueDescription = "<pluginId1>><pluginId2>")
    private String[] pluginOrderConstraints = new String[0];

    @Argument(description = "Disable backend phases.", value = "-Xdisable-phases")
    private String[] disablePhases = new String[0];

    @Argument(description = "Force the compiler to report errors on all public API declarations without an explicit visibility or a return type.\nUse the 'warning' level to issue warnings instead of errors.", value = "-Xexplicit-api", valueDescription = "{strict|warning|disable}")
    private String explicitApi = "disable";

    @Argument(delimiter = Argument.Delimiters.none, description = "Declare common klib dependencies for the specific fragment.\nThis argument is required for any HMPP module except the platform leaf module: it takes dependencies from -cp/-libraries.\nThe argument should be used only if the new compilation scheme is enabled with -Xseparate-kmp-compilation\n", value = "-Xfragment-dependency", valueDescription = "<fragment name>:<path>")
    private String[] fragmentDependencies = new String[0];

    @Argument(delimiter = Argument.Delimiters.none, description = "Declare common klib friend dependencies for the specific fragment.\nThis argument can be specified for any HMPP module except the platform leaf module: it takes dependencies from the platform specific friend module arguments.\nThe argument should be used only if the new compilation scheme is enabled with -Xseparate-kmp-compilation\n", value = "-Xfragment-friend-dependency", valueDescription = "<fragment name>:<path>")
    private String[] fragmentFriendDependencies = new String[0];

    @Argument(description = "Declare that <fromModuleName> refines <onModuleName> with the dependsOn/refines relation.", value = "-Xfragment-refines", valueDescription = "<fromModuleName>:<onModuleName>")
    private String[] fragmentRefines = new String[0];

    @Argument(description = "Add sources to a specific fragment of a multiplatform compilation.", value = "-Xfragment-sources", valueDescription = "<fragment name>:<path>")
    private String[] fragmentSources = new String[0];

    @Argument(description = "Declare all known fragments of a multiplatform compilation.", value = "-Xfragments", valueDescription = "<fragment name>")
    private String[] fragments = new String[0];

    @Argument(description = "Generates output based on what it is used for:\n-Xheader-mode-type=compilation: Skips the IR generation for modules that don't have inline functions.\n-Xheader-mode-type=any: Can be used for any downstream dependency which doesn't require linking.", value = "-Xheader-mode-type", valueDescription = "{any|compilation}")
    private String headerModeType = "any";

    @Argument(description = "Dump the backend's state both before and after these phases.", value = "-Xphases-to-dump")
    private String[] phasesToDump = new String[0];

    @Argument(description = "Dump the backend's state after these phases.", value = "-Xphases-to-dump-after")
    private String[] phasesToDumpAfter = new String[0];

    @Argument(description = "Dump the backend's state before these phases.", value = "-Xphases-to-dump-before")
    private String[] phasesToDumpBefore = new String[0];

    @Argument(description = "Validate the backend's state both before and after these phases.", value = "-Xphases-to-validate")
    private String[] phasesToValidate = new String[0];

    @Argument(description = "Validate the backend's state after these phases.", value = "-Xphases-to-validate-after")
    private String[] phasesToValidateAfter = new String[0];

    @Argument(description = "Validate the backend's state before these phases.", value = "-Xphases-to-validate-before")
    private String[] phasesToValidateBefore = new String[0];

    @Argument(description = "Load plugins from the given classpath.", value = "-Xplugin", valueDescription = "<path>")
    private String[] pluginClasspaths = new String[0];

    @Argument(description = "Set improved unused return value checker mode. Use 'check' to run checker only and use 'full' to also enable automatic annotation insertion.", value = "-Xreturn-value-checker", valueDescription = "{check|full|disable}")
    private String returnValueChecker = "disable";

    @Argument(description = "Suppress specified warning module-wide. This option is deprecated in favor of \"-Xwarning-level\" flag", value = "-Xsuppress-warning", valueDescription = "<WARNING_NAME>")
    private String[] suppressedDiagnostics = new String[0];

    @Argument(description = "Compile using the LightTree parser with the frontend IR.", value = "-Xuse-fir-lt")
    private boolean useFirLT = true;

    @Argument(description = "Be verbose while performing the given backend phases.", value = "-Xverbose-phases")
    private String[] verbosePhases = new String[0];

    @Argument(description = "Set the severity of the given warning.\n- `error` level raises the severity of a warning to error level (similar to -Werror but more granular)\n- `disabled` level suppresses reporting of a warning (similar to -nowarn but more granular)\n- `warning` level overrides -nowarn and -Werror for this specific warning (the warning will be reported/won't be considered as an error)", value = "-Xwarning-level", valueDescription = "<WARNING_NAME>:(error|warning|disabled)")
    private String[] warningLevels = new String[0];

    @Argument(deprecatedName = "-Xopt-in", description = "Enable API usages that require opt-in with an opt-in requirement marker with the given fully qualified name.", value = "-opt-in", valueDescription = "<fq.name>")
    private String[] optIn = new String[0];

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014R\u0016\u0010\u0006\u001a\u00020\u00078\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments$DummyImpl;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "<init>", "()V", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DummyImpl extends CommonCompilerArguments {
        private final transient CommonCompilerArgumentsConfigurator configurator = new CommonCompilerArgumentsConfigurator();

        @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
        public Freezable copyOf() {
            return CommonCompilerArgumentsCopyGeneratedKt.copyCommonCompilerArguments(this, new DummyImpl());
        }

        @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
        @Transient
        public CommonCompilerArgumentsConfigurator getConfigurator() {
            return this.configurator;
        }
    }

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getUseFirExperimentalCheckers$annotations() {
    }

    public final boolean getAllowAnyScriptsInSourceRoots() {
        return this.allowAnyScriptsInSourceRoots;
    }

    public final boolean getAllowConditionImpliesReturnsContracts() {
        return this.allowConditionImpliesReturnsContracts;
    }

    public final boolean getAllowContractsOnMoreFunctions() {
        return this.allowContractsOnMoreFunctions;
    }

    public final boolean getAllowHoldsinContract() {
        return this.allowHoldsinContract;
    }

    public final boolean getAllowKotlinPackage() {
        return this.allowKotlinPackage;
    }

    public final boolean getAllowReifiedTypeInCatch() {
        return this.allowReifiedTypeInCatch;
    }

    public final boolean getAllowReturnsResultOf() {
        return this.allowReturnsResultOf;
    }

    public final String getAnnotationDefaultTarget() {
        return this.annotationDefaultTarget;
    }

    public final boolean getAnnotationTargetAll() {
        return this.annotationTargetAll;
    }

    public final String getApiVersion() {
        return this.apiVersion;
    }

    @Transient
    public final boolean getAutoAdvanceApiVersion() {
        return this.autoAdvanceApiVersion;
    }

    @Transient
    public final boolean getAutoAdvanceLanguageVersion() {
        return this.autoAdvanceLanguageVersion;
    }

    public final boolean getCheckPhaseConditions() {
        return this.checkPhaseConditions;
    }

    public final boolean getCollectionLiterals() {
        return this.collectionLiterals;
    }

    public final String[] getCommonSources() {
        return this.commonSources;
    }

    @Transient
    public abstract CommonCompilerArgumentsConfigurator getConfigurator();

    public final boolean getConsistentDataClassCopyVisibility() {
        return this.consistentDataClassCopyVisibility;
    }

    public final boolean getContextParameters() {
        return this.contextParameters;
    }

    public final boolean getContextReceivers() {
        return this.contextReceivers;
    }

    public final boolean getContextSensitiveResolution() {
        return this.contextSensitiveResolution;
    }

    public final boolean getDataFlowBasedExhaustiveness() {
        return this.dataFlowBasedExhaustiveness;
    }

    public final boolean getDebugLevelCompilerChecks() {
        return this.debugLevelCompilerChecks;
    }

    public final boolean getDetailedPerf() {
        return this.detailedPerf;
    }

    public final boolean getDirectJavaActualization() {
        return this.directJavaActualization;
    }

    public final boolean getDisableDefaultScriptingPlugin() {
        return this.disableDefaultScriptingPlugin;
    }

    public final String[] getDisablePhases() {
        return this.disablePhases;
    }

    public final boolean getDontSortSourceFiles() {
        return this.dontSortSourceFiles;
    }

    public final boolean getDontWarnOnErrorSuppression() {
        return this.dontWarnOnErrorSuppression;
    }

    public final String getDumpArgumentsDir() {
        return this.dumpArgumentsDir;
    }

    public final String getDumpDirectory() {
        return this.dumpDirectory;
    }

    public final String getDumpOnlyFqName() {
        return this.dumpOnlyFqName;
    }

    public final String getDumpPerf() {
        return this.dumpPerf;
    }

    public final boolean getExpectActualClasses() {
        return this.expectActualClasses;
    }

    public final String getExplicitApi() {
        return this.explicitApi;
    }

    public final boolean getExplicitBackingFields() {
        return this.explicitBackingFields;
    }

    public final boolean getExplicitContextArguments() {
        return this.explicitContextArguments;
    }

    public final String getExplicitReturnTypes() {
        return this.explicitReturnTypes;
    }

    public final String[] getFragmentDependencies() {
        return this.fragmentDependencies;
    }

    public final String[] getFragmentFriendDependencies() {
        return this.fragmentFriendDependencies;
    }

    public final String[] getFragmentRefines() {
        return this.fragmentRefines;
    }

    public final String[] getFragmentSources() {
        return this.fragmentSources;
    }

    public final String[] getFragments() {
        return this.fragments;
    }

    public final boolean getHeaderMode() {
        return this.headerMode;
    }

    public final String getHeaderModeType() {
        return this.headerModeType;
    }

    public final boolean getIgnoreConstOptimizationErrors() {
        return this.ignoreConstOptimizationErrors;
    }

    public final Boolean getIncrementalCompilation() {
        return this.incrementalCompilation;
    }

    public final boolean getInlineClasses() {
        return this.inlineClasses;
    }

    public final String getIntellijPluginRoot() {
        return this.intellijPluginRoot;
    }

    public final boolean getIntrinsicConstEvaluation() {
        return this.intrinsicConstEvaluation;
    }

    public final String getKotlinHome() {
        return this.kotlinHome;
    }

    public final String getLanguageVersion() {
        return this.languageVersion;
    }

    public final boolean getLenientMode() {
        return this.lenientMode;
    }

    public final boolean getListPhases() {
        return this.listPhases;
    }

    public final boolean getLocalTypeAliases() {
        return this.localTypeAliases;
    }

    public final String[] getManuallyConfiguredFeatures() {
        return this.manuallyConfiguredFeatures;
    }

    public final boolean getMetadataKlib() {
        return this.metadataKlib;
    }

    public final String getMetadataVersion() {
        return this.metadataVersion;
    }

    public final boolean getMultiDollarInterpolation() {
        return this.multiDollarInterpolation;
    }

    public final boolean getMultiPlatform() {
        return this.multiPlatform;
    }

    public final String getNameBasedDestructuring() {
        return this.nameBasedDestructuring;
    }

    public final boolean getNestedTypeAliases() {
        return this.nestedTypeAliases;
    }

    public final boolean getNewInference() {
        return this.newInference;
    }

    public final boolean getNoCheckActual() {
        return this.noCheckActual;
    }

    public final boolean getNoInline() {
        return this.noInline;
    }

    public final boolean getNonLocalBreakContinue() {
        return this.nonLocalBreakContinue;
    }

    public final String[] getOptIn() {
        return this.optIn;
    }

    public final String[] getPhasesToDump() {
        return this.phasesToDump;
    }

    public final String[] getPhasesToDumpAfter() {
        return this.phasesToDumpAfter;
    }

    public final String[] getPhasesToDumpBefore() {
        return this.phasesToDumpBefore;
    }

    public final String[] getPhasesToValidate() {
        return this.phasesToValidate;
    }

    public final String[] getPhasesToValidateAfter() {
        return this.phasesToValidateAfter;
    }

    public final String[] getPhasesToValidateBefore() {
        return this.phasesToValidateBefore;
    }

    public final String[] getPluginClasspaths() {
        return this.pluginClasspaths;
    }

    public final String[] getPluginConfigurations() {
        return this.pluginConfigurations;
    }

    public final String[] getPluginOptions() {
        return this.pluginOptions;
    }

    public final String[] getPluginOrderConstraints() {
        return this.pluginOrderConstraints;
    }

    public final boolean getPrintConfiguration() {
        return this.printConfiguration;
    }

    public final boolean getProfilePhases() {
        return this.profilePhases;
    }

    public final boolean getProgressiveMode() {
        return this.progressiveMode;
    }

    public final boolean getRenderInternalDiagnosticNames() {
        return this.renderInternalDiagnosticNames;
    }

    public final boolean getRepl() {
        return this.repl;
    }

    public final boolean getReportAllWarnings() {
        return this.reportAllWarnings;
    }

    public final boolean getReportOutputFiles() {
        return this.reportOutputFiles;
    }

    public final boolean getReportPerf() {
        return this.reportPerf;
    }

    public final String getReturnValueChecker() {
        return this.returnValueChecker;
    }

    public final boolean getScript() {
        return this.script;
    }

    public final boolean getSeparateKmpCompilationScheme() {
        return this.separateKmpCompilationScheme;
    }

    public final boolean getSkipMetadataVersionCheck() {
        return this.skipMetadataVersionCheck;
    }

    public final boolean getSkipPrereleaseCheck() {
        return this.skipPrereleaseCheck;
    }

    public final boolean getStdlibCompilation() {
        return this.stdlibCompilation;
    }

    public final boolean getSuppressApiVersionGreaterThanLanguageVersionError() {
        return this.suppressApiVersionGreaterThanLanguageVersionError;
    }

    public final boolean getSuppressVersionWarnings() {
        return this.suppressVersionWarnings;
    }

    public final String[] getSuppressedDiagnostics() {
        return this.suppressedDiagnostics;
    }

    public final boolean getUnrestrictedBuilderInference() {
        return this.unrestrictedBuilderInference;
    }

    public final boolean getUseFirExperimentalCheckers() {
        return this.useFirExperimentalCheckers;
    }

    public final boolean getUseFirIC() {
        return this.useFirIC;
    }

    public final boolean getUseFirLT() {
        return this.useFirLT;
    }

    public final boolean getUseK2() {
        return this.useK2;
    }

    public final String[] getVerbosePhases() {
        return this.verbosePhases;
    }

    public final String getVerifyIr() {
        return this.verifyIr;
    }

    public final boolean getVerifyIrNestedOffsets() {
        return this.verifyIrNestedOffsets;
    }

    public final boolean getVerifyIrVisibility() {
        return this.verifyIrVisibility;
    }

    public final String[] getWarningLevels() {
        return this.warningLevels;
    }

    public final boolean getWhenGuards() {
        return this.whenGuards;
    }

    public final void setAllowAnyScriptsInSourceRoots(boolean z) {
        checkFrozen();
        this.allowAnyScriptsInSourceRoots = z;
    }

    public final void setAllowConditionImpliesReturnsContracts(boolean z) {
        checkFrozen();
        this.allowConditionImpliesReturnsContracts = z;
    }

    public final void setAllowContractsOnMoreFunctions(boolean z) {
        checkFrozen();
        this.allowContractsOnMoreFunctions = z;
    }

    public final void setAllowHoldsinContract(boolean z) {
        checkFrozen();
        this.allowHoldsinContract = z;
    }

    public final void setAllowKotlinPackage(boolean z) {
        checkFrozen();
        this.allowKotlinPackage = z;
    }

    public final void setAllowReifiedTypeInCatch(boolean z) {
        checkFrozen();
        this.allowReifiedTypeInCatch = z;
    }

    public final void setAllowReturnsResultOf(boolean z) {
        checkFrozen();
        this.allowReturnsResultOf = z;
    }

    public final void setAnnotationDefaultTarget(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.annotationDefaultTarget = str;
    }

    public final void setAnnotationTargetAll(boolean z) {
        checkFrozen();
        this.annotationTargetAll = z;
    }

    public final void setApiVersion(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.apiVersion = str;
    }

    public final void setAutoAdvanceApiVersion(boolean z) {
        checkFrozen();
        this.autoAdvanceApiVersion = z;
    }

    public final void setAutoAdvanceLanguageVersion(boolean z) {
        checkFrozen();
        this.autoAdvanceLanguageVersion = z;
    }

    public final void setCheckPhaseConditions(boolean z) {
        checkFrozen();
        this.checkPhaseConditions = z;
    }

    public final void setCollectionLiterals(boolean z) {
        checkFrozen();
        this.collectionLiterals = z;
    }

    public final void setCommonSources(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.commonSources = strArr;
    }

    public final void setConsistentDataClassCopyVisibility(boolean z) {
        checkFrozen();
        this.consistentDataClassCopyVisibility = z;
    }

    public final void setContextParameters(boolean z) {
        checkFrozen();
        this.contextParameters = z;
    }

    public final void setContextReceivers(boolean z) {
        checkFrozen();
        this.contextReceivers = z;
    }

    public final void setContextSensitiveResolution(boolean z) {
        checkFrozen();
        this.contextSensitiveResolution = z;
    }

    public final void setDataFlowBasedExhaustiveness(boolean z) {
        checkFrozen();
        this.dataFlowBasedExhaustiveness = z;
    }

    public final void setDebugLevelCompilerChecks(boolean z) {
        checkFrozen();
        this.debugLevelCompilerChecks = z;
    }

    public final void setDetailedPerf(boolean z) {
        checkFrozen();
        this.detailedPerf = z;
    }

    public final void setDirectJavaActualization(boolean z) {
        checkFrozen();
        this.directJavaActualization = z;
    }

    public final void setDisableDefaultScriptingPlugin(boolean z) {
        checkFrozen();
        this.disableDefaultScriptingPlugin = z;
    }

    public final void setDisablePhases(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.disablePhases = strArr;
    }

    public final void setDontSortSourceFiles(boolean z) {
        checkFrozen();
        this.dontSortSourceFiles = z;
    }

    public final void setDontWarnOnErrorSuppression(boolean z) {
        checkFrozen();
        this.dontWarnOnErrorSuppression = z;
    }

    public final void setDumpArgumentsDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.dumpArgumentsDir = str;
    }

    public final void setDumpDirectory(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.dumpDirectory = str;
    }

    public final void setDumpOnlyFqName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.dumpOnlyFqName = str;
    }

    public final void setDumpPerf(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.dumpPerf = str;
    }

    public final void setExpectActualClasses(boolean z) {
        checkFrozen();
        this.expectActualClasses = z;
    }

    public final void setExplicitApi(String str) {
        str.getClass();
        checkFrozen();
        this.explicitApi = str;
    }

    public final void setExplicitBackingFields(boolean z) {
        checkFrozen();
        this.explicitBackingFields = z;
    }

    public final void setExplicitContextArguments(boolean z) {
        checkFrozen();
        this.explicitContextArguments = z;
    }

    public final void setExplicitReturnTypes(String str) {
        str.getClass();
        checkFrozen();
        this.explicitReturnTypes = str;
    }

    public final void setFragmentDependencies(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.fragmentDependencies = strArr;
    }

    public final void setFragmentFriendDependencies(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.fragmentFriendDependencies = strArr;
    }

    public final void setFragmentRefines(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.fragmentRefines = strArr;
    }

    public final void setFragmentSources(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.fragmentSources = strArr;
    }

    public final void setFragments(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.fragments = strArr;
    }

    public final void setHeaderMode(boolean z) {
        checkFrozen();
        this.headerMode = z;
    }

    public final void setHeaderModeType(String str) {
        str.getClass();
        checkFrozen();
        this.headerModeType = str;
    }

    public final void setIgnoreConstOptimizationErrors(boolean z) {
        checkFrozen();
        this.ignoreConstOptimizationErrors = z;
    }

    public final void setIncrementalCompilation(Boolean bool) {
        checkFrozen();
        this.incrementalCompilation = bool;
    }

    public final void setInlineClasses(boolean z) {
        checkFrozen();
        this.inlineClasses = z;
    }

    public final void setIntellijPluginRoot(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.intellijPluginRoot = str;
    }

    public final void setIntrinsicConstEvaluation(boolean z) {
        checkFrozen();
        this.intrinsicConstEvaluation = z;
    }

    public final void setKotlinHome(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.kotlinHome = str;
    }

    public final void setLanguageVersion(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.languageVersion = str;
    }

    public final void setLenientMode(boolean z) {
        checkFrozen();
        this.lenientMode = z;
    }

    public final void setListPhases(boolean z) {
        checkFrozen();
        this.listPhases = z;
    }

    public final void setLocalTypeAliases(boolean z) {
        checkFrozen();
        this.localTypeAliases = z;
    }

    public final void setManuallyConfiguredFeatures(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.manuallyConfiguredFeatures = strArr;
    }

    public final void setMetadataKlib(boolean z) {
        checkFrozen();
        this.metadataKlib = z;
    }

    public final void setMetadataVersion(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.metadataVersion = str;
    }

    public final void setMultiDollarInterpolation(boolean z) {
        checkFrozen();
        this.multiDollarInterpolation = z;
    }

    public final void setMultiPlatform(boolean z) {
        checkFrozen();
        this.multiPlatform = z;
    }

    public final void setNameBasedDestructuring(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.nameBasedDestructuring = str;
    }

    public final void setNestedTypeAliases(boolean z) {
        checkFrozen();
        this.nestedTypeAliases = z;
    }

    public final void setNewInference(boolean z) {
        checkFrozen();
        this.newInference = z;
    }

    public final void setNoCheckActual(boolean z) {
        checkFrozen();
        this.noCheckActual = z;
    }

    public final void setNoInline(boolean z) {
        checkFrozen();
        this.noInline = z;
    }

    public final void setNonLocalBreakContinue(boolean z) {
        checkFrozen();
        this.nonLocalBreakContinue = z;
    }

    public final void setOptIn(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.optIn = strArr;
    }

    public final void setPhasesToDump(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToDump = strArr;
    }

    public final void setPhasesToDumpAfter(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToDumpAfter = strArr;
    }

    public final void setPhasesToDumpBefore(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToDumpBefore = strArr;
    }

    public final void setPhasesToValidate(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToValidate = strArr;
    }

    public final void setPhasesToValidateAfter(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToValidateAfter = strArr;
    }

    public final void setPhasesToValidateBefore(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.phasesToValidateBefore = strArr;
    }

    public final void setPluginClasspaths(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.pluginClasspaths = strArr;
    }

    public final void setPluginConfigurations(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.pluginConfigurations = strArr;
    }

    public final void setPluginOptions(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.pluginOptions = strArr;
    }

    public final void setPluginOrderConstraints(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.pluginOrderConstraints = strArr;
    }

    public final void setPrintConfiguration(boolean z) {
        checkFrozen();
        this.printConfiguration = z;
    }

    public final void setProfilePhases(boolean z) {
        checkFrozen();
        this.profilePhases = z;
    }

    public final void setProgressiveMode(boolean z) {
        checkFrozen();
        this.progressiveMode = z;
    }

    public final void setRenderInternalDiagnosticNames(boolean z) {
        checkFrozen();
        this.renderInternalDiagnosticNames = z;
    }

    public final void setRepl(boolean z) {
        checkFrozen();
        this.repl = z;
    }

    public final void setReportAllWarnings(boolean z) {
        checkFrozen();
        this.reportAllWarnings = z;
    }

    public final void setReportOutputFiles(boolean z) {
        checkFrozen();
        this.reportOutputFiles = z;
    }

    public final void setReportPerf(boolean z) {
        checkFrozen();
        this.reportPerf = z;
    }

    public final void setReturnValueChecker(String str) {
        str.getClass();
        checkFrozen();
        this.returnValueChecker = str;
    }

    public final void setScript(boolean z) {
        checkFrozen();
        this.script = z;
    }

    public final void setSeparateKmpCompilationScheme(boolean z) {
        checkFrozen();
        this.separateKmpCompilationScheme = z;
    }

    public final void setSkipMetadataVersionCheck(boolean z) {
        checkFrozen();
        this.skipMetadataVersionCheck = z;
    }

    public final void setSkipPrereleaseCheck(boolean z) {
        checkFrozen();
        this.skipPrereleaseCheck = z;
    }

    public final void setStdlibCompilation(boolean z) {
        checkFrozen();
        this.stdlibCompilation = z;
    }

    public final void setSuppressApiVersionGreaterThanLanguageVersionError(boolean z) {
        checkFrozen();
        this.suppressApiVersionGreaterThanLanguageVersionError = z;
    }

    public final void setSuppressVersionWarnings(boolean z) {
        checkFrozen();
        this.suppressVersionWarnings = z;
    }

    public final void setSuppressedDiagnostics(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.suppressedDiagnostics = strArr;
    }

    public final void setUnrestrictedBuilderInference(boolean z) {
        checkFrozen();
        this.unrestrictedBuilderInference = z;
    }

    public final void setUseFirExperimentalCheckers(boolean z) {
        checkFrozen();
        this.useFirExperimentalCheckers = z;
    }

    public final void setUseFirIC(boolean z) {
        checkFrozen();
        this.useFirIC = z;
    }

    public final void setUseFirLT(boolean z) {
        checkFrozen();
        this.useFirLT = z;
    }

    public final void setUseK2(boolean z) {
        checkFrozen();
        this.useK2 = z;
    }

    public final void setVerbosePhases(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.verbosePhases = strArr;
    }

    public final void setVerifyIr(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.verifyIr = str;
    }

    public final void setVerifyIrNestedOffsets(boolean z) {
        checkFrozen();
        this.verifyIrNestedOffsets = z;
    }

    public final void setVerifyIrVisibility(boolean z) {
        checkFrozen();
        this.verifyIrVisibility = z;
    }

    public final void setWarningLevels(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.warningLevels = strArr;
    }

    public final void setWhenGuards(boolean z) {
        checkFrozen();
        this.whenGuards = z;
    }
}
