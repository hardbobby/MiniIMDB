#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_hardbobby_data_common_Keys_apiKey(JNIEnv *env, jobject instance) {
    std::string api_key = "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZTY1YjhhNWNhOWFjMTNmYTQyMjkxNmQ5NWE5YzM5ZCIsInN1YiI6IjVlOTdkZjY5MTlhYjU5MDAxNDQ2Y2E0YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FSvpipQB-aB8py7fCwpANX91vqVvnMIRG5Bm9h-5sCs\"";
    return env->NewStringUTF(api_key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_hardbobby_data_common_Keys_baseUrl(JNIEnv *env, jobject thiz) {
    std::string base_url = "https://api.themoviedb.org/3/";
    return env->NewStringUTF(base_url.c_str());
}
