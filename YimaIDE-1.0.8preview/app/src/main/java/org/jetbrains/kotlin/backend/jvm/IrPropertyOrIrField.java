package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.util.DumpIrTreeKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Property", "Field", "Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField$Field;", "Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField$Property;", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrPropertyOrIrField {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField$Field;", "Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField;", "field", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "getField", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Field extends IrPropertyOrIrField {
        private final IrField field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(IrField irField) {
            super(null);
            irField.getClass();
            this.field = irField;
        }

        public static /* synthetic */ Field copy$default(Field field, IrField irField, int i, Object obj) {
            if ((i & 1) != 0) {
                irField = field.field;
            }
            return field.copy(irField);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrField getField() {
            return this.field;
        }

        public final Field copy(IrField field) {
            field.getClass();
            return new Field(field);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Field) && Intrinsics.areEqual(this.field, ((Field) other).field);
        }

        public final IrField getField() {
            return this.field;
        }

        public int hashCode() {
            return this.field.hashCode();
        }

        public String toString() {
            return DumpIrTreeKt.dump$default(this.field, (DumpIrTreeOptions) null, 1, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField$Property;", "Lorg/jetbrains/kotlin/backend/jvm/IrPropertyOrIrField;", "property", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrProperty;)V", "getProperty", "()Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Property extends IrPropertyOrIrField {
        private final IrProperty property;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Property(IrProperty irProperty) {
            super(null);
            irProperty.getClass();
            this.property = irProperty;
        }

        public static /* synthetic */ Property copy$default(Property property, IrProperty irProperty, int i, Object obj) {
            if ((i & 1) != 0) {
                irProperty = property.property;
            }
            return property.copy(irProperty);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrProperty getProperty() {
            return this.property;
        }

        public final Property copy(IrProperty property) {
            property.getClass();
            return new Property(property);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Property) && Intrinsics.areEqual(this.property, ((Property) other).property);
        }

        public final IrProperty getProperty() {
            return this.property;
        }

        public int hashCode() {
            return this.property.hashCode();
        }

        public String toString() {
            return DumpIrTreeKt.dump$default(this.property, (DumpIrTreeOptions) null, 1, (Object) null);
        }
    }

    public /* synthetic */ IrPropertyOrIrField(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private IrPropertyOrIrField() {
    }
}
