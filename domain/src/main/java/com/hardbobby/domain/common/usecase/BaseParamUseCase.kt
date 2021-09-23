package com.hardbobby.domain.common.usecase

import com.hardbobby.domain.common.SimpleResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
abstract class BaseParamUseCase <in Param,T> {
    abstract suspend fun execute(param: Param): Flow<SimpleResult<T>>
}