package com.android.tools.r8.naming.mappinginformation;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class MappingInformationDiagnostics implements Diagnostic {
    private final String b;
    private final Position c;

    private MappingInformationDiagnostics(String str, TextPosition textPosition) {
        this.b = str;
        this.c = textPosition;
    }

    public static MappingInformationDiagnostics a(int i, String str, String str2) {
        return new MappingInformationDiagnostics("Could not find '" + str + "' for object with id '" + str2 + "'", new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics b(int i) {
        return new MappingInformationDiagnostics("Could not locate 'id' in the JSON object", new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics c(int i) {
        return new MappingInformationDiagnostics("The value of 'id' is not a valid string in the JSON object", new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics invalidResidualSignature(String str, int i) {
        return new MappingInformationDiagnostics("The residual signature mapping '" + str + "' is invalid'", new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics invalidResidualSignatureType(String str, int i) {
        return new MappingInformationDiagnostics("The residual signature mapping '" + str + "' is not of the same type as the member it describes.'", new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics notAllowedCombination(e eVar, e eVar2, int i) {
        return new MappingInformationDiagnostics("The mapping '" + eVar + "' is not allowed in combination with '" + eVar2 + "'", new TextPosition(1L, i, -1));
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.c;
    }

    public static MappingInformationDiagnostics a(int i, String str) {
        return new MappingInformationDiagnostics("Could not find a handler for ".concat(str), new TextPosition(1L, i, -1));
    }

    public static MappingInformationDiagnostics a(int i) {
        return new MappingInformationDiagnostics("Could not decode the information for the object with fileName 'sourceFile'", new TextPosition(1L, i, -1));
    }
}
