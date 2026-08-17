package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/ImportedFromObjectOrStaticData;", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", Argument.Delimiters.none, "objectClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "original", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "getObjectClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getOriginal", "()Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImportedFromObjectOrStaticData<D extends FirCallableDeclaration> {
    private final ClassId objectClassId;
    private final D original;

    public ImportedFromObjectOrStaticData(ClassId classId, D d) {
        classId.getClass();
        d.getClass();
        this.objectClassId = classId;
        this.original = d;
    }

    public final ClassId getObjectClassId() {
        return this.objectClassId;
    }

    public final D getOriginal() {
        return this.original;
    }
}
