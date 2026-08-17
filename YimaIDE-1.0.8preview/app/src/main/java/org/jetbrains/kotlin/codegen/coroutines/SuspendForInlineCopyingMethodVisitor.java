package org.jetbrains.kotlin.codegen.coroutines;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.TransformationMethodVisitor;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012:\u0010\t\u001a6\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f\u0012\u0004\u0012\u00020\u00030\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014RB\u0010\t\u001a6\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f\u0012\u0004\u0012\u00020\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/SuspendForInlineCopyingMethodVisitor;", "Lorg/jetbrains/kotlin/codegen/TransformationMethodVisitor;", "delegate", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "newMethod", "Lkotlin/Function6;", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", Argument.Delimiters.none, "keepAccess", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/MethodVisitor;ILjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function6;Z)V", "performTransformations", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SuspendForInlineCopyingMethodVisitor extends TransformationMethodVisitor {
    private final boolean keepAccess;
    private final Function6<JvmDeclarationOrigin, Integer, String, String, String, String[], MethodVisitor> newMethod;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendForInlineCopyingMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, Function6<? super JvmDeclarationOrigin, ? super Integer, ? super String, ? super String, ? super String, ? super String[], ? extends MethodVisitor> function6, boolean z) {
        super(methodVisitor, i, str, str2, null, null, 0, 64, null);
        methodVisitor.getClass();
        str.getClass();
        str2.getClass();
        function6.getClass();
        this.newMethod = function6;
        this.keepAccess = z;
    }

    @Override // org.jetbrains.kotlin.codegen.TransformationMethodVisitor
    public void performTransformations(MethodNode methodNode) {
        methodNode.getClass();
        int i = this.keepAccess ? methodNode.access : (methodNode.access | 2) & (-6);
        String str = methodNode.name + CoroutineTransformerKt.FOR_INLINE_SUFFIX;
        String str2 = methodNode.desc;
        String str3 = methodNode.signature;
        List list = methodNode.exceptions;
        list.getClass();
        MethodNode methodNode2 = new MethodNode(i, str, str2, str3, (String[]) list.toArray(new String[0]));
        Function6<JvmDeclarationOrigin, Integer, String, String, String, String[], MethodVisitor> function6 = this.newMethod;
        JvmDeclarationOrigin jvmDeclarationOrigin = JvmDeclarationOrigin.NO_ORIGIN_SUSPEND_FOR_INLINE;
        Integer numValueOf = Integer.valueOf(methodNode2.access);
        String str4 = methodNode2.name;
        str4.getClass();
        String str5 = methodNode2.desc;
        str5.getClass();
        String str6 = methodNode2.signature;
        List list2 = methodNode2.exceptions;
        list2.getClass();
        MethodVisitor methodVisitor = (MethodVisitor) function6.invoke(jvmDeclarationOrigin, numValueOf, str4, str5, str6, list2.toArray(new String[0]));
        methodNode.instructions.resetLabels();
        methodNode.accept(methodNode2);
        InlineCodegenUtilsKt.preprocessSuspendMarkers(methodNode, false, false);
        InlineCodegenUtilsKt.preprocessSuspendMarkers(methodNode2, true, true);
        methodNode2.accept(methodVisitor);
    }

    public /* synthetic */ SuspendForInlineCopyingMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, Function6 function6, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(methodVisitor, i, str, str2, function6, (i2 & 32) != 0 ? true : z);
    }
}
