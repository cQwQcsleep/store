package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/ProperTrackedReferenceValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/TrackedReferenceValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "descriptor", "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "descriptors", Argument.Delimiters.none, "getDescriptors", "()Ljava/util/Set;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ProperTrackedReferenceValue extends TrackedReferenceValue {
    private final ReferenceValueDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProperTrackedReferenceValue(Type type, ReferenceValueDescriptor referenceValueDescriptor) {
        super(type, null);
        type.getClass();
        referenceValueDescriptor.getClass();
        this.descriptor = referenceValueDescriptor;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public boolean equals(Object other) {
        if (other != this) {
            return (other instanceof ProperTrackedReferenceValue) && Intrinsics.areEqual(((ProperTrackedReferenceValue) other).descriptor, this.descriptor);
        }
        return true;
    }

    public final ReferenceValueDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.TrackedReferenceValue
    public Set<ReferenceValueDescriptor> getDescriptors() {
        return SetsKt.setOf(this.descriptor);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public int hashCode() {
        return this.descriptor.hashCode();
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public String toString() {
        return "[" + this.descriptor + ']';
    }
}
