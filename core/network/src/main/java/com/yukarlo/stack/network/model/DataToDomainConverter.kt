package com.yukarlo.stack.network.model

import com.yukarlo.core.domain.DomainModel

interface DataToDomainConverter<Input : DataModel, Output : DomainModel> {
    fun convert(input: Input): Output
}