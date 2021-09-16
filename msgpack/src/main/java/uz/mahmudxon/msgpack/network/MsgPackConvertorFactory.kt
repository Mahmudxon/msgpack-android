package uz.mahmudxon.msgpack.network

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class MsgPackConvertorFactory(private val gson: Gson = Gson()) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type?, annotations: Array<Annotation?>?, retrofit: Retrofit?
    ): Converter<ResponseBody?, *> {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return MsgPackResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<Annotation?>?,
        methodAnnotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<*, RequestBody?> {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return MsgPackRequestBodyConverter(gson, adapter)
    }
}