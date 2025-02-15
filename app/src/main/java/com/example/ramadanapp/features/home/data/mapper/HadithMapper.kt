package com.example.ramadanapp.features.home.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.data.models.dto.HadithDto
import com.example.ramadanapp.features.home.domain.models.Hadith

object HadithMapper : Mapper<HadithDto, Hadith, Unit>() {
    override fun dtoToDomain(model: HadithDto): Hadith {
        return Hadith(
            id = model.id ?: 0,
            text = model.text.orEmpty(),
            reference = model.reference.orEmpty()
        )
    }
}