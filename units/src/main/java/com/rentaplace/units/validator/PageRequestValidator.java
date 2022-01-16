package com.rentaplace.units.validator;

import com.rentaplace.common.validator.Validator;
import com.rentaplace.units.model.domain.PageReq;
import com.rentaplace.units.exception.UnitsException;
import com.rentaplace.units.exception.error.UnitsErrorCodes;
import org.springframework.stereotype.Component;

@Component
    public class PageRequestValidator implements Validator<PageReq> {

    @Override
    public void validate(PageReq pageReq) throws UnitsException {
        if (pageReq.getPage() < 0 || pageReq.getPageSize() <= 0) {
            throw new UnitsException(UnitsErrorCodes.INVALID_PAGE_REQUEST);
        }
    }
}
