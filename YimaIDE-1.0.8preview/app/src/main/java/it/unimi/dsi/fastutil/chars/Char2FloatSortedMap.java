package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2FloatSortedMap extends Char2FloatMap, SortedMap<Character, Float> {
    @Override // it.unimi.dsi.fastutil.chars.Char2FloatMap
    ObjectSortedSet<Char2FloatMap.Entry> char2FloatEntrySet();

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Character> comparator2();

    char firstCharKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Character firstKey() {
        return Character.valueOf(firstCharKey());
    }

    Char2FloatSortedMap headMap(char c);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2FloatSortedMap headMap(Character ch) {
        return headMap(ch.charValue());
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2FloatMap, java.util.Map
    Set<Character> keySet();

    char lastCharKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Character lastKey() {
        return Character.valueOf(lastCharKey());
    }

    Char2FloatSortedMap subMap(char c, char c2);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2FloatSortedMap subMap(Character ch, Character ch2) {
        return subMap(ch.charValue(), ch2.charValue());
    }

    Char2FloatSortedMap tailMap(char c);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2FloatSortedMap tailMap(Character ch) {
        return tailMap(ch.charValue());
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2FloatMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Float> values2();

    @Override // it.unimi.dsi.fastutil.chars.Char2FloatMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Character, Float>> entrySet() {
        return char2FloatEntrySet();
    }
}
