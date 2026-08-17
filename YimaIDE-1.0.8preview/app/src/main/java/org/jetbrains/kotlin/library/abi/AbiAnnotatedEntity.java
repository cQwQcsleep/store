package org.jetbrains.kotlin.library.abi;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001JH\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'b6\b\u0006\u0012\n\b\u0007\u0012\u0006\b\n0\b8\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u001c\b\f\u0012\u0018\b\u000bB\u0014\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0006\b\u0010\u0012\u0002\b\fJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&Ê\u0001\u0002\b\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiAnnotatedEntity;", "", "hasAnnotation", "", "annotationClassName", "Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "WARNING", "message", "Use annotatedWith instead.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "annotatedWith", "imports", "", "Lorg/jetbrains/kotlin/library/abi/AbiAnnotation;", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public interface AbiAnnotatedEntity {
    List<AbiAnnotation> annotatedWith();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use annotatedWith instead.", replaceWith = @ReplaceWith(expression = "annotatedWith", imports = {}))
    boolean hasAnnotation(AbiQualifiedName annotationClassName);
}
