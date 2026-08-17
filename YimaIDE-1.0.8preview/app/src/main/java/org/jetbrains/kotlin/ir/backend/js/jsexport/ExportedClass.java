package org.jetbrains.kotlin.ir.backend.js.jsexport;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.library.components.KlibIrConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedClass;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedDeclaration;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", KlibIrConstants.KLIB_IR_FOLDER_NAME, "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getIr", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "members", "", "getMembers", "()Ljava/util/List;", "nestedClasses", "getNestedClasses", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedObject;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedRegularClass;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExportedClass extends ExportedDeclaration {
    private ExportedClass() {
        super(null);
    }

    public abstract IrClass getIr();

    public abstract List<ExportedDeclaration> getMembers();

    public abstract String getName();

    public abstract List<ExportedClass> getNestedClasses();

    public /* synthetic */ ExportedClass(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
