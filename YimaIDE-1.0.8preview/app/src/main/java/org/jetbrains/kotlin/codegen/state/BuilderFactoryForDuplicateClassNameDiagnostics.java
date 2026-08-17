package org.jetbrains.kotlin.codegen.state;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilderFactory;
import org.jetbrains.kotlin.codegen.ClassNameCollectionClassBuilderFactory;
import org.jetbrains.kotlin.codegen.state.BuilderFactoryForDuplicateClassNameDiagnostics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J)\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0012\"\u00020\u000bH\u0002¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/BuilderFactoryForDuplicateClassNameDiagnostics;", "Lorg/jetbrains/kotlin/codegen/ClassNameCollectionClassBuilderFactory;", "builderFactory", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "className", "Ljava/util/concurrent/ConcurrentHashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "handleClashingNames", Argument.Delimiters.none, "internalName", "origin", "reportError", "another", Argument.Delimiters.none, "(Ljava/lang/String;[Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;)V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BuilderFactoryForDuplicateClassNameDiagnostics extends ClassNameCollectionClassBuilderFactory {
    private final ConcurrentHashMap<String, JvmDeclarationOrigin> className;
    private final GenerationState state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BuilderFactoryForDuplicateClassNameDiagnostics(ClassBuilderFactory classBuilderFactory, GenerationState generationState) {
        super(classBuilderFactory);
        classBuilderFactory.getClass();
        generationState.getClass();
        this.state = generationState;
        this.className = new ConcurrentHashMap<>();
    }

    public static CharSequence a(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return DescriptorRenderer.ONLY_NAMES_WITH_SHORT_TYPES.render(declarationDescriptor);
    }

    private final void reportError(String internalName, JvmDeclarationOrigin... another) {
        ArrayList arrayList = new ArrayList();
        for (JvmDeclarationOrigin jvmDeclarationOrigin : another) {
            DeclarationDescriptor descriptor = jvmDeclarationOrigin.getDescriptor();
            if (descriptor != null) {
                arrayList.add(descriptor);
            }
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: s11
            public final Object invoke(Object obj) {
                return BuilderFactoryForDuplicateClassNameDiagnostics.a((DeclarationDescriptor) obj);
            }
        }, 31, (Object) null);
        for (JvmDeclarationOrigin jvmDeclarationOrigin2 : another) {
            this.state.getReportDuplicateClassNameError().invoke(jvmDeclarationOrigin2, internalName, strJoinToString$default);
        }
    }

    @Override // org.jetbrains.kotlin.codegen.ClassNameCollectionClassBuilderFactory
    public void handleClashingNames(String internalName, JvmDeclarationOrigin origin) {
        internalName.getClass();
        origin.getClass();
        ConcurrentHashMap<String, JvmDeclarationOrigin> concurrentHashMap = this.className;
        JvmDeclarationOrigin jvmDeclarationOrigin = concurrentHashMap.get(internalName);
        if (jvmDeclarationOrigin == null) {
            JvmDeclarationOrigin jvmDeclarationOriginPutIfAbsent = concurrentHashMap.putIfAbsent(internalName, origin);
            jvmDeclarationOrigin = jvmDeclarationOriginPutIfAbsent == null ? origin : jvmDeclarationOriginPutIfAbsent;
        }
        JvmDeclarationOrigin jvmDeclarationOrigin2 = jvmDeclarationOrigin;
        if (Intrinsics.areEqual(origin.getOriginalSourceElement(), jvmDeclarationOrigin2.getOriginalSourceElement())) {
            return;
        }
        reportError(internalName, origin, jvmDeclarationOrigin2);
    }
}
