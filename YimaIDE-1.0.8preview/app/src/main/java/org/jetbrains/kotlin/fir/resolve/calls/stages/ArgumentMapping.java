package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012.\u0010\u0002\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0003j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0004`\u0007J\u0006\u0010\u0012\u001a\u00020\u0013J1\u0010\u0014\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JK\u0010\u0016\u001a\u00020\u000020\b\u0002\u0010\u0002\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R9\u0010\u0002\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ArgumentMapping;", Argument.Delimiters.none, "parameterToCallArgumentMap", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "Lkotlin/collections/LinkedHashMap;", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "<init>", "(Ljava/util/LinkedHashMap;Ljava/util/List;)V", "getParameterToCallArgumentMap", "()Ljava/util/LinkedHashMap;", "getDiagnostics", "()Ljava/util/List;", "toArgumentToParameterMapping", "numDefaults", Argument.Delimiters.none, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ArgumentMapping {
    private final List<ResolutionDiagnostic> diagnostics;
    private final LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> parameterToCallArgumentMap;

    /* JADX WARN: Multi-variable type inference failed */
    public ArgumentMapping(LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> linkedHashMap, List<? extends ResolutionDiagnostic> list) {
        linkedHashMap.getClass();
        list.getClass();
        this.parameterToCallArgumentMap = linkedHashMap;
        this.diagnostics = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArgumentMapping copy$default(ArgumentMapping argumentMapping, LinkedHashMap linkedHashMap, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            linkedHashMap = argumentMapping.parameterToCallArgumentMap;
        }
        if ((i & 2) != 0) {
            list = argumentMapping.diagnostics;
        }
        return argumentMapping.copy(linkedHashMap, list);
    }

    public final LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> component1() {
        return this.parameterToCallArgumentMap;
    }

    public final List<ResolutionDiagnostic> component2() {
        return this.diagnostics;
    }

    public final ArgumentMapping copy(LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> parameterToCallArgumentMap, List<? extends ResolutionDiagnostic> diagnostics) {
        parameterToCallArgumentMap.getClass();
        diagnostics.getClass();
        return new ArgumentMapping(parameterToCallArgumentMap, diagnostics);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArgumentMapping)) {
            return false;
        }
        ArgumentMapping argumentMapping = (ArgumentMapping) other;
        return Intrinsics.areEqual(this.parameterToCallArgumentMap, argumentMapping.parameterToCallArgumentMap) && Intrinsics.areEqual(this.diagnostics, argumentMapping.diagnostics);
    }

    public final List<ResolutionDiagnostic> getDiagnostics() {
        return this.diagnostics;
    }

    public final LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> getParameterToCallArgumentMap() {
        return this.parameterToCallArgumentMap;
    }

    public int hashCode() {
        return (this.parameterToCallArgumentMap.hashCode() * 31) + this.diagnostics.hashCode();
    }

    public final int numDefaults() {
        Collection<ResolvedCallArgument<ConeResolutionAtom>> collectionValues = this.parameterToCallArgumentMap.values();
        collectionValues.getClass();
        Collection<ResolvedCallArgument<ConeResolutionAtom>> collection = collectionValues;
        int i = 0;
        if (collection.isEmpty()) {
            return 0;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual((ResolvedCallArgument) it.next(), ResolvedCallArgument.DefaultArgument.INSTANCE) && (i = i + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final LinkedHashMap<ConeResolutionAtom, FirValueParameter> toArgumentToParameterMapping() {
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> entry : this.parameterToCallArgumentMap.entrySet()) {
            FirValueParameter key = entry.getKey();
            ResolvedCallArgument<ConeResolutionAtom> value = entry.getValue();
            if (value instanceof ResolvedCallArgument.SimpleArgument) {
                linkedHashMap.put(((ResolvedCallArgument.SimpleArgument) value).getCallArgument(), key);
            } else if (value instanceof ResolvedCallArgument.VarargArgument) {
                Iterator it = ((ResolvedCallArgument.VarargArgument) value).getArguments().iterator();
                while (it.hasNext()) {
                    linkedHashMap.put((ConeResolutionAtom) it.next(), key);
                }
            } else if (!Intrinsics.areEqual(value, ResolvedCallArgument.DefaultArgument.INSTANCE)) {
                bu8.a();
                return null;
            }
        }
        return linkedHashMap;
    }

    public String toString() {
        return "ArgumentMapping(parameterToCallArgumentMap=" + this.parameterToCallArgumentMap + ", diagnostics=" + this.diagnostics + ')';
    }
}
