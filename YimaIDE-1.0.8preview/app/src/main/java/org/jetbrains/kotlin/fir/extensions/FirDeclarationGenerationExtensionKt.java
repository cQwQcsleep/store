package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t*\n\u0010\u0000\"\u00020\u00012\u00020\u0001*\n\u0010\u0002\"\u00020\u00032\u00020\u0003¨\u0006\f"}, d2 = {"MemberGenerationContext", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "NestedClassGenerationContext", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "declarationGenerators", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getDeclarationGenerators", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "declarationGenerators$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationGenerationExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirDeclarationGenerationExtensionKt.class, "declarationGenerators", "getDeclarationGenerators(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor declarationGenerators$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirDeclarationGenerationExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        declarationGenerators$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
    }

    public static final List<FirDeclarationGenerationExtension> getDeclarationGenerators(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) declarationGenerators$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }
}
