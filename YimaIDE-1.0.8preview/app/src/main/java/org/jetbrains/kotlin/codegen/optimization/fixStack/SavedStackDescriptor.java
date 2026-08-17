package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0006\u0010\u0012\u001a\u00020\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;", Argument.Delimiters.none, "savedValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "firstLocalVarIndex", Argument.Delimiters.none, "<init>", "(Ljava/util/List;I)V", "getSavedValues", "()Ljava/util/List;", "getFirstLocalVarIndex", "()I", "savedValuesSize", "firstUnusedLocalVarIndex", "getFirstUnusedLocalVarIndex", "toString", Argument.Delimiters.none, "isNotEmpty", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SavedStackDescriptor {
    private final int firstLocalVarIndex;
    private final int firstUnusedLocalVarIndex;
    private final List<FixStackValue> savedValues;
    private final int savedValuesSize;

    /* JADX WARN: Multi-variable type inference failed */
    public SavedStackDescriptor(List<? extends FixStackValue> list, int i) {
        list.getClass();
        this.savedValues = list;
        this.firstLocalVarIndex = i;
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += ((FixStackValue) it.next()).get_size();
        }
        this.savedValuesSize = i2;
        this.firstUnusedLocalVarIndex = this.firstLocalVarIndex + i2;
    }

    public final int getFirstLocalVarIndex() {
        return this.firstLocalVarIndex;
    }

    public final int getFirstUnusedLocalVarIndex() {
        return this.firstUnusedLocalVarIndex;
    }

    public final List<FixStackValue> getSavedValues() {
        return this.savedValues;
    }

    public final boolean isNotEmpty() {
        return !this.savedValues.isEmpty();
    }

    public String toString() {
        return PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + this.firstLocalVarIndex + ": [" + this.savedValues + ']';
    }
}
