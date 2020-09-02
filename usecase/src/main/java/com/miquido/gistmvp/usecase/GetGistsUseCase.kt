package com.miquido.gistmvp.usecase

import com.miquido.gistmvp.data.repository.GistRemoteRepository

class GetGistsUseCase(private val remoteRepository: GistRemoteRepository) {
    suspend operator fun invoke() = remoteRepository.getGists()
}
