package com.blueground.units.validator;

import com.blueground.units.model.domain.PageReq;
import com.blueground.units.exception.UnitsException;
import com.blueground.units.exception.error.UnitsErrorCodes;
import com.blueground.units.exception.utils.UnitsExceptionUtils;
import org.springframework.stereotype.Component;

@Component
public class PageRequestValidator implements Validator<PageReq> {

    @Override
    public void validate(PageReq pageReq) throws UnitsException {
        if (pageReq.getPage() < 0 || pageReq.getPageSize() <= 0)
            throw new UnitsException(UnitsExceptionUtils.createErrorDetails(UnitsErrorCodes.INVALID_PAGE_REQUEST));
    }
}
