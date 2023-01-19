#include <jni.h>
#include <string>
#include <vector>
#include "IRP.h"
#include "StdAfx.h"
#include "protdefs.h"

using namespace std;

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

string fixup(vector<string> inp){
    string result;
    for(auto a : inp){
        result += a;
        result += " ";
    }
    return result;
}

int ln = 79;

extern "C" JNIEXPORT jstring JNICALL
Java_com_jj15_iremote4_IRUtils_generateIRSeq(
        JNIEnv* env,
        jobject /* this */, jstring protoJ, jint device, jint subdevice, jint function)
        {
            string proto = jstring2string(env, protoJ);
            vector<string> Sequence;


            char irp[1024] = "";

            // Handle D, S, F
            int D = (int) device;
            int S = (int) subdevice;
            int F = (int) function;
            if (S >= 0)
                sprintf(irp, "Device=%d.%d\nFunction=%d\n", D, S, F);
            else
                sprintf(irp, "Device=%d\nFunction=%d\n", D, F);

            // Search for protocol
            char *prot = const_cast<char *>(proto.c_str());
            int p = -1;
            for (int i = 0; i < ln; i++)
                if (strcmp(prot, protdefs[i].prot) == 0) {
                    p = i;
                    break;
                }
            if (p < 0) {
                // Protocol not found, try for special protocols
                int M = 0;
                int L = 0;
                for (int i = 0; i < ln; i++)
                    prot[i] = toupper(prot[i]);
                if (sscanf(prot, "RC6-%d-%d", &M, &L) == 2) {
                    char temp[256];
                    sprintf (temp, "Define M=%d\nDefine L=%d\n", M, L);
                    strcat(irp, temp);
                    prot = "rc6-M-L";
                }
                else if (strcmp("NEC", prot) == 0)
                    prot = "nec2";
                else if (strcmp("NECX", prot) == 0)
                    prot = "NECx2";
                // Search again for protocol
                for (int i = 0; i < ln; i++)
                    if (strcmp(prot, protdefs[i].prot) == 0) {
                        p = i;
                        break;
                    }
                if (p < 0) {
                    printf ("Error: Unknown protocol\n");
                    return env->NewStringUTF(fixup(Sequence).c_str());
                }
            }
            strcat(irp, protdefs[p].def); // dest <- source (protocol from the array)

            // Encode
            IRP Irp;
            if (!Irp.readIrpString(irp)) {
                printf ("Error: Invalid IRP\n");
                return env->NewStringUTF(fixup(Sequence).c_str());
            }

            // Encode

            float seq[1024];
            Irp.m_value['D' - 'A'] = D;
            Irp.m_value['S' - 'A'] = S;
            Irp.m_value['F' - 'A'] = F;
            Irp.m_value['N' - 'A'] = -1;
            int s, r;
            Irp.generate(&s, &r, seq);


            // Output
            for (int i = 0; i < 2 *(s + r); i++) {
                printf("%s%g", i ? " " : "", seq[i]);
                Sequence.push_back(to_string(seq[i]));
            }
            return env->NewStringUTF(fixup(Sequence).c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_jj15_iremote4_IRUtils_params(
        JNIEnv* env,
        jobject /* this */, jstring protoJ){

    string proto = jstring2string(env, protoJ);
    char *prot = const_cast<char *>(proto.c_str());

    char irp[1024] = "";

    int p = -1;
    for (int i = 0; i < ln; i++) {
        if (strcmp(prot, protdefs[i].prot) == 0) {
            p = i;
            break;
        }
    }
    if (p < 0) {
        // Protocol not found, try for special protocols
        int M = 0;
        int L = 0;
        for (int i = 0; i < ln; i++)
            prot[i] = toupper(prot[i]);
        if (sscanf(prot, "RC6-%d-%d", &M, &L) == 2) {
            char temp[256];
            sprintf (temp, "Define M=%d\nDefine L=%d\n", M, L);
            strcat(irp, temp);
            prot = "rc6-M-L";
        }
        else if (strcmp("NEC", prot) == 0)
            prot = "nec2";
        else if (strcmp("NECX", prot) == 0)
            prot = "NECx2";
        // Search again for protocol
        for (int i = 0; i < ln; i++)
            if (strcmp(prot, protdefs[i].prot) == 0) {
                p = i;
                break;
            }
        if (p < 0) {
            printf ("Error: Unknown protocol\n");
            return env->NewStringUTF("null");
        }
    }

    return env->NewStringUTF(protdefs[p].def);
}

