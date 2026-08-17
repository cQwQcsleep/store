package org.jetbrains.kotlin.codegen.optimization.common;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003BW\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012 \b\u0002\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u000e\u001a\u0002\b\u0012¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/FastMethodAnalyzer;", "V", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "Lorg/jetbrains/kotlin/codegen/optimization/common/FastAnalyzer;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "owner", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "pruneExceptionEdges", Argument.Delimiters.none, "newFrame", "Lkotlin/Function2;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;ZLkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/JvmOverloads;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastMethodAnalyzer<V extends Value> extends FastAnalyzer<V, Frame<V>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FastMethodAnalyzer(String str, MethodNode methodNode, Interpreter<V> interpreter, boolean z, Function2<? super Integer, ? super Integer, ? extends Frame<V>> function2) {
        super(str, methodNode, interpreter, z, false, false, function2);
        str.getClass();
        methodNode.getClass();
        interpreter.getClass();
        function2.getClass();
    }

    public static Frame a(int i, int i2) {
        return new Frame(i, i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FastMethodAnalyzer(String str, MethodNode methodNode, Interpreter<V> interpreter, boolean z) {
        this(str, methodNode, interpreter, z, null, 16, null);
        str.getClass();
        methodNode.getClass();
        interpreter.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FastMethodAnalyzer(String str, MethodNode methodNode, Interpreter<V> interpreter) {
        this(str, methodNode, interpreter, false, null, 24, null);
        str.getClass();
        methodNode.getClass();
        interpreter.getClass();
    }

    public /* synthetic */ FastMethodAnalyzer(String str, MethodNode methodNode, Interpreter interpreter, boolean z, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, methodNode, interpreter, (i & 8) != 0 ? false : z, (i & 16) != 0 ? new Function2() { // from class: ao4
            public final Object invoke(Object obj, Object obj2) {
                return FastMethodAnalyzer.a(((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        } : function2);
    }
}
