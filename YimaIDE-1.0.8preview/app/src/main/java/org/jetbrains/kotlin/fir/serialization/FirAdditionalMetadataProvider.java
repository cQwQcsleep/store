package org.jetbrains.kotlin.fir.serialization;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH&J\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", Argument.Delimiters.none, "<init>", "()V", "findGeneratedAnnotationsFor", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "hasGeneratedAnnotationsFor", Argument.Delimiters.none, "findMetadataExtensionsFor", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAdditionalMetadataProvider {
    public abstract List<FirAnnotation> findGeneratedAnnotationsFor(FirDeclaration declaration);

    public abstract Map<String, byte[]> findMetadataExtensionsFor(FirDeclaration declaration);

    public abstract boolean hasGeneratedAnnotationsFor(FirDeclaration declaration);
}
