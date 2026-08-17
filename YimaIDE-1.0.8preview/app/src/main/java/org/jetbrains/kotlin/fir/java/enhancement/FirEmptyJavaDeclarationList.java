package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirEmptyJavaDeclarationList;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "<init>", "()V", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", "()Ljava/util/List;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEmptyJavaDeclarationList implements FirJavaDeclarationList {
    public static final FirEmptyJavaDeclarationList INSTANCE = new FirEmptyJavaDeclarationList();

    private FirEmptyJavaDeclarationList() {
    }

    @Override // org.jetbrains.kotlin.fir.java.enhancement.FirJavaDeclarationList
    public List<FirDeclaration> getDeclarations() {
        return CollectionsKt.emptyList();
    }
}
