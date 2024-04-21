package com.turkcell.rentacar.adapter.findex;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class FindexServiceAdapter implements FindexService {
    @Override
    public int getFindexScoreForIndividualCustomer(String identityNo) {
        Random random = new Random();
        int findexScore = random.nextInt(1900);
        return findexScore;
    }

    @Override
    public int getFindexScoreForCompanyCustomer(String taxNo) {
        Random random = new Random();
        int findexScore = random.nextInt(1900);
        return findexScore;
    }
}
