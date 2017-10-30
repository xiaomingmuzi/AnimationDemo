#include <jni.h>
jstring
Java_com_lixm_animationdemo_utils_NativeUtil_getStrFromJNI(JNIEnv *env,jobject thiz) {
return (*env)->NewStringUTF(env, "I'm Str from jni libs!");
}