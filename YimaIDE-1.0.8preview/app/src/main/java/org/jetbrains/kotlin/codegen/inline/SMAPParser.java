package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J$\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0002¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAPParser;", Argument.Delimiters.none, "<init>", "()V", "parseOrNull", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "mappingInfo", Argument.Delimiters.none, "parseStratum", "stratum", "callSites", "SMAPTokenizer", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SMAPParser {
    public static final SMAPParser INSTANCE = new SMAPParser();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002J\n\u0010\f\u001a\u00020\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0002H\u0096\u0082\u0004R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAPParser$SMAPTokenizer;", Argument.Delimiters.none, Argument.Delimiters.none, "text", "headerString", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "pos", Argument.Delimiters.none, "currentLine", "advance", Argument.Delimiters.none, "hasNext", Argument.Delimiters.none, "next", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SMAPTokenizer implements Iterator<String>, KMappedMarker {
        private String currentLine;
        private final String headerString;
        private int pos;
        private final String text;

        public SMAPTokenizer(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.text = str;
            this.headerString = str2;
            advance();
            while (true) {
                String str3 = this.currentLine;
                if (str3 == null || Intrinsics.areEqual(str3, this.headerString)) {
                    break;
                } else {
                    advance();
                }
            }
            if (Intrinsics.areEqual(this.currentLine, this.headerString)) {
                advance();
            }
        }

        private final void advance() {
            if (this.pos >= this.text.length()) {
                this.currentLine = null;
                return;
            }
            int i = this.pos;
            while (this.pos < this.text.length() && this.text.charAt(this.pos) != '\n' && this.text.charAt(this.pos) != '\r') {
                this.pos++;
            }
            this.currentLine = this.text.substring(i, this.pos);
            this.pos++;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentLine != null;
        }

        @Override // java.util.Iterator
        public String next() {
            String str = this.currentLine;
            if (str != null) {
                advance();
                return str;
            }
            z0e.a();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    private SMAPParser() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v5 */
    private final SMAP parseStratum(String mappingInfo, String stratum, SMAP callSites) {
        ?? r9;
        int iIndexOf$default;
        int i;
        RangeMapping rangeMappingFindRange;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SMAPTokenizer sMAPTokenizer = new SMAPTokenizer(mappingInfo, "*S " + stratum);
        SMAP smap = null;
        if (!sMAPTokenizer.hasNext() || !Intrinsics.areEqual(sMAPTokenizer.next(), SMAP.FILE_SECTION)) {
            return null;
        }
        while (true) {
            r9 = 0;
            if (!sMAPTokenizer.hasNext()) {
                break;
            }
            String next = sMAPTokenizer.next();
            if (Intrinsics.areEqual(next, SMAP.LINE_SECTION)) {
                break;
            }
            if (Intrinsics.areEqual(next, SMAP.FILE_SECTION) || Intrinsics.areEqual(next, SMAP.END) || StringsKt.startsWith$default(next, SMAP.STRATA_SECTION, false, 2, (Object) null)) {
                return null;
            }
            String strSubstring = StringsKt.startsWith$default(next, "+ ", false, 2, (Object) null) ? next.substring(2) : next;
            int i2 = Integer.parseInt(StringsKt.substringBefore$default(strSubstring, ' ', (String) null, 2, (Object) null));
            String strSubstringAfter$default = StringsKt.substringAfter$default(strSubstring, ' ', (String) null, 2, (Object) null);
            linkedHashMap.put(Integer.valueOf(i2), new FileMapping(strSubstringAfter$default, StringsKt.startsWith$default(next, "+ ", false, 2, (Object) null) ? sMAPTokenizer.next() : strSubstringAfter$default));
        }
        while (sMAPTokenizer.hasNext()) {
            String next2 = sMAPTokenizer.next();
            if (Intrinsics.areEqual(next2, SMAP.LINE_SECTION) || Intrinsics.areEqual(next2, SMAP.FILE_SECTION)) {
                return smap;
            }
            if (Intrinsics.areEqual(next2, SMAP.END) || StringsKt.startsWith$default(next2, SMAP.STRATA_SECTION, (boolean) r9, 2, smap)) {
                break;
            }
            int iIndexOf$default2 = StringsKt.indexOf$default(next2, '#', 0, false, 6, (Object) null);
            if (iIndexOf$default2 < 0 || (iIndexOf$default = StringsKt.indexOf$default(next2, ':', iIndexOf$default2, false, 4, (Object) null)) < 0) {
                return smap;
            }
            SMAP smap2 = smap;
            int iIndexOf$default3 = StringsKt.indexOf$default(next2, ',', 0, false, 6, (Object) null);
            if (iIndexOf$default2 > iIndexOf$default3 || iIndexOf$default3 > iIndexOf$default) {
                iIndexOf$default3 = iIndexOf$default;
            }
            int i3 = iIndexOf$default3;
            int iIndexOf$default4 = StringsKt.indexOf$default(next2, ',', iIndexOf$default, false, 4, (Object) null);
            if (iIndexOf$default4 < 0) {
                iIndexOf$default4 = next2.length();
            }
            FileMapping fileMapping = (FileMapping) linkedHashMap.get(Integer.valueOf(Integer.parseInt(next2.substring(iIndexOf$default2 + 1, i3))));
            if (fileMapping == null) {
                return smap2;
            }
            int i4 = Integer.parseInt(next2.substring(r9, iIndexOf$default2));
            int i5 = Integer.parseInt(next2.substring(iIndexOf$default + 1, iIndexOf$default4));
            if (iIndexOf$default4 != next2.length()) {
                i = Integer.parseInt(next2.substring(iIndexOf$default4 + 1));
            } else {
                i = i3 != iIndexOf$default ? Integer.parseInt(next2.substring(i3 + 1, iIndexOf$default)) : 1;
            }
            fileMapping.mapNewInterval(i4, i5, i, (callSites == null || (rangeMappingFindRange = callSites.findRange(i5)) == null) ? smap2 : rangeMappingFindRange.mapDestToSource(rangeMappingFindRange.getDest()));
            r9 = 0;
            smap = smap2;
        }
        Collection collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        return new SMAP(CollectionsKt.toList(collectionValues));
    }

    public final SMAP parseOrNull(String mappingInfo) {
        mappingInfo.getClass();
        if (mappingInfo.length() > 0) {
            return parseStratum(mappingInfo, SMAPKt.KOTLIN_STRATA_NAME, parseStratum(mappingInfo, SMAPKt.KOTLIN_DEBUG_STRATA_NAME, null));
        }
        return null;
    }
}
