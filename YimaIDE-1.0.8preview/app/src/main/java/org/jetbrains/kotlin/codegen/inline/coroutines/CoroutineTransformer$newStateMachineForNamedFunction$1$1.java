package org.jetbrains.kotlin.codegen.inline.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CoroutineTransformer$newStateMachineForNamedFunction$1$1 extends FunctionReferenceImpl implements Function6<JvmDeclarationOrigin, Integer, String, String, String, String[], MethodVisitor> {
    public CoroutineTransformer$newStateMachineForNamedFunction$1$1(Object obj) {
        super(6, obj, ClassBuilder.class, "newMethod", "newMethod(Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", 0);
    }

    public final MethodVisitor invoke(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, String[] strArr) {
        jvmDeclarationOrigin.getClass();
        str.getClass();
        str2.getClass();
        return ((ClassBuilder) ((CallableReference) this).receiver).newMethod(jvmDeclarationOrigin, i, str, str2, str3, strArr);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return invoke((JvmDeclarationOrigin) obj, ((Number) obj2).intValue(), (String) obj3, (String) obj4, (String) obj5, (String[]) obj6);
    }
}
