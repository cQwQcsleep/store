package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "<init>", "()V", "lookupTag", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeTypeParameterType extends ConeLookupTagBasedType {
    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public abstract ConeTypeParameterLookupTag getLookupTag();
}
