package org.jetbrains.kotlin.codegen.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.DescriptorAwareStringTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u0002\b\t¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/codegen/serialization/JvmCodegenStringTable;", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "Lorg/jetbrains/kotlin/serialization/DescriptorAwareStringTable;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmNameResolver;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmNameResolver;)V", "Lkotlin/jvm/JvmOverloads;", "getLocalClassIdReplacement", "Lorg/jetbrains/kotlin/name/ClassId;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptorWithTypeParameters;", "isLocalClassIdReplacementKeptGeneric", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmCodegenStringTable extends JvmStringTable implements DescriptorAwareStringTable {
    private final KotlinTypeMapperBase typeMapper;

    /* JADX WARN: Illegal instructions before constructor call */
    public JvmCodegenStringTable(KotlinTypeMapperBase kotlinTypeMapperBase) {
        kotlinTypeMapperBase.getClass();
        JvmNameResolver jvmNameResolver = null;
        this(kotlinTypeMapperBase, jvmNameResolver, 2, jvmNameResolver);
    }

    public ClassId getLocalClassIdReplacement(ClassifierDescriptorWithTypeParameters descriptor) {
        descriptor.getClass();
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        containingDeclaration.getClass();
        if (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) {
            ClassId localClassIdReplacement = getLocalClassIdReplacement((ClassifierDescriptorWithTypeParameters) containingDeclaration);
            Name name = descriptor.getName();
            name.getClass();
            return localClassIdReplacement.createNestedClassId(name);
        }
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            qu7.a("getLocalClassIdReplacement should only be called for local classes: ", descriptor);
            return null;
        }
        String internalName = this.typeMapper.mapClass(descriptor).getInternalName();
        internalName.getClass();
        FqName fqName = new FqName(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null));
        return new ClassId(fqName.parent(), FqName.Companion.topLevel(fqName.shortName()), true);
    }

    public boolean isLocalClassIdReplacementKeptGeneric() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmCodegenStringTable(KotlinTypeMapperBase kotlinTypeMapperBase, JvmNameResolver jvmNameResolver) {
        super(jvmNameResolver);
        kotlinTypeMapperBase.getClass();
        this.typeMapper = kotlinTypeMapperBase;
    }

    public /* synthetic */ JvmCodegenStringTable(KotlinTypeMapperBase kotlinTypeMapperBase, JvmNameResolver jvmNameResolver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kotlinTypeMapperBase, (i & 2) != 0 ? null : jvmNameResolver);
    }
}
