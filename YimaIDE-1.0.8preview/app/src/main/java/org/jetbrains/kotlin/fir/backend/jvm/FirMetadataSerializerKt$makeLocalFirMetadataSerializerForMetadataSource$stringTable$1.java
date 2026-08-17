package org.jetbrains.kotlin.fir.backend.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/jetbrains/kotlin/fir/backend/jvm/FirMetadataSerializerKt$makeLocalFirMetadataSerializerForMetadataSource$stringTable$1", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getLocalClassLikeDeclarationIdReplacement", "Lorg/jetbrains/kotlin/name/ClassId;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMetadataSerializerKt$makeLocalFirMetadataSerializerForMetadataSource$stringTable$1 extends JvmStringTable implements FirElementAwareStringTable {
    final /* synthetic */ FirSession $session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirMetadataSerializerKt$makeLocalFirMetadataSerializerForMetadataSource$stringTable$1(FirSession firSession) {
        super((JvmNameResolver) null);
        this.$session = firSession;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable
    public ClassId getLocalClassLikeDeclarationIdReplacement(FirClassLikeDeclaration declaration) {
        FirRegularClassSymbol regularClassSymbol;
        FirRegularClass firRegularClass;
        declaration.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassForLocal = ClassMembersKt.containingClassForLocal(declaration);
        if (coneClassLikeLookupTagContainingClassForLocal != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassForLocal, this.$session)) != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null) {
            declaration = firRegularClass;
        }
        return declaration.getSymbol().getClassId();
    }
}
