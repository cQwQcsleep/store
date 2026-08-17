package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.RangeMapping;
import org.jetbrains.kotlin.codegen.inline.SMAPBuilder;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\"\u0010\u000b\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u0014\u0010\u0013\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u001c\u0010\u0015\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\nH\u0002¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAPBuilder;", Argument.Delimiters.none, "<init>", "()V", "build", Argument.Delimiters.none, "fileMappings", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", "backwardsCompatibleSyntax", Argument.Delimiters.none, "toSMAP", Argument.Delimiters.none, "stratumName", "mapToFirstLine", "Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", "fileId", Argument.Delimiters.none, "oneLine", "toSMAPFile", "id", "toSMAPMapping", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SMAPBuilder {
    public static final SMAPBuilder INSTANCE = new SMAPBuilder();

    private SMAPBuilder() {
    }

    public static CharSequence a(int i, boolean z, RangeMapping rangeMapping) {
        rangeMapping.getClass();
        return INSTANCE.toSMAP(rangeMapping, i, z);
    }

    private final String toSMAP(Collection<FileMapping> collection, String str, boolean z) {
        if (collection.isEmpty()) {
            return Argument.Delimiters.none;
        }
        StringBuilder sb = new StringBuilder("*S ");
        sb.append(str);
        sb.append("\n*F\n");
        Collection<FileMapping> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        int i = 0;
        int i2 = 0;
        for (Object obj : collection2) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(INSTANCE.toSMAPFile((FileMapping) obj, i3));
            i2 = i3;
        }
        sb.append(CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        sb.append("*L\n");
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        for (Object obj2 : collection2) {
            int i4 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList2.add(INSTANCE.toSMAPMapping((FileMapping) obj2, i4, z));
            i = i4;
        }
        sb.append(CollectionsKt.joinToString$default(arrayList2, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        return sb.toString();
    }

    private final String toSMAPFile(FileMapping fileMapping, int i) {
        return "+ " + i + ' ' + fileMapping.getName() + '\n' + fileMapping.getPath() + '\n';
    }

    private final String toSMAPMapping(FileMapping fileMapping, final int i, final boolean z) {
        return CollectionsKt.joinToString$default(fileMapping.getLineMappings(), Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: ync
            public final Object invoke(Object obj) {
                return SMAPBuilder.a(i, z, (RangeMapping) obj);
            }
        }, 30, (Object) null);
    }

    public final String build(List<FileMapping> fileMappings, boolean backwardsCompatibleSyntax) {
        fileMappings.getClass();
        if (fileMappings.isEmpty()) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<FileMapping> it = fileMappings.iterator();
        while (it.hasNext()) {
            Iterator<RangeMapping> it2 = it.next().getLineMappings().iterator();
            it2.getClass();
            while (it2.hasNext()) {
                RangeMapping next = it2.next();
                next.getClass();
                RangeMapping rangeMapping = next;
                int dest = rangeMapping.getDest();
                int range = rangeMapping.getRange();
                SourcePosition callSite = rangeMapping.getCallSite();
                if (callSite != null) {
                    int line = callSite.getLine();
                    String file = callSite.getFile();
                    String path = callSite.getPath();
                    Pair pair = TuplesKt.to(file, path);
                    Object fileMapping = linkedHashMap.get(pair);
                    if (fileMapping == null) {
                        fileMapping = new FileMapping(file, path);
                        linkedHashMap.put(pair, fileMapping);
                    }
                    FileMapping.mapNewInterval$default((FileMapping) fileMapping, line, dest, range, null, 8, null);
                }
            }
        }
        String smap = toSMAP((Collection<FileMapping>) fileMappings, SMAPKt.KOTLIN_STRATA_NAME, false);
        Collection<FileMapping> collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        String smap2 = toSMAP(collectionValues, SMAPKt.KOTLIN_DEBUG_STRATA_NAME, !backwardsCompatibleSyntax);
        if (!backwardsCompatibleSyntax || smap.length() <= 0 || smap2.length() <= 0) {
            return "SMAP\n" + fileMappings.get(0).getName() + "\nKotlin\n" + smap + smap2 + "*E\n";
        }
        return "SMAP\n" + fileMappings.get(0).getName() + "\nKotlin\n" + smap + "*E\n" + smap2 + "*E\n";
    }

    private final String toSMAP(RangeMapping rangeMapping, int i, boolean z) {
        if (rangeMapping.getRange() == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(rangeMapping.getSource());
            sb.append('#');
            sb.append(i);
            sb.append(':');
            sb.append(rangeMapping.getDest());
            sb.append('\n');
            return sb.toString();
        }
        if (z) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(rangeMapping.getSource());
            sb2.append('#');
            sb2.append(i);
            sb2.append(':');
            sb2.append(rangeMapping.getDest());
            sb2.append(',');
            sb2.append(rangeMapping.getRange());
            sb2.append('\n');
            return sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(rangeMapping.getSource());
        sb3.append('#');
        sb3.append(i);
        sb3.append(',');
        sb3.append(rangeMapping.getRange());
        sb3.append(':');
        sb3.append(rangeMapping.getDest());
        sb3.append('\n');
        return sb3.toString();
    }
}
