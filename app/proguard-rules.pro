# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/newuser/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.support.v7.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.view.View
-keep public class * extends android.support.v4.app.Fragment
-keep class android.support.** {*;}

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
##########bean类不参与混淆##########
-keep class com.com.nanyixuan.zzyl_andorid.bean.**{*;}
# mobSdk的混淆代码
-keep class cn.smssdk.**{*;}
-keep class com.mob.**{*;}

-dontwarn com.mob.**
-dontwarn cn.smssdk.**
-dontwarn com.facebook.**
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
  #########友盟统计混淆##########d
 -keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }
 #########butterKnife混淆##########d
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }

 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }
 ##########retrofit2混淆##########
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.Nullable
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn retrofit2.**
-keep class com.squareup.okhttp.** { *;}
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions


 ##########androidUtilCode混淆##########
 -keep class com.blankj.utilcode.** { *; }
 -keepclassmembers class com.blankj.utilcode.** { *; }
 -dontwarn com.blankj.utilcode.**
  ##########loading混淆##########
 -keep class com.wang.avi.** { *; }
 -keep class com.wang.avi.indicators.** { *; }
 ##########支付混淆##########
 -dontwarn com.switfpass.pay.**
 -keep class com.swiftpass.pay.**{*;}
   #########二维码#########
 -keep class com.google.zxing-library.**{*;}
 -dontwarn com.google.zxing-library.**

 -keep class io.reactivex.**{*;}
  ##########百度地图混淆##########
 -keep class com.baidu.** {*;}
 -keep class vi.com.** {*;}
 -dontwarn com.baidu.**

 -keep class com.chad.library.adapter.** {
 *;
 }
 -keep class com.brtbeacon.**{*;}
 -dontwarn com.brtbeacon.**

 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

   ##########CYM Adapter##########
 -keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
 -keep public class * extends com.chad.library.adapter.base.BaseViewHolder
 -keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
      <init>(android.view.View);
 }
   ##########frecson##########
 # Keep our interfaces so they can be used by other ProGuard rules.
 # See http://sourceforge.net/p/proguard/bugs/466/
 -keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

 # Do not strip any method/class that is annotated with @DoNotStrip
 -keep @com.facebook.common.internal.DoNotStrip class *
 -keepclassmembers class * {
     @com.facebook.common.internal.DoNotStrip *;
 }

 # Keep native methods
 -keepclassmembers class * {
     native <methods>;
 }

 -dontwarn okio.**
 -dontwarn com.squareup.okhttp.**
 -dontwarn okhttp3.**
 -dontwarn javax.annotation.**
 -dontwarn com.android.volley.toolbox.**
 -dontwarn com.facebook.infer.**