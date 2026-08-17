package org.jetbrains.kotlin.cfg.pseudocode;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/ForAllTypes;", "Lorg/jetbrains/kotlin/cfg/pseudocode/TypePredicate;", "typeSets", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;)V", "getTypeSets", "()Ljava/util/List;", "invoke", "", "typeToCheck", "Lorg/jetbrains/kotlin/types/KotlinType;", "(Lorg/jetbrains/kotlin/types/KotlinType;)Ljava/lang/Boolean;", "toString", "", "component1", "copy", "equals", "other", "", "hashCode", "", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ForAllTypes implements TypePredicate {
    private final List<TypePredicate> typeSets;

    /* JADX WARN: Multi-variable type inference failed */
    public ForAllTypes(List<? extends TypePredicate> list) {
        list.getClass();
        this.typeSets = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ForAllTypes copy$default(ForAllTypes forAllTypes, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = forAllTypes.typeSets;
        }
        return forAllTypes.copy(list);
    }

    public final List<TypePredicate> component1() {
        return this.typeSets;
    }

    public final ForAllTypes copy(List<? extends TypePredicate> typeSets) {
        typeSets.getClass();
        return new ForAllTypes(typeSets);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ForAllTypes) && Intrinsics.areEqual(this.typeSets, ((ForAllTypes) other).typeSets);
    }

    public final List<TypePredicate> getTypeSets() {
        return this.typeSets;
    }

    public int hashCode() {
        return this.typeSets.hashCode();
    }

    @Override // org.jetbrains.kotlin.cfg.pseudocode.TypePredicate
    public Boolean invoke(KotlinType typeToCheck) {
        typeToCheck.getClass();
        List<TypePredicate> list = this.typeSets;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((TypePredicate) it.next()).invoke(typeToCheck).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    public String toString() {
        return "AND{" + CollectionsKt.joinToString$default(this.typeSets, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + '}';
    }
}
