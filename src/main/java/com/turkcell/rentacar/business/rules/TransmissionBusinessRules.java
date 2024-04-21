package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.TransmissionMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransmissionBusinessRules {
    TransmissionRepository transmissionRepository;

    public void transmissionNameCanNotBeDuplicated(String transmissionName){
        Optional<Transmission> transmission = transmissionRepository.findByNameIgnoreCase(transmissionName);
        if(transmission.isPresent()){
            throw new BusinessException(TransmissionMessages.transmissionAlreadyExists);
        }
    }

    public void transmissionShouldBeExist(int transmissionId) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(transmissionId);
        if (foundOptionalTransmission.isEmpty()) {
            throw new BusinessException(TransmissionMessages.transmissionNotFound);
        }
    }
}
