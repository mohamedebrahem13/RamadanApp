package com.example.ramadanapp.features.home.home_content.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.home_content.data.models.dto.CategoryDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.ItemDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.RamadanResponseDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.SectionDto
import com.example.ramadanapp.features.home.home_content.data.models.entity.CategoryEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.ItemEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.SectionEntity
import com.example.ramadanapp.features.home.home_content.domain.models.Category
import com.example.ramadanapp.features.home.home_content.domain.models.Item
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.models.Section

object RamadanResponseMapper : Mapper<RamadanResponseDto, RamadanResponse, RamadanResponseEntity?>() {

    override fun dtoToDomain(model: RamadanResponseDto): RamadanResponse {
        return RamadanResponse(
            sections = model.sections.orEmpty().map { it.toDomain() },
            items = model.items.orEmpty().map { it.toDomain() }
        )
    }

    override fun domainToEntity(model: RamadanResponse): RamadanResponseEntity {
        return RamadanResponseEntity(
            sections = model.sections.map { it.toEntity() },
            items = model.items.map { it.toEntity() }
        )
    }

    override fun entityToDomain(model: RamadanResponseEntity?): RamadanResponse {
        return if (model == null) {
            RamadanResponse(sections = emptyList(), items = emptyList())
        } else {
            RamadanResponse(
                sections = model.sections.orEmpty().map { it.toDomain() },
                items = model.items.orEmpty().map { it.toDomain() }
            )
        }
    }

    private fun SectionDto.toDomain(): Section {
        return Section(
            title = this.title.orEmpty(),
            categories = this.categories.orEmpty().map { it.toDomain() }
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
            url = this.url.orEmpty()
        )
    }

    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(
            title = this.title,
            url = this.url
        )
    }

    private fun CategoryEntity.toDomain(): Category {
        return Category(
            title = this.title,
            url = this.url
        )
    }

    private fun ItemDto.toDomain(): Item {
        return Item(
            title = this.title.orEmpty(),
            url = this.url.orEmpty(),
            category = this.category.orEmpty()
        )
    }

    private fun Item.toEntity(): ItemEntity {
        return ItemEntity(
            title = this.title,
            url = this.url,
            category = this.category
        )
    }

    private fun ItemEntity.toDomain(): Item {
        return Item(
            title = this.title,
            url = this.url,
            category = this.category
        )
    }
}
