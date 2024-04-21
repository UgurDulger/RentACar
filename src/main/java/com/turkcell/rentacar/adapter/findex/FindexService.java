package com.turkcell.rentacar.adapter.findex;

public interface FindexService {
    int getFindexScoreForIndividualCustomer(String identityNo);

    int getFindexScoreForCompanyCustomer(String taxNo);

}
