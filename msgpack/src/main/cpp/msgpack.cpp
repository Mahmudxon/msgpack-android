#include <jni.h>
#include <string>
#include "json.hpp"
#include <jni.h>

using json = nlohmann::json;
using namespace std;
typedef std::vector<uint8_t> bytearray;

static string jstring2string(JNIEnv* env, jstring jstr)
{
    if ( !jstr ) string();

    const jsize len = env->GetStringUTFLength(jstr);
    const char* str = env->GetStringUTFChars(jstr, (jboolean *)0);
    string res(str, len);
    env->ReleaseStringUTFChars(jstr, str);

    return res;
}

extern "C" JNIEXPORT jstring JNICALL
Java_uz_mahmudxon_msgpack_Msgpack_convertToJson(
        JNIEnv *env,
        jobject thiz,
        jbyteArray jmsgpack
)
{
    jbyte *msgpack = env->GetByteArrayElements(jmsgpack, nullptr);
    jsize msgpacklen = env->GetArrayLength(jmsgpack);
    json j = json::from_msgpack(bytearray(msgpack, msgpack + msgpacklen));
    env->ReleaseByteArrayElements(jmsgpack, msgpack, JNI_ABORT);
    return env->NewStringUTF(j.dump().c_str());
}
extern "C" JNIEXPORT jbyteArray JNICALL
Java_uz_mahmudxon_msgpack_Msgpack_convertFromJson(
        JNIEnv *env,
        jobject thiz,
        jstring jjson
)
{
    auto j = json::parse(jstring2string(env, jjson));
    bytearray m = json::to_msgpack(j);
    jbyteArray r = env->NewByteArray(m.size());
    env->SetByteArrayRegion(r, 0, m.size(), (jbyte *)m.data());
    return r;
}