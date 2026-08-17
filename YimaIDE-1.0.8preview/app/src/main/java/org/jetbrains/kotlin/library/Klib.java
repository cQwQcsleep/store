package org.jetbrains.kotlin.library;

import kotlin.Metadata;
import org.jetbrains.kotlin.konan.file.File;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J+\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0010\u0010\r\u001a\f\u0012\u0004\u0012\u0002H\u000b\u0012\u0002\b\u00030\u000eH&¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/Klib;", "", "attributes", "Lorg/jetbrains/kotlin/library/KlibAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/library/KlibAttributes;", "location", "Lorg/jetbrains/kotlin/konan/file/File;", "getLocation", "()Lorg/jetbrains/kotlin/konan/file/File;", "getComponent", "KC", "Lorg/jetbrains/kotlin/library/KlibComponent;", "kind", "Lorg/jetbrains/kotlin/library/KlibComponent$Kind;", "(Lorg/jetbrains/kotlin/library/KlibComponent$Kind;)Lorg/jetbrains/kotlin/library/KlibComponent;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Klib {
    KlibAttributes getAttributes();

    <KC extends KlibComponent> KC getComponent(KlibComponent$Kind<KC, ?> kind);

    File getLocation();
}
