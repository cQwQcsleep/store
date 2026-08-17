package com.sun.tools.javac.code;

import com.sun.source.util.ParameterNameProvider;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MissingInfoHandler {
    protected static final Context.Key<MissingInfoHandler> missingInfoHandlerWrapperKey = new Context.Key<>();
    private final Names names;
    private ParameterNameProvider parameterNameProvider;

    public MissingInfoHandler(Context context) {
        context.put(missingInfoHandlerWrapperKey, this);
        this.names = Names.instance(context);
    }

    public static MissingInfoHandler instance(Context context) {
        MissingInfoHandler missingInfoHandler = (MissingInfoHandler) context.get(missingInfoHandlerWrapperKey);
        return missingInfoHandler == null ? new MissingInfoHandler(context) : missingInfoHandler;
    }

    public Name getParameterName(Symbol.ParamSymbol paramSymbol) {
        CharSequence parameterName;
        ParameterNameProvider parameterNameProvider = this.parameterNameProvider;
        if (parameterNameProvider == null || (parameterName = parameterNameProvider.getParameterName(paramSymbol)) == null) {
            return null;
        }
        return this.names.fromString(parameterName.toString());
    }

    public void setDelegate(ParameterNameProvider parameterNameProvider) {
        this.parameterNameProvider = parameterNameProvider;
    }
}
