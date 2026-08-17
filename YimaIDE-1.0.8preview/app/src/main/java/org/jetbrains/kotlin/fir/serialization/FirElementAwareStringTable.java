package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.metadata.serialization.StringTable;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\bH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "Lorg/jetbrains/kotlin/metadata/serialization/StringTable;", "getQualifiedClassNameIndex", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getFqNameIndex", "classLikeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getLocalClassLikeDeclarationIdReplacement", "declaration", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirElementAwareStringTable extends StringTable {
    /* JADX WARN: Multi-variable type inference failed */
    default int getFqNameIndex(FirClassLikeDeclaration classLikeDeclaration) {
        ClassId localClassLikeDeclarationIdReplacement;
        classLikeDeclaration.getClass();
        FirClassLikeSymbol<FirClassLikeDeclaration> symbol = classLikeDeclaration.getSymbol();
        if (((FirClassLikeDeclaration) symbol.getFir()).isLocal()) {
            symbol = null;
        }
        if ((symbol != null && (localClassLikeDeclarationIdReplacement = symbol.getClassId()) != null) || (localClassLikeDeclarationIdReplacement = getLocalClassLikeDeclarationIdReplacement(classLikeDeclaration)) != null) {
            return getQualifiedClassNameIndex(localClassLikeDeclarationIdReplacement);
        }
        sle.a("Cannot get FQ name of local class: ", UtilsKt.render(classLikeDeclaration));
        return 0;
    }

    default ClassId getLocalClassLikeDeclarationIdReplacement(FirClassLikeDeclaration declaration) {
        declaration.getClass();
        return null;
    }

    default int getQualifiedClassNameIndex(ClassId classId) {
        classId.getClass();
        return getQualifiedClassNameIndex(classId.asString(), classId.isLocal());
    }
}
