package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrTypeParametersContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrTypeParametersContainer;", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParametersContainer;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "prepareTypeParameters", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Fir2IrTypeParametersContainer extends Fir2IrComponents, IrTypeParametersContainer {
    FirMemberDeclaration getFir();

    default void prepareTypeParameters() {
        List<FirTypeParameterRef> typeParameters = getFir().getTypeParameters();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : typeParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) obj;
            IrTypeParameter irTypeParameter = null;
            if (firTypeParameterRef instanceof FirTypeParameter) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterRef;
                IrTypeParameter irTypeParameter$org_jetbrains_kotlin_fir2ir$default = Fir2IrClassifierStorage.getIrTypeParameter$org_jetbrains_kotlin_fir2ir$default(getClassifierStorage(), firTypeParameter, i, null, 4, null);
                irTypeParameter$org_jetbrains_kotlin_fir2ir$default.setParent(this);
                if (irTypeParameter$org_jetbrains_kotlin_fir2ir$default.getSuperTypes().isEmpty()) {
                    List<FirTypeRef> bounds = firTypeParameter.getBounds();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(bounds, 10));
                    Iterator<T> it = bounds.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(Fir2IrTypeConverterKt.toIrType$default(this, (FirTypeRef) it.next(), (ConversionTypeOrigin) null, 2, (Object) null));
                    }
                    irTypeParameter$org_jetbrains_kotlin_fir2ir$default.setSuperTypes(arrayList2);
                }
                irTypeParameter = irTypeParameter$org_jetbrains_kotlin_fir2ir$default;
            }
            if (irTypeParameter != null) {
                arrayList.add(irTypeParameter);
            }
            i = i2;
        }
        setTypeParameters(arrayList);
    }
}
