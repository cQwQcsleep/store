package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.jvm.codegen.JvmIrConflictingDeclarationsData;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.Renderers;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.descriptors.IrBasedDescriptorsKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.kotlin.resolve.MemberComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/JvmIrConflictingDeclarationsData;", "", "signature", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "declarations", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;Ljava/util/Collection;)V", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "getDeclarations", "()Ljava/util/Collection;", "render", "", "Companion", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmIrConflictingDeclarationsData {
    private static final ContextIndependentParameterRenderer<JvmIrConflictingDeclarationsData> renderer;
    private final Collection<IrDeclaration> declarations;
    private final JvmMemberSignature signature;

    static {
        MemberComparator memberComparator = MemberComparator.INSTANCE;
        memberComparator.getClass();
        renderer = CommonRenderers.renderConflictingSignatureData$default("JVM", memberComparator, Renderers.WITHOUT_MODIFIERS, new Function2() { // from class: ry7
            public final Object invoke(Object obj, Object obj2) {
                return JvmIrConflictingDeclarationsData.b((StringBuilder) obj, (JvmIrConflictingDeclarationsData) obj2);
            }
        }, new Function1() { // from class: sy7
            public final Object invoke(Object obj) {
                return JvmIrConflictingDeclarationsData.a((JvmIrConflictingDeclarationsData) obj);
            }
        }, (Function1) null, 32, (Object) null);
    }

    public JvmIrConflictingDeclarationsData(JvmMemberSignature jvmMemberSignature, Collection<? extends IrDeclaration> collection) {
        jvmMemberSignature.getClass();
        collection.getClass();
        this.signature = jvmMemberSignature;
        this.declarations = collection;
    }

    public static Collection a(JvmIrConflictingDeclarationsData jvmIrConflictingDeclarationsData) {
        Collection<IrDeclaration> collection = jvmIrConflictingDeclarationsData.declarations;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(IrBasedDescriptorsKt.toIrBasedDescriptor((IrDeclaration) it.next()));
        }
        return arrayList;
    }

    public static Unit b(StringBuilder sb, JvmIrConflictingDeclarationsData jvmIrConflictingDeclarationsData) {
        sb.getClass();
        jvmIrConflictingDeclarationsData.getClass();
        sb.append(jvmIrConflictingDeclarationsData.signature.getName());
        sb.append(jvmIrConflictingDeclarationsData.signature.getDesc());
        return Unit.INSTANCE;
    }

    private static final Collection<DeclarationDescriptor> renderer$lambda$1(JvmIrConflictingDeclarationsData jvmIrConflictingDeclarationsData) {
        throw null;
    }

    public final Collection<IrDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final JvmMemberSignature getSignature() {
        return this.signature;
    }

    public final String render() {
        return renderer.render(this);
    }
}
