package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousObjectSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousObjectSymbol extends FirClassSymbol<FirAnonymousObject> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAnonymousObjectSymbol(FqName fqName) {
        super(new ClassId(fqName, SpecialNames.ANONYMOUS_FQ_NAME, true), null);
        fqName.getClass();
    }
}
