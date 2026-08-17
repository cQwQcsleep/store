package org.jetbrains.kotlin.fir.java.declarations;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a:\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"buildJavaValueParameter", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaValueParameter;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaValueParameterBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildJavaValueParameterCopy", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "original", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaValueParameterKt {
    public static final FirJavaValueParameter buildJavaValueParameter(Function1<? super FirJavaValueParameterBuilder, Unit> function1) {
        function1.getClass();
        FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
        function1.invoke(firJavaValueParameterBuilder);
        return firJavaValueParameterBuilder.build();
    }

    public static final FirValueParameter buildJavaValueParameterCopy(FirJavaValueParameter firJavaValueParameter, Function1<? super FirJavaValueParameterBuilder, Unit> function1) {
        firJavaValueParameter.getClass();
        function1.getClass();
        FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
        firJavaValueParameterBuilder.setSource(firJavaValueParameter.getSource());
        firJavaValueParameterBuilder.setModuleData(firJavaValueParameter.getModuleData());
        firJavaValueParameterBuilder.setAttributes(firJavaValueParameter.getAttributes().copy());
        firJavaValueParameterBuilder.setFromSource(firJavaValueParameter.getOrigin().getFromSource());
        firJavaValueParameterBuilder.setReturnTypeRef(firJavaValueParameter.getReturnTypeRef());
        firJavaValueParameterBuilder.setName(firJavaValueParameter.getName());
        firJavaValueParameterBuilder.setAnnotationList(firJavaValueParameter.getAnnotationList());
        firJavaValueParameterBuilder.setDefaultValue(firJavaValueParameter.getLazyDefaultValue());
        firJavaValueParameterBuilder.setContainingDeclarationSymbol(firJavaValueParameter.getContainingDeclarationSymbol());
        firJavaValueParameterBuilder.setVararg(firJavaValueParameter.getIsVararg());
        function1.invoke(firJavaValueParameterBuilder);
        return firJavaValueParameterBuilder.build();
    }
}
