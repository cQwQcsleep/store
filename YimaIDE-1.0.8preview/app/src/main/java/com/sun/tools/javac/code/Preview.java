package com.sun.tools.javac.code;

import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Preview {
    protected static final Context.Key<Preview> previewKey = new Context.Key<>();
    private final boolean enabled;
    private final boolean forcePreview;
    private final Log log;
    private final Map<Integer, Source> majorVersionToSource;
    private final Names names;
    private final Source source;
    private final Set<JavaFileObject> sourcesWithPreviewFeatures = new HashSet();

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Preview$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Source$Feature;

        static {
            int[] iArr = new int[Source.Feature.values().length];
            $SwitchMap$com$sun$tools$javac$code$Source$Feature = iArr;
            try {
                iArr[Source.Feature.PRIMITIVE_PATTERNS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public Preview(Context context) {
        context.put(previewKey, this);
        Options optionsInstance = Options.instance(context);
        this.names = Names.instance(context);
        this.enabled = optionsInstance.isSet(Option.PREVIEW);
        this.log = Log.instance(context);
        this.source = Source.instance(context);
        this.forcePreview = optionsInstance.isSet("forcePreview");
        this.majorVersionToSource = initMajorVersionToSourceMap();
    }

    public static /* synthetic */ boolean b(Preview preview, Directive.ExportsDirective exportsDirective) {
        preview.getClass();
        return exportsDirective.packge.fullname == preview.names.jdk_internal_javac;
    }

    private Map<Integer, Source> initMajorVersionToSourceMap() {
        HashMap map = new HashMap();
        for (Target target : Target.values()) {
            int i = target.majorVersion;
            Source sourceLookup = Source.lookup(target.name);
            if (sourceLookup != null) {
                map.put(Integer.valueOf(i), sourceLookup);
            }
        }
        return map;
    }

    public static Preview instance(Context context) {
        Preview preview = (Preview) context.get(previewKey);
        return preview == null ? new Preview(context) : preview;
    }

    public void checkSourceLevel(JCDiagnostic.DiagnosticPosition diagnosticPosition, Source.Feature feature) {
        if (isPreview(feature) && !isEnabled()) {
            this.log.error(diagnosticPosition, disabledError(feature));
            return;
        }
        if (!feature.allowedInSource(this.source)) {
            this.log.error(diagnosticPosition, feature.error(this.source.name));
        }
        if (isEnabled() && isPreview(feature)) {
            warnPreview(diagnosticPosition, feature);
        }
    }

    public boolean declaredUsingPreviewFeature(Symbol symbol) {
        return false;
    }

    public JCDiagnostic.Error disabledError(Source.Feature feature) {
        Assert.check(!isEnabled());
        return feature.isPlural() ? CompilerProperties.Errors.PreviewFeatureDisabledPlural(feature.nameFragment()) : CompilerProperties.Errors.PreviewFeatureDisabled(feature.nameFragment());
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isPreview(Source.Feature feature) {
        if (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Source$Feature[feature.ordinal()] != 1) {
            return this.forcePreview;
        }
        return true;
    }

    public void markUsesPreview(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        this.sourcesWithPreviewFeatures.add(this.log.currentSourceFile());
    }

    public boolean participatesInPreview(Symtab symtab, Symbol symbol, Symbol symbol2) {
        if (symbol2.packge().modle == symbol.packge().modle) {
            return true;
        }
        return participatesInPreview(symtab, symbol.packge().modle);
    }

    public boolean usesPreview(JavaFileObject javaFileObject) {
        return this.sourcesWithPreviewFeatures.contains(javaFileObject);
    }

    public void warnPreview(JCDiagnostic.DiagnosticPosition diagnosticPosition, Source.Feature feature) {
        Assert.check(isEnabled());
        Assert.check(isPreview(feature));
        markUsesPreview(diagnosticPosition);
        this.log.warning(diagnosticPosition, feature.isPlural() ? CompilerProperties.LintWarnings.PreviewFeatureUsePlural(feature.nameFragment()) : CompilerProperties.LintWarnings.PreviewFeatureUse(feature.nameFragment()));
    }

    public boolean participatesInPreview(Symtab symtab, final Symbol.ModuleSymbol moduleSymbol) {
        return symtab.java_base.exports.stream().filter(new Predicate() { // from class: n7b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Preview.b(this.b, (Directive.ExportsDirective) obj);
            }
        }).anyMatch(new Predicate() { // from class: o7b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Directive.ExportsDirective) obj).modules.contains(moduleSymbol);
            }
        });
    }

    public JCDiagnostic.Error disabledError(JavaFileObject javaFileObject, int i) {
        Assert.check(!isEnabled());
        return CompilerProperties.Errors.PreviewFeatureDisabledClassfile(javaFileObject, this.majorVersionToSource.get(Integer.valueOf(i)).name);
    }

    public void warnPreview(int i, Source.Feature feature) {
        warnPreview(new JCDiagnostic.SimpleDiagnosticPosition(i), feature);
    }

    public void warnPreview(JavaFileObject javaFileObject, int i) {
        Assert.check(isEnabled());
        this.log.warning(CompilerProperties.LintWarnings.PreviewFeatureUseClassfile(javaFileObject, this.majorVersionToSource.get(Integer.valueOf(i)).name));
    }
}
