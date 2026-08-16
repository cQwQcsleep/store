# Keep API DTOs (kotlinx.serialization)
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.**
-keep,includedescriptorclasses class com.skyauto.app.**$$serializer { *; }
-keepclassmembers class com.skyauto.app.** {
    *** Companion;
}
-keepclasseswithmembers class com.skyauto.app.** {
    kotlinx.serialization.KSerializer serializer(...);
}