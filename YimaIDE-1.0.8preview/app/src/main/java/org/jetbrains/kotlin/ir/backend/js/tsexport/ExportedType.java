package org.jetbrains.kotlin.ir.backend.js.tsexport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0012\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00002\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0000\u0082\u0001\u0012\u001f !\"#$%&'()*+,-./0¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "", "<init>", "()V", "replaceTypes", "substitution", "", "withNullability", "nullable", "", "withImplicitlyExported", "implicitlyExportedType", "exportedSupertype", "Primitive", "LiteralType", "Array", "Function", "ConstructorType", "ClassType", "TypeParameterRef", "Nullable", "NonNullable", "ErrorType", "TypeOf", "ObjectsParentType", "InlineInterfaceType", "InlineArrayType", "UnionType", "IntersectionType", "PropertyType", "ImplicitlyExportedType", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Array;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ClassType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ConstructorType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ErrorType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Function;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ImplicitlyExportedType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$InlineArrayType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$InlineInterfaceType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$IntersectionType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$NonNullable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Nullable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ObjectsParentType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$PropertyType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$TypeOf;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$TypeParameterRef;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$UnionType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExportedType {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\tH\u0016J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Array;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "elementType", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getElementType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "replaceTypes", "substitution", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Array extends ExportedType {
        private final ExportedType elementType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Array(ExportedType exportedType) {
            super(null);
            exportedType.getClass();
            this.elementType = exportedType;
        }

        public static /* synthetic */ Array copy$default(Array array, ExportedType exportedType, int i, Object obj) {
            if ((i & 1) != 0) {
                exportedType = array.elementType;
            }
            return array.copy(exportedType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ExportedType getElementType() {
            return this.elementType;
        }

        public final Array copy(ExportedType elementType) {
            elementType.getClass();
            return new Array(elementType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Array) && Intrinsics.areEqual(this.elementType, ((Array) other).elementType);
        }

        public final ExportedType getElementType() {
            return this.elementType;
        }

        public int hashCode() {
            return this.elementType.hashCode();
        }

        @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedType
        public ExportedType replaceTypes(Map<ExportedType, ? extends ExportedType> substitution) {
            substitution.getClass();
            ExportedType exportedType = substitution.get(this);
            return exportedType == null ? new Array(this.elementType.replaceTypes(substitution)) : exportedType;
        }

        public String toString() {
            return "Array(elementType=" + this.elementType + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ConstructorType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "typeParameters", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedTypeParameter;", "returnType", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getTypeParameters", "()Ljava/util/List;", "getReturnType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ConstructorType extends ExportedType {
        private final ExportedType returnType;
        private final List<ExportedTypeParameter> typeParameters;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ConstructorType(List<ExportedTypeParameter> list, ExportedType exportedType) {
            super(null);
            list.getClass();
            exportedType.getClass();
            this.typeParameters = list;
            this.returnType = exportedType;
        }

        public final ExportedType getReturnType() {
            return this.returnType;
        }

        public final List<ExportedTypeParameter> getTypeParameters() {
            return this.typeParameters;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ErrorType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "comment", "", "<init>", "(Ljava/lang/String;)V", "getComment", "()Ljava/lang/String;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorType extends ExportedType {
        private final String comment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ErrorType(String str) {
            super(null);
            str.getClass();
            this.comment = str;
        }

        public final String getComment() {
            return this.comment;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Function;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "parameters", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedParameter;", "returnType", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getParameters", "()Ljava/util/List;", "getReturnType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Function extends ExportedType {
        private final List<ExportedParameter> parameters;
        private final ExportedType returnType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Function(List<ExportedParameter> list, ExportedType exportedType) {
            super(null);
            list.getClass();
            exportedType.getClass();
            this.parameters = list;
            this.returnType = exportedType;
        }

        public final List<ExportedParameter> getParameters() {
            return this.parameters;
        }

        public final ExportedType getReturnType() {
            return this.returnType;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\t\u0010\f\u001a\u00020\u0001HÆ\u0003J\t\u0010\r\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ImplicitlyExportedType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "type", "exportedSupertype", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "getExportedSupertype", "withNullability", "nullable", "", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class ImplicitlyExportedType extends ExportedType {
        private final ExportedType exportedSupertype;
        private final ExportedType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImplicitlyExportedType(ExportedType exportedType, ExportedType exportedType2) {
            super(null);
            exportedType.getClass();
            exportedType2.getClass();
            this.type = exportedType;
            this.exportedSupertype = exportedType2;
        }

        public static /* synthetic */ ImplicitlyExportedType copy$default(ImplicitlyExportedType implicitlyExportedType, ExportedType exportedType, ExportedType exportedType2, int i, Object obj) {
            if ((i & 1) != 0) {
                exportedType = implicitlyExportedType.type;
            }
            if ((i & 2) != 0) {
                exportedType2 = implicitlyExportedType.exportedSupertype;
            }
            return implicitlyExportedType.copy(exportedType, exportedType2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ExportedType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ExportedType getExportedSupertype() {
            return this.exportedSupertype;
        }

        public final ImplicitlyExportedType copy(ExportedType type, ExportedType exportedSupertype) {
            type.getClass();
            exportedSupertype.getClass();
            return new ImplicitlyExportedType(type, exportedSupertype);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImplicitlyExportedType)) {
                return false;
            }
            ImplicitlyExportedType implicitlyExportedType = (ImplicitlyExportedType) other;
            return Intrinsics.areEqual(this.type, implicitlyExportedType.type) && Intrinsics.areEqual(this.exportedSupertype, implicitlyExportedType.exportedSupertype);
        }

        public final ExportedType getExportedSupertype() {
            return this.exportedSupertype;
        }

        public final ExportedType getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.exportedSupertype.hashCode();
        }

        public String toString() {
            return "ImplicitlyExportedType(type=" + this.type + ", exportedSupertype=" + this.exportedSupertype + ')';
        }

        @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedType
        public ImplicitlyExportedType withNullability(boolean nullable) {
            return new ImplicitlyExportedType(this.type.withNullability(nullable), this.exportedSupertype.withNullability(nullable));
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$InlineArrayType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "elements", "", "<init>", "(Ljava/util/List;)V", "getElements", "()Ljava/util/List;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class InlineArrayType extends ExportedType {
        private final List<ExportedType> elements;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public InlineArrayType(List<? extends ExportedType> list) {
            super(null);
            list.getClass();
            this.elements = list;
        }

        public final List<ExportedType> getElements() {
            return this.elements;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$InlineInterfaceType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "members", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedDeclaration;", "<init>", "(Ljava/util/List;)V", "getMembers", "()Ljava/util/List;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class InlineInterfaceType extends ExportedType {
        private final List<ExportedDeclaration> members;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public InlineInterfaceType(List<? extends ExportedDeclaration> list) {
            super(null);
            list.getClass();
            this.members = list;
        }

        public final List<ExportedDeclaration> getMembers() {
            return this.members;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$IntersectionType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "lhs", "rhs", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getLhs", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "getRhs", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class IntersectionType extends ExportedType {
        private final ExportedType lhs;
        private final ExportedType rhs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IntersectionType(ExportedType exportedType, ExportedType exportedType2) {
            super(null);
            exportedType.getClass();
            exportedType2.getClass();
            this.lhs = exportedType;
            this.rhs = exportedType2;
        }

        public final ExportedType getLhs() {
            return this.lhs;
        }

        public final ExportedType getRhs() {
            return this.rhs;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$NonNullable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "baseType", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getBaseType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class NonNullable extends ExportedType {
        private final ExportedType baseType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NonNullable(ExportedType exportedType) {
            super(null);
            exportedType.getClass();
            this.baseType = exportedType;
        }

        public final ExportedType getBaseType() {
            return this.baseType;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Nullable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "baseType", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getBaseType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Nullable extends ExportedType {
        private final ExportedType baseType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Nullable(ExportedType exportedType) {
            super(null);
            exportedType.getClass();
            this.baseType = exportedType;
        }

        public final ExportedType getBaseType() {
            return this.baseType;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ObjectsParentType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "constructor", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getConstructor", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ObjectsParentType extends ExportedType {
        private final ExportedType constructor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ObjectsParentType(ExportedType exportedType) {
            super(null);
            exportedType.getClass();
            this.constructor = exportedType;
        }

        public final ExportedType getConstructor() {
            return this.constructor;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$PropertyType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "container", "propertyName", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getContainer", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "getPropertyName", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class PropertyType extends ExportedType {
        private final ExportedType container;
        private final ExportedType propertyName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PropertyType(ExportedType exportedType, ExportedType exportedType2) {
            super(null);
            exportedType.getClass();
            exportedType2.getClass();
            this.container = exportedType;
            this.propertyName = exportedType2;
        }

        public final ExportedType getContainer() {
            return this.container;
        }

        public final ExportedType getPropertyName() {
            return this.propertyName;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$TypeOf;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "classType", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ClassType;", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ClassType;)V", "getClassType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ClassType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class TypeOf extends ExportedType {
        private final ClassType classType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TypeOf(ClassType classType) {
            super(null);
            classType.getClass();
            this.classType = classType;
        }

        public static /* synthetic */ TypeOf copy$default(TypeOf typeOf, ClassType classType, int i, Object obj) {
            if ((i & 1) != 0) {
                classType = typeOf.classType;
            }
            return typeOf.copy(classType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ClassType getClassType() {
            return this.classType;
        }

        public final TypeOf copy(ClassType classType) {
            classType.getClass();
            return new TypeOf(classType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TypeOf) && Intrinsics.areEqual(this.classType, ((TypeOf) other).classType);
        }

        public final ClassType getClassType() {
            return this.classType;
        }

        public int hashCode() {
            return this.classType.hashCode();
        }

        public String toString() {
            return "TypeOf(classType=" + this.classType + ')';
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$TypeParameterRef;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "typeParameter", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedTypeParameter;", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedTypeParameter;)V", "getTypeParameter", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedTypeParameter;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class TypeParameterRef extends ExportedType {
        private final ExportedTypeParameter typeParameter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TypeParameterRef(ExportedTypeParameter exportedTypeParameter) {
            super(null);
            exportedTypeParameter.getClass();
            this.typeParameter = exportedTypeParameter;
        }

        public static /* synthetic */ TypeParameterRef copy$default(TypeParameterRef typeParameterRef, ExportedTypeParameter exportedTypeParameter, int i, Object obj) {
            if ((i & 1) != 0) {
                exportedTypeParameter = typeParameterRef.typeParameter;
            }
            return typeParameterRef.copy(exportedTypeParameter);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ExportedTypeParameter getTypeParameter() {
            return this.typeParameter;
        }

        public final TypeParameterRef copy(ExportedTypeParameter typeParameter) {
            typeParameter.getClass();
            return new TypeParameterRef(typeParameter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TypeParameterRef) && Intrinsics.areEqual(this.typeParameter, ((TypeParameterRef) other).typeParameter);
        }

        public final ExportedTypeParameter getTypeParameter() {
            return this.typeParameter;
        }

        public int hashCode() {
            return this.typeParameter.hashCode();
        }

        public String toString() {
            return "TypeParameterRef(typeParameter=" + this.typeParameter + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$UnionType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "lhs", "rhs", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getLhs", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "getRhs", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class UnionType extends ExportedType {
        private final ExportedType lhs;
        private final ExportedType rhs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnionType(ExportedType exportedType, ExportedType exportedType2) {
            super(null);
            exportedType.getClass();
            exportedType2.getClass();
            this.lhs = exportedType;
            this.rhs = exportedType2;
        }

        public final ExportedType getLhs() {
            return this.lhs;
        }

        public final ExportedType getRhs() {
            return this.rhs;
        }
    }

    public /* synthetic */ ExportedType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public ExportedType replaceTypes(Map<ExportedType, ? extends ExportedType> substitution) {
        substitution.getClass();
        ExportedType exportedType = substitution.get(this);
        return exportedType == null ? this : exportedType;
    }

    public final ExportedType withImplicitlyExported(boolean implicitlyExportedType, ExportedType exportedSupertype) {
        exportedSupertype.getClass();
        return implicitlyExportedType ? new ImplicitlyExportedType(this, exportedSupertype) : this;
    }

    public ExportedType withNullability(boolean nullable) {
        return nullable ? new Nullable(this) : this;
    }

    private ExportedType() {
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003\n\u000b\fB\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0003\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType;", "T", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "StringLiteralType", "NumberLiteralType", "BooleanLiteralType", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$BooleanLiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$NumberLiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$StringLiteralType;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class LiteralType<T> extends ExportedType {
        private final T value;

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$BooleanLiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType;", "", "value", "<init>", "(Z)V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class BooleanLiteralType extends LiteralType<Boolean> {
            public BooleanLiteralType(boolean z) {
                super(Boolean.valueOf(z), null);
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$NumberLiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType;", "", "value", "<init>", "(Ljava/lang/Number;)V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class NumberLiteralType extends LiteralType<Number> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NumberLiteralType(Number number) {
                super(number, null);
                number.getClass();
            }
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType$StringLiteralType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$LiteralType;", "", "value", "<init>", "(Ljava/lang/String;)V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class StringLiteralType extends LiteralType<String> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StringLiteralType(String str) {
                super(str, null);
                str.getClass();
            }
        }

        private LiteralType(T t) {
            super(null);
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }

        public /* synthetic */ LiteralType(Object obj, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj);
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0011\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0011\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "typescript", "", "<init>", "(Ljava/lang/String;)V", "getTypescript", "()Ljava/lang/String;", "Boolean", "Number", "BigInt", "ByteArray", "ShortArray", "IntArray", "FloatArray", "DoubleArray", "LongArray", "String", "Throwable", "Any", "Undefined", "Unit", "Nothing", "UniqueSymbol", "Unknown", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Any;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$BigInt;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Boolean;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$ByteArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$DoubleArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$FloatArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$IntArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$LongArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Nothing;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Number;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$ShortArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$String;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Throwable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Undefined;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$UniqueSymbol;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Unit;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Unknown;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class Primitive extends ExportedType {
        private final java.lang.String typescript;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Any;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Any extends Primitive {
            public static final Any INSTANCE = new Any();

            private Any() {
                super("any", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$BigInt;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class BigInt extends Primitive {
            public static final BigInt INSTANCE = new BigInt();

            private BigInt() {
                super("bigint", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Boolean;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Boolean extends Primitive {
            public static final Boolean INSTANCE = new Boolean();

            private Boolean() {
                super("boolean", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$ByteArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ByteArray extends Primitive {
            public static final ByteArray INSTANCE = new ByteArray();

            private ByteArray() {
                super("Int8Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$DoubleArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class DoubleArray extends Primitive {
            public static final DoubleArray INSTANCE = new DoubleArray();

            private DoubleArray() {
                super("Float64Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$FloatArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FloatArray extends Primitive {
            public static final FloatArray INSTANCE = new FloatArray();

            private FloatArray() {
                super("Float32Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$IntArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class IntArray extends Primitive {
            public static final IntArray INSTANCE = new IntArray();

            private IntArray() {
                super("Int32Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$LongArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class LongArray extends Primitive {
            public static final LongArray INSTANCE = new LongArray();

            private LongArray() {
                super("BigInt64Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Nothing;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Nothing extends Primitive {
            public static final Nothing INSTANCE = new Nothing();

            private Nothing() {
                super("never", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Number;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Number extends Primitive {
            public static final Number INSTANCE = new Number();

            private Number() {
                super("number", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$ShortArray;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ShortArray extends Primitive {
            public static final ShortArray INSTANCE = new ShortArray();

            private ShortArray() {
                super("Int16Array", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$String;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class String extends Primitive {
            public static final String INSTANCE = new String();

            private String() {
                super("string", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Throwable;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Throwable extends Primitive {
            public static final Throwable INSTANCE = new Throwable();

            private Throwable() {
                super("Error", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Undefined;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Undefined extends Primitive {
            public static final Undefined INSTANCE = new Undefined();

            private Undefined() {
                super("undefined", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$UniqueSymbol;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class UniqueSymbol extends Primitive {
            public static final UniqueSymbol INSTANCE = new UniqueSymbol();

            private UniqueSymbol() {
                super("unique symbol", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Unit;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Unit extends Primitive {
            public static final Unit INSTANCE = new Unit();

            private Unit() {
                super("void", null);
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive$Unknown;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$Primitive;", "<init>", "()V", "withNullability", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "nullable", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Unknown extends Primitive {
            public static final Unknown INSTANCE = new Unknown();

            private Unknown() {
                super("unknown", null);
            }

            @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedType
            public ExportedType withNullability(boolean nullable) {
                return nullable ? this : new NonNullable(this);
            }
        }

        private Primitive(java.lang.String str) {
            super(null);
            this.typescript = str;
        }

        public final java.lang.String getTypescript() {
            return this.typescript;
        }

        public /* synthetic */ Primitive(java.lang.String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u001c\u0010\u0016\u001a\u00020\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0018H\u0016J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J/\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType$ClassType;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "name", "", "arguments", "", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lorg/jetbrains/kotlin/name/ClassId;)V", "getName", "()Ljava/lang/String;", "getArguments", "()Ljava/util/List;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "equals", "", "other", "", "hashCode", "", "replaceTypes", "substitution", "", "component1", "component2", "component3", "copy", "toString", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class ClassType extends ExportedType {
        private final List<ExportedType> arguments;
        private final ClassId classId;
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ClassType(String str, List<? extends ExportedType> list, ClassId classId) {
            super(null);
            str.getClass();
            list.getClass();
            this.name = str;
            this.arguments = list;
            this.classId = classId;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassType copy$default(ClassType classType, String str, List list, ClassId classId, int i, Object obj) {
            if ((i & 1) != 0) {
                str = classType.name;
            }
            if ((i & 2) != 0) {
                list = classType.arguments;
            }
            if ((i & 4) != 0) {
                classId = classType.classId;
            }
            return classType.copy(str, list, classId);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<ExportedType> component2() {
            return this.arguments;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ClassId getClassId() {
            return this.classId;
        }

        public final ClassType copy(String name, List<? extends ExportedType> arguments, ClassId classId) {
            name.getClass();
            arguments.getClass();
            return new ClassType(name, arguments, classId);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ClassType) && Intrinsics.areEqual(this.classId, ((ClassType) other).classId);
            }
            return true;
        }

        public final List<ExportedType> getArguments() {
            return this.arguments;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            ClassId classId = this.classId;
            if (classId != null) {
                return classId.hashCode();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedType
        public ExportedType replaceTypes(Map<ExportedType, ? extends ExportedType> substitution) {
            substitution.getClass();
            ExportedType exportedType = substitution.get(this);
            if (exportedType != null) {
                return exportedType;
            }
            List<ExportedType> list = this.arguments;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((ExportedType) it.next()).replaceTypes(substitution));
            }
            return copy$default(this, null, arrayList, null, 5, null);
        }

        public String toString() {
            return "ClassType(name=" + this.name + ", arguments=" + this.arguments + ", classId=" + this.classId + ')';
        }

        public /* synthetic */ ClassType(String str, List list, ClassId classId, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, (i & 4) != 0 ? null : classId);
        }
    }
}
