package org.jetbrains.kotlin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0003*\u00020\u0002¨\u0006\u0006"}, d2 = {"readSourceFileWithMapping", "Lkotlin/Pair;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "Ljava/io/InputStreamReader;", "toSourceLinesMapping", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtSourceFileLinesMappingKt {
    public static final Pair<CharSequence, KtSourceFileLinesMapping> readSourceFileWithMapping(InputStreamReader inputStreamReader) throws IOException {
        inputStreamReader.getClass();
        char[] cArr = new char[255];
        List listMutableListOf = CollectionsKt.mutableListOf(new Integer[]{0});
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (true) {
            if (i2 < i) {
                int i4 = i2 + 1;
                char c = cArr[i2];
                int i5 = i3 + 1;
                if (c == '\n' && z) {
                    z = false;
                    i2 = i4;
                } else {
                    if (c == '\n' || c == '\r') {
                        sb.append('\n');
                        listMutableListOf.add(Integer.valueOf(i5));
                        if (c == '\r') {
                            z = true;
                        }
                        i2 = i4;
                        i3 = i5;
                    } else {
                        sb.append(c);
                    }
                    z = false;
                    i2 = i4;
                    i3 = i5;
                }
            } else {
                i = inputStreamReader.read(cArr);
                if (i < 0) {
                    return TuplesKt.to(sb, new KtSourceFileLinesMappingFromLineStartOffsets(CollectionsKt.toIntArray(listMutableListOf), i3));
                }
                i2 = 0;
            }
        }
    }

    public static final KtSourceFileLinesMapping toSourceLinesMapping(CharSequence charSequence) {
        charSequence.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Integer[]{0});
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i++;
            if (charSequence.charAt(i2) == '\n') {
                listMutableListOf.add(Integer.valueOf(i));
            }
        }
        return new KtSourceFileLinesMappingFromLineStartOffsets(CollectionsKt.toIntArray(listMutableListOf), i);
    }
}
