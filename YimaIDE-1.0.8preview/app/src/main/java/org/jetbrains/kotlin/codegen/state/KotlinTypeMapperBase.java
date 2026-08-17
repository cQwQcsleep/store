package org.jetbrains.kotlin.codegen.state;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", Argument.Delimiters.none, "<init>", "()V", "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "getTypeSystem", "()Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "mapClass", "Lorg/jetbrains/org/objectweb/asm/Type;", "classifier", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "mapTypeCommon", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "mode", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "mapDefaultImpls", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KotlinTypeMapperBase {
    public abstract TypeSystemCommonBackendContext getTypeSystem();

    public abstract Type mapClass(ClassifierDescriptor classifier);

    public final Type mapDefaultImpls(ClassDescriptor descriptor) {
        descriptor.getClass();
        Type objectType = Type.getObjectType(mapClass(descriptor).getInternalName() + "$DefaultImpls");
        objectType.getClass();
        return objectType;
    }

    public abstract Type mapTypeCommon(KotlinTypeMarker type, TypeMappingMode mode);
}
