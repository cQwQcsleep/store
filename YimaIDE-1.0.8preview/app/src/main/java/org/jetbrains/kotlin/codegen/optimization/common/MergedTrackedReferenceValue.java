package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/MergedTrackedReferenceValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/TrackedReferenceValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "descriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Ljava/util/Set;)V", "getDescriptors", "()Ljava/util/Set;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MergedTrackedReferenceValue extends TrackedReferenceValue {
    private final Set<ReferenceValueDescriptor> descriptors;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MergedTrackedReferenceValue(Type type, Set<? extends ReferenceValueDescriptor> set) {
        super(type, null);
        type.getClass();
        set.getClass();
        this.descriptors = set;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public boolean equals(Object other) {
        if (other != this) {
            return (other instanceof MergedTrackedReferenceValue) && Intrinsics.areEqual(((MergedTrackedReferenceValue) other).getDescriptors(), getDescriptors());
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.TrackedReferenceValue
    public Set<ReferenceValueDescriptor> getDescriptors() {
        return this.descriptors;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public int hashCode() {
        return getDescriptors().hashCode();
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public String toString() {
        return getDescriptors().toString();
    }
}
