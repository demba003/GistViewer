package com.miquido.gistmvp.usecase

import com.miquido.gistmvp.data.repository.GistRemoteRepository

class GetUserUseCase(private val remoteRepository: GistRemoteRepository) {
    suspend operator fun invoke(name: String) = remoteRepository.getUser(name)
}
