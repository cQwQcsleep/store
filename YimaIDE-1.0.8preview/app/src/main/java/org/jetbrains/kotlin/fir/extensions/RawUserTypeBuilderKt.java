package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.builder.FirUserTypeRefBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"typeFromQualifierParts", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isMarkedNullable", Argument.Delimiters.none, "typeResolver", "Lorg/jetbrains/kotlin/fir/extensions/FirSupertypeGenerationExtension$TypeResolveService;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "builder", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/extensions/QualifierPartBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RawUserTypeBuilderKt {
    public static final ConeKotlinType typeFromQualifierParts(boolean z, FirSupertypeGenerationExtension.TypeResolveService typeResolveService, KtSourceElement ktSourceElement, Function1<? super QualifierPartBuilder, Unit> function1) {
        typeResolveService.getClass();
        ktSourceElement.getClass();
        function1.getClass();
        FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
        firUserTypeRefBuilder.setMarkedNullable(z);
        firUserTypeRefBuilder.setSource(ktSourceElement);
        function1.invoke(new QualifierPartBuilder(firUserTypeRefBuilder.getQualifier()));
        return typeResolveService.resolveUserType(firUserTypeRefBuilder.build()).getConeType();
    }
}
