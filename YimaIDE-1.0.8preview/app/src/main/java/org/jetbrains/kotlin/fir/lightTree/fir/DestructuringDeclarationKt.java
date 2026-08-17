package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.DestructuringKt;
import org.jetbrains.kotlin.fir.declarations.FirVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\\\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000f¨\u0006\u0010"}, d2 = {"addDestructuringStatements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "multiDeclaration", "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;", "container", "isTmpVariable", Argument.Delimiters.none, "forceLocal", "configure", "Lkotlin/Function1;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DestructuringDeclarationKt {
    public static Unit a(FirVariable firVariable) {
        firVariable.getClass();
        return Unit.INSTANCE;
    }

    public static final void addDestructuringStatements(AbstractRawFirBuilder<?> abstractRawFirBuilder, List<? super FirVariable> list, FirModuleData firModuleData, DestructuringDeclaration destructuringDeclaration, FirVariable firVariable, boolean z, boolean z2, Function1<? super FirVariable, Unit> function1) {
        abstractRawFirBuilder.getClass();
        list.getClass();
        firModuleData.getClass();
        destructuringDeclaration.getClass();
        firVariable.getClass();
        function1.getClass();
        DestructuringKt.addDestructuringVariables(DestructuringEntry.INSTANCE, abstractRawFirBuilder, list, firModuleData, firVariable, destructuringDeclaration.getEntries(), destructuringDeclaration.isNameBased(), z, z2, function1);
    }
}
