package org.jetbrains.kotlin.ir.descriptors;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.ir.descriptors.LazyTypeConstructor;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.AbstractClassTypeConstructor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u0019\u0010\u0015R\u0014\u0010 \u001a\u00020!8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/ir/descriptors/LazyTypeConstructor;", "Lorg/jetbrains/kotlin/types/AbstractClassTypeConstructor;", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "parametersBuilder", "Lkotlin/Function0;", "", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "superTypesBuilder", "Lorg/jetbrains/kotlin/types/KotlinType;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "getClassDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getParametersBuilder", "()Lkotlin/jvm/functions/Function0;", "getSuperTypesBuilder", "parameters_", "getParameters_", "()Ljava/util/List;", "parameters_$delegate", "Lkotlin/Lazy;", "superTypes_", "getSuperTypes_", "superTypes_$delegate", "getParameters", "computeSupertypes", "isDenotable", "", "getDeclarationDescriptor", "supertypeLoopChecker", "Lorg/jetbrains/kotlin/descriptors/SupertypeLoopChecker;", "getSupertypeLoopChecker", "()Lorg/jetbrains/kotlin/descriptors/SupertypeLoopChecker;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LazyTypeConstructor extends AbstractClassTypeConstructor {
    private final ClassDescriptor classDescriptor;
    private final Function0<List<TypeParameterDescriptor>> parametersBuilder;

    /* JADX INFO: renamed from: parameters_$delegate, reason: from kotlin metadata */
    private final Lazy parameters_;
    private final Function0<List<KotlinType>> superTypesBuilder;

    /* JADX INFO: renamed from: superTypes_$delegate, reason: from kotlin metadata */
    private final Lazy superTypes_;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyTypeConstructor(ClassDescriptor classDescriptor, Function0<? extends List<? extends TypeParameterDescriptor>> function0, Function0<? extends List<? extends KotlinType>> function1, StorageManager storageManager) {
        super(storageManager);
        classDescriptor.getClass();
        function0.getClass();
        function1.getClass();
        storageManager.getClass();
        this.classDescriptor = classDescriptor;
        this.parametersBuilder = function0;
        this.superTypesBuilder = function1;
        this.parameters_ = LazyKt.lazy(new Function0() { // from class: qx8
            public final Object invoke() {
                return LazyTypeConstructor.h(this.b);
            }
        });
        this.superTypes_ = LazyKt.lazy(new Function0() { // from class: rx8
            public final Object invoke() {
                return LazyTypeConstructor.i(this.b);
            }
        });
    }

    public static List h(LazyTypeConstructor lazyTypeConstructor) {
        return (List) lazyTypeConstructor.parametersBuilder.invoke();
    }

    public static List i(LazyTypeConstructor lazyTypeConstructor) {
        return (List) lazyTypeConstructor.superTypesBuilder.invoke();
    }

    public final ClassDescriptor getClassDescriptor() {
        return this.classDescriptor;
    }

    public List<TypeParameterDescriptor> getParameters() {
        return getParameters_();
    }

    public final Function0<List<TypeParameterDescriptor>> getParametersBuilder() {
        return this.parametersBuilder;
    }

    public final List<TypeParameterDescriptor> getParameters_() {
        return (List) this.parameters_.getValue();
    }

    public final Function0<List<KotlinType>> getSuperTypesBuilder() {
        return this.superTypesBuilder;
    }

    public final List<KotlinType> getSuperTypes_() {
        return (List) this.superTypes_.getValue();
    }

    public SupertypeLoopChecker getSupertypeLoopChecker() {
        return SupertypeLoopChecker.EMPTY.INSTANCE;
    }

    public boolean isDenotable() {
        return true;
    }

    /* JADX INFO: renamed from: getDeclarationDescriptor, reason: merged with bridge method [inline-methods] */
    public ClassDescriptor m384getDeclarationDescriptor() {
        return this.classDescriptor;
    }

    public List<KotlinType> computeSupertypes() {
        return getSuperTypes_();
    }
}
