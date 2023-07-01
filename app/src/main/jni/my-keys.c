//
// Created by João Luís on 26/08/2022.
//
#include <jni.h>
JNIEXPORT jstring JNICALL
Java_eu_jobernas_demoapicom_api_AppKeys_getApiKey(JNIEnv *env, jobject thiz) {
    // Replace *************** by your API Key
    return (*env)->NewStringUTF(env, "************");
}