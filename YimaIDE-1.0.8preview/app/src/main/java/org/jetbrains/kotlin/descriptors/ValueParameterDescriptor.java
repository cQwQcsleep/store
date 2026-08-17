package org.jetbrains.kotlin.descriptors;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.mpp.ValueParameterSymbolMarker;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\u0010\u001a\u00020\u0000H&J\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H&J \u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H&J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001aH&J\b\u0010\u001e\u001a\u00020\u000bH\u0016R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u001b\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001c¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;", "Lorg/jetbrains/kotlin/mpp/ValueParameterSymbolMarker;", "getContainingDeclaration", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "index", "", "getIndex", "()I", "declaresDefaultValue", "", "varargElementType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getVarargElementType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "getOriginal", "substitute", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "copy", "newOwner", "newName", "Lorg/jetbrains/kotlin/name/Name;", "newIndex", "getOverriddenDescriptors", "", "isCrossinline", "()Z", "isNoinline", "isLateInit", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ValueParameterDescriptor extends ParameterDescriptor, VariableDescriptor, ValueParameterSymbolMarker {
    /* JADX INFO: renamed from: copy */
    ValueParameterDescriptor mo354copy(CallableDescriptor newOwner, Name newName, int newIndex);

    boolean declaresDefaultValue();

    CallableDescriptor getContainingDeclaration();

    int getIndex();

    /* JADX INFO: renamed from: getOriginal */
    ValueParameterDescriptor mo358getOriginal();

    Collection<ValueParameterDescriptor> getOverriddenDescriptors();

    KotlinType getVarargElementType();

    boolean isCrossinline();

    boolean isLateInit();

    boolean isNoinline();

    /* JADX INFO: renamed from: substitute */
    ValueParameterDescriptor mo377substitute(TypeSubstitutor substitutor);
}
