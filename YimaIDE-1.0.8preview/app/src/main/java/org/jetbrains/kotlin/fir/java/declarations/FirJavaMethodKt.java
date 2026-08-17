package org.jetbrains.kotlin.fir.java.declarations;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildJavaMethod", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaMethod;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaMethodBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildJavaMethodCopy", "original", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaMethodKt {
    public static final FirJavaMethod buildJavaMethod(Function1<? super FirJavaMethodBuilder, Unit> function1) {
        function1.getClass();
        FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
        function1.invoke(firJavaMethodBuilder);
        return firJavaMethodBuilder.mo288build();
    }

    public static final FirJavaMethod buildJavaMethodCopy(FirJavaMethod firJavaMethod, Function1<? super FirJavaMethodBuilder, Unit> function1) {
        firJavaMethod.getClass();
        function1.getClass();
        FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
        firJavaMethodBuilder.setSource(firJavaMethod.getSource());
        firJavaMethodBuilder.setModuleData(firJavaMethod.getModuleData());
        firJavaMethodBuilder.setAttributes(firJavaMethod.getAttributes().copy());
        firJavaMethodBuilder.setReturnTypeRef(firJavaMethod.getReturnTypeRef());
        firJavaMethodBuilder.getValueParameters().addAll(firJavaMethod.getValueParameters());
        firJavaMethodBuilder.setBody(firJavaMethod.getBody());
        firJavaMethodBuilder.setStatus(firJavaMethod.getStatus());
        firJavaMethodBuilder.setDispatchReceiverType(firJavaMethod.getDispatchReceiverType());
        firJavaMethodBuilder.setName(firJavaMethod.getName());
        firJavaMethodBuilder.setSymbol(firJavaMethod.getSymbol());
        firJavaMethodBuilder.setFromSource(firJavaMethod.getOrigin().getFromSource());
        firJavaMethodBuilder.getTypeParameters().addAll(firJavaMethod.getTypeParameters());
        firJavaMethodBuilder.setAnnotationList(firJavaMethod.getAnnotationList());
        firJavaMethodBuilder.setContainingClassSymbol(firJavaMethod.getContainingClassSymbol());
        function1.invoke(firJavaMethodBuilder);
        return firJavaMethodBuilder.mo288build();
    }
}
