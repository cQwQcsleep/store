package org.jetbrains.kotlin.fir.declarations.utils;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0018\u0010\u0007\u001a\u00020\u0001*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t¨\u0006\n"}, d2 = {"addDefaultBoundIfNecessary", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParameterBuilder;", "addDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "addDeclarations", "declarations", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationBuildingUtilsKt {
    public static final void addDeclaration(FirRegularClassBuilder firRegularClassBuilder, FirDeclaration firDeclaration) {
        firRegularClassBuilder.getClass();
        firDeclaration.getClass();
        firRegularClassBuilder.getDeclarations().add(firDeclaration);
    }

    public static final void addDeclarations(FirRegularClassBuilder firRegularClassBuilder, Collection<? extends FirDeclaration> collection) {
        firRegularClassBuilder.getClass();
        collection.getClass();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            addDeclaration(firRegularClassBuilder, (FirDeclaration) it.next());
        }
    }

    public static final void addDefaultBoundIfNecessary(FirTypeParameterBuilder firTypeParameterBuilder) {
        firTypeParameterBuilder.getClass();
        if (firTypeParameterBuilder.getBounds().isEmpty()) {
            firTypeParameterBuilder.getBounds().add(firTypeParameterBuilder.getModuleData().getSession().getBuiltinTypes().getNullableAnyType());
        }
    }
}
