package com.miquido.gistmvp.usecase

import com.miquido.gistmvp.data.repository.GistRemoteRepository

class GetGistDetailsUseCase(private val remoteRepository: GistRemoteRepository) {
    suspend operator fun invoke(id: String) = remoteRepository.getGist(id)
}
