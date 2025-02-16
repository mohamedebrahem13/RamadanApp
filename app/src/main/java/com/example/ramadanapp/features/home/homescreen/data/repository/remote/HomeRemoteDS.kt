package com.example.ramadanapp.features.home.homescreen.data.repository.remote

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.homescreen.data.models.dto.VideoItemDto
import com.example.ramadanapp.features.home.homescreen.data.models.dto.VideoResponseDto
import com.example.ramadanapp.features.home.homescreen.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRemoteDS @Inject constructor(
    private val networkProvider: INetworkProvider
) : IHomeRemoteDS {

    override suspend fun fetchHomeData(): VideoResponseDto {
        return VideoResponseDto(
            data = listOf(
                // رمضان ٢٣
                VideoItemDto("كل يوم آية", "رمضان ٢٣", "كل يوم آية 1 - محمد الغليظ", "https://www.youtube.com/watch?v=tHvfqbfQuXg"),
                VideoItemDto("كل يوم آية", "رمضان ٢٣", "كل يوم آية 2 - محمد الغليظ", "https://www.youtube.com/watch?v=iQQGgB9nFO0"),
                VideoItemDto("كل يوم آية", "رمضان ٢٣", "كل يوم آية 3 - محمد الغليظ", "https://www.youtube.com/watch?v=bXTxrkTk5EE"),
                VideoItemDto("كل يوم آية", "رمضان ٢٣", "كل يوم آية 4 - محمد الغليظ", "https://www.youtube.com/watch?v=TVSDsp6iWQo"),
                VideoItemDto("كل يوم آية", "رمضان ٢٣", "كل يوم آية 5 - محمد الغليظ", "https://www.youtube.com/watch?v=myoJ8nRW29Y"),

                // متفرقات - هموم الشباب
                VideoItemDto("هموم الشباب", "متفرقات", "لماذا هذه السلسلة؟", "https://www.youtube.com/watch?v=Q_RKRIhNwUQ"),
                VideoItemDto("هموم الشباب", "متفرقات", "كيف أترك المعصية؟", "https://www.youtube.com/watch?v=o8PP5NxefHk"),
                VideoItemDto("هموم الشباب", "متفرقات", "كيف أُرضي أبَوَي؟", "https://www.youtube.com/watch?v=zMWU3NoGxe4"),
                VideoItemDto("هموم الشباب", "متفرقات", "كيف أجدُ عملاً مناسباً؟", "https://www.youtube.com/watch?v=pRwL77nI2y0"),
                VideoItemDto("هموم الشباب", "متفرقات", "كيف لي بالزواج؟", "https://www.youtube.com/watch?v=q5nYNNFv5fc"),
                VideoItemDto("هموم الشباب", "متفرقات", "ماذا أقرأ لأفهم ديني؟", "https://www.youtube.com/watch?v=asgrdqnTwJo"),
                VideoItemDto("هموم الشباب", "متفرقات", "كيف أجمعُ بين الدنيا و الآخرة؟", "https://www.youtube.com/watch?v=EWfn9V1vRms"),

                // Add your new category here
                // سمير مصطفي
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ عقيدة القضاء والقدر جـ 2 ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=tldDnDX5UKM&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=1&pp=iAQB"),
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ عقيدة القضاء والقدر ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=qSJ_4Y0jRxE&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=2&pp=iAQB"),
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ ثمرات الايمان بالقضاء والقدر ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=YAKQEmNC944&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=3&pp=iAQB"),
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ واجبات القدر واجب الرضا ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=hGJe9SAO49c&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=4&pp=iAQB"),
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ واجبات القدر واجب الرضا 2 ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=25PUsDmDAoU&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=5&pp=iAQB"),
                VideoItemDto("سمير مصطفي", "سلسلة القضاء والقدر", "مسجد الحق ۞ واجبات القدر واجب الرضا 3 ۞ للشيخ سمير مصطفى", "https://www.youtube.com/watch?v=6Y5KQuc2xQI&list=PLSUcSqxe9RhyS7_hi2I5D7CH2sACsPjFW&index=6&pp=iAQB")
            )
        )
    }
}

