package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineScopesGeneratorKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006\u001a\u0018\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\b*\u00020\nH\u0000\u001a\u0018\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\b*\u00020\nH\u0002\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0004\u001a\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\"\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u0013"}, d2 = {"updateCallSiteLineNumber", Argument.Delimiters.none, ModuleXmlParser.NAME, "newLineNumber", Argument.Delimiters.none, "calculate", "Lkotlin/Function1;", "getLabelToIndexMap", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Label;", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getLabelToLineNumberMap", "addScopeInfo", "number", "getInlineDepth", "variableName", "isInlineLambdaName", Argument.Delimiters.none, "(Ljava/lang/String;)Z", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineScopesGeneratorKt {
    public static int a(int i, int i2) {
        return i;
    }

    public static final String addScopeInfo(String str, int i) {
        str.getClass();
        return str + InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getInlineDepth(String str) {
        int length = str.length();
        int i = 0;
        while (length >= 3) {
            int i2 = length - 3;
            if (!Intrinsics.areEqual(str.substring(i2, length), InlineCodegenUtilsKt.INLINE_FUN_VAR_SUFFIX)) {
                break;
            }
            i++;
            length = i2;
        }
        return i;
    }

    public static final Map<Label, Integer> getLabelToIndexMap(MethodNode methodNode) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : insnList) {
            if (obj instanceof LabelNode) {
                arrayList.add(obj);
            }
        }
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(arrayList);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            Pair pair = TuplesKt.to(((LabelNode) indexedValue.component2()).getLabel(), Integer.valueOf(indexedValue.getIndex()));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<Label, Integer> getLabelToLineNumberMap(MethodNode methodNode) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ListIterator it = methodNode.instructions.iterator();
        it.getClass();
        int i = 0;
        while (it.hasNext()) {
            LabelNode labelNode = (AbstractInsnNode) it.next();
            if (labelNode instanceof LineNumberNode) {
                i = ((LineNumberNode) labelNode).line;
            } else if (labelNode instanceof LabelNode) {
                linkedHashMap.put(labelNode.getLabel(), Integer.valueOf(i));
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInlineLambdaName(String str) {
        return StringsKt.startsWith$default(str, "$i$a$", false, 2, (Object) null);
    }

    public static final String updateCallSiteLineNumber(String str, Function1<? super Integer, Integer> function1) {
        int iIntValue;
        str.getClass();
        function1.getClass();
        InlineScopeInfo inlineScopeInfo = InlineScopeUtilsKt.getInlineScopeInfo(str);
        if (inlineScopeInfo != null) {
            int scopeNumber = inlineScopeInfo.getScopeNumber();
            Integer callSiteLineNumber = inlineScopeInfo.getCallSiteLineNumber();
            Integer surroundingScopeNumber = inlineScopeInfo.getSurroundingScopeNumber();
            if (callSiteLineNumber != null && (iIntValue = ((Number) function1.invoke(callSiteLineNumber)).intValue()) != callSiteLineNumber.intValue()) {
                String strAddScopeInfo = addScopeInfo(addScopeInfo(InlineScopeUtilsKt.dropInlineScopeInfo(str), scopeNumber), iIntValue);
                return surroundingScopeNumber == null ? strAddScopeInfo : addScopeInfo(strAddScopeInfo, surroundingScopeNumber.intValue());
            }
        }
        return str;
    }

    public static final String updateCallSiteLineNumber(String str, final int i) {
        str.getClass();
        return updateCallSiteLineNumber(str, (Function1<? super Integer, Integer>) new Function1() { // from class: bq6
            public final Object invoke(Object obj) {
                return Integer.valueOf(InlineScopesGeneratorKt.a(i, ((Integer) obj).intValue()));
            }
        });
    }
}
