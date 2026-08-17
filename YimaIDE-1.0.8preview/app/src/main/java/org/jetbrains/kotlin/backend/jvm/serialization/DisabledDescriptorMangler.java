package org.jetbrains.kotlin.backend.jvm.serialization;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0014\u0010\r\u001a\u00020\u0006*\u00020\u000b2\u0006\u0010\f\u001a\u00020\nH\u0016R\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/serialization/DisabledDescriptorMangler;", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "hashMangle", "", "", "getHashMangle", "(Ljava/lang/String;)J", "isExported", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "compatibleMode", "signatureString", "org.jetbrains.kotlin:ir.serialization.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DisabledDescriptorMangler implements KotlinMangler.DescriptorMangler {
    public static final DisabledDescriptorMangler INSTANCE = new DisabledDescriptorMangler();

    private DisabledDescriptorMangler() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.ir.util.KotlinMangler
    public long getHashMangle(String str) throws KotlinNothingValueException {
        str.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.ir.util.KotlinMangler
    public boolean isExported(DeclarationDescriptor declarationDescriptor, boolean z) throws KotlinNothingValueException {
        declarationDescriptor.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.ir.util.KotlinMangler
    public String signatureString(DeclarationDescriptor declarationDescriptor, boolean z) throws KotlinNothingValueException {
        declarationDescriptor.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }
}
