package org.jetbrains.kotlin.contracts;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.MutableContextInfo;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfoFactory;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue;
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo;
import org.jetbrains.kotlin.resolve.calls.smartcasts.Nullability;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001aK\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\r0\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\u000fH\u0082\b\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0012"}, d2 = {"toDataFlowInfo", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowInfo;", "Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "extractDataFlowStatements", Argument.Delimiters.none, "D", "dictionary", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESValue;", Argument.Delimiters.none, "callback", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValue;", "toDataFlowValue", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContextInfoToDataFlowInfoKt {
    public static final DataFlowInfo toDataFlowInfo(MutableContextInfo mutableContextInfo, LanguageVersionSettings languageVersionSettings, KotlinBuiltIns kotlinBuiltIns) {
        mutableContextInfo.getClass();
        languageVersionSettings.getClass();
        kotlinBuiltIns.getClass();
        DataFlowInfo dataFlowInfoEstablishSubtyping = DataFlowInfoFactory.EMPTY;
        for (Map.Entry<ESValue, Set<ESValue>> entry : mutableContextInfo.getEqualValues().entrySet()) {
            ESValue key = entry.getKey();
            Set<ESValue> value = entry.getValue();
            DataFlowValue dataFlowValue = toDataFlowValue(key, kotlinBuiltIns);
            if (dataFlowValue != null) {
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    DataFlowValue dataFlowValue2 = toDataFlowValue((ESValue) it.next(), kotlinBuiltIns);
                    if (dataFlowValue2 != null) {
                        dataFlowInfoEstablishSubtyping = dataFlowInfoEstablishSubtyping.equate(dataFlowValue, dataFlowValue2, false, languageVersionSettings);
                    }
                    int[] iArr = new int[42];
                    for (int i = 0; i < 42; i++) {
                        iArr[i] = i;
                    }
                }
            }
        }
        for (Map.Entry<ESValue, Set<ESValue>> entry2 : mutableContextInfo.getNotEqualValues().entrySet()) {
            ESValue key2 = entry2.getKey();
            Set<ESValue> value2 = entry2.getValue();
            DataFlowValue dataFlowValue3 = toDataFlowValue(key2, kotlinBuiltIns);
            if (dataFlowValue3 != null) {
                Iterator<T> it2 = value2.iterator();
                while (it2.hasNext()) {
                    DataFlowValue dataFlowValue4 = toDataFlowValue((ESValue) it2.next(), kotlinBuiltIns);
                    if (dataFlowValue4 != null) {
                        dataFlowInfoEstablishSubtyping = dataFlowInfoEstablishSubtyping.disequate(dataFlowValue3, dataFlowValue4, languageVersionSettings);
                    }
                }
            }
        }
        for (Map.Entry<ESValue, Set<KotlinType>> entry3 : mutableContextInfo.getSubtypes().entrySet()) {
            ESValue key3 = entry3.getKey();
            Set<KotlinType> value3 = entry3.getValue();
            DataFlowValue dataFlowValue5 = toDataFlowValue(key3, kotlinBuiltIns);
            if (dataFlowValue5 != null) {
                Iterator<T> it3 = value3.iterator();
                while (it3.hasNext()) {
                    dataFlowInfoEstablishSubtyping = dataFlowInfoEstablishSubtyping.establishSubtyping(dataFlowValue5, (KotlinType) it3.next(), languageVersionSettings);
                }
            }
        }
        return dataFlowInfoEstablishSubtyping;
    }

    private static final DataFlowValue toDataFlowValue(ESValue eSValue, KotlinBuiltIns kotlinBuiltIns) {
        if (eSValue instanceof ESDataFlowValue) {
            return ((ESDataFlowValue) eSValue).getDataFlowValue();
        }
        if (!(eSValue instanceof ESConstant)) {
            return null;
        }
        ESConstant eSConstant = (ESConstant) eSValue;
        return Intrinsics.areEqual(eSConstant.getConstantReference(), ConstantReference.INSTANCE.getNULL()) ? DataFlowValue.Companion.nullValue(kotlinBuiltIns) : new DataFlowValue(IdentifierInfo.NO.INSTANCE, eSConstant.getType().toKotlinType(kotlinBuiltIns), (Nullability) null, 4, (DefaultConstructorMarker) null);
    }
}
