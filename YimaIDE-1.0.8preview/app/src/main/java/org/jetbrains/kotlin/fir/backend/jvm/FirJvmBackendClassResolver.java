package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.JvmBackendClassResolver;
import org.jetbrains.kotlin.codegen.JvmBackendClassResolverKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendClassResolver;", "Lorg/jetbrains/kotlin/codegen/JvmBackendClassResolver;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "resolveToClassDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmBackendClassResolver implements JvmBackendClassResolver {
    private final Fir2IrComponents components;

    public FirJvmBackendClassResolver(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.components = fir2IrComponents;
    }

    public final Fir2IrComponents getComponents() {
        return this.components;
    }

    @Override // org.jetbrains.kotlin.codegen.JvmBackendClassResolver
    public List<ClassDescriptor> resolveToClassDescriptors(Type type) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        type.getClass();
        if (type.getSort() == 10 && (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.components.getSession()).getClassLikeSymbolByClassId(JvmBackendClassResolverKt.getClassId(type))) != null) {
            if (classLikeSymbolByClassId instanceof FirClassSymbol) {
                return CollectionsKt.listOf(this.components.getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) classLikeSymbolByClassId).getDescriptor());
            }
            w01.a("Failed requirement.");
            return null;
        }
        return CollectionsKt.emptyList();
    }
}
