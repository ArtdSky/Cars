package com.example.cars.domain.usecases

import com.example.cars.domain.repository.ResetRepository

/**
 * Use case  сброса базы данных приложения.
 * @param resetRepository экхемпляр ResetRepository.
 */
class ResetAppUseCase(
    private val resetRepository: ResetRepository
) {
    /**
     * Метод для вызова Use Case.
     * @return true в случае успеха, false в противоположном случае.
     */
    suspend operator fun invoke(): Boolean {
        return resetRepository.resetAppData()
    }
}