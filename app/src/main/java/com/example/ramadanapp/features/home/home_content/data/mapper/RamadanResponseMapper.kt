package com.example.ramadanapp.features.home.home_content.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.home_content.data.models.dto.CategoryDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.RamadanResponseDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.SectionDto
import com.example.ramadanapp.features.home.home_content.data.models.entity.CategoryEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.SectionEntity
import com.example.ramadanapp.features.home.home_content.domain.models.Category
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.models.Section

object RamadanResponseMapper : Mapper<RamadanResponseDto, RamadanResponse, RamadanResponseEntity?>() {

    override fun dtoToDomain(model: RamadanResponseDto): RamadanResponse {
        return RamadanResponse(
            sections = model.sections?.map { it.toDomain() } ?: emptyList(),
            totalVideoCount = model.totalVideoCount ?: 0,
            playlistCount = model.playlistCount ?: 0
        )
    }

    override fun domainToEntity(model: RamadanResponse): RamadanResponseEntity {
        return RamadanResponseEntity(
            sections = model.sections.map { it.toEntity() },
            totalVideoCount = model.totalVideoCount,
            playlistCount = model.playlistCount
        )
    }

    override fun entityToDomain(model: RamadanResponseEntity?): RamadanResponse {
        return model?.let {
            RamadanResponse(
                sections = it.sections.map { section -> section.toDomain() },
                totalVideoCount = it.totalVideoCount,
                playlistCount = it.playlistCount
            )
        } ?: RamadanResponse(emptyList(), 0, 0)
    }

    private fun SectionDto.toDomain(): Section {
        return Section(
            title = this.title.orEmpty(),
            categories = this.categories?.map { it.toDomain() } ?: emptyList()
        )
    }

    private fun Section.toEntity(): SectionEntity {
        return SectionEntity(
            title = this.title,
            categories = this.categories.map { it.toEntity() }
        )
    }

    private fun SectionEntity.toDomain(): Section {
        return Section(
            title = this.title,
            categories = this.categories.map { it.toDomain() }
        )
    }

    private fun CategoryDto.toDomain(): Category {
        return Category(
            title = this.title.orEmpty(),
            url = this.url.orEmpty(),
            playlistId = this.playlistId.orEmpty()

        )
    }

    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(
            title = this.title,
            url = this.url,
            playlistId = this.playlistId

        )
    }

    private fun CategoryEntity.toDomain(): Category {
        return Category(
            title = this.title,
            url = this.url,
            playlistId = this.playlistId

        )
    }
}