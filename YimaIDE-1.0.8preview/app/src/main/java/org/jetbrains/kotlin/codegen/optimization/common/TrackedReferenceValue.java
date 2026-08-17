package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/TrackedReferenceValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;)V", "descriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "getDescriptors", "()Ljava/util/Set;", "Lorg/jetbrains/kotlin/codegen/optimization/common/MergedTrackedReferenceValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/ProperTrackedReferenceValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/TaintedTrackedReferenceValue;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TrackedReferenceValue extends StrictBasicValue {
    public /* synthetic */ TrackedReferenceValue(Type type, DefaultConstructorMarker defaultConstructorMarker) {
        this(type);
    }

    public abstract Set<ReferenceValueDescriptor> getDescriptors();

    private TrackedReferenceValue(Type type) {
        super(type);
    }
}
