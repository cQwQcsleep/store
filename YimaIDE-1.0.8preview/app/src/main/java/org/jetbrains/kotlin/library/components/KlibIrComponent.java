package org.jetbrains.kotlin.library.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.library.KlibComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0011\bf\u0018\u00002\u00020\u0001:\u0001\u0017J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0003H&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\u0015\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/components/KlibIrComponent;", "Lorg/jetbrains/kotlin/library/KlibComponent;", "irFileCount", "", "getIrFileCount", "()I", "bodies", "", "fileIndex", "body", "index", "declaration", "declarations", "irFile", "irFileEntries", "irFileEntry", "signature", "signatureDebugInfo", "signatures", "stringLiteral", "stringLiterals", "type", "types", "Kind", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface KlibIrComponent extends KlibComponent {
    byte[] bodies(int fileIndex);

    byte[] body(int index, int fileIndex);

    byte[] declaration(int index, int fileIndex);

    byte[] declarations(int fileIndex);

    int getIrFileCount();

    byte[] irFile(int index);

    byte[] irFileEntries(int fileIndex);

    byte[] irFileEntry(int index, int fileIndex);

    byte[] signature(int index, int fileIndex);

    byte[] signatureDebugInfo(int index, int fileIndex);

    byte[] signatures(int fileIndex);

    byte[] stringLiteral(int index, int fileIndex);

    byte[] stringLiterals(int fileIndex);

    byte[] type(int index, int fileIndex);

    byte[] types(int fileIndex);
}
