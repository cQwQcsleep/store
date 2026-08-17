package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0001\u0018\u00002\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0096\u0002J0\u0010\u0015\u001a\u0002H\u0017\"\b\b\u0000\u0010\u0018*\u00020\u0019\"\u000e\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00180\u00022\u0006\u0010\u001a\u001a\u0002H\u0017H\u0096\u0002¢\u0006\u0002\u0010\u001bJl\u0010\u001c\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u001d\"\b\b\u0001\u0010\u0018*\u0002H\u001d2\u0006\u0010\u001e\u001a\u0002H\u00182\"\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d0\bj\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d`\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001d0\r2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d0\"H\u0082\b¢\u0006\u0002\u0010#J\u0006\u0010$\u001a\u00020\u0003J\u008d\u0001\u0010%\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u001d2\"\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d0\bj\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d`\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001d0\r2K\u0010&\u001aG\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00030'H\u0002J%\u0010-\u001a\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010/J!\u00100\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u0002012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00102J!\u00103\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u0002042\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00105J!\u00106\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u0002072\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00108J!\u00109\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020:2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010;J!\u0010<\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020=2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010>J!\u0010?\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020@2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010AJ!\u0010B\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020C2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010DJ!\u0010E\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020F2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010GJ!\u0010H\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020I2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010JJ!\u0010K\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020L2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010MJ!\u0010N\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020O2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010PJ!\u0010Q\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020R2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010SJ!\u0010T\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020U2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010VJ!\u0010W\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020X2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010YJ!\u0010Z\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020[2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\\J!\u0010]\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020^2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010_J!\u0010`\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020a2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010bJ!\u0010c\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020d2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010eJ!\u0010f\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020g2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010hJ!\u0010i\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020j2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010kJ!\u0010l\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020m2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010nJ!\u0010o\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020p2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010qJ!\u0010r\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020s2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010tJ!\u0010u\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020v2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010wJ!\u0010x\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020y2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010zJ!\u0010{\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020|2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010}J\"\u0010~\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020\u007f2\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0080\u0001J$\u0010\u0081\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0082\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0083\u0001J$\u0010\u0084\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0085\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0086\u0001J$\u0010\u0087\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0088\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0089\u0001J$\u0010\u008a\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u008b\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u008c\u0001J$\u0010\u008d\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u008e\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u008f\u0001J$\u0010\u0090\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0091\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0092\u0001J$\u0010\u0093\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0094\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0095\u0001J$\u0010\u0096\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0097\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0098\u0001J$\u0010\u0099\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u009a\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u009b\u0001J$\u0010\u009c\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u009d\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u009e\u0001J$\u0010\u009f\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030 \u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¡\u0001J$\u0010¢\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030£\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¤\u0001J$\u0010¥\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¦\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010§\u0001J$\u0010¨\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030©\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ª\u0001J$\u0010«\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¬\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u00ad\u0001J$\u0010®\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¯\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010°\u0001J$\u0010±\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030²\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010³\u0001J$\u0010´\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030µ\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¶\u0001J$\u0010·\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¸\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¹\u0001J$\u0010º\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030»\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¼\u0001J$\u0010½\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¾\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¿\u0001J$\u0010À\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Á\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Â\u0001J$\u0010Ã\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ä\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Å\u0001J$\u0010Æ\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ç\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010È\u0001J$\u0010É\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ê\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Ë\u0001J$\u0010Ì\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Í\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Î\u0001J$\u0010Ï\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ð\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Ñ\u0001J$\u0010Ò\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ó\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Ô\u0001J$\u0010Õ\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ö\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010×\u0001J$\u0010Ø\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ù\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Ú\u0001J$\u0010Û\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030Ü\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010Ý\u0001J$\u0010Þ\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ß\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010à\u0001J$\u0010á\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030â\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ã\u0001J$\u0010ä\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030å\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010æ\u0001J$\u0010ç\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030è\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010é\u0001J$\u0010ê\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ë\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ì\u0001J$\u0010í\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030î\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ï\u0001J$\u0010ð\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ñ\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ò\u0001J$\u0010ó\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ô\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010õ\u0001J$\u0010ö\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030÷\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010ø\u0001J$\u0010ù\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ú\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010û\u0001J$\u0010ü\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ý\u00012\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010þ\u0001J$\u0010ÿ\u0001\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0080\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0081\u0002J$\u0010\u0082\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0083\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0084\u0002J$\u0010\u0085\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0086\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0087\u0002J$\u0010\u0088\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0089\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u008a\u0002J$\u0010\u008b\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u008c\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u008d\u0002J$\u0010\u008e\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u008f\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0090\u0002J$\u0010\u0091\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0092\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0093\u0002J$\u0010\u0094\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0095\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0096\u0002J$\u0010\u0097\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u0098\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u0099\u0002J$\u0010\u009a\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u009b\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u009c\u0002J$\u0010\u009d\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u009e\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010\u009f\u0002J$\u0010 \u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¡\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¢\u0002J$\u0010£\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030¤\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¥\u0002J$\u0010¦\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030§\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010¨\u0002J$\u0010©\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030ª\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010«\u0002J$\u0010¬\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030\u00ad\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010®\u0002J$\u0010¯\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030°\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010±\u0002J$\u0010²\u0002\u001a\u0006\u0012\u0002\b\u00030\u00022\u0007\u0010\u001a\u001a\u00030³\u00022\u0006\u0010.\u001a\u00020\u0003H\u0016¢\u0006\u0003\u0010´\u0002R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010\u000b\u001a.\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\bj\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014Ê\u0001\u0003\b¶\u0002¨\u0006µ\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphCopier;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "<init>", "()V", "cachedGraphs", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "Lkotlin/collections/HashMap;", "cachedNodes", "unprocessedGraphs", "Lkotlin/collections/ArrayDeque;", "unprocessedNodes", "isFinished", Argument.Delimiters.none, "graphMapping", Argument.Delimiters.none, "getGraphMapping", "()Ljava/util/Map;", "get", "graph", "N", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "node", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "getCached", "I", "entity", "entityCache", "entityQueue", "copier", "Lkotlin/Function1;", "(Ljava/lang/Object;Ljava/util/HashMap;Lkotlin/collections/ArrayDeque;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "finish", "postProcess", "processor", "Lkotlin/Function3;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "newEntity", "oldEntity", "mapper", "visitNode", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLocalFunctionDeclarationNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitEnterValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitEnterDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitExitDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitExitValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitSplitPostponedLambdasNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitPostponedLambdaExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitMergePostponedLambdaExitsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitAnonymousFunctionCaptureNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitAnonymousFunctionExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFileEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFileExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitAnonymousObjectEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitAnonymousObjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitClassEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLocalClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitScriptEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitScriptExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCodeFragmentEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCodeFragmentExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitPropertyInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitPropertyInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitDelegateExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFieldInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFieldInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitInitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitInitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenBranchConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenBranchConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenBranchResultEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenBranchResultExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenSyntheticElseBranchNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLoopExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitTryExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitTryMainBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitTryMainBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCatchClauseEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCatchClauseExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFinallyBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFinallyBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitTryExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBooleanOperatorEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBooleanOperatorExitLeftOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBooleanOperatorEnterRightOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitBooleanOperatorExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitTypeOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitComparisonExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitEqualityOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitJumpNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitLiteralExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCheckNotNullCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitResolvedQualifierNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionCallArgumentsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionCallArgumentsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionCallEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionCallExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitCallableReferenceNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitGetClassCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitDelegatedConstructorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitStringConcatenationCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitThrowExceptionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitStubNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitVariableDeclarationEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitEnterSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitExitSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitWhenSubjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitElvisLhsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitElvisLhsIsNotNullNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitElvisRhsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitElvisExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitSmartCastExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFakeExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;Lkotlin/Unit;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@CfgInternals
public final class ControlFlowGraphCopier extends ControlFlowGraphVisitor<CFGNode<?>, Unit> implements ControlFlowNodeMapper {
    private boolean isFinished;
    private final HashMap<ControlFlowGraph, ControlFlowGraph> cachedGraphs = new HashMap<>();
    private final HashMap<CFGNode<?>, CFGNode<?>> cachedNodes = new HashMap<>();
    private final ArrayDeque<ControlFlowGraph> unprocessedGraphs = new ArrayDeque<>();
    private final ArrayDeque<CFGNode<?>> unprocessedNodes = new ArrayDeque<>();

    private final <I> void postProcess(HashMap<I, I> entityCache, ArrayDeque<I> entityQueue, Function3<? super I, ? super I, ? super ControlFlowNodeMapper, Unit> processor) {
        while (!entityQueue.isEmpty()) {
            Object objRemoveFirst = entityQueue.removeFirst();
            I i = entityCache.get(objRemoveFirst);
            if (i == null) {
                k2d.a("Unprocessed entity must be cached");
                return;
            }
            processor.invoke(i, objRemoveFirst, this);
        }
    }

    public final void finish() {
        if (this.isFinished) {
            k2d.a("The copier has already finished node processing");
            return;
        }
        this.isFinished = true;
        while (true) {
            if (this.unprocessedGraphs.isEmpty() && this.unprocessedNodes.isEmpty()) {
                return;
            }
            postProcess(this.cachedGraphs, this.unprocessedGraphs, AnonymousClass1.INSTANCE);
            postProcess(this.cachedNodes, this.unprocessedNodes, AnonymousClass2.INSTANCE);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowNodeMapper
    public ControlFlowGraph get(ControlFlowGraph graph) {
        graph.getClass();
        HashMap<ControlFlowGraph, ControlFlowGraph> map = this.cachedGraphs;
        ArrayDeque<ControlFlowGraph> arrayDeque = this.unprocessedGraphs;
        ControlFlowGraph controlFlowGraph = map.get(graph);
        if (controlFlowGraph == null) {
            controlFlowGraph = new ControlFlowGraph(graph.getDeclaration(), graph.getName(), graph.getKind());
            if (Intrinsics.areEqual(controlFlowGraph, graph)) {
                w01.a("Failed requirement.");
                return null;
            }
            map.put(graph, controlFlowGraph);
            arrayDeque.addLast(graph);
        }
        return controlFlowGraph;
    }

    public final Map<ControlFlowGraph, ControlFlowGraph> getGraphMapping() {
        if (this.isFinished) {
            return this.cachedGraphs;
        }
        k2d.a("Call 'finish()' first");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitAnonymousFunctionCaptureNode(AnonymousFunctionCaptureNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new AnonymousFunctionCaptureNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitAnonymousFunctionExpressionNode(AnonymousFunctionExpressionNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new AnonymousFunctionExpressionNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitAnonymousObjectEnterNode(AnonymousObjectEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new AnonymousObjectEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitAnonymousObjectExpressionExitNode(AnonymousObjectExpressionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new AnonymousObjectExpressionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBlockEnterNode(BlockEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BlockEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBlockExitNode(BlockExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BlockExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBooleanOperatorEnterNode(BooleanOperatorEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BooleanOperatorEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBooleanOperatorEnterRightOperandNode(BooleanOperatorEnterRightOperandNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BooleanOperatorEnterRightOperandNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBooleanOperatorExitLeftOperandNode(BooleanOperatorExitLeftOperandNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BooleanOperatorExitLeftOperandNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitBooleanOperatorExitNode(BooleanOperatorExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new BooleanOperatorExitNode(get(node.getOwner()), node.getFir(), get(node.getLeftOperandNode()), get(node.getRightOperandNode()), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCallableReferenceNode(CallableReferenceNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CallableReferenceNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCatchClauseEnterNode(CatchClauseEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CatchClauseEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCatchClauseExitNode(CatchClauseExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CatchClauseExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCheckNotNullCallNode(CheckNotNullCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CheckNotNullCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitClassEnterNode(ClassEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ClassEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitClassExitNode(ClassExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ClassExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCodeFragmentEnterNode(CodeFragmentEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CodeFragmentEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitCodeFragmentExitNode(CodeFragmentExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new CodeFragmentExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitComparisonExpressionNode(ComparisonExpressionNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ComparisonExpressionNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitDelegateExpressionExitNode(DelegateExpressionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new DelegateExpressionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitDelegatedConstructorCallNode(DelegatedConstructorCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new DelegatedConstructorCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitElvisExitNode(ElvisExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ElvisExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitElvisLhsExitNode(ElvisLhsExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ElvisLhsExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitElvisLhsIsNotNullNode(ElvisLhsIsNotNullNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ElvisLhsIsNotNullNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitElvisRhsEnterNode(ElvisRhsEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ElvisRhsEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitEnterDefaultArgumentsNode(EnterDefaultArgumentsNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new EnterDefaultArgumentsNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitEnterSafeCallNode(EnterSafeCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new EnterSafeCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitEnterValueParameterNode(EnterValueParameterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new EnterValueParameterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitEqualityOperatorCallNode(EqualityOperatorCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new EqualityOperatorCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitExitDefaultArgumentsNode(ExitDefaultArgumentsNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ExitDefaultArgumentsNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitExitSafeCallNode(ExitSafeCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ExitSafeCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitExitValueParameterNode(ExitValueParameterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ExitValueParameterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFakeExpressionEnterNode(FakeExpressionEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FakeExpressionEnterNode(get(node.getOwner()), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFieldInitializerEnterNode(FieldInitializerEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FieldInitializerEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFieldInitializerExitNode(FieldInitializerExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FieldInitializerExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFileEnterNode(FileEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FileEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFileExitNode(FileExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FileExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFinallyBlockEnterNode(FinallyBlockEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FinallyBlockEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFinallyBlockExitNode(FinallyBlockExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FinallyBlockExitNode(get(node.getOwner()), node.getFir(), (FinallyBlockEnterNode) get(node.getEnterNode()), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionCallArgumentsEnterNode(FunctionCallArgumentsEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionCallArgumentsEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionCallArgumentsExitNode(FunctionCallArgumentsExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionCallArgumentsExitNode(get(node.getOwner()), node.getFir(), get(node.getExplicitReceiverExitNode()), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionCallEnterNode(FunctionCallEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionCallEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionCallExitNode(FunctionCallExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionCallExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionEnterNode(FunctionEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitFunctionExitNode(FunctionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new FunctionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitGetClassCallNode(GetClassCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new GetClassCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitInitBlockEnterNode(InitBlockEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new InitBlockEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitInitBlockExitNode(InitBlockExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new InitBlockExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitJumpNode(JumpNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new JumpNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLiteralExpressionNode(LiteralExpressionNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LiteralExpressionNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLocalClassExitNode(LocalClassExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LocalClassExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLocalFunctionDeclarationNode(LocalFunctionDeclarationNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LocalFunctionDeclarationNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopBlockEnterNode(LoopBlockEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopBlockEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopBlockExitNode(LoopBlockExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopBlockExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopConditionEnterNode(LoopConditionEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopConditionEnterNode(get(node.getOwner()), node.getFir(), node.getLoop(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopConditionExitNode(LoopConditionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopConditionExitNode(get(node.getOwner()), node.getFir(), node.getLoop(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopEnterNode(LoopEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitLoopExitNode(LoopExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new LoopExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitMergePostponedLambdaExitsNode(MergePostponedLambdaExitsNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new MergePostponedLambdaExitsNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    /* JADX INFO: renamed from: visitNode, reason: avoid collision after fix types in other method */
    public CFGNode<?> visitNode2(CFGNode<?> node, Unit data) {
        node.getClass();
        data.getClass();
        throw new IllegalStateException(("Copying is not implemented for " + Reflection.getOrCreateKotlinClass(node.getClass()).getSimpleName()).toString());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitPostponedLambdaExitNode(PostponedLambdaExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new PostponedLambdaExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitPropertyInitializerEnterNode(PropertyInitializerEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new PropertyInitializerEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitPropertyInitializerExitNode(PropertyInitializerExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new PropertyInitializerExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitQualifiedAccessNode(QualifiedAccessNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new QualifiedAccessNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitResolvedQualifierNode(ResolvedQualifierNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ResolvedQualifierNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitScriptEnterNode(ScriptEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ScriptEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitScriptExitNode(ScriptExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ScriptExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitSmartCastExpressionExitNode(SmartCastExpressionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new SmartCastExpressionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitSplitPostponedLambdasNode(SplitPostponedLambdasNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new SplitPostponedLambdasNode(get(node.getOwner()), node.getFir(), node.getLambdas(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitStringConcatenationCallNode(StringConcatenationCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new StringConcatenationCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitStubNode(StubNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new StubNode(get(node.getOwner()), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitThrowExceptionNode(ThrowExceptionNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new ThrowExceptionNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitTryExpressionEnterNode(TryExpressionEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new TryExpressionEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitTryExpressionExitNode(TryExpressionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new TryExpressionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitTryMainBlockEnterNode(TryMainBlockEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new TryMainBlockEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitTryMainBlockExitNode(TryMainBlockExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new TryMainBlockExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitTypeOperatorCallNode(TypeOperatorCallNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new TypeOperatorCallNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitVariableAssignmentNode(VariableAssignmentNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new VariableAssignmentNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitVariableDeclarationEnterNode(VariableDeclarationEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new VariableDeclarationEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitVariableDeclarationExitNode(VariableDeclarationExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new VariableDeclarationExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenBranchConditionEnterNode(WhenBranchConditionEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenBranchConditionEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenBranchConditionExitNode(WhenBranchConditionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenBranchConditionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenBranchResultEnterNode(WhenBranchResultEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenBranchResultEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenBranchResultExitNode(WhenBranchResultExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenBranchResultExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenEnterNode(WhenEnterNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenEnterNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenExitNode(WhenExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenSubjectExpressionExitNode(WhenSubjectExpressionExitNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenSubjectExpressionExitNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public CFGNode<?> visitWhenSyntheticElseBranchNode(WhenSyntheticElseBranchNode node, Unit data) {
        node.getClass();
        data.getClass();
        return new WhenSyntheticElseBranchNode(get(node.getOwner()), node.getFir(), node.getLevel());
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphCopier$finish$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<ControlFlowGraph, ControlFlowGraph, ControlFlowNodeMapper, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, ControlFlowGraph.class, "copyData", "copyData(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public final void invoke(ControlFlowGraph controlFlowGraph, ControlFlowGraph controlFlowGraph2, ControlFlowNodeMapper controlFlowNodeMapper) throws UninitializedPropertyAccessException {
            controlFlowGraph.getClass();
            controlFlowGraph2.getClass();
            controlFlowNodeMapper.getClass();
            controlFlowGraph.copyData(controlFlowGraph2, controlFlowNodeMapper);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) throws UninitializedPropertyAccessException {
            invoke((ControlFlowGraph) obj, (ControlFlowGraph) obj2, (ControlFlowNodeMapper) obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphCopier$finish$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function3<CFGNode<?>, CFGNode<?>, ControlFlowNodeMapper, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(3, CFGNode.class, "copyData", "copyData(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;)V", 0);
        }

        public final void invoke(CFGNode<?> cFGNode, CFGNode<?> cFGNode2, ControlFlowNodeMapper controlFlowNodeMapper) {
            cFGNode.getClass();
            cFGNode2.getClass();
            controlFlowNodeMapper.getClass();
            cFGNode.copyData(cFGNode2, controlFlowNodeMapper);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((CFGNode<?>) obj, (CFGNode<?>) obj2, (ControlFlowNodeMapper) obj3);
            return Unit.INSTANCE;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ CFGNode<?> visitNode(CFGNode cFGNode, Unit unit) {
        return visitNode2((CFGNode<?>) cFGNode, unit);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowNodeMapper
    public <E extends FirElement, N extends CFGNode<? extends E>> N get(N node) {
        node.getClass();
        HashMap<CFGNode<?>, CFGNode<?>> map = this.cachedNodes;
        ArrayDeque<CFGNode<?>> arrayDeque = this.unprocessedNodes;
        CFGNode<?> cFGNode = map.get(node);
        if (cFGNode == null) {
            cFGNode = (CFGNode) node.accept(this, Unit.INSTANCE);
            if (!Intrinsics.areEqual(cFGNode, node)) {
                map.put(node, cFGNode);
                arrayDeque.addLast(node);
            } else {
                w01.a("Failed requirement.");
                return null;
            }
        }
        return (N) cFGNode;
    }
}
